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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.OrphanChildCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

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
		CompoundCommand result = new CompoundCommand("orphanchildcommand");
		for (int i = 0; i < parts.size(); i++) {
			OrphanChildCommand orphan = new OrphanChildCommand();
			orphan
					.setChild((PageflowNode) ((EditPart) parts.get(i))
							.getModel());
			orphan.setParent((Pageflow) getHost().getModel());
			orphan.setLabel("label here");
			result.add(orphan);
		}
		return result.unwrap();
	}

}
