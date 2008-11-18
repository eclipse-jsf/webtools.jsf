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

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.property.TextDecorationMeta;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * @author mengbo
 */
public class TextLayoutSupport {
	private static final Logger _log = PDPlugin
			.getLogger(TextLayoutSupport.class);

	private static final String[] DELIMITERS = { "\r\n", //$NON-NLS-1$
			"\n", //$NON-NLS-1$
			"\r" //$NON-NLS-1$
	};

	static private int delimeterLength;

	/**
	 * Reuses an existing <code>TextFragmentBox</code>, or creates a new one.
	 * 
	 * @param i
	 *            the index
	 * @param fragments
	 *            the original list of fragments
	 * @return a TextFragmentBox
	 */
	// copied from TextLayout
	protected static TextFragmentBox getFragment(int i, List fragments) {
		if (fragments.size() > i) {
			return (TextFragmentBox) fragments.get(i);
		}
		TextFragmentBox box = new TextFragmentBox();
		fragments.add(box);
		return box;
	}

	/**
	 * Returns the average character width of given TextFragmentbox
	 * 
	 * @param fragment
	 *            the TextFragmentBox
	 * @return the average character width
	 */
	public static float getAverageCharWidth(TextFragmentBox fragment) {
		if (fragment._width != 0 && fragment._length != 0) {
			return fragment._width / (float) fragment._length;
		}
		return 0.0f;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * this method will create a set of TextFragment. Each fragment will offset
	 * to the original text (whole text for the text figure).
	 * @param context 
	 * @param text 
	 * @param fragments 
	 * @param font 
	 * @param wrappingStyle 
	 * @param trimLeading 
	 */
	public static void layoutNormal(FlowContext context, String text,
			List fragments, Font font, int wrappingStyle, boolean trimLeading) {
		int i = 0; // The index of the current fragment;
		int offset = 0;
		if (trimLeading) {
			offset = 1;
			text = text.substring(1);
		}

		int length = 0; // The length of the current fragment
		float prevAvgCharWidth;
		LineBox currentLine;
		TextFragmentBox fragment;

		while (text.length() > 0) {
			fragment = null;
			prevAvgCharWidth = 0f;
			fragment = getFragment(i, fragments);
			prevAvgCharWidth = getAverageCharWidth(fragment);

			// Check for newline, if it exists, call context.endLine and skip
			// over the newline
			// Exccept for first time through, don't do this.
			if (i != 0) {
				boolean changed = false;
				if (text.charAt(0) == '\r') {
					text = text.substring(1);
					changed = true;
					offset += 1;
				}
				if (text.length() != 0 && text.charAt(0) == '\n') {
					text = text.substring(1);
					changed = true;
					offset += 1;
				}
				if (changed) {
					context.endLine();
				}
			}

			fragment._offset = offset;

			// This loop is done at most twice.
			// The second time through, a context.endLine()
			// was requested, and the loop will break.
			while (true) {
				currentLine = context.getCurrentLine();
				length = FlowUtilities.setupFragmentBasedOnTextSpace(fragment,
						text, font, currentLine.getAvailableWidth(),
						prevAvgCharWidth, wrappingStyle);

				if (fragment._width <= currentLine.getAvailableWidth()
						|| !context.isCurrentLineOccupied()) {
					break;
				}
				context.endLine();
			}
			// fragment.x = context.getCurrentX();
			context.addToCurrentLine(fragment);
			text = text.substring(length);
			offset += length;
			if (text.length() > 0) {
				context.endLine();
			}
			i++;
		}

		// Remove the remaining unused fragments.
		while (i < fragments.size()) {
			fragments.remove(fragments.size() - 1);
		}
	}

	/**
	 * @param context
	 * @param text
	 * @param fragments
	 * @param font
	 */
	public static void layoutNoWrap(FlowContext context, String text,
			List fragments, Font font) {
		TextFragmentBox fragment;
		int i = 0;
		int offset = 0;

		while (offset < text.length()) {
			int result = nextLineBreak(text, offset);
			fragment = getFragment(i++, fragments);
			fragment._length = result - offset;
			fragment._offset = offset;
			FlowUtilities.setupFragment(fragment, font, text.substring(offset));
			context.getCurrentLine().add(fragment);
			offset = result + delimeterLength;
			if (delimeterLength != 0) {
				// in nextLineBreak we fo
				context.endLine();
			}

		}
		// Remove the remaining unused fragments.
		while (i < fragments.size()) {
			fragments.remove(i++);
		}
	}

	private static int nextLineBreak(String text, int offset) {
		int result = text.length();
		delimeterLength = 0;
		int current;
		for (int i = 0; i < DELIMITERS.length; i++) {
			current = text.indexOf(DELIMITERS[i], offset);
			if (current != -1 && current < result) {
				result = current;
				delimeterLength = DELIMITERS[i].length();
			}
		}
		return result;
	}

	/**
	 * @param g
	 * @param fragments
	 * @param font
	 * @param textDecoration
	 */
	public static void paintTextFigure(Graphics g, List fragments, Font font,
			int textDecoration) {
		paintTextFigure(g, fragments, font, null, textDecoration);
	}

	/**
	 * @param g
	 * @param rect
	 * @param textDecoration
	 */
	public static void paintTextDecoration(Graphics g, Rectangle rect,
			int textDecoration) {
		if ((textDecoration & TextDecorationMeta.UNDERLINE) != 0) {
			g.drawLine(rect.x, rect.y + rect.height - 1, rect.x + rect.width
					- 1, rect.y + rect.height - 1);
		}
		if ((textDecoration & TextDecorationMeta.OVERLINE) != 0) {
			g.drawLine(rect.x, rect.y + 1, rect.x + rect.width - 1, rect.y + 1);
		}
		if ((textDecoration & TextDecorationMeta.LINETHROUGH) != 0) {
			g.drawLine(rect.x, rect.y + rect.height / 2, rect.x + rect.width
					- 1, rect.y + rect.height / 2);
		}
	}

	/**
	 * @param g
	 * @param fragments
	 * @param font
	 * @param color
	 * @param textDecoration
	 */
	public static void paintTextFigure(Graphics g, List fragments, Font font,
			Color color, int textDecoration) {
		// FIXME: It happens there is problem in this method's parameters. what
		// exception should be catched?
		try {
			TextFragmentBox frag;
			// XXX: adjust font. Here is not using setFont(), because that will
			// result in revalidate
			g.setFont(font);

			for (int i = 0; i < fragments.size(); i++) {
				frag = (TextFragmentBox) fragments.get(i);
				// if (!g.getClip(Rectangle.SINGLETON).intersects(frag))
				// continue;
				String draw;
				draw = frag.getTextData();

				if (color != null) {
					g.setForegroundColor(color);
				}
				g.drawText(draw, frag._x, frag._y);
				if ((textDecoration & TextDecorationMeta.UNDERLINE) != 0) {
					g.drawLine(frag._x, frag._y + frag.getHeight() - 1, frag._x
							+ frag.getWidth(), frag._y + frag.getHeight() - 1);
				}
				if ((textDecoration & TextDecorationMeta.OVERLINE) != 0) {
					g.drawLine(frag._x, frag._y, frag._x + frag.getWidth(),
							frag._y);
				}
				if ((textDecoration & TextDecorationMeta.LINETHROUGH) != 0) {
					g.drawLine(frag._x, frag._y + frag.getHeight() / 2, frag._x
							+ frag.getWidth(), frag._y + frag.getHeight() / 2);
				}

				if (Debug.DEBUG_BASELINE) {
					g.drawLine(frag._x, frag._y + frag.getAscent(), frag._x
							+ frag.getWidth(), frag._y + frag.getAscent());
				}
			}
		} catch (Exception e) {
			// "Error in text painting:"
			_log.info("TextLayoutSupport.Info.1", e); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * @param g
	 * @param fragments
	 * @param text
	 *            all the text in the Text figure.
	 * @param font
	 * @param color
	 * @param textDecoration
	 * @param start
	 * @param end
	 * @param selectionForeColor
	 * @param selectionBackColor
	 */
	public static void paintTextFigureWithSelection(Graphics g, List fragments,
			String text, Font font, Color color, int textDecoration, int start,
			int end, Color selectionForeColor, Color selectionBackColor) {
		// FIXME: It happens there is problem in this method's parameters. what
		// exception should be catched?
		try {
			TextFragmentBox frag;

			Color originalForeground = g.getForegroundColor();
			Color originalBackgroud = g.getBackgroundColor();

			// XXX: adjust font. Here is not using setFont(), because that will
			// result in revalidate
			g.setFont(font);

			for (int i = 0, n = fragments.size(); i < n; i++) {
				frag = (TextFragmentBox) fragments.get(i);

				// to make things simpler, we always draw the line using default
				// color
				if (color != null) {
					g.setForegroundColor(color);
				}

				// if (!g.getClip(Rectangle.SINGLETON).intersects(frag))
				// continue;
				String draw;
				draw = frag.getTextData();
				if (frag._offset >= end || frag._offset + frag._length <= start) {
					// we are not in selection. no need to change color
					g.drawText(draw, frag._x, frag._y);
					paintTextDecoration(g, frag.getRectangle(), textDecoration);
				} else if (frag._offset >= start
						&& frag._offset + frag._length <= end) {
					// we are fully in selection
					g.setForegroundColor(selectionForeColor);
					g.setBackgroundColor(selectionBackColor);
					g
							.fillRectangle(frag._x, frag._y, FlowUtilities
									.getTextExtents(draw, font).width, frag
									.getHeight());
					g.drawText(draw, frag._x, frag._y);
					paintTextDecoration(g, frag.getRectangle(), textDecoration);
				} else {
					// partial of the fragment's text is in selection.

					// draw the original string first
					g.drawText(draw, frag._x, frag._y);
					// then override with the selected parts.
					g.setForegroundColor(selectionForeColor);
					g.setBackgroundColor(selectionBackColor);
					int partialStart = frag._offset > start ? frag._offset
							: start;
					int partialEnd = (frag._offset + frag._length > end) ? end
							: (frag._offset + frag._length);
					int x = 0;
					String skip = text.substring(frag._offset, partialStart);
					x = FlowUtilities.getTextExtents(skip, font).width;
					String todraw = text.substring(partialStart, partialEnd);
					if (todraw.length() > 0) {
						Dimension dimension = FlowUtilities.getTextExtents(skip
								+ todraw, font);
						g.fillRectangle(frag._x + x, frag._y, dimension.width
								- x, dimension.height);
						g.drawText(skip + todraw, frag._x, frag._y);
						if (color != null) {
							g.setForegroundColor(color);
						} else {
							g.setForegroundColor(originalForeground);
						}
						g.drawText(skip, frag._x, frag._y);
						paintTextDecoration(g, frag.getRectangle(),
								textDecoration);
						g.setForegroundColor(selectionForeColor);
						paintTextDecoration(g,
								new Rectangle(frag._x + x, frag._y,
										dimension.width - x, dimension.height),
								textDecoration);
					}
				}

				// we do this in each loop, to make sure we are using correct
				// color
				g.setForegroundColor(originalForeground);
				g.setBackgroundColor(originalBackgroud);

			}
		} catch (Exception e) {
			// "Error in text painting:"
			_log.info("TextLayoutSupport.Info.1", e); //$NON-NLS-1$
		}
	}

	/**
	 * @param textAlign
	 * @param rect
	 * @param textWidth
	 * @return the x value
	 */
	public static int getBeginX(Object textAlign, Rectangle rect, int textWidth) {
		int x = rect.x;
		if (textAlign != null) {
			String align = textAlign.toString();
			if ("left".equalsIgnoreCase(align)) //$NON-NLS-1$
			{
				x = rect.x + 1;
			} else if ("right".equalsIgnoreCase(align)) //$NON-NLS-1$
			{
				x = rect.x + rect.width - textWidth - 1;
				if (x < 1) {
					x = 1;
				}
			} else if ("center".equalsIgnoreCase(align)) //$NON-NLS-1$
			{
				int offset = (rect.width - textWidth) / 2;
				if (offset <= 0) {
					offset = 0;
				}
				x = x + offset + 1;
			}
		}
		return x;
	}
	
	private TextLayoutSupport()
	{
	    // no instantiation
	}
}
