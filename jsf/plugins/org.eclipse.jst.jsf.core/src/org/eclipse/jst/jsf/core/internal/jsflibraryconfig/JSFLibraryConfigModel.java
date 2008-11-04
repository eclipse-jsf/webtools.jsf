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

import org.eclipse.jst.jsf.core.internal.Messages;

/**
 * Model for the custom control <b>JSFLibraryConfigControl</b>.
 * A JSFLibraryConfigModel object is initialized from a source and updated with 
 * selected implementation and component libraries when selections are changed.
 * 
 * @author Justin Chen - Oracle
 * @deprecated
 */
public class JSFLibraryConfigModel {
	final private JSFLibraryConfigDialogSettingData data;
	final private JSFLibraryRegistryUtil jsfLibReg;
	private List colJSFCompLib;	
	
	/**
	 * Private constructor.  
	 * @param data
	 */
	private JSFLibraryConfigModel(JSFLibraryConfigDialogSettingData data) {
		this.data = data;
		this.jsfLibReg = JSFLibraryRegistryUtil.getInstance();
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
			JSFLibraryInternalReference targetItem = null;
			JSFLibraryInternalReference srcItem = null;
			while (it.hasNext()) {
				targetItem = (JSFLibraryInternalReference) it.next();
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
	 * Return the selected JSF component libraries currently.
	 * An empty list is returned when no component libraries are selected.
	 * 
	 * @return list List
	 */
	public List getCurrentJSFComponentLibrarySelection() {
		List list = new ArrayList();

		Iterator it = getJSFComponentLibraries().iterator();
		JSFLibraryInternalReference crtItem = null;
		while (it.hasNext()) {
			crtItem = (JSFLibraryInternalReference) it.next();
			if (crtItem.isSelected()) {
				list.add(crtItem);
			}
		}
		return list;
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
			JSFLibraryInternalReference crtItem;
			JSFLibraryInternalReference srcItem = null;
			while (it.hasNext()) {
				crtItem = (JSFLibraryInternalReference) it.next();
				srcItem = jsfLibReg.getJSFLibraryReferencebyID(crtItem.getID());
				
				if (srcItem != null) {
					srcItem.setSelected(true);
					srcItem.setToBeDeployed(crtItem.isCheckedToBeDeployed());
				}
			}		
		}
	}
		
	/**
	 * Set selection state to given state to each libray in the collection.
	 * 
	 * @param libs List
	 * @param state boolean
	 */
	private void setJSFLibrariesSelection(final List libs, final boolean state) {
		Iterator it = libs.iterator();
		JSFLibraryInternalReference crtjsflib;
		while (it.hasNext()) {
			crtjsflib = (JSFLibraryInternalReference) it.next();
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
		public static JSFLibraryConfigModel createInstance(final JSFLibraryConfigDialogSettingData source) {
			if (source == null) {
				throw new NullPointerException(Messages.JSFLibraryConfigModel_Null_Data_Source);
			}
			return new JSFLibraryConfigModel(source);
		}
	}
	
}
