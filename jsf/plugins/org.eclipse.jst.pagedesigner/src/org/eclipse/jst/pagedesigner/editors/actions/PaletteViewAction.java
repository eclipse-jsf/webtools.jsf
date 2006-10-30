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

import org.eclipse.gef.ui.views.palette.PaletteView;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.logging.Logger;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author mengbo
 * @version 1.5
 */
public class PaletteViewAction extends Action {
	public final static String ID = "org.eclipse.jst.pagedesigner.editors.actions.PaletteViewAction"; //$NON-NLS-1$

	private static Logger _log = PDPlugin.getLogger(PaletteViewAction.class);

	public PaletteViewAction() {
		setText(ActionsMessages.getString("PaletteViewAction.Menu.PaletteView")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			getPage().showView(PaletteView.ID);
		} catch (PartInitException e) {
			_log.info("Open the Palette View", e);
		}

	}

	private IWorkbenchPage getPage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return window.getActivePage();
	}
}
