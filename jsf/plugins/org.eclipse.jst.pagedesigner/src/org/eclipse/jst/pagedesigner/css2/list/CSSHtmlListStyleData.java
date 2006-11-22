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
package org.eclipse.jst.pagedesigner.css2.list;

import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.swt.graphics.Image;

/**
 * @author mengbo
 */
public class CSSHtmlListStyleData {
	public final static int LIST_T_IMAGE = 0;

	public final static int LIST_T_DISC = 1;

	public final static int LIST_T_CIRCLE = 2;

	public final static int LIST_T_SQUARE = 3;

	public final static int LIST_T_DECIMAL = 0x11;

	public final static int LIST_T_DECIMAL_LEADING_ZERO = 0x12;

	public final static int LIST_T_LOWER_ALPHA = 0x13;

	public final static int LIST_T_LOWER_ROMAN = 0x14;

	public final static int LIST_T_UPPER_ALPHA = 0x15;

	public final static int LIST_T_UPPER_ROMAN = 0x16;

	//private CSSMarkerStyleData _markerStyleData;

	private Image _markerImage;

	private int _type;

	public boolean isDefaultPicture() {
		return (_type & 0xf) != 0;
	}

	private String getResolvedURL() {
		//String textValue = _markerStyleData.getTextContent();
		// TODO: when I found this, resolver was not being set and would either
        // have thrown NPE or returned "".. so I've "improved it :)
        //URIResolver resolver = null;// FIXME: this is not implemented yet.
		// DesignerPropertyTool.getModel().getResolver();
		//if (textValue != null && textValue.length() > 0) {
		//	return resolver.getLocationByURI(textValue);
		//}
		return "";
	}

	/**
	 * @return Returns the markerImage.
	 */
	public Image getMarkerImage() {
		if (!this.isImage()) {
			return null;
		}
		if (_markerImage == null) {
			String uri = this.getResolvedURL();
			_markerImage = new Image(null, uri);
		}
		return _markerImage;
	}

	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return _type;
	}

	public void setType(String type) {

		this._type = toTypeInt(type);
	}

	/**
	 * @return Returns the markerString.
	 */
	public String getTextValue(int index) {
		if (!this.isText()) {
			return null;
		}
		// ICounter counter = CounterFactory.getInstance().getCounter(_type);
		return "";

	}

	public boolean isText() {
		return (_type & 0xf0) != 0;
	}

	public boolean isImage() {
		return (_type == LIST_T_IMAGE);
	}

	public static int toTypeInt(String type) {
		if (type.equalsIgnoreCase(ICSSPropertyID.VAL_DECIMAL)) {
			return LIST_T_DECIMAL;
		} else if (type
				.equalsIgnoreCase(ICSSPropertyID.VAL_DECIMAL_LEADING_ZERO)) {
			return LIST_T_DECIMAL_LEADING_ZERO;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_DISC)) {
			return LIST_T_DISC;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_CIRCLE)) {
			return LIST_T_CIRCLE;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_SQUARE)) {
			return LIST_T_SQUARE;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_IMAGE)) {
			return LIST_T_IMAGE;
		}

		return 0;
	}
}
