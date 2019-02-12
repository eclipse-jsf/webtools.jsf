/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.OrphanChildCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * Customize the container policy for page flow containers
 *
 */
public class PageflowContainerEditPolicy extends ContainerEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see ContainerEditPolicy#getCreateCommand()
	 */
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ContainerEditPolicy#getOrphanChildrenCommand()
	 */
	public Command getOrphanChildrenCommand(GroupRequest request) {
		List parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand("orphanchildcommand"); //$NON-NLS-1$
		for (int i = 0; i < parts.size(); i++) {
			OrphanChildCommand orphan = new OrphanChildCommand();
			orphan
					.setChild((PageflowNode) ((EditPart) parts.get(i))
							.getModel());
			orphan.setParent((Pageflow) getHost().getModel());
			orphan.setLabel(PageflowMessages.PageflowContainerEditPolicy_OrphanLabel);
			result.add(orphan);
		}
		return result.unwrap();
	}

}
