/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:  Oracle
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.Messages;

/**
 * Model for the custom control <b>JSFLibraryConfigControl</b>.
 * A JSFLibraryConfigModel object is initialized from a source and updated with 
 * selected implementation and component libraries when selections are changed.
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryConfigModel {
	final private JSFLibraryConfiglModelSource data;
	final private JSFLibraryRegistryUtil jsfLibReg;
	private List colJSFImplLib;
	private List colJSFCompLib;	
	
	/**
	 * Private constructor.  
	 * @param data
	 */
	private JSFLibraryConfigModel(JSFLibraryConfiglModelSource data) {
		this.data = data;
		this.jsfLibReg = JSFLibraryRegistryUtil.getInstance();
	}
		
	/**
	 * Return JSF implementation libraries.
	 * 
	 * This list is initialized from JSF library registry and updated with persistent configuration data.  
	 * 
	 * @return List
	 * see also org.eclipse.jst.jsf.ui.properties.IJSFLibraryDecoratorProvider#getProjectJSFImplementationLibraries()
	 */
	public List getJSFImplementationLibraries() {
		if (colJSFImplLib == null) {
			/* To initialze an implementation library list from registry 
			 * and then update the list with saved implementation library.
			 */
			colJSFImplLib = jsfLibReg.getJSFImplementationLibraries();			
			JSFLibraryReference targetLib = data.getJSFImplementationLibrary();
			if (targetLib == null) {
				// no saved implementation, get default implementation library
				targetLib = jsfLibReg.getDefaultJSFImplementationLibrary();
			}
			if (targetLib != null) {
				JSFLibraryReference srcLib = jsfLibReg.getJSFLibraryReferencebyID(targetLib.getID());				
				if (srcLib != null) {
					srcLib.setSelected(true);
					srcLib.setToBeDeployed(targetLib.isCheckedToBeDeployed());				
				}
			}
		} 
		return colJSFImplLib;
	}

	/**
	 * Return JSF component libraries.
	 * 
	 * This list is initialized from JSF library registry and updated with persistent 
	 * configuration data.  
	 * 
	 * @return List
	 * see also org.eclipse.jst.jsf.ui.properties.IJSFLibraryDecoratorProvider#getProjectJSFComponentLibraries()
	 */	
	public List getJSFComponentLibraries() {
		if (colJSFCompLib == null) {
			/* To initialize a component library list from registry and then 
			 * update list with saved component libraries.
			 */
			colJSFCompLib = jsfLibReg.getJSFComponentLibraries();			
			Iterator it = data.getJSFComponentLibraries().iterator();
			JSFLibraryReference targetItem = null;
			JSFLibraryReference srcItem = null;
			while (it.hasNext()) {
				targetItem = (JSFLibraryReference) it.next();
				srcItem = jsfLibReg.getJSFLibraryReferencebyID(targetItem.getID());
				if (srcItem != null) {
					srcItem.setSelected(true);
					srcItem.setToBeDeployed(targetItem.isCheckedToBeDeployed());
				}
			}
		}
		return colJSFCompLib;
	}

	/**
	 * Return the selected JSF implementation library currently.
	 * A null is returned if none is selected.
	 * 
	 * @return JSFLibraryReference 
	 */
	public JSFLibraryReference getCurrentJSFImplementationLibrarySelection() {
		Iterator it = getJSFImplementationLibraries().iterator();
		JSFLibraryReference crtItem = null;
		while (it.hasNext()) {
			crtItem = (JSFLibraryReference) it.next();
			if (crtItem.isSelected()) {
				return crtItem;
			}
		}		
		return null;
	}
	
	/**
	 * Return the selected JSF component libraries currently.
	 * An empty list is returned when no component libraries are selected.
	 * 
	 * @return list List
	 */
	public List getCurrentJSFComponentLibrarySelection() {
		List list = new ArrayList();

		Iterator it = getJSFComponentLibraries().iterator();
		JSFLibraryReference crtItem = null;
		while (it.hasNext()) {
			crtItem = (JSFLibraryReference) it.next();
			if (crtItem.isSelected()) {
				list.add(crtItem);
			}
		}
		return list;
	}	
	
	/**
	 * Returned a saved implementation library which was persisted as 
	 * DialogSettings or as project properties.
	 * 
	 * @return JSFLibraryReference
	 */
	public JSFLibraryReference getSavedJSFImplementationLibrary() {
		return data.getJSFImplementationLibrary();
	}

	/**
	 * Returned saved component libraries which were persisted as 
	 * DialogSettings or project persistent properties.
	 *  
	 * @return List
	 */
	public List getSavedJSFComponentLibraries() {
		return data.getJSFComponentLibraries();
	}
	
	/**
	 * Update the selected JSF implementation library.
	 * 
	 * Note: The library parameter won't be not added into the collection 
	 * if it does not exist already. 
	 * 
	 * @param library JSFLibraryReference
	 */
	public void setCurrentJSFImplementationLibrarySelection(final JSFLibraryReference library) {
		if (library != null) {			
			Iterator it = getJSFImplementationLibraries().iterator();
			JSFLibraryReference crtjsflib = null;
			while (it.hasNext()) {
				crtjsflib = (JSFLibraryReference) it.next();
				if (crtjsflib.getID().equals(library.getID())) {
					crtjsflib.setSelected(true);
					crtjsflib.setToBeDeployed(library.isCheckedToBeDeployed());
				} else {
					crtjsflib.setSelected(false);
				}
			}				
		}
	}

	/**
	 * Update the JSF library component libraries selection.
	 * 
	 * @param libraries List
	 */
	public void setCurrentJSFComponentLibrarySelection(final List libraries) {
		if (libraries != null) {
			/* Reset all item in component library list to unselect first.
			 * Then, update each item in cmponent libraries to the provided list.
			 */
			setJSFLibrariesSelection(getJSFComponentLibraries(), false);
	
			Iterator it = libraries.iterator();
			JSFLibraryReference crtItem;
			JSFLibraryReference srcItem = null;
			while (it.hasNext()) {
				crtItem = (JSFLibraryReference) it.next();
				srcItem = jsfLibReg.getJSFLibraryReferencebyID(crtItem.getID());
				
				if (srcItem != null) {
					srcItem.setSelected(true);
					srcItem.setToBeDeployed(crtItem.isCheckedToBeDeployed());
				}
			}		
		}
	}
		
	/**
	 * To save current configuration of implementation and component libraries 
	 * as project properties.
	 * 
	 * @param project IProject
	 */
	public void saveData(final IProject project) {
		// Instantiate one to make sure it is for a project.
		JSFLibraryConfigProjectData data_ = new JSFLibraryConfigProjectData(project);
		List implLibs = new ArrayList();
		implLibs.add(getCurrentJSFImplementationLibrarySelection());
		data_.saveData(implLibs, getCurrentJSFComponentLibrarySelection());
	}	
	
	/**
	 * Set selection state to given state to each libray in the collection.
	 * 
	 * @param libs List
	 * @param state boolean
	 */
	private void setJSFLibrariesSelection(final List libs, final boolean state) {
		Iterator it = libs.iterator();
		JSFLibraryReference crtjsflib;
		while (it.hasNext()) {
			crtjsflib = (JSFLibraryReference) it.next();
			crtjsflib.setSelected(state);
		}		
	}	
	
	/**
	 * Factory class to create new JSFLibraryConfigModel instances
	 */
	public static final class JSFLibraryConfigModelFactory {
	    /** 
	     * To create a new instance of JSFLibraryConfigModel object.  
		 * A NullPointerException is raised if source is null.
		 * 
		 * @param source JSFLibraryConfiglModelSource 
		 * @return JSFLibraryConfigModel 
		 */
		public static JSFLibraryConfigModel createInstance(final JSFLibraryConfiglModelSource source) {
			if (source == null) {
				throw new NullPointerException(Messages.JSFLibraryConfigModel_Null_Data_Source);
			}
			return new JSFLibraryConfigModel(source);
		}
	}
	
}
