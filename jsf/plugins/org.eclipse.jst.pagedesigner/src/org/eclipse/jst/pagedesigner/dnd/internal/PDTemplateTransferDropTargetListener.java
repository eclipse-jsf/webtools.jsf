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
package org.eclipse.jst.pagedesigner.dnd.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.DropCustomizationController;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;

/**
 * @author mengbo
 * @version 1.5
 */
public class PDTemplateTransferDropTargetListener extends
		AbstractTransferDropTargetListener {

	/**
	 * @param viewer
	 */
	public PDTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer, TemplateTransfer.getInstance());
	}

	/**
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		ItemCreationRequest request = new ItemCreationRequest();
		/*TODO: nothing is done with this var.  Does the method have a side-effect? TagToolPaletteEntry tagItem =*/TemplateTransfer.getInstance().getTemplate();
		return request;
	}

	/**
	 * A helper method that casts the target Request to a CreateRequest.
	 * 
	 * @return CreateRequest
	 */
	protected final Request getCreateRequest() {
		return getTargetRequest();
	}

	/**
	 * The purpose of a template is to be copied. Therefore, the drop operation
	 * can't be anything but <code>DND.DROP_COPY</code>.
	 * 
	 * @see AbstractTransferDropTargetListener#handleDragOperationChanged()
	 */
	protected void handleDragOperationChanged() {
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOperationChanged();
	}

	/**
	 * The purpose of a template is to be copied. Therefore, the Drop operation
	 * is set to <code>DND.DROP_COPY</code> by default.
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDragOver()
	 */
	protected void handleDragOver() {
		getCurrentEvent().detail = DND.DROP_COPY;
		getCurrentEvent().feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND;
		super.handleDragOver();
	}

	/**
	 * Assumes that the target request is a {@link org.eclipse.gef.requests.CreateRequest}.
	 */
	protected void updateTargetRequest() {
		// We should never see request that is not of ItemCreationRequest type,
		// but there is an error case that Eclipse send out other types
		// requests.
		// Add this test to avoid potential error.
		if (getCreateRequest() instanceof ItemCreationRequest) {
			ItemCreationRequest request = (ItemCreationRequest) getCreateRequest();
            Object transferObj = TemplateTransfer.getInstance().getObject();
            
            if (transferObj instanceof IDropSourceData)
            {
                request.setTagCreationProvider((IDropSourceData) transferObj);
                request.setLocation(getDropLocation());
            }
            else
            {
                PDPlugin.getLogger(this.getClass()).error("Unexpected transfer object on palette drag:"+transferObj, new Throwable("Artificial throwable for stack tracing")); //$NON-NLS-1$ //$NON-NLS-2$
            }
		}
	}

	public void drop(DropTargetEvent event) {
		if (getCreateRequest() instanceof ItemCreationRequest) {
			getViewer().getControl().setFocus();
			super.drop(event);
		}
	}

    @Override
    protected void handleDrop()
    {
        // copied from AbstractTransferDropListener and modified for drop 
        // customization
        updateTargetRequest();
        updateTargetEditPart();

        if (getTargetEditPart() != null) {
            Command command = getCommand();
            if (command != null && command.canExecute())
                if (customizeAndCheckExecute(command))
                {
                    getViewer().getEditDomain().getCommandStack().execute(command);
                }
            else
                getCurrentEvent().detail = DND.DROP_NONE;
        } else
            getCurrentEvent().detail = DND.DROP_NONE;
    }

    private boolean customizeAndCheckExecute(final Command command)
    {
        if (command instanceof CreateItemCommand)
        {
            final ItemCreationRequest request = (ItemCreationRequest) getCreateRequest();
            final CreateItemCommand createCommand = (CreateItemCommand) command;
            final IStatus status  = 
                new DropCustomizationController(createCommand, request.getTagCreationProvider(), createCommand.getDocument(), createCommand.getPosition())
                    .performCustomization();
            
            return status.getSeverity() == IStatus.OK;
        }
        // don't block a drop if the command is not customizable
        return true;
    }

}
