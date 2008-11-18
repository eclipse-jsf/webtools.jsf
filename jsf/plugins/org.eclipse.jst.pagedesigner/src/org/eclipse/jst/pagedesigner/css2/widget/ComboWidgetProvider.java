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
import org.eclipse.draw2d.geometry.Dimension;
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
public class ComboWidgetProvider extends AbstractWidgetProvider {
	private static final int VERTICAL_PADDING = 6;

	private static final int HORIZONTAL_PADDING = 12;

	private static int ARRAWWIDTH = 16;

	//private static int ARROWHEIGHT = 16;

	private String _firstString;

	private String _longestString;

	private String _label;

	/**
	 * @param style
	 */
	public ComboWidgetProvider(ICSSStyle style) {
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
		return new DimensionInfo(new Dimension(width, height), -1);
	}

	/**
	 * by default, the combo's width will be calculated from the longest option
	 * value.
	 * 
	 * @return the default width
	 */
	private int getDefaultWidth() {
		int textareaWidth;
		if (this._longestString == null || this._longestString.length() == 0) {
			textareaWidth = 20;
		} else {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			ICSSFont font = style.getCSSFont();

			textareaWidth = FigureUtilities.getTextWidth(_longestString, font
					.getSwtFont());
		}
		return textareaWidth + ARRAWWIDTH + HORIZONTAL_PADDING;
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
		int fontSize = FigureUtilities.getFontMetrics(swtfont).getHeight();
		return fontSize + VERTICAL_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		if (this._firstString != null) {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			ICSSFont font = style.getCSSFont();
			g.setFont(font.getSwtFont());

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
			g.clipRect(rect);
			String label = _label != null ? _label : _firstString;
			label = label.replaceAll("[ \r\n]+", " "); //$NON-NLS-1$ //$NON-NLS-2$
			if (label.endsWith(" ")) { //$NON-NLS-1$
				label = label.substring(0, label.length() - 1);
			}
			g.drawString(label, rect.x + HORIZONTAL_PADDING / 2, rect.y
					+ VERTICAL_PADDING / 2);
			if (newColor != null) {
				newColor.dispose();
			}

		}

		BorderUtil.drawBorder(g, rect.x, rect.y, rect.width, rect.height,
				BORDERTHICK, true);
		// next the drop down button
		int width = ARRAWWIDTH;
		int left = rect.x + rect.width - width - BORDERTHICK;
		int top = rect.y + BORDERTHICK;
		int height = rect.height - BORDERTHICK * 2;
		g.setBackgroundColor(ColorConstants.button);
		g.fillRectangle(left, top, width, height);

		Rectangle borderRect = new Rectangle(left, top, width, height);
		BorderUtil.drawBorder(g, borderRect.x, borderRect.y, borderRect.width,
				borderRect.height, BORDERTHICK, false);

		g.setForegroundColor(ColorConstants.black);

		int decoratorWidth = (width - BORDERTHICK * 2) / 2;
		int length = decoratorWidth / 2 + 1;
		int leftX = left + (width - decoratorWidth) / 2 - 1;
		int topY = top + (height - length) / 2 + 1;
		for (int i = 0; i < length; i++) {
			g.drawLine(leftX + i, topY + i, leftX - i + decoratorWidth, topY
					+ i);
		}
	}

	/**
	 * set the options to be displayed in this combo box. Will calculate out the
	 * first string and the longest string.
	 * 
	 * @param options
	 */
	public void setOptions(String[] options) {
		if (options == null || options.length == 0) {
			this._firstString = null;
			this._longestString = null;
		} else {
			this._firstString = options[0];
			this._longestString = (options[0] == null ? "" : options[0]); //$NON-NLS-1$
			for (int i = 1; i < options.length; i++) {
				if (options[i] == null) {
					continue;
				}
				if (options[i].length() > this._longestString.length()) {
					this._longestString = options[i];
				}
			}
		}
	}

	/**
	 * @param label
	 */
	public void setSelectedLabel(String label) {
		this._label = label;
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
