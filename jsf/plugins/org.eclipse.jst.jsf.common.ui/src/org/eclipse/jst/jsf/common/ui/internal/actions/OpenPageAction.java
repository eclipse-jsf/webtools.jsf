/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * This class is used to open a page in the editor based on the configuration
 * parameter.
 * 
 * @author collinsc,jchoi
 */
public class OpenPageAction extends Action implements IExecutableExtension {
	private String pageID = null;

	private ISelection selection;

	/**
	 * 
	 */
	public OpenPageAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		if (pageID != null) {
			// get the active editor
			IEditorPart editor = getActiveEditor();
			IOpenPage openPage = (IOpenPage)editor.getAdapter(IOpenPage.class);
			if(openPage != null){
				openPage.setActiveEditorPage(pageID);
				if (selection != null && editor instanceof FormEditor) {
					IEditorPart activePage = ((FormEditor) editor)
							.getActiveEditor();
					if (activePage instanceof ISelectionProvider) {
						((ISelectionProvider) activePage)
								.setSelection(selection);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		pageID = config.getAttribute("actionparameters"); //$NON-NLS-1$
	}

	/**
	 * set the class name to open.
	 * @param className 
	 */
	public void setPageName(String className) {
		this.pageID = className;
	}

	/**
	 * get the current active editor
	 * 
	 * @return the active editor part
	 */
	public static IEditorPart getActiveEditor() {
		IEditorPart editor = null;
		IWorkbenchWindow win = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (win != null) {
			IWorkbenchPage page = win.getActivePage();
			if (page != null) {
				editor = page.getActiveEditor();
			}
		}
		return editor;
	}

	/**
	 * @return stored selection
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}
}
