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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.J2EEModuleDependencyDelegate;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModelAdapter;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryDecorator;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF Facet Install Delegate for WTP faceted web projects.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> TODO: creates JSF Application Model
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
			// Create JSF App Model
			// tbd

			// Add JSF Impls to WEB-INF/lib
			//deployJSFLibraries(project, config, monitor);
			
			// Update Model
			java.util.List implLibs = (List) config.getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION_LIBRARIES);
			java.util.List compLibs = (List) config.getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);			
			JSFLibraryConfigModelAdapter provider = new JSFLibraryConfigModelAdapter(project);
			provider.saveData(implLibs, compLibs);
			
			// Update J2EE Module dependency for deployment and add JARs into project build path.
			J2EEModuleDependencyDelegate modulesDep = new J2EEModuleDependencyDelegate(project);
			JSFLibraryDecorator selJSFImplLib = (JSFLibraryDecorator)implLibs.get(0);
			modulesDep.addProjectDependency(selJSFImplLib.getLibrary(), selJSFImplLib.checkForDeploy(), monitor);
			JSFLibraryDecorator jsfLibItem;
			for(int i = 0; i < compLibs.size(); i++) {
				jsfLibItem = (JSFLibraryDecorator)compLibs.get(i);
				if (jsfLibItem.isSelected()) {
					modulesDep.addProjectDependency(jsfLibItem.getLibrary(), jsfLibItem.checkForDeploy(), monitor);
				}				
			}	
			
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
	
	/** Obsoleted M1 approach
	private void deployJSFLibraries(IProject project,
			final IDataModel config, IProgressMonitor monitor) {
		
		if (config.getBooleanProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION)){
			JSFLibrary impl = (JSFLibrary)config.getProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION);
			if (impl != null){
				IPath destPath = project.getLocation().append(getWebContentPath(project));
				impl.copyTo(destPath.toOSString());			
			}
		}
	}
	*/
	
	private void createConfigFile(final IProject project,
			final IProjectFacetVersion fv, final IDataModel config,
			IProgressMonitor monitor) {
		final IPath configPath = resolveConfigPath(project, config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));

		try {
			// do not overwrite if the file exists
			if (!configPath.toFile().exists()) {
				IWorkspaceRunnable op = new IWorkspaceRunnable(){

					public void run(IProgressMonitor monitor)
							throws CoreException{ 
						JSFUtils.createConfigFile(fv.getVersionString(),
								configPath);
						project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					}

				};
				op.run(monitor);
			}
		} catch (CoreException e) {
			JSFCorePlugin.getDefault().getMsgLogger().log(e);
			e.printStackTrace();
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
