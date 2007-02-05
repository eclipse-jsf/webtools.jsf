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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.commands.CloneNodeCommand;
import org.eclipse.jst.pagedesigner.commands.MoveNodeCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData.DropData;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class DragMoveEditPolicy extends DropEditPolicy
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public final Command getCommand(Request request) {
		if (!(request instanceof ChangeBoundsRequest)) {
			return null;
		}

		ChangeBoundsRequest r = (ChangeBoundsRequest) request;

		// we only support move/copy a single node.
		if (!MoveSupport.isSingleNode(r)) {
			return UnexecutableCommand.INSTANCE;
		}

		// the edit policy only handle at the target part, so only care about
		// the
		// target part request.
		final Object type = r.getType();
		if (type != REQ_ADD && type != REQ_CLONE && type != REQ_MOVE_CHILDREN) {

			return null;
		}

		Node draggedNode = MoveSupport.getDraggedNode(r);
		Node hostNode = ((NodeEditPart) getHost()).getIDOMNode();

		if (DOMUtil.isAncester(draggedNode, hostNode)) {
			return UnexecutableCommand.INSTANCE;
		}

		DesignPosition position = findPosition(r);
		if (position == null || !position.isValid()) {
			return null;
		}

		// can't move/copy into self.
		Node node = position.getContainerNode();
		if (DOMUtil.isAncester(draggedNode, node)) {
			return UnexecutableCommand.INSTANCE;
		}

		// ok, we are about to move/copy into the specified position.
		IDOMPosition domposition = DOMPositionHelper.toDOMPosition(position);

		if (REQ_CLONE.equals(type)) {
			return new CloneNodeCommand((IHTMLGraphicalViewer) getHost()
					.getViewer(), domposition, draggedNode);
		}
        return new MoveNodeCommand((IHTMLGraphicalViewer) getHost()
        		.getViewer(), domposition, draggedNode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			return this.getHost();
		}
		return super.getTargetEditPart(request);
	}

	protected final DesignPosition findPosition(DropRequest r) {
		final IPositionMediator mediator = getDropChildValidator(r);
        if (mediator == null)
        {
            return null;
        }
        final IDropLocationStrategy dropStrategy = createDropLocationStrategy(r);
		final DesignPosition position = 
            dropStrategy.calculateDesignPosition(getHost(), r.getLocation(), mediator);
        
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

    public void showTargetFeedback(Request request) 
    {
        Object type = request.getType();
        // only show feedback for these request types
        if (type == REQ_ADD || type == REQ_CLONE
                || type == REQ_MOVE_CHILDREN || type == REQ_MOVE) {
            super.showTargetFeedback(request);
        }
    }

    protected final IPositionMediator createDefaultDropChildValidator(DropData r)
    {
        return new DnDPositionValidator(new DropActionData(
                ActionData.COMPONENT_MOVE, r));
    }
}
