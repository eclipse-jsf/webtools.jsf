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
package org.eclipse.jst.pagedesigner.css2.color;

import java.util.StringTokenizer;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.swt.graphics.RGB;

/**
 * @author mengbo
 */
public class CSSColorConverter {
	private static final String PREFIX_RGB = "rgb"; //$NON-NLS-1$

	private static Logger _log = PDPlugin.getLogger(CSSColorConverter.class);

	private static CSSColorConverter _instance = new CSSColorConverter();

	/**
	 * @return singleton instance
	 */
	public static CSSColorConverter getInstantce() {
		if (_instance == null) {
			_instance = new CSSColorConverter();
		}
		return _instance;
	}

	Object getCSSColor(String CSSText) {
		if (CSSText == null) {
			return null;
		}

		CSSText = CSSText.trim().toLowerCase();
		if (CSSText.length() == 0) {
			return null;
		}
		if (CSSColorDefaults.SYSTEM_DEFAULT_COLORS.containsKey(CSSText)) {
			Object result = null;
			result = CSSColorDefaults.SYSTEM_DEFAULT_COLORS.get(CSSText);
			return result;
		} else if (CSSColorDefaults.EXTENDED_COLORS.containsKey(CSSText)) {
			Object result = null;
			result = CSSColorDefaults.EXTENDED_COLORS.get(CSSText);
			return result;
		} else {
			return convertStringToRGB(CSSText);
		}
	}

	private RGB convertStringToRGB(String CSSText) {
		StringBuffer sb = new StringBuffer(CSSText);
		int value;
		try {
			if (sb.indexOf("#") == 0) //$NON-NLS-1$
			{
				if (sb.length() == 4) {
					sb.insert(1, sb.charAt(1));
					sb.insert(3, sb.charAt(3));
					sb.insert(5, sb.charAt(5));
					value = Integer.parseInt(sb.substring(1, sb.length())
							.toString(), 16);
					return new RGB(value >>> 16 & 0xff, value >>> 8 & 0xff,
							value & 0xff);
				} else if (sb.length() == 7) {
					value = Integer.parseInt(sb.substring(1, sb.length()), 16);
					return new RGB(value >>> 16 & 0xff, value >>> 8 & 0xff,
							value & 0xff);
				}
			} else if (CSSText.startsWith(PREFIX_RGB)) {
				return convertRgbToRGB(sb.substring(
						sb.indexOf("(") + 1, sb.indexOf(")"))); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (Exception e) {
			_log.info("CSSColorConverter.0", CSSText, null); //$NON-NLS-1$
			return null;
		}
		return null;
	}

	private RGB convertRgbToRGB(String text) {
		text = text.trim();
		try {
			StringTokenizer tokenizer = new StringTokenizer(text, ",");//$NON-NLS-1$

			if (tokenizer.countTokens() != 3) {
				return null;
			}
			String[] rgbText = new String[3];
			for (int i = 0; i < 3; i++) {
				rgbText[i] = tokenizer.nextToken();
			}

			int[] intRGB = new int[] { 0, 0, 0 };
			for (int i = 0; i < 3; i++) {
				int intValue = -1;
				String textValue = rgbText[i].trim();

				if (textValue.endsWith("%"))//$NON-NLS-1$
				{
					textValue = textValue.substring(0, textValue.length() - 1);

					intValue = Integer.parseInt(textValue) * 255 / 100;
				} else {
					intValue = Integer.parseInt(textValue);
				}
				if (intValue < 0) {
					intValue = 0;
				} else if (intValue > 255) {
					intValue = 255;
				}
				intRGB[i] = intValue;
			}
			return new RGB(intRGB[0], intRGB[1], intRGB[2]);
		} catch (Exception e) {
			// notify
			_log.info("CSSColorConverter.1", text, null); //$NON-NLS-1$
			return null;
		}
	}
}
