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
import org.w3c.dom.Node;

/**
 * This class is intended to be the base class for all designer GEF commands.
 * Basically, it will wrap the real command with common actions like handle
 * undo/redo, etc.
 * 
 * @author Yang Liu, Xiao-guang Zhang
 */
public abstract class BaseSectionPartCommand extends Command {

	boolean hasException = false;

	public BaseSectionPartCommand(String label) {
		super(label);
	}

	/**
	 * executes the Command. This method should not be called if the Command is
	 * not executable.
	 */
	public final void execute() {
		boolean ok = prePreExecute();
		if (ok) {
			try {
				preExecute();
				doExecute();
				postExecute();
			} catch (Exception ex) {
				handleException(ex);
			} finally {
				postPostExecute();
			}
		}
	}

	/**
	 * child class can override.
	 * 
	 * @param ex
	 */
	protected void handleException(Exception ex) {
		ex.printStackTrace();
		hasException = true;
	}

	/**
	 * prePreExecute and postPostExecute is a pair. prePreExecute() SHOULD NOT
	 * throw any exception, if it throw any exception, it should catch itself
	 * and return false to indicate not continue.
	 * 
	 */
	protected boolean prePreExecute() {
		return true;
	}

	/**
	 * child class can override this method for any pre action.
	 * 
	 */
	protected void preExecute() {
	}

	/**
	 * child class should override this method for the real action.
	 * 
	 */
	protected abstract void doExecute();

	/**
	 * child class can override this method for any post action. NOTE: if
	 * preExecute() or doExecute() throw exception, then this method will NOT be
	 * called.
	 * 
	 */
	protected void postExecute() {
	}

	/**
	 * if prePreExecute() return true, then this method will always be called
	 * even preExecute()/doExecute() and postExecute() fail.
	 * 
	 */
	protected void postPostExecute() {
		// about model change
		// and EditPart will be refreshed. Only at this time, could we use
		// EditPart to construct the
		// result selection.
		// setSelection();
		// getViewer().selectionChanged();
	}

	/**
	 * Child class is expected to override this method to set the selections.
	 */
	protected void setSelection() {
		// getViewer().deselectAll();
	}

	/**
	 * format the specified node in source code. Utility method that can be
	 * called by child classes
	 * 
	 * @param node
	 */
	public void formatNode(Node node) {
		// XXX: there should have some other way to get the FormatProcessor.
		// currently hardcoded to HTMLFormatProcessorImpl().
		// new HTMLFormatProcessorImpl().formatNode(node);
	}

	/**
	 * Re-executes the Command. This method should only be called after
	 * <code>undo()</code> has been called.
	 */
	public void redo() {
		// this method should in fact never be called, because we have already
		// delegate undo
		// operations to source view.
		// getSSEModel().getUndoManager().redo();
	}

	/**
	 * Undoes the changes performed during <code>execute()</code>. This
	 * method should only be called after <code>execute</code> has been
	 * called, and only when <code>canUndo()</code> returns <code>true</code>.
	 * 
	 * @see #canUndo()
	 */
	public void undo() {
		// this method should in fact never be called, because we have already
		// delegate undo
		// operations to source view.
		// getSSEModel().getUndoManager().undo();
	}
}
