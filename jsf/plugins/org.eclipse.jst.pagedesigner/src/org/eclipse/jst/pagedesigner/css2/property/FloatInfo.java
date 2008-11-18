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

import org.eclipse.wst.css.core.internal.provisional.document.ICSSPrimitiveValue;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * copied from wtp's internal code.
 * 
 * @author mengbo
 */
public final class FloatInfo {
	/**
	 * @param text 
	 */
	public FloatInfo(String text) {
		parse(text);
	}

	/**
	 * 
	 */
	void parse(String text) {
		StringBuffer bufValue = new StringBuffer();
		StringBuffer bufIdent = new StringBuffer();
		boolean bNum = true;
		int len = text.length();
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			if (bNum) {
				if ('0' <= c && c <= '9' || c == '.' || c == '+' || c == '-') {
					bufValue.append(c);
				} else {
					bufIdent.append(c);
					bNum = false;
				}
			} else {
				bufIdent.append(c);
			}
		}
		String valueStr = bufValue.toString();
		_value = Float.valueOf(valueStr).floatValue();
		_identifier = bufIdent.toString();
		_type = getFloatValueType(valueStr, _identifier);
	}

	/**
	 * @return the value
	 * 
	 */
	public float getValue() {
		return _value;
	}

	/**
	 * 
	 */
	String getIdentifier() {
		return _identifier;
	}

	/**
	 * @return the value type
	 */
	public short getValueType() {
		return _type;
	}

	/**
	 * 
	 */
	static short getFloatValueType(String value, String ident) {
		ident = ident.toLowerCase();
		short valueType;
		if (ident.length() == 0) {
			if (0 <= value.indexOf('.')) {
				valueType = CSSPrimitiveValue.CSS_NUMBER;
			} else {
				valueType = ICSSPrimitiveValue.CSS_INTEGER;
			}
		} else if (ident.equals("%")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_PERCENTAGE;
		} else if (ident.equalsIgnoreCase("em")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_EMS;
		} else if (ident.equalsIgnoreCase("ex")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_EXS;
		} else if (ident.equalsIgnoreCase("px")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_PX;
		} else if (ident.equalsIgnoreCase("cm")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_CM;
		} else if (ident.equalsIgnoreCase("mm")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_MM;
		} else if (ident.equalsIgnoreCase("in")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_IN;
		} else if (ident.equalsIgnoreCase("pt")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_PT;
		} else if (ident.equalsIgnoreCase("pc")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_PC;
		} else if (ident.equalsIgnoreCase("deg")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_DEG;
		} else if (ident.equalsIgnoreCase("rad")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_RAD;
		} else if (ident.equalsIgnoreCase("grad")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_GRAD;
		} else if (ident.equalsIgnoreCase("ms")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_MS;
		} else if (ident.equalsIgnoreCase("s")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_S;
		} else if (ident.equalsIgnoreCase("hz")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_HZ;
		} else if (ident.equalsIgnoreCase("khz")) { //$NON-NLS-1$
			//$NON-NLS-1$
			valueType = CSSPrimitiveValue.CSS_KHZ;
		} else {
			valueType = CSSPrimitiveValue.CSS_DIMENSION;
		}
		return valueType;
	}

	private float _value = 0.0f;

	private String _identifier = null;

	private short _type = CSSPrimitiveValue.CSS_UNKNOWN;
}
