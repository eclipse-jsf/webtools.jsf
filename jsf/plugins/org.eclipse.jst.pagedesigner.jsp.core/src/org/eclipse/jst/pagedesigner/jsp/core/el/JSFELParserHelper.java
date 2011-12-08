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
package org.eclipse.jst.pagedesigner.jsp.core.el;


/**
 * Utility class to implement support functionality to "morph" JSP EL into JSF
 * EL
 * 
 * @author mengbo
 * @version 1.5
 */
public final class JSFELParserHelper {
	/**
	 * an EL left brace
	 */
	public static String JSF_EL_LEFT_BRACE = "#{"; //$NON-NLS-1$

	/**
	 * an EL right brace
	 */
	public static String JSF_EL_RIGHT_BRACE = "}"; //$NON-NLS-1$

	private JSFELParserHelper() {
		// util class, do not instantiate
	}

	/**
	 * Gets the parsed form of the given expression string.
	 * @param expressionString 
	 * @return the result of parsing expressionString
	 * @deprecated Use desired EL parser directly.
	 */
	public static Object parseExpression(String expressionString) {
		throw new UnsupportedOperationException(
				"Use desired EL parser directly."); //$NON-NLS-1$
	}

	/**
	 * @param expressionString
	 * @return true if the expression is 'valid'
	 */
	public static boolean isValidEL(String expressionString) {
		if (expressionString == null || expressionString.length() == 0) {
			return false;
		}

		return expressionString.startsWith(JSF_EL_LEFT_BRACE)
				&& expressionString.endsWith(JSF_EL_RIGHT_BRACE);
	}

	/**
	 * @param expressionString
	 * @return expressionString with the left and right braces removed
	 * or the original string if isValidEL(expression) == false
	 */
	public static String trimELBrace(String expressionString) {
		if (!isValidEL(expressionString)) {
			return expressionString;
		}
		String trimedExpression = null;

		trimedExpression = expressionString.substring(JSF_EL_LEFT_BRACE
				.length(), expressionString.length()
				- JSF_EL_RIGHT_BRACE.length());

		return trimedExpression;
	}

	/**
	 * Convert ValueBinding syntax #{ } to JSP EL syntax ${ }
	 * 
	 * @param expressionString
	 *            <code>ValueBinding</code> reference expression
	 * 
	 * @return JSP EL compatible expression
	 */
	public static String toJspElExpression(String expressionString) {
		StringBuffer sb = new StringBuffer(expressionString.length());
		int remainsPos = 0;

		for (int posOpenBrace = expressionString.indexOf('{'); posOpenBrace >= 0; posOpenBrace = expressionString
				.indexOf('{', remainsPos)) {
			if (posOpenBrace > 0) {
				if (posOpenBrace - 1 > remainsPos)
					sb.append(expressionString.substring(remainsPos,
							posOpenBrace - 1));

				if (expressionString.charAt(posOpenBrace - 1) == '$') {
					sb.append("${'${'}"); //$NON-NLS-1$
					remainsPos = posOpenBrace + 1;
					continue;
				} else if (expressionString.charAt(posOpenBrace - 1) == '#') {
					// TODO: should use \\ as escape for \ always, not just when
					// before #{
					// allow use of '\' as escape symbol for #{ (for
					// compatibility with Sun's extended implementation)
					/*
					 * if (isEscaped(expressionString, posOpenBrace - 1)) {
					 * escapes: { for (int i = sb.length() - 1; i >= 0; i--) {
					 * if (sb.charAt(i) != '\\') { sb.setLength( sb.length() -
					 * (sb.length() - i) / 2); break escapes; } }
					 * sb.setLength(sb.length() / 2); } sb.append("#{"); } else {
					 */
					sb.append("${"); //$NON-NLS-1$
					int posCloseBrace = indexOfMatchingClosingBrace(
							expressionString, posOpenBrace);
					sb.append(expressionString.substring(posOpenBrace + 1,
							posCloseBrace + 1));
					remainsPos = posCloseBrace + 1;
					continue;
					// }
				} else {
					if (posOpenBrace > remainsPos)
						sb.append(expressionString.charAt(posOpenBrace - 1));
				}
			}

			// Standalone brace
			sb.append('{');
			remainsPos = posOpenBrace + 1;
		}

		sb.append(expressionString.substring(remainsPos));

		// Create a new String to shrink mem size since we are caching
		return new String(sb.toString());
	}

