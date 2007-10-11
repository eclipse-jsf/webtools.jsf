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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This layout if for those thigns that it's parent will decide its size. Such
 * as table cell.
 * 
 * @author mengbo
 * @version 1.5
 */
public class CSSPageFlowLayout extends CSSBlockFlowLayout {
	private Dimension _pageSize = new Dimension();

	private int _recommendedWidth;

	private int _pageSizeCacheKeys[] = new int[4];

	private Dimension _pageSizeCacheValues[] = new Dimension[4];


	/**
	 * @param cssfigure
	 */
	public CSSPageFlowLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#invalidate()
	 */
	public void invalidate() {
		super.invalidate();
		_pageSizeCacheKeys = new int[4];
		_pageSizeCacheValues = new Dimension[4];
		_pageSize = new Dimension();
		_recommendedWidth = 0;
	}

	protected void endBlock() {
		layoutLines();
	}

	/**
	 * TODO: This method is not being called.
	 */
	public void postValidate() {
		Rectangle r = new Rectangle(_blockBox._x, _blockBox._y, _blockBox
				.getWidth(), _blockBox.getHeight());
		r = r.expand(getCSSFigure().getInsets());
		_pageSize.width = r.width;
		_pageSize.height = r.height;

		List list = getCSSFigure().getChildren();
		for (int i = 0; i < list.size(); i++) {
			((FlowFigure) list.get(i)).postValidate();
		}

	}

	/**
	 * Setup blockBox to the initial bounds of the Page
	 */
	protected void setupBlock() {
		// Remove all current Fragments
		_blockBox.clear();

		// Setup the one fragment for this Block with the correct X and
		// available width
		int recommendedWidth = getRecommendedWidth();
		_blockBox.setRecommendedWidth(recommendedWidth);

		if (recommendedWidth > 0 && recommendedWidth != Integer.MAX_VALUE) {
			_blockBox.setWidth(recommendedWidth);
		}

		_blockBox._x = 0;
	}

	private int getRecommendedWidth() {
		return _recommendedWidth;
	}

	private void setRecommendedWidth(int width) {
		if (_recommendedWidth == width) {
			return;
		}
		_recommendedWidth = width;
		getCSSFigure().invalidate2();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#setBoundsCalled(org.eclipse.jst.pagedesigner.css2.layout.CSSFigure,
	 *      org.eclipse.draw2d.geometry.Rectangle, boolean)
	 */
	public void setBoundsCalled(Rectangle r, boolean invalidate) {
		super.setBoundsCalled(r, invalidate);
		CSSFigure figure = getCSSFigure();
		int newWidth = r.width - figure.getInsets().getWidth();
		if (invalidate || getRecommendedWidth() != newWidth) {
			setRecommendedWidth(newWidth);
			figure.getUpdateManager().addInvalidFigure(figure);
		}
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(IFigure container, int width, int h) {
		if (width >= 0) {
			width = Math.max(0, width - container.getInsets().getWidth());
		}

		for (int i = 0; i < 4; i++) {
			if (_pageSizeCacheKeys[i] == width
					&& _pageSizeCacheValues[i] != null) {
				return _pageSizeCacheValues[i];
			}
		}

		_pageSizeCacheKeys[3] = _pageSizeCacheKeys[2];
		_pageSizeCacheKeys[2] = _pageSizeCacheKeys[1];
		_pageSizeCacheKeys[1] = _pageSizeCacheKeys[0];
		_pageSizeCacheKeys[0] = width;

		_pageSizeCacheValues[3] = _pageSizeCacheValues[2];
		_pageSizeCacheValues[2] = _pageSizeCacheValues[1];
		_pageSizeCacheValues[1] = _pageSizeCacheValues[0];

		// Flowpage must temporarily layout to determine its preferred size
		int oldWidth = getRecommendedWidth();
		setRecommendedWidth(width);
		container.validate();
		_pageSizeCacheValues[0] = _pageSize.getExpanded(container.getInsets()
				.getWidth(), container.getInsets().getHeight());

		if (width != oldWidth) {
			setRecommendedWidth(oldWidth);
			container.getUpdateManager().addInvalidFigure(container);
		}
		return _pageSizeCacheValues[0];
	}
}
