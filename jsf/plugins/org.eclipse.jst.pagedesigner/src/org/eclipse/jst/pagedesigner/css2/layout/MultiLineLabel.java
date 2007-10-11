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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;

/**
 * A multi-line label
 *
 */
public class MultiLineLabel extends Label {
	private static String ELLIPSIS = "..."; //$NON-NLS-1$

	protected void paintFigure(Graphics graphics) {
		if (isOpaque()) {
			graphics.fillRectangle(getBounds());
		}
		Rectangle bounds1 = getBounds();
		graphics.translate(bounds1.x, bounds1.y);
		drawText(graphics);
		graphics.translate(-bounds1.x, -bounds1.y);
	}

	private void drawText(Graphics graphics) {
		String[] strings = splitString(getText());
		int y = 0;
		int lineHeight = FigureUtilities.getFontMetrics(getFont()).getHeight();
		for (int i = 0; i < strings.length; i++) {
			graphics.drawText(getSubStringText(strings[i]), 0, y);
			y += lineHeight;
		}

	}

	private String[] splitString(String text) {
		String[] lines = new String[1];
		int start = 0, pos;
		do {
			pos = text.indexOf('\n', start);
			if (pos == -1) {
				lines[lines.length - 1] = text.substring(start);
			} else {
				boolean crlf = (pos > 0) && (text.charAt(pos - 1) == '\r');
				lines[lines.length - 1] = text.substring(start, pos
						- (crlf ? 1 : 0));
				start = pos + 1;
				String[] newLines = new String[lines.length + 1];
				System.arraycopy(lines, 0, newLines, 0, lines.length);
				lines = newLines;
			}
		} while (pos != -1);
		return lines;
	}

	/**
	 * @param text
	 * @return the substring text
	 */
	private String getSubStringText(String text) {
		String subStringText = text;

		Font currentFont = getFont();
		int textWidth = FigureUtilities.getTextWidth(text, currentFont);
		if (textWidth - getSize().width <= 0) {
			return subStringText;
		}

		Dimension effectiveSize = new Dimension(getSize().width, 0);

		int dotsWidth = FigureUtilities.getTextWidth(ELLIPSIS, currentFont);

		if (effectiveSize.width < dotsWidth) {
			effectiveSize.width = dotsWidth;
		}

		int subStringLength = getLargestSubstringConfinedTo(text, currentFont,
				effectiveSize.width - dotsWidth);
		subStringText = new String(text.substring(0, subStringLength)
				+ ELLIPSIS);
		return subStringText;
	}

	int getLargestSubstringConfinedTo(String s, Font f, int availableWidth) {
		FontMetrics metrics = FigureUtilities.getFontMetrics(f);
		int min, max;
		float avg = metrics.getAverageCharWidth();
		min = 0;
		max = s.length() + 1;

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
			guessSize = FigureUtilities
					.getTextExtents(s.substring(0, guess), f).width;

			if (guessSize < availableWidth) {
				// We did not use the available width
				min = guess;
			} else {
				// We exceeded the available width
				max = guess;
			}
		}
		return min;
	}
}
