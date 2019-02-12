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

import org.eclipse.draw2d.Locator;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableSideHandle extends AbstractHandle {
	/**
	 * @param owner
	 * @param loc
	 */
	public TableSideHandle(GraphicalEditPart owner, Locator loc) {
		super(owner, loc);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the edit part
	 */
	public GraphicalEditPart getTableEditPart() {
		return this.getOwner();
	}

	/**
	 * @return the adapter
	 */
	public ITableEditAdapter getTableEditAdapter() {
		return TableEditHelper.getTableEditAdapter(getOwner());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
	 */
	protected boolean useLocalCoordinates() {
		return true;
	}
}
