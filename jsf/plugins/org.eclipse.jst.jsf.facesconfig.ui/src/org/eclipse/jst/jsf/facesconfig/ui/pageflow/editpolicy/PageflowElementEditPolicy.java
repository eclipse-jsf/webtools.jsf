/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * Customize component edit policies for page flows
 *
 */
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
