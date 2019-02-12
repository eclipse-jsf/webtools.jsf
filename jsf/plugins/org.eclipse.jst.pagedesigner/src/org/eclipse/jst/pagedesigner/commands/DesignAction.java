/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.editors.IDesignViewer;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public abstract class DesignAction extends Action implements UpdateAction {
	private GraphicalEditor _editor;

	/**
	 * @param editor
	 * @param text
	 */
	public DesignAction(GraphicalEditor editor, String text) {
		super(text);
		_editor = editor;
	}

	/**
	 * @return Returns the _editor.
	 */
	protected GraphicalEditor getEditor() {
		return _editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		perform();
		// Since the parameters don't affect, so they could be null, may change
		// in the future
		_editor.selectionChanged(null, null);
	}

	abstract void perform();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.UpdateAction#update()
	 */
	public void update() {
		setEnabled(isEnabled());
	}

	/**
	 * @return the viewer
	 */
	protected IHTMLGraphicalViewer getViewer() {
		return ((IDesignViewer) _editor).getGraphicViewer();
	}
}
