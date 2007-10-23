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
public class WhiteSpaceMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_NORMAL,
			ICSSPropertyID.VAL_PRE, ICSSPropertyID.VAL_NOWRAP };

	/**
	 * Default constructor
	 */
	public WhiteSpaceMeta() {
		super(true, ICSSPropertyID.VAL_NORMAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return KEYWORDS;
	}

	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style) {
		if (IHTMLConstants.TAG_TD.equalsIgnoreCase(htmltag)
				|| IHTMLConstants.TAG_TH.equalsIgnoreCase(htmltag)) {
			String noWrap = DOMUtil.getAttributeIgnoreCase(element,
					IHTMLConstants.ATTR_NOWRAP);
			if (Boolean.TRUE.toString().equalsIgnoreCase(noWrap)) {
				return ICSSPropertyID.VAL_NOWRAP;
			}
		}
		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}
}
