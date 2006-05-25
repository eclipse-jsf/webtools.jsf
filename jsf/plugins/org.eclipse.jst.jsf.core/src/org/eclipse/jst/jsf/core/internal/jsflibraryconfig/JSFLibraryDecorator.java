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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl;

/**
 * Additional functionality to check if a JSF libary 
 * is selected and needs to be deloyed.
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryDecorator extends JSFLibraryImpl {
	private JSFLibrary jsfLib;
	private boolean check4Deploy;			// Initialized from default in workspace
	private boolean isSelected = false; 	// selected for project
	
	/**
	 * @param jsfLib
	 * @param selected
	 * @param deploy
	 */
	public JSFLibraryDecorator(JSFLibrary jsfLib, boolean selected, boolean deploy) {
		this.jsfLib = jsfLib;
		this.isSelected = selected;
		this.check4Deploy = deploy;		
	}
	
	/**
	 * @return JSFLibrary
	 */
	public JSFLibrary getLibrary() {
		return jsfLib;
	}
	
	/**
	 * Return true if the JSF library needs to be deployed to server.
	 * Otheriwse, return false.
	 * 
	 * The value is initialized from default value defined in JSF Library Registry.
	 * 
	 * @return boolean
	 */
	public boolean checkForDeploy() {
		return check4Deploy;
	}
	
	/**
	 * Return true if the JSF library is referenced 
	 * for the project.  Otherwise, return false.
	 * @return boolean
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param deploy
	 */
	public void setDeployment(boolean deploy) {
		this.check4Deploy = deploy;
	}
	
	/**
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.isSelected = selected;
	}
	
	/**
	 * @return String
	 */
	protected String generatePersistString() {
		String sptr = JSFLibraryConfigPersistData.SPTR_TUPLE;
		return this.getID() + sptr + String.valueOf(this.isSelected()) + sptr + String.valueOf(this.checkForDeploy()); 
	}

	public String getID() {
		return jsfLib.getID();
	}

	public String getName() {
		return jsfLib.getName();
	}

	public boolean isImplementation() {
		return jsfLib.isImplementation();
	}

	public EList getArchiveFiles() {
		return jsfLib.getArchiveFiles();
	}
	
}
