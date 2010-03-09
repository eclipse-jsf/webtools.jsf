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
import org.eclipse.jst.pagedesigner.ui.preferences.PDPreferences;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class PaddingWidthMeta extends LengthMeta {
    
	private final PDPreferences _prefs;

    /**
	 * Default constructor
     * @param prefs 
	 */
	public PaddingWidthMeta(final PDPreferences prefs) 
	{
		super(false, ICSSPropertyID.VAL_AUTO);
		_prefs = prefs;
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
			if (tableEle != null) 
			{
			    int artificialCellPadding = _prefs.getCssArtificialCellPadding(); 
				//TODO:  Why is only cellpadding being checked?  Why does this class even exist?  What is difference with BorderWidthMeta?   
				String padding = DOMUtil.getAttributeIgnoreCase(tableEle,
						"cellpadding");//$NON-NLS-1$
				if (padding != null && padding.trim().length() > 0) {//fix for 200592						
					Object length = LengthMeta.toLength(padding, style, this
								.getPercentageType(), getBaseFont(style));
                    if (length instanceof Length && ((Length)length).getValue() >= artificialCellPadding)                    
                        return length;
                    
				}
                return new Length(artificialCellPadding, false);
			}
		}
		return super.calculateHTMLAttributeOverride(element, htmltag,
				propertyName, style);
	}
}
