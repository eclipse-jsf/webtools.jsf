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

import java.util.EventObject;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * This adapter is used to let editor be able to monitor EMF CommandStack, e.g.,
 * in StructuredTextEditor, using GEF CommandStack.
 * 
 * @author xgzhang
 * @version
 */
public class EMFCommandStackGEFAdapter extends CommandStack implements
		CommandStackListener {
	private org.eclipse.emf.common.command.BasicCommandStack emfCommandStack;
	private IStructuredModel model;

	/**
	 * @param doc
	 */
	public EMFCommandStackGEFAdapter (IDocument doc) {
		
		super();
		
		model = StructuredModelManager.getModelManager().getExistingModelForEdit(doc);
		
		if (model == null) {
			model = StructuredModelManager.getModelManager().getModelForEdit((IStructuredDocument) doc);
		}
		
		emfCommandStack = ((BasicCommandStack) this.model.getUndoManager().getCommandStack());
		emfCommandStack.addCommandStackListener(this);
	}

	
	@Override
	public void dispose() {
		model.releaseFromEdit();
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#canRedo()
	 */
	public boolean canRedo() {
		if (emfCommandStack == null) {
			return false;
		}
		return emfCommandStack.canRedo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#canUndo()
	 */
	public boolean canUndo() {
		if (emfCommandStack == null) {
			return false;
		}
		return emfCommandStack.canUndo();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
	 */
	public void execute(Command command) {
		if (!(command instanceof EMFCommandGEFAdapter)) {
			return;
		}

		org.eclipse.emf.common.command.Command emfCommand = ((EMFCommandGEFAdapter) command)
				.getEMFCommand();
		if (emfCommand != null) {
			emfCommandStack.execute(emfCommand);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#flush()
	 */
	public void flush() {
		if (emfCommandStack == null) {
			return;
		}
		emfCommandStack.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#getRedoCommand()
	 */
	public Command getRedoCommand() {
		if (emfCommandStack == null || emfCommandStack.getRedoCommand() == null) {
			return null;
		}

		return new EMFCommandGEFAdapter(emfCommandStack.getRedoCommand());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#getUndoCommand()
	 */
	public Command getUndoCommand() {
		if (emfCommandStack == null || emfCommandStack.getUndoCommand() == null) {
			return null;
		}

		return new EMFCommandGEFAdapter(emfCommandStack.getUndoCommand());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#isDirty()
	 */
	public boolean isDirty() {
		if (emfCommandStack == null) {
			return false;
		}
		return emfCommandStack.isSaveNeeded();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#markSaveLocation()
	 */
	public void markSaveLocation() {
		if (emfCommandStack == null) {
			return;
		}
		emfCommandStack.saveIsDone();
		super.markSaveLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#redo()
	 */
	public void redo() {
		if (emfCommandStack == null) {
			return;
		}
		emfCommandStack.redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#undo()
	 */
	public void undo() {
		if (emfCommandStack == null) {
			return;
		}
		emfCommandStack.undo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		this.notifyListeners();
	}

}
