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
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.border.CSSBorder;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.MessageFormater;

/**
 * @author mengbo
 */
public class InputFileWidgetProvider extends AbstractWidgetProvider {
	private static final String BODER_QUERY_TEMPLETE = "border-{0}-style"; //$NON-NLS-1$

	private static final int GAP = 2;

	private ICSSWidgetProvider _sub1;

	private ICSSWidgetProvider _sub2;

	/**
	 * @param style
	 * @param sub1 
	 * @param sub2 
	 */
	public InputFileWidgetProvider(ICSSStyle style, ICSSWidgetProvider sub1,
			ICSSWidgetProvider sub2) {
		super(style);
		_sub1 = sub1;
		_sub2 = sub2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#getPreferredDimension(int,
	 *      int)
	 */
	public DimensionInfo getPreferredDimension(int width, int height) {
		Insets borderInset = new Insets();
		Insets borderPaddingInset = new Insets();
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			borderInset = style.getBorderInsets();
			borderPaddingInset = borderInset.getAdded(style.getPaddingInsets());
		}

		Dimension d1 = _sub1.getPreferredDimension(-1, -1).getDimension();
		Dimension d2 = _sub2.getPreferredDimension(-1, -1).getDimension();

		int minWidth = d2.width + 2 * (borderInset.left + borderInset.right)
				+ GAP;
		int prefWidth = d1.width + d2.width + 2
				* (borderPaddingInset.left + borderPaddingInset.right) + GAP;
		int minHeight = borderInset.top + borderInset.bottom;
		int prefHeight = Math.max(d1.height, d2.height)
				+ borderPaddingInset.top + borderPaddingInset.bottom;

		if (width > 0 && minWidth > width) {
			prefWidth = minWidth;
		} else if (width != 0) {
			prefWidth = width;
		}

		if (height > 0 && minHeight > height) {
			prefHeight = minHeight;
		} else if (height != 0) {
			prefHeight = height;
		}
		return new DimensionInfo(prefWidth, prefHeight, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		Rectangle rect1, rect2;

		Insets borderInset = new Insets();
		Insets paddingInset = new Insets();
		Insets borderPaddingInset = new Insets();
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			borderInset = style.getBorderInsets();
			paddingInset = style.getPaddingInsets();
			borderPaddingInset.add(borderInset).add(paddingInset);
		}

		Dimension d1 = _sub1.getPreferredDimension(-1, -1).getDimension();
		Dimension d2 = _sub2.getPreferredDimension(-1, -1).getDimension();
		int prefWidth = d1.width + d2.width + 2 * borderPaddingInset.left + 2
				* borderPaddingInset.right + GAP;
		if (rect.width < prefWidth) {
			paddingInset.left = 0;
			paddingInset.right = 0;
		}
		int prefHeight = Math.max(d1.height, d2.height)
				+ borderPaddingInset.top + borderPaddingInset.bottom;
		if (rect.height < prefHeight) {
			paddingInset.top = 0;
			paddingInset.bottom = 0;
		}

		int width = rect.width
				- d2.width
				- (borderInset.left + borderInset.right + paddingInset.left + paddingInset.right)
				- GAP;
		rect1 = new Rectangle(rect.x - 1, rect.y, width, rect.height);
		rect2 = new Rectangle(rect.x + rect1.width + 2, rect.y, rect.width
				- rect1.width - 3, rect.height);
		Rectangle innerRect1 = rect1.getCopy().crop(borderInset);
		Rectangle innerRect2 = rect2.getCopy().crop(borderInset);

		g.pushState();
		g.setBackgroundColor(ColorConstants.button);
		g.fillRectangle(innerRect2);
		g.popState();

		CSSBorder cssBorder = new CSSBorder(style);
		String[] edges = new String[] { ICSSStyle.LEFT, ICSSStyle.RIGHT,
				ICSSStyle.TOP, ICSSStyle.BOTTOM };
		for (int i = 0; i < edges.length; i++) {
			cssBorder.paintEdge(g, rect1, innerRect1, edges[i],
					getBorderStyle(edges[i]));
		}
		for (int i = 0; i < edges.length; i++) {
			String borderStyle = getBorderStyle(edges[i]);
			if (ICSSPropertyID.VAL_INSET.equals(borderStyle)) {
				borderStyle = ICSSPropertyID.VAL_OUTSET;
			}
			cssBorder.paintEdge(g, rect2, innerRect2, edges[i], borderStyle);
		}

		_sub2.paintFigure(g, innerRect2.getCopy().crop(paddingInset));
		// _sub1.paintFigure(g, innerRect1.crop(paddingInset));
	}

	private String getBorderStyle(String edge) {
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			String property = MessageFormater
					.format(BODER_QUERY_TEMPLETE, edge);
			String borderStyle = style.getStyleProperty(property).toString();
			return borderStyle;
		}
		return ICSSPropertyID.VAL_HIDDEN;
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
