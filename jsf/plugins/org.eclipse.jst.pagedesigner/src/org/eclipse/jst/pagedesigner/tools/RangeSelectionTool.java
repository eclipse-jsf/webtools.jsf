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

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.commands.DeleteNodeCommand;
import org.eclipse.jst.pagedesigner.commands.SwitchSelectionCommand;
import org.eclipse.jst.pagedesigner.commands.nav.HorizontalMoveCommand;
import org.eclipse.jst.pagedesigner.commands.nav.ICaretPositionMover;
import org.eclipse.jst.pagedesigner.commands.nav.VerticalMoveCommand;
import org.eclipse.jst.pagedesigner.commands.range.CopyCommand;
import org.eclipse.jst.pagedesigner.commands.range.CutCommand;
import org.eclipse.jst.pagedesigner.commands.range.DeleteCommand;
import org.eclipse.jst.pagedesigner.commands.range.InsertCommand;
import org.eclipse.jst.pagedesigner.commands.range.KeyboardData;
import org.eclipse.jst.pagedesigner.commands.range.PasteCommand;
import org.eclipse.jst.pagedesigner.commands.range.SelectAllCommand;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.requests.LocationModifierRequest;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;

/**
 * @author mengbo
 */
public class RangeSelectionTool extends SelectionTool 
{
	private LocationRequest _hoverRequest;
    

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SelectionTool#handleKeyDown(org.eclipse.swt.events.KeyEvent)
	 */
	protected boolean handleKeyDown(KeyEvent e) {
		// resetHover() is not visible.
		if (isHoverActive()) {
			handleHoverStop();
		}
		setHoverActive(false);

		if ((e.stateMask & SWT.ALT) != 0) {
			return false;
		}
		Command command = null;
		KeyboardData keyCode = null;
		switch (e.keyCode) {
		case SWT.F2:
			command = new SwitchSelectionCommand((IHTMLGraphicalViewer) this
					.getCurrentViewer());

			break;
		case SWT.ARROW_UP:
			command = new VerticalMoveCommand((IHTMLGraphicalViewer) this
					.getCurrentViewer(), true, (e.stateMask & SWT.SHIFT) != 0);
			break;
		case SWT.ARROW_DOWN:
			command = new VerticalMoveCommand((IHTMLGraphicalViewer) this
					.getCurrentViewer(), false, (e.stateMask & SWT.SHIFT) != 0);
			break;
		case SWT.ARROW_LEFT:
			command = new HorizontalMoveCommand((IHTMLGraphicalViewer) this
					.getCurrentViewer(), false, (e.stateMask & SWT.SHIFT) != 0);
			break;
		case SWT.ARROW_RIGHT:
			command = new HorizontalMoveCommand((IHTMLGraphicalViewer) this
					.getCurrentViewer(), true, (e.stateMask & SWT.SHIFT) != 0);
			break;
		case SWT.DEL:
			if ((e.stateMask & SWT.SHIFT) == 0) {
				if (getCurrentViewer().getSelection() != null) {
					ISelection selection = getCurrentViewer().getSelection();
					if (selection instanceof StructuredSelection) {
						Object object = ((StructuredSelection) selection)
								.getFirstElement();
						if (!(object instanceof DocumentEditPart)) {
							// "delete node"
							command = new DeleteNodeCommand(
									(IHTMLGraphicalViewer) getCurrentViewer());
						}
					}
				}
				if (command == null) {
					// "delete"
					command = new DeleteCommand(true,
							(IHTMLGraphicalViewer) this.getCurrentViewer());
				}
			} else {
				// "cut"
				command = new CutCommand((IHTMLGraphicalViewer) this
						.getCurrentViewer());
				e.doit = false;
			}
			break;
		case SWT.BS:
			// "delete"
			command = new DeleteCommand(false, (IHTMLGraphicalViewer) this
					.getCurrentViewer());
			e.doit = false;
			break;
		case SWT.INSERT:
			if ((e.stateMask & SWT.SHIFT) != 0) {
				// "paste"
				command = new PasteCommand((IHTMLGraphicalViewer) this
						.getCurrentViewer());
				e.doit = false;
				break;
			} else if ((e.stateMask & SWT.CONTROL) != 0) {
				// "copy"
				command = new CopyCommand((IHTMLGraphicalViewer) this
						.getCurrentViewer());
				e.doit = false;
				break;
			}
			break;
		case SWT.LF:
		case SWT.CR:
			// "insert"
			keyCode = new KeyboardData(e.character, e.stateMask,
					(IHTMLGraphicalViewer) getCurrentViewer());
			command = new InsertCommand(
					PageDesignerResources.getInstance().getString(
							"RangeSelectionTool.CommandLabel.Insert"), (IHTMLGraphicalViewer) this.getCurrentViewer(), keyCode); //$NON-NLS-1$
			e.doit = false;
			break;
		default:
			if (e.keyCode == 'a' && (e.stateMask & SWT.CTRL) != 0) {
				command = new SelectAllCommand("selectAll", //$NON-NLS-1$
						(IHTMLGraphicalViewer) this.getCurrentViewer());
				e.doit = false;
			} else {
				if (getCurrentViewer() instanceof IHTMLGraphicalViewer
						&& ((IHTMLGraphicalViewer) getCurrentViewer())
								.isInRangeMode()
						&& (!Character.isIdentifierIgnorable(e.character) && !Character
								.isISOControl(e.character))
						|| (e.character == '\r')) {
					keyCode = new KeyboardData(e.character, e.stateMask,
							(IHTMLGraphicalViewer) getCurrentViewer());
					// "insert"
					command = new InsertCommand(
							PageDesignerResources.getInstance().getString(
									"RangeSelectionTool.CommandLabel.Insert"), (IHTMLGraphicalViewer) this.getCurrentViewer(), keyCode); //$NON-NLS-1$
					e.doit = false;
					break;
				}
                return super.handleKeyDown(e);
			}
		}
		if (command != null) {
			command.execute();
			e.doit = false;
			if (command instanceof ICaretPositionMover) {
				if (getCurrentViewer() instanceof IHTMLGraphicalViewer) {
					((IHTMLGraphicalViewer) getCurrentViewer())
							.updateHorizontalPos();
				}
			}
			return true;
		}
		return false;
	}

