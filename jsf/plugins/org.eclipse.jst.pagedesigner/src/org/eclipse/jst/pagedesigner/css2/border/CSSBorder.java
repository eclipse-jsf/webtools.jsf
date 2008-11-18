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
package org.eclipse.jst.pagedesigner.css2.border;

import java.util.Arrays;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.MessageFormater;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 */
public class CSSBorder extends AbstractBorder {

	private static final String BODER_QUERY_TEMPLETE = "border-{0}-style"; //$NON-NLS-1$

	private static final String COLOR_QUERY_TEMPLETE = "border-{0}-color"; //$NON-NLS-1$

	private final ICSSStyle _style;

	private final Rectangle _innerRect = new Rectangle();

	/**
	 * @param style
	 */
	public CSSBorder(ICSSStyle style) {
		this._style = style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		return _style.getBorderInsets();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure,
	 *      org.eclipse.draw2d.Graphics, org.eclipse.draw2d.geometry.Insets)
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		_innerRect.setBounds(tempRect);
		_innerRect.crop(_style.getBorderInsets());
		paintEdge(graphics, tempRect, _innerRect, ICSSStyle.LEFT);
		paintEdge(graphics, tempRect, _innerRect, ICSSStyle.RIGHT);
		paintEdge(graphics, tempRect, _innerRect, ICSSStyle.TOP);
		paintEdge(graphics, tempRect, _innerRect, ICSSStyle.BOTTOM);
	}

	/**
	 * @param style
	 * @return
	 */
	private boolean shouldDraw(String style) {
		return style != null && !ICSSPropertyID.VAL_NONE.equals(style)
				&& !ICSSPropertyID.VAL_HIDDEN.equals(style);
	}

	/**
	 * Fetchs the color array used to draw the given edge under the given style
	 * 
	 * @param graphics
	 * @param style
	 * @param edge
	 * @return
	 */
	private RGB[] getEdgeColors(Graphics graphics, String style, String edge) {
		String property = MessageFormater.format(COLOR_QUERY_TEMPLETE, edge);
		Object obj = _style.getStyleProperty(property);
		if (obj instanceof RGB) {
			return getCustomColors(graphics, style, edge, (RGB) obj);
		} else if (obj instanceof Color) {
			return getCustomColors(graphics, style, edge, ((Color) obj)
					.getRGB());
		} else {
			return getDefaultColors(graphics, style, edge);
		}
	}
	// TODO: needs refactoring
	private RGB[] getDefaultColors(Graphics graphics, String style, String edge) {
		if (ICSSPropertyID.VAL_OUTSET.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { ColorConstants.button.getRGB(),
						ColorConstants.buttonLightest.getRGB() };
			}
            return new RGB[] { ColorConstants.buttonDarkest.getRGB(),
            		ColorConstants.buttonDarker.getRGB() };
		} else if (ICSSPropertyID.VAL_INSET.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { ColorConstants.buttonDarker.getRGB(),
						ColorConstants.buttonDarkest.getRGB() };
			}
            return new RGB[] { ColorConstants.buttonLightest.getRGB(),
            		ColorConstants.button.getRGB() };
		} else if (ICSSPropertyID.VAL_TDBORDERSTYLE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { ColorConstants.buttonDarker.getRGB() };
			}
            return new RGB[] { ColorConstants.button.getRGB() };
		} else if (ICSSPropertyID.VAL_RIDGE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { ColorConstants.button.getRGB(),
						ColorConstants.buttonDarkest.getRGB() };
			}
            return new RGB[] { ColorConstants.buttonDarkest.getRGB(),
            		ColorConstants.button.getRGB() };
		} else if (ICSSPropertyID.VAL_GROOVE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { ColorConstants.buttonDarker.getRGB(),
						ColorConstants.buttonLightest.getRGB() };
			}
            return new RGB[] { ColorConstants.buttonLightest.getRGB(),
            		ColorConstants.buttonDarker.getRGB(), };
		} else if (ICSSPropertyID.VAL_DOUBLE.equals(style)) {
			return new RGB[] { ColorConstants.buttonDarkest.getRGB(),
					graphics.getBackgroundColor().getRGB(),
					ColorConstants.buttonDarkest.getRGB() };
		} else if (ICSSPropertyID.VAL_SOLID.equals(style)) {
			return new RGB[] { ColorConstants.black.getRGB() };
		}

		return new RGB[] { ColorConstants.black.getRGB() };
	}

    // TODO: needs refactoring
	private RGB[] getCustomColors(Graphics graphics, String style, String edge,
			RGB baseColor) {
		if (ICSSPropertyID.VAL_OUTSET.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] {
						new RGB(baseColor.red * 3 / 4,
								baseColor.green * 3 / 4,
								baseColor.blue * 3 / 4),
						new RGB(baseColor.red, baseColor.green, baseColor.blue) };
			}
            return new RGB[] {
            		new RGB(baseColor.red / 2,
            				baseColor.green / 2,
            				baseColor.blue / 2),
            		new RGB(baseColor.red / 4,
            				baseColor.green / 4,
            				baseColor.blue / 4) };
		} else if (ICSSPropertyID.VAL_INSET.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] {
						new RGB(baseColor.red / 4,
								baseColor.green / 4,
								baseColor.blue / 4),
						new RGB(baseColor.red / 2,
								baseColor.green / 2,
								baseColor.blue / 2) };
			}
            return new RGB[] {
            		new RGB(baseColor.red, baseColor.green, baseColor.blue),
            		new RGB(baseColor.red * 3 / 4,
            				baseColor.green * 3 / 4,
            				baseColor.blue * 3 / 4), };
		} else if (ICSSPropertyID.VAL_TDBORDERSTYLE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] { new RGB(baseColor.red / 4,
						baseColor.green / 4, baseColor.blue / 4) };
			}
            return new RGB[] { new RGB(baseColor.red, baseColor.green,
            		baseColor.blue) };
		} else if (ICSSPropertyID.VAL_RIDGE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] {
						new RGB(baseColor.red * 3 / 4,
								baseColor.green * 3 / 4,
								baseColor.blue * 3 / 4),
						new RGB(baseColor.red / 2,
								baseColor.green / 2,
								baseColor.blue / 2) };
			}
            return new RGB[] {
            		new RGB(baseColor.red / 2,
            				baseColor.green / 2,
            				baseColor.blue / 2),
            		new RGB(baseColor.red * 3 / 4,
            				baseColor.green * 3 / 4,
            				baseColor.blue * 3 / 4) };
		} else if (ICSSPropertyID.VAL_GROOVE.equals(style)) {
			if (ICSSStyle.TOP.equals(edge) || ICSSStyle.LEFT.equals(edge)) {
				return new RGB[] {
						new RGB(baseColor.red / 4,
								baseColor.green / 4,
								baseColor.blue / 4),
						new RGB(baseColor.red, baseColor.green, baseColor.blue) };

			}
            return new RGB[] {
            		new RGB(baseColor.red, baseColor.green, baseColor.blue),
            		new RGB(baseColor.red / 4,
            				baseColor.green / 4,
            				baseColor.blue / 4) };
		} else if (ICSSPropertyID.VAL_DOUBLE.equals(style)) {
			return new RGB[] {
					new RGB(baseColor.red, baseColor.green, baseColor.blue),
					graphics.getBackgroundColor().getRGB(),
					new RGB(baseColor.red, baseColor.green, baseColor.blue) };
		} else if (ICSSPropertyID.VAL_SOLID.equals(style)) {
			return new RGB[] { new RGB(baseColor.red, baseColor.green,
					baseColor.blue) };
		}
		return new RGB[] { new RGB(baseColor.red, baseColor.green,
				baseColor.blue) };
	}

	/**
	 * @param graphics
	 * @param rect
	 * @param innerRect
	 * @param edge
	 * @param style
	 */
	public void paintEdge(Graphics graphics, Rectangle rect,
			Rectangle innerRect, String edge, String style) {
		if (!shouldDraw(style)) {
			return;
		}
		RGB[] rgbs = getEdgeColors(graphics, style, edge);

		if (ICSSStyle.TOP.equals(edge)) {
			paintTopEdge(graphics, rgbs, style, rect, innerRect);
		} else if (ICSSStyle.BOTTOM.equals(edge)) {
			paintBottomEdge(graphics, rgbs, style, rect, innerRect);
		} else if (ICSSStyle.LEFT.equals(edge)) {
			paintLeftEdge(graphics, rgbs, style, rect, innerRect);
		} else if (ICSSStyle.RIGHT.equals(edge)) {
			paintRightEdge(graphics, rgbs, style, rect, innerRect);
		}
	}

	/**
	 * @param graphics
	 * @param rect
	 * @param innerRect
	 * @param edge
	 */
	protected void paintEdge(Graphics graphics, Rectangle rect,
			Rectangle innerRect, String edge) {
		String property = MessageFormater.format(BODER_QUERY_TEMPLETE, edge);
		Object obj = _style.getStyleProperty(property);
		String style = obj.toString();
		paintEdge(graphics, rect, innerRect, edge, style);
	}

	private void paintTopEdge(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, Rectangle innerRect) {
		int leftX = rect.x;
		int rightX = rect.right() - 1;
		int y = rect.y;
		int width = innerRect.y - rect.y;

		if (ICSSPropertyID.VAL_DOTTED.equals(style)) {
			drawDottedBorder(graphics, rgbs, ICSSStyle.TOP, rect, width);
		} else if (ICSSPropertyID.VAL_DASHED.equals(style)) {
			drawDashedBorder(graphics, rgbs, ICSSStyle.TOP, rect, width);
		} else {
			double xLeftRate = ((double) (innerRect.x - rect.x)) / width;
			double xRightRate = ((double) (rect.right() - innerRect.right()))
					/ width;
			graphics.pushState();
			for (int i = 0; i < width; i++) {
				Color color = new Color(Display.getCurrent(), rgbs[rgbs.length
						* i / width]);
				graphics.setForegroundColor(color);
				graphics.drawLine((int) (leftX + i * xLeftRate), y + i,
						(int) (rightX - i * xRightRate), y + i);
				color.dispose();
			}
			graphics.popState();
		}
	}

	private void paintBottomEdge(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, Rectangle innerRect) {
		int leftX = rect.x;
		int rightX = rect.right() - 1;
		int y = rect.bottom() - 1;
		int width = rect.bottom() - innerRect.bottom();

		if (ICSSPropertyID.VAL_DOTTED.equals(style)) {
			drawDottedBorder(graphics, rgbs, ICSSStyle.BOTTOM, rect, width);
		} else if (ICSSPropertyID.VAL_DASHED.equals(style)) {
			drawDashedBorder(graphics, rgbs, ICSSStyle.BOTTOM, rect, width);
		} else {
			double xLeftRate = ((double) (innerRect.x - rect.x)) / width;
			double xRightRate = ((double) (rect.right() - innerRect.right()))
					/ width;
			graphics.pushState();
			for (int i = 0; i < width; i++) {
				Color color = new Color(Display.getCurrent(), rgbs[rgbs.length
						* i / width]);
				graphics.setForegroundColor(color);
				graphics.drawLine(leftX + (int) (i * xLeftRate), y - i, rightX
						- (int) (i * xRightRate), y - i);
				color.dispose();
			}
			graphics.popState();
		}
	}

	private void paintLeftEdge(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, Rectangle innerRect) {
		int x = rect.x;
		int topY = rect.y;
		int bottomY = rect.bottom() - 1;
		int width = innerRect.x - rect.x;

		if (ICSSPropertyID.VAL_DOTTED.equals(style)) {
			drawDottedBorder(graphics, rgbs, ICSSStyle.LEFT, rect, width);
		} else if (ICSSPropertyID.VAL_DASHED.equals(style)) {
			drawDashedBorder(graphics, rgbs, ICSSStyle.LEFT, rect, width);
		} else {
			double yTopRate = ((double) (innerRect.y - rect.y)) / width;
			double yBottomRate = ((double) (rect.bottom() - innerRect.bottom()))
					/ width;
			graphics.pushState();
			for (int i = 0; i < width; i++) {
				Color color = new Color(Display.getCurrent(), rgbs[rgbs.length
						* i / width]);
				graphics.setForegroundColor(color);
				graphics.drawLine(x + i, topY + (int) (i * yTopRate), x + i,
						bottomY - (int) (i * yBottomRate));
				color.dispose();
			}
			graphics.popState();
		}

	}

	private void paintRightEdge(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, Rectangle innerRect) {
		int x = rect.right() - 1;
		int topY = rect.y;
		int bottomY = rect.bottom() - 1;
		int width = rect.right() - innerRect.right();

		if (ICSSPropertyID.VAL_DOTTED.equals(style)) {
			drawDottedBorder(graphics, rgbs, ICSSStyle.RIGHT, rect, width);
		} else if (ICSSPropertyID.VAL_DASHED.equals(style)) {
			drawDashedBorder(graphics, rgbs, ICSSStyle.RIGHT, rect, width);
		} else {
			graphics.pushState();
			for (int i = 0; i < width; i++) {
				double yTopRate = ((double) (innerRect.y - rect.y)) / width;
				double yBottomRate = ((double) (rect.bottom() - innerRect
						.bottom()))
						/ width;
				Color color = new Color(Display.getCurrent(), rgbs[rgbs.length
						* i / width]);
				graphics.setForegroundColor(color);
				graphics.drawLine(x - i, topY + (int) (i * yTopRate), x - i,
						bottomY - (int) (i * yBottomRate));
				color.dispose();
			}
			graphics.popState();
		}
	}

	private void drawDottedBorder(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, int width) {
		if (width == 0 || 3 * width > rect.width) {
			return;
		}

		int beginX = 0;
		int beginY = 0;
		int xRate = 0;
		int yRate = 0;
		int span = 0;

		if (ICSSStyle.TOP.equals(style)) {
			beginX = rect.x;
			beginY = rect.y;
			xRate = 1;
			yRate = 0;
			span = rect.width;
		} else if (ICSSStyle.LEFT.equals(style)) {
			beginX = rect.x;
			beginY = rect.y;
			xRate = 0;
			yRate = 1;
			span = rect.height;
		} else if (ICSSStyle.BOTTOM.equals(style)) {
			beginX = rect.x;
			beginY = rect.y + rect.height - width;
			xRate = 1;
			yRate = 0;
			span = rect.width;
		} else if (ICSSStyle.RIGHT.equals(style)) {
			beginX = rect.x + rect.width - width;
			beginY = rect.y;
			xRate = 0;
			yRate = 1;
			span = rect.height;
		}

		int dottedCount = (span + width) / (2 * width);
		if (dottedCount < 2) {
			dottedCount = 2;
		}
		int averagePad = (span - dottedCount * width) / (dottedCount - 1);
		int leftPad = (span - dottedCount * width) % (dottedCount - 1);
		int[] paddings = new int[dottedCount - 1];
		Arrays.fill(paddings, averagePad);
		for (int i = 0; i < leftPad; i++) {
			paddings[i] = paddings[i] + 1;
		}

		int pad = 0;
		Color color = new Color(Display.getCurrent(), rgbs[0]);
		graphics.pushState();
		graphics.setBackgroundColor(color);
		for (int i = 0; i < dottedCount; i++) {
			graphics.fillOval(beginX + (pad + width * i) * xRate, beginY
					+ (pad + width * i) * yRate, width, width);
			if (i != dottedCount - 1) {
				pad += paddings[i];
			}
		}
		graphics.popState();
		color.dispose();

	}

	private void drawDashedBorder(Graphics graphics, RGB[] rgbs, String style,
			Rectangle rect, int borderThick) {
		if (borderThick == 0 || 5 * borderThick > rect.width) {
			return;
		}

		if ((5 * borderThick > rect.height)
				&& (ICSSStyle.LEFT.equals(style) || ICSSStyle.RIGHT
						.equals(style))) {
			return;
		}

		int width = 0;
		int height = 0;
		int edgeLength = 0;
		int beginX = 0;
		int beginY = 0;
		int xRate = 0;
		int yRate = 0;
		int span = 0;

		if (ICSSStyle.TOP.equals(style)) {
			width = borderThick * 2;
			height = borderThick;
			beginX = rect.x;
			beginY = rect.y;
			xRate = 1;
			yRate = 0;

			span = rect.width;
			edgeLength = width;
		} else if (ICSSStyle.LEFT.equals(style)) {
			width = borderThick;
			height = borderThick * 2;
			beginX = rect.x;
			beginY = rect.y;
			xRate = 0;
			yRate = 1;

			span = rect.height;
			edgeLength = height;
		} else if (ICSSStyle.BOTTOM.equals(style)) {
			width = borderThick * 2;
			height = borderThick;
			beginX = rect.x;
			beginY = rect.y + rect.height - height;
			xRate = 1;
			yRate = 0;

			span = rect.width;
			edgeLength = width;
		} else if (ICSSStyle.RIGHT.equals(style)) {
			width = borderThick;
			height = borderThick * 2;
			beginX = rect.x + rect.width - width;
			beginY = rect.y;
			xRate = 0;
			yRate = 1;

			span = rect.height;
			edgeLength = height;
		}

		int dottedCount = (span + borderThick) / (edgeLength + borderThick);
		if (dottedCount < 2) {
			dottedCount = 2;
		}
		int averagePad = (span - dottedCount * edgeLength) / (dottedCount - 1);
		int leftPad = (span - dottedCount * edgeLength) % (dottedCount - 1);
		int[] paddings = new int[dottedCount - 1];
		Arrays.fill(paddings, averagePad);
		for (int i = 0; i < leftPad; i++) {
			paddings[i] = paddings[i] + 1;
		}

		int pad = 0;
		graphics.pushState();
		Color color = new Color(Display.getCurrent(), rgbs[0]);
		graphics.setBackgroundColor(color);
		for (int i = 0; i < dottedCount; i++) {
			graphics.fillRectangle(beginX + (pad + width * i) * xRate, beginY
					+ (pad + height * i) * yRate, width, height);
			if (i != dottedCount - 1) {
				pad += paddings[i];
			}
		}
		graphics.popState();
		color.dispose();
	}

}
