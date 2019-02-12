/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Dialog Cell Editor for Page path browser.
 * 
 * @author Xiao-guang Zhang
 */
public class PagePathDialogCellEditor extends EditableDialogCellEditor {
	PageflowPage element;

	/**
	 * Default constructor
	 */
	public PagePathDialogCellEditor() {
		super();
	}

	/**
	 * @param parent
	 * @param element
	 */
	public PagePathDialogCellEditor(Composite parent, PageflowPage element) {
		super(parent);
		this.element = element;
	}


	/**
	 * @param parent
	 * @param style
	 */
	public PagePathDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#openDialogBox(Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		String jsfSelection = ""; //$NON-NLS-1$
		if (getDefaultText() != null && getDefaultText().getText().length() > 0) {
			jsfSelection = getDefaultText().getText();
			IProject project = WebrootUtil.getProject(element);
			ProjectWebPageSelectionDialog dlg = new ProjectWebPageSelectionDialog(
					cellEditorWindow.getShell(), project);
			if (dlg.open() == Window.OK) {
				jsfSelection = dlg.getResultFilePath();
			}
		}
		return jsfSelection;
	}

}
