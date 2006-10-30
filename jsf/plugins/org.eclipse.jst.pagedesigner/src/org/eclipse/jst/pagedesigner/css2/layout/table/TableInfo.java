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

import org.eclipse.draw2d.IFigure;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableInfo extends TableItemInfo {
	List _tableHeaderGroups = new ArrayList();

	List _tableTRandTRGs = new ArrayList();

	List _tableFooterGroups = new ArrayList();

	TableCaptionInfo _caption;

	int _columnCount;

	int _rowCount;

	List _cells = null;

	private List _rows = null;

	int _tableWidth; // calculated table width, valid after calling to

	// calculateTableWidth

	int _availableWidth;

	int _tableHeight;

	private int[] _widthPercentage;

	private int[] _heightPercentage;

	boolean[] _widthSpecified;

	/**
	 * @param figure
	 */
	public TableInfo(ICSSFigure figure) {
		super(figure);
	}

	public List getTableHeaderGroups() {
		return _tableHeaderGroups;
	}

	public List getTRandTRGs() {
		return _tableTRandTRGs;
	}

	public List getTableFooterGroups() {
		return _tableFooterGroups;
	}

	public int getColumnCount() {
		return _columnCount;
	}

	public int getRowCount() {
		return _rowCount;
	}

	protected void constructTable() {
		List child = getFigure().getChildren();
		for (int i = 0, size = child.size(); i < size; i++) {
			IFigure childfigure = (IFigure) child.get(i);
			if (childfigure instanceof ICSSFigure) {
				ICSSStyle style = ((ICSSFigure) childfigure).getCSSStyle();
				if (style != null) {
					String display = style.getDisplay();
					if ("table-caption".equalsIgnoreCase(display)) //$NON-NLS-1$
					{
						_caption = new TableCaptionInfo(
								(ICSSFigure) childfigure);
					} else if ("table-row".equalsIgnoreCase(display)) //$NON-NLS-1$
					{
						TableRowInfo rowInfo = new TableRowInfo(
								(ICSSFigure) childfigure);
						_tableTRandTRGs.add(rowInfo);
					} else if ("table-row-group".equalsIgnoreCase(display)) //$NON-NLS-1$
					{
						TableRowGroupInfo groupInfo = new TableRowGroupInfo(
								(ICSSFigure) childfigure);
						_tableTRandTRGs.add(groupInfo);
					} else if ("table-header-group".equalsIgnoreCase(display)) //$NON-NLS-1$
					{
						TableRowGroupInfo groupInfo = new TableRowGroupInfo(
								(ICSSFigure) childfigure);
						_tableHeaderGroups.add(groupInfo);
					} else if ("table-footer-group".equalsIgnoreCase(display)) //$NON-NLS-1$
					{
						TableRowGroupInfo groupInfo = new TableRowGroupInfo(
								(ICSSFigure) childfigure);
						_tableFooterGroups.add(groupInfo);
					} else {
						// something unexpected inside table
					}
				} else {
					// something unexpected inside table
				}
			} else {
				// something unexpected inside table
			}
		}

		TableInfoContext context = new TableInfoContext();
		// now we have the rows ordered, need to calculate row details now.
		for (int i = 0, size = _tableHeaderGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableHeaderGroups
					.get(i);
			groupInfo.calculateRowGroup(context);
		}
		for (int i = 0, size = _tableTRandTRGs.size(); i < size; i++) {
			Object obj = _tableTRandTRGs.get(i);
			if (obj instanceof TableRowGroupInfo) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) obj;
				groupInfo.calculateRowGroup(context);
			} else {
				TableRowInfo rowInfo = (TableRowInfo) obj;
				rowInfo.calculateRow(context);
			}
		}
		for (int i = 0, size = _tableFooterGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableFooterGroups
					.get(i);
			groupInfo.calculateRowGroup(context);
		}
		context.finishTable();

		_columnCount = context.getColumnCount();
		_rowCount = context.getRowCount();

		this._widthPercentage = new int[_columnCount];
		this._heightPercentage = new int[_rowCount];

		this._widthSpecified = new boolean[_columnCount];
		for (int i = 0; i < _columnCount; i++) {
			this._widthSpecified[i] = false;
		}
	}

	public void setWidthPercentage(int columnIndex, int percentageValue) {
		if (percentageValue > this._widthPercentage[columnIndex]) {
			this._widthPercentage[columnIndex] = percentageValue;
		}
	}

	public void setHeightPercentage(int rowIndex, int percentageValue) {
		if (percentageValue > this._heightPercentage[rowIndex]) {
			this._heightPercentage[rowIndex] = percentageValue;
		}
	}

	/**
	 * width percentage will be used to calculate remaining width distribution.
	 * 
	 * @return
	 */
	public int[] getWidthPercentages() {
		return this._widthPercentage;
	}

	public int[] getHeightPercentages() {
		return this._heightPercentage;
	}

	public List getRows() {
		if (_rows == null) {
			this._rows = new ArrayList();

			for (int i = 0, size = _tableHeaderGroups.size(); i < size; i++) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableHeaderGroups
						.get(i);
				_rows.addAll(groupInfo.getRowList());
			}
			for (int i = 0, size = _tableTRandTRGs.size(); i < size; i++) {
				Object obj = _tableTRandTRGs.get(i);
				if (obj instanceof TableRowGroupInfo) {
					TableRowGroupInfo groupInfo = (TableRowGroupInfo) obj;
					_rows.addAll(groupInfo.getRowList());
				} else {
					TableRowInfo rowInfo = (TableRowInfo) obj;
					_rows.add(rowInfo);
				}
			}
			for (int i = 0, size = _tableFooterGroups.size(); i < size; i++) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableFooterGroups
						.get(i);
				_rows.addAll(groupInfo.getRowList());
			}
		}
		return _rows;
	}

	public List getCells() {
		if (_cells == null) {
			_cells = new ArrayList();

			for (int i = 0, size = _tableHeaderGroups.size(); i < size; i++) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableHeaderGroups
						.get(i);
				groupInfo.getCells(_cells);
			}
			for (int i = 0, size = _tableTRandTRGs.size(); i < size; i++) {
				Object obj = _tableTRandTRGs.get(i);
				if (obj instanceof TableRowGroupInfo) {
					TableRowGroupInfo groupInfo = (TableRowGroupInfo) obj;
					groupInfo.getCells(_cells);
				} else {
					TableRowInfo rowInfo = (TableRowInfo) obj;
					rowInfo.getCells(_cells);
				}
			}
			for (int i = 0, size = _tableFooterGroups.size(); i < size; i++) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableFooterGroups
						.get(i);
				groupInfo.getCells(_cells);
			}
		}
		return _cells;
	}

	/**
	 * @param containerWidth
	 *            if the width specification is percentage, then will use
	 *            container width.
	 */
	public void calculateWidth(int contentWidth, int availableWidth) {
		_tableWidth = contentWidth;
		_availableWidth = availableWidth;

		// next calculate cell width
		List cells = getCells();
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			cellinfo.calculateWidth(this, _tableWidth);
		}
	}

	public void calculateHeight(int contentHeight) {
		_tableHeight = contentHeight;

		List rows = getRows();
		for (int i = 0, size = rows.size(); i < size; i++) {
			TableRowInfo rowinfo = (TableRowInfo) rows.get(i);
			rowinfo.calculateHeight(this, _tableHeight);
		}

		// next calculate cell width
		List cells = getCells();
		for (int i = 0, size = cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) cells.get(i);
			cellinfo.calculateHeight(this, _tableHeight);
		}
	}

	/**
	 * @return
	 */
	public int getTableWidth() {
		return _tableWidth;
	}

	/**
	 * @param figure
	 * @return
	 */
	public TableRowGroupInfo findGroupInfo(CSSFigure figure) {
		for (int i = 0, size = _tableHeaderGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableHeaderGroups
					.get(i);
			if (figure == groupInfo.getFigure()) {
				return groupInfo;
			}
		}
		for (int i = 0, size = _tableTRandTRGs.size(); i < size; i++) {
			Object obj = _tableTRandTRGs.get(i);
			if (obj instanceof TableRowGroupInfo) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) obj;
				if (figure == groupInfo.getFigure()) {
					return groupInfo;
				}

			}
		}
		for (int i = 0, size = _tableFooterGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableFooterGroups
					.get(i);
			if (figure == groupInfo.getFigure()) {
				return groupInfo;
			}
		}
		return null; // should not happen.
	}

	/**
	 * @param figure
	 * @return
	 */
	public TableRowInfo findRowInfo(CSSFigure figure) {
		for (int i = 0, size = _tableHeaderGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableHeaderGroups
					.get(i);
			TableRowInfo rowinfo = groupInfo.findRowInfo(figure);
			if (rowinfo != null) {
				return rowinfo;
			}
		}
		for (int i = 0, size = _tableTRandTRGs.size(); i < size; i++) {
			Object obj = _tableTRandTRGs.get(i);
			if (obj instanceof TableRowGroupInfo) {
				TableRowGroupInfo groupInfo = (TableRowGroupInfo) obj;
				TableRowInfo rowinfo = groupInfo.findRowInfo(figure);
				if (rowinfo != null) {
					return rowinfo;
				}
			} else if (obj instanceof TableRowInfo) {
				TableRowInfo info = (TableRowInfo) obj;
				if (figure == info.getFigure()) {
					return info;
				}
			}
		}
		for (int i = 0, size = _tableFooterGroups.size(); i < size; i++) {
			TableRowGroupInfo groupInfo = (TableRowGroupInfo) _tableFooterGroups
					.get(i);
			TableRowInfo rowinfo = groupInfo.findRowInfo(figure);
			if (rowinfo != null) {
				return rowinfo;
			}
		}
		return null; // should not happen.
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasWidthPercentage() {
		for (int i = 0; i < this._widthPercentage.length; i++) {
			if (this._widthPercentage[i] > 0) {
				return true;
			}
		}
		return false;
	}
}
