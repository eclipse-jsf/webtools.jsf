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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ActionOutcomeSelectionDialog;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ITabbedPropertiesConstants;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author jchoi
 * @version
 */
/*package*/ class LinkGroup extends AbstractEditPartGroup {
	private StringButtonDialogField fromOutcomeField;

	private StringDialogField fromActionField;

	private ComboDialogField redirectField;

	private PageflowLink pfLink;

	/** Integer value for boolean type */
	private static final Integer P_VALUE_TRUE = new Integer(0);

	private static final Integer P_VALUE_FALSE = new Integer(1);

	private class StringButtonAdapter implements IStringButtonAdapter {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.IStringButtonAdapter#changeControlPressed(org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogField)
		 */
		public void changeControlPressed(DialogField field) {
			String outcome = ""; //$NON-NLS-1$
			String jspPage = ""; //$NON-NLS-1$
			Shell shell = field.getLabelControl(null, null).getShell();
			if (pfLink != null) {
				outcome = pfLink.getOutcome();
				jspPage = WebrootUtil.getProjectPath(pfLink,
						getPreviousJSPPath(pfLink));
				ActionOutcomeSelectionDialog actionDialog = new ActionOutcomeSelectionDialog(
						shell, outcome, jspPage);
				if (actionDialog.open() == Window.OK) {
					outcome = actionDialog.getSelectedAction();
					fromOutcomeField.setText(outcome);
					pfLink.setOutcome(outcome);
					refreshData();
				}
			}
		}

		/**
		 * @param _element
		 * @return
		 */
		private String getPreviousJSPPath(PageflowElement element) {
			String jspPath = null;
			if (element instanceof PageflowLink) {
				PageflowNode source = ((PageflowLink) element).getSource();
				if (source instanceof PageflowPage) {
					jspPath = ((PageflowPage) source).getPath();
				}
			}
			return jspPath;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		fromOutcomeField = new StringButtonDialogField(
				new StringButtonAdapter());
		fromOutcomeField.setLabelText(PropertyMessages.property_fromOutcome);
		fromOutcomeField.setButtonLabel(PropertyMessages.property_browseButton);
		fromOutcomeField
				.setDialogFieldChangeListener(getDefaultChangeListener());
		fromActionField = new StringDialogField();
		fromActionField.setLabelText(PropertyMessages.property_fromAction);
		fromActionField
				.setDialogFieldChangeListener(getDefaultChangeListener());
		redirectField = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY);
		redirectField.setLabelText(PropertyMessages.property_redirect);
		redirectField
				.setDialogFieldChangeListener((getDefaultChangeListener()));
		List redirectList;
		redirectList = new ArrayList();
		redirectList.add(Boolean.TRUE.toString());
		redirectList.add(Boolean.FALSE.toString());
		redirectField.setItems((String[]) redirectList.toArray(new String[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
		if (pfLink != null) {
			fromOutcomeField.setTextWithoutUpdate(pfLink.getOutcome());
			fromActionField.setTextWithoutUpdate(pfLink.getFromaction());
			if (pfLink.isRedirect()) {
				redirectField.setTextWithoutUpdate(Boolean.TRUE.toString());
			} else {
				redirectField.setTextWithoutUpdate(Boolean.FALSE.toString());
			}
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

		fromActionField.doFillIntoGrid(toolkit, top, numberOfColumns);
		fromOutcomeField.doFillIntoGrid(toolkit, top, numberOfColumns);
		redirectField.doFillIntoGrid(toolkit, top, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(fromOutcomeField.getTextControl(
				toolkit, top));
		GridData data = new GridData();
		data.widthHint = ITabbedPropertiesConstants.BROWSE_WIDTH;
		data.heightHint = ITabbedPropertiesConstants.BROWSE_HEIGHT;

		fromOutcomeField.getChangeControl(toolkit, top).setLayoutData(data);

	}

	/**
	 * @param elem
	 */
	public void setPropertyProvider(PageflowLink elem) {
		pfLink = elem;
		this.refreshData();
	}

	/**
	 * @return the outcome field
	 */
	public StringButtonDialogField getFromOutcomeField() {
		return fromOutcomeField;
	}

	/**
	 * @return the fromAction field
	 */
	public StringDialogField getFromActionField() {
		return fromActionField;
	}

	/**
	 * @return the redirect field
	 */
	public ComboDialogField getRedirectField() {
		return redirectField;
	}

	/**
	 * @return the redirect value
	 */
	public Integer getRedirectValue() {
		if (redirectField.getText().equalsIgnoreCase(Boolean.TRUE.toString())) {
			return LinkGroup.P_VALUE_TRUE;
		}
        return LinkGroup.P_VALUE_FALSE;
	}
}
