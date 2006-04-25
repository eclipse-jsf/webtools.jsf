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
package org.eclipse.jst.jsf.facesconfig.ui.page.detail;

import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.InitializationSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.ManagedBeanGeneralSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author sfshi
 * 
 */
public class ManagedBeanDetailsPage extends FacesConfigDetailsPage {

	/**
	 * 
	 * @param page
	 */
	public ManagedBeanDetailsPage(FacesConfigMasterDetailPage page) {
		super(page);
	}

	/**
	 * create the detail sections and set layout for them.
	 */
	protected IFacesConfigSection[] createDetailSections(Composite composite,
			IManagedForm managedForm, FormToolkit toolkit, FacesConfigMasterDetailPage page) {
		ManagedBeanGeneralSection generalSection = new ManagedBeanGeneralSection(
				composite, managedForm, getPage(), toolkit);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		generalSection.getSection().setLayoutData(gd);

		InitializationSection initializationSection  = new InitializationSection(
				composite, managedForm, getPage(), toolkit);
		gd = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_FILL);
		initializationSection.getSection().setLayoutData(gd);
	
		return new IFacesConfigSection[] { generalSection, initializationSection};
	}

}
