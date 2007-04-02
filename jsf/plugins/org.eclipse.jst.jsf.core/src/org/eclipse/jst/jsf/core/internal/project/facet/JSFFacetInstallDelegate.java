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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFLibrariesContainerInitializer;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF Facet Install Delegate for WTP faceted web projects.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> creates JSF configuration file if not present
 * 	 <li> updates web.xml for: servlet, servlet-mapping and context-param
 * 	 <li> adds implementation jars to WEB-INF/lib if user requests
 * 
 * @see com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallConfig
 * @see com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider
 * @author Gerry Kessler - Oracle
 * @since M1
 */
public class JSFFacetInstallDelegate implements IDelegate {

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

	private void createClasspathEntries(IProject project, IDataModel config, IProgressMonitor monitor) {
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
		IPath cp = new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID);		
		JSFLibraryReference libref = (JSFLibraryReference)config.getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION);
		IPath path = cp.append(new Path(libref.getID()));
		IClasspathEntry entry = getNewCPEntry(path, libref);		
		cpEntries.add(entry);

		JSFLibraryReference[] compLibs = (JSFLibraryReference[])config.getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);
		for (int i=0;i<compLibs.length;i++){
			libref = (JSFLibraryReference)compLibs[i];		
			cp = new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID);		
			path = cp.append(new Path(libref.getID()));
			entry = getNewCPEntry(path, libref);
			if (entry != null)
				cpEntries.add(entry);
		}	

		JSFLibraryRegistryUtil.setRawClasspath(javaProject, cpEntries, monitor);
	}

	//creates new IClasspathEntry with WTP dependency attribute set, if required
	private IClasspathEntry getNewCPEntry(IPath path, JSFLibraryReference lib) {
		
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

	//revisit when can set attr on new cp container.   curren
//	private void addWTPDependencyAttribute(IProject project, IClasspathEntry entry) {
//		try {
//			UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getProject().getName(), entry);
//		} catch (ExecutionException e) {
//			JSFCorePlugin.log(e, "Unable to set WTP J2EE Module Dependency for: "+entry.getPath().lastSegment());
//		}
//	}
	
	private void createConfigFile(final IProject project,
			final IProjectFacetVersion fv, final IDataModel config,
			IProgressMonitor monitor) {
		final IPath configPath = resolveConfigPath(project, config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));

		try {
			// do not overwrite if the file exists
			if (!configPath.toFile().exists()) {
				IWorkspaceRunnable op = new IWorkspaceRunnable(){

					public void run(IProgressMonitor monitor_inner)
							throws CoreException{ 
						JSFUtils.createConfigFile(fv.getVersionString(),
								configPath);
						project.refreshLocal(IResource.DEPTH_INFINITE, monitor_inner);
					}

				};
				op.run(monitor);
			}
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while creating faces-config.xml");
		}

	}

	private void createServletAndModifyWebXML(IProject project,
			final IDataModel config, IProgressMonitor monitor) {
		WebApp webApp = null;
		WebArtifactEdit artifactEdit = null;
		try {
			artifactEdit = JSFUtils.getWebArtifactEditForWrite(project);
			webApp = artifactEdit.getWebApp();

			// create or update servlet ref
			Servlet servlet = JSFUtils.findJSFServlet(webApp);// check to see
																// if already
																// present
			if (servlet != null) {
				// remove old mappings
				JSFUtils.removeURLMappings(webApp, servlet);
			}
			
			servlet = JSFUtils
					.createOrUpdateServletRef(webApp, config, servlet);

			// init mappings
			List listOfMappings = getServletMappings(config);
			JSFUtils.setUpURLMappings(webApp, listOfMappings, servlet);

			// setup context params
			if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)//shouldn't have to do it this way, but that's the way it goes 119442
				JSFUtils.setupConfigFileContextParamForV2_3(webApp, config);
			else
				JSFUtils.setupConfigFileContextParamForV2_4(webApp, config);
			
		} finally {
			if (artifactEdit != null) {
				// save and dispose
				artifactEdit.saveIfNecessary(monitor);
				artifactEdit.dispose();
			}
		}
	}

	private List getServletMappings(IDataModel config) {
		List mappings = new ArrayList();
		String[] patterns = (String[])config.getProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
		for (int i = 0; i < patterns.length; i++) {
			String pattern = patterns[i];
			mappings.add(pattern);
		}

		return mappings;
	}
	/*
	private IPath getWebContentPath(IProject project) {
		WebArtifactEdit web = null;
		try {
			web = WebArtifactEdit.getWebArtifactEditForRead(project);
			IPath webxml = web.getDeploymentDescriptorPath();
			//remove project name, WEB-INF an web.xml from path
			IPath webContentPath = webxml.removeLastSegments(2).removeFirstSegments(1);
			return webContentPath;
		} finally {
			if (web != null) {
				web.dispose();
			}
		}
	}
	*/
	private IPath resolveConfigPath(IProject project, String jsfConfigPath) {
		// TODO: fix me
		WebArtifactEdit web = null;
		try {
			web = WebArtifactEdit.getWebArtifactEditForRead(project);
			IPath webxml = web.getDeploymentDescriptorPath();
			IPath webcontent = webxml.removeLastSegments(2)
					.removeFirstSegments(1);

			return project.getLocation().append(
					webcontent.append(new Path(jsfConfigPath)));
		} finally {
			if (web != null)
				web.dispose();
		}
	}
	
}
