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

import java.util.Arrays;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class HTMLUtil {
	/**
	 * check whether a char is a HTML whitespace.
	 * 
	 * @param ch
	 * @return true if ch is considered to be HTML whitespace
	 * @see <a href="http://www.w3.org/TR/html4/struct/text.html#h-9.1">white
	 *      space </a>
	 */
	public static boolean isHTMLWhitespace(char ch) {
		return ch == ' ' || ch == 0x09 || ch == 0x0c || ch == 0x0d
				|| ch == 0x0a || ch == 0x200b;
	}

	/**
	 * @param text
	 * @return true if the entire string is considered to be HTML whitespace
	 */
	public static boolean isHTMLWhitespaceString(String text) {
		for (int i = 0, size = text.length(); i < size; i++) {
			if (!isHTMLWhitespace(text.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * the HTML tags considered to be hidden
	 */
	static final String[] HiddenTags = new String[] {
			IHTMLConstants.TAG_APPLET,
			IHTMLConstants.TAG_AREA,
			IHTMLConstants.TAG_BASE,
			IHTMLConstants.TAG_BASEFONT,
			IHTMLConstants.TAG_HEAD,
			IHTMLConstants.TAG_IFRAME,
			IHTMLConstants.TAG_ISINDEX,
			IHTMLConstants.TAG_LINK,
			IHTMLConstants.TAG_META,
			IHTMLConstants.TAG_NOEMBED,
			IHTMLConstants.TAG_NOFRAMES,
			IHTMLConstants.TAG_NOSCRIPT,
			IHTMLConstants.TAG_SCRIPT,
			IHTMLConstants.TAG_STYLE,
			IHTMLConstants.TAG_TITLE,
			IHTMLConstants.TAG_PARAM };

	/**
	 * @param tag
	 * @return true if the tag name is not in the list of hidden tags
	 */
	public static boolean isVisualHtmlElement(String tag) {
		return !Arrays.asList(HiddenTags).contains(tag.toLowerCase());
	}

	/**
	 * Handling white space. Basically, for leading and trailing whitespace,
	 * will handle according whether the text is just after tag start or before
	 * tag close.
	 * <p>
	 * For consequent whitespace, will compact them.
	 * @param textNode 
	 * @param s 
	 * @return the compacted string
	 * see http://www.w3.org/TR/html4/struct/text.html#h-9.1
	 */
	// XXX: currently, the whitespace handling is in this class, in the future
	// may consider move it
	// into lower layer (display/CSS layer)
	public static String compactWhitespaces(Text textNode, String s) {
		char[] array = s.toCharArray();
		StringBuffer buffer = new StringBuffer(array.length);
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
			buffer.append(array[posi++]);
			continue;
		}
		return buffer.toString();
	}
}
