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
public class WidthMeta extends LengthMeta {
	/**
	 * Default constructor
	 */
	public WidthMeta() {
		super(false, ICSSPropertyID.VAL_AUTO);
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
		if (!IHTMLConstants.TAG_INPUT.equalsIgnoreCase(htmltag)
				&& !IHTMLConstants.TAG_BUTTON.equalsIgnoreCase(htmltag)) {
			String width = DOMUtil
					.getAttributeIgnoreCase(element, propertyName);
			if (width != null) {
				return LengthMeta.toLength(width, style, this
						.getPercentageType(), getBaseFont(style));
			}
		}
		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}
}
