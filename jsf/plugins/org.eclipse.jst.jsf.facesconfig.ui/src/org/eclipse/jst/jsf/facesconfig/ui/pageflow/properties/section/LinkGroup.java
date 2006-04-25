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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ActionOutcomeSelectionDialog;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.ITabbedPropertiesConstants;
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
public class LinkGroup extends AbstractEditPartGroup {
	private StringButtonDialogField fromOutcomeField;

	private ComboDialogField redirectField;

	private PFLink pfLink;

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
			String outcome = "";
			String jspPage = "";
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
			if (element instanceof PFLink) {
				PageflowNode source = ((PFLink) element).getSource();
				if (source instanceof PFPage) {
					jspPath = ((PFPage) source).getPath();
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
		fromOutcomeField.setLabelText(resource
				.getString("property.fromOutcome"));
		fromOutcomeField.setButtonLabel("...");
		fromOutcomeField
				.setDialogFieldChangeListener(getDefaultChangeListener());
		redirectField = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY);
		redirectField.setLabelText(resource.getString("property.redirect"));
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

		fromOutcomeField.doFillIntoGrid(toolkit, top, numberOfColumns);
		redirectField.doFillIntoGrid(toolkit, top, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(fromOutcomeField.getTextControl(
				toolkit, top));
		GridData data = new GridData();
		data.widthHint = ITabbedPropertiesConstants.BROWSE_WIDTH;
		data.heightHint = ITabbedPropertiesConstants.BROWSE_HEIGHT;

		fromOutcomeField.getChangeControl(toolkit, top).setLayoutData(data);

	}

	public void setPropertyProvider(PFLink elem) {
		pfLink = elem;
		this.refreshData();
	}

	public StringButtonDialogField getFromOutcomeField() {
		return fromOutcomeField;
	}

	public ComboDialogField getRedirectField() {
		return redirectField;
	}

	public Integer getRedirectValue() {
		if (redirectField.getText().equalsIgnoreCase(Boolean.TRUE.toString())) {
			return LinkGroup.P_VALUE_TRUE;
		} else {
			return LinkGroup.P_VALUE_FALSE;
		}
	}
}
