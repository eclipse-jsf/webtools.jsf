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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.handles.MoveHandleLocator;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableColumnHandleLocator extends MoveHandleLocator {

	GraphicalEditPart _tablePart;

	/**
	 * @param tablePart 
	 */
	public TableColumnHandleLocator(GraphicalEditPart tablePart) {
		super(tablePart.getFigure());
		_tablePart = tablePart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.MoveHandleLocator#relocate(org.eclipse.draw2d.IFigure)
	 */
	public void relocate(IFigure target) {
		Rectangle bounds;
		if (getReference() instanceof HandleBounds) {
			bounds = ((HandleBounds) getReference()).getHandleBounds();
		} else {
			bounds = getReference().getBounds();
		}
		Insets referenceInsets = getReference().getInsets();

		Rectangle r = new Rectangle(bounds.x + referenceInsets.left, bounds.y
				+ bounds.height, bounds.width - referenceInsets.getWidth(),
				TableEditConst.HEIGHT);

		getReference().translateToAbsolute(r);
		target.translateToRelative(r);

		target.setBounds(r);
		relocateChildren(target, getReference());
	}

	/**
	 * @param target
	 * @param reference
	 */
	private void relocateChildren(IFigure target, IFigure reference) {
		// As user may removed columns/rows, so need to recalculate columns.
		TableColumnHandle tableColumnHandle = (TableColumnHandle) target;
		tableColumnHandle.removeAll();
		tableColumnHandle.setupColumns();

		// ---------------------------
		List children = target.getChildren();

		ITableEditAdapter tableAdapter = TableEditHelper
				.getTableEditAdapter(this._tablePart);
		if (tableAdapter == null) {
			// XXX: what should we do if we found it is no longer table?
			// here just skip
			return;
		}
		for (int i = 0, size = children.size(); i < size; i++) {
			Rectangle rect = null;
			IFigure child = (IFigure) children.get(i);
			if (child instanceof ColumnHandle) {
				ColumnHandle columnHandle = (ColumnHandle) child;
				int columnIndex = columnHandle.getIndex();
				rect = new Rectangle(tableAdapter.getColumnStart(columnIndex),
						0, tableAdapter.getColumnWidth(columnIndex),
						TableEditConst.HEIGHT);
			} else if (child instanceof ColumnResizeHandle) {
				ColumnResizeHandle resizeHandle = (ColumnResizeHandle) child;
				int columnIndex = resizeHandle.getColumnIndex();
				rect = new Rectangle(tableAdapter
						.getColumnResizeStart(columnIndex), 0, tableAdapter
						.getColumnResizeWidth(), TableEditConst.HEIGHT);
			} else {
				// should not happen.
			}
			if (rect != null) {
				child.setBounds(rect);
			}
		}
	}

}
