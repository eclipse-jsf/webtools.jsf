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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.core.libprov.LibraryInstallDelegate;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF Facet Install Delegate for WTP faceted web projects.  Deals with 2.3, 2.4 and 2.5 web app models.
 * 
 * Uses <code>com.eclispe.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider<code> for model
 * 	 <li> creates JSF configuration file if not already present.  It will not attempt to upgrade or downgrade the version if there is a mismatch.
 * 	 <li> updates web.xml for: servlet, servlet-mapping and context-param
 * 	 <li> adds implementation jars to WEB-INF/lib if user requests
 * 
 * @see org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider
 * @since 1.0
 */
public final class JSFFacetInstallDelegate implements IDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.project.facet.core.IDelegate#execute(org.eclipse.core.resources.IProject, org.eclipse.wst.common.project.facet.core.IProjectFacetVersion, java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
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
				throw new JSFFacetException(						
								Messages.JSFFacetInstallDelegate_InternalErr);
			}

			//Before we do any configuration, verify that web.xml is available for update
			IModelProvider provider = JSFUtils.getModelProvider(project);
			if (provider == null ) {				
				throw new JSFFacetException( NLS.bind(Messages.JSFFacetInstallDelegate_ConfigErr, project.getName())); 
			} else if (!(provider.validateEdit(null, null).isOK())){				
				if (!(provider.validateEdit(null, null).isOK())) {//checks for web.xml file being read-only and allows user to set writeable		
					throw new JSFFacetException(NLS.bind(Messages.JSFFacetInstallDelegate_NonUpdateableWebXML,  project.getName()));
				}
			}
			
//			// Create JSF Libs as classpath containers and set WTP dependencies
//			// as required
//			createClasspathEntries(project, fv, config, monitor);
			
			//Configure libraries
			( (LibraryInstallDelegate) config.getProperty( IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE ) ).execute( new NullProgressMonitor() );
			
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

//	/**
//	 * Adds the JSF Library references specified in the wizard to the project as
//	 * classpath containers. Marks the containers as J2EE module dependencies as
//	 * required
//	 * 
//	 * @param project
//	 * @param config
//	 * @param monitor
//	 */
//	private void createClasspathEntries(final IProject project, final IProjectFacetVersion fv, final IDataModel config, final IProgressMonitor monitor) throws CoreException {
//		IJavaProject javaProject = JavaCore.create(project);	
//		List cpEntries = new ArrayList();
//		try {
//			for (int i=0;i<javaProject.getRawClasspath().length;i++){
//				cpEntries.add(javaProject.getRawClasspath()[i]);
//			}
//		} catch (JavaModelException e) {
//			JSFCorePlugin.log(e, "Unable to read classpath"); //$NON-NLS-1$
//		}
//		
//		IPath path, cp = null;
//		IClasspathEntry entry = null;
//		JSFLibraryInternalReference libref = null;
//		
//		JSFLibraryInternalReference[] compLibs = (JSFLibraryInternalReference[])config.getProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES);
//		for (int i=0;i<compLibs.length;i++){
//			libref = compLibs[i];		
//			cp = new Path(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID);		
//			path = cp.append(new Path(libref.getID()));
//			entry = getNewCPEntry(path, libref);
//			if (entry != null)
//				cpEntries.add(entry);
//		}	
//
//		JSFLibraryRegistryUtil.setRawClasspath(javaProject, cpEntries, monitor);
//		
//		( (LibraryInstallDelegate) config.getProperty( IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE ) ).execute( new NullProgressMonitor() );
//	}

