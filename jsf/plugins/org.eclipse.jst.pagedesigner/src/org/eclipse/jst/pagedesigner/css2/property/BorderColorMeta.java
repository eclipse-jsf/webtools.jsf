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
import org.eclipse.jst.pagedesigner.css2.color.CSSColorManager;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * @author mengbo
 */
public class BorderColorMeta extends CSSPropertyMeta {

	private static final String[] _keywords = new String[] { ICSSPropertyID.VAL_TRANSPARENT };

	/**
	 */
	public BorderColorMeta() {
		super(false, ICSSPropertyID.VAL_TRANSPARENT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.property.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		String text = value.getCssText();
		Object result = CSSColorManager.getInstance().getColor(text);
		if (result != null) {
			return result;
		}
        return getInitialValue(propertyName, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return _keywords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateHTMLAttributeOverride(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	 */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		Object result;
		String colorAttr = null;
		if (IHTMLConstants.TAG_HR.equalsIgnoreCase(htmltag)) {
			colorAttr = DOMUtil.getAttributeIgnoreCase(element,
					ICSSPropertyID.ATTR_COLOR);
		} else if (ICSSPropertyID.ATTR_BACKGROUND_COLOR
				.equalsIgnoreCase(propertyName)) {
			colorAttr = DOMUtil.getAttributeIgnoreCase(element,
					ICSSPropertyID.ATTR_BGCOLOR);
		}
		if (colorAttr != null && colorAttr.trim().length() != 0) {
			colorAttr = colorAttr.trim();
			result = CSSColorManager.getInstance().getColor(colorAttr);
			return result;
		}
		return null;
	}
}
