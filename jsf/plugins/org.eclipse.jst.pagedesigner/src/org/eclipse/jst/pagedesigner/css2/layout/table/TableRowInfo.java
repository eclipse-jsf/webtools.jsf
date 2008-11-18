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
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.value.Length;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TableRowInfo extends TableItemInfo {
	List _cells = new ArrayList();

	int _rowIndex;

	private int _rowHeight;

	/**
	 * @param figure
	 */
	public TableRowInfo(ICSSFigure figure) {
		super(figure);
	}

	List getCells() {
		return _cells;
	}

	int getRowIndex() {
		return _rowIndex;
	}

	int getSpecifiedRowHeight() {
		return _rowHeight;
	}

	/**
	 * @param context
	 */
	public void calculateRow(TableInfoContext context) {
		this._rowIndex = context.getCurrentRow();

		List children = getFigure().getChildren();
		for (int i = 0, size = children.size(); i < size; i++) {
			IFigure childfigure = (IFigure) children.get(i);
			if (childfigure instanceof ICSSFigure) {
				ICSSStyle childstyle = ((ICSSFigure) childfigure).getCSSStyle();
				if (childstyle != null) {
					String display = childstyle.getDisplay();
					if ("table-cell".equalsIgnoreCase(display)) { //$NON-NLS-1$
						TableCellInfo cellInfo = new TableCellInfo(
								(ICSSFigure) childfigure);
						cellInfo.calculateCellInfo(context);
						_cells.add(cellInfo);
					} else {
						// skip
					}
				}
			} else {
				// skip
			}
		}
		// ok, we have finished a row
		context.finishRow();
	}

	/**
	 * @param cells
	 */
	public void getCells(List cells) {
		cells.addAll(this._cells);
	}

	/**
	 * @param figure
	 * @return the table cell info
	 */
	public TableCellInfo getCellInfo(CSSFigure figure) {
		for (int i = 0, size = _cells.size(); i < size; i++) {
			TableCellInfo cellinfo = (TableCellInfo) _cells.get(i);
			if (cellinfo.getFigure() == figure) {
				return cellinfo;
			}
		}
		return null;
	}

	/**
	 * @param info
	 * @param tableHeight
	 */
	public void calculateHeight(TableInfo info, int tableHeight) {
		ICSSStyle style = this.getFigure().getCSSStyle();
		if (style == null) {
			this._rowHeight = -1;
		} else {
			Object height = style.getStyleProperty(ICSSPropertyID.ATTR_HEIGHT);
			Length recommendedHeight = (height instanceof Length) ? (Length) height
					: null;

			int rh = 0;
			if (recommendedHeight == null || recommendedHeight.getValue() <= 0) {
				rh = 0;
			} else {
				if (recommendedHeight.isPercentage()) {
					// not supported.
				} else {
					rh = recommendedHeight.getValue();
				}
				if (rh > 0 && !style.isSizeIncludeBorderPadding()) {
					rh += style.getBorderInsets().getHeight()
							+ style.getPaddingInsets().getHeight();
				}
			}
			this._rowHeight = rh;
		}

	}
}
