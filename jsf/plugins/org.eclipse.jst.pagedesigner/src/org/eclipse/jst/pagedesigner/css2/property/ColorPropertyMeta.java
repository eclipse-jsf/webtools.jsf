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

import java.util.Arrays;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.color.CSSColorManager;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * "color" will be in this type
 * 
 * @author mengbo
 */
public class ColorPropertyMeta extends CSSPropertyMeta {
	private static final Object DEFAULT_COLOR = ColorConstants.black;

	private static final String[] KEYWORDS = new String[] {};

	private final static String[] NOTSUPPORT_TAG = { IHTMLConstants.TAG_H1,
			IHTMLConstants.TAG_H2, IHTMLConstants.TAG_H3,
			IHTMLConstants.TAG_H4, IHTMLConstants.TAG_H5, IHTMLConstants.TAG_H6 };

	/**
	 */
	public ColorPropertyMeta() {
		super(true, DEFAULT_COLOR);
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
        return this.getInitialValue(propertyName, style);
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
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateHTMLAttributeOverride(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.ICSSStyle)
	 */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		if (Arrays.asList(NOTSUPPORT_TAG).contains(htmltag.toLowerCase())) {
			return null;
		}
		Object result;
		String colorAttr = null;
		// There are some conditions need to be dealt with hyperlink and text
		// properties.
		if (ICSSPropertyID.ATTR_COLOR.equalsIgnoreCase(propertyName)) {
			colorAttr = DOMUtil.getAttributeIgnoreCase(element,
					ICSSPropertyID.ATTR_COLOR);
			if (colorAttr == null
					&& element.getLocalName().equalsIgnoreCase(
							IHTMLConstants.TAG_BODY)) {
				colorAttr = DOMUtil.getAttributeIgnoreCase(element,
						ICSSPropertyID.ATTR_TEXT);
			}
		}
		if (colorAttr != null) {
			colorAttr = colorAttr.trim();
			result = CSSColorManager.getInstance().getColor(colorAttr);
			return result;
		}
		return null;
	}
}
