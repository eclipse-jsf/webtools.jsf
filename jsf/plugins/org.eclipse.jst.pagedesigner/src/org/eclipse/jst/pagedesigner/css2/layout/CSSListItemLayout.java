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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.list.CounterHelper;
import org.eclipse.jst.pagedesigner.css2.list.ICounterValueGenerator;
import org.eclipse.jst.pagedesigner.css2.marker.CounterUtil;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 */
public class CSSListItemLayout extends CSSBlockFlowLayout implements
		ICSSPainter {
	private static final String DEFAULT_LIST_COUNTER = "_anonymous"; //$NON-NLS-1$

	private static final int CIRCLE_DIAMETER = 6;

	private static final int DISC_DIAMETER = 5;

	private static final int ROUNDRECT_ARC = 2;

	private static final int TEXT_PADDING = 16;

	private int _count;

	/**
	 * @param cssfigure
	 */
	public CSSListItemLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSPainter#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		ICSSStyle style = this.getCSSStyle();
		if (style == null) {
			style = DefaultStyle.getInstance();
		}

		Rectangle drawArea = null;
		Font font = getCSSStyle().getCSSFont().getSwtFont();

		// draw the marker box
		Object styleType = style
				.getStyleProperty(ICSSPropertyID.ATTR_LIST_STYLE_TYPE);

		g.pushState();

		Color newColor = null;
		Object color = style.getColor();
		if (color instanceof Color) {
			g.setForegroundColor((Color) color);
			g.setBackgroundColor((Color) color);
		} else if (color instanceof RGB) {
			newColor = new Color(Display.getCurrent(), (RGB) color);
			g.setForegroundColor(newColor);
			g.setBackgroundColor(newColor);
		}

		if (styleType instanceof String) {
			int type = CounterHelper.toTypeInt((String) styleType);
			switch (type) {
			case CounterHelper.LIST_T_UPPER_ALPHA:
			case CounterHelper.LIST_T_LOWER_ALPHA:
			case CounterHelper.LIST_T_LOWER_ROMAN:
			case CounterHelper.LIST_T_UPPER_ROMAN:
			case CounterHelper.LIST_T_DECIMAL:
				g.setFont(font);
				String displayString = CounterUtil.convertCount(_count, type);
				Point point = getDrawPointForText(displayString);
				g.drawString(displayString, point);
				break;
			case CounterHelper.LIST_T_CIRCLE:
				drawArea = getDrawAreaForGraph(CIRCLE_DIAMETER, CIRCLE_DIAMETER);
				g.drawArc(drawArea, 0, 360);
				break;
			case CounterHelper.LIST_T_SQUARE:
				drawArea = getDrawAreaForGraph(DISC_DIAMETER, DISC_DIAMETER);
				g.fillRectangle(drawArea);
			case CounterHelper.LIST_T_DECIMAL_LEADING_ZERO:
			case CounterHelper.LIST_T_LOWER_GREEK:
			case CounterHelper.LIST_T_ARMENIAN:
			case CounterHelper.LIST_T_GEORGIAN:
			case CounterHelper.LIST_T_IMAGE:
			case CounterHelper.LIST_T_NONE:
			default:
				drawArea = getDrawAreaForGraph(DISC_DIAMETER, DISC_DIAMETER);
				g.fillRoundRectangle(drawArea, ROUNDRECT_ARC, ROUNDRECT_ARC);
				break;
			}
		}
		g.popState();

		if (newColor != null) {
			newColor.dispose();
		}
	}

	/**
	 * @param g
	 * @return
	 */
	private Rectangle getDrawAreaForGraph(int width, int height) {
		Rectangle drawArea;

		int x = 0;
		int y = 0;

		List list = _blockBox.getFragments();
		Rectangle box = _blockBox.toRectangle().getCopy().expand(
				_blockBox.getBorderPaddingInsets().getAdded(
						_blockBox.getMarginInsets()));
		if (list != null && !list.isEmpty()) {
			LineBox line = (LineBox) list.get(0);
			y = line.getBaseline() - CIRCLE_DIAMETER;
			x = box.x;
		} else {
			x = box.x;
			y = box.height / 2 - CIRCLE_DIAMETER;
		}
		drawArea = new Rectangle(x - CIRCLE_DIAMETER * 5 / 2, y, width, height);
		return drawArea;
	}

	private Point getDrawPointForText(String displayString) {
		Font font = getCSSStyle().getCSSFont().getSwtFont();

		int x = 0;
		int y = 0;

		Rectangle box = _blockBox.toRectangle().getCopy().expand(
				_blockBox.getBorderPaddingInsets().getAdded(
						_blockBox.getMarginInsets()));

		x = box.x - FigureUtilities.getTextWidth(displayString, font);
		x = x
				- (TEXT_PADDING - FigureUtilities.getFontMetrics(font)
						.getDescent());

		return new Point(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContainerLayout#layoutChildren()
	 */
	protected void layoutChildren() {
		ICounterValueGenerator counter = this.getCSSStyle().findCounter(
				DEFAULT_LIST_COUNTER, true);
		if (counter != null) {
			_count = counter.getCurrentCount();
		} else {
			// should not happen.
			_count = 1; // use 1 as the default value
		}
		super.layoutChildren();
	}
}
