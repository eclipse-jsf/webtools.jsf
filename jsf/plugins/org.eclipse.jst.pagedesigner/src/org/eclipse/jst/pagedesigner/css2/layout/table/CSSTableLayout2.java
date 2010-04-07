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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSPainter;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.swt.SWT;

/**
 * see also http://www.w3.org/TR/REC-CSS2/tables.html
 * 
 * @author mengbo
 * @version 1.5
 */
public class CSSTableLayout2 extends CSSBlockFlowLayout implements ICSSPainter {
	static Logger _log = PDPlugin.getLogger(CSSTableLayout2.class);

	int _hspacing;

	int _vspacing;

	int[] _columnWidths;

	int[] _rowHeights;

	Dimension _captionSize;

	// _tableInfo will be initialized in preLayout
	TableInfo _tableInfo;

	private int _internalTableWidth;

	private int _internalTableHeight;

	private int _rowx;

	private int _rowwidth;

	/**
	 * @param flowfigure
	 */
	public CSSTableLayout2(CSSFigure flowfigure) {
		super(flowfigure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#preLayout()
	 */
	protected void preLayout() {
		// super.preLayout will setup the block box.
		super.preLayout();

		ICSSStyle style = this.getCSSStyle();

		_hspacing = _vspacing = 3; // default value

		if (style != null) {
			Object borderspacing = style
					.getStyleProperty(ICSSPropertyID.ATTR_BORDER_SPACING);
			if (borderspacing instanceof int[]) {
				int[] intvalues = (int[]) borderspacing;
				_hspacing = intvalues[0];
				_vspacing = intvalues[1];
			} else {
				ITagEditInfo info = (ITagEditInfo) style
						.getAdapter(ITagEditInfo.class);
				if (info != null && info.needTableDecorator()) {
					// default decorating value. to make things look more
					// separated.
					if (_hspacing < 5) {
						_hspacing = 5;
					}
					if (_vspacing < 5) {
						_vspacing = 5;
					}
				}
			}
		}

		// TODO: support caption
		_tableInfo = new TableInfo(getCSSFigure());

		// construct the table structure.
		_tableInfo.constructTable();

		// calculate the user specified width/height for table and cells.
		// contentWidth is the user specified content width. If <= 0 means no
		// user
		// specification.
		int contentWidth = this._blockBox.getContentWidth();
		int availableWidth = this._blockBox.getRecommendedContentWidth();
		int contentHeight = this._blockBox.getContentHeight();

		_tableInfo.calculateWidth(contentWidth, availableWidth);
		_tableInfo.calculateHeight(contentHeight);

		int columnCount = _tableInfo.getColumnCount();

		int columnMinWidths[] = new int[columnCount];
		int columnMaxWidths[] = new int[columnCount];

		// For each column, determine a maximum and minimum column width from
		// the cells that span only that column. The minimum is that required by
		// the cell with the largest minimum cell width (or the column 'width',
		// whichever is larger). The maximum is that required by the cell with
		// the
		// largest maximum cell width (or the column 'width', whichever is
		// larger).
		List cells = _tableInfo.getCells();
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			if (cellinfo.getColSpan() == 1) {
				int column = cellinfo.getColumnIndex();
				Dimension mincw = cellinfo.getMinCWDimension();
				Dimension maxcw = cellinfo.getMaxCWDimension();
				if (maxcw.width < mincw.width) {
					maxcw.width = mincw.width;
				}
				if (mincw.width > columnMinWidths[column]) {
					columnMinWidths[column] = mincw.width;
				}
				if (maxcw.width > columnMaxWidths[column]) {
					columnMaxWidths[column] = maxcw.width;
				}
			}
		}
		// For caption, determine a maximum and minimum width from it.
		int captionWidth = 0;
		if (_tableInfo.getCaption() != null) {
			captionWidth = _tableInfo.getCaption().getDimension().width;
		}

		// For each cell that spans more than one column, increase the
		// minimum widths of the columns it spans so that together, they
		// are at least as wide as the cell. Do the same for the maximum
		// widths. If possible, widen all spanned columns by approximately
		// the same amount.
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			int colspan = cellinfo.getColSpan();
			if (colspan > 1) {
				int column = cellinfo.getColumnIndex();
				Dimension mincw = cellinfo.getMinCWDimension();
				Dimension maxcw = cellinfo.getMaxCWDimension();

				adjustWidth(column, colspan, mincw.width, columnMinWidths);
				adjustWidth(column, colspan, maxcw.width, columnMaxWidths);
			}
		}

		int sigmaMinWidth = 0;
		int sigmaMaxWidth = 0;
		for (int i = 0; i < columnMinWidths.length; i++) {
			sigmaMinWidth += columnMinWidths[i];
			if (columnMaxWidths[i] == Integer.MAX_VALUE) {
				sigmaMaxWidth = Integer.MAX_VALUE;
			} else if (sigmaMaxWidth != Integer.MAX_VALUE) {
				sigmaMaxWidth += columnMaxWidths[i];
				if (sigmaMaxWidth < 0) {
					sigmaMaxWidth = Integer.MAX_VALUE;
				}
			}
		}
		int spacingall = (columnMinWidths.length + 1) * _hspacing;
		sigmaMinWidth += spacingall;
		if (sigmaMaxWidth != Integer.MAX_VALUE) {
			sigmaMaxWidth += spacingall;
			if (sigmaMaxWidth < 0) {
				sigmaMaxWidth = Integer.MAX_VALUE;
			}
		}

		int tableWidth = _tableInfo.getTableWidth();
		if (tableWidth > 0) {
			// If the 'table' or 'inline-table' element's 'width' property has a
			// specified value (W) other than 'auto', the property's computed
			// value
			// is the greater of W and the minimum width required by all the
			// columns
			// plus cell spacing or borders (MIN). If W is greater than MIN, the
			// extra
			// width should be distributed over the columns.
			int maxMin = Math.max(captionWidth, sigmaMinWidth);
			if (maxMin >= tableWidth) {
				tableWidth = maxMin;
			}
			distribute(tableWidth - sigmaMinWidth, columnMinWidths,
					columnMaxWidths);
		} else {
			// If the 'table' or 'inline-table' element has 'width: auto', the
			// computed
			// table width is the greater of the table's containing block width
			// and MIN.
			// However, if the maximum width required by the columns plus cell
			// spacing or
			// borders (MAX) is less than that of the containing block, use MAX.
			// int availableWidth = this.getCurrentLine().getAvailableWidth();
			int maxMin = Math.max(captionWidth, sigmaMaxWidth);
			if (maxMin <= availableWidth) {
				// TODO: if _tableInfo.hasWidthPercentage, then we need take
				// that into consideration
				// to distribute the column width. Left to next version.
				tableWidth = maxMin;
				// columnMinWidths = columnMaxWidths;
			} else {
				tableWidth = availableWidth;
			}
			distribute(tableWidth - sigmaMinWidth, columnMinWidths,
					columnMaxWidths);
		}

		// now columnMinWidths contains width for each column
		_columnWidths = columnMinWidths;

		// ok, we have finished calculating column width.
		// next we need to find out row heights.
		_rowHeights = new int[_tableInfo.getRowCount()];

		// first find out those TR that has height settings and use them.
		List rows = _tableInfo.getRows();
		for (int i = 0, size = rows.size(); i < size && i < _rowHeights.length; i++) {
			TableRowInfo rowInfo = (TableRowInfo) rows.get(i);
			if (rowInfo.getSpecifiedRowHeight() > 0) {
				_rowHeights[i] = rowInfo.getSpecifiedRowHeight();
			}
		}

		// First the cells don't span multiple rows.
		cells = _tableInfo.getCells();
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			IFigure figure = cellinfo.getFigure();
			int rowspan = cellinfo.getRowSpan();
			if (rowspan == 1) {
				int cellWidth = getCellWidth(cellinfo, _columnWidths);
				Dimension d = figure.getPreferredSize(cellWidth, cellinfo
						.getHeight());
				if (d.height > _rowHeights[cellinfo.getRowIndex()]) {
					_rowHeights[cellinfo.getRowIndex()] = d.height;
				}
			}
		}

