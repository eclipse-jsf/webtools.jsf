/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF v1.2 Facet Install Delegate for WTP faceted web projects.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> creates JSF configuration file if not present
 * 	 <li> updates web.xml for: servlet, servlet-mapping and context-param
 * 	 <li> adds implementation jars to WEB-INF/lib if user requests
 * 
 * @see org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider
 * @author Gerry Kessler - Oracle
 * @since 1.0
 */
public class JSFJavaEEFacetInstallDelegate extends AbstractJSFFacetInstallDelegate {
	

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.project.facet.AbstractJSFFacetInstallDelegate#createConfigFile(org.eclipse.core.resources.IProject, org.eclipse.wst.common.project.facet.core.IProjectFacetVersion, org.eclipse.wst.common.frameworks.datamodel.IDataModel, org.eclipse.core.runtime.IProgressMonitor)
	 */
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
						JSFUtils12.createConfigFile(fv.getVersionString(),
								configPath);
						project.refreshLocal(IResource.DEPTH_INFINITE, monitor_inner);
					}

				};
				op.run(monitor);
			}
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while creating faces-config.xml");//$NON-NLS-1$
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.project.facet.AbstractJSFFacetInstallDelegate#createServletAndModifyWebXML(org.eclipse.core.resources.IProject, org.eclipse.wst.common.frameworks.datamodel.IDataModel, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createServletAndModifyWebXML(IProject project,
			final IDataModel config, IProgressMonitor monitor) {
		
		IModelProvider provider = ModelProviderManager.getModelProvider(project); 
		Object webAppObj = provider.getModelObject();
		if (webAppObj == null){
			JSFCorePlugin.log(IStatus.ERROR, project.getName()+": unable to configure web module for JavaServer Faces"); //$NON-NLS-1$
			return;
		}			
		 
		if (JSFUtils12.isWebApp25(webAppObj)) {			
			final WebApp webApp = (WebApp)webAppObj;
			provider.modify(new UpdateWebXMLFor25(webApp, config), new Path("WEB-INF").append("web.xml")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			//
		}

	}
		
	private class UpdateWebXMLFor25 implements Runnable {
		private WebApp webApp;
		private IDataModel config;
		
		UpdateWebXMLFor25(WebApp webApp, IDataModel config){
			this.webApp = webApp;
			this.config = config;
		}
		
		public void run() {

			// create or update servlet ref
			Servlet servlet = JSFUtils12.findJSFServlet(webApp);// check to see
																// if already
																// present
			if (servlet != null) {
				// remove old mappings
				JSFUtils12.removeURLMappings(webApp, servlet);
			}
			
			servlet = JSFUtils12
					.createOrUpdateServletRef(webApp, config, servlet);
	
			// init mappings
			List listOfMappings = getServletMappings(config);
			JSFUtils12.setUpURLMappings(webApp, listOfMappings, servlet);
	
			// setup context params
			JSFUtils12.setupConfigFileContextParamForV2_5(webApp, config);
		}
	}
	
}
