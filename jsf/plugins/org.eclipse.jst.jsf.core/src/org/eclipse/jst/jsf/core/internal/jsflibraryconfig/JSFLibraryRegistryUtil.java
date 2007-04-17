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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFLibrariesContainerInitializer;
import org.eclipse.jst.jsf.core.internal.JSFLibraryClasspathContainer;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;

/**
 * A singleton maintains lists of implementation and component libraries 
 * in registry.
 *   
 * Each item in the lists contains a workingcopy of a JSF library and 
 * decorates with usage information such selection and deployment.  
 * 
 * The lists are updated when there are changes in JSF library registry.
 * 
 * @author Justin Chen, etc. - Oracle
 * TODO: consider making all methods static
 */
public class JSFLibraryRegistryUtil {
	private static JSFLibraryRegistryUtil instance = null;
	private List implLibs = null;
	private List compLibs = null;
	
	/**
	 * Private constructor
	 */
	private JSFLibraryRegistryUtil() {
	    // no external instantiation
	}
	
	/**
	 * Return the singleton instance of JSFLibraryRegistryUtil.
	 *   
	 * @return JSFLibraryRegistryUtil
	 */
	public synchronized static JSFLibraryRegistryUtil getInstance() {
		if ( instance == null ) {
			instance = new JSFLibraryRegistryUtil();
		}
		return instance;
	}

	/**
	 * Convenience method to return the JSFLibraryRegistry instance.
	 * 
	 * @return jsfLibReg JSFLibraryRegistry
	 */
	public JSFLibraryRegistry getJSFLibraryRegistry() {
		return JSFCorePlugin.getDefault().getJSFLibraryRegistry();
	}
	
