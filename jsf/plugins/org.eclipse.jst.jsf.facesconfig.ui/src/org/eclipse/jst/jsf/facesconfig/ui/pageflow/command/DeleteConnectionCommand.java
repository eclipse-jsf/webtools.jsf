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

/**
 * Delete a connection.
 * 
 * @author hmeng
 * 
 */
public class DeleteConnectionCommand extends ConnectionCommand {

	public boolean canExecute() {
		return link != null;
	}

	protected void doExecute() {
		// It is a delete connection command
		if (link != null) {
			link.setSource(null);
			link.setTarget(null);
			oldSource.getPageflow().getLinks().remove(link);
			link.eAdapters().clear();
		}
	}

	public void undo() {
		// It is a delete connection command
		if (canExecute()) {
			link.setSource(oldSource);
			link.setTarget(oldTarget);
			oldSource.getPageflow().getLinks().add(link);
		}
	}

}
