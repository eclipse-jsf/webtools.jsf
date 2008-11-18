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

import java.util.StringTokenizer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.layout.TextLayoutSupport;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * For ButtonWidget, it displays some text value in it. And lays the text with
 * nowrap.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ButtonWidgetProvider extends AbstractWidgetProvider {
	// The button width should include the label length and padding,
	// to let the button looks fine, we set the padding 0.46 width as the label
	// length.
	private final static double HORIZONTAL_RATE = 1.46;

	// The button height should include the label height and padding,
	// to let the button looks fine, we set the padding 0.36 height as the
	// character height.
	private final static double VERTICAL_PADDING_RATE = 0.36;

	private static final String[] DEFAULTLINES = new String[] { "  " }; //$NON-NLS-1$

	private String _value = ""; //$NON-NLS-1$

	private String[] _lines = DEFAULTLINES;

	/**
	 * @param style
	 */
	public ButtonWidgetProvider(ICSSStyle style) {
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
	 * @return the default width value
	 */
	public int getDefaultWidth() {
		int longestStringWidth = 0;
		if (_lines != null) {
			ICSSStyle style = this.getCSSStyle();
			if (style == null) {
				style = DefaultStyle.getInstance();
			}
			ICSSFont font = style.getCSSFont();
			Font swtFont = font.getSwtFont();
			for (int i = 0; i < _lines.length; i++) {
				int width = FigureUtilities.getTextWidth(_lines[i], swtFont);
				if (width > longestStringWidth) {
					longestStringWidth = width;
				}
			}
		}
		// text area width + padding
		return (int) ((longestStringWidth) * HORIZONTAL_RATE);
	}

	/**
	 * 
	 * @return the default height value
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
		return (int) ((fontHeight) * (_lines.length + VERTICAL_PADDING_RATE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		ICSSStyle style = this.getCSSStyle();
		if (style == null) {
			style = DefaultStyle.getInstance();
		}
		Object textAlign = style
				.getStyleProperty(ICSSPropertyID.ATTR_TEXTALIGN);
		if (ICSSPropertyMeta.NOT_SPECIFIED == textAlign) {
			textAlign = "center"; //$NON-NLS-1$
		}
		Font font = style.getCSSFont().getSwtFont();
		g.setFont(font);
		int fontHeight = FigureUtilities.getFontMetrics(font).getHeight();

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

		int y = rect.y + (int) (fontHeight * VERTICAL_PADDING_RATE / 2);
		if (rect.height > fontHeight * _lines.length) {
			y = rect.y + (rect.height - fontHeight * _lines.length) / 2;
		}

		for (int i = 0; i < _lines.length && y < rect.y + rect.height; i++) {
			int width = FigureUtilities.getTextWidth(_lines[i], font);
			int x = TextLayoutSupport.getBeginX(textAlign, rect, width);
			g.drawString(_lines[i], x, y);
			TextLayoutSupport.paintTextDecoration(g, new Rectangle(x, y, width,
					fontHeight), ((Integer) getCSSStyle().getStyleProperty(
					ICSSPropertyID.ATTR_TEXTDECORATION)).intValue());
			y += fontHeight;
		}

		if (newColor != null) {
			newColor.dispose();
		}
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		if (value == null) {
			value = ""; //$NON-NLS-1$
		}
		_value = value;
		_lines = splitValue(_value);
	}

	/**
	 * Split the value to multiple lines.
	 * 
	 * @param _value2
	 * @return
	 */
	private String[] splitValue(String _value2) {
		if (_value2 == null || "".equals(_value2)) { //$NON-NLS-1$
			return DEFAULTLINES;
		}

		StringTokenizer tokenizer = new StringTokenizer(_value2, "\r\n"); //$NON-NLS-1$
		String[] result = new String[tokenizer.countTokens()];
		for (int i = 0; i < result.length; i++) {
			result[i] = tokenizer.nextToken().replaceAll("\t", "    "); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}
}
