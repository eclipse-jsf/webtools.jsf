/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.AddNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.CreateNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.SetConstraintCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowNodeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * Customize the layout policy for page flows
 *
 */
public class PageflowXYLayoutEditPolicy extends XYLayoutEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#createAddCommand()
	 */
	protected Command createAddCommand(EditPart childEditPart, Object constraint) {
		PageflowNode part = (PageflowNode) childEditPart.getModel();
		Rectangle rect = (Rectangle) constraint;

		AddNodeCommand add = new AddNodeCommand();
		add.setParent((Pageflow) getHost().getModel());
		add.setChild(part);
		add.setLabel(PageflowMessages.PageflowXYLayoutEditPolicy_Add);
		add.setDebugLabel("PageFlowXYEP add subpart"); //$NON-NLS-1$

		SetConstraintCommand setConstraint = new SetConstraintCommand();

		setConstraint.setLocation(rect);
		setConstraint.setPart(part);
		setConstraint.setLabel(PageflowMessages.PageflowXYLayoutEditPolicy_Add);
		setConstraint.setDebugLabel("PageFlowXYEP setConstraint"); //$NON-NLS-1$
		return add.chain(setConstraint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#createChangeConstraintCommand()
	 */
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		SetConstraintCommand locationCommand = new SetConstraintCommand();
		locationCommand.setPart((PageflowNode) child.getModel());
		locationCommand.setLocation((Rectangle) constraint);
		return locationCommand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#createChildEditPolicy()
	 */
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof PageflowNodeEditPart) {
			return new PageflowNodeSelectionEditPolicy();
		}

		return new ResizableEditPolicy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#getCreateCommand()
	 */
	protected Command getCreateCommand(CreateRequest request) {
		CreateNodeCommand create = new CreateNodeCommand();
		create.setParent((Pageflow) getHost().getModel());
		create.setChild((PageflowNode) request.getNewObject());
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		create.setLocation(constraint);
		create.setLabel(PageflowMessages.PageflowXYLayoutEditPolicy_Add);
		return create;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#getDeleteDependantCommand()
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see XYLayoutEditPolicy#getOrphanChildrenCommand()
	 */
	protected Command getOrphanChildrenCommand(Request request) {
		return null;
	}
}
