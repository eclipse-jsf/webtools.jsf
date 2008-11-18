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

import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author mengbo
 * @version 1.5
 */
public class DataBindingViewAction extends Action {

	private static Logger _log = PDPlugin
			.getLogger(DataBindingViewAction.class);

	/**
	 * Default constructor
	 */
	public DataBindingViewAction() {
		setText(ActionsMessages
				.getString("DataBindingViewAction.Menu.DataBinding")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			getPage().showView(
					"org.eclipse.jst.pagedesigner.databinding.ui.views.DataBindingsView");//$NON-NLS-1$
		} catch (PartInitException e) {
			_log.error("Error opening the DataBindingView"); //$NON-NLS-1$
		}
	}

	private IWorkbenchPage getPage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return window.getActivePage();
	}
}
