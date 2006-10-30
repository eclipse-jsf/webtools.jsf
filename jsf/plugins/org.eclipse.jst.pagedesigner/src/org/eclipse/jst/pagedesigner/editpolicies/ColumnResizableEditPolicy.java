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
package org.eclipse.jst.pagedesigner.editpolicies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.graphics.Cursor;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class ColumnResizableEditPolicy extends ElementResizableEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	protected List createSelectionHandles() {
		String elementName = ((Element) getHost().getModel()).getLocalName();
		if ("column".equalsIgnoreCase(elementName)) {
			List list = new ArrayList();

			GraphicalEditPart part = (GraphicalEditPart) getHost();
			MoveHandle handle = new MoveHandle(part, new ColumnHandleLocator(
					part));
			list.add(handle);

			SelectEditPartTracker tracker = new SelectEditPartTracker(getHost());
			list.add(createHandle(part, PositionConstants.SOUTH_EAST, tracker,
					SharedCursors.ARROW));
			list.add(createHandle(part, PositionConstants.SOUTH_WEST, tracker,
					SharedCursors.ARROW));
			list.add(createHandle(part, PositionConstants.NORTH_WEST, tracker,
					SharedCursors.ARROW));
			list.add(createHandle(part, PositionConstants.NORTH_EAST, tracker,
					SharedCursors.ARROW));
			return list;

		}
		return super.createSelectionHandles();
	}

	private Handle createHandle(GraphicalEditPart owner, int direction,
			DragTracker tracker, Cursor cursor) {
		ResizeHandle handle = new ResizeHandle(owner, new ColumnCornerLocator(
				owner, direction), cursor);
		handle.setCursor(cursor);
		handle.setDragTracker(tracker);
		return handle;
	}

	private class ColumnHandleLocator implements Locator {
		private GraphicalEditPart editPart;

		public ColumnHandleLocator(GraphicalEditPart editPart) {
			this.editPart = editPart;
		}

		public void relocate(IFigure target) {
			target.setBounds(ColumnHelper.getColumnBounds(editPart, target));
		}
	}

	private class ColumnCornerLocator implements Locator {
		private double relativeX;

		private double relativeY;

		private GraphicalEditPart editPart;

		/**
		 * Constructs a RelativeLocator with the given reference figure and
		 * relative location. The location is a constant from
		 * {@link PositionConstants} used as a convenient and readable way to
		 * set both the relativeX and relativeY values.
		 * 
		 * @param reference
		 *            the reference figure
		 * @param location
		 *            one of NORTH, NORTH_EAST, etc.
		 */
		public ColumnCornerLocator(GraphicalEditPart editPart, int location) {
			this.editPart = editPart;
			switch (location & PositionConstants.NORTH_SOUTH) {
			case PositionConstants.NORTH:
				relativeY = 0;
				break;
			case PositionConstants.SOUTH:
				relativeY = 1.0;
				break;
			default:
				relativeY = 0.5;
			}

			switch (location & PositionConstants.EAST_WEST) {
			case PositionConstants.WEST:
				relativeX = 0;
				break;
			case PositionConstants.EAST:
				relativeX = 1.0;
				break;
			default:
				relativeX = 0.5;
			}
		}

		/**
		 * Relocates the target using the relative offset locations.
		 * 
		 * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
		 */
		public void relocate(IFigure target) {
			Rectangle targetBounds = ColumnHelper.getColumnBounds(editPart,
					target);

			Dimension targetSize = target.getPreferredSize();

			targetBounds.x += (int) (targetBounds.width * relativeX - ((targetSize.width + 1) / 2));
			targetBounds.y += (int) (targetBounds.height * relativeY - ((targetSize.height + 1) / 2));
			targetBounds.setSize(targetSize);
			target.setBounds(targetBounds);
		}
	}
}
