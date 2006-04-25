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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Dialog Cell Editor for Page path browser.
 * 
 * @author Xiao-guang Zhang
 */
public class PagePathDialogCellEditor extends EditableDialogCellEditor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor()
	 */
	public PagePathDialogCellEditor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent)
	 */
	public PagePathDialogCellEditor(Composite parent) {
		super(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent, int style)
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
		// FIXME: remove the get the project path and the webroot path later.
		String jsfSelection = "";
		if (getDefaultText() != null && getDefaultText().getText().length() > 0) {
			jsfSelection = getDefaultText().getText();
			// jsfSelection =
			// PageflowModelManager.getProjectPath(getDefaultLabel().getText());
		}
		// IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
		// Create a new jsf Wizard
		// TODO create a new jsf
		/**
		 * JSFFileSelectionWizard jsfSelectionWizard = new
		 * JSFFileSelectionWizard();
		 * jsfSelectionWizard.init(EditorPlugin.getDefault().getWorkbench(),
		 * selectionToPass); jsfSelectionWizard.setJSFSelection(jsfSelection);
		 * //using a wizard dialog to display the new jsf wizard WizardDialog
		 * jsfSelectionWizardDialog = new
		 * WizardDialog(cellEditorWindow.getShell(), jsfSelectionWizard); if
		 * (jsfSelectionWizardDialog.open() == WizardDialog.OK) { jsfSelection =
		 * jsfSelectionWizard.getJSFSelection(); } //jsfSelection =
		 * PageflowModelManager.getWebPath(jsfSelection);
		 */

		return jsfSelection;
	}

}
