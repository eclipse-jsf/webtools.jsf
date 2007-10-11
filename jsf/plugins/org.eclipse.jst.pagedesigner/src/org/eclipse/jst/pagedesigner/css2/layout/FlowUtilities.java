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
package org.eclipse.jst.pagedesigner.css2.layout;

import java.text.BreakIterator;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;

/**
 * Utility class for FlowFigures.
 * 
 */
public final class FlowUtilities extends FigureUtilities {
	/**
	 * Returns the number of characters from the specified String that will fit
	 * in the available amount of space. An average character width can be
	 * provided as a hint for faster calculation.
	 * 
	 * @param frag
	 *            the TextFragmentBox
	 * @param string
	 *            the String
	 * @param font
	 *            the Font used for measuring
	 * @param availableWidth
	 *            the available width in pixels
	 * @param avg
	 *            0.0, or an avg character width to use during calculation
	 * @param wrapping
	 *            the word wrap style
	 * @return the number of characters that will fit in the space
	 */
	public static int setupFragmentBasedOnTextSpace(TextFragmentBox frag,
			String string, Font font, int availableWidth, float avg,
			int wrapping) {
		int result = getTextForSpace(string, font, availableWidth, avg,
				wrapping);
		frag._length = result;
		setupFragment(frag, font, string);
		return result;
	}

	/**
	 * given the text string, font and available width and wrapping mode.
	 * Calculate how much text can fit into.
	 * 
	 * @param string
	 * @param font
	 * @param availableWidth
	 * @param avg
	 * @param wrapping
	 * @return how much text can fit into
	 */
	public static int getTextForSpace(String string, Font font,
			int availableWidth, float avg, int wrapping) {
		if (string.length() == 0) {
			return 0;
		}

		FontMetrics metrics = getFontMetrics(font);
		BreakIterator breakItr = BreakIterator.getLineInstance();
		breakItr.setText(string);
		int MIN, min, max;
		if (avg == 0.0) {
			avg = metrics.getAverageCharWidth();
		}

		int firstBreak = breakItr.next();

		int winNL = string.indexOf("\r\n"); //$NON-NLS-1$
		int macNL = string.indexOf('\r');
		int unixNL = string.indexOf('\n');

		MIN = min = (wrapping == CSSTextLayout.WORD_WRAP_HARD) ? firstBreak : 1;
		if (macNL == winNL) {
			macNL = -1; // If the Mac newline is just the prefix to the win NL,
			// ignore it
		}

		max = string.length() + 1;

		if (winNL != -1) {
			max = Math.min(max, winNL);
			min = Math.min(min, winNL);
		}
		if (unixNL != -1) {
			max = Math.min(max, unixNL);
			min = Math.min(min, unixNL);
		}
		if (macNL != -1) {
			max = Math.min(max, macNL);
			min = Math.min(min, macNL);
		}

		int origMax = max;
		// The size of the current guess
		int guess = 0, guessSize = 0;

		while ((max - min) > 1) {
			// Pick a new guess size
			// New guess is the last guess plus the missing width in pixels
			// divided by the average character size in pixels
			guess = guess + (int) ((availableWidth - guessSize) / avg);

			if (guess >= max) {
				guess = max - 1;
			}
			if (guess <= min) {
				guess = min + 1;
			}

			// Measure the current guess
			guessSize = getStringExtents2(string.substring(0, guess), font).width;

			if (guessSize <= availableWidth) {
				// We did not use the available width
				min = guess;
			} else {
				// We exceeded the available width
				max = guess;
			}
		}

		int result = string.length();
		switch (wrapping) {
		case CSSTextLayout.WORD_WRAP_HARD:
			if (min == string.length() || min == winNL || min == unixNL
					|| min == macNL) {
				result = min;
			} else if (max == origMax
					&& getStringExtents2(string.substring(0, max), font).width <= availableWidth) {
				result = max;
			} else {
				result = Math.max(MIN, breakItr.preceding(Math.min(max, string
						.length() - 1)));
			}
			break;

		case CSSTextLayout.WORD_WRAP_SOFT:
			if (min == string.length() || min == winNL || min == unixNL
					|| min == macNL) {
				result = min;
			} else if (max == origMax
					&& getStringExtents2(string.substring(0, max), font).width <= availableWidth) {
				result = max;
			} else if (breakItr.isBoundary(min)) {
				result = min;
			} else if (breakItr.isBoundary(Math.min(max, string.length() - 1))) {
				result = max;
			} else {
				result = breakItr.preceding(Math.min(max, string.length() - 1));
			}
			if (result <= 0) {
				result = min;
			}
			break;
		// case CSSTextLayout.WORD_WRAP_TRUNCATE:
		// if (min == string.length() || min == winNL || min == unixNL || min ==
		// macNL)
		// {
		// result = frag._length = min;
		// setupFragment(frag, font, string);
		// if (frag.getWidth() <= availableWidth)
		// return result;
		// min -= 1;
		// }
		// else if (max == origMax && getStringExtents(string.substring(0, max),
		// font).width <= availableWidth)
		// {
		// result = frag._length = max;
		// setupFragment(frag, font, string);
		// return result;
		// }
		// result = breakItr.preceding(Math.min(max + 1, string.length() - 1));
		// if (result <= 0)
		// {
		// ELLIPSIS_SIZE =
		// FigureUtilities.getStringExtents(CSSTextFigure.ELLIPSIS, font);
		// getTextForSpace(frag, string, font, availableWidth -
		// ELLIPSIS_SIZE.width, avg, CSSTextLayout.WORD_WRAP_SOFT);
		// //frag.length = min;
		// frag._truncated = true;
		// result = breakItr.following(min);
		// if (result == BreakIterator.DONE)
		// result = string.length();
		// }
		// else
		// {
		// frag._length = result;
		// }
		}

		return result;
	}

	/**
	 * @param string
	 * @param font
	 * @param availableWidth
	 * @param avg
	 * @return the text width
	 */
	public static int getTextInWidth(String string, Font font,
			int availableWidth, float avg) {
		if (string.length() == 0) {
			return 0;
		}
		int guess = 0;
		while (true) {
			Dimension a = getTextExtents(string.substring(0, guess), font);
			if (a.width >= availableWidth) {
				return guess;
			}
			guess++;
			if (guess == string.length()) {
				return guess;
			}
		}
	}

	/**
	 * change the parent implementation of getStringExtents(). Don't expend the
	 * 1 width. So empty string will not have any width.
	 * 
	 * @param s
	 * @param f
	 * @return the dimension
	 */
	public static Dimension getStringExtents2(String s, Font f) {
		return new Dimension(getStringDimension(s, f));
	}

	static void setupFragment(TextFragmentBox frag, Font f, String s) {
		// if (frag.length != s.length())
		// we don't skip whitespace here. since already truncated in
		// CSSTextLayout

		// while (frag.length > 0 &&
		// Character.isElementContentWhitespace(s.charAt(frag.length - 1)))
		// frag.length--;
		frag.setTextData(s.substring(0, frag._length));
		Dimension d = getStringExtents2(s.substring(0, frag._length), f);
		FontMetrics fm = getFontMetrics(f);
		frag.setHeight(fm.getHeight());
		frag.setAscent(fm.getAscent() + fm.getLeading());
		if (frag._length > 0
				&& Character.isWhitespace(s.charAt(frag._length - 1))) {
			frag._isLastCharWhitespace = true;
		} else {
			frag._isLastCharWhitespace = false;
		}
		frag.setWidth(d.width);
	}

	private FlowUtilities()
	{
	    // no instantiation
	}
}
