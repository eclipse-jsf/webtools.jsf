/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationEditPolicy;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData.DropData;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.w3c.dom.Node;

/**
 *
 */
public class HeadItemCreationEditPolicy extends ItemCreationEditPolicy {

	/**
	 * @param host
	 */
	public HeadItemCreationEditPolicy(ElementEditPart host) {
		super();
		setHost(host);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	@Override
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

			// since the head tag is a widget, the offset will need 
			// to be updated in the position so that new node is
			// appended to the list of children.
			Node node = domposition.getContainerNode();
			domposition = new DOMPosition(node, node.getChildNodes().getLength());

			return new CreateItemCommand(
					PDPlugin
							.getResourceString("ItemCreationEditPolicy.CommandLabel.CreateItem"),//$NON-NLS-1$
					getViewer(getHost()).getModel(), domposition, r.getTagCreationProvider());
		}
		return null;
	}

	@Override
	protected IPositionMediator createDropChildValidator(DropRequest r) {
		DropData dropData = createDropData(r);

		if (dropData != null) {
			MyDnDPositionValidator validator = new MyDnDPositionValidator(
					new DropActionData(ActionData.PALETTE_DND, dropData));
			return validator;
		}
		return null;
	}

	private static class MyDnDPositionValidator extends DnDPositionValidator {

		public MyDnDPositionValidator(DropActionData dropActionData) {
			super(dropActionData);
		}

		@Override
		public boolean isValidPosition(IDOMPosition position) {
			// if position is really a IDOMRefPosition, convert it to DOMPosition
			Node node = null;
			if (position instanceof DOMRefPosition) {
				node = ((DOMRefPosition) position).getReferenceNode();
				if (IHTMLConstants.TAG_HEAD.equalsIgnoreCase(node.getLocalName())) {
					return true;
				}
			} else {
				node = position.getContainerNode();
				if (IHTMLConstants.TAG_HEAD.equalsIgnoreCase(node.getLocalName())) {
					return true;
				}
			}

			return super.isValidPosition(position);
		}
	}
}
