/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.util.regex.Pattern;

/**
 * This is tool class for String processing.
 * 
 * @author mengbo
 */
public class StringUtil {
    
    // pattern to find all end-of-paragraph and end-of-line html tags
    // so they can be converted to newlines
    final private static Pattern htmlToNewline = Pattern.compile("</p>|<br>"); //$NON-NLS-1$
    
    // pattern to strip all <x> and </x> HTML tags
    final private static Pattern removeHTMLTags = Pattern.compile("<[/?\\w\\s=\"\\.\\#]+>"); //$NON-NLS-1$
    
    // pattern to find all runs of spaces longer than one
    final private static Pattern trimInteriorWhitespace = Pattern.compile("[ ]+"); //$NON-NLS-1$
    
	/**
	 * Split a java variable name into words For example, "_aJavaVariable" will
	 * split to "A Java Variable", "_aWTPPackageImplementation" will be "A WTP
	 * Package Implementation"
	 * 
	 * @param variable
	 * @return String
	 */
	public static String splitVariable(String variable) {
		StringBuffer output = new StringBuffer(""); //$NON-NLS-1$
		boolean isCapitalLeading = false;
		boolean isLastSpace = false;

		for (int i = 0, n = variable.length(); i < n; i++) {
			char letter = variable.charAt(i);

			if (letter == '_' || letter == '$') {
				output.append(" "); //$NON-NLS-1$
				isCapitalLeading = false;
				isLastSpace = true;
				continue;
			}

			if (Character.isLowerCase(letter)) {
				int nextIndex = i + 1;
				if (nextIndex < n) {
					char nextLetter = variable.charAt(nextIndex);
					if (Character.isUpperCase(nextLetter)) {
						if (isCapitalLeading) {
							output.append(letter);
							isLastSpace = false;
						} else {
							output.append(Character.toUpperCase(letter));
							isLastSpace = false;
						}
						if (!isLastSpace) {
							output.append(' ');
							isLastSpace = true;
						}
						isCapitalLeading = false;
						continue;
					}
				}
			}
			if (Character.isUpperCase(letter)) {
				int nextIndex = i + 1;
				if (nextIndex < n) {
					char nextLetter = variable.charAt(nextIndex);
					if (Character.isLowerCase(nextLetter)) {
						if (!isLastSpace) {
							output.append(' ');
							isLastSpace = true;
						}
						output.append(letter);
						isCapitalLeading = true;
						isLastSpace = false;
						continue;
					}
				}
			}
			if (isCapitalLeading) {
				output.append(letter);
				isLastSpace = false;
			} else {
				output.append(Character.toUpperCase(letter));
				isCapitalLeading = true;
				isLastSpace = false;
			}
		}

		return output.toString().trim();
	}

	/**
	 * @param str1
	 * @param str2
	 * @return boolean
	 * 
	 * Check if two strings match or not if str1=null and str2=null, it will
	 * return true
	 */
	public static boolean isSameString(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
        return str1.equals(str2);
	}

	/**
	 * 
	 * @param text
	 * @return String Filter and convert a string, or normalize a string For
	 *         example,
	 *         <p>
	 *         test
	 *         </p>
	 *         test1<a/> will be converted to: test\ntest1
	 * 
	 */
	public static String filterConvertString(String text) {
		if (text == null) {
			return ""; //$NON-NLS-1$
		}
        
		String result = htmlToNewline.matcher(text).replaceAll("\n");  //$NON-NLS-1$
        result = removeHTMLTags.matcher(result).replaceAll(""); //$NON-NLS-1$
        result = trimInteriorWhitespace.matcher(result).replaceAll(" "); //$NON-NLS-1$

		return result;
	}

	/**
	 * Determine whether a string is empty or not Example: null string -> return
	 * true; Example: "" string -> return true; Otherwise, will return false;
	 * @param str 
	 * 
	 * @return true if str is null or empty
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
}
