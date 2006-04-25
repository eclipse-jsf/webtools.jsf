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

package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogFieldBase;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This dialog is used to edit managed bean's property. if the
 * bDefinitionEditable is true, the property's name, class type, and initial
 * value can be edit and changed. otherwise, only initial value can be changed.
 * 
 * @author Xiao-guang Zhang, sfshi
 */
public class EditValueDialog extends Dialog {

	/** default dialog width and height */
	private static final int VALUE_WIDTH = 380;

	private static final int VALUE_HEIGHT = 220;

	private String value;

	/** property's initla value text control */
	private Text valueText;

	private Button nullValueTypeButton;

	private boolean isNullButtonShown = false;

	private boolean isNullValue;

	public EditValueDialog(Shell parentShell, String value) {
		super(parentShell);
		this.value = value;
		this.isNullButtonShown = false;
	}

	public EditValueDialog(Shell parentShell, boolean isNullButtonShown,
			boolean isNullValue, String value) {
		super(parentShell);
		this.isNullValue = isNullValue;
		this.value = value;
		this.isNullButtonShown = isNullButtonShown;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#createDialogArea(Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout;
		Composite container = (Composite) super.createDialogArea(parent);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		container.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		if (this.isNullButtonShown) {
			nullValueTypeButton = SWTUtils.createCheckBox(container,
					IFacesConfigConstants.NULL_VALUE, 2);
			nullValueTypeButton.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					valueText.setEnabled(!nullValueTypeButton.getSelection());
					isNullValue = nullValueTypeButton.getSelection();
				}

			});
		}

		DialogField valueTitle = new DialogFieldBase();
		// ManagedBeansPage.ManagedBeanInitializationSection.MapTable.Title =
		// Values:
		valueTitle
				.setLabelText(NewEditorResourcesNLS.ManagedBeanPropertyEditDialog_Value);//$NON-NLS-1$
		valueTitle.doFillIntoGrid(null, container, 1);

		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = VALUE_WIDTH;
		gd.heightHint = VALUE_HEIGHT;
		valueText = new Text(container, SWT.MULTI | SWT.BORDER);
		valueText.setLayoutData(gd);

		initFields();
		return container;
	}

	private void initFields() {

		if (isNullButtonShown && isNullValue) {
			nullValueTypeButton.setSelection(true);
			valueText.setEnabled(false);
		} else if (value != null) {
			valueText.setText(value);
		}
	}

	/**
	 * create a inital value group
	 * 
	 * @param container -
	 *            parent composite control
	 */
	protected void createValueSection(Composite container) {
		Composite valueSection = new Composite(container, SWT.NONE);
		GridLayout gl = new GridLayout();
		// gl.marginHeight = 20;
		gl.numColumns = 2;
		valueSection.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		valueSection.setLayoutData(gd);

		Label lblValue = new Label(valueSection, SWT.LEFT);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.widthHint = 100;
		lblValue.setLayoutData(gd);

		// ManagedBeanPropertyEditDialog.Value = Value:
		lblValue
				.setText(NewEditorResourcesNLS.ManagedBeanPropertyEditDialog_Value); //$NON-NLS-1$

		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		valueText = new Text(valueSection, SWT.BORDER);
		valueText.setLayoutData(gd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#createButtonsForButtonBar(Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#configureShell(Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(NewEditorResourcesNLS.ValueEditDialog_Title); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#buttonPressed(int)
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CANCEL_ID) {
			setReturnCode(CANCEL);
			close();
			return;
		} else if (buttonId == IDialogConstants.OK_ID) {
			value = valueText.getText();
			setReturnCode(OK);
			close();
			return;
		}
		super.buttonPressed(buttonId);
	}

	public Object getResultData() {
		return value;
	}

	public boolean isNullValue() {
		return this.isNullButtonShown && isNullValue;
	}
}
