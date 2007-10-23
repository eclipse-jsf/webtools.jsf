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
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.CSSValueList;

/**
 * @author mengbo
 */
public class TextDecorationMeta extends CSSPropertyMeta {
	private static final int NONE = 0;

	/**
	 * 
	 */
	public static final int UNDERLINE = 1;

	/**
	 * 
	 */
	public static final int OVERLINE = 1 << 1;

	/**
	 * 
	 */
	public static final int LINETHROUGH = 1 << 2;

	private static final int BLINK = 1 << 3;

	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_NONE,
			ICSSPropertyID.VAL_UNDERLINE, ICSSPropertyID.VAL_OVERLINE,
			ICSSPropertyID.VAL_LINETHROUGH, ICSSPropertyID.VAL_BLINK };

	/**
	 * Default constructor
	 */
	public TextDecorationMeta() {
		// the spec say text-decoration is not inherited. but the description
		// seemed to make use inherit easier.
		// It seems that the property is inherited in IE and Mozilla.
		super(true, new Integer(NONE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return KEYWORDS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.property.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		String[] decorations = null;
		if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
			CSSValueList valueList = (CSSValueList) value;
			decorations = new String[valueList.getLength()];
			for (int i = 0; i < decorations.length; i++) {
				decorations[i] = valueList.item(i).getCssText();
			}
		} else {
			decorations = new String[1];
			decorations[0] = value.getCssText();
		}

		int intvalue = 0;
		for (int i = 0; i < decorations.length; i++) {
			String key = super.checkKeywordValues(decorations[i]);
			if (key == ICSSPropertyID.VAL_NONE) {
				intvalue = NONE;
			} else if (key == ICSSPropertyID.VAL_UNDERLINE) {
				intvalue |= UNDERLINE;
			} else if (key == ICSSPropertyID.VAL_OVERLINE) {
				intvalue |= OVERLINE;
			} else if (key == ICSSPropertyID.VAL_LINETHROUGH) {
				intvalue |= LINETHROUGH;
			} else if (key == ICSSPropertyID.VAL_BLINK) {
				intvalue |= BLINK;
			}
		}

		return new Integer(intvalue);
	}
}
