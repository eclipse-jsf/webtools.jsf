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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.j2ee.application.internal.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.internal.operations.ClasspathElement;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.common.operations.UpdateJavaBuildPathOperation;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.internal.ui.WTPUIPlugin;

/**
 * The <b>J2EEModuleDependencyDelegate</b> provide services to update 
 * J2EE module dependencies and java build path if JSF libraries 
 * are referenced in a web application. 
 * 
 * @author Justin Chen - Oracle
 *
 */
public class J2EEModuleDependencyDelegate {	
	private IProject project = null;
	private ClasspathModel model = null;

	/**
	 * @param project
	 */
	public J2EEModuleDependencyDelegate(IProject project) {
		this.project = project;
		this.model = newClasspathModel(project);
	}	
	
	/**
	 * Add given <b>jsfLibrary</b> as project dependency.
	 * If <b>toDeploy</b> flag is true, the JSF library JARs will be 
	 * added as J2EE module dependencies.  
	 * Otherwise, JARs are added in project build path only.
	 * 
	 * @param jsfLibrary
	 * @param toDeploy
	 * @param monitor
	 */
	public void addProjectDependency(JSFLibrary jsfLibrary, boolean toDeploy, IProgressMonitor monitor) {
		IPath[] jarPaths = JSFUtils.getJARPathforJSFLib(jsfLibrary);
		this.updateProjectDependency(jarPaths, toDeploy, monitor);		
	}	

	/**
	 * Remove given <b>jsfLibrary</b> from project dependency.
	 * 
	 * @param jsfLibrary
	 * @param monitor
	 */
	public void removeProjectDependency(JSFLibrary jsfLibrary, IProgressMonitor monitor) {
		IPath[] elements = JSFUtils.getJARPathforJSFLib(jsfLibrary);
		this.removeProjectDependency_(elements, monitor);
	}
	
	/**
	 * @param jsfLibrary
	 * @param monitor
	 * 
	public void addLibraryToBuildPath(JSFLibraryDecorator jsfLibrary, IProgressMonitor monitor) {
		IPath[] elements = JSFUtils.getJARPathforJSFLib(jsfLibrary.getLibrary());
		this.updateProjectDependency(elements, false, monitor);
	}
	*/
	
	/**
	 * To update J2EE Module dependencies from given collection of JARs and deployment flag.
	 * 
	 * @param elems
	 * @param deployme
	 * @param monitor
	 */
	private void updateProjectDependency(IPath[] elems, boolean deployme, IProgressMonitor monitor) {
		
		ClassPathSelection selection = null;
		/**
		 * For each JAR defined in a JSF Library, it is added as J2EE Module Dependency if 
		 * the JSF Library is checked for deployment.  However, JARs are added into project build path 
		 * regardless if a JSF Library is checked for deployment or not. 
		 *
		 */
		String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
		for (int i= 0; i < elems.length; i++) {				
			IVirtualComponent archive = ComponentCore.createArchiveComponent( model.getComponent().getProject(), type + elems[i].toString());
			
			// Added as J2EE Module Dependency if need to be deployed
			if (deployme) {
				ArrayList vlist = new ArrayList(Collections.EMPTY_LIST);
				IVirtualReference[] oldrefs = model.getComponent().getReferences();
				for (int j = 0; j < oldrefs.length; j++) {
					IVirtualReference ref = oldrefs[j];
					vlist.add(ref);
				}		
				
				IVirtualReference ref = ComponentCore.createReference( 
						model.getComponent(), 
						archive, 
						new Path("/WEB-INF/lib") ); //$NON-NLS-1$
				addReferences(vlist, ref);
				
				IVirtualReference[] refs = new IVirtualReference[vlist.size()];
				for (int j = 0; j < vlist.size(); j++) {
					IVirtualReference tmpref = (IVirtualReference) vlist.get(j);
					refs[j] = tmpref;
				}				
				model.getComponent().setReferences(refs);
			} else {
				// added into the path selection to update build path later.			
				ClasspathElement element = createClassPathElement(archive, archive.getName(), true);
				if (selection == null) {
					selection = createClassPathSelectionForExternalJar(element);
				} else {
					selection.getClasspathElements().add(element);
				}
				model.getClassPathSelectionForWLPs().getClasspathElements().add(element);
			}
		}	// end For
		
		// Add JARs into build path for selected JSF libs checked for not deploy. 
		if (selection != null) {
			IRunnableWithProgress buildPathOP = createBuildPathOperationForExternalJar(selection);
			try {
				buildPathOP.run(monitor);	
			} catch (InvocationTargetException e) {
				JSFCorePlugin.getDefault().getMsgLogger().log(e);
			} catch (InterruptedException e) {
				JSFCorePlugin.getDefault().getMsgLogger().log(e);
			}
		}
	}

