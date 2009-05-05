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
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.DropCustomizationController;
import org.eclipse.swt.graphics.Cursor;

/**
 * This Tool is used to create items. It is to replace the default CreationTool
 * of GEF. We are not using CreationToolEntry for creating item, since the
 * default GEF implementation require creating of the object before drop into
 * the view. We do not want to create the XML element (and possibly its taglib
 * declaration) before the drop is really performed.)
 * 
 * @author mengbo
 */
public class ItemCreationTool extends TargetingTool {
    private final ITagDropSourceData _tagDropSourceData; //can we get rid of this?

	/**
	 * Default constructor. Sets the default and disabled cursors.
	 * @param tagDropSourceData 
	 */
	public ItemCreationTool(ITagDropSourceData tagDropSourceData) {
		setDefaultCursor(SharedCursors.CURSOR_TREE_ADD);
		setDisabledCursor(SharedCursors.NO);

		this._tagDropSourceData = tagDropSourceData;  
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#calculateCursor()
	 */
	protected Cursor calculateCursor() {
		/*
		 * Fix for Bug# 66010 The following two lines of code were added for the
		 * case where a tool is activated via the keyboard (that code hasn't
		 * been released yet). However, they were causing a problem as described
		 * in 66010. Since the keyboard activation code is not being released
		 * for 3.0, the following lines are being commented out.
		 */
		// if (isInState(STATE_INITIAL))
		// return getDefaultCursor();
		return super.calculateCursor();
	}

	/**
	 * Creates a {@link ItemCreationRequest}and sets this tool's factory on the
	 * request.
	 * 
	 * @see org.eclipse.gef.tools.TargetingTool#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		ItemCreationRequest request = new ItemCreationRequest();
		request.setTagCreationProvider(_tagDropSourceData);
		return request;
	}

	/**
	 * @see org.eclipse.gef.Tool#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		// TODO: never read helper = null;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
	 */
	protected String getCommandName() {
		return ItemCreationRequest.REQ_ITEM_CREATION;
	}

	/**
	 * Cast the target request to a CreateRequest and returns it.
	 * 
	 * @return the target request as a CreateRequest
	 * @see TargetingTool#getTargetRequest()
	 */
	protected ItemCreationRequest getCreateRequest() {
		return (ItemCreationRequest) getTargetRequest();
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "Item Creation Tool";//$NON-NLS-1$
	}

	/**
	 * The creation tool only works by clicking mouse button 1 (the left mouse
	 * button in a right-handed world). If any other button is pressed, the tool
	 * goes into an invalid state. Otherwise, it goes into the drag state,
	 * updates the request's location and calls
	 * {@link TargetingTool#lockTargetEditPart(org.eclipse.gef.EditPart)}with the edit part
	 * that was just clicked on.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		if (button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
			return true;
		}
		if (stateTransition(STATE_INITIAL, STATE_DRAG)) {
			if (getTargetEditPart() != null) {
				getCreateRequest().setLocation(getLocation());
				lockTargetEditPart(getTargetEditPart());
				// Snap only when size on drop is employed
				// TODO: never read helper = (SnapToHelper) getTargetEditPart().getAdapter(
				//SnapToHelper.class);
			}
		}
		return true;
	}

	/**
	 * If the tool is currently in a drag or drag-in-progress state, it goes
	 * into the terminal state, performs some cleanup (erasing feedback,
	 * unlocking target edit part), and then calls {@link #performCreation(int)}.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) 
	{
		if (stateTransition(STATE_DRAG | STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) 
		{
			eraseTargetFeedback();
			unlockTargetEditPart();
			
			// customizer may cancel the drop
			customizeDropAndMaybeExecute(button);
		}
		
		setState(STATE_TERMINAL);
		handleFinished();

		return true;
	}

	/**
     * @param button
     */
    protected void customizeDropAndMaybeExecute(final int button)
    {
        Command command = getCurrentCommand();

        IStatus status = Status.OK_STATUS;
        if (command instanceof CreateItemCommand)
        {
            status = new DropCustomizationController((CreateItemCommand) command,
                    _tagDropSourceData, 
                    ((CreateItemCommand)command).getDocument(),
                    ((CreateItemCommand)command).getPosition()).
                performCustomization();
        }

        if (status.getSeverity() == IStatus.OK)
        {
            performCreation(button);
        }
    }


    /**
	 * Updates the request, sets the current command, and asks to show feedback.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragInProgress()
	 */
	protected boolean handleDragInProgress() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			updateTargetRequest();
			setCurrentCommand(getCommand());
			showTargetFeedback();
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragStarted()
	 */
	protected boolean handleDragStarted() {
		return stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
	}

	/**
	 * If the user is in the middle of creating a new edit part, the tool erases
	 * feedback and goes into the invalid state when focus is lost.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleFocusLost()
	 */
	protected boolean handleFocusLost() {
		if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
			eraseTargetFeedback();
			setState(STATE_INVALID);
			handleFinished();
			return true;
		}
		return false;
	}

	/**
	 * @see org.eclipse.gef.tools.TargetingTool#handleHover()
	 */
	protected boolean handleHover() {
		if (isInState(STATE_INITIAL))
			updateAutoexposeHelper();
		return true;
	}

	/**
	 * Updates the request and mouse target, gets the current command and asks
	 * to show feedback.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleMove()
	 */
	protected boolean handleMove() {
		updateTargetRequest();
		updateTargetUnderMouse();
		setCurrentCommand(getCommand());
		showTargetFeedback();
		return true;
	}

	/**
	 * Executes the current command and selects the newly created object. The
	 * button that was released to cause this creation is passed in, but since
	 * {@link #handleButtonDown(int)}goes into the invalid state if the button
	 * pressed is not button 1, this will always be button 1.
	 * 
	 * @param button
	 *            the button that was pressed
	 */
	protected void performCreation(int button) {
		executeCurrentCommand();
		// selectAddedObject();
	}

	// /*
	// * Add the newly created object to the viewer's selected objects.
	// */
	// private void selectAddedObject() {
	// final Object model = getCreateRequest().getNewObject();
	// if (model == null)
	// return;
	// EditPartViewer viewer = getCurrentViewer();
	// Object editpart = viewer.getEditPartRegistry().get(model);
	// if (editpart instanceof EditPart) {
	// viewer.flush();
	// viewer.select((EditPart)editpart);
	// }
	// }

	/**
	 * Sets the location (and size if the user is performing size-on-drop) of
	 * the request.
	 * 
	 * @see org.eclipse.gef.tools.TargetingTool#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		ItemCreationRequest req = getCreateRequest();
		req.setLocation(getLocation());
		// if (isInState(STATE_DRAG_IN_PROGRESS)) {
		// Point loq = getStartLocation();
		// req.setLocation(bounds.getLocation());
		// req.getExtendedData().clear();
		// if (!getCurrentInput().isAltKeyDown() && helper != null) {
		// PrecisionRectangle baseRect = new PrecisionRectangle(bounds);
		// PrecisionRectangle result = baseRect.getPreciseCopy();
		// helper.snapRectangle(req, PositionConstants.NSEW,
		// baseRect, result);
		// req.setLocation(result.getLocation());
		// req.setSize(result.getSize());
		// }
		// } else {
		// req.setLocation(getLocation());
		// }
	}

}
