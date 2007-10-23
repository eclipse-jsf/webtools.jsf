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
import org.eclipse.jst.pagedesigner.css2.layout.TextLayoutSupport;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
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
public class TextInputWidgetProvider extends AbstractWidgetProvider {
	private static final int VERTICAL_INCREMENT = 2;

	/**
	 * password size
	 */
	public static final int PWD_SIZE = 18;

	private int DEFAULTSIZE = 20;

	private int _size = DEFAULTSIZE;

	private String _value;

	/**
	 * @param style
	 */
	public TextInputWidgetProvider(ICSSStyle style) {
		this(style, 20);
	}

	/**
	 * @param style
	 * @param size
	 */
	public TextInputWidgetProvider(ICSSStyle style, int size) {
		super(style);
		DEFAULTSIZE = size;
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
	 * @return
	 */
	private int getDefaultHeight() {
		ICSSStyle style = this.getCSSStyle();
		if (style == null) {
			style = DefaultStyle.getInstance();
		}
		ICSSFont font = style.getCSSFont();
		Font swtfont = font.getSwtFont();
		int fontSize = FigureUtilities.getFontMetrics(swtfont).getHeight();
		return fontSize + VERTICAL_INCREMENT;
	}

	/**
	 * @return the default width
	 */
	public int getDefaultWidth() {
		ICSSStyle style = this.getCSSStyle();
		if (style == null) {
			style = DefaultStyle.getInstance();
		}
		return computeWidth(style.getCSSFont());
	}

	private int computeWidth(ICSSFont font) {
		int fontWidth = FigureUtilities
				.getTextWidth("abcde", font.getSwtFont());//$NON-NLS-1$
		return (fontWidth + 1) * _size / 5;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		String value = getValue();
		if (value != null) {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			Object textAlign = style
					.getStyleProperty(ICSSPropertyID.ATTR_TEXTALIGN);

			Font font = style.getCSSFont().getSwtFont();
			g.setFont(font);

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
			int width = FigureUtilities.getTextWidth(value, g.getFont());
			int x = TextLayoutSupport.getBeginX(textAlign, rect, width);
			int y = rect.y + VERTICAL_INCREMENT / 2;

			g.drawString(value, x, y);
			TextLayoutSupport.paintTextDecoration(g, new Rectangle(x, y, width,
					g.getFontMetrics().getHeight()), ((Integer) getCSSStyle()
					.getStyleProperty(ICSSPropertyID.ATTR_TEXTDECORATION))
					.intValue());
			if (newColor != null) {
				newColor.dispose();
			}
		}
	}

	/**
	 * set the value in this text input control
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this._value = value;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this._size = (size <= 0 ? DEFAULTSIZE : size);
	}

	/**
	 * get current value in this text input control
	 * 
	 * @return the value
	 */
	public String getValue() {
		return _value;
	}
}
