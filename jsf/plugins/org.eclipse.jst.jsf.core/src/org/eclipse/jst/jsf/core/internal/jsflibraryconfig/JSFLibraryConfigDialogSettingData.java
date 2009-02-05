/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties.IMPLEMENTATION_TYPE;

/**
 * To construct implementation library and component libraries from sticky values 
 * in DialogSettings as saved libraries.  
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryConfigDialogSettingData implements JSFLibraryConfiglModelSource {
	/**
	 * Delimintor for parsing a persistent property string.
	 */
	final protected static String SEPARATOR =":";	//$NON-NLS-1$

	final private JSFLibraryRegistryUtil jsfLibReg;
	final private boolean dftImplLibDeployFlag;
	private String[] savedCompLibs;
	private JSFLibraryInternalReference selJSFLibImpl;	// lazy initialized	
	private List selJSFLibComp;							// lazy initialized

	private IMPLEMENTATION_TYPE implType;
	
	
	/**
	 * Constructor where implementation type was chosen to be USER_SPECIFIED.  Created for backwards compatibilty when server supplied was not an option.
	 * @param implLibDeployFlag
	 * @param compLibs
	 */
	public JSFLibraryConfigDialogSettingData(boolean implLibDeployFlag, String[] compLibs) {
		this(IMPLEMENTATION_TYPE.USER_SPECIFIED, implLibDeployFlag, compLibs);		
	}
	
	/**
	 * Constructor
	 * @param implType {@link IMPLEMENTATION_TYPE}
	 * @param implLibDeployFlag String  valid options are "true" or "false"
	 * @param compLibs String[]  saved component library settings in string array
	 * 
	public JSFLibraryConfigDialogSettingData(String implLibDeployFlag, String[] compLibs) {
		this.jsfLibReg = JSFLibraryRegistryUtil.getInstance();
		this.dftImplLibDeployFlag = implLibDeployFlag;
		this.savedCompLibs = compLibs;
		
		// Verify and log a message if a saved component library no longer exists. 
		verifySavedLibAvailability();
	}
	*/
	public JSFLibraryConfigDialogSettingData(IMPLEMENTATION_TYPE implType, boolean implLibDeployFlag, String[] compLibs) {
		
		this.jsfLibReg = JSFLibraryRegistryUtil.getInstance();
		this.dftImplLibDeployFlag = implLibDeployFlag;
		this.savedCompLibs = compLibs;
		this.implType = implType;
		
		// Verify and log a message if a saved component library no longer exists. 
		verifySavedLibAvailability();
	}	
	
	/**
	 * There is no saved JSFImplLibrary per se if initializing from DialogSettings 
	 * since default implementation library is always selected and only the 
	 * deployment flag is saved.  
	 * 
	 * A null is returned when there is no default 
	 * implementation library in registry.
	 *     
	 * @return selJSFLibImpl JSFLibraryInternalReference return default implementation library with updated deployment flag 
	 */
	public JSFLibraryInternalReference getJSFImplementationLibrary() {
		if (selJSFLibImpl == null) {
			// To instanciate a JSFLibraryReferenceUserSpecified object from default impl lib as the saved library.  
			JSFLibraryInternalReference dftImplLib = jsfLibReg.getDefaultJSFImplementationLibrary(); 		
			if (dftImplLib != null) {
				selJSFLibImpl = new JSFLibraryInternalReference(dftImplLib.getLibrary(), 
						true,	// selected 
						dftImplLibDeployFlag);
			}
		}
		return selJSFLibImpl;
	}
	
	/**
	 * Return the list of saved component libraries and their deployment settings.
	 * 
	 * @return selJSFLibComp List 
	 */
	public List getJSFComponentLibraries() {
		if (selJSFLibComp == null) {
			selJSFLibComp = new ArrayList();		
			
			if (savedCompLibs != null && savedCompLibs.length > 0) {
				JSFLibraryInternalReference lib = null;
				String item;
				String[] attributes;
				String id;
				boolean deploy = false;
				
				for (int i = 0; i < savedCompLibs.length; i++) {
					item = savedCompLibs[i];
					attributes = item.split(SEPARATOR);
					
					id = attributes[0];				
					deploy = Boolean.valueOf(attributes[1]).booleanValue();
					
					lib = jsfLibReg.getJSFLibraryReferencebyID(id);
					if (lib != null) {
						selJSFLibComp.add(new JSFLibraryInternalReference(lib.getLibrary(), true, deploy));
					} /*else {
						// already logged if a saved component library is no longer available.
					}*/
				}
			}
		}
		return selJSFLibComp;
	}

	/**
	 * Only need to verify component library availability from sticky settings.
	 */
 	private void verifySavedLibAvailability() {
 		List validLibs = new ArrayList();
		if (savedCompLibs != null && savedCompLibs.length > 0) {
			String item = null;
			String[] attributes;
			boolean invalidRefs = false;
			for (int i = 0; i < savedCompLibs.length; i++) {
				item = savedCompLibs[i];
				attributes = item.split(SEPARATOR);
													
				if (jsfLibReg.getJSFLibraryReferencebyID(attributes[0]) != null) {
					validLibs.add(item);
				} else {
					invalidRefs = true;
//					JSFCorePlugin.log(IStatus.INFO, Messages.JSFLibCfgDialogSettingData_Sticky_Component_Lib_Not_Exist);
				}
			}  				
			if (invalidRefs){
				String [] validCompLibs = (String[])validLibs.toArray(new String[validLibs.size()]);
				if (validCompLibs.length > 0)
					System.arraycopy(validCompLibs, 0, savedCompLibs, 0, validCompLibs.length);
				else
					savedCompLibs = new String[]{};
			}
		}	
		

 	}
	
 	/**
 	 * @return {@link IMPLEMENTATION_TYPE}
 	 */
 	public IJSFFacetInstallDataModelProperties.IMPLEMENTATION_TYPE getImplementationType(){
 		return implType;
 	}
}
