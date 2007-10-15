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
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * @author mengbo
 */
public abstract class CSSPropertyMeta implements ICSSPropertyMeta {
	static final String[] EMPTY_KEYWORDS = new String[0];

	boolean _inherited;

	Object _initialValue;

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public CSSPropertyMeta(boolean inherit, Object initvalue) {
		this._inherited = inherit;
		this._initialValue = initvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#isInherited()
	 */
	public boolean isInherited() {
		return _inherited;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#getHTMLElementInitialValue(org.w3c.dom.Element,
	 *      java.lang.String, java.lang.String)
	 */
	public Object getHTMLElementInitialValue(Element element, String htmltag,
			String propertyName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#getInitialValue()
	 */
	public Object getInitialValue(String propertyName, ICSSStyle style) {
		return _initialValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#getPercentageType()
	 */
	public int getPercentageType() {
		return PERCENTAGE_NONE;
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
		// child class should override this method!!!
		return null;
	}

	/**
	 * for many properties, they have a set of keyword property values.
	 * 
	 * @return the keyword values
	 */
	protected abstract String[] getKeywordValues();

	/**
	 * @param value
	 * @return null if is not a keyword.
	 */
	protected String checkKeywordValues(String value) {
		if (value == null)
			return null;
		String[] keywords = getKeywordValues();
		if (keywords != null) {
			for (int i = 0; i < keywords.length; i++) {
				if (keywords[i].equalsIgnoreCase(value)) {
					return keywords[i];
				}
			}
		}
		return null;
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
		String s = checkKeywordValues(value.getCssText());
		if (s != null) {
			return s;
		}
        return this.getInitialValue(propertyName, style);
	}
}
