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

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.CSSValueList;

/**
 * The value will always be of type int[2]
 * 
 * @author mengbo
 */
public class BorderSpacingMeta extends CSSPropertyMeta {
	private static final int[] INITIAL_SPACING = new int[] { 3, 3 };

	/**
	 */
	public BorderSpacingMeta() {
		// the specification says "border-spacing" is inherited. But seemed
		// browse is not treating it as inherited. So we treat is as not
		// inherited.
		super(false, INITIAL_SPACING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return EMPTY_KEYWORDS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		if (value == null || value.getCssValueType() == CSSValue.CSS_CUSTOM) {
			return INITIAL_SPACING;
		}
		if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
			int intvalue = toIntValue(value.getCssText(), style);
			if (intvalue >= 0) {
				return new int[] { intvalue, intvalue };
			}
            return INITIAL_SPACING;
		} else if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
			CSSValueList list = (CSSValueList) value;
			if (list.getLength() >= 2) {
				int i1 = toIntValue(list.item(0).getCssText(), style);
				int i2 = toIntValue(list.item(1).getCssText(), style);
				if (i1 < 0) {
					i1 = 0;
				}
				if (i2 < 0) {
					i2 = 0;
				}
				return new int[] { i1, i2 };
			}
            return INITIAL_SPACING;
		} else {
			return INITIAL_SPACING;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#calculateHTMLAttributeOverride(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	 */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		if (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(htmltag)) {
			String spacing = DOMUtil.getAttributeIgnoreCase(element,
					IHTMLConstants.ATTR_CELLSPACING);
			if (spacing != null) {
				int intvalue = toIntValue(spacing, style);
				if (intvalue >= 0) {
					return new int[] { intvalue, intvalue };
				}
			}
		}

		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}

	/**
	 * @param valuetext
	 * @param style
	 * @return the integer value for value text
	 */
	protected int toIntValue(String valuetext, ICSSStyle style) {
		Object length = LengthMeta.toLength(valuetext, style, PERCENTAGE_NONE,
				style.getCSSFont());
		if (length instanceof Length && !((Length) length).isPercentage()) {
			return ((Length) length).getValue();
		}
        return Integer.MIN_VALUE;
	}
}
