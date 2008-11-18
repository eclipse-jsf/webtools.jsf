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
package org.eclipse.jst.pagedesigner.commands.nav;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingNavigationMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.widgets.Caret;

/**
 * @author mengbo
 */
public class VerticalMoveCommand extends Command {
	private static Logger _log = PDPlugin
			.getLogger(HorizontalMoveCommand.class);

	IHTMLGraphicalViewer _viewer;

	boolean _up;

	boolean _onlyMoveEnd;

	/**
	 * @param viewer
	 * @param up
	 * @param c
	 */
	public VerticalMoveCommand(IHTMLGraphicalViewer viewer, boolean up,
			boolean c) {
		_viewer = viewer;
		_up = up;
		_onlyMoveEnd = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		int OFFSET = 2;
		DesignRange range = _viewer.getRangeSelection();
		if (range == null || !range.isValid()) {
			_log.error("invalid range"); //$NON-NLS-1$
			return;
		}
		IMovementMediator moveMediator = new InlineEditingNavigationMediator(
				new ActionData(ActionData.KEYBOARD_NAVAGATION, null));
		DesignPosition position = range.getEndPosition();
		Caret caret = _viewer.getCaret();
		Point point = null;
		DesignPosition newPos = position;
		EditPart containerpart = null, rootpart1;
		if (_up) {
			point = new Point(((CaretPositionTracker) _viewer).getXoffset(),
					caret.getBounds().y);
		} else {
			point = new Point(((CaretPositionTracker) _viewer).getXoffset(),
					caret.getBounds().y + caret.getBounds().height);

		}
		rootpart1 = getRootEditablePart(position.getContainerPart(),
				moveMediator);// position.getContainerPart();
		if (rootpart1 == null) {
			return;
		}
        
        point = adjustLocation(rootpart1, point);
		Rectangle bound = EditPartPositionHelper.getAbsoluteBounds(rootpart1);
		// get current according to the point.
		// FlowBoxLine line =
		// getCurrentLine(moveMediator.getEditableContainer(new
		// Target(rootpart)), point, moveMediator);
		while (true) {
			// try to change offset and then to search for new point.
			if (_up) {
				point.y -= OFFSET;
				if (point.y <= bound.y) {
					newPos = position;
					break;
				}
			} else {
				point.y += OFFSET;
				if (point.y >= bound.y + bound.height) {
					newPos = position;
					break;
				}
			}
			containerpart = ((InlineEditingNavigationMediator) moveMediator)
					.getConstainedEditableContainer(position, point, _viewer);
			if (containerpart != null) {
				if (!EditModelQuery.isChild(Target.resolveNode(rootpart1),
						Target.resolveNode(containerpart))) {
					containerpart = rootpart1;
				}
				bound = EditPartPositionHelper.getAbsoluteBounds(rootpart1);
				newPos = EditPartPositionHelper
						.findEditPartPositionConstrained(containerpart, point,
								moveMediator);
			} else {
				newPos = position;
				break;
			}
			if (newPos != null) {
				if (found(newPos, position)) {
					break;
				}
			}
		}
		setRange(position, newPos);
	}

	private void setRange(DesignPosition position, DesignPosition newPos) {
		if (!EditModelQuery.isSame(position, newPos)) {
			{
				if (_onlyMoveEnd) {
					_viewer.setRangeEndPosition(newPos);
				} else {
					_viewer.setRange(newPos, newPos);
				}
			}
		}
	}

	/*
	 * We should change this, it is too tricky to do in this way.
	 */
	private Point adjustLocation(EditPart rootPart, Point point) {
		Point result = point.getCopy();
		Rectangle bounds = EditPartPositionHelper.getAbsoluteBounds(rootPart);
		if (!bounds.contains(point)) {
			if (bounds.getLeft().x > point.x) {
				result.x = bounds.getLeft().x;
			} else if (bounds.getRight().x < point.x) {
				result.x = bounds.getRight().x;
			}
		}
		return result;
	}

	private EditPart getRootEditablePart(EditPart part,
			IMovementMediator moveMediator) {
		EditPart rootpart = null;
		if ((rootpart = ((InlineEditingNavigationMediator) moveMediator)
				.getRootConstainedEditableContainer(new Target(part))) == null) {
			rootpart = moveMediator.getEditableContainer(new Target(part));
		}
		return rootpart;
	}

	private boolean found(DesignPosition newPos, DesignPosition prevPos) {
		Rectangle newRec = EditPartPositionHelper
				.convertToAbsoluteCaretRect(newPos);
		Rectangle prevRec = EditPartPositionHelper
				.convertToAbsoluteCaretRect(prevPos);
		if (_up) {
			return getYDistance(newRec, prevRec, _up) < 0;
		}
        return getYDistance(newRec, prevRec, _up) > 0;
	}

	/**
	 * Distance from rec1 to rec2 at y coordination, if top, compare top,
	 * otherwise compare bottom.
	 * 
	 * @param rec1
	 * @param rec2
	 * @param up
	 * @return
	 */
	private int getYDistance(Rectangle rec1, Rectangle rec2, boolean top) {
		if (rec1.getCopy().intersect(rec2).height > 0) {
			return 0;
		}
        if (top) {
        	return rec1.getTop().y - rec2.getTop().y;
        }
        return rec1.getBottom().y - rec2.getBottom().y;
	}
}
