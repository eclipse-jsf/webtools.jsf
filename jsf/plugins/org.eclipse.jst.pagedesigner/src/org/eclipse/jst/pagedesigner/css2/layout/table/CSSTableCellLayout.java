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
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;
import org.eclipse.jst.pagedesigner.css2.layout.LineBox;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.VerticalAlignMeta;

/**
 * This layout is for those thigns that it's parent will decide its size. Such
 * as table cell.
 * 
 * @author mengbo
 * @version 1.5
 */
public class CSSTableCellLayout extends CachedTableCellLayout {
	private CSSTableLayout2 _tableLayout;

	private TableRowInfo _rowinfo;

	private TableCellInfo _cellinfo;

	/**
	 * @param cssfigure
	 */
	public CSSTableCellLayout(CSSFigure cssfigure) {
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
		_rowinfo = null;
		_cellinfo = null;
	}

	protected void endBlock() {
		if (isTable()) {
			verticalLayoutLines();
			layoutLines();
		} else {
			super.endBlock();
		}
	}

	/**
	 * process the vertical layout lines
	 */
	protected void verticalLayoutLines() {
		List lines = _blockBox.getFragments();

		String verticalStyle = getVerticalAlign();
		int linesHeight = 0;

		if (lines != null && !lines.isEmpty()) {
			FlowBox bottomBox = ((FlowBox) lines.get(lines.size() - 1));
			FlowBox topBox = ((FlowBox) lines.get(0));
			linesHeight = bottomBox.getY() + bottomBox.getHeight() - topBox.getY();
		}
		int movement = 0;
		if (VerticalAlignMeta.BOTTOM.equals(verticalStyle)) {
			movement = _blockBox.getHeight() - linesHeight
					- _blockBox.getBorderPaddingHeight() / 2;
		} else if (VerticalAlignMeta.TOP.equals(verticalStyle)) {
			movement = 0;
		}
		// else if (VerticalAlignMeta.BASELINE.equals(verticalStyle))
		// {
		// movement = _blockBox.getHeight() - linesHeight;
		// }
		else // if (VerticalAlignMeta.MIDDLE.equals(verticalStyle))
		{
			movement = (_blockBox.getHeight() - linesHeight - _blockBox
					.getBorderPaddingHeight()) / 2;
		}
		// VerticalAlignMeta.TOP, ICSSPropertyID.VAL_AUTO and others
		// else
		// {
		// movement = 0;
		// }
		if (lines != null) {
			for (int i = 0, n = lines.size(); i < n; i++) {
				if (lines.get(i) instanceof LineBox) {
					LineBox lineBox = (LineBox) lines.get(i);
					int LineMovement = Math.max(lineBox.getMarginInsets()
							.getHeight(), movement);
					lineBox.setY(lineBox.getY() + LineMovement
							- lineBox.getMarginInsets().getHeight());
				}
			}
		}
	}

	private String getVerticalAlign() {
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			return style.getStyleProperty(ICSSPropertyID.ATTR_VERTICAL_ALIGN)
					.toString();
		}
		return VerticalAlignMeta.MIDDLE;
	}

	public Rectangle getCellRect() {
		int columnIndex = _cellinfo.getColumnIndex();
		int rowIndex = _cellinfo.getRowIndex();
		int[] columnWidths = _tableLayout.getColumnWidths();
		int hspacing = _tableLayout.getHSpacing();
		int x = hspacing;
		for (int col = 0; col < columnIndex; col++) {
			x += columnWidths[col];
			x += hspacing;
		}

		int[] rowHeights = _tableLayout.getRowHeights();
		int vspacing = _tableLayout.getVSpacing();
		int y = vspacing;
		for (int row = 0; row < rowIndex; row++) {
			y += rowHeights[row];
			y += vspacing;
		}
		if (_tableLayout.getCaptionInfo() != null
				&& "top".equalsIgnoreCase(_tableLayout.getCaptionInfo().getAlign())) //$NON-NLS-1$
		{
			y += _tableLayout.getCaptionSize().height;
		}

		int width = _tableLayout.getCellWidth(_cellinfo, columnWidths);
		int height = _tableLayout.getCellHeight(_cellinfo, rowHeights);

		Rectangle rect = new Rectangle(x, y, width, height);
		return rect;
	}

	/**
	 * the parent figure of TRGroup should be table figure. If so, return the
	 * corresponding table layout.
	 * 
	 * @return the table layout context
	 */
	public CSSTableLayout2 getTableLayoutContext() {
		IFigure parent = getCSSFigure().getParent();
		if (parent != null) {
			LayoutManager parentLayout = parent.getLayoutManager();
			if (parentLayout instanceof CSSTRLayout) {
				return ((CSSTRLayout) parentLayout).getTableLayoutContext();
			}
		}

		return null;
	}

	/**
	 * @return true if the table layout has cell info
	 */
	public boolean initializeTableInfo() {
		_rowinfo = null;
		_cellinfo = null;
		_tableLayout = getTableLayoutContext();
		if (_tableLayout != null) {
			_rowinfo = _tableLayout.getRowInfo((CSSFigure) this.getCSSFigure()
					.getParent());
			if (_rowinfo != null) {
				_cellinfo = _rowinfo.getCellInfo(this.getCSSFigure());
				if (_cellinfo != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return the table layout or nul if none
	 */
	public CSSTableLayout2 getTableLayout() {
		return _tableLayout;
	}

	/**
	 * @return the table cell info or null
	 */
	public TableCellInfo getTableCellInfo() {
		return _cellinfo;
	}
}
