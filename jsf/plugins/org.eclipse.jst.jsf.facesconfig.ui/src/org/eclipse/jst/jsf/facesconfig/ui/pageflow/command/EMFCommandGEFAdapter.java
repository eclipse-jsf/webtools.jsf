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

import org.eclipse.gef.commands.Command;

/**
 * This class is used to adapter EMF Command to GEF Command.
 * 
 * @author xgzhang
 * @version
 */
/*package*/ class EMFCommandGEFAdapter extends Command {
	private org.eclipse.emf.common.command.Command emfCommand;

	/**
	 * @param emfCommand 
	 */
	public EMFCommandGEFAdapter(
			org.eclipse.emf.common.command.Command emfCommand) {
		super();
		this.emfCommand = emfCommand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (emfCommand == null) {
			return false;
		}
		return emfCommand.canExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		if (emfCommand == null) {
			return false;
		}
		return emfCommand.canUndo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#dispose()
	 */
	public void dispose() {
		if (emfCommand == null) {
			return;
		}
		emfCommand.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (emfCommand == null) {
			return;
		}
		emfCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	public String getLabel() {
		if (emfCommand == null) {
			return null;
		}
		return emfCommand.getLabel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		if (emfCommand == null) {
			return;
		}
		emfCommand.redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (emfCommand == null) {
			return;
		}
		emfCommand.undo();
	}

	/**
	 * 
	 */
	org.eclipse.emf.common.command.Command getEMFCommand() {
		return emfCommand;
	}
}
