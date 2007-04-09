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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;

/**
 * @author mengbo
 * @version 1.5
 */
public class CSSTableCaptionLayout extends CachedTableCellLayout {
	private CSSTableLayout2 _tableLayout;

	private TableCaptionInfo _caption;

	/**
	 * @param cssfigure
	 */
	public CSSTableCaptionLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#invalidate()
	 */
	public void invalidate() {
		super.invalidate();

		_tableLayout = null;
		_caption = null;
	}

	public Rectangle getCellRect() {
		int x = 0;

		int[] rowHeights = _tableLayout.getRowHeights();
		int vspacing = _tableLayout.getVSpacing();
		int y = vspacing;
		if (_caption != null && "bottom".equalsIgnoreCase(_caption.getAlign())) //$NON-NLS-1$
		{
			for (int row = 0; row < rowHeights.length; row++) {
				y += rowHeights[row];
				y += vspacing;
			}
		}

		int height = 0;
		height = _tableLayout.getCaptionSize().height;
		int width = _tableLayout.getCaptionSize().width;
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}

	/**
	 * the parent figure of TRGroup should be table figure. If so, return the
	 * corresponding table layout.
	 * 
	 * @return the css table layout context or null if none.
	 */ 
	public CSSTableLayout2 getTableLayoutContext() {
		IFigure parent = getCSSFigure().getParent();
		if (parent != null) {
			LayoutManager parentLayout = parent.getLayoutManager();
			if (parentLayout instanceof CSSTableLayout2) {
				return (CSSTableLayout2) parentLayout;
			}
		}
		return null;
	}

	/**
	 * @return true if the table has a caption
	 */
	public boolean initializeTableInfo() {
		_caption = null;
		_tableLayout = getTableLayoutContext();
		if (_tableLayout != null) {
			_caption = _tableLayout.getCaptionInfo();
			return _caption != null;
		}
		return false;
	}

	/**
	 * @return the table layout
	 */
	public CSSTableLayout2 getTableLayout() {
		return _tableLayout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#isCalculatingMaxWidth()
	 */
	public boolean isCalculatingMaxWidth() {
		return false;
	}
}
