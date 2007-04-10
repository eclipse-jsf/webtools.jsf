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
package org.eclipse.jst.pagedesigner.jsp.core.el;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.el.ComplexValue;
import org.apache.commons.el.NamedValue;

/**
 * 
 * @author mengbo
 * @version 1.5
 */
public class ELParser {
	private String _jsfExpression;

	private List _expressionList;

	/**
	 * @param expression 
	 * 
	 */
	public ELParser(String expression) {
		super();
		_jsfExpression = expression;
		init();
	}

	/**
	 * 
	 */
	private void init() {
		Object parsedExpression = JSFELParserHelper
				.parseExpression(_jsfExpression);

		if (parsedExpression instanceof ComplexValue) {
			_expressionList = new ArrayList();
			_expressionList.add(((ComplexValue) parsedExpression).getPrefix());
			_expressionList.addAll(((ComplexValue) parsedExpression)
					.getSuffixes());
		} else if (parsedExpression instanceof NamedValue) {
			_expressionList = new ArrayList();
			_expressionList.add(parsedExpression);
		}

	}

	/**
	 * @return the expression elements of null if none
	 */
	public Object[] getElements() {
		if (_expressionList == null) {
			return null;
		}

		return _expressionList.toArray();
	}
}
