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
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class BorderStyleMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_NONE,
			ICSSPropertyID.VAL_HIDDEN, ICSSPropertyID.VAL_DOTTED,
			ICSSPropertyID.VAL_DASHED, ICSSPropertyID.VAL_SOLID,
			ICSSPropertyID.VAL_DOUBLE, ICSSPropertyID.VAL_GROOVE,
			ICSSPropertyID.VAL_RIDGE, ICSSPropertyID.VAL_INSET,
			ICSSPropertyID.VAL_OUTSET };

	/**
	 */
	public BorderStyleMeta() {
		super(false, ICSSPropertyID.VAL_NONE);
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
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#calculateHTMLAttributeOverride(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	 */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		if (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(htmltag)) {
			return calculateTableOverride(element, propertyName);
		} else if (IHTMLConstants.TAG_TD.equalsIgnoreCase(htmltag)
				|| IHTMLConstants.TAG_TH.equalsIgnoreCase(htmltag)) {
			return calculateTDOverride(element, propertyName, style);
		} else if (IHTMLConstants.TAG_IMG.equalsIgnoreCase(htmltag)) {
			String border = DOMUtil.getAttributeIgnoreCase(element,
					IHTMLConstants.ATTR_BORDER);
			if (border != null) {
				return ICSSPropertyID.VAL_SOLID;
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
	private Object calculateTDOverride(Element element, String propertyName,
			ICSSStyle style) {
		// if element is empty, and "empty-cells" property is hide, then we
		// don't display border.
		Object obj = style.getStyleProperty(ICSSPropertyID.ATTR_EMPTY_CELLS);
		if (EmptyCellsMeta.HIDE.equals(obj) && TableUtil.isEmptyCell(element)) {
			return ICSSPropertyID.VAL_NONE;
		}

		// find containing table first
		Node parent = element;
		Element tableEle = null;
		while ((parent = parent.getParentNode()) != null
				&& parent instanceof Element) {
			if (((Element) parent).getTagName().equalsIgnoreCase(
					IHTMLConstants.TAG_TABLE)) {
				tableEle = (Element) parent;
				break;
			}

		}
		if (tableEle != null) {
			String rules = DOMUtil.getAttributeIgnoreCase(tableEle,
					IHTMLConstants.ATTR_RULES);
			String borderstr = DOMUtil.getAttributeIgnoreCase(tableEle,
					IHTMLConstants.ATTR_BORDER);
			if (rules == null || rules.length() == 0) {
				if (borderstr == null || "0".equals(borderstr.trim())) { //$NON-NLS-1$
					return ICSSPropertyID.VAL_NONE;
				}
                return ICSSPropertyID.VAL_TDBORDERSTYLE;
			}
            // ok, we got a value for rules
            if (TableUtil.matchRules(extractEdge(propertyName), rules)) {
            	return ICSSPropertyID.VAL_TDBORDERSTYLE;
            }
            return ICSSPropertyID.VAL_NONE;
		}
        return null;
	}

	/**
	 * @param element
	 * @param propertyName
	 * @return
	 */
	private Object calculateTableOverride(Element element, String propertyName) {
		String frame = DOMUtil.getAttributeIgnoreCase(element,
				IHTMLConstants.ATTR_FRAME);
		String borderstr = DOMUtil.getAttributeIgnoreCase(element,
				IHTMLConstants.ATTR_BORDER);
		// border="0" implies frame="void"
		if ("0".equals(borderstr)) { //$NON-NLS-1$
			return ICSSPropertyID.VAL_NONE;
		}
		if (frame == null || frame.length() == 0) {
			if (borderstr == null) {
				return ICSSPropertyID.VAL_NONE;
			} else if (borderstr.trim().length() == 0) {
				frame = IHTMLConstants.ATTR_BORDER;
			} else {
				try {
					Integer.parseInt(borderstr);
					frame = IHTMLConstants.ATTR_BORDER;
				} catch (Exception ex) {
					frame = borderstr;
				}
			}
		}
		// ok, we got a value for frame.
		if (TableUtil.matchFrame(extractEdge(propertyName), frame)) {
			return ICSSPropertyID.VAL_OUTSET;
		}
        return ICSSPropertyID.VAL_NONE;
	}

	static String extractEdge(String propertyName) {
		if (ICSSPropertyID.ATTR_BORDER_BOTTOM_STYLE
				.equalsIgnoreCase(propertyName)) {
			return "bottom"; //$NON-NLS-1$
		} else if (ICSSPropertyID.ATTR_BORDER_LEFT_STYLE
				.equalsIgnoreCase(propertyName)) {
			return "left"; //$NON-NLS-1$
		} else if (ICSSPropertyID.ATTR_BORDER_RIGHT_STYLE
				.equalsIgnoreCase(propertyName)) {
			return "right"; //$NON-NLS-1$
		} else {
			return "top"; //$NON-NLS-1$
		}
	}

	/**
	 * @param propertyName
	 * @return true if property name is a border style
	 */
	public static boolean isBorderStyle(String propertyName) {
		return ICSSPropertyID.ATTR_BORDER_BOTTOM_STYLE
				.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_LEFT_STYLE
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_RIGHT_STYLE
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_BORDER_TOP_STYLE
						.equalsIgnoreCase(propertyName);
	}
}
