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

import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.range.CutCommand;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class CutAction extends DesignAction implements UpdateAction {
	/**
	 * @param editor
	 */
	public CutAction(GraphicalEditor editor) {
		super(editor, PDPlugin.getResourceString("Action.Name.Cut"));//$NON-NLS-1$
	}

	public void perform() {
		DesignerCommand command = null;
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer.isInRangeMode()) {
			command = new CutCommand(viewer);
			command.execute();
		} else {
			command = new CutNodeCommand(viewer);
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
			} else if (!viewer.isInRangeMode()
					&& viewer.getSelectedEditParts().size() > 0) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.UpdateAction#update()
	 */
	public void update() {
		setEnabled(isEnabled());
	}
}
