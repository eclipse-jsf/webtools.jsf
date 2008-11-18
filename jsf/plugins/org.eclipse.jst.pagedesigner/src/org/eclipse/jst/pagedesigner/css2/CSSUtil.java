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
package org.eclipse.jst.pagedesigner.css2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.font.CSSFont;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wst.css.core.internal.provisional.adapters.IStyleSheetListAdapter;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSModel;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSNode;
import org.eclipse.wst.css.core.internal.util.CSSClassTraverser;
import org.eclipse.wst.html.core.internal.htmlcss.CSSQueryTraverser;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.stylesheets.StyleSheet;
import org.w3c.dom.stylesheets.StyleSheetList;

/**
 * @author mengbo
 */
public class CSSUtil {
	private static StyleSheet _userAgentDefault;

	/**
	 * @param doc
	 * @return the css classes
	 */
	public static String[] getCSSClasses(Document doc) {
		Collection c = Collections.EMPTY_SET;
		if (doc instanceof INodeNotifier) {
			IStyleSheetListAdapter adapter = (IStyleSheetListAdapter) ((INodeNotifier) doc)
					.getAdapterFor(IStyleSheetListAdapter.class);
			StyleSheetList ssl = (adapter == null ? null : adapter
					.getStyleSheets());

			CSSClassTraverser traverser = new CSSClassTraverser();
			if (ssl != null) {
				for (int i = 0, numStyles = ssl.getLength(); i < numStyles; i++) {
					// loop for styles (<style> and <link>)
					org.w3c.dom.stylesheets.StyleSheet ss = ssl.item(i);

					try {
						traverser.apply((ICSSNode) ss);
					} catch (ClassCastException ex) {
						Logger log = PDPlugin
								.getLogger(CSSStyleDeclaration.class);
						log.error("Error.CSSUtil.0", ex); //$NON-NLS-1$
						// FIXME: should this continue to be processed?
					}
				}
				c = traverser.getClassNames();
			}
		}
		String[] result = new String[c.size()];
		c.toArray(result);
		return result;
	}

	/**
	 * Get the css style of a node.
	 * 
	 * @param node
	 * @return the style
	 */
	public static ICSSStyle getCSSStyle(Element node) {
		ICSSStyle style = null;
		if (node instanceof IDOMElement) {
			style = (ICSSStyle) ((IDOMElement) node)
					.getAdapterFor(ICSSStyle.class);
		}
		if (style == null) {
			return DefaultStyle.getInstance();
		}
        return style;
	}

	/**
	 * Resolve the css style string from css style elements.
	 * 
	 * @param style
	 * @return the style string
	 */
	public static String resolveCSSStyle(ICSSStyle style) {
		StringBuffer sb = new StringBuffer();
		Object object1 = style.getColor();
		if (object1 instanceof Color) {
			sb.append("color:"); //$NON-NLS-1$

			sb.append("#").append( //$NON-NLS-1$
					Integer.toHexString(((Color) object1).getRed()));
			sb.append("#").append( //$NON-NLS-1$
					Integer.toHexString(((Color) object1).getGreen()));
			sb.append("#").append( //$NON-NLS-1$
					Integer.toHexString(((Color) object1).getBlue()));
			sb.append(";"); //$NON-NLS-1$
		}
		Object object2 = style.getCSSFont();
		if (object2 instanceof CSSFont) {
			sb.append(((CSSFont) object2).getCSSString());
		}
		return sb.toString();
	}