//	/**
//	 * @param path
//	 * @param lib
//	 * @return creates new IClasspathEntry with WTP dependency attribute set, if required
//	 */
//	private IClasspathEntry getNewCPEntry(final IPath path, final JSFLibraryInternalReference lib) {
//		
//		IClasspathEntry entry = null;
//		if (lib.isCheckedToBeDeployed()){
//			IClasspathAttribute depAttrib = JavaCore.newClasspathAttribute(IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY,
//					 ClasspathDependencyUtil.getDefaultRuntimePath(true).toString());
//			entry = JavaCore.newContainerEntry(path,null, new IClasspathAttribute[]{depAttrib}, true);
//		}
//		else {
//			entry = JavaCore.newContainerEntry(path);
//		}
//		
//		return entry;
//	}		

	/**
	 * @param config
	 * @return list of URL patterns from the datamodel
	 */
	private List getServletMappings(final IDataModel config) {
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
	private IPath resolveConfigPath(final IProject project, final String jsfConfigPath) {
		return ComponentCore.createComponent(project).getRootFolder()
				.getUnderlyingFolder().getRawLocation().append(
						new Path(jsfConfigPath));

	}

	/**
	 * Create the faces configuration file.  If the file already exist, then the file is left alone.
	 * @param project
	 * @param fv
	 * @param config
	 * @param monitor
	 */
	private void createConfigFile(final IProject project,
			final IProjectFacetVersion fv, final IDataModel config,
			final IProgressMonitor monitor) {


		final IPath configPath = resolveConfigPath(project, config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		try {
			// do not overwrite if the file exists
			if (!configPath.toFile().exists()) {
				final IWorkspaceRunnable op = new IWorkspaceRunnable(){
					public void run(IProgressMonitor monitor_inner) throws CoreException{ 
						if (shouldUseJ2EEConfig(fv)){
							JSFUtils11.createConfigFile(fv.getVersionString(),
									configPath);
						} else {
							JSFUtils12.createConfigFile(fv.getVersionString(),
									configPath);
						}
						project.refreshLocal(IResource.DEPTH_INFINITE, monitor_inner);
					}

					private boolean shouldUseJ2EEConfig(final IProjectFacetVersion facetVersion) {
						if (IJSFCoreConstants.FACET_VERSION_1_1.equals(facetVersion.getVersionString()))
						{
							return true;
						}
						return false;
					}
				};
				op.run(monitor);
			}
		} catch (CoreException e) {
			JSFCorePlugin.log(e, "Exception occured while creating faces-config.xml");//$NON-NLS-1$
		}

	}
	
	/**
	 * Create servlet and URL mappings and update the webapp
	 * @param project
	 * @param config
	 * @param monitor
	 */
	private void createServletAndModifyWebXML(final IProject project,
			final IDataModel config, final IProgressMonitor monitor) {
		
		IModelProvider provider = JSFUtils.getModelProvider(project);
		IPath webXMLPath = new Path("WEB-INF").append("web.xml"); //$NON-NLS-1$ //$NON-NLS-2$
		if (JSFUtils12.isWebApp25(provider.getModelObject())) {			
			provider.modify(new UpdateWebXMLForJavaEE(project, config), doesDDFileExist(project, webXMLPath) ? webXMLPath : IModelProvider.FORCESAVE); 
		}
		else {//must be 2.3 or 2.4			
			provider.modify(new UpdateWebXMLForJ2EE(project, config), webXMLPath);
		}

	}

	private boolean doesDDFileExist(IProject project, IPath webXMLPath) {
		return project.getProjectRelativePath().append(webXMLPath).toFile().exists();		
	}

	private class UpdateWebXMLForJavaEE implements Runnable {
		private IProject project;
		private IDataModel config;
		
		UpdateWebXMLForJavaEE(final IProject project, final IDataModel config){
			this.project = project;
			this.config = config;
		}
		
		public void run() {
			WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
			// create or update servlet ref
			Servlet servlet = JSFUtils12.findJSFServlet(webApp);// check to see
																// if already
// No longer removing any old mappings on install - see 194919 															// present
//			if (servlet != null) {
//				// remove old mappings
//				JSFUtils12.removeURLMappings(webApp, servlet);
//			}
			
			servlet = JSFUtils12
					.createOrUpdateServletRef(webApp, config, servlet);
	
			// init mappings
			List listOfMappings = getServletMappings(config);
			JSFUtils12.setUpURLMappings(webApp, listOfMappings, servlet);
	
			// setup context params
			JSFUtils12.setupConfigFileContextParamForV2_5(webApp, config);
		}
	}
	
	private class UpdateWebXMLForJ2EE implements Runnable {		
		private IProject project;
		private IDataModel config;
		
		UpdateWebXMLForJ2EE(IProject project, final IDataModel config){
			this.project = project ;
			this.config = config;
		}
		
		public void run() {
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp)ModelProviderManager.getModelProvider(project).getModelObject();
			// create or update servlet ref
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = JSFUtils11.findJSFServlet(webApp);// check to see
																// if already
																// present
			
// No longer removing any old mappings on install - see 194919 
//			if (servlet != null) {
//				// remove old mappings
//				JSFUtils11.removeURLMappings(webApp, servlet);
//			}
			
			servlet = JSFUtils11
					.createOrUpdateServletRef(webApp, config, servlet);
	
			// init mappings
			List listOfMappings = getServletMappings(config);
			JSFUtils11.setUpURLMappings(webApp, listOfMappings, servlet);
	
			// setup context params
			setupContextParams(webApp, config);
		}
		
		private void setupContextParams(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final IDataModel config) {
			if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)//shouldn't have to do it this way, but that's the way it goes 119442
				JSFUtils11.setupConfigFileContextParamForV2_3(webApp, config);
			else 
				JSFUtils11.setupConfigFileContextParamForV2_4(webApp, config);
		}
	}


	
}
