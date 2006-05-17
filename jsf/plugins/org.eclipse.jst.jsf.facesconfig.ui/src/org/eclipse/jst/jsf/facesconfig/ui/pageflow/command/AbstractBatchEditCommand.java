/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;

/**
 * A batched edit command will enable adapters to interven before and after a
 * set of operations was done by sending notification to the adapters.
 * 
 * @author hmeng
 */

public abstract class AbstractBatchEditCommand extends Command {
	Pageflow pageflow;

	public AbstractBatchEditCommand(Pageflow pageflow) {
		super();
		this.pageflow = pageflow;
	}

	public AbstractBatchEditCommand(Pageflow pageflow, String label) {
		super(label);
		this.pageflow = pageflow;
	}

	public Pageflow getPageflow() {
		return pageflow;
	}

	/**
	 * The subclass should extend methods doXXX only.
	 */
	final public void execute() {
		preExecute();
		doExecute();
		postExecute();
	}

	final public void redo() {
		super.redo();
	}

	final public void undo() {
		preExecute();
		doUndo();
		postExecute();
	}

	abstract public void doExecute();

	public void doRedo() {
		execute();
	}

	abstract void doUndo();

	final void preExecute() {
		getPageflow().eNotify(
				new ENotificationImpl((InternalEObject) getPageflow(),
						FC2PFTransformer.MY_NOTIFICATION_TYPE,
						PageflowPackage.PAGEFLOW, null, null));
	}

	final protected void postExecute() {
		getPageflow().eNotify(
				new ENotificationImpl((InternalEObject) getPageflow(),
						FC2PFTransformer.MY_NOTIFICATION_TYPE1,
						PageflowPackage.PAGEFLOW, null, null));
		notifyPageflow(getPageflow());
	}

	protected void notifyPageflow(Pageflow pageflow) {
		pageflow.notifyModelChanged(new ENotificationImpl(
				(InternalEObject) pageflow, Notification.ADD,
				PageflowPackage.PAGEFLOW, null, null));
	}
}
