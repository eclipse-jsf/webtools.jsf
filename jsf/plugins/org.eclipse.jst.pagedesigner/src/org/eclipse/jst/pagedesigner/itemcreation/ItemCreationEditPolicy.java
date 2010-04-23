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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editpolicies.DropEditPolicy;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData.DropData;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class ItemCreationEditPolicy extends DropEditPolicy 
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		if (request instanceof ItemCreationRequest) {
			ItemCreationRequest r = (ItemCreationRequest) request;
			DesignPosition position = findPosition((ItemCreationRequest)request);
            
			if (position == null) {
				return null;
			}
            IDOMPosition domposition = DOMPositionHelper.toDOMPosition(position);
			if (domposition == null) {
				return null;
			}
//			TagToolCreationAdapter tagToolCreationAdapter = new TagToolCreationAdapter(r.getTagToolPaletteEntry(), getViewer(getHost()).getModel());
			return new CreateItemCommand(
					PDPlugin
							.getResourceString("ItemCreationEditPolicy.CommandLabel.CreateItem"),//$NON-NLS-1$
					getViewer(getHost()).getModel(), domposition, r.getTagCreationProvider());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof ItemCreationRequest) {
			DesignPosition position = findPosition((ItemCreationRequest)request);

			if (position == null) {
				return null;
			}

			EditPart container = position.getContainerPart();
			return container;
		}
		return null;
	}

	/**
	 * @param host
	 * @return the graphical viewer
	 */
	protected IHTMLGraphicalViewer getViewer(EditPart host) {
		return (IHTMLGraphicalViewer) ((GraphicalEditPart) host).getViewer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) 
    {
        if (request instanceof DropRequest 
                && request.getType() == ItemCreationRequest.REQ_ITEM_CREATION)
        {
            super.showTargetFeedback(request);
            if (getHost() instanceof GraphicalEditPart) {
                ExposeHelper exposeHelper = new ExposeHelper(
                        getViewer(getHost()));
                exposeHelper.adjustVertical(((DropRequest)request).getLocation());
            }
        }
	}

	protected DesignPosition findPosition(DropRequest request) {
        final IPositionMediator mediator = getDropChildValidator(request);
        if (mediator == null)
        {
            return null;
        }
        
        final IDropLocationStrategy dropStrategy = createDropLocationStrategy(request);
        final DesignPosition position = 
            dropStrategy.calculateDesignPosition(getHost(), request.getLocation(), mediator);
        
        // verify that the drop strategy has honoured it's contract that our
        // mediator be respected
        if (position != null)
        {
            if (!mediator.isValidPosition(position))
            {
                // if our mediator says no go, then veto the requestor
                // there is no drop location
                return null;
            }
        }
        return position;
    }

    protected final IPositionMediator createDefaultDropChildValidator(DropData data) {
        return new DnDPositionValidator(new DropActionData(
                ActionData.PALETTE_DND, data));    }
}
