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

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jst.pagedesigner.commands.CloneNodeCommand;
import org.eclipse.jst.pagedesigner.commands.MoveNodeCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class DragMoveEditPolicy extends GraphicalEditPolicy implements IDropRequestorProvider
{
    private List        _feedbackFigures;

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

		// System.out.println();
		// System.out.println("r.type = " + r.getType());
		// System.out.println("Host: " + hostNode);
		// System.out.println("Dragged: " + draggedNode);

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

	protected final DesignPosition findPosition(ChangeBoundsRequest r) {
		final IPositionMediator mediator = getDropChildValidator(r);
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

    /**
     * @param r
     * @return the validator to be used to validate the 'request' to drop
     * the edit parts specified by 'r' into this policy's host edit part
     * MUST NOT RETURN NULL
     */
    protected final IPositionMediator getDropChildValidator(ChangeBoundsRequest r)
    {
        IPositionMediator mediator = createDropChildValidator(r);
        
        if (mediator == null)
        {
            mediator = createDefaultDropChildValidator(r);
        }
       
        return mediator;
    }
    
    /**
     * @param r
     * @return a mediator that can validate valid model drops into the
     * host's edit part
     */
    protected IPositionMediator createDropChildValidator(ChangeBoundsRequest r)
    {
        // sub-class may override to customize the drop container validator
        return null;
    }
    
    protected final IPositionMediator createDefaultDropChildValidator(ChangeBoundsRequest r)
    {
        return new DnDPositionValidator(new ActionData(
                ActionData.COMPONENT_MOVE, r.getEditParts()));
    }
    
    protected final IDropLocationStrategy createDropLocationStrategy(ChangeBoundsRequest r)
    {
        List requestingParts = r.getEditParts();
        
        // TODO: support a composite strategy can collect all requesting parts
        if (requestingParts.size() == 1)
        {
            EditPart requestPart = (EditPart) requestingParts.get(0);
            IDropRequestorProvider strategyProvider = 
                (IDropRequestorProvider) requestPart.getAdapter(IDropRequestorProvider.class);
            
            if (strategyProvider != null)
            {
                IDropLocationStrategy strategy = 
                    strategyProvider.getDropRequestorLocationStrategy(r);
                
                if (strategy != null)
                {
                    return strategy;
                }
            }

        }
        
        // by default, return the default strategy
        return new DefaultDropLocationStrategy(getHost());
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public final void eraseTargetFeedback(Request request) {
        if (_feedbackFigures != null)
        {
            for (final Iterator it = _feedbackFigures.iterator(); it.hasNext();)
            {
                final IFigure figure = (IFigure) it.next();
                
                if (figure != null)
                {
                    removeFeedback(figure);
                }
            }
            
            _feedbackFigures.clear();
            _feedbackFigures = null;
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public final void showTargetFeedback(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest r = (ChangeBoundsRequest) request;
			
			Object type = r.getType();
			if (type != REQ_ADD && type != REQ_CLONE
					&& type != REQ_MOVE_CHILDREN && type != REQ_MOVE) {
				return;
			}

            DesignPosition position = findPosition(r);
			if (position != null) {
                // erase any prior feedback
                eraseTargetFeedback(request);
                // add figures to feedback layer and save them in _feedbackFigures
                // for later.
                _feedbackFigures = createDropLocationStrategy(r).showTargetFeedback(getHost(), position, r); 
			}
		}
	}

    public IDropLocationStrategy getDropRequestorLocationStrategy(
            Request request) 
    {
        // by default, always return null.  Sub-classes should override
        // to customize their drop request strategy
        return null;
    }
}
