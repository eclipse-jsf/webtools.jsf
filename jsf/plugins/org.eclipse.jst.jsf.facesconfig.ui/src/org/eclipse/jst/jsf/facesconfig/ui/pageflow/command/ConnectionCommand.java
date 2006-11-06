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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.TransformUtil;

/**
 * This is the connection command for pageflow editpart
 * 
 * @author Xiao-guang Zhang, hmeng
 */
public abstract class ConnectionCommand extends Command {
	/** Old source pageflow node */
	protected PageflowNode oldSource;

	/** Old target pageflow node */
	protected PageflowNode oldTarget;

	/** New source pageflow node */
	protected PageflowNode source;

	/** New target pageflow node */
	protected PageflowNode target;

	/** pageflow link */
	protected PageflowLink link;

	public ConnectionCommand() {
		super(PageflowMessages.Pageflow_Commands_ConnectionCommand_Label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#canExecute()
	 */
	public boolean canExecute() {
		return TransformUtil.isValidPageflowElement(link);
	}

	protected void doExecute() {
	    // do nothing; sub-class can over-ride
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public final void execute() {
		if (canExecute()) {
			doExecute();
		} else {
			link = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ommand#undo()
	 */
	public void undo() {
        // do nothing
	}

	/**
	 * Get the source pageflow node
	 * 
	 * @return - the source pageflow node
	 */
	public PageflowNode getSource() {
		return source;
	}

	/**
	 * Get the target pageflow node
	 * 
	 * @return - the target pageflow node.
	 */
	public PageflowNode getTarget() {
		return target;
	}

	/**
	 * Get the pageflow link
	 * 
	 * @return - the pageflow link.
	 */
	public PageflowLink getPFLink() {
		return link;
	}

	/**
	 * Set the source pageflow node
	 * 
	 * @param newSource -
	 *            new source pageflow node
	 */
	public void setSource(PageflowNode newSource) {
		source = newSource;
	}

	/**
	 * Set the target pageflow node
	 * 
	 * @param newTarget -
	 *            new target pageflow node
	 */
	public void setTarget(PageflowNode newTarget) {
		target = newTarget;
	}

	/**
	 * Set the pageflow link
	 * 
	 * @param link -
	 *            new pageflow link
	 */
	public void setPFLink(PageflowLink link) {
		this.link = link;
		oldSource = link.getSource();
		oldTarget = link.getTarget();
	}
}
