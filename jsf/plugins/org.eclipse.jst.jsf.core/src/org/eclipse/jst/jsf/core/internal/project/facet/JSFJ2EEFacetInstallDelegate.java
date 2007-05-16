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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF v1.1 Facet Install Delegate for WTP faceted web projects.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> creates JSF configuration file if not present
 * 	 <li> updates web.xml for: servlet, servlet-mapping and context-param
 * 	 <li> adds implementation jars to WEB-INF/lib if user requests
 * 
 * @see org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider
 * @author Gerry Kessler - Oracle
 * @since M1
 */
public class JSFJ2EEFacetInstallDelegate extends AbstractJSFFacetInstallDelegate {


	protected void createConfigFile(final IProject project,
			final IProjectFacetVersion fv, final IDataModel config,
			IProgressMonitor monitor) {


		final IPath configPath = resolveConfigPath(project, config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		try {
			// do not overwrite if the file exists
			if (!configPath.toFile().exists()) {
				IWorkspaceRunnable op = new IWorkspaceRunnable(){

					public void run(IProgressMonitor monitor_inner)
							throws CoreException{ 
						JSFUtils11.createConfigFile(fv.getVersionString(),
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

	protected void createServletAndModifyWebXML(IProject project,
			final IDataModel config, IProgressMonitor monitor) {
		
//		IModelProvider provider = ModelProviderManager.getModelProvider(project); 
//		Object webAppObj = provider.getModelObject();
//		if (webAppObj == null){
//			JSFCorePlugin.log(IStatus.ERROR, project.getName()+": unable to configure web module for JavaServer Faces");
//			return;
//		}			
//		 
//		if (webAppObj instanceof WebApp) {
//			WebApp webApp = (WebApp)webAppObj;
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForWrite(project);
			WebApp webApp = webEdit.getWebApp();
			// create or update servlet ref
			Servlet servlet = JSFUtils11.findJSFServlet(webApp);// check to see
																// if already
																// present
			if (servlet != null) {
				// remove old mappings
				JSFUtils11.removeURLMappings(webApp, servlet);
			}
			
			servlet = JSFUtils11
					.createOrUpdateServletRef(webApp, config, servlet);
	
			// init mappings
			List listOfMappings = getServletMappings(config);
			JSFUtils11.setUpURLMappings(webApp, listOfMappings, servlet);
	
			// setup context params
			setupContextParams(webApp, config);
		} finally {
			if (webEdit != null) {
				webEdit.saveIfNecessary(monitor);
				webEdit.dispose();
			}
		}
		
			
	}

	private void setupContextParams(WebApp webApp, IDataModel config) {
		if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)//shouldn't have to do it this way, but that's the way it goes 119442
			JSFUtils11.setupConfigFileContextParamForV2_3(webApp, config);
		else 
			JSFUtils11.setupConfigFileContextParamForV2_4(webApp, config);
	}
}

	
