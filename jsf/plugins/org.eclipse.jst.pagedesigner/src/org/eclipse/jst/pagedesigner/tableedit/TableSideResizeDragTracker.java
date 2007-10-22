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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * This is the drag tracker for the small resize item on the
 * TableSideResizeHandle. used to adjust a single column/row size.
 * 
 * @author mengbo
 * @version 1.5
 */
public class TableSideResizeDragTracker extends DragEditPartsTracker {
	private boolean _isrow;

	private int _index;

	private MarqueeRectangleFigure _marqueeFigure;

	/**
	 * @param sourceEditPart
	 * @param isrow 
	 * @param index 
	 */
	public TableSideResizeDragTracker(EditPart sourceEditPart, boolean isrow,
			int index) {
		super(sourceEditPart);
		this._isrow = isrow;
		this._index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#showSourceFeedback()
	 */
	protected void showSourceFeedback() {
		Rectangle rect = getFeedbackRect();
		if (rect != null) {
			rect = rect.getCopy();
			getMarqueeRectangleFigure().setBounds(rect);
		} else {
			// ignore.
		}
	}

	private int calculateDelta() {
		int delta;
		// FIXME: TODO: XXX: when delta is too small (<0) to make previous
		// column/row
		// to have negative size, then we need adjust delta.
		if (_isrow) {
			delta = getLocation().y - getStartLocation().y;
		} else {
			delta = getLocation().x - getStartLocation().x;
		}
		return delta;
	}

	/**
	 * @return null if this is not a table.
	 */
	private Rectangle getFeedbackRect() {
		ITableEditAdapter adapter = getTableEditAdapter();
		if (adapter == null) {
			return null;
		}

		IFigure figure = ((GraphicalEditPart) this.getSourceEditPart())
				.getFigure();
		Rectangle bounds = figure.getBounds();
		Insets insets = figure.getInsets();
		Rectangle rect;
		if (_isrow) {
			int delta = calculateDelta();
			rect = new Rectangle(0, adapter.getRowResizeStart(_index) + delta,
					bounds.width - insets.getWidth(), adapter
							.getRowResizeWidth());
		} else {
			int delta = calculateDelta();
			rect = new Rectangle(adapter.getColumnResizeStart(_index) + delta,
					0, adapter.getColumnResizeWidth(), bounds.height
							- insets.getHeight());
		}
		rect.translate(bounds.x + insets.left, bounds.y + insets.top);

		figure.translateToAbsolute(rect);
		getMarqueeRectangleFigure().translateToRelative(rect);
		return rect;
	}

	/**
	 * 
	 * @return null if this is not a table.
	 */
	private ITableEditAdapter getTableEditAdapter() {
		return TableEditHelper.getTableEditAdapter((GraphicalEditPart) this
				.getSourceEditPart());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#eraseSourceFeedback()
	 */
	protected void eraseSourceFeedback() {
		super.eraseSourceFeedback();
		if (_marqueeFigure != null) {
			removeFeedback(_marqueeFigure);
			_marqueeFigure = null;
		}
	}

	private MarqueeRectangleFigure getMarqueeRectangleFigure() {
		if (this._marqueeFigure == null) {
			this._marqueeFigure = new MarqueeRectangleFigure();
			addFeedback(this._marqueeFigure);
		}
		return this._marqueeFigure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		return new TableResizeRequest(this._isrow, this._index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		TableResizeRequest req = (TableResizeRequest) getTargetRequest();
		req.setDelta(calculateDelta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SelectEditPartTracker#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		lockTargetEditPart(getSourceEditPart());
		return super.handleButtonDown(button);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#getCommand()
	 */
	protected Command getCommand() {
		return getTargetEditPart().getCommand(getTargetRequest());
	}
}
