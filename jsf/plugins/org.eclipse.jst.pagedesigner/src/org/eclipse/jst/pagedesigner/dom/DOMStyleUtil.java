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
package org.eclipse.jst.pagedesigner.dom;

import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * @author mengbo
 * @version 1.5
 */
public final class DOMStyleUtil {
	/**
	 * @param original
	 * @param cssProperty
	 * @return the inline style property
	 */
	public static String getInlineStyleProperty(Element original,
			String cssProperty) {
		if (original instanceof ElementCSSInlineStyle) {
			CSSStyleDeclaration styledecl = ((ElementCSSInlineStyle) original)
					.getStyle();
			if (styledecl == null) {
				if (original.getAttribute("style") == null) { //$NON-NLS-1$
					return null;
				}
				// else mean it has style attribute.
			}

			if (styledecl != null) {
				return styledecl.getPropertyValue(cssProperty);
			}
		}

		// when we reach here, means we can't use the CSSStyleDeclaration API to
		// get style, we'll take the
		// pain to do the parsing and replacing.
		// normally should not happen. But anyway, we need to have a fail safe
		// path.

		String oldstyle = original.getAttribute("style"); //$NON-NLS-1$
		if (oldstyle == null || oldstyle.length() == 0) {
			return null;
		}
		StringTokenizer tokenizer = new StringTokenizer(oldstyle, ";"); //$NON-NLS-1$

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if (token.length() == 0) {
				continue;
			}
			int index = token.indexOf(':');
			if (index == -1) {
				continue;
			}
			String propertyName = token.substring(0, index).trim();
			if (cssProperty.equals(propertyName)) {
				// ok, we found the property
				return token.substring(index + 1).trim();
			}
		}
		return null;
	}

	/**
	 * insert style into element
	 * 
	 * @param original
	 * @param map
	 */
	public static void insertStyle(Element original, Map map) {
		if (original instanceof ElementCSSInlineStyle) {
			CSSStyleDeclaration styledecl = ((ElementCSSInlineStyle) original)
					.getStyle();
			if (styledecl == null) {
				if (original.getAttribute("style") == null) { //$NON-NLS-1$
					original.setAttribute("style", ""); //$NON-NLS-1$ //$NON-NLS-2$
					styledecl = ((ElementCSSInlineStyle) original).getStyle();
				}
			}

			if (styledecl != null) {
				for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
					String key = (String) iter.next();
					String value = (String) map.get(key);
					if (value == null) {
						styledecl.removeProperty(key);
					} else {
						styledecl.setProperty(key, value, null);
					}
				}

				return;
			}
		}

		// when we reach here, means we can't use the CSSStyleDeclaration API to
		// change style, we'll take the
		// pain to do the parsing and replacing.
		// normally should not happen. But anyway, we need to have a fail safe
		// path.

		String oldstyle = original.getAttribute("style"); //$NON-NLS-1$
		if (oldstyle == null) {
			oldstyle = ""; //$NON-NLS-1$
		}
		StringTokenizer tokenizer = new StringTokenizer(oldstyle, ";"); //$NON-NLS-1$

		StringBuffer buffer = new StringBuffer();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if (token.length() == 0) {
				continue;
			}
			int index = token.indexOf(':');
			if (index == -1) {
				// wrong property? ignore.
				buffer.append(token).append("; "); //$NON-NLS-1$
				continue;
			}
			String propertyName = token.substring(0, index).trim();

			if (map.containsKey(propertyName)) {
				String propertyValue = (String) map.remove(propertyName);
				if (propertyValue == null) {
					// we want to remove this css property. so don't append
					// anything here
				} else {
					buffer.append(propertyName).append(": ").append( //$NON-NLS-1$
							propertyValue).append("; "); //$NON-NLS-1$
				}
			} else {
				buffer.append(token).append("; "); //$NON-NLS-1$
			}
		}
		// ok, we have loop through existing properties and did replacement.
		// now _styleProperties only contain those new CSS properties we need to
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			String value = (String) map.get(key);
			if (value != null) {
				buffer.append(key).append(": ").append(value).append("; "); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		original.setAttribute("style", buffer.toString()); //$NON-NLS-1$
	}

	/**
	 * @param ele
	 * @return true if supports the style attribute
	 */
	public static boolean supportStyleAttribute(IDOMElement ele) {
		CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
		if (decl != null && decl.getAttributes().getNamedItem("style") != null) { //$NON-NLS-1$
			return true;
		}
        return false;
	}
	
	private DOMStyleUtil()
	{
	    // util class, no external instantiation
	}

}
