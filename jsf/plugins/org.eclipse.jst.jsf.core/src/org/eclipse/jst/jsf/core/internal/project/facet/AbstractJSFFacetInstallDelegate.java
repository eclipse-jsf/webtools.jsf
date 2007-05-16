/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Abstract JSF Facet Install Delegate for WTP faceted web projects.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> concrete delegat creates JSF configuration file if not present
 * 	 <li> concrete delegate updates web app for: servlet, servlet-mapping and context-params
 * 	 <li> adds implementation jars to WEB-INF/lib if user requests
 * 
 * @see org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider
 */
public abstract class AbstractJSFFacetInstallDelegate implements IDelegate {

	public void execute(final IProject project, final IProjectFacetVersion fv,
			final Object cfg, final IProgressMonitor monitor)

	throws CoreException

	{

		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			IDataModel config = null;

			if (cfg != null) {
				config = (IDataModel) cfg;
			} else {
				//FIXME: how would we hit this???
//				config = new JSFFacetInstallConfig();
//				config.setJsfImplID(jsfImplID);
			}
			
			// Create JSF Libs as classpath containers and set WTP dependencies as required					
			createClasspathEntries(project, config, monitor);
			
			// Create config file
			createConfigFile(project, fv, config, monitor);

			// Update web model
			createServletAndModifyWebXML(project, config, monitor);

			if (monitor != null) {
				monitor.worked(1);
			}

		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	/**
	 * Adds the JSF Library references specified in the wizard to the project as classpath containers.
	 * Marks the containers as J2EE module dependencies as required
	 * 
	 * @param project
	 * @param config
	 * @param monitor
	 */
	protected void createClasspathEntries(IProject project, IDataModel config, IProgressMonitor monitor) {
		IJavaProject javaProject = JavaCore.create(project);	
		List cpEntries = new ArrayList();
		try {
			for (int i=0;i<javaProject.getRawClasspath().length;i++){
				cpEntries.add(javaProject.getRawClasspath()[i]);
			}
		} catch (JavaModelException e) {
			JSFCorePlugin.log(e, "Unable to read classpath");
		}
		
		//Implementation
		IPath cp = new Path(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID);		
		JSFLibraryInternalReference libref = (JSFLibraryInternalReference)config.getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION);
		IPath path = cp.append(new Path(libref.getID()));
		IClasspathEntry entry = getNewCPEntry(path, libref);		
		cpEntries.add(entry);

		JSFLibraryInternalReference[] compLibs = (JSFLibraryInternalReference[])config.getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);
		for (int i=0;i<compLibs.length;i++){
			libref = compLibs[i];		
			cp = new Path(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID);		
			path = cp.append(new Path(libref.getID()));
			entry = getNewCPEntry(path, libref);
			if (entry != null)
				cpEntries.add(entry);
		}	

		JSFLibraryRegistryUtil.setRawClasspath(javaProject, cpEntries, monitor);
	}

	/**
	 * @param path
	 * @param lib
	 * @return creates new IClasspathEntry with WTP dependency attribute set, if required
	 */
	protected IClasspathEntry getNewCPEntry(IPath path, JSFLibraryInternalReference lib) {
		
		IClasspathEntry entry = null;
		if (lib.isCheckedToBeDeployed()){
			IClasspathAttribute depAttrib = JavaCore.newClasspathAttribute(IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY,
					 ClasspathDependencyUtil.getDefaultRuntimePath(true).toString());
			entry = JavaCore.newContainerEntry(path,null, new IClasspathAttribute[]{depAttrib}, true);
		}
		else {
			entry = JavaCore.newContainerEntry(path);
		}
		
		return entry;
	}

	
	/**
	 * Create the faces configuration file
	 * @param project
	 * @param fv
	 * @param config
	 * @param monitor
	 */
	protected abstract void createConfigFile(final IProject project,
			final IProjectFacetVersion fv, final IDataModel config,
			IProgressMonitor monitor) ;
	
	/**
	 * Create servlet and URL mappings and update the webapp
	 * @param project
	 * @param config
	 * @param monitor
	 */
	protected abstract void createServletAndModifyWebXML(IProject project,
			final IDataModel config, IProgressMonitor monitor);
		

	/**
	 * @param config
	 * @return list of URL patterns from the datamodel
	 */
	protected List getServletMappings(IDataModel config) {
		List mappings = new ArrayList();
		String[] patterns = (String[])config.getProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
		for (int i = 0; i < patterns.length; i++) {
			String pattern = patterns[i];
			mappings.add(pattern);
		}

		return mappings;
	}

	/**
	 * @param project
	 * @param jsfConfigPath
	 * @return absolute IPath to jsfConfig
	 */
	protected IPath resolveConfigPath(IProject project, String jsfConfigPath) {
		return ComponentCore.createComponent(project).getRootFolder()
				.getUnderlyingFolder().getRawLocation().append(
						new Path(jsfConfigPath));

	}
	
}
