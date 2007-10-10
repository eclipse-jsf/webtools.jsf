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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.jst.pagedesigner.dnd.FeedBackInfo;
import org.eclipse.jst.pagedesigner.dnd.ILocalDropHandler;
import org.eclipse.jst.pagedesigner.dnd.LocalDropRequest;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editpolicies.LocationHelper;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * This is the editpolicy to handle LocalSelectionDrop
 * 
 * @author mengbo
 */
public class LocalDropEditPolicy extends GraphicalEditPolicy {
	private RectangleFigure _feedbackFigure;

	// indicate whether update is checked
	private static boolean _checkUpdate = true;

	/**
	 * @param widgetEditPart
	 * @param request
	 * @param results
	 * @return true if should update widget
	 */
	public boolean checkUpdateWidget(EditPart widgetEditPart,
			LocalDropRequest request, Map results) {
		if (!(widgetEditPart instanceof ElementEditPart)) {
			return false;
		}
		Node widget = ((ElementEditPart) widgetEditPart).getIDOMNode();
		ILocalDropHandler[] handlers = RegistryReader.getAllHandlers();
		Object localData = request.getLocalObject();
		for (int i = 0; i < handlers.length; i++) {
			FeedBackInfo feedback = handlers[i].supportUpdateWidget(localData,
					widget);
			if (feedback != null) {
				results.put(feedback, handlers[i]);
			}
		}
		return !results.isEmpty();
	}

	/**
	 * @param request
	 * @param host
	 * @param results
	 * @param dpHolder
	 * @param position
	 * @return true if should insert element
	 */
	public boolean checkInsertElement(LocalDropRequest request,
			EditPart[] host, Map results, DesignPosition[] dpHolder,
			IDOMPosition[] position) {
		DesignPosition designPosition = EditPartPositionHelper
		    .findEditPartPosition(host[0], request.getLocation(),
		        new DnDPositionValidator(new ActionData(
		            ActionData.DATABINDING_DND, request)));
		dpHolder[0] = designPosition;
		if (designPosition == null) {
			return false;
		}

		host[0] = designPosition.getContainerPart();
		position[0] = DOMPositionHelper.toDOMPosition(designPosition);
		ILocalDropHandler[] handlers = RegistryReader.getAllHandlers();
		Object localData = request.getLocalObject();
		for (int i = 0; i < handlers.length; i++) {
			FeedBackInfo feedback = handlers[i].supportInsertElements(
					localData, position[0]);
			if (feedback != null) {
				results.put(feedback, handlers[i]);
			}
		}
		return !results.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof LocalDropRequest) {
			LocalDropRequest r = (LocalDropRequest) request;
			EditPart host = getHost();
			if (_checkUpdate && checkUpdateWidget(host, r, new HashMap())) {
				return host;
			}
			EditPart[] hostHolder = new EditPart[] { host };
			if (checkInsertElement(r, hostHolder, new HashMap(),
					new DesignPosition[1], new IDOMPosition[1])) {
				return hostHolder[0];
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
		if (request instanceof LocalDropRequest) {
			LocalDropRequest r = (LocalDropRequest) request;
			EditPart host = getHost();

			Map result = new HashMap();
			if (_checkUpdate && checkUpdateWidget(host, r, result)) {
				LocalDropCommand command = new LocalDropCommand(
						getViewer(host), r.getLocalObject(), result);
				command.setWidget(((NodeEditPart) host).getIDOMNode());
				return command;
			}
			result.clear();

			EditPart[] hostHolder = new EditPart[] { host };
			IDOMPosition[] positionHolder = new IDOMPosition[1];
			if (checkInsertElement(r, hostHolder, result,
					new DesignPosition[1], positionHolder)) {
				LocalDropCommand command = new LocalDropCommand(
						getViewer(host), r.getLocalObject(), result);
				command.setDOMPosition(positionHolder[0]);
				return command;
			}
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
		if (request instanceof LocalDropRequest) {
			LocalDropRequest r = (LocalDropRequest) request;
			EditPart host = getHost();
			if (_checkUpdate && checkUpdateWidget(host, r, new HashMap())) {
				Rectangle rect = LocationHelper
						.getAbsoluteBounds((GraphicalEditPart) host);
				showFeedbackRect(rect);
				return;
			}
			EditPart[] hostHolder = new EditPart[] { host };
			DesignPosition[] holder = new DesignPosition[1];
			if (checkInsertElement(r, hostHolder, new HashMap(), holder,
					new IDOMPosition[1])) {
				Rectangle rect = EditPartPositionHelper
						.convertToAbsoluteCaretRect(holder[0]);
				showFeedbackRect(rect);
				if (getHost() instanceof GraphicalEditPart) {
					ExposeHelper exposeHelper = new ExposeHelper(
							getViewer(getHost()));
					exposeHelper.adjustVertical(r.getLocation());
				}
				return;
			}
		}
	}

	/**
	 * @return the feedback figure
	 */
	protected RectangleFigure getFeedbackFigure() {
		if (_feedbackFigure == null) {
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

	/**
	 * @param rect
	 */
	protected void showFeedbackRect(Rectangle rect) {
		RectangleFigure pf = getFeedbackFigure();
		pf.translateToRelative(rect);
		pf.setBounds(rect);
	}

	/**
	 * @return Returns the _forUpdate.
	 */
	public static boolean isCheckUpdate() {
		return _checkUpdate;
	}

	/**
	 * @param update
	 *            The _forUpdate to set.
	 */
	public static void setCheckUpdate(boolean update) {
		_checkUpdate = update;
	}
}
