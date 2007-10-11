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
package org.eclipse.jst.pagedesigner.css2.widget;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 * @version 1.5
 */
public final class BorderUtil {
	/**
	 * vertical bar style
	 */
	public final static int VERTICAL_BAR = 0;

	/**
	 * horizontal bar style
	 */
	public final static int HORIZONTAL_BAR = 1;

	/**
	 * both scroll bar style
	 */
	public final static int BOTH = 2;

	/**
	 * The scroll width
	 */
	public final static int SCROLL_WIDTH = 16;

	/**
	 * the border thickness
	 */
	public final static int BORDER_THICK = 2;

	/**
	 * draw a mask to a rectangle
	 * 
	 * @param g 
	 * @param rect 
	 * @param color 
	 * 
	 */
	public static void maskRectangle(Graphics g, Rectangle rect, Color color) {
		// set default if one not provided
		if (color == null) {
			color = ColorConstants.blue;
		}

		// get old information and keep them
		int lineStyle = g.getLineStyle();
		Color foregroundColor = g.getForegroundColor();

		g.setLineStyle(SWT.LINE_SOLID);
		g.setForegroundColor(color);
		for (int i = 0, n = rect.height; i < n; i++, i++) {
			for (int j = 0, m = rect.width; j < m; j++, j++) {
				g.drawLine(rect.x + j, rect.y + i, rect.x + j, rect.y + i);
			}
		}

		// restore to the old state
		g.setLineStyle(lineStyle);
		g.setForegroundColor(foregroundColor);
	}

	/**
	 * @param g
	 * @param rect
	 * @param thick
	 * @param inset
	 */
	public static void drawBorder(Graphics g, Rectangle rect, int thick,
			boolean inset) {
		drawBorder(g, rect.x, rect.y, rect.width, rect.height, thick, inset);
	}

