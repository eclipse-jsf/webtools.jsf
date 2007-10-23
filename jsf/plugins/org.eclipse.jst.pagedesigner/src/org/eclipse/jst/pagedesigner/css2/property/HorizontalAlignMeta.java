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

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class HorizontalAlignMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_LEFT,
			"bottom", "top", //$NON-NLS-1$ //$NON-NLS-2$
			ICSSPropertyID.VAL_RIGHT, ICSSPropertyID.VAL_CENTER,
			ICSSPropertyID.VAL_JUSTIFY };

	private static final String[] HOR_SLIGN_HTMLTAGS = new String[] {
			"TABLE", "HR", "LEGEND", "APPLET", "IFRAME", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"IMG", "INPUT", "OBJECT", "CAPTION" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	};

	/**
	 * default constructor
	 */
	public HorizontalAlignMeta() {
		super(true, NOT_SPECIFIED);
	}

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public HorizontalAlignMeta(boolean inherit, Object initvalue) {
		super(inherit, initvalue);
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

		if (Arrays.asList(HOR_SLIGN_HTMLTAGS).contains(htmltag.toUpperCase())) {
			String align = DOMUtil.getAttributeIgnoreCase(element, "align"); //$NON-NLS-1$
			return checkKeywordValues(align);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#getHTMLElementInitialValue(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String)
	 */
	public Object getHTMLElementInitialValue(Element element, String htmltag,
			String propertyName) {
		if ("HR".equalsIgnoreCase(htmltag.toUpperCase())) //$NON-NLS-1$
		{
			return ICSSPropertyID.VAL_CENTER;
		} else if (Arrays.asList(HOR_SLIGN_HTMLTAGS).contains(
				htmltag.toUpperCase())) {
			return ICSSPropertyID.VAL_LEFT;
		}

		return super.getHTMLElementInitialValue(element, htmltag, propertyName);
	}
}
