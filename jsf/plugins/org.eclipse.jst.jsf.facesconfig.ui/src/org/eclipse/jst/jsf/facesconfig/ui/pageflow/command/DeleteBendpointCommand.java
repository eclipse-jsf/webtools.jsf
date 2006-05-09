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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;

/**
 * 
 */
public class DeleteBendpointCommand extends BendpointCommand {

	/** the deleting bendpoint */
	private PageflowLinkBendpoint bendpoint;

	public DeleteBendpointCommand() {

		// Pageflow.Commands.DeleteBendpointCommand.Label = Delete Bendpoint
		super(EditorResources.getInstance().getString(
				"Pageflow.Commands.DeleteBendpointCommand.Label"));

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
