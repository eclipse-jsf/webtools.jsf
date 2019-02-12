/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.page.detail;

import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.RendererSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.RenderkitGeneralSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Bryan Yang
 * 
 */
public class RenderkitDetailsPage extends FacesConfigDetailsPage {

	/**
	 * 
	 * @param page
	 */
	public RenderkitDetailsPage(FacesConfigMasterDetailPage page) {
		super(page);
	}

	/**
	 * create the detail sections and set layout for them.
	 */
	protected IFacesConfigSection[] createDetailSections(Composite composite,
			IManagedForm managedForm, FormToolkit toolkit, FacesConfigMasterDetailPage page) {
		RenderkitGeneralSection generalSection = new RenderkitGeneralSection(
				composite, managedForm, getPage(), toolkit);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		generalSection.getSection().setLayoutData(gd);
		
		RendererSection rendererSection = new RendererSection(composite,
				managedForm, getPage(), toolkit);
		gd = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_FILL);
		rendererSection.getSection().setLayoutData(gd);

		return new IFacesConfigSection[] { generalSection,rendererSection };
	}

}
