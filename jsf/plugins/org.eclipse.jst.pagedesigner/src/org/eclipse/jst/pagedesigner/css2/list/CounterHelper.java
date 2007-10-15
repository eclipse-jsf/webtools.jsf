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

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSPrimitiveValue;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * @author mengbo
 */
public final class CounterHelper {
	/**
	 * Image
	 */
	public final static int LIST_T_IMAGE = 0;

	/**
	 * Disc
	 */
	public final static int LIST_T_DISC = 1;

	/**
	 * Circle
	 */
	public final static int LIST_T_CIRCLE = 2;

	/**
	 * Square
	 */
	public final static int LIST_T_SQUARE = 3;

	/**
	 * Decimal
	 */
	public final static int LIST_T_DECIMAL = 0x11;

	/**
	 * Leading-zero decimal
	 */
	public final static int LIST_T_DECIMAL_LEADING_ZERO = 0x12;

	/**
	 * Lower alpha
	 */
	public final static int LIST_T_LOWER_ALPHA = 0x13;

	/**
	 * Lower roman
	 */
	public final static int LIST_T_LOWER_ROMAN = 0x14;

	/**
	 * Upper alpha
	 */
	public final static int LIST_T_UPPER_ALPHA = 0x15;

	/**
	 * Upper roman
	 */
	public final static int LIST_T_UPPER_ROMAN = 0x16;

	/**
	 * Lower greek
	 */
	public final static int LIST_T_LOWER_GREEK = 0x21;

	/**
	 * Armenian
	 */
	public final static int LIST_T_ARMENIAN = 0x22;

	/**
	 * Georgian
	 */
	public final static int LIST_T_GEORGIAN = 0x23;

	/**
	 * None
	 */
	public final static int LIST_T_NONE = 0x24;
	/**
	 * @param style
	 * @param counters
	 */
	public static void processCounterReset(ICSSStyle style, HashMap counters) {
		Assert.isTrue(style != null && counters != null);
		// counter-reset will create new one.
		Object counterResets = style
				.getStyleProperty(ICSSPropertyID.ATTR_COUNTER_RESET);
		if ((counterResets) != null
				&& counterResets != ICSSPropertyMeta.NOT_SPECIFIED) {
			if (counterResets instanceof List) {
				List crList = (List) counterResets;
				for (int i = 0, n = crList.size(); i < n; i++) {
					ResetObject rObject = (ResetObject) crList.get(i);
					String name = rObject.getCounterName();
					Object counter = null;
					if (counters.size() > 0 && counters.containsKey(name)) {
						// Already resolved
						counter = counters.get(name);
					}
					if (counter != null) {
						if (rObject.getInitial() != null) {
							((ICounterValueGenerator) counter)
									.resetCount(rObject.getInitial().intValue());
						} else {
							counter = ((ICounterValueGenerator) counter)
									.resetCount();
						}
						counters.put(((ICounterValueGenerator) counter)
								.getIdentifier(), counter);
					} else {
						// create new one
						Object listStyle = style
								.getStyleProperty(ICSSPropertyID.ATTR_LIST_STYLE_TYPE);
						if (listStyle instanceof String) {
							counter = new CounterValueGenerator(name,
									(String) listStyle, null, style);
							if (rObject.getInitial() != null) {
								((ICounterValueGenerator) counter)
										.resetCount(rObject.getInitial()
												.intValue());
							} else {
								counter = ((ICounterValueGenerator) counter)
										.resetCount();
							}
						}
						counters.put(((ICounterValueGenerator) counter)
								.getIdentifier(), counter);
					}
				}
			}
		}
	}

	/**
	 * @param style
	 */
	public static void processCounterIncrement(ICSSStyle style) {
		Object counterIncrements = style
				.getStyleProperty(ICSSPropertyID.ATTR_COUNTER_INCREMENT);
		if (counterIncrements != null
				&& counterIncrements != ICSSPropertyMeta.NOT_SPECIFIED) {
			if (counterIncrements instanceof List) {
				List crList = (List) counterIncrements;
				for (int i = 0, n = crList.size(); i < n; i++) {
					IncrementObject rObject = (IncrementObject) crList.get(i);
					String name = rObject.getCounterName();
					Object counter = null;
					counter = style.findCounter(name, true);
					if (counter != null) {
						if (HTMLListInfoHelper.getValueInt(style) == null) {
							if (rObject.getIncrement() != null) {
								((ICounterValueGenerator) counter)
										.increase(rObject.getIncrement()
												.intValue());
							} else {
								((ICounterValueGenerator) counter).increase();
							}
						} else {
							((ICounterValueGenerator) counter)
									.setCount(HTMLListInfoHelper
											.getValueInt(style));
						}
					}
				}
			}
		}
	}

	/**
	 * @param style
	 * @return true if the style is a text style
	 */
	public static boolean isText(ICSSStyle style) {
		String display = style.getDisplay();
		Object styleType = style
				.getStyleProperty(ICSSPropertyID.ATTR_LIST_STYLE_TYPE);
		return (display
				.equalsIgnoreCase(ICSSPropertyID.VAL_LIST_ITEM) //
				&& styleType instanceof String //
		&& !CounterValueGenerator.NON_STRING_TYPES.contains(styleType));
	}

	/**
	 * @param style
	 * @return the type of the style.  One of the LIST_T_* values, or -1 if the
	 * style is unknown
	 */
	public static int getType(ICSSStyle style) {
		Object type = style
				.getStyleProperty(ICSSPropertyID.ATTR_LIST_STYLE_TYPE);
		if (type instanceof String) {
			return toTypeInt((String) type);
		}
        return -1;
	}

	/**
	 * @param type
	 * @return the type enumeration for the  type string
	 */
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
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_UPPER_LATIN)) {
			return LIST_T_UPPER_ALPHA;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_UPPER_ALPHA)) {
			return LIST_T_UPPER_ALPHA;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_LOWER_LATIN)) {
			return LIST_T_LOWER_ALPHA;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_LOWER_ALPHA)) {
			return LIST_T_LOWER_ALPHA;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_UPPER_ROMAN)) {
			return LIST_T_UPPER_ROMAN;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_LOWER_ROMAN)) {
			return LIST_T_LOWER_ROMAN;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_LOWER_GREEK)) {
			return LIST_T_LOWER_GREEK;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_ARMENIAN)) {
			return LIST_T_ARMENIAN;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_GEORGIAN)) {
			return LIST_T_GEORGIAN;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_NONE)) {
			return LIST_T_NONE;
		}
		return 0;
	}

	/**
	 * @param cssValue
	 * @return true if the cssValue is an IDENT
	 */
	public static boolean isIdentifier(Object cssValue) {
		return (cssValue instanceof ICSSPrimitiveValue)
				&& ((ICSSPrimitiveValue) cssValue).getPrimitiveType() == CSSPrimitiveValue.CSS_IDENT;
	}

	/**
	 * @param cssValue
	 * @return true if the css value is a number
	 */
	public static boolean isNumber(Object cssValue) {
		return cssValue instanceof ICSSPrimitiveValue
				&& ((ICSSPrimitiveValue) cssValue).getPrimitiveType() == ICSSPrimitiveValue.CSS_INTEGER;
	}
	
	private  CounterHelper()
	{
	    //  util class; no instantiation
	}
}
