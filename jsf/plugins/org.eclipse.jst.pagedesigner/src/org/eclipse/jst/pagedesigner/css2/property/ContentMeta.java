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

/**
 * @author mengbo
 */
public class ContentMeta extends CSSPropertyMeta {
	/**
	 * @param inherit
	 * @param initvalue
	 */
	public ContentMeta() {
		// why inherit this??
		super(false, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	/*
	 * public Object calculateCSSValueResult(CSSValue value, String
	 * propertyName, AbstractStyle style) { if (value instanceof Counter) {
	 * Counter c = (Counter)value; ContentObject object = new ContentObject();
	 * 
	 * MyCounter counter = new MyCounter(c.getIdentifier(), c.getListStyle(),
	 * c.getSeparator()); object.setCounter(counter); return object; } return
	 * value; }
	 */
}
