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
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class PaddingWidthMeta extends LengthMeta {
    
    private static int MIN_PADDING_THRESHOLD = 4;

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public PaddingWidthMeta() {
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
		if (IHTMLConstants.TAG_TD.equalsIgnoreCase(htmltag)
				|| IHTMLConstants.TAG_TH.equalsIgnoreCase(htmltag)) {
			Node parent = element;
			Element tableEle = null;
			while ((parent = parent.getParentNode()) != null
					&& parent instanceof Element) {
				if (((Element) parent).getTagName().equalsIgnoreCase(
						IHTMLConstants.TAG_TABLE)) {
					tableEle = (Element) parent;
					break;
				}
			}
			if (tableEle != null) {
				String padding = DOMUtil.getAttributeIgnoreCase(tableEle,
						"cellpadding");//$NON-NLS-1$
				if (padding != null) {
					Length length = (Length) LengthMeta.toLength(padding, style, this
							.getPercentageType(), getBaseFont(style));
                    // TODO should not be hardcoded value.  Either should change to a pref
                    // or a per-component customization.
                    if (length.getValue() < MIN_PADDING_THRESHOLD)
                    {
                        return new Length(MIN_PADDING_THRESHOLD, false);
                    }
                    return length;
				}
                // TODO should not be hardcoded value.  Either should change to a pref
                // or a per-component customization.
                return new Length(MIN_PADDING_THRESHOLD, false);
			}
		}
		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}
}
