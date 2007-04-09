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

import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jst.pagedesigner.commands.range.DeleteCommand;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class DeleteAction extends DesignAction {
	/**
	 * @param editor
	 */
	public DeleteAction(GraphicalEditor editor) {
		super(editor, CommandResources
				.getString("DeleteAction.CommandLabel.Delete")); //$NON-NLS-1$
	}

	public void perform() {
		DesignerCommand command = null;
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer.isInRangeMode()) {
			command = new DeleteCommand(true, viewer);
			command.execute();
		} else {
			command = new DeleteNodeCommand(viewer);
			command.execute();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer != null) {
			if (viewer.isInRangeMode()
					&& !EditModelQuery.isSame(viewer.getRangeSelection())) {
				return true;
			} else if (!viewer.isInRangeMode()) {
				int size = viewer.getSelectedEditParts().size();
				if (size > 1) {
					return true;
				} else if (size == 1
						&& !(viewer.getSelectedEditParts().get(0) instanceof DocumentEditPart)) {
					return true;
				}
			}
		}
		return false;
	}
}