	/**
	 * Many of this method implementation copied from HTMLDocumentAdapter,
	 * mainly add support for user agent default style sheet.
	 * 
	 * @param element
	 *            the element.
	 * @param pseudoName
	 *            the pseudoname of the element
	 * @return the style declaration
	 */
	public static CSSStyleDeclaration getCSSDeclaration(Element element,
			String pseudoName) {
		// please reference comments in CSSTempUtil.
		List styleSheets = CSSTempUtil.getStyleSheets(element);
		// INodeNotifier docnotifier = (INodeNotifier)
		// element.getOwnerDocument();
		// IStyleSheetListAdapter adapter = (IStyleSheetListAdapter)
		// docnotifier.getAdapterFor(IStyleSheetListAdapter.class);
		//
		// StyleSheetList ssl = (adapter == null ? null :
		// adapter.getStyleSheets());

		CSSQueryTraverser query = new CSSQueryTraverser();
		query.setTraverseImported(true);
		query.setTraverseImportFirst(true);
		query.setElement(element, pseudoName);

		// if (ssl != null)
		// {
		// for (int i = 0, numStyles = ssl.getLength(); i < numStyles; i++)
		// {
		// // loop for styles (<style> and <link>)
		// org.w3c.dom.stylesheets.StyleSheet ss = ssl.item(i);
		if (styleSheets != null) {
			for (int i = 0, numStyles = styleSheets.size(); i < numStyles; i++) {
				StyleSheet ss = (StyleSheet) styleSheets.get(i);
				try {
					query.apply((ICSSNode) ss);
				} catch (ClassCastException ex) {
					Logger log = PDPlugin.getLogger(CSSStyleDeclaration.class);
					log.error("Error.CSSUtil.0", ex); //$NON-NLS-1$
					// FIXME: should this continue to be processed?
				}
			}
		}
		CSSStyleDeclaration declare = query.getDeclaration();
		// FIXME: when do we need to apply the user agent style sheet?
		return declare;
	}

	/**
	 * Many of this method implementation copied from HTMLDocumentAdapter,
	 * mainly add support for user agent default style sheet.
	 * 
	 * @param element
	 *            the element.
	 * @param pseudoName
	 *            the pseudoname of the element
	 * @return the style declaration
	 */
	public static CSSStyleDeclaration getDefaultCSSDeclaration(Element element,
			String pseudoName) {
		CSSQueryTraverser query = new CSSQueryTraverser();
		query.setTraverseImported(true);
		query.setTraverseImportFirst(true);

		query.setElement(element, pseudoName);

		// FIXME: when do we need to apply the user agent style sheet?
		try {
			getUserAgentDefaultStyleSheet(element);
		} catch (UnsupportedEncodingException e) {
			Logger log = PDPlugin.getLogger(CSSStyleDeclaration.class);
			log.error("Error.CSSUtil.1", e); //$NON-NLS-1$
		} catch (IOException e) {
			Logger log = PDPlugin.getLogger(CSSStyleDeclaration.class);
			log.error("Error.CSSUtil.2", e); //$NON-NLS-1$
		}
		if (_userAgentDefault != null) {
			try {
				query.apply((ICSSNode) _userAgentDefault);
			} catch (ClassCastException ex) {
				Logger log = PDPlugin.getLogger(CSSStyleDeclaration.class);
				log.error("Error.CSSUtil.3", ex); //$NON-NLS-1$
			}
		}
		CSSStyleDeclaration declare = query.getDeclaration();
		return declare;
	}

	/**
	 * Get the user agent default style sheet.
	 * 
	 * @param element
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	// XXX: in the future, we may get user agent default style sheet based on
	// device type.
	private static StyleSheet getUserAgentDefaultStyleSheet(Element element)
			throws UnsupportedEncodingException, IOException {
		if (_userAgentDefault == null) {
			InputStream input = null;
			
			try
			{
				input = CSSUtil.class
					.getResourceAsStream(IJMTConstants.USERAGENT);
				IStructuredModel model = StructuredModelManager.getModelManager()
						.getModelForEdit(IJMTConstants.USERAGENT, input, null);
				ICSSModel cssmodel = (ICSSModel) model;
				_userAgentDefault = (StyleSheet) cssmodel.getDocument();
			}
			finally
			{
				ResourceUtils.ensureClosed(input);
			}
		}

		return _userAgentDefault;
	}

}
