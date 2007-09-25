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
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 */
public class EntityMap {
	private static Logger _log = PDPlugin.getLogger(EntityMap.class);

	/**
	 * @param s
	 * @return s translated for HTML entities.
	 */
	public static String translate(String s) {
		char[] array = s.toCharArray();
		StringBuffer buffer = new StringBuffer();
		int posi = 0;
		int len = array.length;
		while (posi < len) {
			if (array[posi] != '&') {
				buffer.append(replaceBadEntity(array[posi++]));
				continue;
			}

			// now array[posi] == '&'
			int lastPosi = posi;
			posi++;
			if (posi < len && array[posi] == '#') {
				posi++;
			}
			while (posi < len) {
				if (!Character.isLetterOrDigit(array[posi]))
                {
					break;
                }
				posi++;
			}
			// now posi>=array.length or array[posi] is non letter or digit
			String str = new String(array, lastPosi, posi - lastPosi);
			if (translateEntity(str, buffer)) {
				// translated, skip the ';'
				if (posi < len && array[posi] == ';') {
					posi++;
				}
			}

			if (posi == len) {
				return buffer.toString();
			}
		}
		return buffer.toString();
	}

	/**
	 * Translate entity maps and compact whitespace. For heading and training
	 * space, will not trim, only compact (making multiple whitespace to become
	 * a single ' ' char).
	 * @param s 
	 * @return the result string.
	 */
	public static String translateAndCompact(String s) {
		char[] array = s.toCharArray();
		StringBuffer buffer = new StringBuffer();
		int posi = 0;
		int len = array.length;
		while (posi < len) {
			if (HTMLUtil.isHTMLWhitespace(array[posi])) {
				while (++posi < len && HTMLUtil.isHTMLWhitespace(array[posi]))
                {
                    // no body
                    // loop until we have find non-whitepspace or endof array
                }

                buffer.append(' ');
				continue;
			}
			if (array[posi] != '&') {
				buffer.append(replaceBadEntity(array[posi++]));
				continue;
			}

			// now array[posi] == '&'
			int lastPosi = posi;
			posi++;
			if (posi < len && array[posi] == '#') {
				posi++;
			}
			while (posi < len) {
				if (!Character.isLetterOrDigit(array[posi])) {
					break;
				}
                posi++;
			}
			// now posi>=array.length or array[posi] is non letter or digit
			String str = new String(array, lastPosi, posi - lastPosi);

			if (translateEntity(str, buffer)) {
				// translated, skip the ';'
				if (posi < len && array[posi] == ';') {
					posi++;
				}
			}

			if (posi == len) {
				return buffer.toString();
			}
		}
		return buffer.toString();
	}

	/**
	 * if can translate will return true and append the result string if can't
	 * translate will return false and append original string
	 * 
	 * @param s
	 *            the form &#number or &letterordigit without the trailing ";"
	 * @param strBuf 
	 * @return true  the translation can be done
	 */
	public static boolean translateEntity(String s, StringBuffer strBuf) {
		int i = HTMLSpecialCharHelper.getSpecial(s); // HTMLSpecialCharHelper
		// support without
		// traning ';'
		if (i != -1) {
			strBuf.append((char) i);
			return true;
		}
		if (s.length() > 2 && s.charAt(1) == '#') {
			String number;
			number = s.substring(2);
			try {
				int n;
				if (number.length() > 0
						&& (number.charAt(0) == 'x' || number.charAt(0) == 'X')) {
					n = Integer.parseInt(number.substring(1), 16);
				} else {
					n = Integer.parseInt(number);
				}
				strBuf.append(replaceBadEntity((char) n));
				return true;
			} catch (Exception ex) {
				// Error in integer formating
				_log.info("Error occurred in integer formatting", ex); //$NON-NLS-1$
				strBuf.append(s);
				return false;
			}
		}
        strBuf.append(s);
        return false;
	}

	/**
	 * In HTML &#149; is sometimes used (mostly based on CP 1252), but is
	 * illegal, because it does not exist in Unicode
	 * 
	 * @param n
	 * @return
	 * see http://www.w3.org/Talks/1999/0830-tutorial-unicode-mjd/slide27-0.html
	 */
	private static char replaceBadEntity(char n) {
		if (n < 132 || n > 156)
			return n;
		switch (n) {
		case 132:
			return (char) 8222;
		case 133:
			return (char) 8230;
		case 134:
			return (char) 8224;
		case 135:
			return (char) 8225;
		case 139:
			return (char) 8249;
		case 140:
			return (char) 338;
		case 145:
			return (char) 8216;
		case 146:
			return (char) 8217;
		case 147:
			return (char) 8220;
		case 148:
			return (char) 8221;
		case 149:
			return (char) 8226;
		case 151:
			return (char) 8212;
		case 153:
			return (char) 8482;
		case 155:
			return (char) 8250;
		case 156:
			return (char) 339;
		default:
			return n;
		}
	}

}
