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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

public class PageflowElementEditPolicy extends
		org.eclipse.gef.editpolicies.ComponentEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see ComponentEditPolicy#createDeleteCommand()
	 */
	protected Command createDeleteCommand(GroupRequest request) {
		Object parent = getHost().getParent().getModel();
		DeleteNodeCommand deleteCmd = new DeleteNodeCommand((Pageflow) parent);
		deleteCmd.setParent((Pageflow) parent);
		deleteCmd.setChild((PageflowNode) getHost().getModel());
		return deleteCmd;
	}

}
