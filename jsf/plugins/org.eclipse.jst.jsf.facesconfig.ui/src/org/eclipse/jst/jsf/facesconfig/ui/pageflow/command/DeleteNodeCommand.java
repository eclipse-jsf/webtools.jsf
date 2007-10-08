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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;

/**
 * This is the delete command for pageflow editpart
 * 
 */
public class DeleteNodeCommand extends AbstractBatchEditCommand {
	/** the deleting pageflow node */
	private PageflowNode child;

	/** parent pageflow */
	private Pageflow parent;

	/** index of pageflow nodes */
	private int index = -1;

	/** source connections with the deleting node */
	private List sourceConnections = new ArrayList();

	private List sourceConnectionSources = new ArrayList();

	private List sourceConnectionTargets = new ArrayList();

	/** target connections with the deleting node */
	private List targetConnections = new ArrayList();

	private List targetConnectionSources = new ArrayList();

	private List targetConnectionTargets = new ArrayList();

	/**
	 * @param pageflow
	 */
	public DeleteNodeCommand(Pageflow pageflow) {
		// Pageflow.Commands.DeleteNodeCommand.Label = Delete
		super(pageflow, PageflowMessages.Pageflow_Commands_DeleteNodeCommand_Label);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void doExecute() {
		primExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void doRedo() {
		primExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void doUndo() {
		parent.getNodes().add(index, child);
		restoreConnections(child);
	}

	/**
	 * delete the connections related with the pageflow node.
	 * 
	 * @param node -
	 *            the deleting pageflow node
	 */
	private void deleteConnections(PageflowNode node) {
		while (node.getOutlinks().size() > 0) {
			// Note:should save the source and target of the link, otherwise
			// source and target will be lost,
			// when the source and target node remove the link.
			PageflowLink link = (PageflowLink) node.getOutlinks().get(0);
			sourceConnections.add(link);
			sourceConnectionSources.add(link.getSource());
			sourceConnectionTargets.add(link.getTarget());

			link.getTarget().getInlinks().remove(link);
			node.getOutlinks().remove(link);
			parent.getLinks().remove(link);
		}

		while (node.getInlinks().size() > 0) {
			// Note: should save the source and target of the link, otherwise
			// source and target will be lost,
			// when the source and target node remove the link.
			PageflowLink link = (PageflowLink) node.getInlinks().get(0);
			targetConnections.add(link);
			targetConnectionSources.add(link.getSource());
			targetConnectionTargets.add(link.getTarget());

			link.getSource().getOutlinks().remove(link);
			node.getInlinks().remove(link);
			parent.getLinks().remove(link);
		}
	}

	/**
	 * execute the delete command
	 * 
	 * 
	 */
	protected void primExecute() {
		deleteConnections(child);
		index = parent.getNodes().indexOf(child);
		parent.getNodes().remove(child);
	}

	/**
	 * restore the connections of the deleted pageflow node
	 * 
	 * @param node -
	 *            the deleted pageflow node
	 */
	private void restoreConnections(PageflowNode node) {
		for (int i = 0; i < sourceConnections.size(); i++) {
			// restore the link with the source and target
			PageflowLink link = (PageflowLink) sourceConnections.get(i);
			PageflowNode source = (PageflowNode) sourceConnectionSources.get(i);
			PageflowNode target = (PageflowNode) sourceConnectionTargets.get(i);
			link.setSource(source);
			link.setTarget(target);

			parent.getLinks().add(link);
			node.getOutlinks().add(link);
			link.getTarget().getInlinks().add(link);
		}
		sourceConnections.clear();
		for (int i = 0; i < targetConnections.size(); i++) {
			// restore the link with the source and target
			PageflowLink link = (PageflowLink) targetConnections.get(i);
			PageflowNode source = (PageflowNode) targetConnectionSources.get(i);
			PageflowNode target = (PageflowNode) targetConnectionTargets.get(i);
			link.setSource(source);
			link.setTarget(target);

			parent.getLinks().add(link);
			node.getInlinks().add(link);
			link.getSource().getOutlinks().add(link);
		}
		targetConnections.clear();
	}

	/**
	 * set the child pageflow node
	 * 
	 * @param c -
	 *            the child pageflow node
	 */
	public void setChild(PageflowNode c) {
		child = c;
	}

	/**
	 * set the parent pageflow
	 * 
	 * @param p -
	 *            the parent pageflow
	 */
	public void setParent(Pageflow p) {
		parent = p;
	}
}
