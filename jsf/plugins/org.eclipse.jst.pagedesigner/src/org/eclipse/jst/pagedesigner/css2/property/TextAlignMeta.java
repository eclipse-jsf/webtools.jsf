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

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class TextAlignMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_LEFT,
			ICSSPropertyID.VAL_RIGHT, ICSSPropertyID.VAL_CENTER,
			ICSSPropertyID.VAL_JUSTIFY };

	private static final String[] HOR_SLIGN_HTMLTAGS = new String[] {
			IHTMLConstants.TAG_TABLE, IHTMLConstants.TAG_HR,
			IHTMLConstants.TAG_LEGEND, IHTMLConstants.TAG_APPLET,
			IHTMLConstants.TAG_IFRAME, IHTMLConstants.TAG_IMG,
			IHTMLConstants.TAG_INPUT, IHTMLConstants.TAG_OBJECT,
			IHTMLConstants.TAG_CAPTION };

	/**
	 * Default constructor
	 */
	public TextAlignMeta() {
		super(true, NOT_SPECIFIED);
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
		// /for td, it may look for the attribute value up to tr, but must stop
		// at table.
		// so for table,set _inherited=false to stop it.
		if (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(htmltag)) {
			this._inherited = false;
		} else {
			this._inherited = true;
		}
		if (Arrays.asList(HOR_SLIGN_HTMLTAGS).contains(htmltag.toLowerCase())) {
			return null;
		}

		String align = DOMUtil.getAttributeIgnoreCase(element,
				IHTMLConstants.ATTR_ALIGN);
		String value = checkKeywordValues(align);
		if (value != null) {
			return value;
		}
		if ("middle".equalsIgnoreCase(align)) { //$NON-NLS-1$
			return ICSSPropertyID.VAL_CENTER;
		}

		return null;
	}
}
