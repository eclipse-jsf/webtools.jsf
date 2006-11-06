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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author jchoi, Xiao-guang Zhang
 * @version
 */
public class OverviewGeneralSection extends AbstractFacesConfigSection {

	private Text versionText;

	/**
	 * @param parent
	 * @param toolkit
	 * @param style
	 */
	public OverviewGeneralSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);

		getSection().setText(
				EditorMessages.OverviewPage_GeneralSection_name); //$NON-NLS-1$
		getSection().setDescription(
				EditorMessages.OverviewPage_GeneralSection_description); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.update.ui.forms.internal.FormSection#createClient(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.update.ui.forms.internal.FormWidgetFactory)
	 */
	public void createContents(Composite container, FormToolkit toolkit) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);

		IEditorInput editorInput = this.getPage().getEditor().getEditorInput();

		toolkit.createLabel(container,
				EditorMessages.OverviewPage_GeneralSection_label_name); //$NON-NLS-1$ 	 
		Text typeText = toolkit.createText(container, ((FileEditorInput)editorInput).getName());
		typeText.setEditable(false);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		typeText.setLayoutData(data);

		toolkit.createLabel(container,
				EditorMessages.OverviewPage_GeneralSection_label_version); //$NON-NLS-1$
		versionText = toolkit.createText(container, "");
		versionText.setLayoutData(data);
		versionText.setEditable(false);

		// set the service type description
		data = new GridData(GridData.FILL_HORIZONTAL);
		versionText.setLayoutData(data);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		Label sep = toolkit.createSeparator(container, SWT.HORIZONTAL);
		sep.setLayoutData(gd);
		toolkit.paintBordersFor(container);

		// return container;
	}

	public void refreshAll() {
		if (getInput() == null || !(getInput() instanceof EObject)) {
			return;
		}
		
//		FacesConfigType facesConfig = (FacesConfigType)getInput();
		// TODO get the version and display it.
		// String version = FacesConfigUtil
		// .getFacesConfigVersion((IDOMModel) getInput());
		//
		// if (null == version) {
		// Document document = ((IDOMModel) getInput()).getDocument();
		// DocumentType doctype = document.getDoctype();
		// if (doctype != null) {
		// String pid = doctype.getPublicId();
		// version = pid.substring(PUBLIC_ID.length() + 1);
		// version = version.substring(version.indexOf(VERSION_PREFIX)
		// + VERSION_PREFIX.length() + 1, version.indexOf("//"));
		// }
		// if (null == version) {
		// version = "";
		// }
		// }
		// versionText.setText(version);
	}
}
