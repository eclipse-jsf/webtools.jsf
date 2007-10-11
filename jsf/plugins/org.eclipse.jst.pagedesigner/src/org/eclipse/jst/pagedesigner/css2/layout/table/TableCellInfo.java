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

import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.utils.IntFlexArray;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableCellInfo extends TableItemInfo {
	int _rowSpan = 1;

	int _colSpan = 1;

	int _colIndex;

	int _rowIndex;

	int _cellWidth = 0;

	int _cellHeight = 0;

	/**
	 * @param childfigure
	 */
	public TableCellInfo(ICSSFigure childfigure) {
		super(childfigure);
	}

	/**
	 * @return column span
	 */
	public int getColSpan() {
		return _colSpan;
	}

	/**
	 * @return row span
	 */
	public int getRowSpan() {
		return _rowSpan;
	}

	/**
	 * @return the row index
	 */
	public int getRowIndex() {
		return _rowIndex;
	}

	/**
	 * @return the column index
	 */
	public int getColumnIndex() {
		return _colIndex;
	}

	/**
	 * @return the minimum cell dimensions
	 */
	public Dimension getMinCWDimension() {
		return getFigure().getPreferredSize(_cellWidth, _cellHeight);
	}

	/**
	 * @return the max cell dimensions
	 */
	public Dimension getMaxCWDimension() {
		ICSSFigure figure = getFigure();
		LayoutManager layout = figure.getLayoutManager();
		if (layout instanceof CSSTableCellLayout) {
			Dimension d = ((CSSTableCellLayout) layout).getMaxContentWidthSize(
					figure, _cellWidth, _cellHeight);
			return d;
		}

        // should not happen
        return getMinCWDimension();
	}

	/**
	 * @param context
	 */
	public void calculateCellInfo(TableInfoContext context) {
		ICSSStyle style = this.getStyle();
		_rowSpan = style.getRowSpan();
		_colSpan = style.getColSpan();

		// FIXME: we don't support rowspan and colspan to be 0.
		// by spec, 0 means span from current col/row to end.
		if (_rowSpan <= 0) {
			_rowSpan = 1;
		}
		if (_colSpan <= 0) {
			_colSpan = 1;
		}

		_rowIndex = context.getCurrentRow();

		IntFlexArray array = context.getIntFlexArray();
		int currentCol = context.getCurrentCol();

		// find a cell that is not occupied by cells in previous rows.
		while (array.getAt(currentCol) > 0) {
			currentCol++;
		}

		// ok, now array.getAt(currentCol) == 0
		_colIndex = currentCol;

		for (int i = 0; i < _colSpan; i++, currentCol++) {
			array.setAt(currentCol, _rowSpan);
		}
		context.setCurrentCol(currentCol);
	}

	/**
	 * @param tableInfo 
	 * @param tablewidth
	 *            table width
	 */
	public void calculateWidth(TableInfo tableInfo, int tablewidth) {
		ICSSStyle style = this.getFigure().getCSSStyle();
		if (style == null) {
			_cellWidth = -1;
		} else {
			Object width = style.getStyleProperty(ICSSPropertyID.ATTR_WIDTH);
			Length recommendedWidth = (width instanceof Length) ? (Length) width
					: null;

			int rw = 0;
			if (recommendedWidth == null || recommendedWidth.getValue() <= 0) {
				rw = 0;
			} else {
				if (recommendedWidth.isPercentage()) {
					// percentage width is used for remaining width
					// distribution, so not used here.
					int colspan = this.getColSpan();
					for (int i = 0; i < colspan; i++) {
						tableInfo.setWidthPercentage(this.getColumnIndex() + i,
								recommendedWidth.getValue() / colspan);
					}
				} else {
					rw = recommendedWidth.getValue();
					if (!style.isSizeIncludeBorderPadding()) {
						rw += style.getBorderInsets().getWidth()
								+ style.getPaddingInsets().getWidth();
					}
					if (this.getColSpan() == 1) {
						tableInfo.getWidthSpecified()[this.getColumnIndex()] = true;
					}
				}

			}
			_cellWidth = rw;
		}

	}

	/**
	 * @param tableInfo 
	 * @param tableheight 
	 */
	public void calculateHeight(TableInfo tableInfo, int tableheight) {
		ICSSStyle style = this.getFigure().getCSSStyle();
		if (style == null) {
			_cellHeight = -1;
		} else {
			Object height = style.getStyleProperty(ICSSPropertyID.ATTR_HEIGHT);
			Length recommendedHeight = (height instanceof Length) ? (Length) height
					: null;

			int rh = 0;
			if (recommendedHeight == null || recommendedHeight.getValue() <= 0) {
				rh = 0;
			} else {
				if (recommendedHeight.isPercentage()) {
					int rowspan = this.getRowSpan();
					for (int i = 0; i < rowspan; i++) {
						tableInfo.setHeightPercentage(this.getRowIndex() + i,
								recommendedHeight.getValue() / rowspan);
					}
				} else {
					rh = recommendedHeight.getValue();
				}
				if (!style.isSizeIncludeBorderPadding()) {
					rh += style.getBorderInsets().getHeight()
							+ style.getPaddingInsets().getHeight();
				}
			}
			_cellHeight = rh;
		}

	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return _cellHeight;
	}
}
