/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen - development check in
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModelAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Justin Chen - Oracle
 */
public class JSFLibraryPropertyPage extends PropertyPage {
	private static String JSF_FACET_ID = "jst.jsf"; //$NON-NLS-1$
	private JSFLibraryConfigControl jsfLibCfgControl;
	private JSFLibraryConfigModelAdapter provider;
	private IProject project;
		
	/**
	 * Constructor for SamplePropertyPage.
	 */
	public JSFLibraryPropertyPage() {
		super();
		super.noDefaultAndApplyButton();
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.PropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
	 */
	public void setElement(IAdaptable element) {
		super.setElement(element);
		project = (IProject) element.getAdapter(IResource.class);
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {

		// Do nothing because of invalid settings.
		if (!validatePage()) {
			return true;
		}
		
		provider = jsfLibCfgControl.getModelProvider();		
		provider.updateProjectDependencies();
		provider.saveData();	// save library configuration data
		
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {		
		//if (isJSFFacetInstalled()) {			
			return createForJSFProject(parent);
		//} else {
		//	return createForNonJSFProject(parent);
		//}
	}

	private Control createForJSFProject(Composite parent) {		 
		jsfLibCfgControl = new JSFLibraryConfigControl(parent, SWT.NULL);
		jsfLibCfgControl.initControlsValues(project);
				
		jsfLibCfgControl.addOkClickedListener(new IJSFImplLibraryCreationListener() {			
			public void okClicked(JSFImplLibraryCreationEvent event) {
				validatePage();
				/*setValid(jsfLibCfgControl.getSelectedJSFLibImplementation() != null && 
						isJSFFacetInstalled());
				*/
			}
		});		
		
		validatePage();
		
		return jsfLibCfgControl;
	}

	protected boolean validatePage() {
		if (!isJSFFacetInstalled() || jsfLibCfgControl.getSelectedJSFLibImplementation() == null) {
			if (!isJSFFacetInstalled()) {
				JSFLibraryPropertyPage.this.setErrorMessage("JSF Facet not installed.");
			} 
			if (jsfLibCfgControl.getSelectedJSFLibImplementation() == null) {
				JSFLibraryPropertyPage.this.setErrorMessage("No JSF implementation library selected.");
			}
			return false;
		}/* else {
			this.setMessage("Configurations are valid.", IMessageProvider.INFORMATION);
		}*/
		return true;
	}
	
	private boolean isJSFFacetInstalled() {
		if (project == null) {
			return false;
		}
		
		try {
			IFacetedProject fProject = ProjectFacetsManager.create(project);
			
			Iterator it = ((IFacetedProject)fProject).getProjectFacets().iterator();
			IProjectFacetVersion fv = null;
			String facetId = null;
			while (it.hasNext()) {
				fv = ((IProjectFacetVersion)it.next());
				facetId = fv.getProjectFacet().getId();
				if (facetId.equals(JSF_FACET_ID)) {
					return true;
				}
			}	
			
		} catch (CoreException e) {
			
		}		
		return false;

	}
		
}
