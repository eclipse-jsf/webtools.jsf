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
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.handles.MoveHandleLocator;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableRowHandleLocator extends MoveHandleLocator {

	GraphicalEditPart _tablePart;

	/**
	 * @param tablePart
	 */
	public TableRowHandleLocator(GraphicalEditPart tablePart) {
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
		// bounds = new PrecisionRectangle(bounds.getResized(-1, -1));
		Insets referenceInsets = getReference().getInsets();

		Rectangle r = new Rectangle(bounds.x + bounds.width, bounds.y
				+ referenceInsets.top, TableEditConst.WIDTH, bounds.height
				- referenceInsets.getHeight());
		bounds = new PrecisionRectangle(r);

		getReference().translateToAbsolute(bounds);
		target.translateToRelative(bounds);

		target.setBounds(bounds);
		relocateChildren(target, getReference());
	}

	/**
	 * @param target
	 * @param reference
	 */
	private void relocateChildren(IFigure target, IFigure reference) {
		// As user may removed columns/rows, so need to recalculate columns.
		TableRowHandle tableRowHandle = (TableRowHandle) target;
		tableRowHandle.removeAll();
		tableRowHandle.setupRows();
		List children = target.getChildren();

		ITableEditAdapter tableAdapter = TableEditHelper
				.getTableEditAdapter(this._tablePart);
		if (tableAdapter == null) {
			return;
		}
		for (int i = 0, size = children.size(); i < size; i++) {
			Rectangle rect = null;
			IFigure child = (IFigure) children.get(i);
			if (child instanceof RowHandle) {
				RowHandle rowHandle = (RowHandle) child;
				int rowIndex = rowHandle.getIndex();
				rect = new Rectangle(0, tableAdapter.getRowStart(rowIndex),
						TableEditConst.WIDTH, tableAdapter
								.getRowHeight(rowIndex));
			} else if (child instanceof RowResizeHandle) {
				RowResizeHandle resizeHandle = (RowResizeHandle) child;
				int rowIndex = resizeHandle.getRowIndex();
				rect = new Rectangle(0, tableAdapter
						.getRowResizeStart(rowIndex), TableEditConst.WIDTH,
						tableAdapter.getRowResizeWidth());
			} else {
				// should not happen.
			}
			if (rect != null) {
				child.setBounds(rect);
			}
		}
	}

}
