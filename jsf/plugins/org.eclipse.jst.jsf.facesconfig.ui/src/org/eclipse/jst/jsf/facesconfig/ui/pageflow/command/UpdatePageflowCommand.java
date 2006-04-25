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
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowModelManager;

/**
 * This is the update command for current pageflow according to faces-config
 * update.
 * 
 * @author Xiao-guang Zhang
 */
public class UpdatePageflowCommand extends Command {
	/** current pageflow node */
	private Pageflow curPageflow;

	private Pageflow oldPageflow;

	/** new pageflow from faces-config */
	private Pageflow newPageflow;

	/** index of pageflow nodes */
	// private int index = -1;
	public UpdatePageflowCommand() {
		// Pageflow.Commands.UpdatePageflowCommand.Label = Update pageflow
		super(EditorResources.getInstance().getString(
				"Pageflow.Commands.UpdatePageflowCommand.Label"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		PageflowFactory factory = PageflowModelManager.getFactory();
		oldPageflow = factory.createPageflow();
		oldPageflow.getNodes().addAll(curPageflow.getNodes());
		oldPageflow.getLinks().addAll(curPageflow.getLinks());

		curPageflow.getNodes().clear();
		curPageflow.getLinks().clear();

		curPageflow.getNodes().addAll(newPageflow.getNodes());
		curPageflow.getLinks().addAll(newPageflow.getLinks());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		oldPageflow.getNodes().addAll(curPageflow.getNodes());
		oldPageflow.getLinks().addAll(curPageflow.getLinks());

		curPageflow.getNodes().clear();
		curPageflow.getLinks().clear();

		curPageflow.getNodes().addAll(newPageflow.getNodes());
		curPageflow.getLinks().addAll(newPageflow.getLinks());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		newPageflow.getNodes().addAll(curPageflow.getNodes());
		newPageflow.getLinks().addAll(curPageflow.getLinks());

		curPageflow.getNodes().clear();
		curPageflow.getLinks().clear();

		curPageflow.getNodes().addAll(oldPageflow.getNodes());
		curPageflow.getLinks().addAll(oldPageflow.getLinks());
	}

	/**
	 * set the parent pageflow
	 * 
	 * @param newParent -
	 *            the new parent pageflow
	 */
	public void setPageflowUpdateDelta(Pageflow curPageflow,
			Pageflow newPageflow) {
		this.curPageflow = curPageflow;
		this.newPageflow = newPageflow;
	}
}
