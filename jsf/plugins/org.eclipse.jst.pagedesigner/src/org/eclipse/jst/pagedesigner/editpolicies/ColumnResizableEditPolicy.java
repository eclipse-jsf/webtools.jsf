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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.handles.SquareHandle;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
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
		if ("column".equalsIgnoreCase(elementName)) { //$NON-NLS-1$
			List list = new ArrayList();

			GraphicalEditPart part = (GraphicalEditPart) getHost();
            
            {
    			MoveHandle borderhandle = new MoveHandle(part, new ColumnBorderHandleLocator(
    					part));
    			list.add(borderhandle);
            }
            
            {
    			MyMoveHandle cornerHandle = new MyMoveHandle(part, new ColumnHandleLocator(part, getHostFigure(),
    					PositionConstants.NORTH_WEST));
    			list.add(cornerHandle);
            }
            
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

    private class ColumnHandleLocator extends CornerRelativeHandleLocator {
		private GraphicalEditPart editPart;

		/**
		 * @param editPart
		 * @param reference
		 * @param location
		 */
		public ColumnHandleLocator(GraphicalEditPart editPart, IFigure reference, int location) {
            super(reference, location);
			this.editPart = editPart;
		}
        
        protected Dimension getNewTargetSize(IFigure relocateFigure) {
            return relocateFigure.getPreferredSize();
        }

        protected Rectangle getCurrentTargetBounds(IFigure relocateFigure) {
            return ColumnHelper.getColumnBounds(editPart, relocateFigure);
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
		 * @param editPart
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

    private class ColumnBorderHandleLocator implements Locator {
        private GraphicalEditPart editPart;

        /**
         * @param editPart
         */
        public ColumnBorderHandleLocator(GraphicalEditPart editPart) {
            this.editPart = editPart;
        }

        public void relocate(IFigure target) {
            target.setBounds(ColumnHelper.getColumnBounds(editPart, target));
        }
    }
    
    private static class MyMoveHandle extends SquareHandle
    {
        private static final String MOVE_HANDLE_IMAGE_FILE = "MoveHandle.png"; //$NON-NLS-1$

		/**
         * @param owner
         * @param loc
         */
        public MyMoveHandle(GraphicalEditPart owner, Locator loc) {
            super(owner, loc);
            setCursor(SharedCursors.SIZEALL);
        }

        protected void init() {
            setPreferredSize(16,16);
        }

        protected Color getBorderColor() {
            return ColorConstants.black;
        }

        protected Color getFillColor() {
            return ColorConstants.white;
        }

        protected DragTracker createDragTracker() {
            DragTracker tracker = new DragEditPartsTracker(getOwner());
            
            return tracker;
        }

        public void paintFigure(Graphics graphics) {
            super.paintFigure(graphics);
            final  Image moveImage = PDPlugin.getDefault().getImage(MOVE_HANDLE_IMAGE_FILE);
            Point topLeft = getBounds().getTopLeft().getCopy();
            topLeft.performTranslate(3,3);
            graphics.drawImage(moveImage, topLeft);
        }
    }
}
