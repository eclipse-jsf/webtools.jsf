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
package org.eclipse.jst.pagedesigner.tableedit;

import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableLayout2;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableEditAdapter implements ITableEditAdapter {
	CSSTableLayout2 _tableLayout;

	/**
	 * @param layout2
	 */
	public TableEditAdapter(CSSTableLayout2 layout2) {
		this._tableLayout = layout2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getColumnCount()
	 */
	public int getColumnCount() {
		return _tableLayout.getColumnWidths().length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getRowCount()
	 */
	public int getRowCount() {
		return _tableLayout.getRowHeights().length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#insertColumn(int)
	 */
	public void insertColumn(int atPosition) {
	    // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#insertRow(int)
	 */
	public void insertRow(int rowPosition) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getResizeStart(int)
	 */
	public int getColumnResizeStart(int columnIndex) {
		int w = 0;
		int[] columnWidths = _tableLayout.getColumnWidths();
		for (int i = 0; i < columnIndex; i++) {
			w += columnWidths[i];
		}
		w += columnIndex * _tableLayout.getHSpacing();
		return w;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getResizeWidth()
	 */
	public int getColumnResizeWidth() {
		return _tableLayout.getHSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getColumnStart(int)
	 */
	public int getColumnStart(int columnIndex) {
		return getColumnResizeStart(columnIndex) + _tableLayout.getHSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getColumnWidth(int)
	 */
	public int getColumnWidth(int columnIndex) {
		return _tableLayout.getColumnWidths()[columnIndex];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getRowStart(int)
	 */
	public int getRowStart(int rowIndex) {
		int start = getRowResizeStart(rowIndex) + _tableLayout.getVSpacing();
		if (_tableLayout.getCaptionInfo() != null
				&& "top".equalsIgnoreCase(_tableLayout.getCaptionInfo().getAlign())) //$NON-NLS-1$
		{
			start += _tableLayout.getCaptionSize().height;
		}
		return start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getRowHeight(int)
	 */
	public int getRowHeight(int rowIndex) {
		return _tableLayout.getRowHeights()[rowIndex];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getRowResizeStart(int)
	 */
	public int getRowResizeStart(int rowIndex) {
		int w = 0;
		int[] rowHeights = _tableLayout.getRowHeights();
		for (int i = 0; i < rowIndex; i++) {
			w += rowHeights[i];
		}
		w += rowIndex * _tableLayout.getHSpacing();
		return w;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter#getRowResizeWidth()
	 */
	public int getRowResizeWidth() {
		return _tableLayout.getVSpacing();
	}

}
