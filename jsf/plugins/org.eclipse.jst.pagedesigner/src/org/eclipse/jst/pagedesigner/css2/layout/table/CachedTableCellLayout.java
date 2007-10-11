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
package org.eclipse.jst.pagedesigner.css2.layout.table;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.layout.BoxUtil;
import org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowFigure;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class CachedTableCellLayout extends CSSBlockFlowLayout 
{
	private Dimension _pageSize = new Dimension();

	private boolean _calculatingSize = false;

	private int _pageSizeCacheKeys[] = new int[4];

	private Dimension _pageSizeCacheValues[] = new Dimension[4];

	private int _recommendedWidth;

	private Dimension _cacheMaxWidthSize = null;

	private boolean _isTable;

	/**
	 * @param cssfigure
	 */
	public CachedTableCellLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/**
	 * when figure revalidated, means some child or itself get changed somehow,
	 * so clear the cache information here.
	 */
	public void figureRevalidate() {
		super.figureRevalidate();
		_pageSizeCacheKeys = new int[4];
		_pageSizeCacheValues = new Dimension[4];
		_pageSize = new Dimension();
		_recommendedWidth = 0;
		_cacheMaxWidthSize = null;
		_isTable = false;
	}

	/**
	 * TODO: This method is not being called.
	 */
	public void postValidate() {
		if (_isTable) {
			if (_calculatingSize) {
				_pageSize.width = _blockBox.getWidth();
				_pageSize.height = _blockBox.getHeight();
			} else {
				if (_isTable) {
					Rectangle rect = getCellRect();
					_blockBox.setXYWidthHeight(rect);
					this.getCSSFigure().setBounds(rect);
				}
			}
			List list = getCSSFigure().getChildren();
			for (int i = 0, n = list.size(); i < n; i++) {
				((FlowFigure) list.get(i)).postValidate();
			}
		} else {
			super.postValidate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#preLayout()
	 */
	protected void preLayout() {
		_isTable = initializeTableInfo();
		if (_isTable) {
			if (!_calculatingSize) {
				// XXX: I don't know why need to call setValid(false) here, if I
				// don't call
				// it, the layout will be wrong.
				getCSSFigure().setValid(false);
			}
		}
		super.preLayout();
	}

	/**
	 * @return the cell rectangle
	 */
	protected abstract Rectangle getCellRect();

	/**
	 * @return true if initialized
	 */
	protected abstract boolean initializeTableInfo();

	protected void setupBlock() {
		if (_isTable) {
			// Remove all current Fragments
			_blockBox.clear();

			if (_calculatingSize) {
				// we are not in the real layout
				// Setup the one fragment for this Block with the correct X and
				// available width
				int recommendedWidth = getRecommendedWidth();
				_blockBox.setRecommendedWidth(recommendedWidth);

				if (recommendedWidth > 0
						&& recommendedWidth != Integer.MAX_VALUE) {
					_blockBox.setWidth(recommendedWidth);
				}
			} else {
				Rectangle rect = getCellRect();
				_blockBox.setWidth(rect.width);
				_blockBox.setRecommendedWidth(rect.width);
				_blockBox.setHeight(rect.height);
				_blockBox.setRecommendedHeight(rect.height);
			}

			BoxUtil.setupBorderPaddingMargin(_blockBox, getCSSStyle());
		} else {
			super.setupBlock();
		}
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(IFigure container, int width, int h) {
		if (this.isCalculatingMaxWidth()) {
			return getMaxContentWidthSize(container, width, h);
		}
		try {
			_calculatingSize = true;
			// if (width >= 0)
			// {
			// width = Math.max(0, width - container.getInsets().getWidth());
			// }

			for (int i = 0; i < 4; i++) {
				if (_pageSizeCacheKeys[i] == width
						&& _pageSizeCacheValues[i] != null) {
					if (h > _pageSizeCacheValues[i].height) {
						return new Dimension(_pageSizeCacheValues[i].width, h);
					}
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
			((CSSFigure) container).setValid(false);
			container.validate();
			((CSSFigure) container).postValidate();
			_pageSizeCacheValues[0] = new Dimension(_pageSize);

			if (width != oldWidth) {
				setRecommendedWidth(oldWidth);
				// container.getUpdateManager().addInvalidFigure(container);
			}
			if (h > _pageSizeCacheValues[0].height) {
				return new Dimension(_pageSizeCacheValues[0].width, h);
			}
            return _pageSizeCacheValues[0];
		} finally {
			_calculatingSize = false;
		}
	}

	private int getRecommendedWidth() {
		return _recommendedWidth;
	}

	private void setRecommendedWidth(int width) {
		if (_recommendedWidth == width) {
			return;
		}
		_recommendedWidth = width;
	}

	/**
	 * @param container
	 * @param width
	 * @param height
	 * @return max width size
	 */
	public Dimension getMaxContentWidthSize(IFigure container, int width,
			int height) {
		try {
			_calculatingSize = true;

			if (this._cacheMaxWidthSize == null) {
				boolean b = getCalcuatingMaxWidth();
				setCalculatingMaxWidth(true);

				// Flowpage must temporarily layout to determine its preferred
				// size
				int oldWidth = getRecommendedWidth();
				if (width <= 0) {
					setRecommendedWidth(Integer.MAX_VALUE);
				} else {
					setRecommendedWidth(width);
				}
				((CSSFigure) container).setValid(false);
				container.validate();

				((CSSFigure) container).postValidate();
				_cacheMaxWidthSize = new Dimension(_pageSize);
				if (height > _pageSize.height) {
					_cacheMaxWidthSize.height = height;
				}

				if (0 != oldWidth) {
					setRecommendedWidth(oldWidth);
					// container.getUpdateManager().addInvalidFigure(container);
				}

				setCalculatingMaxWidth(b);
			}
			return _cacheMaxWidthSize;
		} finally {
			_calculatingSize = false;
		}
	}

	/**
	 * @return Returns the _isTable.
	 */
	protected boolean isTable() {
		return _isTable;
	}
}
