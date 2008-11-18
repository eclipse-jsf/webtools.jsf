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
package org.eclipse.jst.pagedesigner.css2.property;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * @author mengbo
 */
public class ListStyleTypeMeta extends CSSPropertyMeta {
	private static final String INITIAL_VALUE = ICSSPropertyID.VAL_DISC;

	/**
	 * Default constructor
	 */
	public ListStyleTypeMeta() {
		super(true, INITIAL_VALUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		return value.getCssText();
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateHTMLAttributeOverride(org.w3c.dom.Element,
	// java.lang.String, java.lang.String,
	// org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	// */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		String listStyle = DOMUtil.getAttributeIgnoreCase(element, "type"); //$NON-NLS-1$
		if (listStyle == null && element != null
				&& element.getParentNode() != null) {
			listStyle = DOMUtil.getAttributeIgnoreCase((Element) element
					.getParentNode(), "type"); //$NON-NLS-1$
		}
		if (listStyle != null) {
			listStyle = listStyle.trim();
			if (listStyle.equals("1")) { //$NON-NLS-1$
				return ICSSPropertyID.VAL_DECIMAL;
			} else if (listStyle.equals("a")) { //$NON-NLS-1$
				return ICSSPropertyID.VAL_LOWER_ALPHA;
			} else if (listStyle.equals("A")) { //$NON-NLS-1$
				return ICSSPropertyID.VAL_UPPER_ALPHA;
			} else if (listStyle.equals("i")) { //$NON-NLS-1$
				return ICSSPropertyID.VAL_LOWER_ROMAN;
			} else if (listStyle.equals("I")) { //$NON-NLS-1$
				return ICSSPropertyID.VAL_UPPER_ROMAN;
			}
			return listStyle;
		}
		return null;
	}
}
