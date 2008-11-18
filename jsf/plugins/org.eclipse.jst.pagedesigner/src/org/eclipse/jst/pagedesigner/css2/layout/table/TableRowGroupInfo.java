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
/*package*/ class TableRowGroupInfo extends TableItemInfo {
	List _rowList = new ArrayList();

	private int _rowIndex;

	private int _rowCount;

	/**
	 * @param figure
	 */
	public TableRowGroupInfo(ICSSFigure figure) {
		super(figure);
	}

	List getRowList() {
		return _rowList;
	}

	int getRowIndex() {
		return _rowIndex;
	}

	int getRowCount() {
		return this._rowCount;
	}

	/**
	 * @param context
	 */
	public void calculateRowGroup(TableInfoContext context) {
		this._rowIndex = context.getCurrentRow();
		List children = getFigure().getChildren();
		for (int i = 0, size = children.size(); i < size; i++) {
			IFigure childfigure = (IFigure) children.get(i);
			if (childfigure instanceof ICSSFigure) {
				ICSSStyle childstyle = ((ICSSFigure) childfigure).getCSSStyle();
				if (childstyle != null
						&& "table-row" //$NON-NLS-1$
								.equalsIgnoreCase(childstyle.getDisplay())) {
					TableRowInfo rowInfo = new TableRowInfo(
							(ICSSFigure) childfigure);
					_rowList.add(rowInfo);
					rowInfo.calculateRow(context);
				} else {
					// skip
				}
			} else {
				// skip
			}
		}
		context.finishRowGroup();
		this._rowCount = context.getCurrentRow() - this._rowIndex;
	}

	/**
	 * @param _cells
	 */
	public void getCells(List _cells) {
		for (int i = 0, size = _rowList.size(); i < size; i++) {
			TableRowInfo rowInfo = (TableRowInfo) _rowList.get(i);
			rowInfo.getCells(_cells);
		}
	}

	/**
	 * @param figure
	 * @return the table row info
	 */
	public TableRowInfo findRowInfo(CSSFigure figure) {
		for (int i = 0, size = _rowList.size(); i < size; i++) {
			TableRowInfo rowInfo = (TableRowInfo) _rowList.get(i);
			if (figure == rowInfo.getFigure()) {
				return rowInfo;
			}
		}
		return null;
	}
}
