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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 * @version 1.5
 */
public class ListWidgetProvider extends AbstractWidgetProvider {
	private static int DEFAULTSIZE = 4;

	private static final int VERTICAL_PADDING = 6;

	private static final int HORIZONTAL_PADDING = 12;

	private static int ARRAWWIDTH = 16;

	private static int ARROWHEIGHT = 16;

	private String[] _options;

	private int _rows = DEFAULTSIZE;

	/**
	 * @param style
	 */
	public ListWidgetProvider(ICSSStyle style) {
		super(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#getPreferredDimension(int,
	 *      int)
	 */
	public DimensionInfo getPreferredDimension(int width, int height) {
		if (width <= 0) {
			width = getDefaultWidth();
		}
		if (height <= 0) {
			height = getDefaultHeight();
		}
		return new DimensionInfo(width, height, -1);
	}

	/**
	 * by default, the combo's width will be calculated from the longest option
	 * value.
	 * 
	 * @return the default width
	 */
	public int getDefaultWidth() {
		int longestStringWidth = 0;
		if (_options != null) {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			ICSSFont font = style.getCSSFont();
			Font swtFont = font.getSwtFont();
			for (int i = 0; i < _options.length; i++) {
				int width = FigureUtilities.getTextWidth(_options[i], swtFont);
				if (width > longestStringWidth) {
					longestStringWidth = width;
				}
			}
		}
		// text area width + borderWidth + vertical scroll width
		return (longestStringWidth) + HORIZONTAL_PADDING + ARRAWWIDTH;
	}

	/**
	 * 
	 * @return the default height
	 * @see TextInputWidgetProvider#getDefaultHeight()
	 */
	public int getDefaultHeight() {
		ICSSStyle style = this.getCSSStyle();
		if (style == null) {
			style = DefaultStyle.getInstance();
		}
		ICSSFont font = style.getCSSFont();
		Font swtfont = font.getSwtFont();
		int fontHeight = FigureUtilities.getFontMetrics(swtfont).getHeight();
		return (fontHeight) * _rows + VERTICAL_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		BorderUtil.drawBorder(g, rect.x, rect.y, rect.width, rect.height,
				BORDERTHICK, true);
		if (_options != null) {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			ICSSFont font = style.getCSSFont();
			Font swtfont = font.getSwtFont();
			g.setFont(swtfont);

			Color newColor = null;
			Object color = style.getColor();
			if (color instanceof Color) {
				g.setForegroundColor((Color) color);
			} else if (color instanceof RGB) {
				newColor = new Color(Display.getCurrent(), (RGB) color);
				g.setForegroundColor(newColor);
			} else {
				g.setForegroundColor(ColorConstants.black);
			}
			int fontHeight = FigureUtilities.getFontMetrics(swtfont)
					.getHeight();
			int x = rect.x + HORIZONTAL_PADDING / 2;
			int y = rect.y + VERTICAL_PADDING / 2;

			g.clipRect(rect);
			for (int i = 0; i < _options.length
					&& (i * fontHeight < rect.height - VERTICAL_PADDING); i++) {
				g.drawString(_options[i], x, y);
				y += fontHeight;
			}
			if (newColor != null) {
				newColor.dispose();
			}

			int borderThick = 2;
			Rectangle barRect = new Rectangle(rect.x, rect.y + borderThick,
					rect.width - borderThick, rect.height - 2 * borderThick);
			BorderUtil.drawVertialBar(g, ARRAWWIDTH, ARROWHEIGHT, borderThick,
					barRect);
		}
	}

	/**
	 * @param string
	 * @param x
	 * @param y
	 * @return
	 */
	private String normalize(String string) {
		if (string == null) {
			return ""; //$NON-NLS-1$
		}
		int index = string.indexOf('\r');
		if (index >= 0) {
			string = string.substring(0, index);
		}
		index = string.indexOf('\n');
		if (index >= 0) {
			string = string.substring(0, index);
		}
		return string;
	}

	/**
	 * set the options to be displayed in this combo box.
	 * 
	 * @param options
	 */
	public void setOptions(String[] options) {
		this._options = options;
		if (_options != null) {
			for (int i = 0; i < _options.length; i++) {
				_options[i] = normalize(_options[i]);
			}
		}
	}

	/**
	 * set the default number of rows to be displayed.
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		_rows = (rows > 0 ? rows : DEFAULTSIZE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#isHandlingBorder()
	 */
	public boolean isHandlingBorder() {
		return false;
	}
}
