/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.list;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.style.AbstractStyle;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Resolve the attrbites 'start' on 'ol' and 'value' on 'li'.
 * 
 * @author mengbo
 */
/*package*/ final class HTMLListInfoHelper {
	/**
	 * @return Returns the start.
	 */
	private static String getStart(ICSSStyle _style) {
		if (_style instanceof AbstractStyle) {
			Element element = ((AbstractStyle) _style).getElement();
			Node parent = null;
			if ((parent = EditModelQuery.getParent(IHTMLConstants.TAG_OL,
					element, true)) != null) {
				return ((Element) parent)
						.getAttribute(IHTMLConstants.ATTR_START);
			}
		}
		return null;
	}

	/**
	 * @param style
	 * @return the start int
	 */
	public static Integer getStartInt(ICSSStyle style) {
		try {
			return new Integer(getStart(style));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @return Returns the value.
	 */
	private static String getValue(ICSSStyle _style) {
		if (_style instanceof AbstractStyle) {
			Element element = ((AbstractStyle) _style).getElement();
			if (element != null) {
				return element.getAttribute(IHTMLConstants.ATTR_VALUE);
			}
		}
		return null;
	}

	/**
	 * @param style
	 * @return the Integer value or null if not parsable
	 */
	public static Integer getValueInt(ICSSStyle style) {
		try {
			return Integer.valueOf(getValue(style));
		} catch (NumberFormatException ex) {
			return null;
		}
	}
	
	private HTMLListInfoHelper()
	{
	    // no external instantiation
	}
}
