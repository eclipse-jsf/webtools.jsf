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
 * 
 * Dialog Cell Editor for Action browser.
 * 
 * @author Xiao-guang Zhang
 */
public class ActionDialogCellEditor extends EditableDialogCellEditor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor()
	 */
	public ActionDialogCellEditor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent)
	 */
	public ActionDialogCellEditor(Composite parent) {
		super(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent, int style)
	 */
	public ActionDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		String actionExpression = getDefaultText().getText();
		// IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
		// Create a new jsf Wizard

		// FIXME sfshi 2005-11-16
		// ActionSelectionWizard actionSelectionWizard =
		// new ActionSelectionWizard(ActionSelectionWizard.CONSIDER_METHODS);
		// actionSelectionWizard.init(EditorPlugin.getDefault().getWorkbench(),
		// selectionToPass);
		// actionSelectionWizard.setActionExpression(actionExpression);
		// //using a wizard dialog to display the new jsf wizard
		// WizardDialog actionSelectionWizardDialog =
		// new WizardDialog(cellEditorWindow.getShell(),actionSelectionWizard);
		// if (actionSelectionWizardDialog.open() == WizardDialog.OK )
		// {
		// actionExpression = actionSelectionWizard.getActionExpression();
		// }
		return actionExpression;
	}
}
