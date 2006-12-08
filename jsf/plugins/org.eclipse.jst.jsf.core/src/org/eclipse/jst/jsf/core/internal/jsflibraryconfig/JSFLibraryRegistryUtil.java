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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;

/**
 * A singleton maintains lists of implementation and component libraries 
 * in registry.
 *   
 * Each item in the lists contains a workingcopy of a JSF library and 
 * decorates with usage information such selection and deployment.  
 * 
 * The lists are updated when there are changes in JSF library registry.
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryRegistryUtil {
	private static JSFLibraryRegistryUtil instance = null;
	final private JSFLibraryRegistry jsfLibReg;
	private List implLibs = null;
	private List compLibs = null;
	
	/**
	 * Private constructor
	 */
	private JSFLibraryRegistryUtil() {
		jsfLibReg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
	}
	
	/**
	 * Return the singleton instance of JSFLibraryRegistryUtil.
	 *   
	 * @return JSFLibraryRegistryUtil
	 */
	public static JSFLibraryRegistryUtil getInstance() {
		if ( instance == null ) {
			instance = new JSFLibraryRegistryUtil();
		}
		return instance;
	}

	/**
	 * Return the JSFLibraryRegistry instance.
	 * 
	 * @return jsfLibReg JSFLibraryRegistry
	 */
	public JSFLibraryRegistry getJSFLibraryRegistry() {
		return jsfLibReg;
	}
	
	/**
	 * Get the default JSF implementation library instance.
	 * A null is returned when there is no libraries in the registry.
	 * 
	 * @return JSFLibraryDecorator
	 */
	public JSFProjectLibraryReference getDefaultJSFImplementationLibrary() {
		JSFLibrary dftImplLib = jsfLibReg.getDefaultImplementation();
		
		return ((dftImplLib != null) ? 
				getJSFLibryReferencebyID(dftImplLib.getID()) :
				null);
	}
	
	/**
	 * Get the working copy of JSF implementation libraries.
	 * The list is updated when there are changes in registry.
	 * 
	 * @return List
	 */
	List getJSFImplementationLibraries() {
		if (implLibs == null) {
			implLibs = wrapJSFLibraries(jsfLibReg.getImplJSFLibraries());
		} else {
			if (implLibs.size() != jsfLibReg.getImplJSFLibraries().size() || 
					isAnyLibraryChanged(implLibs)) {
				implLibs.clear();
				implLibs = wrapJSFLibraries(jsfLibReg.getImplJSFLibraries());
			}
		}
		return implLibs;
	}
		
	/**
	 * Get the working copy of JSF component libraries.
	 * The list is updated when there are changes in registry.
	 * 
	 * @return List
	 */
	List getJSFComponentLibraries() {
		if (compLibs == null) {
			compLibs = wrapJSFLibraries(jsfLibReg.getNonImplJSFLibraries());
		} else {
			if (compLibs.size() != jsfLibReg.getNonImplJSFLibraries().size() || 
					isAnyLibraryChanged(compLibs)) {
				compLibs.clear();
				compLibs = wrapJSFLibraries(jsfLibReg.getNonImplJSFLibraries());
			}			
		}
		return compLibs;
	}
	
	/**
	 * Get the JSFLibraryDecorator object from the provided ID. 
	 * A null is returned no library matches the ID.
	 * 
	 * @param id String
	 * @return JSFLibraryDecorator
	 */
	public JSFProjectLibraryReference getJSFLibryReferencebyID(final String id) {
		Iterator it = getJSFImplementationLibraries().iterator();
		JSFProjectLibraryReference crtItem = null;
		
		// search implementation libraries
		while(it.hasNext()) {
			crtItem = (JSFProjectLibraryReference)it.next();
			if (id.equals(crtItem.getID())) {
				return crtItem;
			}
		}
		// search component libraries
		it = getJSFComponentLibraries().iterator();
		while(it.hasNext()) {
			crtItem = (JSFProjectLibraryReference)it.next();
			if (id.equals(crtItem.getID())) {
				return crtItem;
			}
		}
		return null;
	}

	/**
	 * Add a JSF Library into collection for either 
	 * JSF implementation libraries or component libraries.  
	 * The decision is based on if a JSF library is an implementation.
	 * 
	 * @param library JSFProjectLibraryReference
	 */
	public void addJSFLibrary(final JSFProjectLibraryReference library) {
		 // Library is added only if it does not exist in registry 
		if (library != null && jsfLibReg.getJSFLibraryByID(library.getID()) == null) {
			// Add the library working copy into workspace registry.
			JSFLibrary jsfLib = library.getLibrary();
			jsfLibReg.addJSFLibrary(jsfLib.getWorkingCopy());
			
			// Add library into the collection depends on its type.
			List list = (library.isImplementation() ? 
							getJSFImplementationLibraries() :
							getJSFComponentLibraries());
			list.add(library);
		}
	}	
	
	private List wrapJSFLibraries(final EList libs) {
		List list = new ArrayList();
		if (libs != null) {
			JSFLibrary jsfLib;
			JSFProjectLibraryReference jsfLibDctr;
			
			Iterator it = libs.iterator();
			while (it.hasNext()) {
				jsfLib = (JSFLibrary) it.next();
				 // Set unselected and undeployed initially.
				jsfLibDctr = new JSFProjectLibraryReference(jsfLib.getWorkingCopy(), 
								false, 
								false);
				list.add(jsfLibDctr);
			}
		}	
		return list;		
	}

	private boolean isAnyLibraryChanged(final List list) {
		Iterator it = list.iterator();
		JSFProjectLibraryReference wclib = null;		// working copy library
		JSFLibrary lib = null;
		
		while(it.hasNext()) {
			wclib = (JSFProjectLibraryReference)it.next();
			lib = getJSFLibraryRegistry().getJSFLibraryByID(wclib.getID());
			if (lib == null) {					// removed. Hence, changed.
				return true;
			}
			if (wclib.getArchiveFiles().size() != 
				lib.getArchiveFiles().size()) { // Archives changed..
				return true;
			}
			if (isAnyArchiveFileChanged(wclib.getArchiveFiles(), 
					lib.getArchiveFiles())) {   // Check archive file changes.  I.e., name and location
				return true;
			}
		}
		return false;
	}

	private boolean isAnyArchiveFileChanged(final EList source, EList target) {		
		ArchiveFile arSrc = null;
		Iterator it = source.iterator();
		while (it.hasNext()) {
			arSrc = (ArchiveFile) it.next();
			if (!findMatchedArchive(arSrc, target)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean findMatchedArchive(ArchiveFile source, EList list) {
		ArchiveFile target = null;
		Iterator it = list.iterator();
		while (it.hasNext()) {
			target = (ArchiveFile) it.next();
			if (target.equals(source)) {
				return true;
			}
		}
		return false;
	}
	
}
