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
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
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
 * The dialog is for adding and editing a facet.
 * 
 * @author sfshi
 * 
 */
public class AddEditFacetDialog extends Dialog {

	private StringDialogField facetNameField;

	private StringDialogField displayNameField;

	private StringDialogField descriptionField;

	private boolean isNew;

	private static final int MIN_DIALOG_WIDTH = 300;

	private String facetName;

	private String displayName;

	private String description;

	/**
	 * Constructor of this dialog, could be used for adding and editing, usually
	 * it is used for adding;
	 * 
	 * @param parentShell
	 * @param isNew
	 *            whether this dialog is used for create a new facet or not;
	 */
	public AddEditFacetDialog(Shell parentShell, boolean isNew) {
		super(parentShell);
		this.isNew = isNew;
	}

	/**
	 * Usually this contructor is used for editing a facet, passing the original
	 * values of facet here.
	 * 
	 * @param parentShell
	 * @param facetName
	 *            the original text value of facet-name element;
	 * @param displayName
	 *            the original text value of display-name element;
	 * @param description
	 *            the original text value of description element;
	 */
	public AddEditFacetDialog(Shell parentShell, String facetName,
			String displayName, String description) {
		super(parentShell);
		this.facetName = facetName;
		this.displayName = displayName;
		this.description = description;
		this.isNew = false;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isNew)
			newShell.setText(EditorMessages.FacetSection_Dialog_Title_Add);
		else
			newShell.setText(EditorMessages.FacetSection_Dialog_Title_Edit);
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
		generalTabItem.setText(EditorMessages.FacetSection_Dialog_Tab_General);
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

		facetNameField = new StringDialogField();
//		facetNameField.setRequired(true);
		facetNameField
				.setLabelText(EditorMessages.FacetSection_Dialog_FacetName);

		displayNameField = new StringDialogField();
		displayNameField
				.setLabelText(EditorMessages.FacetSection_Dialog_DisplayName);

		descriptionField = new StringDialogField();
		descriptionField
				.setLabelText(EditorMessages.FacetSection_Dialog_Description);

		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		composite.setLayout(gl);

		facetNameField.doFillIntoGrid(null, composite, numberOfColumns);
		displayNameField.doFillIntoGrid(null, composite, numberOfColumns);

		descriptionField.doFillIntoGrid(null, composite, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(facetNameField.getTextControl(null,
				composite));

		// set the initial value for these fields.
		facetNameField.setText(facetName);
		displayNameField.setText(displayName);
		descriptionField.setText(description);

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

	protected void okPressed() {
		facetName = facetNameField.getText();
		displayName = displayNameField.getText();
		description = descriptionField.getText();
		super.okPressed();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the facet name
	 */
	public String getFacetName() {
		return facetName;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param facetName
	 */
	public void setFacetName(String facetName) {
		this.facetName = facetName;
	}

}