	/**
	 * To remove given collection of JARs from project's J2EE Module Dependencies.
	 * 
	 * @param elems
	 * @param monitor
	 */
	private void removeProjectDependency_(IPath[] elems, IProgressMonitor monitor) {		
		
		ClassPathSelection selection = null;
		for (int i= 0; i < elems.length; i++) {			
			String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
			IVirtualComponent archive = ComponentCore.createArchiveComponent( model.getComponent().getProject(), type + elems[i].toString());
			
			ArrayList vlist = new ArrayList(Collections.EMPTY_LIST);
			IVirtualReference[] oldrefs = model.getComponent().getReferences();
			for (int j = 0; j < oldrefs.length; j++) {
				IVirtualReference ref = oldrefs[j];
				vlist.add(ref);
			}		
			
			// Remove the reference
			IVirtualReference ref = ComponentCore.createReference( 
					model.getComponent(), 
					archive, 
					new Path("/WEB-INF/lib") ); //$NON-NLS-1$
			removeReference(vlist, ref);
			
			IVirtualReference[] refs = new IVirtualReference[vlist.size()];
			for (int j = 0; j < vlist.size(); j++) {
				IVirtualReference tmpref = (IVirtualReference) vlist.get(j);
				refs[j] = tmpref;
			}				
			model.getComponent().setReferences(refs);
						
			// Update project classpath
			ClasspathElement element = createClassPathElement(archive, archive.getName(), false);
			if (selection == null) {
				selection = createClassPathSelectionForExternalJar(element);
			} else {
				selection.getClasspathElements().add(element);
			}
			selection.getClasspathElements().add(element);			
			model.getClassPathSelectionForWLPs().getClasspathElements().remove(element);
		}	// end for
		
		IRunnableWithProgress buildPathOP = createBuildPathOperationForExternalJar(selection);			
		try {
			buildPathOP.run(monitor);	
		} catch (InvocationTargetException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		} catch (InterruptedException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
		}				
	}
	
	private void removeReference(ArrayList vList, IVirtualReference ref) {		
		IVirtualReference elem = null;
		for (int i = 0; i < vList.size(); i++) {			
			elem = (IVirtualReference)vList.get(i);
			if (elem.getReferencedComponent().getName().equalsIgnoreCase(ref.getReferencedComponent().getName())) {
				vList.remove(elem);
			}
		}
	}

	/**
	 * Check if archive component already exists
	 * But, how to tell if two references are equal?
	 * 
	 * @param vlist
	 * @param ref
	 */
	private void addReferences(ArrayList vlist, IVirtualReference ref) {		
		IVirtualReference elem = null;
		boolean exist = false;
		for (int i = 0; i < vlist.size(); i++) {
			elem = (IVirtualReference)vlist.get(i);
			if (elem.getReferencedComponent().getName().equalsIgnoreCase(ref.getReferencedComponent().getName()))  {
				exist = true;
				break;				
			}
		}
		if ( !exist) {
			vlist.add(ref);
		}
	}
	
	private ClasspathModel newClasspathModel(IProject project) {
		ClasspathModel model = new ClasspathModel(null);
	    model.setProject(project);
        if( model.getComponent() != null ){
	        updateModelManifest(model);
        }	
        return model;
	}
	
	private ClasspathElement createClassPathElement(
			IVirtualComponent archiveComp,
			String unresolvedName, 
			boolean addedToPath) {
	
		URI uri = URI.createURI(ModuleURIUtil.getHandleString(archiveComp));
		ClasspathElement element = new ClasspathElement(uri);
		element.setValid(false);
		element.setSelected(addedToPath);
		element.setRelativeText(unresolvedName);
		element.setText(unresolvedName);
		element.setEarProject(null);
		return element;
	}

	private ClassPathSelection createClassPathSelectionForExternalJar(ClasspathElement element){
		ClassPathSelection selection = new ClassPathSelection();
		selection.getClasspathElements().add(element);
		return selection;
	}	

	private IRunnableWithProgress createBuildPathOperationForExternalJar(ClassPathSelection selection) {
	    IJavaProject javaProject = JemProjectUtilities.getJavaProject(project);
	    return WTPUIPlugin.getRunnableWithProgress(new UpdateJavaBuildPathOperation(javaProject, selection));
	}    

	private void updateModelManifest(ClasspathModel model) {
	    if (JemProjectUtilities.isBinaryProject(project) || model.getAvailableEARComponents().length == 0)
	        return;
	    
	    IContainer root = null;
	    IFile manifestFile = null;
	    if (project != null) {
	        root = project;
	    } else {
	        root = JemProjectUtilities.getSourceFolderOrFirst(project, null);
	    }
	    if (root != null) {
	        manifestFile = root.getFile(new Path(J2EEConstants.MANIFEST_URI));
	    }
	    if (manifestFile == null || !manifestFile.exists())
	        return;
	
	    InputStream in = null;
	    try {
	        in = manifestFile.getContents();
	        ArchiveManifest mf = new ArchiveManifestImpl(new Manifest(in));
	        model.primSetManifest(mf);
	    } catch (CoreException e) {
	        model.primSetManifest(new ArchiveManifestImpl());
	    } catch (IOException iox) {
	        model.primSetManifest(new ArchiveManifestImpl());
	    } finally {
	        if (in != null) {
	            try {
	                in.close();
	            } catch (IOException weTried) {
	            	//Ignore
	            }
	        }
	    }
	}	
	
}
