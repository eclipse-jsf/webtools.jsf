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
public class TableColumnHandle extends TableSideHandle {
	/**
	 * @param tableHost
	 */
	public TableColumnHandle(GraphicalEditPart tableHost) {
		super(tableHost, new TableColumnHandleLocator(tableHost));
	}

	/**
	 * 
	 * 
	 */
	public void setupColumns() {
		ITableEditAdapter tableAdapter = getTableEditAdapter();
		if (tableAdapter == null) {
			return;
		}
		int numColumns = tableAdapter.getColumnCount();
		for (int i = 0; i < numColumns; i++) {
			ColumnHandle columnHandle = createColumnHandle(i);
			add(columnHandle);
			ColumnResizeHandle columnResizeHandle = createColumnResizeHandle(i);
			add(columnResizeHandle);
		}
		ColumnResizeHandle lastResize = createColumnResizeHandle(numColumns);
		add(lastResize);
	}

	/**
	 * @param numColumns
	 * @return
	 */
	private ColumnResizeHandle createColumnResizeHandle(int numColumns) {
		return new ColumnResizeHandle(getOwner(), numColumns);
	}

	/**
	 * @param i
	 * @return
	 */
	private ColumnHandle createColumnHandle(int i) {
		return new ColumnHandle(getOwner(), i);
	}
}
