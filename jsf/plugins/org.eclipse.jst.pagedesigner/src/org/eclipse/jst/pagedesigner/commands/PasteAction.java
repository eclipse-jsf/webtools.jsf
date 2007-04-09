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

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.range.PasteCommand;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;

/**
 * @author mengbo
 */
public class PasteAction extends DesignAction {

	/**
	 * @param editor
	 */
	public PasteAction(GraphicalEditor editor) {
		super(editor, PDPlugin.getResourceString("Action.Name.Paste"));//$NON-NLS-1$
	}

	public void perform() {
		DesignerCommand command = null;
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer.isInRangeMode()) {
			command = new PasteCommand(viewer);
			command.execute();
		} else {
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer != null && viewer.isInRangeMode()
				&& viewer.getRangeSelection().isValid()) {
			Clipboard clipboard = new Clipboard(viewer.getControl()
					.getDisplay());
			return clipboard.getContents(TemplateTransfer.getInstance()) != null
					|| clipboard.getContents(TextTransfer.getInstance()) != null;
		}
		return false;
	}
}
