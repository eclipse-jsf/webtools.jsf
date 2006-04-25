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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * This is the Action for opening a palette view.
 * 
 * @author Xiao-guang Zhang
 */
public class ShowPaletteViewAction extends SelectionAction {
	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(ShowPaletteViewAction.class);

	/** The ID of the action */
	public static final String SHOW_PALETTE_VIEW = "com.sybase.stf.jmt.editors.pageflow.ShowPaletteView"; //$NON-NLS-1$ 

	/**
	 * The constructor
	 * 
	 * @param part -
	 *            the IWorkbenchPart
	 */
	public ShowPaletteViewAction(IWorkbenchPart part) {
		super(part);
		// Pageflow.Action.ShowPaletteView.Label = Show Palette View
		setText(EditorResources.getInstance().getString(
				"Pageflow.Action.ShowPaletteView.Label")); //$NON-NLS-1$
		setId(SHOW_PALETTE_VIEW);
		// Pageflow.Action.ShowPaletteView.ToolTip = Show Palette View
		setToolTipText(EditorResources.getInstance().getString(
				"Pageflow.Action.ShowPaletteView.ToolTip")); //$NON-NLS-1$
	}

	/**
	 * Determines if the action can be enabled
	 * 
	 * @return boolean - the enabled state
	 */
	protected boolean calculateEnabled() {
		return canPerformAction();
	}

	/**
	 * Determines if the action can be performed
	 * 
	 * @return boolean - the perform state
	 */
	private boolean canPerformAction() {
		if (getSelectedObjects().isEmpty()) {
			return false;
		}
		List parts = getSelectedObjects();
		for (int i = 0; i < parts.size(); i++) {
			Object o = parts.get(i);
			if (!(o instanceof EditPart)) {
				return false;
			}
			EditPart part = (EditPart) o;
			if (!((part.getModel() instanceof PageflowElement))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Runs the command
	 */
	public void run() {
		List editparts = getSelectedObjects();
		if (editparts.size() > 0) {
			EditPart part = (EditPart) editparts.get(0);
			// execute(getCommand());
			if (part.getModel() instanceof PageflowElement) {
				// ensure the visibility of the palette view and property veiw.
				IWorkbenchWindow dw = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow();
				IWorkbenchPage page = dw.getActivePage();
				if (page != null) {
					try {
						page.showView("org.eclipse.gef.ui.palette_view");
					} catch (PartInitException e) {
						// Pageflow.PageflowEditor.Error.canNotShowPaletteView =
						// Failed to show palette sheet view.
						log
								.error(
										"Pageflow.PageflowEditor.Error.canNotShowPaletteView",
										e);
					}

				}
			}
		}
	}
}
