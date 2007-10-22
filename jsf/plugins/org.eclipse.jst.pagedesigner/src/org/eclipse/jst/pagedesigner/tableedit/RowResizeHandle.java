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

/**
 * @author mengbo
 * @version 1.5
 */
public class RowResizeHandle extends TableSideResizeHandle {
	// 0 means before first row
	int _rowIndex;

	/**
	 * @param owner 
	 * @param index 
	 */
	public RowResizeHandle(GraphicalEditPart owner, int index) {
		super(owner, true, index);

		_rowIndex = index;
	}

	/**
	 * @return the row index
	 */
	public int getRowIndex() {
		return _rowIndex;
	}
}
