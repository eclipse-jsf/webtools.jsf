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

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
 * @author mengbo
 */
public class LengthMeta extends CSSPropertyMeta {
	private static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_AUTO };

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public LengthMeta(boolean inherit, Object initvalue) {
		super(inherit, initvalue);
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
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.property.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		if (value == null || value.getCssValueType() == CSSValue.CSS_VALUE_LIST
				|| value.getCssValueType() == CSSValue.CSS_CUSTOM) {
			return ICSSPropertyID.VAL_AUTO;
		}
		if (value.getCssValueType() == CSSValue.CSS_INHERIT) {
			return ICSSPropertyID.VAL_AUTO;
		}
		CSSPrimitiveValue primitive = (CSSPrimitiveValue) value;
		String key = checkKeywordValues(primitive.getCssText());
		if (key != null) {
			return key;
		}
		short unitType = primitive.getPrimitiveType();
		float fvalue;
		try {
			fvalue = primitive.getFloatValue(unitType);
		} catch (DOMException ex) {
// Hide this info.   Consider adding DEBUG option			
//			_log.info("Get length failed, use auto value instead ("
//					+ value.getCssText() + ")");
			return ICSSPropertyID.VAL_AUTO;
		}
		return toLength(fvalue, unitType, style, this.getPercentageType(),
				getBaseFont(style));
	}

	/**
	 * @param lengthStr
	 * @param style
	 * @param percenttype
	 * @param font
	 * @return the object
	 */
	public static Object toLength(String lengthStr, ICSSStyle style,
			int percenttype, ICSSFont font) {
		try {
			FloatInfo floatinfo = new FloatInfo(lengthStr);
			return toLength(floatinfo.getValue(), floatinfo.getValueType(),
					style, percenttype, font);
		} catch (Exception e) {
			return ICSSPropertyID.VAL_AUTO;
		}
	}

	/**
	 * Will not calculate percentage value. Used for calculate the "width" and
	 * "height" css property
	 * @param result 
	 * @param unitType 
	 * @param style 
	 * @param percenttype 
	 * @param font
	 * @return an object?
	 */
	public static Object toLength(float result, short unitType,
			ICSSStyle style, int percenttype, ICSSFont font) {
		switch (unitType) {
		case CSSPrimitiveValue.CSS_PERCENTAGE:
			if (percenttype == PERCENTAGE_FONT) {
				result = (int) ((result * font.getFontSize()) / 100.0);
				break;
			}
			return new Length((int) result, true);
		case CSSPrimitiveValue.CSS_PX: // no more calculation needed
		case CSSPrimitiveValue.CSS_NUMBER:
			break;
		case CSSPrimitiveValue.CSS_EMS:
			result *= font.getFontSize();
			break;
		case CSSPrimitiveValue.CSS_EXS:
			result *= font.getXHeight();
			break;
		case CSSPrimitiveValue.CSS_CM:
			result = cmToPixel(result);
			break;
		case CSSPrimitiveValue.CSS_IN:
			result = inToPixel(result);
			break;
		case CSSPrimitiveValue.CSS_MM:
			result = mmToPixel(result);
			break;
		case CSSPrimitiveValue.CSS_PT:
			result = ptToPixel(result);
			break;
		case CSSPrimitiveValue.CSS_PC:
			result = pcToPixel(result);
			break;
		case CSSPrimitiveValue.CSS_STRING:
			return ICSSPropertyID.VAL_AUTO;
			// FIXME:every thing is delt with?
		}
		// ok, when reach here, means we get the float value "result"
		return new Length((int) result, false);
	}

	/**
	 * child class can override this method. e.g: font-size style's base font is
	 * parent style's font.
	 * 
	 * @param style
	 * @return the base font
	 */
	protected ICSSFont getBaseFont(ICSSStyle style) {
		return style.getCSSFont();
	}

	private static float pcToPixel(float value) {
		return ptToPixel(12 * value);
	}

	/**
	 * @param floatValue
	 * @return
	 */
	private static float ptToPixel(float floatValue) {
		// the points used by CSS 2.1 are equal to 1/72th of an inch.
		return inToPixel(floatValue / 72);
	}

	/**
	 * @param floatValue
	 * @return
	 */
	private static float mmToPixel(float floatValue) {
		return cmToPixel(floatValue / 10);
	}

	/**
	 * @param floatValue
	 * @return
	 */
	private static float inToPixel(float floatValue) {
		return getDPI() * floatValue;
	}

	/**
	 * @param floatValue
	 * @return
	 */
	private static float cmToPixel(float floatValue) {
		// 1 inch is equal to 2.54 centimeters
		return inToPixel((float) (floatValue / 2.54));
	}

	/**
	 * @return
	 */
	private static float getDPI() {
		// XXX: cache the value?
		return Display.getCurrent().getDPI().x;
	}

	/**
	 * @param result
	 * @return true if is auto
	 */
	public static boolean isAuto(Object result) {
		if (result == ICSSPropertyID.VAL_AUTO) {
			return true;
		}
		if (result instanceof Length && ((Length) result).getValue() <= 0) {
			return true;
		}
		return false;
	}
}
