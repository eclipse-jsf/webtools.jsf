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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class TableSideItemHandle extends AbstractHandle {
	private boolean _isRow;

	private int _index;

	/**
	 * @param owner 
	 * @param isRow 
	 * @param index 
	 * 
	 */
	public TableSideItemHandle(GraphicalEditPart owner, boolean isRow, int index) {
		super(owner, new EmptyLocator());
		this._isRow = isRow;
		this._index = index;
		initialize();
	}

	/**
	 * 
	 */
	private void initialize() {
		this.setOpaque(false);
		LineBorder border1 = new LineBorder(ColorConstants.green, 1);
		this.setBorder(border1);
		this.setCursor(Cursors.ARROW);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		graphics.setXORMode(true);
		graphics.setBackgroundColor(ColorConstants.darkGray);
		graphics.fillRectangle(getBounds());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		return new TableSideItemDragTracker(getOwner(), _isRow, _index);
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return _index;
	}

	/**
	 * @return true if is row
	 */
	public boolean isRow() {
		return _isRow;
	}
}
