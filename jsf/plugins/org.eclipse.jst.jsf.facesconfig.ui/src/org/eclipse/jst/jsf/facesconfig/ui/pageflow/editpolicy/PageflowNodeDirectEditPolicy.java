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
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.RenameNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * EditPolicy for the direct editing of pageflow node's name.
 * 
 * @author Xiaoguang Zhang
 */
public class PageflowNodeDirectEditPolicy extends DirectEditPolicy {

	/*
	 * (non-javadoc)
	 * 
	 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest request) {
		RenameNodeCommand cmd = new RenameNodeCommand();
		cmd.setSource((PageflowNode) getHost().getModel());
		cmd.setOldName(((PageflowNode) getHost().getModel()).getName());
		cmd.setName((String) request.getCellEditor().getValue());
		return cmd;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		// String value = (String)request.getCellEditor().getValue();
		// ((PageflowNodeFigure)getHostFigure()).setText(value);
	}

}
