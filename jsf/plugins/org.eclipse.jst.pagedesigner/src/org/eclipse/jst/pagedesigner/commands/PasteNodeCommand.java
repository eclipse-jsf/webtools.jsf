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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jst.pagedesigner.commands.nav.ICaretPositionMover;
import org.eclipse.wst.html.core.internal.document.ElementStyleImpl;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class PasteNodeCommand extends Command implements ICaretPositionMover {
	private static final String COMMAND_LABEL = CommandResources
			.getString("PasteNodeCommand.Label.DeleteNode"); //$NON-NLS-1$

	private Node child;

	private SourceViewer _sourceViewer;

	/**
	 * @param viewer
	 */
	public PasteNodeCommand(SourceViewer viewer) {
		super(COMMAND_LABEL);
		this._sourceViewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		_sourceViewer.getTextWidget().setSelection(
				((ElementStyleImpl) child).getStartOffset(),
				((ElementStyleImpl) child).getStartOffset());
		_sourceViewer.doOperation(ITextOperationTarget.PASTE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		_sourceViewer.doOperation(ITextOperationTarget.REDO);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		_sourceViewer.doOperation(ITextOperationTarget.UNDO);

	}

	/**
	 * @param child
	 *            The child to set.
	 */
	public void setChild(Node child) {
		this.child = child;
	}
}
