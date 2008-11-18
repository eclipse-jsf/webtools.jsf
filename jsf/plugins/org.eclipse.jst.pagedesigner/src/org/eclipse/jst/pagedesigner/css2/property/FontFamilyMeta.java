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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.CSSValueList;

/**
 * @author mengbo
 */
public class FontFamilyMeta extends CSSPropertyMeta {
	/**
	 * the default font name
	 */
	public static final String DEFAULT_FONT = "Times New Roman"; //$NON-NLS-1$

	private static FontData[] _FontData;

	private static FontData[] getFontData() {
		if (_FontData == null) {
			ArrayList list = new ArrayList();
			list.addAll(Arrays.asList(Display.getCurrent().getFontList(null,
					false)));
			list.addAll(Arrays.asList(Display.getCurrent().getFontList(null,
					true)));
			_FontData = (FontData[]) list.toArray(new FontData[list.size()]);
		}
		return _FontData;
	}

	/**
	 */
	public FontFamilyMeta() {
		super(true, DEFAULT_FONT);
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
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
			CSSValueList valueList = (CSSValueList) value;
			for (int i = 0, count = valueList.getLength(); i < count; i++) {
				String name = valueList.item(i).getCssText();
				name = trimPadding(name);
				if (isSupportedFont(name)) {
					return name;
				}
			}
		}
		return trimPadding(value.getCssText());
	}

	private String trimPadding(String name) {
		String value = name;
		if (value != null) {
			value = value.replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
			value = value.replaceAll("'", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return value;
	}

	private boolean isSupportedFont(String name) {
		FontData[] fontData = getFontData();
		for (int i = 0; i < fontData.length; i++) {
			if (fontData[i].getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
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
		if ("FONT".equalsIgnoreCase(htmltag) //$NON-NLS-1$
				|| "BASEFONT".equalsIgnoreCase(htmltag)) { //$NON-NLS-1$
			String face = DOMUtil.getAttributeIgnoreCase(element, "face"); //$NON-NLS-1$
			if (face != null) {
				String[] names = getFontNameList(face);
				for (int i = 0; i < names.length; i++) {
					if (isSupportedFont(names[i])) {
						return names[i];
					}
				}
			} else {
				return null;
			}
		}
		return null;
	}

	private String[] getFontNameList(String face) {
		StringTokenizer tokenizer = new StringTokenizer(face, ","); //$NON-NLS-1$
		String[] names = new String[tokenizer.countTokens()];
		for (int i = 0; i < names.length; i++) {
			names[i] = trimPadding(tokenizer.nextToken());
		}
		return names;
	}
}