	// /**
	// * @param e
	// * @return
	// */
	// protected boolean handleRangeModeKeyDown(KeyEvent e)
	// {
	// if (e.keyCode == SWT.SHIFT || e.keyCode == SWT.CONTROL || e.keyCode ==
	// SWT.ALT)
	// {
	// return false;
	// }
	//
	// dumpKey(e);
	// Command command = null;
	// switch (e.keyCode)
	// {
	// case SWT.ARROW_LEFT:
	// command = new HorizontalMoveCommand((IHTMLGraphicalViewer)
	// this.getCurrentViewer(), false,
	// (e.stateMask & SWT.SHIFT) != 0);
	// break;
	// case SWT.ARROW_RIGHT:
	// command = new HorizontalMoveCommand((IHTMLGraphicalViewer)
	// this.getCurrentViewer(), true,
	// (e.stateMask & SWT.SHIFT) != 0);
	// break;
	//
	// }
	// if (command != null)
	// {
	// command.execute();
	// return true;
	// }
	//
	// char content = e.character;
	//
	// // when reach here, should be standard content keys.
	// return handleRangeModeContentChar(content);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SelectionTool#createHoverRequest()
	 */
	protected void createHoverRequest() {
		this._hoverRequest = new LocationModifierRequest();
		_hoverRequest.setType(RequestConstants.REQ_SELECTION_HOVER);
	}

	protected Request getTargetHoverRequest() {
		if (_hoverRequest == null) {
			createHoverRequest();
		}
		return _hoverRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SelectionTool#updateHoverRequest()
	 */
	protected void updateHoverRequest() {
		LocationModifierRequest request = (LocationModifierRequest) getTargetHoverRequest();
		request.setLocation(getLocation());
		request.setControlKeyPressed(getCurrentInput().isControlKeyDown());
	}

    /* 
     * If the target edit part is a NodeEditPart, then inform it if of the current
     * drag status before calling its hover feedback
     */
    protected void showHoverFeedback() 
    {
        if (getTargetEditPart() instanceof NodeEditPart)
        {
            ((NodeEditPart)getTargetEditPart())
                .setDragActive(
                        isInState(STATE_DRAG_IN_PROGRESS 
                                | STATE_ACCESSIBLE_DRAG_IN_PROGRESS 
                                | STATE_DRAG));
        }
        super.showHoverFeedback();
    }

    // TODO : this method is for debug purposes and should
    // be removed in production
//    protected boolean updateTargetUnderMouse() {
//        EditPart editPart = getTargetEditPart();
//        boolean retValue =  super.updateTargetUnderMouse();
//        if (getTargetEditPart() != editPart)
//        {
//            System.out.println("New target editpart:  "+getTargetEditPart()+" Old edit part: "+editPart);
//        }
//        return retValue;
//    }

    protected boolean handleMove() {
        boolean handled =  super.handleMove();
        EditPart targetEditPart = getTargetEditPart();
        
        if (isInState(STATE_INITIAL)
                && targetEditPart instanceof NodeEditPart)
        {
            LocationRequest request = new LocationRequest(org.eclipse.jst.pagedesigner.requests.PageDesignerRequestConstants.REQ_SELECTION_TRACKER);
            request.setLocation(getLocation());
            DragTracker selectionTracker = targetEditPart.getDragTracker(request);
            setDragTracker(selectionTracker);
        }
        
        return handled;
    }

    protected Cursor calculateCursor() {
        EditPart targetEditPart = getTargetEditPart();
        if (isInState(STATE_INITIAL)
               && targetEditPart instanceof NodeEditPart)
        {
            final Cursor  nodeCursor = 
                ((NodeEditPart)targetEditPart).getCursor(getLocation());
            
            // if the edit part specified a custom cursor and there is no
            // active drag tracker (which would otherwise manage cursor)
            // then set the custom cursor
            // if we fall-through, the default behaviour will be used
            if (nodeCursor != null
                    && getDragTracker() == null)
            {
                return nodeCursor;
            }
        }
        // otherwise, use super's defaults
        return super.calculateCursor();
    }
}
