/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.actions;

import java.text.MessageFormat;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.editors.SimpleGraphicalEditor;
import org.eclipse.wst.sse.core.internal.undo.IStructuredTextUndoManager;

/**
 * SSE has a IDocumentSelectionMediator mechanism, basically it let the viewer
 * that invoke the redo/undo to reset the selection after redo/undo.
 * 
 * To utilize this feature, we can't directly use the undo/redo action of the
 * text editor for the designer, since in that way it will be the TextEditor to
 * handle selection after redo/undo.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DesignerUndoRedoAction extends Action implements UpdateAction {
	private boolean _undo = true; // if false means redo

	private final SimpleGraphicalEditor _designer;

	/**
	 * @param undo 
	 * @param designer 
	 * 
	 */
	public DesignerUndoRedoAction(boolean undo, SimpleGraphicalEditor designer) {
		this._undo = undo;
		this._designer = designer;

		if (undo) {
			setText(ActionsMessages.getString("DesignerUndoRedoAction.UNDO")); //$NON-NLS-1$
		} else {
			setText(ActionsMessages.getString("DesignerUndoRedoAction.REDO")); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.UpdateAction#update()
	 */
	public void update() {
		IStructuredTextUndoManager undoManager = _designer.getHTMLEditor()
				.getModel().getUndoManager();
		if (_undo) {
			Command c = undoManager.getUndoCommand();
			this.setEnabled(undoManager.undoable());
			if (c != null) {
				String label = c.getLabel();
				this
						.setText(MessageFormat
								.format(
										ActionsMessages
												.getString("DesignerUndoRedoAction.UNDO_LABEL"), new Object[] { label })); //$NON-NLS-1$
			} else {
				this.setText(ActionsMessages
						.getString("DesignerUndoRedoAction.UNDO")); //$NON-NLS-1$
			}
		} else {
			Command c = undoManager.getRedoCommand();
			this.setEnabled(undoManager.redoable());
			if (c != null) {
				String label = c.getLabel();
				this
						.setText(MessageFormat
								.format(
										ActionsMessages
												.getString("DesignerUndoRedoAction.REDO_LABEL"), new Object[] { label })); //$NON-NLS-1$
			} else {
				this.setText(ActionsMessages
						.getString("DesignerUndoRedoAction.REDO")); //$NON-NLS-1$
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		IStructuredTextUndoManager undoManager = _designer.getHTMLEditor()
				.getModel().getUndoManager();
		if (_undo) {
			undoManager.undo(_designer);
		} else {
			undoManager.redo(_designer);
		}
	}

}