	/**
	 * draw a standard border.
	 * 
	 * @param g
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param thick
	 * @param inset
	 */
	public static void drawBorder(Graphics g, int left, int top, int width,
			int height, int thick, boolean inset) {
		Color[] ltColors = new Color[] {
				Display.getCurrent().getSystemColor(
						SWT.COLOR_WIDGET_LIGHT_SHADOW),
				Display.getCurrent().getSystemColor(
						SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW) };
		if (inset) {
			ltColors = new Color[] { ColorConstants.buttonDarker,
					ColorConstants.buttonDarkest };
		}
		for (int i = 0; i < thick; i++) {
			g.setForegroundColor(ltColors[ltColors.length * i / thick]);
			g.drawLine(left + i, top + i, left + width - 1 - i, top + i);
			g.drawLine(left + i, top + i, left + i, top + height - i - i);
		}

		Color[] rbColors = new Color[] {
				Display.getCurrent().getSystemColor(
						SWT.COLOR_WIDGET_DARK_SHADOW),
				Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY) };
		if (inset) {
			rbColors = new Color[] { ColorConstants.buttonLightest,
					ColorConstants.button };
		}
		for (int i = 0; i < thick; i++) {
			g.setForegroundColor(rbColors[ltColors.length * i / thick]);
			g.drawLine(left + i, top - i + height - 1, left + width - 1 - i,
					top - i + height - 1);
			g.drawLine(left + width - 1 - i, top + i, left + width - 1 - i, top
					+ height - 1 - i);
		}
	}

	/**
	 * @param g
	 * @param scrollWidth
	 * @param rect
	 * @param style
	 */
	public static void drawScrollBar(Graphics g, int scrollWidth,
			Rectangle rect, int style) {
		drawScrollBar(g, scrollWidth, BORDER_THICK, rect, style);
	}

	/**
	 * @param g
	 * @param scrollWidth
	 * @param borderThick
	 * @param rect
	 * @param style
	 */
	public static void drawScrollBar(Graphics g, int scrollWidth,
			int borderThick, Rectangle rect, int style) {
		if (style == BOTH) {
			int width = scrollWidth;
			int left = rect.x + rect.width - width;
			int top = rect.y;
			int height = rect.height;
			Rectangle barRect = new Rectangle(left, top, width, height);
			fillBar(g, barRect);
			barRect = new Rectangle(left, top, width, height - scrollWidth);
			drawVerticalPart(g, scrollWidth, borderThick, barRect);

			left = rect.x + borderThick;
			top = top + height - scrollWidth;
			width = rect.width;
			height = scrollWidth;
			barRect = new Rectangle(left, top, width, height);
			fillBar(g, barRect);
			barRect = new Rectangle(left, top, width - scrollWidth - 2, height);
			drawHorizontalPart(g, scrollWidth, borderThick, barRect);
		} else if (style == VERTICAL_BAR) {
			int width = scrollWidth;
			int left = rect.x + rect.width - width;
			int top = rect.y;
			int height = rect.height;
			Rectangle barRect = new Rectangle(left, top, width, height);
			fillBar(g, barRect);
			drawVerticalPart(g, scrollWidth, borderThick, barRect);
		} else if (style == HORIZONTAL_BAR) {
			int left = rect.x + borderThick;
			int top = rect.y + rect.height - scrollWidth;
			int width = rect.width;
			int height = scrollWidth;
			Rectangle barRect = new Rectangle(left, top, width, height);
			fillBar(g, barRect);
			drawHorizontalPart(g, scrollWidth, borderThick, barRect);
		}
	}

	private static void fillBar(Graphics g, Rectangle rect) {
		g.setBackgroundColor(ColorConstants.button);
		g.fillRectangle(rect);
	}

	private static void drawVerticalPart(Graphics g, int arrawLength,
			int borderThick, Rectangle rect) {
		int left = rect.x;
		int top = rect.y;
		int width = Math.min(arrawLength, rect.width);
		int height = Math.min(arrawLength, rect.height / 2);

		Rectangle borderRect = new Rectangle(left, top, width, height);
		BorderUtil.drawBorder(g, borderRect, borderThick, false);

		g.setForegroundColor(ColorConstants.black);
		int decoratorWidth = (width - borderThick * 2) / 2;
		int length = decoratorWidth / 2 + 1;
		int leftX = rect.x + (width - decoratorWidth) / 2 - 1;
		int bottomY = rect.y + (height + length) / 2 - 1;
		for (int i = 0; i < length; i++) {
			g.drawLine(leftX + i, bottomY - i, leftX - i + decoratorWidth,
					bottomY - i);
		}

		top = rect.y + rect.height - height;
		borderRect = new Rectangle(left, top, width, height);
		BorderUtil.drawBorder(g, borderRect, borderThick, false);

		int topY = top + (height - length) / 2;
		g.setForegroundColor(ColorConstants.black);
		for (int i = 0; i < length; i++) {
			g.drawLine(leftX + i, topY + i, leftX - i + decoratorWidth, topY
					+ i);
		}
	}

	private static void drawHorizontalPart(Graphics g, int arrawLength,
			int borderThick, Rectangle rect) {
		int left = rect.x;
		int top = rect.y;
		int width = Math.min(arrawLength, rect.width / 2);
		int height = Math.min(arrawLength, rect.height);

		Rectangle borderRect = new Rectangle(left, top, width, height);
		BorderUtil.drawBorder(g, borderRect, borderThick, false);

		g.setForegroundColor(ColorConstants.black);
		int decoratorHeight = (height - borderThick * 2) / 2;
		int length = decoratorHeight / 2 + 1;
		int leftX = rect.x + (width + length) / 2 - 1;
		int bottomY = rect.y + (height - length) / 2 - 1;
		for (int i = 0; i < length; i++) {
			g.drawLine(leftX - i, bottomY + i, leftX - i, bottomY - i
					+ decoratorHeight);
		}

		left = rect.x + rect.width - width;
		borderRect = new Rectangle(left, top, width, height);
		BorderUtil.drawBorder(g, borderRect, borderThick, false);

		leftX = left + (width - length) / 2;
		g.setForegroundColor(ColorConstants.black);
		for (int i = 0; i < length; i++) {
			g.drawLine(leftX + i, bottomY + i, leftX + i, bottomY - i
					+ decoratorHeight);
		}
	}

	/**
	 * @param g
	 * @param arrawWidth
	 * @param arrawHeight
	 * @param borderThick
	 * @param rect
	 */
	public static void drawVertialBar(Graphics g, int arrawWidth,
			int arrawHeight, int borderThick, Rectangle rect) {
		drawScrollBar(g, arrawWidth, borderThick, rect, VERTICAL_BAR);
	}

	/**
	 * @param figure
	 * @param graphics
	 */
	public static void drawBorderDecorator(CSSFigure figure, Graphics graphics) {
		graphics.setLineWidth(1);
		graphics.setLineStyle(Graphics.LINE_DASH);
		graphics.setForegroundColor(ColorConstants.lightGray);
		List fragments = figure.getFragmentsForRead();
		for (int i = 0, size = fragments.size(); i < size; i++) {
			FlowBox box = (FlowBox) fragments.get(i);
			// XXX: why -1?
			graphics.drawRectangle(box.getX(), box.getY(), box.getWidth() - 1, box
					.getHeight() - 1);
		}
		graphics.restoreState();
	}

	/**
	 * @param figure
	 * @param g
	 */
	public static void maskFigure(CSSFigure figure, Graphics g) {
		List fragments = figure.getFragmentsForRead();
		for (int i = 0, size = fragments.size(); i < size; i++) {
			FlowBox box = (FlowBox) fragments.get(i);
			maskRectangle(g, new Rectangle(box.getX(), box.getY(), box.getWidth(), box
					.getHeight()), null);
		}
	}
	
	private BorderUtil()
	{
	    // util class, no instantiation
	}
}