	/**
	 * Get the default JSF implementation library instance.
	 * A null is returned when there is no libraries in the registry.
	 * 
	 * @return JSFLibraryDecorator
	 */
	public JSFLibraryReference getDefaultJSFImplementationLibrary() {
		JSFLibrary dftImplLib = getJSFLibraryRegistry().getDefaultImplementation();
		
		return ((dftImplLib != null) ? 
				getJSFLibraryReferencebyID(dftImplLib.getID()) :
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
			implLibs = wrapJSFLibraries(getJSFLibraryRegistry().getImplJSFLibraries());
		} else {
			if (implLibs.size() != getJSFLibraryRegistry().getImplJSFLibraries().size() || 
					isAnyLibraryChanged(implLibs)) {
				implLibs.clear();
				implLibs = wrapJSFLibraries(getJSFLibraryRegistry().getImplJSFLibraries());
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
			compLibs = wrapJSFLibraries(getJSFLibraryRegistry().getNonImplJSFLibraries());
		} else {
			if (compLibs.size() != getJSFLibraryRegistry().getNonImplJSFLibraries().size() || 
					isAnyLibraryChanged(compLibs)) {
				compLibs.clear();
				compLibs = wrapJSFLibraries(getJSFLibraryRegistry().getNonImplJSFLibraries());
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
	public JSFLibraryReference getJSFLibraryReferencebyID(final String id) {
		Iterator it = getJSFImplementationLibraries().iterator();
		JSFLibraryReference crtItem = null;
		
		// search implementation libraries
		while(it.hasNext()) {
			crtItem = (JSFLibraryReference)it.next();
			if (id.equals(crtItem.getID())) {
				return crtItem;
			}
		}
		// search component libraries
		it = getJSFComponentLibraries().iterator();
		while(it.hasNext()) {
			crtItem = (JSFLibraryReference)it.next();
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
	 * @param library JSFLibraryReference
	 */
	public void addJSFLibrary(final JSFLibraryReference library) {
		 // Library is added only if it does not exist in registry 
		if (library != null && getJSFLibraryRegistry().getJSFLibraryByID(library.getID()) == null) {
			// Add the library working copy into workspace registry.
			JSFLibrary jsfLib = library.getLibrary();
			getJSFLibraryRegistry().addJSFLibrary(jsfLib.getWorkingCopy());
			
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
			JSFLibraryReference jsfLibDctr;
			
			Iterator it = libs.iterator();
			while (it.hasNext()) {
				jsfLib = (JSFLibrary) it.next();
				 // Set unselected and undeployed initially.
				jsfLibDctr = new JSFLibraryReference(jsfLib, //.getWorkingCopy(), 
								false, 
								false);
				list.add(jsfLibDctr);
			}
		}	
		return list;		
	}

	private boolean isAnyLibraryChanged(final List list) {
		Iterator it = list.iterator();
		JSFLibraryReference wclib = null;		// working copy library
		JSFLibrary lib = null;
		
		while(it.hasNext()) {
			wclib = (JSFLibraryReference)it.next();
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

	/**
	 * Get the classpath entries for a JSF Library
	 * @param lib
	 * @return IClasspathEntry[]
	 */
	public IClasspathEntry[] getClasspathEntries(JSFLibrary lib){		
		//TODO: cache to optimize.   probably belongs inside JSFLibrary model.
		ArrayList res= new ArrayList(lib.getArchiveFiles().size());
		for (Iterator it=lib.getArchiveFiles().iterator();it.hasNext();) {
			ArchiveFile jar= (ArchiveFile)it.next();			
			if (jar != null && jar.exists()) {
				IClasspathEntry entry = getClasspathEntry(jar);
				if (entry != null)
					res.add(entry);
			}
		}
		IClasspathEntry[] entries= (IClasspathEntry[]) res.toArray(new IClasspathEntry[res.size()]);
		return entries;
	}
	
	/**
	 * Create IClasspathEntry for ArchiveFile
	 * @param jar
	 * @return IClasspathEntry
	 */
	public IClasspathEntry getClasspathEntry(ArchiveFile jar){
		IClasspathEntry entry = null;
		if (jar !=null && jar.exists()){
			entry = JavaCore.newLibraryEntry(new Path(jar.getResolvedSourceLocation()), null, null);//, nu, sourceAttachRoot, accessRules, extraAttributes, false/*not exported*/);
		}
		return entry;
	}
	
	/**
	 * Binds JSF Libraries to classpath containers when the library changes.
	 * 
	 * This method will deal with library/cp container renames by removing the old classpath container and then adding.
	 * 
	 * @param oldId
	 * @param newId
	 * @param removeAndAddBecauseOfRename
	 * @param monitor
	 * @throws JavaModelException
	 */
	public static void rebindClasspathContainerEntries(String oldId, String newId, boolean removeAndAddBecauseOfRename, IProgressMonitor monitor) throws JavaModelException {
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		IJavaProject[] projects= JavaCore.create(root).getJavaProjects();
		IPath containerPath= new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID).append(newId);
		IPath oldContainerPath = new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID).append(oldId);
		
		JSFLibrary lib = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(newId);
		List affectedProjects= new ArrayList();
		removeAndAddBecauseOfRename = (!oldId.equals(newId));
		// find all projects using the old container name...
		for (int i= 0; i < projects.length; i++) {
			IJavaProject project= projects[i];
			IClasspathEntry[] entries= project.getRawClasspath();
			for (int k= 0; k < entries.length; k++) {
				IClasspathEntry curr= entries[k];
				if (curr.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					if (oldContainerPath.equals(curr.getPath())) {
						affectedProjects.add(project);
						break;
					}				
				}
			}
		}
		
		if (!affectedProjects.isEmpty()) {
			IJavaProject[] affected= (IJavaProject[]) affectedProjects.toArray(new IJavaProject[affectedProjects.size()]);
			IClasspathContainer[] containers= new IClasspathContainer[affected.length];
			removeAndAddBecauseOfRename = (!oldId.equals(newId));
			if (removeAndAddBecauseOfRename){//not very pretty... remove and add new container				
				IClasspathEntry newEntry = JavaCore.newContainerEntry(containerPath);
				for (int i= 0; i < affected.length; i++) {
					IJavaProject project= affected[i];
					IClasspathEntry[] entries= project.getRawClasspath();
					List keptEntries = new ArrayList();
					//keep all entries except the old one
					for (int k= 0; k < entries.length; k++) {
						IClasspathEntry curr= entries[k];
						if (curr.getEntryKind() == IClasspathEntry.CPE_CONTAINER){
								if( ! oldContainerPath.equals(curr.getPath())) 
							keptEntries.add(curr);						
						}
						else {
							keptEntries.add(curr);
						}						
					}
					// add new container entry
					keptEntries.add(newEntry);
					setRawClasspath(project, keptEntries, monitor);
				}
				
			}
			else {//rebind

				JSFLibraryClasspathContainer container= new JSFLibraryClasspathContainer(lib);
				containers[0] = container;
	
				JavaCore.setClasspathContainer(containerPath, affected, containers, monitor);
			}
		} else {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	/**
	 * Sets the raw classpath on a project and logs an error if it when a JavaModelException occurs
	 * @param project
	 * @param cpEntries
	 * @param monitor
	 */
	public static void setRawClasspath(IJavaProject project, List cpEntries, IProgressMonitor monitor) {
		IClasspathEntry[] entries = (IClasspathEntry[])cpEntries.toArray(new IClasspathEntry[0]);
		try {
			project.setRawClasspath(entries, monitor);
		} catch (JavaModelException e) {
			JSFCorePlugin.log(e, "Unable to set classpath for: "+project.getProject().getName());
		}
	}
	
	/**
	 * @param iproject
	 * @return true if iproject has persistent properties indicating that it may still
	 * be using V1 JSF Library references
	 */
	public static boolean doesProjectHaveV1JSFLibraries(IProject iproject)
	{
       try
        {
            Object compLib = iproject.getPersistentProperty(new QualifiedName(JSFLibraryConfigProjectData.QUALIFIEDNAME, JSFUtils.PP_JSF_COMPONENT_LIBRARIES));
            Object implLib = iproject.getPersistentProperty(new QualifiedName(JSFLibraryConfigProjectData.QUALIFIEDNAME, JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
            
            if (compLib != null || implLib != null)
            {
                return true;
            }
            return false;
        }
        catch(CoreException ce)
        {
            JSFCorePlugin.log(ce, "Error checking age of project");
            return false;
        }
	}
	
	/**
	 * Removes the persistent property from JSF projects tagged with
	 * V1 JSF libraries.
	 * @param projects
	 */
	public static void removeV1JSFLibraryProperty(List<IProject> projects)
	{
	    for (final Iterator<IProject> it = projects.iterator(); it.hasNext();)
	    {
	        IProject project = it.next();
            try {
                project.setPersistentProperty(new QualifiedName(JSFLibraryConfigProjectData.QUALIFIEDNAME, JSFUtils.PP_JSF_COMPONENT_LIBRARIES), null);
                project.setPersistentProperty(new QualifiedName(JSFLibraryConfigProjectData.QUALIFIEDNAME, JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES), null);
            } catch (CoreException e) {
                JSFCorePlugin.log(e, "Error removing JSF library persistent property");
            }
	    }
	}
}
