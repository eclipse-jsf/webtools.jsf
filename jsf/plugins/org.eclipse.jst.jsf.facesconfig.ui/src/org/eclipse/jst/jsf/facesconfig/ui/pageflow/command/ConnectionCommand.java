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
	protected PageflowNode pageflowNode;

	/** New target pageflow node */
	protected PageflowNode target;

	/** pageflow link */
	protected PageflowLink link;

	public ConnectionCommand() {
		// Pageflow.Commands.ConnectionCommand.Label = Connection
		super(PageflowMessages.Pageflow_Commands_ConnectionCommand_Label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#canExecute()
	 */
	public boolean canExecute() {
		return false;
	}

	protected void doExecute() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public final void execute() {
		if (canExecute()) {
			String outcome = null, action = null, largeIcon = null, smallIcon = null;
			boolean isRedirect = false;
			// XXX: if the event that is sent is customized later, this would be
			// not necessary.
			if (link != null) {
				outcome = link.getOutcome();
				action = link.getFromaction();
				isRedirect = link.isRedirect();
				largeIcon = link.getLargeicon();
				smallIcon = link.getSmallicon();
			}
			doExecute();
			if (link != null) {
				link.setOutcome(outcome);
				link.setFromaction(action);
				link.setLargeicon(largeIcon);
				link.setSmallicon(smallIcon);
				link.setRedirect(isRedirect);
			}
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
		// if (link == null) {
		// return;
		// }
		// // It is a delete connection command
		// if (pageflowNode == null && target == null) {
		// oldSource.getOutlinks().add(link);
		// oldTarget.getInlinks().add(link);
		// oldSource.getPageflow().getLinks().add(link);
		// }

		// // It was a reconnect source command
		// if (oldSource != null && pageflowNode != null) {
		// // The link source must be replaced by the oldSource
		// if (link.getSource() != null) {
		// link.getSource().getOutlinks().remove(link);
		// }
		// // Source should not know link anymore
		// pageflowNode.getOutlinks().remove(link);
		// // Re-link with oldSource
		// // No containment link between link and input and output
		// // Two add method need to be called
		// link.setSource(oldSource);
		// oldSource.getOutlinks().add(link);
		// }
		// // It was a reconnect target command
		// if (oldTarget != null && target != null) {
		// // The link target must be replaced by the oldTarget
		// if (link.getTarget() != null) {
		// link.getTarget().getInlinks().remove(link);
		// }
		// // Target should not know link anymore
		// target.getInlinks().remove(link);
		// // Re-link with oldTarget
		// // No containment link between link and input and output port
		// // Two add method need to be called
		// link.setTarget(oldTarget);
		// oldTarget.getInlinks().add(link);
		// }

		// // It was a connection create command
		// if (oldSource == null && oldTarget == null) {
		//
		// Pageflow pageflow = null;
		// // Get a reference to the pageflow
		// if (pageflowNode != null) {
		// pageflow = pageflowNode.getPageflow();
		// } else if (target != null) {
		// pageflow = target.getPageflow();
		// }
		//
		// // Remove all reference to the link
		// pageflowNode.getOutlinks().remove(link);
		// target.getInlinks().remove(link);
		//
		// // Ensure that link knows nothing about nodes anymore.
		// link.setSource(null);
		// link.setTarget(null);
		// // Remove link from pageflow
		// pageflow.getLinks().remove(link);
		// }
	}

	/**
	 * Get the source pageflow node
	 * 
	 * @return - the source pageflow node
	 */
	public PageflowNode getSource() {
		return pageflowNode;
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
		pageflowNode = newSource;
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
