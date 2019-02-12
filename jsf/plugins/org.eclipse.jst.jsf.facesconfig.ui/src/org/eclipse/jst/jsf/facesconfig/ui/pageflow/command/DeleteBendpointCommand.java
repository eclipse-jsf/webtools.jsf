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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;

/**
 * 
 */
public class DeleteBendpointCommand extends BendpointCommand {

	/** the deleting bendpoint */
	private PageflowLinkBendpoint bendpoint;

	/**
	 * Default constructor
	 */
	public DeleteBendpointCommand() {

		// Pageflow.Commands.DeleteBendpointCommand.Label = Delete Bendpoint
		super(PageflowMessages.Pageflow_Commands_DeleteBendpointCommand_Label);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		super.execute();
		bendpoint = (PageflowLinkBendpoint) getPFLink().getBendPoints().get(
				getIndex());
		getPFLink().removeBendpoint(getIndex());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		super.undo();
		getPFLink().insertBendpoint(getIndex(), bendpoint);
	}
}
