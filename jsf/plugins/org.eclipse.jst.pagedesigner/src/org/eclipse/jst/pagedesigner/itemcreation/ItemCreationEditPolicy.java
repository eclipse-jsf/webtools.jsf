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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class ItemCreationEditPolicy extends GraphicalEditPolicy {
	private RectangleFigure _feedbackFigure;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		if (request instanceof ItemCreationRequest) {
			ItemCreationRequest r = (ItemCreationRequest) request;
			DesignPosition position = EditPartPositionHelper
					.findEditPartPosition(getHost(), r.getLocation(),
							new DnDPositionValidator(new ActionData(
									ActionData.PALETTE_DND, request)));
			IDOMPosition domposition = null;
			if (position == null) {
				return null;
			} else {
				domposition = DOMPositionHelper.toDOMPosition(position);
			}
			if (domposition == null) {
				return null;
			}
			return new CreateItemCommand(
					PDPlugin
							.getResourceString("ItemCreationEditPolicy.CommandLabel.CreateItem"),//$NON-NLS-1$
					getViewer(getHost()), domposition, r.getItemDescriptor());
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
			ItemCreationRequest r = (ItemCreationRequest) request;
			DesignPosition position = EditPartPositionHelper
					.findEditPartPosition(getHost(), r.getLocation(),
							new DnDPositionValidator(new ActionData(
									ActionData.PALETTE_DND, request)));

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
	 * @return
	 */
	private IHTMLGraphicalViewer getViewer(EditPart host) {
		return (IHTMLGraphicalViewer) ((GraphicalEditPart) host).getViewer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		if (_feedbackFigure != null) {
			removeFeedback(_feedbackFigure);
			_feedbackFigure = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) {
		if (request instanceof ItemCreationRequest) {
			ItemCreationRequest r = (ItemCreationRequest) request;
			DesignPosition position = EditPartPositionHelper
					.findEditPartPosition(getHost(), r.getLocation(),
							new DnDPositionValidator(new ActionData(
									ActionData.PALETTE_DND, request)));

			if (position == null) {
				return;
			}
			Rectangle rect = EditPartPositionHelper
					.convertToAbsoluteCaretRect(position);
			showFeedbackRect(rect);
			if (getHost() instanceof GraphicalEditPart) {
				ExposeHelper exposeHelper = new ExposeHelper(
						getViewer(getHost()));
				exposeHelper.adjustVertical(r.getLocation());
			}
		}
	}

	protected RectangleFigure getFeedbackFigure() {
		if (_feedbackFigure == null) {
			_feedbackFigure = new RectangleFigure();
			_feedbackFigure.setFill(true);
			_feedbackFigure.setXOR(true);
			_feedbackFigure.setOutline(true);
			_feedbackFigure.setLineWidth(1);
			_feedbackFigure.setForegroundColor(ColorConstants.red);
			_feedbackFigure.setBounds(new Rectangle(0, 0, 0, 0));
			addFeedback(_feedbackFigure);
		}
		return _feedbackFigure;
	}

	protected void showFeedbackRect(Rectangle rect) {
		RectangleFigure pf = getFeedbackFigure();
		pf.translateToRelative(rect);
		pf.setBounds(rect);
	}
}
