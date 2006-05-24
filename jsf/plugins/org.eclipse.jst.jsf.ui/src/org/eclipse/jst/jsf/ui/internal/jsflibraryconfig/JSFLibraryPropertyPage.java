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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.J2EEModuleDependencyDelegate;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModelAdapter;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryDecorator;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

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
		project= (IProject) element.getAdapter(IResource.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		
		List colImplLibs = new ArrayList();
		colImplLibs.add(jsfLibCfgControl.getSelectedJSFLibImplementation());
		List colCompLibs = jsfLibCfgControl.getSelectedJSFLibComponents();
		
		// update model first
		provider.saveData(colImplLibs, colCompLibs);
		
		// Update dependencies. 		
		J2EEModuleDependencyDelegate modulesDep = new J2EEModuleDependencyDelegate(project);									
		IProgressMonitor monitor = new NullProgressMonitor();		
 
		JSFLibraryDecorator newImplLib = jsfLibCfgControl.getSelectedJSFLibImplementation();
		JSFLibraryDecorator preSelectedJSFImplLib = provider.getPreviousJSFImplementation(); 
		if (preSelectedJSFImplLib != null) {
			if (!newImplLib.getID().equals(preSelectedJSFImplLib.getID())) {
				modulesDep.removeProjectDependency(preSelectedJSFImplLib.getLibrary(), monitor);
				modulesDep.addProjectDependency(newImplLib.getLibrary(), newImplLib.checkForDeploy(), monitor);
			} else {
				if (newImplLib.checkForDeploy()) {
					modulesDep.addProjectDependency(newImplLib.getLibrary(), newImplLib.checkForDeploy(), monitor);
				} else {
					modulesDep.removeProjectDependency(newImplLib.getLibrary(), monitor);
					modulesDep.addLibraryToBuildPath(newImplLib, monitor);
				}
			}
		} else {
			modulesDep.addProjectDependency(newImplLib.getLibrary(), newImplLib.checkForDeploy(), monitor);
		}

		List ComponentLibs = provider.getProjectJSFComponentLibraries();
		JSFLibraryDecorator prjJSFCompLib = null;
		JSFLibrary compLib = null;
		for (int i = 0; i < ComponentLibs.size(); i++) {
			prjJSFCompLib = (JSFLibraryDecorator) ComponentLibs.get(i);
			compLib = prjJSFCompLib.getLibrary();
			modulesDep.removeProjectDependency(compLib, monitor);
		}
		for (int i = 0; i < ComponentLibs.size(); i++) {
			prjJSFCompLib = (JSFLibraryDecorator) ComponentLibs.get(i);
			if (prjJSFCompLib.isSelected()) {
				if (prjJSFCompLib.checkForDeploy()) {			
					modulesDep.addProjectDependency(prjJSFCompLib.getLibrary(), prjJSFCompLib.checkForDeploy(), monitor);
				} else {
					modulesDep.addLibraryToBuildPath(prjJSFCompLib, monitor);
				}
			}
		}

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
		if (provider == null) {
			provider = new JSFLibraryConfigModelAdapter(project);
		}		
		
		// 
		jsfLibCfgControl = new JSFLibraryConfigControl(parent, SWT.NULL, project);
		
		/** JC Test
		   ResourcesPlugin.getWorkspace().addResourceChangeListener(jsfLibCfgControl,
				      IResourceChangeEvent.PRE_CLOSE
				      | IResourceChangeEvent.PRE_DELETE
				      | IResourceChangeEvent.PRE_AUTO_BUILD
				      | IResourceChangeEvent.POST_AUTO_BUILD
				      | IResourceChangeEvent.POST_CHANGE);		
		 End of JC Test
		 */
		
		setValid(jsfLibCfgControl.getSelectedJSFLibImplementation() != null);		
		jsfLibCfgControl.addOkClickedListener(new IJSFImplLibraryCreationListener() {
			public void okClicked(JSFImplLibraryCreationEvent event) {
				setValid(jsfLibCfgControl.getSelectedJSFLibImplementation() != null);				
			}
		});		
				
		return jsfLibCfgControl;
	}

/**		
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

	private Control createForNonJSFProject(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("No JSF facet installed.  \n\nPlease add JavaServer Faces facet from Project Facets.");
		
		return label;
	}
*/	
	
}
