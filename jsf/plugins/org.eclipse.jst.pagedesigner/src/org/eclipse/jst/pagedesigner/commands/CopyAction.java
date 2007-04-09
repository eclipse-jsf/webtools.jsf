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
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.range.CopyCommand;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class CopyAction extends DesignAction implements UpdateAction {
	/**
	 * @param editor
	 */
	public CopyAction(GraphicalEditor editor) {
		super(editor, PDPlugin.getResourceString("Action.Name.Copy"));//$NON-NLS-1$
		// this.setAccelerator(SWT.CTRL | SWT.INSERT);
		// this.setActionDefinitionId(ITextEditorActionDefinitionIds.CUT);
	}

	public void perform() {
		DesignerCommand command = null;
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer.isInRangeMode()) {
			command = new CopyCommand(viewer);
			command.execute();
		} else {
			Command nodeCopy = new CopyNodeCommand(viewer);
			nodeCopy.execute();
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
		this.setEnabled(isEnabled());
	}
}
