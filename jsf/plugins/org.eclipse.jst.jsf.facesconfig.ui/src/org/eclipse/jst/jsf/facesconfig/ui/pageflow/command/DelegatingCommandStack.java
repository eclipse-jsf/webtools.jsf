/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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

import java.util.EventObject;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.UnexecutableCommand;

/**
 * This is a delegating command stack, which delegates everything to another
 * CommandStack except event listners.
 * <p>
 * Event listeners registered to a <code>DelegatingCommandStack</code> will be
 * informed whenever the underlying <code>CommandStack</code> changes. They
 * will not be registered to the underlying <code>CommandStack</code> directly
 * but they will be informed about change events of them.
 * 
 */
public class DelegatingCommandStack extends CommandStack implements
		CommandStackListener {
	/** the empty object array */
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

	/** the current command stack */
	private CommandStack currentCommandStack = null;

	/**
	 * Returns the current <code>CommandStack</code>.
	 * 
	 * @return - the current <code>CommandStack</code>
	 */
	public CommandStack getCurrentCommandStack() {
		return currentCommandStack;
	}

	/**
	 * Sets the current <code>CommandStack</code>.
	 * 
	 * @param stack -
	 *            the <code>CommandStack</code> to set
	 */
	public void setCurrentCommandStack(CommandStack stack) {
		if (currentCommandStack == stack) {
			return;
		}

		// remove from old command stack
		if (null != currentCommandStack) {
			currentCommandStack.removeCommandStackListener(this);
		}

		// set new command stack
		currentCommandStack = stack;

		if (currentCommandStack != null) {
			// watch new command stack
			currentCommandStack.addCommandStackListener(this);

			// the command stack changed
			notifyListeners();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#canRedo()
	 */
	public boolean canRedo() {
		if (null == currentCommandStack) {
			return false;
		}

		return currentCommandStack.canRedo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#canUndo()
	 */
	public boolean canUndo() {
		if (null == currentCommandStack) {
			return false;
		}

		return currentCommandStack.canUndo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#dispose()
	 */
	public void dispose() {
		if (null != currentCommandStack) {
			currentCommandStack.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#execute(org.eclipse.gef.commands.Command)
	 */
	public void execute(Command command) {
		if (null != currentCommandStack) {
			currentCommandStack.execute(command);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#flush()
	 */
	public void flush() {
		if (null != currentCommandStack) {
			currentCommandStack.flush();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#getCommands()
	 */
	public Object[] getCommands() {
		if (null == currentCommandStack) {
			return EMPTY_OBJECT_ARRAY;
		}

		return currentCommandStack.getCommands();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#getRedoCommand()
	 */
	public Command getRedoCommand() {
		if (null == currentCommandStack) {
			return UnexecutableCommand.INSTANCE;
		}

		return currentCommandStack.getRedoCommand();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#getUndoCommand()
	 */
	public Command getUndoCommand() {
		if (null == currentCommandStack) {
			return UnexecutableCommand.INSTANCE;
		}

		return currentCommandStack.getUndoCommand();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#getUndoLimit()
	 */
	public int getUndoLimit() {
		if (null == currentCommandStack) {
			return -1;
		}

		return currentCommandStack.getUndoLimit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#isDirty()
	 */
	public boolean isDirty() {
		if (null == currentCommandStack) {
			return false;
		}

		return currentCommandStack.isDirty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#markSaveLocation()
	 */
	public void markSaveLocation() {
		if (null != currentCommandStack) {
			currentCommandStack.markSaveLocation();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#redo()
	 */
	public void redo() {
		if (null != currentCommandStack) {
			currentCommandStack.redo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#setUndoLimit(int)
	 */
	public void setUndoLimit(int undoLimit) {
		if (null != currentCommandStack) {
			currentCommandStack.setUndoLimit(undoLimit);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStack#undo()
	 */
	public void undo() {
		if (null != currentCommandStack) {
			currentCommandStack.undo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Object#toString()
	 */
	public String toString() {
		return "DelegatingCommandStack(" + currentCommandStack + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		notifyListeners();
	}
}
