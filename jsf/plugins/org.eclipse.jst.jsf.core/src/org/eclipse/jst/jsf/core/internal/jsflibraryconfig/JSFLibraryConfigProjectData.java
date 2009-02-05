/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Oracle
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties.IMPLEMENTATION_TYPE;
import org.eclipse.osgi.util.NLS;

/**
 * To construct implementation library and component libraries 
 * from persistent project properties as saved libraries.  
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryConfigProjectData implements JSFLibraryConfiglModelSource {
	final static String QUALIFIEDNAME = "org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigProjectData"; 
	/**
	 * Parsing delimnitor for elements in a tuple.
	 */
	final protected static String SPTR_TUPLE = ":"; //$NON-NLS-1$
	/**
	 * Parsing delimintor for tuples in a persistent property string.
	 */
	final protected static String EO_TUPLE = ";"; 	//$NON-NLS-1$
	
	final private IProject project;
	final private JSFLibraryRegistryUtil jsfLibReg;
	private JSFLibraryInternalReference selJSFLibImpl;	
	private List selJSFLibComp;

	/**
	 * Constructor
	 * @param project
	 */
	public JSFLibraryConfigProjectData(IProject project) {
		this.project = project;
		this.jsfLibReg = JSFLibraryRegistryUtil.getInstance();
		
		/* logging message when object is instantiated instead of at method call to 
		 * reduce log entries.
		 */ 
		if (!isProjectFirstCreated()) {
			verifySavedLibAvailability();
		}		
	}
	 	
	public IMPLEMENTATION_TYPE getImplementationType() {
		try {
			String type = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
					JSFUtils.PP_JSF_IMPLEMENTATION_TYPE));
			return IMPLEMENTATION_TYPE.getValue(type);
		} catch (CoreException e) {//
		}
		return IMPLEMENTATION_TYPE.UNKNOWN;
	}	
	
	/**
	 * Return the previously selected JSF implementation library from project persistent properties.
	 * Return null if none exists.
	 * 
	 * @return selJSFLibImpl JSFLibraryDecorator
	 */
 	public JSFLibraryInternalReference getJSFImplementationLibrary() {
		try {
			if (!isProjectFirstCreated() && 
					selJSFLibImpl == null ) {
					String strImplLibs = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
							JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
					selJSFLibImpl = getJSFImplLibfromPersistentProperties(getTuples(strImplLibs));
			}
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while returning reference to the JSF implementation library");
		}
		return selJSFLibImpl; 
	}
	
	/**
	 * Return the selected JSF component libraries from project persistent properties.
	 * An empty List is returned if no JSF component libraries were saved or 
	 * if the project is newly created.
	 * 
	 * @return selJSFLibComp List
	 */
	public List getJSFComponentLibraries() {	
		try {
			if (!isProjectFirstCreated()) {
				if ( selJSFLibComp == null ) {
					selJSFLibComp = new ArrayList();
					
					String strCompLibs = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_COMPONENT_LIBRARIES));
					List savedList = getTuples(strCompLibs);
					
					Iterator it = savedList.iterator();
					Tuple crtTuple = null;
					JSFLibraryInternalReference srcItem = null;
					while (it.hasNext()) {
						crtTuple = (Tuple) it.next();
						
						srcItem = jsfLibReg.getJSFLibraryReferencebyID(crtTuple.getID());
						if (srcItem != null) {
							selJSFLibComp.add( new JSFLibraryInternalReference(srcItem.getLibrary(), 
													true, 
													crtTuple.needDeploy()) );
						} /*else {
							// already logged a message for a missing library
						}*/
					}
				}		
			} else {
				selJSFLibComp = new ArrayList(0);  
			}
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while returning references to the JSF component libraries.");
		}
		return selJSFLibComp;
	}
	
	/**
	 * To save configuration data as a project persistent properties.
	 * 
	 * @param implementation
	 * @param component
	 */
	void saveData(final List implementation, final List component, final IMPLEMENTATION_TYPE implType) {
		try {
			((IResource)project).setPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES), generatePersistString(implementation));
			((IResource)project).setPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_COMPONENT_LIBRARIES), generatePersistString(component));
			((IResource)project).setPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_IMPLEMENTATION_TYPE), IMPLEMENTATION_TYPE.getStringValue(implType));
			
			/* Flush the selection so that they can be reconstructed from 
			 * persistent properties when getSavedJSFImplLib and getSavedJSFCompLibs 
			 * called next time.
			 */
			selJSFLibImpl = null;
			selJSFLibComp = null;
			
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while persisting the JSF Library preferences");
		}
	}
	
	/**
	 * Check if a project is just created by inspecting persistent properties    
	 * if there is any.  ?
	 */
 	private boolean isProjectFirstCreated() {
 		boolean isNew = false;
 		try {
			((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
														JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
		} catch (CoreException e) {
			isNew = true;
		}
		return isNew;
 	}
	
 	private void verifySavedLibAvailability() {
 		try {
			String strImplLibs = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
	 		String strCompLibs = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, JSFUtils.PP_JSF_COMPONENT_LIBRARIES));
	 		
	 		logMissingLib(getTuples(strImplLibs), true);
	 		logMissingLib(getTuples(strCompLibs), false);
	 		
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while verifying saved JSF Library preferences");
		}		
 	} 	
 	
 	private void logMissingLib(final List jsfLibTuples, final boolean isVerifyImpl) {
		if (jsfLibReg != null) {
			Iterator itTuple = jsfLibTuples.iterator();
			while(itTuple.hasNext()) {
				Tuple tuple = (Tuple)itTuple.next();			
				JSFLibraryInternalReference jsfLib = jsfLibReg.getJSFLibraryReferencebyID(tuple.getID());				
				/* Information logged when saved JSF lib is removed from registry.
				 * One log entry is created for each missing library.
				 */
				if (jsfLib == null) {
					String prjName = project.getName();
					String msg = (isVerifyImpl) ?
							Messages.JSFLibraryConfigPersistData_SAVED_IMPLLIB_NOT_FOUND : 
							Messages.JSFLibraryConfigPersistData_SAVED_COMPLIB_NOT_FOUND;
					JSFCorePlugin.log(IStatus.INFO, NLS.bind(msg, prjName));
				}
			}
		} 		
 	}	
	
	private JSFLibraryInternalReference getJSFImplLibfromPersistentProperties(final List jsfLibTuples) {
		if (jsfLibReg != null) {			
			Tuple tuple = null;
			JSFLibraryInternalReference lib = null;
			Iterator itTuple = jsfLibTuples.iterator();			
			while(itTuple.hasNext()) {
				tuple = (Tuple) itTuple.next();			
				lib = jsfLibReg.getJSFLibraryReferencebyID(tuple.id);				
				if (lib != null) {
					return new JSFLibraryInternalReference(lib.getLibrary(), 
							tuple.selected, 
							tuple.deploy);
				} /*else {
					// already logged a message for a missing library
				}*/
			}
		}
		return null;
	}
		
	private String generatePersistString(List list) {
		JSFLibraryInternalReference jsfLibItem;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			jsfLibItem = (JSFLibraryInternalReference)list.get(i);
			sb = sb.append(jsfLibItem.generatePersistString());
			sb.append(JSFLibraryConfigProjectData.EO_TUPLE);
		}
		return sb.toString();		
	}	
	
	private List getTuples(String strJSFLibs) {
		List list = new ArrayList();
		
		if (strJSFLibs != null) {
			String patternStr = JSFLibraryConfigProjectData.EO_TUPLE;
			String[] fields = strJSFLibs.split(patternStr);
			if (strJSFLibs.length() > 0) {
				Tuple tuple;
				for (int i = 0; i < fields.length; i++) {
					tuple = new Tuple(fields[i]);
					list.add(tuple);
				}
			}			
		}
		return list;
	}
	
	/**
	 * Inner class for parsing project persistent properties. 
	 * 
	 * To Do: Take out selected attribute since it is not needed.
	 *        Add the library name as an attribute.
	 *        Provide code path to migrate earlier project.
	 *        
	 * NOTE: this class should no longer be used except to support
	 * legacy (pre-2.0M6 library registries)
	 */
	static class Tuple {
		final private String id;
		final private boolean selected;
		final private boolean deploy;
		
		Tuple(String id, boolean selected, boolean deploy) {
			this.id = id;
			this.selected = selected;
			this.deploy = deploy;
		}
		// parse tuple = ID:selected:deploy
		Tuple(String tuple) {
			String[] fields = tuple.split(JSFLibraryConfigProjectData.SPTR_TUPLE);
			
			if (fields.length >= 3)
			{
    			this.id = fields[0];
    			this.selected = Boolean.valueOf(fields[1]).booleanValue();
    			this.deploy = Boolean.valueOf(fields[2]).booleanValue();
			}
			else
			{
			    throw new IllegalStateException("Library registry is corrupt");
			}
		}
		
		String getID() {
			return id;
		}
		
		boolean isSelected() {
			return selected;
		}
		
		boolean needDeploy() {
			return deploy;
		}		
	}
	
}
