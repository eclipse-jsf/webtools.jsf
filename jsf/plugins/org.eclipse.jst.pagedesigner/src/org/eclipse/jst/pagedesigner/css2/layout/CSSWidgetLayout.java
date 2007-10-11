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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;

/**
 * @author mengbo
 */
public class CSSWidgetLayout extends CSSBlockFlowLayout implements ICSSPainter {
	private WidgetBox _widgetBox;

	private ICSSWidgetProvider _provider;

	/**
	 * @param flowfigure
	 * @param provider 
	 */
	public CSSWidgetLayout(CSSFigure flowfigure, ICSSWidgetProvider provider) {
		super(flowfigure);
		_provider = provider;
	}

	/**
	 * normally this method is called directly after constructor
	 * 
	 * @param provider
	 */
	public void setProvider(ICSSWidgetProvider provider) {
		_provider = provider;
	}

	/**
	 * @return the provider
	 */
	public ICSSWidgetProvider getProvider() {
		// return ((CSSWidgetFigure)this.getFlowFigure()).getProvider();
		return _provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#isInlineBlock()
	 */
	public boolean isInlineBlock() {
		ICSSWidgetProvider provider = getProvider();
		return provider.isInline();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#layout()
	 */
	protected void layoutChildren() {
		ICSSWidgetProvider provider = getProvider();

		// if we did endLine, then will result in context create a new line, so
		// we may in the new line now.
		// passing in the top margin, and context will consider that when
		// creating the new line.

		int suggestedWith = _blockBox.getContentWidth();
		int suggestedHeight = _blockBox.getContentHeight();
		// int suggestedWith = getSuggestedWidth(line, style, provider);
		// int suggestedHeight = getSuggestedHeight(line, style, provider);

		DimensionInfo resultInfo = provider.getPreferredDimension(
				suggestedWith, suggestedHeight);
		Dimension resultSize = resultInfo.getDimension();

		_widgetBox = new WidgetBox(); // ((CSSWidgetFigure)getFlowFigure()).getWidgetBox();
		// if (provider.isHandlingBorder() || style == null)
		// {
		_widgetBox.setWidth(resultSize.width);
		_widgetBox.setHeight(resultSize.height);
		_widgetBox.setAscent(resultInfo.getAscent());
		// }
		// else
		// {
		// widgetBox.setWidth(resultSize.width +
		// style.getBorderInsets().getWidth());
		// widgetBox.setHeight(resultSize.height +
		// style.getBorderInsets().getHeight());
		// widgetBox.setAscent(resultInfo.getAscent()+style.getBorderInsets().top);
		// }
		this.addToCurrentLine(_widgetBox);
		// if (!provider.isInline())
		// {
		// context.endLine();
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#dispose()
	 */
	public void dispose() {
        // TODO: anything to dispose?
	}

	// public int getSuggestedWidth(LineBox line, ICSSStyle style,
	// ICSSWidgetProvider provider)
	// {
	// if (style == null) return -1;
	//
	// Object width = style.getStyleProperty(ICSSPropertyID.ATTR_WIDTH);
	// Length recommendedWidth = (width instanceof Length) ? (Length) width :
	// null;
	//
	// int rw = 0;
	// if (recommendedWidth == null || recommendedWidth.getValue() <= 0)
	// {
	// return -1;
	// }
	// else
	// {
	// if (recommendedWidth.isPercentage())
	// {
	// rw = line.getAvailableWidth() * recommendedWidth.getValue() / 100;
	// }
	// else
	// {
	// rw = recommendedWidth.getValue();
	// }
	//
	// if (!style.isSizeIncludeBorderPadding() && provider.isHandlingBorder())
	// {
	// rw += style.getBorderInsets().getWidth() +
	// style.getPaddingInsets().getWidth();
	// }
	// else if (style.isSizeIncludeBorderPadding() &&
	// !provider.isHandlingBorder())
	// {
	// rw -= style.getBorderInsets().getWidth() +
	// style.getPaddingInsets().getWidth();
	// }
	// }
	//
	// return rw;
	// }
	//
	// public int getSuggestedHeight(LineBox line, ICSSStyle style,
	// ICSSWidgetProvider provider)
	// {
	// if (style == null) return -1;
	//
	// Object height = style.getStyleProperty(ICSSPropertyID.ATTR_HEIGHT);
	// Length recommendedHeight = (height instanceof Length) ? (Length) height :
	// null;
	//
	// int rh = 0;
	// if (recommendedHeight == null || recommendedHeight.getValue() <= 0)
	// {
	// return -1;
	// }
	// else
	// {
	// if (recommendedHeight.isPercentage())
	// {
	// // we don't support percentage height for this version, ignore
	// return -1;
	// }
	// else
	// {
	// rh = recommendedHeight.getValue();
	// }
	//
	// if (!style.isSizeIncludeBorderPadding() && provider.isHandlingBorder())
	// {
	// rh += style.getBorderInsets().getHeight() +
	// style.getPaddingInsets().getHeight();
	// }
	// else if (style.isSizeIncludeBorderPadding() &&
	// !provider.isHandlingBorder())
	// {
	// rh -= style.getBorderInsets().getHeight() +
	// style.getPaddingInsets().getHeight();
	// }
	// }
	//
	// return rh;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSPainter#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		ICSSWidgetProvider provider = this.getProvider();
		if (provider != null && _widgetBox != null) {
			provider.paintFigure(g, new Rectangle(_widgetBox._x, _widgetBox._y,
					_widgetBox.getWidth(), _widgetBox.getHeight()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#handlingBorderForBlock()
	 */
	public boolean handlingBorderForBlock() {
		ICSSWidgetProvider provider = this.getProvider();
		if (provider != null) {
			return provider.isHandlingBorder();
		}
		return super.handlingBorderForBlock();
	}
}
