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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;

/**
 * Attach additonal attributes such as selection and deployment 
 * to a JSF library when it is referenced by a project.  
 * 
 * @author Justin Chen - Oracle
 * @deprecated
 */
public class JSFLibraryInternalReference {
	final private JSFLibrary jsfLib;
	private boolean check4Deploy;			// Initialized from default in workspace
	private boolean selected; 				// selected for project
	
	/**
	 * Constructor
	 * @param jsfLib JSFLibrary  instance embedded inside.
	 * @param selected boolean  true if selected, otherwise, not selected.
	 * @param deploy boolean  true if needs to be deployed, otherwise, won't be deployed.
	 */
	public JSFLibraryInternalReference(JSFLibrary jsfLib, boolean selected, boolean deploy) {
		this.jsfLib = jsfLib;
		this.selected = selected;
		this.check4Deploy = deploy;		
	}
	
	/**
	 * Return the embedded JSFLibrary instance.
	 *  
	 * @return jsfLib JSFLibrary
	 */	 
	public JSFLibrary getLibrary() {
		return jsfLib;
	}

	/**
	 * Set the to be deployed flag.
	 * 
	 * @param deploy boolean
	 */
	public void setToBeDeployed(final boolean deploy) {
		check4Deploy = deploy;
	}	
	
	/**
	 * Return true if the JSF library needs to be deployed.
	 * Otheriwse, return false.
	 * 
	 * @return boolean
	 */
	public boolean isCheckedToBeDeployed() {
		return check4Deploy;
	}

	/**
	 * Set the selected attribute to a JSFLibraryLibraryReference object.
	 * 
	 * @param selected boolean
	 */
	public void setSelected(final boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Return true if the JSF library is referenced by a project.  
	 * Otherwise, return false.
	 * 
	 * @return selected boolean
	 */
	public boolean isSelected() {
		return selected;
	}	
	
	/**
	 * To generate a string that represents the JSFLibraryLibraryReference 
	 * object for persistence. 
	 * 
	 * @return String
	 */
	protected String generatePersistString() {
		return (getID() + JSFLibraryConfigDialogSettingData.SPTR_TUPLE + 
				String.valueOf(isSelected()) + JSFLibraryConfigDialogSettingData.SPTR_TUPLE + 
				String.valueOf(isCheckedToBeDeployed())); 
	}

	/**
	 * Helper method to return the library ID from the embedded 
	 * JSFLibrary instance. 
	 * 
	 * @return id String
	 */
	public String getID() {
		return jsfLib.getID();
	}

	/**
	 * Helper method to return the library name from the embedded 
	 * JSFLibrary instance. 
	 * 
	 * @return name String
	 */
	public String getName() {
		return jsfLib.getName();
	}

	/**
	 * Helper method to return the label for the library from the embedded 
	 * JSFLibrary instance. 
	 * 
	 * @return name String
	 */
	public String getLabel() {
		return jsfLib.getLabel();
	}
	
	/**
	 * Return true if the embedded JSF library instance i implementation. 
	 * Otherwise, return false.
	 * 
	 * @return boolean
	 */
	public boolean isImplementation() {
		return jsfLib.isImplementation();
	}

	/**
	 *  Help method to return a list of Archive files from 
	 *  the embedded JSFLibrary instance.
	 * 
	 * @return boolean
	 */	
	public EList getArchiveFiles() {
		return jsfLib.getArchiveFiles();
	}
	
}
