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
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;

/**
 * To provide the following services.
 *  
 * 1. return previously selected JSF libraries. 
 * 2. return JSF libraries collection. 
 * 3. update JSF libraries selections. 
 * 4. save JSF libraries selection as property resource.
 * 5. return JSF Library Registry.
 * 6. update project dependencies based on jsf library selections.
 * 7. Return default jsf implementation library.
 * 8. Add a JSF library.
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryConfigModelAdapter {
	private IProject project;
	private JSFLibraryRegistry jsfLibReg;
	private List colJSFImplLib;
	private List colJSFCompLib;
	private JSFLibraryConfigPersistData data;
	private J2EEModuleDependencyDelegate modulesDep;

	/**
	 * Constructor.
	 * 
	 * @param project
	 */
	public JSFLibraryConfigModelAdapter(IProject project) {
		this.project = project;
		this.jsfLibReg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
		this.data = new JSFLibraryConfigPersistData(this.project);
		this.modulesDep = new J2EEModuleDependencyDelegate(this.project);
	}

	/**
	 * 	Update project dependencies based on the model  information.
	 * 
	 */
	public void updateProjectDependencies() {									
		IProgressMonitor monitor = new NullProgressMonitor();		
 
		JSFLibraryDecorator newImplLib = getSelectedJSFImplLibrary();
		JSFLibraryDecorator preSelectedJSFImplLib = getSavedJSFImplementationLibrary(); 
		if (preSelectedJSFImplLib != null) {
			modulesDep.removeProjectDependency(preSelectedJSFImplLib.getLibrary(), monitor);
			modulesDep.addProjectDependency(newImplLib.getLibrary(), newImplLib.isCheckedToBeDeployed(), monitor);
		} else {
			modulesDep.addProjectDependency(newImplLib.getLibrary(), newImplLib.isCheckedToBeDeployed(), monitor);
		}

		List ComponentLibs = getSavedJSFComponentLibraries();
		JSFLibraryDecorator prjJSFCompLib = null;
		JSFLibrary compLib = null;
		for (int i = 0; i < ComponentLibs.size(); i++) {
			prjJSFCompLib = (JSFLibraryDecorator) ComponentLibs.get(i);
			compLib = prjJSFCompLib.getLibrary();
			modulesDep.removeProjectDependency(compLib, monitor);
		}
		
		List SelComponentLibs = getSelectedJSFCompLibraries();
		JSFLibraryDecorator jsfCompLibDctr = null;		
		for (int i = 0; i < SelComponentLibs.size(); i++) {
			jsfCompLibDctr = (JSFLibraryDecorator) SelComponentLibs.get(i);
			if (jsfCompLibDctr.isSelected()) {			
				modulesDep.addProjectDependency(jsfCompLibDctr.getLibrary(), jsfCompLibDctr.isCheckedToBeDeployed(), monitor);
			}
		}
	}
	
	/**
	 * Delegate to JSFlibraryConfigPersistData and return previously selected
	 * JSF implementation library.
	 * 
	 * The return library is used to remove dependencies selected previously.
	 * 
	 * @return List
	 */
	public JSFLibraryDecorator getSavedJSFImplementationLibrary() {
		return data.getSavedJSFImplLib();
	}

	/**
	 * Delegate to JSFlibraryConfigPersistData and return a list of selected JSF
	 * component libraries previously.
	 * 
	 * The list is used to remove dependencies selected previously.
	 * 
	 * @return List
	 */
	public List getSavedJSFComponentLibraries() {
		return data.getSavedJSFCompLibs();
	}

	/**
	 * Return a list of all JSF implementation libraries in JSF Library Registry.
	 * 
	 * This list is initialized w/ persistent JSF library reference
	 * configuration data and updated constantly to reflect current
	 * selections from UI.
	 * 
	 * @return List
	 * @see org.eclipse.jst.jsf.ui.properties.IJSFLibraryDecoratorProvider#getProjectJSFImplementationLibraries()
	 */
	public List getJSFImplementationLibraries() {
		if (colJSFImplLib == null) {
			List list = new ArrayList(Collections.EMPTY_LIST);

			JSFLibraryDecorator savedImplLib = data.getSavedJSFImplLib();
			if (savedImplLib != null) {
				list.add(savedImplLib);
			}
			colJSFImplLib = buildJSFLibraryDecoratorList(jsfLibReg
					.getImplJSFLibraries(), list);
		}
		return colJSFImplLib;
	}

	/**
	 * Return a list of all JSF implementation libraries in JSF Library Registry.
	 * 
	 * This list is initialized w/ persistent JSF library reference
	 * configuration data and updated constantly to reflect current
	 * selections from UI.
	 * 
	 * @return List
	 * @see org.eclipse.jst.jsf.ui.properties.IJSFLibraryDecoratorProvider#getProjectJSFComponentLibraries()
	 */
	public List getJSFComponentLibraries() {
		if (colJSFCompLib == null) {
			colJSFCompLib = buildJSFLibraryDecoratorList(jsfLibReg
					.getNonImplJSFLibraries(), data.getSavedJSFCompLibs());
		}
		return colJSFCompLib;
	}

	/**
	 * Return currently selected JSF implementation library.
	 * 
	 * @return JSFLibraryDecorator 
	 */
	public JSFLibraryDecorator getSelectedJSFImplLibrary() {
		List list = getSelectedJSFImplLibs();
		if (list.size() > 0) {
			return (JSFLibraryDecorator) list.get(0); 
		}
		return null;
	}
	
	/**
	 * Return currently selected JSF component libraries.
	 * 
	 * @return List
	 */
	public List getSelectedJSFCompLibraries() {
		return getSelectedJSFCompLibs();
	}
	
	/**
	 * Add a JSF Library into collection for either 
	 * JSF implementation libraries or component libraries.  
	 * The decision is based on if a JSF library is an implementation.
	 * 
	 * @param jsflib
	 * @param type
	 */
	public void addJSFLibrary(JSFLibraryDecorator jsflib) {
		List list = null;
		if (jsflib.isImplementation()) {
			list = getJSFImplementationLibraries();
		} else {
			list = getJSFComponentLibraries();
		}
		list.add(jsflib);
	}
	
	/**
	 * Update selected JSF implementation library from provided library.
	 * The provided jsf library needs to be added first if it is newly created.
	 * @param jsflib 
	 */
	public void updateJSFImplementationLibrary(JSFLibraryDecorator jsflib) {
		if (jsflib != null) {
			Iterator it = getJSFImplementationLibraries().iterator();
			JSFLibraryDecorator crtjsflib = null;
			while (it.hasNext()) {
				crtjsflib = (JSFLibraryDecorator) it.next();
				if (crtjsflib.getID().equals(jsflib.getID())) {
					crtjsflib.setSelected(true);
					crtjsflib.setToBeDeployed(jsflib.isCheckedToBeDeployed());
				} else {
					crtjsflib.setSelected(false);
				}
			}				
		}
	}

	/**
	 * Update JSF library state in component libraries from given list.
	 * 
	 * @param complibs 
	 */
	public void updateJSFComponentLibraries(List complibs) {
		if (complibs != null) {
			setJSFLibrariesSelection(this.getJSFComponentLibraries(), false);
			
			Iterator it = complibs.iterator();
			JSFLibraryDecorator crtjsflib;
			while (it.hasNext()) {
				crtjsflib = (JSFLibraryDecorator) it.next();
				setJSFLibStateAsGivenTarget(getJSFComponentLibraries(), crtjsflib);
			}		
		}
	}
	
	/**
	 * Save JSF libraries selection and state as property resource.
	 */
	public void saveData() {
		data.saveData(getSelectedJSFImplLibs(), getSelectedJSFCompLibs());
		
		colJSFImplLib = null;
		colJSFCompLib = null;
	}
	
	/**
	 * Return default JSF implementation library defined in JSF Library Registry.
	 * Return null if no default implementation in registry. 
	 */
	public JSFLibraryDecorator getDefaultJSFImplemntationLibrary() {
		return data.getDefaultJSFImplLib();
	}
		
	/**
	 * Return the currently selected JSF implementation library 
	 * as a single item collection
	 * 
	 * @return List
	 */
	private List getSelectedJSFImplLibs() {
		List list = new ArrayList(Collections.EMPTY_LIST);

		Iterator it = getJSFImplementationLibraries().iterator();
		JSFLibraryDecorator jsflib = null;
		while (it.hasNext()) {
			jsflib = (JSFLibraryDecorator) it.next();
			if (jsflib.isSelected()) {
				list.add(jsflib);
				break; // Only one selection.
			}
		}
		return list;
	}

	/**
	 * Return currently selected JSF component libraries.
	 * 
	 * @return List
	 */
	private List getSelectedJSFCompLibs() {
		List list = new ArrayList(Collections.EMPTY_LIST);

		Iterator it = getJSFComponentLibraries().iterator();
		JSFLibraryDecorator jsflib = null;
		while (it.hasNext()) {
			jsflib = (JSFLibraryDecorator) it.next();
			if (jsflib.isSelected()) {
				list.add(jsflib);
			}
		}
		return list;
	}
	
	/**
	 * Set selection to true and isCheckedToBeDeployed as given target 
	 * when JSF library ID matched.
	 * 
	 * @param jsflibs
	 * @param target
	 */
	private void setJSFLibStateAsGivenTarget(List jsflibs,
			JSFLibraryDecorator target) {
		Iterator it = jsflibs.iterator();
		JSFLibraryDecorator crtjsflib = null;
		while (it.hasNext()) {
			crtjsflib = (JSFLibraryDecorator) it.next();
			if (crtjsflib.getID().equals(target.getID())) {
				crtjsflib.setSelected(true);
				crtjsflib.setToBeDeployed(target.isCheckedToBeDeployed());
				break;
			}
		}
	} 

	/**
	 * Set selection state to given state to each libray in the collection.
	 * 
	 * @param libs
	 * @param state
	 */
	private void setJSFLibrariesSelection(List libs, boolean state) {
		Iterator it = libs.iterator();
		JSFLibraryDecorator crtjsflib;
		while (it.hasNext()) {
			crtjsflib = (JSFLibraryDecorator) it.next();
			crtjsflib.setSelected(state);
		}		
	}	
	
	/**
	 * Helper method to construct a list of JSFLibraryDecorator
	 * 
	 * @param wsJSFLibs
	 * @param pjJSFLibs
	 * @return List
	 */
	private List buildJSFLibraryDecoratorList(EList wsJSFLibs, List pjJSFLibs) {
		List list = new ArrayList(Collections.EMPTY_LIST);

		JSFLibraryDecorator newPrjJSFLib;
		JSFLibraryDecorator prjJSFLib = null;
		JSFLibrary jsfLib;
		Iterator it = wsJSFLibs.iterator();
		while (it.hasNext()) {
			jsfLib = (JSFLibrary) it.next();

			Iterator itPrjLibs = pjJSFLibs.iterator();
			boolean selected = false;
			while (itPrjLibs.hasNext()) {
				prjJSFLib = (JSFLibraryDecorator) itPrjLibs.next();
				selected = prjJSFLib.getID().equals(jsfLib.getID());
				if (selected) {
					break;
				}
			}
			if (selected && prjJSFLib != null) {
				newPrjJSFLib = prjJSFLib;
			} else {
				newPrjJSFLib = new JSFLibraryDecorator(jsfLib, false, true);
			}
			list.add(newPrjJSFLib);
		}
		return list;
	}

}
