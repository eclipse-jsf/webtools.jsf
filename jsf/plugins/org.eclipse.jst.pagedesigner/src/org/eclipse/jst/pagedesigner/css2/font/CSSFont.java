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
package org.eclipse.jst.pagedesigner.css2.font;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * @author mengbo
 */
public class CSSFont implements ICSSFont {
	private String _family;

	private int _size;

	private int _style;

	private int _weight;

	private String _cssString;

	/**
	 * @param family 
	 * @param size 
	 * @param style 
	 * @param weight 
	 * @param cssString 
	 * 
	 */
	public CSSFont(String family, int size, int style, int weight,
			String cssString) {
		this._family = family;
		this._size = size;
		this._style = style;
		this._weight = weight;
		_cssString = cssString;
	}

	public String getFontFamily() {
		return _family;
	}

	public int getFontSize() {
		return _size;
	}

	public int getFontStyle() {
		return _style;
	}

	public String getCSSString() {
		return _cssString;
	}

	public int getWeight() {
		return _weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof CSSFont) {
			CSSFont fd = (CSSFont) obj;
			return this._family.equals(fd._family) && this._size == fd._size
					&& this._style == fd._style && this._weight == fd._weight;
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return _family.hashCode() + _size + _style + _weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.font.ICSSFont#getSwtFont()
	 */
	public Font getSwtFont() {
		// return FontPoolManager.getInstance().getFont(this);
		return CSSFontManager.getInstance().getSwtFont(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.font.ICSSFont#getXHeight()
	 */
	public int getXHeight() {
		return getFontSize();
	}

	/**
	 * @return the style mask for the font style
	 */
	public int getSwtFontStyle() {
		int style = SWT.NONE;
		// see:http://www.htmlhelp.com/reference/css/font/font-weight.html
		if (getWeight() >= 600)
			style |= SWT.BOLD;
		style |= getFontStyle();
		return style;
	}
}
