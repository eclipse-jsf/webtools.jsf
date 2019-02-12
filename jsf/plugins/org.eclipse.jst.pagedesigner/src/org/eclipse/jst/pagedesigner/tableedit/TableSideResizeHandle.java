/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tableedit;

import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableSideResizeHandle extends AbstractHandle {
	private boolean _isRow;

	private int _index;

	/**
	 * @param owner 
	 * @param isrow 
	 * @param index 
	 * 
	 */
	public TableSideResizeHandle(GraphicalEditPart owner, boolean isrow,
			int index) {
		super(owner, new EmptyLocator());
		this._isRow = isrow;
		this._index = index;

		this.setCursor(isrow ? Cursors.SIZEN : Cursors.SIZEE);
		// this.setBackgroundColor(ColorConstants.green);
		this.setOpaque(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		return new TableSideResizeDragTracker(getOwner(), _isRow, _index);
	}
}
