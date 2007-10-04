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

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * The dialog is for adding and editing property.
 * 
 * @author sfshi
 * 
 */
public class AddEditPropertyDialog extends Dialog {

	private static final int MIN_DIALOG_WIDTH = 300;

	private StringDialogField propertyNameField;

	private ClassButtonDialogField propertyClassField;

	private StringDialogField defaultValueField;

	private StringDialogField suggestedValueField;

	private IProject project;

	private boolean isNew;

	private String propertyName;

	private String propertyClass;

	private String defaultValue;

	private String suggestedValue;

	/**
	 * 
	 * @param parentShell
	 * @param isNew
	 */
	public AddEditPropertyDialog(Shell parentShell, boolean isNew) {
		super(parentShell);
		this.isNew = isNew;
	}

	/**
	 * 
	 * @param parentShell
	 * @param propertyName
	 * @param propertyClass
	 * @param defaultValue
	 * @param suggestedValue
	 */
	public AddEditPropertyDialog(Shell parentShell, String propertyName,
			String propertyClass, String defaultValue, String suggestedValue) {
		super(parentShell);
		this.propertyName = propertyName;
		this.propertyClass = propertyClass;
		this.defaultValue = defaultValue;
		this.suggestedValue = suggestedValue;
		this.isNew = false;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isNew)
			newShell
					.setText(EditorMessages.PropertySection_Dialog_Title_Add);
		else

			newShell
					.setText(EditorMessages.PropertySection_Dialog_Title_Edit);
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = layout.marginHeight = 5;
		container.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		TabFolder tabFolder = new TabFolder(container, SWT.FILL);
		tabFolder.setLayoutData(gd);
		TabItem generalTabItem = new TabItem(tabFolder, SWT.NONE);
		generalTabItem
				.setText(EditorMessages.PropertySection_Dialog_Tab_General);
		Control control = createGeneralTabControl(generalTabItem.getParent());
		generalTabItem.setControl(control);
		return container;
	}

	/**
	 * @param parent
	 * @return
	 */
	private Control createGeneralTabControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 5;
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		composite.setFont(parent.getFont());

		propertyNameField = new StringDialogField();
		// propertyNameField.setRequired(true);
		propertyNameField
				.setLabelText(EditorMessages.PropertySection_Dialog_PropertyName);

		propertyClassField = new ClassButtonDialogField(getProject());
		// propertyClassField.setRequired(true);
		propertyClassField
				.setLabelText(EditorMessages.PropertySection_Dialog_PropertyClass);//$NON-NLS-1$

		propertyClassField.setAutoOpenResource(false);

		defaultValueField = new StringDialogField();
		defaultValueField
				.setLabelText(EditorMessages.PropertySection_Dialog_DefaultValue);

		suggestedValueField = new StringDialogField();
		suggestedValueField
				.setLabelText(EditorMessages.PropertySection_Dialog_SuggestedValue);

		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		composite.setLayout(gl);

		propertyNameField.doFillIntoGrid(null, composite, numberOfColumns);

		propertyClassField.doFillIntoGrid(null, composite, numberOfColumns);

		defaultValueField.doFillIntoGrid(null, composite, numberOfColumns);

		suggestedValueField.doFillIntoGrid(null, composite, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(propertyClassField.getTextControl(
				null, composite));

		// set the initial value for these fields.
		propertyNameField.setText(propertyName);
		propertyClassField.setText(propertyClass);
		defaultValueField.setText(defaultValue);
		suggestedValueField.setText(suggestedValue);

		return composite;
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

	private IProject getProject() {
		return project;
	}

	protected void okPressed() {
		propertyName = propertyNameField.getText();
		propertyClass = propertyClassField.getText();
		defaultValue = defaultValueField.getText();
		suggestedValue = suggestedValueField.getText();

		super.okPressed();
	}

	/**
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the property class
	 */
	public String getPropertyClass() {
		return propertyClass;
	}

	/**
	 * @param propertyClass
	 */
	public void setPropertyClass(String propertyClass) {
		this.propertyClass = propertyClass;
	}

	/**
	 * @return the property name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the suggested value
	 */
	public String getSuggestedValue() {
		return suggestedValue;
	}

	/**
	 * @param suggestedValue
	 */
	public void setSuggestedValue(String suggestedValue) {
		this.suggestedValue = suggestedValue;
	}

	/**
	 * @param project
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

}
