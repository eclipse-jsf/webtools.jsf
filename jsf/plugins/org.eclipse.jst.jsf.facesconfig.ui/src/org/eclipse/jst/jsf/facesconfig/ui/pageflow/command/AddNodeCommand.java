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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * This is the add command for pageflow editpart
 * 
 */
public class AddNodeCommand extends Command {
	/** Pageflow node including Page, action, begin, end. */
	private PageflowNode child;

	/** parent Pageflow */
	private Pageflow parent;

	/** index of pageflow nodes */
	private int index = -1;

	/**
	 * Default constructor
	 */
	public AddNodeCommand() {
		// Pageflow.Commands.AddNodeCommand.Label = add
		super(PageflowMessages.Pageflow_Commands_AddNodeCommand_Label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		if (index < 0) {
			parent.getNodes().add(child);
		} else {
			parent.getNodes().add(index, child);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		if (index < 0) {
			parent.getNodes().add(child);
		} else {
			parent.getNodes().add(index, child);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		parent.getNodes().remove(child);
	}

	/**
	 * get parent pageflow of the node.
	 * @return the parent
	 * 
	 */
	public Pageflow getParent() {
		return parent;
	}

	/**
	 * Set the child pageflow node
	 * 
	 * @param subpart -
	 *            child pageflow node
	 */
	public void setChild(PageflowNode subpart) {
		child = subpart;
	}

	/**
	 * Creates a new AbstractEditorPage instance.
	 * 
	 * @param i -
	 *            index of pageflow node
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * Set the new parent pageflow
	 * 
	 * @param newParent -
	 *            parent pageflow
	 */
	public void setParent(Pageflow newParent) {
		parent = newParent;
	}
}
