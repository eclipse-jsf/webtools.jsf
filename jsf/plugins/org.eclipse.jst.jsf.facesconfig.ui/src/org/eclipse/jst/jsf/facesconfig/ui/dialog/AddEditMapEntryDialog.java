/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The dialog is for adding and editing map-entry.
 * 
 * @author sfshi
 * 
 */
public class AddEditMapEntryDialog extends Dialog {

	private static final int MIN_DIALOG_WIDTH = 300;

	private static final int VALUE_DEFAULT_LINENUMS = 10;

	private boolean isNew;

	private StringDialogField keyField;

	private Button nullValueTypeButton;

	private StringDialogField valueField;

	private String key;

	private String value;

	private boolean isNullValue;

	/**
	 * @param parentShell
	 * @param isNew
	 */
	public AddEditMapEntryDialog(Shell parentShell, boolean isNew) {
		super(parentShell);
		this.isNew = isNew;
	}

	/**
	 * 
	 * @param parentShell
	 * @param attributeName
	 * @param attributeClass
	 * @param defaultValue
	 * @param suggestedValue
	 */
	public AddEditMapEntryDialog(Shell parentShell, String attributeName,
			String attributeClass, String defaultValue, String suggestedValue) {
		super(parentShell);
		this.isNew = false;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isNew)
			// TODO change it to "add"
			newShell.setText(EditorMessages.MapEntryEditPage_Title);
		else
			newShell.setText(EditorMessages.MapEntryEditPage_Title);
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		gl.numColumns = 1;
		container.setLayout(gl);

		layoutKeySection(container);
		layoutValueSection(container);
		if (!isNew) {
			initFields();
		}
		return container;
	}

	private void initFields() {
		keyField.setText(key);
		if (this.isNullValue) {
			nullValueTypeButton.setSelection(true);
			valueField.setText(""); //$NON-NLS-1$
			valueField.setEnabled(false);
		} else {
			nullValueTypeButton.setSelection(false);
			valueField.setEnabled(true);
			valueField.setText(value);
		}
	}

	/**
	 * @param parent
	 */
	public void layoutKeySection(Composite parent) {
		keyField = new StringDialogField();
		keyField.setLabelText(EditorMessages.MapEntryEditGroup_Key); //$NON-NLS-1$
		Composite

		keySection = SWTUtils.createComposite(parent, SWT.NONE);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		keySection.setLayoutData(gd);

		int numberOfColumns = 3;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		keySection.setLayout(gl);

		keyField.doFillIntoGrid(null, keySection, numberOfColumns);

		LayoutUtil.setGrabHorizontal(keyField.getTextControl(null, keySection),
				true);

	}

	/**
	 * @param parent
	 */
	public void layoutValueSection(Composite parent) {
		Composite valueSection = SWTUtils.createComposite(parent, SWT.NONE);

		GridData gd = new GridData(GridData.FILL_BOTH);
		valueSection.setLayoutData(gd);

		int numberOfColumns = 3;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		gl.verticalSpacing = 10;
		gl.marginHeight = 10;
		valueSection.setLayout(gl);

		nullValueTypeButton = SWTUtils.createCheckBox(valueSection,
				EditorMessages.AddEditMapEntryDialog_NullValue, 2);

		nullValueTypeButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				valueField.setEnabled(!nullValueTypeButton.getSelection());

			}

		});
		valueField = new StringDialogField(VALUE_DEFAULT_LINENUMS);

		valueField.setLabelText(EditorMessages.ValueEditGroup_Value); //$NON-NLS-1$
		valueField.doFillIntoGrid(null, valueSection, numberOfColumns);

		gd = (GridData) valueField.getLabelControl(null, valueSection)
				.getLayoutData();
		gd.verticalAlignment = gd.verticalAlignment | GridData.GRAB_VERTICAL;
		LayoutUtil.setHorizontalGrabbing(valueField.getTextControl(null,
				valueSection));

		gd = (GridData) valueField.getTextControl(null, valueSection)
				.getLayoutData();
		gd.verticalAlignment = gd.verticalAlignment | GridData.FILL_VERTICAL;

	}

	/*
	 * @see org.eclipse.jface.window.Window#getInitialSize()
	 */
	protected Point getInitialSize() {
		Point shellSize = super.getInitialSize();
		return new Point(Math.max(
				convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
				shellSize.y);
	}

	/**
	 * 
	 */
	protected void okPressed() {

		key = this.keyField.getText().trim();
		value = this.valueField.getText().trim();
		isNullValue = nullValueTypeButton.getSelection();
		super.okPressed();
	}

	/**
	 * @return true if is null
	 */
	public boolean isNullValue() {
		return isNullValue;
	}

	/**
	 * @param isNullValue
	 */
	public void setNullValue(boolean isNullValue) {
		this.isNullValue = isNullValue;
	}

	/**
	 * @return the key 
	 */ 
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