		// Next those cells span multiple rows.
		cells = _tableInfo.getCells();
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			IFigure figure = cellinfo.getFigure();
			int rowspan = cellinfo.getRowSpan();
			if (rowspan > 1) {
				int cellWidth = getCellWidth(cellinfo, _columnWidths);
				Dimension d = figure.getPreferredSize(cellWidth, cellinfo
						.getHeight());
				if (d.height > getCellHeight(cellinfo, _rowHeights)) {
					adjustHeight(cellinfo.getRowIndex(), rowspan, d.height,
							_rowHeights);
				}
			}
		}

		// Next we may need distribute height.
		int sigmaHeight = (_tableInfo.getRowCount() + 1) * _vspacing;
		for (int i = 0; i < _rowHeights.length; i++) {
			sigmaHeight += _rowHeights[i];
		}
		if (sigmaHeight < contentHeight) {
			distributeHeights(contentHeight - sigmaHeight, _rowHeights);
		}

		// now we have calculated the width and height of all cells.
		// FIXME: border?
		Insets insets = (style == null ? new Insets() : style.getBorderInsets()
				.getAdded(style.getPaddingInsets()));
		_internalTableWidth = (_tableInfo.getColumnCount() + 1) * _hspacing;
		for (int i = 0; i < _columnWidths.length; i++) {
			_internalTableWidth += _columnWidths[i];
		}
		int minWidth = getLengthValue(style, ICSSPropertyID.ATTR_MIN_WIDTH);
		_internalTableWidth = _internalTableWidth > minWidth ? _internalTableWidth
				: minWidth;

		_blockBox.setWidth(_internalTableWidth + insets.getWidth());
		_internalTableHeight = (_tableInfo.getRowCount() + 1) * _vspacing;
		for (int i = 0; i < _rowHeights.length; i++) {
			_internalTableHeight += _rowHeights[i];
		}
		int minHeight = getLengthValue(style, ICSSPropertyID.ATTR_MIN_HEIGHT);
		_internalTableHeight = _internalTableHeight > minHeight ? _internalTableHeight
				: minHeight;

		int captionHeight = 0;
		if (_tableInfo.getCaption() != null) {
			_captionSize = _tableInfo.getCaption().getFigure().getPreferredSize(
					_internalTableWidth, SWT.DEFAULT);
			captionHeight = _captionSize.height;
		} else {
			_captionSize = null;
		}
		_internalTableHeight += captionHeight;

		_blockBox.setHeight(_internalTableHeight + insets.getHeight());

		_rowwidth = _internalTableWidth - 2 * _hspacing;
		_rowx = _hspacing; // XXX: table border width left?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#endBlock()
	 */
	protected void endBlock() {
		_blockBox.setWidth(_internalTableWidth
				+ _blockBox.getBorderPaddingWidth());
		_blockBox.setHeight(_internalTableHeight
				+ _blockBox.getBorderPaddingHeight());
		super.endBlock();
	}

	//
	// /**
	// * when some of the column has percentage width, and sigmaMax smaller than
	// container,
	// * @param containerWidth
	// * @param columnMinWidths
	// * @param columnMaxWidths
	// * @return
	// */
	// private int distribute2(int containerWidth, int[] columnMinWidths, int[]
	// columnMaxWidths)
	// {
	//        
	// }
	//    
	/**
	 * Distribute the additional width to columnMinWidths, using max width as a
	 * possible reference on how to distribute.
	 * 
	 * @param toDistribute
	 * @param columnMinWidths
	 * @param columnMaxWidths
	 */
	private void distribute(int toDistribute, int[] columnMinWidths,
			int[] columnMaxWidths) {
		if (toDistribute <= 0)
			return;
		if (columnMinWidths.length == 0)
			return;

		int[] delta = new int[columnMinWidths.length];
		int sigmaDelta = 0;
		for (int i = 0; i < columnMinWidths.length; i++) {
			if (_tableInfo.getWidthSpecified()[i]) {
				delta[i] = 0;
			} else {
				delta[i] = columnMaxWidths[i] - columnMinWidths[i];
				if (delta[i] <= 0) {
					delta[i] = 0;
				}
				sigmaDelta += delta[i];
			}
		}

		// re-calculate the width of columns that use a percentage
		int[] widthPercentages = _tableInfo.getWidthPercentages();
		int[] calculatedWidths = new int[columnMaxWidths.length];
		int percentageWidthsTotal = 0;
		for (int i=0; i < widthPercentages.length; i++) {
			if (widthPercentages[i] > 0) {
				// add the widths of the percent width columns
				// back into the available pool
				toDistribute += columnMinWidths[i];
			}
		}

		for (int i=0; i < widthPercentages.length; i++) {
			if (widthPercentages[i] > 0) {
				double val = toDistribute * (widthPercentages[i] / 100.0);
				calculatedWidths[i] = (int) val;
				if (calculatedWidths[i] < columnMinWidths[i]) {
					// percent width is too small, so use
					// the columnMinWidth instead
					calculatedWidths[i] = columnMinWidths[i];
				}
				percentageWidthsTotal += calculatedWidths[i];
			} else {
				calculatedWidths[i] = 0;
			}
		}

		if (percentageWidthsTotal > toDistribute) {
			// calculated width is too large, so shrink the columns
			// to fit the available space
			int widthColumnCount = 0;
			for (int i=0; i < widthPercentages.length; i++) {
				if (widthPercentages[i] > 0) {
					widthColumnCount++;
				}
			}

			int extraSpace = percentageWidthsTotal - toDistribute;
			int shrinkBy = (int)
				Math.ceil((double) extraSpace / (double) widthColumnCount);

			for (int i=0; i < calculatedWidths.length; i++) {
				if (calculatedWidths[i] > 0) {
					calculatedWidths[i] -= shrinkBy;
				}
			}
		}

		// adjust the columnMinWidth values to compensate for the
		// calculated percentages
		for (int i=0; i < calculatedWidths.length; i++) {
			// if column size was calculated, then re-calculate the delta
			if (calculatedWidths[i] > 0) {
				// remove the previous calculation from the sigmaDelta
				int len = columnMaxWidths[i] - columnMinWidths[i];
				delta[i] = 0;
				if (len <= 0) {
					len = 0;
				}
				sigmaDelta -= len;

				// change the minSize to the calculated size
				columnMinWidths[i] = calculatedWidths[i];
				toDistribute -= columnMinWidths[i];
			}
		}

		if (sigmaDelta == 0) {
			// may happen with percent width column calculations.
			// find out how much space is left and distribute it
			// equally to all columns that are not fixed-width.
			int extraSpace = toDistribute;
			for (int i=0; i < columnMinWidths.length; i++) {
				extraSpace -= columnMinWidths[i];
			}

			averageDeltaToCell(columnMinWidths, extraSpace);
		} else {
			int left = toDistribute;
			for (int i = 0; i < columnMinWidths.length - 1; i++) {
				if (delta[i] > 0) {
					int add = delta[i] * toDistribute / sigmaDelta;
					left -= add;
					columnMinWidths[i] += add;
				}
			}
			columnMinWidths[columnMinWidths.length - 1] += left;
		}
	}

	private void averageDeltaToCell(int[] columnMinWidths, int toDistribute) {

		if (toDistribute <= 0) {
			return;
		}
		ArrayList list = new ArrayList();
		for (int i = 0; i < columnMinWidths.length; i++) {
			if (!_tableInfo.getWidthSpecified()[i]) {
				list.add(new Integer(i));
			}
		}
		if (list.size() == 0) {
			for (int i = 0; i < columnMinWidths.length; i++) {
				list.add(new Integer(i));
			}
		}
		int padding = toDistribute / list.size();
		int left = toDistribute % list.size();
		for (int i = 0, n = list.size(); i < n; i++) {
			columnMinWidths[((Integer) list.get(i)).intValue()] += padding;
		}
		if (left > 0) {
			for (int i = 0; i < left; i++) {
				columnMinWidths[((Integer) list.get(i)).intValue()] += 1;
			}
		}
	}

	/**
	 * @param i
	 * @param heights
	 */
	private void distributeHeights(int toDistribute, int[] heights) {
		if (heights.length == 0)
			return;
		int eachDelta = toDistribute / heights.length;
		for (int i = 0; i < heights.length - 1; i++) {
			heights[i] += eachDelta;
		}
		heights[heights.length - 1] += toDistribute - (heights.length - 1)
				* eachDelta;
	}

	/**
	 * @param cellinfo
	 * @param heights
	 * @return the cell height
	 */
	public int getCellHeight(TableCellInfo cellinfo, int[] heights) {
		int rowIndex = cellinfo.getRowIndex();
		int rowspan = cellinfo.getRowSpan();
		int h = 0;
		for (int i = 0; i < rowspan; i++) {
			h += heights[rowIndex + i];
		}
		h += (rowspan - 1) * _vspacing;
		return h;
	}

	/**
	 * @param cellinfo
	 * @param widths
	 * @return the cell width
	 */
	public int getCellWidth(TableCellInfo cellinfo, int[] widths) {
		int columnIndex = cellinfo.getColumnIndex();
		int colspan = cellinfo.getColSpan();
		int w = 0;
		for (int i = 0; i < colspan; i++) {
			w += widths[columnIndex + i];
		}
		w += (colspan - 1) * _hspacing;
		return w;
	}

	/**
	 * @param column
	 *            the start column
	 * @param colspan
	 *            number of columns
	 * @param width
	 *            desired width
	 * @param columnWidths
	 *            current columns widths. After the adjust, need make sure the
	 *            columnWidths to be bigger than desired width
	 */
	private void adjustWidth(int column, int colspan, int width,
			int[] columnWidths) {
		adjustSpan(column, colspan, width, columnWidths, _hspacing);
	}

	/**
	 * @see #adjustWidth(int, int, int, int[])
	 */
	private void adjustHeight(int rowIndex, int rowspan, int height,
			int[] heights) {
		adjustSpan(rowIndex, rowspan, height, heights, _vspacing);
	}

	static private void adjustSpan(int column, int colspan, int width,
			int[] columnWidths, int spacing) {
		int spanwidth = 0;
		for (int i = 0; i < colspan; i++) {
			spanwidth += columnWidths[column + i];
		}
		// XXX: vspacing here?
		spanwidth += (colspan - 1) * spacing;

		if (spanwidth >= width) {
			return;
		}
        int delta = width - spanwidth;
        int deltaeach = delta / colspan;
        for (int i = 0; i < colspan - 1; i++) {
        	columnWidths[column + i] += deltaeach;
        }
        columnWidths[column + colspan - 1] += (delta - (colspan - 1)
        		* deltaeach);
	}

	/**
	 * @return the row heights
	 */
	public int[] getRowHeights() {
		return _rowHeights;
	}

	/**
	 * @return the column widths
	 */
	public int[] getColumnWidths() {
		return _columnWidths;
	}

	/**
	 * @return the vertical spacing value
	 */
	public int getVSpacing() {
		return _vspacing;
	}

	/**
	 * @return the horizontal spacing value
	 */
	public int getHSpacing() {
		return _hspacing;
	}

	/**
	 * @param figure
	 * @return the table row info for the figure
	 */
	public TableRowInfo getRowInfo(CSSFigure figure) {
		return _tableInfo.findRowInfo(figure);
	}

	/**
	 * @return the table caption info
	 */
	public TableCaptionInfo getCaptionInfo() {
		return _tableInfo.getCaption();
	}

	/**
	 * @param figure
	 * @return the table row group info for the figure
	 */
	public TableRowGroupInfo getGroupInfo(CSSFigure figure) {
		return _tableInfo.findGroupInfo(figure);
	}

	/**
	 * @return the row's x
	 */
	public int getRowX() {
		return _rowx;
	}

	/**
	 * @return the row's width
	 */
	public int getRowWidth() {
		return _rowwidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#shouldExpand()
	 */
	public boolean shouldExpand() {
		return false;
	}

	/**
	 * @return the rendered dimensions of the table caption
	 */
	public Dimension getCaptionSize() {
		return _captionSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSPainter#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			ITagEditInfo info = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (info != null && info.needTableDecorator()) {
				List cells = _tableInfo.getCells();
				for (int i = 0, size = cells.size(); i < size; i++) {
					TableCellInfo cellInfo = (TableCellInfo) cells.get(i);
					IFigure cellfigure = cellInfo.getFigure();
					Rectangle rect = cellfigure.getBounds().getCopy();
					rect = rect.expand(1, 1);
					g.setLineStyle(Graphics.LINE_SOLID);
					g.setLineWidth(1);
					g.setForegroundColor(ColorConstants.lightGray);
					g.drawRectangle(rect);
				}
			}
		}
	}

}
