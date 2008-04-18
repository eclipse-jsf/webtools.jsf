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
package org.eclipse.jst.pagedesigner.tools;

import java.util.Collections;

import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.graphics.Cursor;

/**
 * @author mengbo
 */
public class RangeDragTracker extends TargetingTool implements DragTracker {
	/** Flag to indicate selection has been performed. */
	private static final int FLAG_SELECTION_PERFORMED = TargetingTool.MAX_FLAG << 1;

	private EditPart editpart;

	/**
	 * Constructs a new SelectEditPartTracker with the given edit part as the
	 * source.
	 * 
	 * @param owner
	 *            the source edit part
	 */
	public RangeDragTracker(EditPart owner) {
		setSourceEditPart(owner);
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#calculateCursor()
	 */
	protected Cursor calculateCursor() {
        return Cursors.IBEAM;
//		if (isInState(STATE_INITIAL))
//        {
//            return Cursors.IBEAM;
//        }
//        else if (isInState(STATE_DRAG | STATE_ACCESSIBLE_DRAG))
//        {
//			return getDefaultCursor();
//		}
//        return Cursors.IBEAM;
//		return super.calculateCursor();
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
	 */
	protected String getCommandName() {
		return "Range Drag Tracker";//$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "Range Drag Tracker";//$NON-NLS-1$
	}

	/**
	 * Returns the source edit part.
	 * 
	 * @return the source edit part
	 */
	protected EditPart getSourceEditPart() {
		return editpart;
	}

	/**
	 * Performs a conditional selection if needed (if right or left mouse button
	 * have been pressed) and goes into the drag state. If any other button has
	 * been pressed, the tool goes into the invalid state.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		if (button == 3 && isInState(STATE_INITIAL)) {
			EditPart sourcePart = this.getSourceEditPart();
			IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) sourcePart
					.getViewer();
			if (viewer != null && viewer.isInRangeMode()) {
				DesignRange range = viewer.getRangeSelection();
				if (range != null && range.isValid()) {
					if (RangeUtil.intersect(range, sourcePart)) {
						return true;
					}
				}
			}
		}

		if ((button == 1 || button == 3) && isInState(STATE_INITIAL)) {
			peroformSelectionBegin();
		}

		if (button != 1) {
			setState(STATE_INVALID);
			if (button == 3)
				setState(STATE_TERMINAL);
			handleInvalidInput();
		} else {
			stateTransition(STATE_INITIAL, STATE_DRAG);
		}
		return true;
	}

	/**
	 * If the
	 * source is selected and there are no modifier keys pressed (i.e. the user
	 * isn't selecting multiple edit parts or deselecting edit parts), sets the
	 * direct edit flag so that when the mouse is released, a direct edit will
	 * be performed.
	 */
	protected void peroformSelectionBegin() {
		// if (getCurrentInput().isControlKeyDown())
		// {
		// // when control key is down, switch to object selection mode.
		// getHTMLGraphicalViewer().ensureObjectSelectionMode();
		// setFlag(FLAG_SELECTION_PERFORMED, true);
		// EditPartViewer viewer = getCurrentViewer();
		// List selectedObjects = viewer.getSelectedEditParts();
		//
		// if (selectedObjects.contains(getSourceEditPart()))
		// viewer.deselect(getSourceEditPart());
		// else
		// viewer.appendSelection(getSourceEditPart());
		// }
		// else
		if (getCurrentInput().isShiftKeyDown()) {
			getHTMLGraphicalViewer().ensureRangeSelectionMode();
			rangeSelection(true);
		} else {
			if (shouldStartRangeSelection()) {
				rangeSelection(false);
			} else {
				getCurrentViewer().select(getSourceEditPart());
			}
		}
	}

	/**
	 * If in the drag state, the tool selects the source edit part.  If the
	 * edit part is newly selected and not completely visible,
	 * {@link org.eclipse.gef.EditPartViewer#reveal(EditPart)}is called to show the selected
	 * edit part.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) {
		if (isInState(STATE_DRAG)) {
			// XXX: commented the following two line (lium)
			// performSelection();
			// if (button == 1 && getSourceEditPart().getSelected() !=
			// EditPart.SELECTED_NONE)
			// getCurrentViewer().reveal(getSourceEditPart());
			setState(STATE_TERMINAL);
			return true;
		}
		return false;
	}

	/**
	 * Calls {@link #performOpen()}if the double click was with mouse button 1.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleDoubleClick(int)
	 */
	protected boolean handleDoubleClick(int button) {
		if (button == 1) {
			performOpen();
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragStarted()
	 */
	protected boolean handleDragStarted() {
		return stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragInProgress()
	 */
	protected boolean handleDragInProgress() {
		if (getHTMLGraphicalViewer().isInRangeMode()) {
			rangeSelection(true);
			return true;
		}
        return super.handleDragInProgress();
	}

	/**
	 * Returns <code>true</code> if selection has already occured.
	 * 
	 * @return <code>true</code> if selection has occured
	 */
	protected boolean hasSelectionOccurred() {
		return getFlag(FLAG_SELECTION_PERFORMED);
	}

	/**
	 * Creates a {@link SelectionRequest}and sends it to the source edit part
	 * via {@link EditPart#performRequest(org.eclipse.gef.Request)}. Possible uses are to open
	 * the selected item in another editor or replace the current editor's
	 * contents based on the selected item.
	 */
	protected void performOpen() {
		SelectionRequest request = new SelectionRequest();
		request.setLocation(getLocation());
		request.setType(RequestConstants.REQ_OPEN);
		getSourceEditPart().performRequest(request);
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#resetFlags()
	 */
	protected void resetFlags() {
		super.resetFlags();
		setFlag(FLAG_SELECTION_PERFORMED, false);
	}

	/**
	 * Sets the source edit part.
	 * 
	 * @param part
	 *            the source edit part
	 */
	protected void setSourceEditPart(EditPart part) {
		this.editpart = part;
	}

	/**
	 * @return the html graphical viewer
	 */
	public IHTMLGraphicalViewer getHTMLGraphicalViewer() {
		return (IHTMLGraphicalViewer) getCurrentViewer();
	}

	/**
	 * @return
	 */
	private boolean shouldStartRangeSelection() {
		IPositionMediator positionMediator = new InlineEditingPositionMediator(
				new ActionData(ActionData.INLINE_EDIT, null));
		if (positionMediator.isEditable(new Target(getSourceEditPart()))) {
			return getSourceEditPart() instanceof TextEditPart
					|| !(((NodeEditPart) getSourceEditPart()).isWidget());
		}
        return false;
	}

	/**
	 * @param b
	 *            true means remain the old range start position.
	 */
	private void rangeSelection(boolean b) {
		// XXX: not using updateTargetEditPartUnderMouse. Maybe should. Don't
		// want to
		// go through the request mechanism, so simple implementation for now.
		
		//to avoid 219038 and possibility of current viewer changing
		final EditPartViewer viewer = getCurrentViewer();
		final IHTMLGraphicalViewer graphicalViewer = (IHTMLGraphicalViewer)viewer;
		EditPart editPart = viewer.findObjectAtExcluding(
				getLocation(), Collections.EMPTY_LIST);
		IPositionMediator positionMediator = new InlineEditingPositionMediator(
				new ActionData(ActionData.INLINE_EDIT, null));
		ExposeHelper exposeHelper = new ExposeHelper(graphicalViewer);
		exposeHelper.adjustVertical(getCurrentInput().getMouseLocation());
		DesignPosition position = EditPartPositionHelper.findEditPartPosition(
				editPart, getCurrentInput().getMouseLocation(),
				positionMediator);
		if (b) {
			graphicalViewer.setRangeEndPosition(position);
		} else {
			graphicalViewer.setRange(position, position);
		}
		graphicalViewer.updateHorizontalPos();
	}

    protected boolean handleHover() {
        boolean retValue = super.handleHover();
        refreshCursor();
        return retValue;
    }
}
