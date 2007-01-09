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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class DragMoveEditPolicy extends GraphicalEditPolicy {
    // the amount of vertical offset below the mouse pointer to place
    // the upper left of the drop hint tooltip
	private static final int DROP_HINT_VERTICAL_OFFSET = 20;
    private RectangleFigure _feedbackFigure;
    private Label           _dropHintLabel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
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

	DesignPosition findPosition(ChangeBoundsRequest r) {
		IPositionMediator mediator = new DnDPositionValidator(new ActionData(
				ActionData.COMPONENT_MOVE, r.getEditParts()));
		DesignPosition position = EditPartPositionHelper.findEditPartPosition(
				getHost(), r.getLocation(), mediator);
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		if (_feedbackFigure != null)
//                && getFeedbackLayer().getChildren().contains(_feedbackFigure)) 
        {
			removeFeedback(_feedbackFigure);
			_feedbackFigure = null;
		}
        
        if (_dropHintLabel != null)
//                && getFeedbackLayer().getChildren().contains(_dropHintLabel))
        {
            removeFeedback(_dropHintLabel);
            _dropHintLabel = null;
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest r = (ChangeBoundsRequest) request;

			Object type = r.getType();
			if (type != REQ_ADD && type != REQ_CLONE
					&& type != REQ_MOVE_CHILDREN) {
				return;
			}
			//TODO: not used EditPart host = getHost();
			DesignPosition position = findPosition(r);
			if (position != null) {
				Rectangle rect = EditPartPositionHelper
						.convertToAbsoluteCaretRect(position);

				// to avoid enlarge feedback pane.
				rect = rect.intersect(getFeedbackLayer().getBounds());
				showFeedbackRect(rect);
                showDropHintLabel(r.getLocation(), position);
			}
		}
	}

	protected RectangleFigure getFeedbackFigure() {
		if (this._feedbackFigure == null) {
			_feedbackFigure = new RectangleFigure();
			_feedbackFigure.setFill(true);
			_feedbackFigure.setOutline(true);
			_feedbackFigure.setLineWidth(1);
			_feedbackFigure.setForegroundColor(ColorConstants.red);
			_feedbackFigure.setBounds(new Rectangle(0, 0, 0, 0));
			_feedbackFigure.setXOR(true);
			addFeedback(_feedbackFigure);
		}
		return _feedbackFigure;
	}

	protected void showFeedbackRect(Rectangle rect) {
		RectangleFigure pf = getFeedbackFigure();
		pf.translateToRelative(rect);
		pf.setBounds(rect);
	}

    /**
     * Shows a label in a position relative to the drop marker
     * that hints where the new component will be dropped in
     * respect of components already there
     */
    protected void showDropHintLabel(Point mousePosition, DesignPosition position)
    {
        if (_dropHintLabel == null){
            _dropHintLabel = new Label();
            _dropHintLabel.setOpaque(true);
            _dropHintLabel.setBackgroundColor(ColorConstants.tooltipBackground);
            _dropHintLabel.setBorder(
                    new LineBorder(ColorConstants.black, 1)
                    {
                        // add an extra pixel of inset to make sure the text
                        // isn't pressed against the border
                        public Insets getInsets(IFigure figure) {
                            return new Insets(getWidth()+1);
                        }
                    }
            );
            addFeedback(_dropHintLabel);
        }
        final String hintText = getDropHintText(position);
        _dropHintLabel.setText(hintText);
        //TODO: need to handle viewport clipping and adjust label location appropriately
        Dimension hintSize = _dropHintLabel.getPreferredSize();
        Point hintLocation = new Point(mousePosition.x, mousePosition.y+DROP_HINT_VERTICAL_OFFSET);
        
        Rectangle hintRect = new Rectangle(hintLocation, hintSize);

        // to avoid enlarge feedback pane.
        //hintRect = hintRect.intersect(getFeedbackLayer().getBounds());
        _dropHintLabel.setBounds(hintRect);
    }
    
    private String getDropHintText(DesignPosition position)
    {
        StringBuffer buffer = new StringBuffer("Place");
        
        EditPart prevPart = position.getSiblingEditPart(false);
        EditPart nextPart = position.getSiblingEditPart(true);

        if (nextPart instanceof ElementEditPart)
        {
            buffer.append(" before ");
            buffer.append(((ElementEditPart)nextPart).getTagConvert().getHostElement().getNodeName());
            buffer.append(",");
        }
        
        if (prevPart instanceof ElementEditPart)
        {
            buffer.append(" after ");
            buffer.append(((ElementEditPart)prevPart).getTagConvert().getHostElement().getNodeName());
            buffer.append(",");
        }
        
        buffer.append(" inside ");
        buffer.append(position.getContainerNode().getNodeName());
        
        return buffer.toString();
    }
}
