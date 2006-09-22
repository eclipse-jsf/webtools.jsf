/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen - development check in
 *    Ian Trimble - run reference-changing code in WorkspaceJob (bug# 144006)
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.internal.ui.WTPUIPlugin;

/**
 * <b>J2EEModuleDependencyDelegate</b> provides a service to update project dependencies.
 * It includes updating web library dependencies and java build path for web modules. 
 * 
 * @author Justin Chen - Oracle
 */
public class J2EEModuleDependencyDelegate {	
	final private IProject project;
	final private ClasspathModel model;

	/**
	 * Constructor
	 * @param project IProject instance on which to update dependencies.
	 */
	public J2EEModuleDependencyDelegate(IProject project) {
		this.project = project;
		this.model = newClasspathModel(project);
	}	
	
	/**
	 * To update project dependencies including Java buildpath and 
	 * web library dependencies from JSFLibraryConfigModel.  
	 * 
	 * @param model JSFLibraryConfigModel  instance with information to update project dependencies
	 * @param monitor IProgressMonitor  progress monitor passed.  New instance is creatsed if a null is given  
	 */
	public void updateProjectDependencies(final JSFLibraryConfigModel model, 
			IProgressMonitor monitor) {
		if (monitor == null) {		
			monitor = new NullProgressMonitor();
		}
		// update implementation library
		JSFProjectLibraryReference newImplLib = model.getCurrentJSFImplementationLibrarySelection();
		JSFProjectLibraryReference savedImplLib = model.getSavedJSFImplementationLibrary();
		if (savedImplLib != null) {
			this.removeProjectDependency(savedImplLib.getLibrary(), monitor);
			this.addProjectDependency(newImplLib.getLibrary(), newImplLib.isCheckedToBeDeployed(), monitor);
		} else {
			this.addProjectDependency(newImplLib.getLibrary(), newImplLib.isCheckedToBeDeployed(), monitor);
		}

		 // update component libraries
		 // Remove first and then add dependencies from selected libraries currently. 
		JSFProjectLibraryReference compLibDctr = null;
		List savedCompLibs = model.getSavedJSFComponentLibraries();
		Iterator it = savedCompLibs.iterator();
		while (it.hasNext()) {
			compLibDctr = (JSFProjectLibraryReference) it.next();
			this.removeProjectDependency(compLibDctr.getLibrary(), monitor);
		}

		List selComponentLibs = model.getCurrentJSFComponentLibrarySelection();
		it = selComponentLibs.iterator();
		while (it.hasNext()) {
			compLibDctr = (JSFProjectLibraryReference) it.next();
			if (compLibDctr.isSelected()) {
				this.addProjectDependency(compLibDctr.getLibrary(), 
						compLibDctr.isCheckedToBeDeployed(), 
						monitor);
			}
		}		
	}	
	
	/**
	 * Add the given jsfLibrary instance as project dependency.
	 * If toDeploy is true, the JARs from JSF library is added as web library dependencies.  
	 * Otherwise, JARs are added in project build path only.
	 * 
	 * @param jsfLibrary JSFLibrary  instance to be added as project dependencies.
	 * @param toDeploy boolean  add as web library project for a web module if true.  Otheriwse, add to build path only 
	 * @param monitor IProgressMOnitor 
	 */
	private void addProjectDependency(final JSFLibrary jsfLibrary, 
			final boolean toDeploy, 
			final IProgressMonitor monitor) {
		IPath[] jarPaths = JSFUtils.getJARPathforJSFLibwFilterMissingJars(jsfLibrary, true);
		updateProjectDependency(jarPaths, toDeploy, monitor);		
	}	
		
	/**
	 * Remove given <b>jsfLibrary</b> from project dependency, 
	 * including web library dependencies and project build path.
	 * 
	 * @param jsfLibrary JSFLibrary  instance to be removed from project dependencies.
	 * @param monitor 
	 */
	private void removeProjectDependency(final JSFLibrary jsfLibrary, final IProgressMonitor monitor) {
		IPath[] elements = JSFUtils.getJARPathforJSFLib(jsfLibrary, false);
		removeProjectDependency_(elements, monitor);
	}
	
	/**
	 * To update project dependencies by the path collection of JARs.
	 * 
	 * All JARs are added into project build path.   
	 * However, JARs are added as web library dependencies only if deployme is true.  
	 * 
	 * @param elems IPath[] 
	 * @param deployme boolean
	 * @param monitor IProgressMonitor
	 */
	private void updateProjectDependency(final IPath[] elems, 
			final boolean deployme, 
			final IProgressMonitor monitor) {
		
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
				// fix for bug# 144006 - wrap in WorkspaceJob
				SetReferencesJob setRefsJob = new SetReferencesJob(project, model.getComponent(), archive, true);
				setRefsJob.schedule();
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
	 * To remove project dependencies from the path collection of JARs.
	 * 
	 * @param elems IPath[] elems
	 * @param monitor IProgressMonitor
	 */
	private void removeProjectDependency_(final IPath[] elems, final IProgressMonitor monitor) {		
		
		ClassPathSelection selection = null;
		for (int i= 0; i < elems.length; i++) {			
			String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
			IVirtualComponent archive = ComponentCore.createArchiveComponent( model.getComponent().getProject(), type + elems[i].toString());
			// fix for bug# 144006 - wrap in WorkspaceJob
			SetReferencesJob setRefsJob = new SetReferencesJob(project, model.getComponent(), archive, false);
			setRefsJob.schedule();

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
	 * To Do: check if archive component already exists
	 * 
	 * @param vlist ArrayList
	 * @param ref IVirtualReference
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
	    if (JemProjectUtilities.isBinaryProject(project) || model.getAvailableEARComponents().length == 0) {
	        return;
	    }
	    
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
	    if (manifestFile == null || !manifestFile.exists()) {
	        return;
	    }
	
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

	/**
	 * SetReferencesJob is responsible for adding or removing IVirtualReference
	 * instances on a specified IVirtualComponent and runs as a WorkspaceJob.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	class SetReferencesJob extends WorkspaceJob {

		/**
		 * IProject instance.
		 */
		protected IProject project = null;
		/**
		 * IVirtualComponent instance on which to set references.
		 */
		protected IVirtualComponent parentComponent = null;
		/**
		 * IVirtualComponent instance to be added or removed as a reference on parentComponent.
		 */
		protected IVirtualComponent referencedComponent = null;
		/**
		 * If true, referencedComponent is added; if false, it is removed.
		 */
		protected boolean addReferencedComponent = true;

		/**
		 * Instantiates and configures a SetReferencesJob.
		 * 
		 * @param project IProject instance on which to set rule.
		 * @param parentComponent IVirtualComponent instance on which to set references.
		 * @param referencedComponent IVirtualComponent instance to be added or removed as a reference on parentComponent.
		 * @param addReferencedComponent If true, referencedComponent is added; if false, it is removed.
		 */
		public SetReferencesJob(
				IProject project,
				IVirtualComponent parentComponent,
				IVirtualComponent referencedComponent,
				boolean addReferencedComponent) {
			super(Messages.J2EEModuleDependencyDelegate_UpdatingJ2EEModuleDependencies);
			this.project = project;
			this.parentComponent = parentComponent;
			this.referencedComponent = referencedComponent;
			this.addReferencedComponent = addReferencedComponent;
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			setRule(workspace.getRuleFactory().createRule(workspace.getRoot()));
			setUser(true);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
		 */
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			try {
				String projectName = project != null ? project.getName() : "null";
				monitor.beginTask(
						NLS.bind(Messages.J2EEModuleDependencyDelegate_UpdatingJ2EEModuleDependenciesForProject, projectName),
						5);
				if (parentComponent != null) {
					//copy from array to list
					ArrayList refList = new ArrayList();
					IVirtualReference[] refArray = parentComponent.getReferences();
					for (int i = 0; i < refArray.length; i++) {
						refList.add(refArray[i]);
					}
					monitor.worked(1);
					//create new reference
					IVirtualReference reference = ComponentCore.createReference( 
							parentComponent, 
							referencedComponent, 
							new Path("/WEB-INF/lib")); //$NON-NLS-1$
					monitor.worked(1);
					//add or remove reference
					if (addReferencedComponent) {
						addReferences(refList, reference);
					} else {
						removeReference(refList, reference);
					}
					monitor.worked(1);
					//copy from list to array
					IVirtualReference[] newRefArray = new IVirtualReference[refList.size()];
					newRefArray = (IVirtualReference[])refList.toArray(newRefArray);
					monitor.worked(1);
					//set references
					parentComponent.setReferences(newRefArray);
					monitor.worked(1);
				} else {
					monitor.worked(5);
				}
			} finally {
				monitor.done();
			}
			return Status.OK_STATUS;
		}
	}

}
