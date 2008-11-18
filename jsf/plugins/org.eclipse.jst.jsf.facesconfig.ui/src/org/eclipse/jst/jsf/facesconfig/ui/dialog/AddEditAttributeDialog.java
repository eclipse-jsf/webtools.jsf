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
 * The dialog is for adding and editing attribute.
 * 
 * @author sfshi
 * 
 */
public class AddEditAttributeDialog extends Dialog {

	private static final int MIN_DIALOG_WIDTH = 300;

	private StringDialogField attributeNameField;

	private ClassButtonDialogField attributeClassField;

	private StringDialogField defaultValueField;

	private StringDialogField suggestedValueField;

	private IProject project;

	private String attributeName;

	private String attributeClass;

	private String defaultValue;

	private String suggestedValue;

	private boolean isNew;

	/**
	 * @param parentShell
	 * @param isNew
	 */
	public AddEditAttributeDialog(Shell parentShell, boolean isNew) {
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
	public AddEditAttributeDialog(Shell parentShell, String attributeName,
			String attributeClass, String defaultValue, String suggestedValue) {
		super(parentShell);
		this.attributeName = attributeName;
		this.attributeClass = attributeClass;
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
			newShell.setText(EditorMessages.AttributeSection_Dialog_Title_Add);
		else
			newShell
					.setText(EditorMessages.AttributeSection_Dialog_Title_Edit);
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
				.setText(EditorMessages.AttributeSection_Dialog_Tab_General);
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

		attributeNameField = new StringDialogField();
//		attributeNameField.setRequired(true);
		attributeNameField
				.setLabelText(EditorMessages.AttributeSection_Dialog_AttributeName);

		attributeClassField = new ClassButtonDialogField(getProject());
//		attributeClassField.setRequired(true);
		attributeClassField
				.setLabelText(EditorMessages.AttributeSection_Dialog_AttributeClass);

		attributeClassField.setAutoOpenResource(false);

		defaultValueField = new StringDialogField();
		defaultValueField
				.setLabelText(EditorMessages.AttributeSection_Dialog_DefaultValue);

		suggestedValueField = new StringDialogField();
		suggestedValueField
				.setLabelText(EditorMessages.AttributeSection_Dialog_SuggestedValue);

		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		composite.setLayout(gl);

		attributeNameField.doFillIntoGrid(null, composite, numberOfColumns);

		attributeClassField.doFillIntoGrid(null, composite, numberOfColumns);

		defaultValueField.doFillIntoGrid(null, composite, numberOfColumns);

		suggestedValueField.doFillIntoGrid(null, composite, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(attributeClassField.getTextControl(
				null, composite));

		// set the initial value for these fields.
		attributeNameField.setText(attributeName);
		attributeClassField.setText(attributeClass);
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

	/**
	 * 
	 */
	protected void okPressed() {
		attributeName = attributeNameField.getText();
		attributeClass = attributeClassField.getText();
		defaultValue = defaultValueField.getText();
		suggestedValue = suggestedValueField.getText();

		super.okPressed();
	}

	/**
	 * @return the attribute class
	 */
	public String getAttributeClass() {
		return attributeClass;
	}

	/**
	 * @param attributeClass
	 */
	public void setAttributeClass(String attributeClass) {
		this.attributeClass = attributeClass;
	}

	/**
	 * @return the attribute name
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
