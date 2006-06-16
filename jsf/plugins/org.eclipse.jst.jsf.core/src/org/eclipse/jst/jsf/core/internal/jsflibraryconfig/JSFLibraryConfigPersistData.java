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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.osgi.util.NLS;

/**
 * To save and retore JSF library reference configuration 
 * for <b>JSFLibraryConfigControl</b>.  
 * 
 * @author Justin Chen - Oracle
 *
 */
public class JSFLibraryConfigPersistData {
	/**
	 * Parsing delimnitor for elements in a tuple.
	 */
	final protected static String SPTR_TUPLE = ":"; //$NON-NLS-1$
	/**
	 * Parsing delimintor for tuples in a persistent property string.
	 */
	final protected static String EO_TUPLE = ";"; //$NON-NLS-1$
	
	private IProject project;
	private JSFLibraryRegistry jsfLibReg;
	private JSFLibraryDecorator dftImplLib = null;
	private JSFLibraryDecorator selJSFLibImpl = null;	
	private List selJSFLibComp = null;
	
 	protected JSFLibraryConfigPersistData(IProject project) {
		this.project = project;	
		this.jsfLibReg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
		
		// Checked when object is instanciated instead of at method call
		if (!isProjectFirstCreated()) {
			VerifySavedLibAvailability();
		}
	} 

 	/**
 	 * Return default JSF implementation library 
 	 * if there is one defined in JSF library registry.
 	 * 
 	 * @return JSFLibraryDecorator dftImplLib 
 	 */
 	protected JSFLibraryDecorator getDefaultJSFImplLib() {
 		if (dftImplLib == null) {
 			if (jsfLibReg != null) {
				JSFLibrary jsfLib = jsfLibReg.getDefaultImplementation();
				if (jsfLib != null) {
					dftImplLib = new JSFLibraryDecorator(jsfLib, true, true);
				}
	 		}
 		}
 		return dftImplLib;
 	} 	
 	
