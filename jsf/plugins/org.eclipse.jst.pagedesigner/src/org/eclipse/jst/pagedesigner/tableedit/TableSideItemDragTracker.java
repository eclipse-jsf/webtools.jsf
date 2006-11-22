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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

/**
 * This is the tracker for the TableSideItem. It will be responsible to track
 * the right mouse down event and popup a menu.
 * 
 * @author mengbo
 * @version 1.5
 */
public class TableSideItemDragTracker extends DragEditPartsTracker {
    // TODO: dead?
    //	private boolean _isRow;
//
//	private int _index;

	/**
	 * 
	 * @param sourceEditPart
	 * @param isrow
	 * @param index
	 */
	public TableSideItemDragTracker(EditPart sourceEditPart, boolean isrow,
			int index) {
		super(sourceEditPart);
        // TODO: dead?
//		this._isRow = isrow;
//		this._index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) {
		boolean result = super.handleButtonUp(button);
		//
		// if (button == 3)
		// {
		// MenuManager m = new MenuManager();
		// if (_isRow)
		// {
		// m.add(new InsertRowColumnAction("Insert row before",
		// getSourceEditPart(), _index, _isRow, true));
		// m.add(new InsertRowColumnAction("Insert row after",
		// getSourceEditPart(), _index, _isRow, false));
		// m.add(new DeleteRowColumnAction("Delete row", getSourceEditPart(),
		// _index, _isRow));
		// }
		// else
		// {
		// m.add(new InsertRowColumnAction("Insert column before",
		// getSourceEditPart(), _index, _isRow, true));
		// m.add(new InsertRowColumnAction("Insert column after",
		// getSourceEditPart(), _index, _isRow, false));
		// m.add(new DeleteRowColumnAction("Delete column", getSourceEditPart(),
		// _index, _isRow));
		// }
		// m.createContextMenu(this.getCurrentViewer().getControl()).setVisible(true);
		// }
		return result;
	}
}
