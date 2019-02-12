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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * This is the orphan child command for pageflow container
 * 
 */
public class OrphanChildCommand extends Command {

	/** old location of pageflow node */
	private Point oldLocation;

	/** the parent pageflow */
	private Pageflow pageflow;

	/** the child pageflow node */
	private PageflowNode child;

	/** the index of pageflow nodes */
	private int index;

	/**
	 * Default constructor
	 */
	public OrphanChildCommand() {
		// Pageflow.Commands.OrphanChildCommand.Label = Orphan Child
		super(PageflowMessages.Pageflow_Commands_OrphanChildCommand_Label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		List children = pageflow.getNodes();
		index = children.indexOf(child);
		oldLocation = new Point(child.getX(), child.getY());
		pageflow.getNodes().remove(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		pageflow.getNodes().remove(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		child.setX(oldLocation.x);
		child.setY(oldLocation.y);
		pageflow.getNodes().add(index, child);
	}

	/**
	 * set the child pageflow node
	 * 
	 * @param child -
	 *            child pageflow node
	 */
	public void setChild(PageflowNode child) {
		this.child = child;
	}

	/**
	 * set the parent pageflow
	 * 
	 * @param parent -
	 *            parent pageflow
	 */
	public void setParent(Pageflow parent) {
		pageflow = parent;
	}

}
