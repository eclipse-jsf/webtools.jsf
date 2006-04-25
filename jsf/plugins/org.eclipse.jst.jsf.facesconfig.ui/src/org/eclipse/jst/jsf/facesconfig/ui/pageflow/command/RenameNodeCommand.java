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

import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * Command to rename Node.
 * 
 * @author xgzhang
 */
public class RenameNodeCommand extends Command {

	/** new pageflow node */
	private PageflowNode node;

	/** new node name */
	private String name;

	/** old node name */
	private String oldName;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		node.setName(name);
	}

	/**
	 * Sets the new Activity name
	 * 
	 * @param string
	 *            the new name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * Sets the old Activity name
	 * 
	 * @param string
	 *            the old name
	 */
	public void setOldName(String string) {
		oldName = string;
	}

	/**
	 * Sets the source Activity
	 * 
	 * @param activity
	 *            the source Activity
	 */
	public void setSource(PageflowNode node) {
		this.node = node;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		node.setName(oldName);
	}

}