	private static int findQuote(String expressionString, int start) {
		int indexofSingleQuote = expressionString.indexOf('\'', start);
		int indexofDoubleQuote = expressionString.indexOf('"', start);
		return minIndex(indexofSingleQuote, indexofDoubleQuote);
	}

	/**
	 * Return the index of the matching closing brace, skipping over quoted text
	 * 
	 * @param expressionString
	 *            string to search
	 * @param indexofOpeningBrace
	 *            the location of opening brace to match
	 * 
	 * @return the index of the matching closing brace
	 * 
	 * @throws ReferenceSyntaxException
	 *             if matching brace cannot be found
	 */
	private static int indexOfMatchingClosingBrace(String expressionString,
			int indexofOpeningBrace) {
		int len = expressionString.length();
		int i = indexofOpeningBrace + 1;

		// Loop through quoted strings
		for (;;) {
			if (i >= len) {
				throw new IllegalStateException(
						"Missing closing brace. Expression: '" //$NON-NLS-1$
								+ expressionString + "'"); //$NON-NLS-1$
			}

			int indexofClosingBrace = expressionString.indexOf('}', i);
			i = minIndex(indexofClosingBrace, findQuote(expressionString, i));

			if (i < 0) {
				// No delimiter found
				throw new IllegalStateException(
						"Missing closing brace. Expression: '" //$NON-NLS-1$
								+ expressionString + "'"); //$NON-NLS-1$
			}

			// 1. If quoted literal, find closing quote
			if (i != indexofClosingBrace) {
				i = indexOfMatchingClosingQuote(expressionString, i) + 1;
				if (i == 0) {
					// Note: if no match, i==0 because -1 + 1 = 0
					throw new IllegalStateException(
							"Missing closing quote. Expression: '" //$NON-NLS-1$
									+ expressionString + "'"); //$NON-NLS-1$
				}
			} else {
				// Closing brace
				return i;
			}
		}
	}

	/**
	 * Returns the index of the matching closing quote, skipping over escaped
	 * quotes
	 * 
	 * @param expressionString
	 *            string to scan
	 * @param indexOfOpeningQuote
	 *            start from this position in the string
	 * @return -1 if no match, the index of closing quote otherwise
	 */
	private static int indexOfMatchingClosingQuote(String expressionString,
			int indexOfOpeningQuote) {
		char quote = expressionString.charAt(indexOfOpeningQuote);
		for (int i = expressionString.indexOf(quote, indexOfOpeningQuote + 1); i >= 0; i = expressionString
				.indexOf(quote, i + 1)) {
			if (!isEscaped(expressionString, i)) {
				return i;
			}
		}

		// No matching quote found
		return -1;
	}

	private static boolean isEscaped(String expressionString, int i) {
		int escapeCharCount = 0;
		while ((--i >= 0) && (expressionString.charAt(i) == '\\')) {
			escapeCharCount++;
		}

		return (escapeCharCount % 2) != 0;
	}

	/**
	 * Returns the minimum index >= 0, if any
	 * 
	 * <p>
	 * Use to find the first of two characters in a string:<br>
	 * <code>minIndex(s.indexOf('/'), indexOf('\'))</code>
	 * </p>
	 * @param a 
	 * @param b 
	 * @return the minimum index >= 0, if any
	 * 
	 */
	public static int minIndex(int a, int b) {
		return (a < 0) ? b : (b < 0) ? a : (a < b) ? a : b;
	}
}
