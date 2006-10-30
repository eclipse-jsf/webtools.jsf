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
package org.eclipse.jst.pagedesigner.css2.style;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jst.pagedesigner.css2.property.BorderStyleMeta;
import org.eclipse.jst.pagedesigner.css2.property.BorderWidthMeta;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta;
import org.eclipse.jst.pagedesigner.css2.property.TextDecorationMeta;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.swt.graphics.Color;

/**
 * This class provides support for setting default styles of certain UI
 * controls.
 * 
 * @author mengbo
 */
public class ControlOverrideSupport {
	static Color _buttonBackground = ColorConstants.button;

	public static Object handleButtonOverride(ICSSPropertyMeta meta,
			String propertyName) {
		// if (BorderStyleMeta.isBorderStyle(propertyName))
		// {
		// return ICSSPropertyID.VAL_RIDGE;
		// }
		// else if (BorderWidthMeta.isBorderWidth(propertyName))
		// {
		// return Length.LENGTH_3;
		// }
		// else if
		// (ICSSPropertyID.ATTR_TEXTDECORATION.equalsIgnoreCase(propertyName))
		// {
		// return new Integer(TextDecorationMeta.NONE);
		// }
		// else if
		// (ICSSPropertyID.ATTR_BACKGROUND_COLOR.equalsIgnoreCase(propertyName))
		// {
		// return _buttonBackground;
		// }
		// else if
		// (ICSSPropertyID.ATTR_PADDING_LEFT.equalsIgnoreCase(propertyName) ||
		// ICSSPropertyID.ATTR_PADDING_RIGHT.equalsIgnoreCase(propertyName))
		// {
		// return Length.LENGTH_8;
		// }

		return null;
	}

	public static Object handleInputTextOverride(ICSSPropertyMeta meta,
			String propertyName) {
		if (BorderStyleMeta.isBorderStyle(propertyName)) {
			return ICSSPropertyID.VAL_GROOVE;
		} else if (BorderWidthMeta.isBorderWidth(propertyName)) {
			return Length.LENGTH_3;
		} else if (ICSSPropertyID.ATTR_TEXTDECORATION
				.equalsIgnoreCase(propertyName)) {
			return new Integer(TextDecorationMeta.NONE);
		}

		return null;
	}

	public static Object handleHighlightBorderOverride(ICSSPropertyMeta meta,
			String propertyName) {
		if (BorderStyleMeta.isBorderStyle(propertyName)) {
			return ICSSPropertyID.VAL_DOTTED;
		} else if (BorderWidthMeta.isBorderWidth(propertyName)) {
			return Length.LENGTH_1;
		}
		return null;
	}
}
