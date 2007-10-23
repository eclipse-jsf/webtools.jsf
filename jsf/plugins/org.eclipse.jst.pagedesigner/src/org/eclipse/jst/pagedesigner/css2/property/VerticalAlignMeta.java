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

/**
 * @author mengbo
 */
public class VerticalAlignMeta extends LengthMeta {
	/**
	 * baseline keyword
	 */
	public static final String BASELINE = "baseline"; //$NON-NLS-1$

	/**
	 * middle keyword
	 */
	public static final String MIDDLE = "middle"; //$NON-NLS-1$

	/**
	 * sub keyword
	 */
	public static final String SUB = "sub"; //$NON-NLS-1$

	/**
	 * super keyword
	 */
	public static final String SUPER = "super"; //$NON-NLS-1$

	/**
	 * text-top keyword
	 */
	public static final String TEXT_TOP = "text-top"; //$NON-NLS-1$

	/**
	 * text-bottom keyword
	 */
	public static final String TEXT_BOTTOM = "text-bottom"; //$NON-NLS-1$

	/**
	 * top keyword
	 */
	public static final String TOP = "top"; //$NON-NLS-1$

	/**
	 * bottom keyword
	 */
	public static final String BOTTOM = "bottom"; //$NON-NLS-1$

	/**
	 * center keyword
	 */
	public static final String CENTER = "center"; //$NON-NLS-1$

	/**
	 * default vertical align
	 */
	public static final String DEFAULT_VERTICAL_ALIGN = BASELINE;

	private static final String[] KEYWORDS = new String[] { BASELINE, MIDDLE,
			SUB, SUPER, TEXT_TOP, TEXT_BOTTOM, TOP, BOTTOM };

	private static final String[] htmlAttributes = new String[] { IHTMLConstants.ATTR_VALIGN };

	/**
	 * Default constructor
	 */
	public VerticalAlignMeta() {
		super(true, DEFAULT_VERTICAL_ALIGN);
	}

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public VerticalAlignMeta(boolean inherit, Object initvalue) {
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
		for (int i = 0; i < htmlAttributes.length; i++) {
			String align = DOMUtil.getAttributeIgnoreCase(element,
					htmlAttributes[i]);
			if (TOP.equalsIgnoreCase(align)) {
				return TOP;
			}
			if (MIDDLE.equalsIgnoreCase(align)) {
				return MIDDLE;
			}
			if (BOTTOM.equalsIgnoreCase(align)) {
				return BOTTOM;
			}
			if (BASELINE.equalsIgnoreCase(align)) {
				return BASELINE;
			}
			if (CENTER.equalsIgnoreCase(align)) {
				return MIDDLE;
			}
		}
		return null;
	}

}