	/**
	 * Return previoulsly selected JSF implementation library.
	 * The default JSF implementation library is returned if 
	 * there is one defined in registry.  
	 * Otheriwise, null is returned.
	 * 
	 * @return JSFLibraryDecorator
	 */
 	protected JSFLibraryDecorator getSavedJSFImplLib() {
		try {
			if (!isProjectFirstCreated()) {
				if ( selJSFLibImpl == null ) {
					String strImplLibs = ((IResource)project).getPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
					selJSFLibImpl = getJSFImplLibfromPersistentProperties(getTuples(strImplLibs));
					// project created but no JSF facet installed yet.
					if (selJSFLibImpl == null) {
						selJSFLibImpl = getDefaultJSFImplLib();
					}
				}
			} else {
				selJSFLibImpl = getDefaultJSFImplLib();
			}
		} catch (CoreException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		}
		return selJSFLibImpl;
	}
	
 	
	/**
	 * Return the collection of selected JSF component libraries.
	 * An empty List is returned if no JSF comp libs saved or 
	 * the project is newly created.
	 * 
	 * @return List
	 */
	protected List getSavedJSFCompLibs() {	
		try {
			if (!isProjectFirstCreated()) {
				if ( selJSFLibComp == null ) {
					selJSFLibComp = new ArrayList(Collections.EMPTY_LIST);
					String strCompLibs = ((IResource)project).getPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_COMPONENT_LIBRARIES));
					getJSFCompLibsfromPersistentProperties(getTuples(strCompLibs), selJSFLibComp);
				}		
			} else {
				selJSFLibComp = new ArrayList(Collections.EMPTY_LIST);  
			}
		} catch (CoreException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		}
		return selJSFLibComp;
	}
	
	/**
	 * To save configuration data as a project resource.
	 * 
	 * @param selJSFLibImpl
	 * @param selJSFLibComp
	 */
	protected void saveData(List selJSFLibImpl, List selJSFLibComp) {
		try {
			((IResource)project).setPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES), generatePersistString(selJSFLibImpl));
			((IResource)project).setPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_COMPONENT_LIBRARIES), generatePersistString(selJSFLibComp));
			
			/**
			 * Flush the selection so that they are reconstructed from 
			 * persistent properties when getSavedJSFImplLib and getSavedJSFCompLibs 
			 * called next time.
			 */
			this.selJSFLibImpl = null;
			this.selJSFLibComp = null;
			
		} catch (CoreException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		}
	}
	
	
	/**
	 * Check if a project is just created by inspecting persistent properties    
	 * if there is any.  ?
	 */
 	private boolean isProjectFirstCreated() {
 		boolean isNew = false;
 		try {
			String strImplLibs = ((IResource)project).getPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
		} catch (CoreException e) {
			isNew = true;
		}
		return isNew;
 	}
	
 	private void VerifySavedLibAvailability() {
 		try {
			String strImplLibs = ((IResource)project).getPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
	 		String strCompLibs = ((IResource)project).getPersistentProperty(new QualifiedName("", JSFUtils.PP_JSF_COMPONENT_LIBRARIES));
	 		
	 		logMissingLib(getTuples(strImplLibs), true);
	 		logMissingLib(getTuples(strCompLibs), false);
	 		
		} catch (CoreException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		}		
 	} 	
 	
 	private void logMissingLib(List jsfLibTuples, boolean isVerifyImpl) {
		if (jsfLibReg != null) {
			Iterator itTuple = jsfLibTuples.iterator();
			while(itTuple.hasNext()) {
				Tuple tuple = (Tuple)itTuple.next();			
				JSFLibrary jsfLib = jsfLibReg.getJSFLibraryByID(tuple.ID);				
				// Info logged when saved JSF lib is removed from registry.
				// One log entry is created for each missing library.
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
	
	private JSFLibraryDecorator getJSFImplLibfromPersistentProperties(List jsfLibTuples) {
		if (jsfLibReg != null) {
			Iterator itTuple = jsfLibTuples.iterator();

			while(itTuple.hasNext()) {
				Tuple tuple = (Tuple)itTuple.next();			
				JSFLibrary jsfLib = jsfLibReg.getJSFLibraryByID(tuple.ID);				
				if (jsfLib != null) {
					return new JSFLibraryDecorator(jsfLib, tuple.selected, tuple.deploy);
				}
			}
		}
		return null;
	}
	
	private void getJSFCompLibsfromPersistentProperties(List jsfLibTuples, List JSFCompLibs) {		
		if (jsfLibReg != null) {
			Iterator itTuple = jsfLibTuples.iterator();	
			while(itTuple.hasNext()) {
				Tuple tuple = (Tuple)itTuple.next();
								
				JSFLibrary jsfLib = jsfLibReg.getJSFLibraryByID(tuple.ID);				
				if (jsfLib != null) {
					JSFLibraryDecorator newJSFLibDcrt = new JSFLibraryDecorator(jsfLib, tuple.selected, tuple.deploy);
					JSFCompLibs.add(newJSFLibDcrt);
				}	
			}
		}
	}
	
	private String generatePersistString(List list) {
		JSFLibraryDecorator jsfLibItem;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			jsfLibItem = (JSFLibraryDecorator)list.get(i);
			sb = sb.append(jsfLibItem.generatePersistString());
			sb.append(JSFLibraryConfigPersistData.EO_TUPLE);
		}
		return sb.toString();		
	}	
	
	private List getTuples(String strJSFLibs) {
		List list = new ArrayList(Collections.EMPTY_LIST);
		
		if (strJSFLibs != null) {
			String patternStr = JSFLibraryConfigPersistData.EO_TUPLE;
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
	 * Inner class for parsing persistent property resource. 
	 * 
	 * Note: Take out selected attribute since it is not needed.
	 *       And, to provide a way to migrating earlier 
	 *       project.
	 */
	class Tuple {
		private String ID;
		private boolean selected = false;
		private boolean deploy = true;
		
		Tuple(String ID, boolean selected, boolean deploy) {
			this.ID = ID;
			this.selected = selected;
			this.deploy = deploy;
		}
		// parse tuple = ID:selected:deploy
		Tuple(String tuple) {
			String[] fields = tuple.split(JSFLibraryConfigPersistData.SPTR_TUPLE);
			this.ID = fields[0];
			this.selected = Boolean.valueOf(fields[1]).booleanValue();
			this.deploy = Boolean.valueOf(fields[2]).booleanValue();
		}
		
		String getID() {
			return ID;
		}
		
		boolean isSelected() {
			return selected;
		}
		
		boolean needDeploy() {
			return deploy;
		}		
	}

}
