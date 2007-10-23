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
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * The result value should be Length object only.
 * 
 * @author mengbo
 */
// XXX: in the future, we may add "unit" into Length, the one unit will be
// "MEDIUM"
// and specific for font. Means the relative size to MEDIUM. Then it is possible
// for
// the caller to decide the actual font size based on the style specific MEDIUM
// size.
public class FontSizeMeta extends LengthMeta {
	private static final String[] KEYWORDS = { ICSSPropertyID.VAL_XX_SMALL,
			ICSSPropertyID.VAL_X_SMALL, ICSSPropertyID.VAL_SMALL,
			ICSSPropertyID.VAL_MEDIUM, ICSSPropertyID.VAL_LARGE,
			ICSSPropertyID.VAL_X_LARGE, ICSSPropertyID.VAL_XX_LARGE,
			ICSSPropertyID.VAL_LARGER, ICSSPropertyID.VAL_SMALLER };

	/**
	 * font size
	 */
	public static final double MEDIUM_VAL_INT = 16;

	private static final double SCALING_FACTOR = 1.2;

	private static final double FACTORS[] = { 0.6, 0.89, 1, 1.2, 1.5, 2.0, 3.0 };

	// The scaling factors in IE is different from CSS definition. Here we
	// follow IE.
	private static final double CSS_ABSOLUTE_FACTORS[] = { 0.63, 0.82, 1, 1.12,
			1.5, 2.0, 3.0 };

	private static final int MIN_SIZE_FOR_SMALLER = 1;

	private static final int MIN_SIZE_FOR_LARGER = 9;

	/**
	 * Default constructor
	 */
	public FontSizeMeta() {
		super(true, new Length((int) MEDIUM_VAL_INT, false));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.LengthMeta#getBaseFont(org.eclipse.jst.pagedesigner.css2.property.AbstractStyle)
	 */
	protected ICSSFont getBaseFont(ICSSStyle style) {
		return style.getParentStyle().getCSSFont();
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
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getPercentageType()
	 */
	public int getPercentageType() {
		return PERCENTAGE_FONT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.LengthMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		Object obj = super.calculateCSSValueResult(value, propertyName, style);
		if (obj instanceof Length) {
			return obj;
		}
		// otherwise, it is keyword.
		return convertKeyword(obj, style);
	}

	/**
	 * @param fontsizeobj
	 * @param style
	 * @return the length
	 */
	protected Length convertKeyword(Object fontsizeobj, ICSSStyle style) {
		int fontsize;
		int parentfontsize = style.getParentStyle().getCSSFont().getFontSize();
		if (fontsizeobj instanceof Length) {
			fontsize = ((Length) fontsizeobj).getValue();
		} else if (ICSSPropertyID.VAL_XX_SMALL.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[0]);
		} else if (ICSSPropertyID.VAL_X_SMALL.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[1]);
		} else if (ICSSPropertyID.VAL_SMALL.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[2]);
		} else if (ICSSPropertyID.VAL_MEDIUM.equals(fontsizeobj)) {
			fontsize = (int) Math.round(MEDIUM_VAL_INT
					* CSS_ABSOLUTE_FACTORS[3]);
		} else if (ICSSPropertyID.VAL_LARGE.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[4]);
		} else if (ICSSPropertyID.VAL_X_LARGE.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[5]);
		} else if (ICSSPropertyID.VAL_XX_LARGE.equals(fontsizeobj)) {
			fontsize = (int) (MEDIUM_VAL_INT * CSS_ABSOLUTE_FACTORS[6]);
		} else if (ICSSPropertyID.VAL_SMALLER.equals(fontsizeobj)) {
			fontsize = (int) (parentfontsize / SCALING_FACTOR);
			if (fontsize < MIN_SIZE_FOR_SMALLER) {
				fontsize = MIN_SIZE_FOR_SMALLER;
			}
		} else if (ICSSPropertyID.VAL_LARGER.equals(fontsizeobj)) {
			fontsize = (int) (parentfontsize * SCALING_FACTOR);
			if (fontsize < MIN_SIZE_FOR_LARGER) {
				fontsize = MIN_SIZE_FOR_LARGER;
			}
		} else {
			fontsize = parentfontsize;
		}

		return new Length(fontsize, false);

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
		if (IHTMLConstants.TAG_FONT.equalsIgnoreCase(htmltag)
				|| ICSSPropertyID.ATTR_BASEFONT.equalsIgnoreCase(htmltag)) {
			String size = DOMUtil.getAttributeIgnoreCase(element,
					ICSSPropertyID.ATTR_SIZE);
			if (size != null) {
				size = size.trim();
				try {
					int fontSize = 0;
					if (size.startsWith("+")) //$NON-NLS-1$
					{
						fontSize = Integer.parseInt(size.substring(1)) + 3;
					} else if (size.startsWith("-")) //$NON-NLS-1$
					{
						fontSize = 3 - Integer.parseInt(size.substring(1));
					} else {
						fontSize = Integer.parseInt(size);
					}
					if (fontSize < 1) {
						fontSize = 1;
					}
					if (fontSize > 7) {
						fontSize = 7;
					}
					return new Length((int) (Math.round(FACTORS[fontSize - 1]
							* MEDIUM_VAL_INT)), false);
				} catch (Exception ex) {
					// Error in tag font attr calculating.
					// _log.error("Error.FontSizeMeta.0", ex); //$NON-NLS-1$
					return null;
				}
			}
		}
		return null;
	}
}
