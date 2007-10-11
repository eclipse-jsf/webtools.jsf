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

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;

/**
 * @author mengbo
 * @version 1.5
 */
public class FragmentHandle extends AbstractHandle implements Handle {
	/**
	 * @param owner
	 */
	public FragmentHandle(GraphicalEditPart owner) {
		super(owner, new FragmentLocator());
		this.setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#paint(org.eclipse.draw2d.Graphics)
	 */
	public void paint(Graphics graphics) {
		graphics.setClip(this.getBounds().getCopy().expand(7, 7));
		super.paint(graphics);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		DragEditPartsTracker tracker = new DragEditPartsTracker(getOwner());
		tracker.setDefaultCursor(getCursor());
		return tracker;
	}

	private void refresh() {

		// set the bounds of this figure, so it could cover all children.
		bounds = getOwnerFigure().getBounds();
		bounds = new PrecisionRectangle(bounds.getResized(-1, -1));
		getOwnerFigure().translateToAbsolute(bounds);
		this.translateToRelative(bounds);
		this.setBounds(bounds);

		this.removeAll();
		// ok, recreate all children.
		CSSFigure cssfigure = (CSSFigure) getOwner().getFigure();
		List fragments = cssfigure.getFragmentsForRead();
		for (int i = 0, size = fragments.size(); i < size; i++) {
			// the rectangle.
			Figure childFigure = new BorderFigure();
			childFigure.setBorder(new LineBorder(1));
			this.add(childFigure);

			FlowBox box = (FlowBox) fragments.get(i);
			Rectangle rect = new Rectangle(box.getX(), box.getY(), box.getWidth(), box
					.getHeight());
			cssfigure.translateToAbsolute(rect);

			childFigure.translateToRelative(rect);
			childFigure.setBounds(rect);

			createCornerHandles(cssfigure, box);
		}
	}

	/**
	 * 
	 */
	private void createCornerHandles(CSSFigure reference, FlowBox referencebox) {
		createHandle(reference, referencebox, PositionConstants.SOUTH_EAST);
		createHandle(reference, referencebox, PositionConstants.SOUTH_WEST);
		createHandle(reference, referencebox, PositionConstants.NORTH_WEST);
		createHandle(reference, referencebox, PositionConstants.NORTH_EAST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#containsPoint(int, int)
	 */
	public boolean containsPoint(int x, int y) {
		List children = this.getChildren();
		for (int i = 0, n = children.size(); i < n; i++) {
			if (((IFigure) children.get(i)).containsPoint(x, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param north_east
	 */
	private void createHandle(CSSFigure reference, FlowBox referencebox,
			int location) {
		double relativeX, relativeY;
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

		FragmentCornerHandle target = new FragmentCornerHandle(getOwner());
		this.add(target);

		// for corner small box.
		Rectangle targetBounds = new Rectangle(referencebox.getX(),
				referencebox.getY(), referencebox.getWidth(), referencebox
						.getHeight());
		targetBounds = new PrecisionRectangle(targetBounds);
		reference.translateToAbsolute(targetBounds);
		target.translateToRelative(targetBounds);
		// targetBounds.resize(1, 1);

		Dimension targetSize = target.getPreferredSize();

		targetBounds.x += (int) (targetBounds.width * relativeX - ((targetSize.width + 1) / 2));
		targetBounds.y += (int) (targetBounds.height * relativeY - ((targetSize.height + 1) / 2));
		targetBounds.setSize(targetSize);
		target.setBounds(targetBounds);
	}

	static class FragmentLocator implements Locator {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
		 */
		public void relocate(IFigure target) {
			((FragmentHandle) target).refresh();
		}
	}

	static class BorderFigure extends Figure {
		/**
		 * the bounds padding
		 */
		public static final int INNER_PAD = 2;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.draw2d.IFigure#containsPoint(int, int)
		 */
		public boolean containsPoint(int x, int y) {
			if (!super.containsPoint(x, y))
				return false;
			return !Rectangle.SINGLETON.setBounds(getBounds()).shrink(
					INNER_PAD, INNER_PAD).contains(x, y);
		}
	}
}
