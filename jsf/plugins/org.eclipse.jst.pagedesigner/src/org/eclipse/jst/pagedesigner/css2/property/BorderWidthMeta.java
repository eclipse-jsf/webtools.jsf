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
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.css.CSSValue;

/**
 * 
 * @author mengbo
 */
// FIXME: when the border-style is none, how should we treat border-width?
public class BorderWidthMeta extends LengthMeta {

	private static final Length LENGTH_4 = new Length(4, false);

	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_AUTO,
			ICSSPropertyID.VAL_THIN, ICSSPropertyID.VAL_MEDIUM,
			ICSSPropertyID.VAL_THICK };

	/**
	 */
	public BorderWidthMeta() {
		super(false, LENGTH_4); // medium length.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.LengthMeta#getKeywordValues()
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
		return PERCENTAGE_NONE;
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
		// Computed value: absolute length; '0' if the border style is 'none' or
		// 'hidden'
		Object stylevalue = style.getStyleProperty("border-" //$NON-NLS-1$
				+ extractEdge(propertyName) + "-style"); //$NON-NLS-1$
		if (ICSSPropertyID.VAL_NONE.equals(stylevalue)
				|| ICSSPropertyID.VAL_HIDDEN.equals(stylevalue)) {
			return Length.LENGTH_0;
		}

		Object obj = super.calculateCSSValueResult(value, propertyName, style);
		if (ICSSPropertyID.VAL_THIN.equals(obj)) {
			return Length.LENGTH_1;
		} else if (ICSSPropertyID.VAL_MEDIUM.equals(obj)) {
			return Length.LENGTH_3;
		} else if (ICSSPropertyID.VAL_THICK.equals(obj)) {
			return Length.LENGTH_8;
		}
		return obj;
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
		if ("table".equalsIgnoreCase(htmltag)) { //$NON-NLS-1$
			return calculateTableOverride(element, propertyName);
		} else if ("td".equalsIgnoreCase(htmltag) //$NON-NLS-1$
				|| "th".equalsIgnoreCase(htmltag)) { //$NON-NLS-1$
			return calculateTDOverride(element, propertyName);
		} else if ("img".equalsIgnoreCase(htmltag)) { //$NON-NLS-1$
			String border = DOMUtil.getAttributeIgnoreCase(element, "border");// ICSSPropertyID.ATTR_BORDERSIZE); //$NON-NLS-1$
			if (border != null) {
				try {
					return new Length(Integer.parseInt(border), false);
				} catch (Exception ex) {
					// Integer processing, no need to report.
				}
			}
		}

		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}

	/**
	 * @param element
	 * @param propertyName
	 * @return
	 */
	private Object calculateTDOverride(Element element, String propertyName) {
		// find containing table first
		Node parent = element;
		Element tableEle = null;
		while ((parent = parent.getParentNode()) != null
				&& parent instanceof Element) {
			if (((Element) parent).getTagName().equalsIgnoreCase("table")) { //$NON-NLS-1$
				tableEle = (Element) parent;
				break;
			}

		}
		if (tableEle != null) {
			String rules = DOMUtil.getAttributeIgnoreCase(tableEle, "rules"); //$NON-NLS-1$
			String borderstr = DOMUtil.getAttributeIgnoreCase(tableEle,
					"border"); //$NON-NLS-1$
			if (rules == null || rules.length() == 0) {
				if (borderstr == null || "0".equals(borderstr.trim())) { //$NON-NLS-1$
					return null;
				}
                return Length.LENGTH_1;
			}
            // ok, we got a value for rules
            if (TableUtil.matchRules(extractEdge(propertyName), rules)) {
            	return Length.LENGTH_1;
            }
            return Length.LENGTH_0;
		}
        return Length.LENGTH_0;
	}

	/**
	 * @param element
	 * @param propertyName
	 * @return
	 */
	private Object calculateTableOverride(Element element, String propertyName) {
		String frame = DOMUtil.getAttributeIgnoreCase(element, "frame"); //$NON-NLS-1$
		String borderstr = DOMUtil.getAttributeIgnoreCase(element, "border"); //$NON-NLS-1$
		// border="0" implies frame="void"
		if ("0".equals(borderstr)) { //$NON-NLS-1$
			return Length.LENGTH_0;
		}
		if (frame == null || frame.length() == 0) {
			if (borderstr == null) {
				return null;
			} else if (borderstr.trim().length() == 0) {
				return Length.LENGTH_1;
			} else {
				try {
					return new Length(Integer.parseInt(borderstr), false);
				} catch (Exception ex) {
					frame = borderstr;
				}
			}
		}
		// ok, we got a value for frame.
		if (TableUtil.matchFrame(extractEdge(propertyName), frame)) {
			if (borderstr != null) {
				try {
					return new Length(Integer.parseInt(borderstr), false);
				} catch (Exception ex) {
					// ignore. pass through to return length_1
				}
			}
			return Length.LENGTH_1;
		}
        return Length.LENGTH_0;
	}

	static String extractEdge(String propertyName) {
		if (ICSSPropertyID.ATTR_BORDER_BOTTOM_WIDTH
				.equalsIgnoreCase(propertyName)) {
			return "bottom"; //$NON-NLS-1$
		} else if (ICSSPropertyID.ATTR_BORDER_LEFT_WIDTH
				.equalsIgnoreCase(propertyName)) {
			return "left"; //$NON-NLS-1$
		} else if (ICSSPropertyID.ATTR_BORDER_RIGHT_WIDTH
				.equalsIgnoreCase(propertyName)) {
			return "right"; //$NON-NLS-1$
		} else {
			return "top"; //$NON-NLS-1$
		}
	}

	/**
	 * @param propertyName
	 * @return true if property name is a border width
	 */
	public static boolean isBorderWidth(String propertyName) {
		return ICSSPropertyID.ATTR_BORDER_BOTTOM_WIDTH
				.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_LEFT_WIDTH
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_RIGHT_WIDTH
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_TOP_WIDTH
						.equalsIgnoreCase(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getInitialValue(java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	 */
	public Object getInitialValue(String propertyName, ICSSStyle style) {
		// Computed value: absolute length; '0' if the border style is 'none' or
		// 'hidden'
		Object stylevalue = style.getStyleProperty("border-" //$NON-NLS-1$
				+ extractEdge(propertyName) + "-style"); //$NON-NLS-1$
		if (ICSSPropertyID.VAL_NONE.equals(stylevalue)
				|| ICSSPropertyID.VAL_HIDDEN.equals(stylevalue)) {
			return Length.LENGTH_0;
		}
		return super.getInitialValue(propertyName, style);
	}
}
