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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ITabbedPropertiesConstants;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ProjectWebPageSelectionDialog;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author jchoi
 * @version
 */
public class PageGroup extends AbstractEditPartGroup {

	private StringButtonDialogField fromViewField;

	private PageflowPage pfPage = null;

	private StringDialogField largeIconField;

	private StringDialogField smallIconField;

	class StringButtonAdapter implements IStringButtonAdapter {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.IStringButtonAdapter#changeControlPressed(org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogField)
		 */
		public void changeControlPressed(DialogField field) {
			IProject project = WebrootUtil.getProject(pfPage);
			ProjectWebPageSelectionDialog dlg = new ProjectWebPageSelectionDialog(
					field.getLabelControl(null, null).getShell(), project);
			if (dlg.open() == Window.OK) {
				fromViewField.setText(dlg.getResultFilePath());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		fromViewField = new StringButtonDialogField(new StringButtonAdapter());
		fromViewField.setLabelText(PropertyMessages.property_fromView);
		fromViewField.setButtonLabel("...");
		fromViewField.setDialogFieldChangeListener(getDefaultChangeListener());

		largeIconField = new StringDialogField();
		largeIconField.setLabelText(PropertyMessages.property_largeIcon);
		largeIconField
				.setDialogFieldChangeListener((getDefaultChangeListener()));
		smallIconField = new StringDialogField();
		smallIconField.setLabelText(PropertyMessages.property_smallIcon);
		smallIconField
				.setDialogFieldChangeListener((getDefaultChangeListener()));
		super.initialize();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
		if (pfPage != null) {
			fromViewField.setTextWithoutUpdate(assertString(pfPage.getPath()));
			largeIconField.setTextWithoutUpdate(assertString(pfPage
					.getLargeicon()));
			smallIconField.setTextWithoutUpdate(assertString(pfPage
					.getSmallicon()));
			super.refreshData(pfPage);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void layoutDialogFields(FormToolkit toolkit, Composite parent) {
		Composite top;
		if (toolkit == null) {
			top = new Composite(parent, SWT.NONE);
		} else {
			top = toolkit.createComposite(parent);
		}
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		parent.setLayout(fillLayout);

		int numberOfColumns = 4;
		GridLayout layout = new GridLayout(numberOfColumns, false);
		top.setLayout(layout);

		fromViewField.doFillIntoGrid(toolkit, top, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(fromViewField.getTextControl(toolkit,
				top));
		largeIconField.doFillIntoGrid(toolkit, top, numberOfColumns);
		smallIconField.doFillIntoGrid(toolkit, top, numberOfColumns);
		super.layoutDialogFields(toolkit, top, 4);

		GridData data = new GridData();
		data.widthHint = ITabbedPropertiesConstants.BROWSE_WIDTH;
		data.heightHint = ITabbedPropertiesConstants.BROWSE_HEIGHT;

		fromViewField.getChangeControl(toolkit, top).setLayoutData(data);
	}

	public void setPropertyProvider(PageflowPage elem) {
		pfPage = elem;
		this.refreshData();
	}

	public StringButtonDialogField getFromViewField() {
		return fromViewField;
	}

	public StringDialogField getLargeIconField() {
		return largeIconField;
	}

	public StringDialogField getSmallIconField() {
		return smallIconField;
	}

}
