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

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableRowHandle extends TableSideHandle {
	/**
	 * @param tableHost
	 */
	public TableRowHandle(GraphicalEditPart tableHost) {
		super(tableHost, new TableRowHandleLocator(tableHost));
		// setupRows();
	}

	/**
	 * 
	 * 
	 */
	public void setupRows() {
		ITableEditAdapter tableAdapter = getTableEditAdapter();
		if (tableAdapter == null) {
			return;
		}
		int numRows = tableAdapter.getRowCount();
		for (int i = 0; i < numRows; i++) {
			RowHandle rowHandle = createRowHandle(i);
			add(rowHandle);
			RowResizeHandle rowResizeHandle = createRowResizeHandle(i);
			add(rowResizeHandle);
		}
		RowResizeHandle lastResize = createRowResizeHandle(numRows);
		add(lastResize);
	}

	/**
	 * @param rowIndex
	 * @return
	 */
	private RowResizeHandle createRowResizeHandle(int rowIndex) {
		return new RowResizeHandle(getOwner(), rowIndex);
	}

	/**
	 * @param rowIndex
	 * @return
	 */
	private RowHandle createRowHandle(int rowIndex) {
		return new RowHandle(getOwner(), rowIndex);
	}
}
