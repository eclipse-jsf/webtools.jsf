/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.preview;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.FunctionMapper;
import javax.servlet.jsp.el.VariableResolver;

import org.apache.commons.el.ExpressionEvaluatorImpl;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.jsp.core.el.JSFELParserHelper;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;

/**
 * This is a static class. But it has "session" concept in it. To make it static
 * is only to simplify its use.
 * 
 * This class currently is only used by TagConverter, when calculating the
 * displayed string for resource bundle items.
 * 
 * XXX: in the future, if we want to this to be non-static, we may incorportate
 * it into the context of the tag converter.
 * 
 * @author mengbo
 * @version 1.5
 */
// TODO: we may consider support cache the properties.
public class PageExpressionContext {
	private static final Logger _log = PDPlugin
			.getLogger(PageExpressionContext.class);

	static PageExpressionContext _current;

	List _pageVarProviders = new ArrayList();

	VariableResolver resolver = new SimpleVariableResolver();

	private IProject _prj;

	/**
	 * @param prj
	 */
	public PageExpressionContext(IProject prj) {
		_prj = prj;
	}

	/**
	 * reset the context.
	 */
	public static void reset() {
		_current = null;
	}

	/**
	 * Initialize the current context
	 * @param prj
	 */
	public static void initialize(IProject prj) {
		_current = new PageExpressionContext(prj);
	}

	/**
	 * @return the current context
	 */
	public static PageExpressionContext getCurrent() {
		return _current;
	}

	/**
	 * @param provider
	 */
	public void pushPageVarProvider(IPageVariablesProvider provider) {
		_pageVarProviders.add(provider);
	}

	/**
	 * @param provider
	 */
	public void popPageVarProvider(IPageVariablesProvider provider) {
		try {
			_pageVarProviders.remove(_pageVarProviders.size() - 1);
		} catch (Exception ex) {
			// "Error"
			_log.info("PageExpressionContext.Info.0", ex); //$NON-NLS-1$
		}
	}

	/**
	 * this method is for design time expression evaluation. Currently it only
	 * handles
	 * 
	 * @param expression
	 * @param expectedClass 
	 * @param options
	 *            XXX: not used today. In the future, we may support things like
	 *            locale in options
	 * @return the result of evaluating the expression
	 * @throws ELException 
	 */
	public Object evaluateExpression(String expression, Class expectedClass,
			Map options) throws ELException {
		expression = JSFELParserHelper.toJspElExpression(expression);
		ExpressionEvaluatorImpl evaluator = new ExpressionEvaluatorImpl();
		FunctionMapper mapper = new EmptyFunctionMapper();

		return evaluator.evaluate(expression, expectedClass, resolver, mapper);
	}

	class SimpleVariableResolver implements VariableResolver {
		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.servlet.jsp.el.VariableResolver#resolveVariable(java.lang.String)
		 */
		public Object resolveVariable(String varName) throws ELException {
			// reverse order.
			for (int k = _pageVarProviders.size() - 1; k >= 0; k--) {
				IPageVariablesProvider _pageVars = (IPageVariablesProvider) _pageVarProviders
						.get(k);
				if (_pageVars != null) {
					IVariableInfo[] vars = _pageVars.getBeanInfos();
					if (vars != null) {
						for (int i = 0; i < vars.length; i++) {
							if (varName.equals(vars[i].getName())) {
								// ok we found.
								if (vars[i].getMode() == IVariableInfo.RESOURCEBUNDLE) {
									String resourceName = vars[i]
											.getTypeInfoString();
									IStorage s = null;
									try {
										s = LoadBundleUtil
												.getLoadBundleResource(_prj,
														resourceName);
									} catch (CoreException ex) {
										// "Error"
										_log
												.info(
														"PageExpressionContext.Info.0", ex); //$NON-NLS-1$
									}
									if (s == null) {
										throw new ELException();
									}
									InputStream input = null;
									try {
										input = new BufferedInputStream(s
												.getContents());
										Properties p = new Properties();
										p.load(input);
										return p;
									} catch (CoreException e) {
										throw new ELException(e);
									} catch (IOException e) {
										throw new ELException(e);
									} finally {
										ResourceUtils.ensureClosed(input);
									}
								}
							}
						}
					}
				}
			}
			throw new ELException("Can't find: " + varName); //$NON-NLS-1$
		}

	}

	static class EmptyFunctionMapper implements FunctionMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.servlet.jsp.el.FunctionMapper#resolveFunction(java.lang.String,
		 *      java.lang.String)
		 */
		public Method resolveFunction(String arg0, String arg1) {
			return null;
		}
	}
}
