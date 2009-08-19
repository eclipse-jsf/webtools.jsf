/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Debajit Adhikary - Fixes for bug 255097 ("Request to remove input fields 
 *                       from facet install page")
 *                     - MyFaces and SunRI-specific configuration
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
import org.eclipse.jst.common.project.facet.core.libprov.LibraryProviderOperationConfig;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.facet.libraryprovider.jsf.JsfLibraryUtil;
import org.eclipse.jst.jsf.common.webxml.WebXmlUpdater;
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

    private final boolean jsfFacetConfigurationEnabled = JsfFacetConfigurationUtil.isJsfFacetConfigurationEnabled();
    
    
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

			if (jsfFacetConfigurationEnabled)
			{
    			//Before we do any configuration, verify that web.xml is available for update
    			final IModelProvider provider = JSFUtils.getModelProvider(project);
    			if (provider == null ) {				
    				throw new JSFFacetException( NLS.bind(Messages.JSFFacetInstallDelegate_ConfigErr, project.getName())); 
    			} else if (!(provider.validateEdit(null, null).isOK())){				
    				if (!(provider.validateEdit(null, null).isOK())) {//checks for web.xml file being read-only and allows user to set writeable		
    					throw new JSFFacetException(NLS.bind(Messages.JSFFacetInstallDelegate_NonUpdateableWebXML,  project.getName()));
    				}
    			}
			}
			
//			// Create JSF Libs as classpath containers and set WTP dependencies
//			// as required
//			createClasspathEntries(project, fv, config, monitor);
			
			//Configure libraries
			( (LibraryInstallDelegate) config.getProperty( IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE ) ).execute( new NullProgressMonitor() );

	        final LibraryInstallDelegate libDelegate = (LibraryInstallDelegate) (config.getProperty( IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE));
	        final LibraryProviderOperationConfig libConfig = libDelegate.getLibraryProviderOperationConfig();

			if (jsfFacetConfigurationEnabled)
            {
    			// Create config file
    			createConfigFile(project, fv, config, monitor);

    			// Update web model
    			createServletAndModifyWebXML(project, config, monitor);
                updateWebXmlByJsfVendor(libConfig, project, monitor);
            }
            
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
     * Configures web.xml for MyFaces or Sun-RI when a user-library is used.
     * Does no special configuration when a third party library provider is used
     * (e.g. WebLogic shared library)
     * 
     * @param libConfig
     *            Library install delegate
     * @param project
     *            IProject to update
     * @param monitor
     *            IProgressMonitor
     */
    private void updateWebXmlByJsfVendor (final LibraryProviderOperationConfig libConfig, 
                                          final IProject project, 
                                          final IProgressMonitor monitor)
    {
        final UserLibraryProviderInstallOperationConfig userLibConfig = getUserLibConfig(libConfig);
        if (userLibConfig == null) // Not a user lib provider (e.g. shared lib provider)
            return;  // Any special configuration will be done by third party lib provider.


        switch (JsfLibraryUtil.getJsfLibraryVendorType(userLibConfig))
        {
        case MYFACES:
            updateWebXmlForMyfaces(project, monitor);
            break;
        case SUN_RI:
            updateWebXmlForSunRi(project, monitor);
            break;
        case UNKNOWN:
            break;
        }
    }


    /**
     * (Refactored into a protected method for JUnit testing)
     * 
     * @param libConfig LibraryProviderOperationConfig object
     * 
     * @return WtpUserLibraryProviderInstallOperationConfig object if the
     *         argument object has the same runtime type. Returns null
     *         otherwise e.g. if the libConfig is of the runtime type 
     *         SharedLibraryProviderInstallOperationConfig.
     */
    public static UserLibraryProviderInstallOperationConfig getUserLibConfig (final LibraryProviderOperationConfig libConfig)
    {
        if (!(libConfig instanceof UserLibraryProviderInstallOperationConfig)) // Third-party shared library providers etc.
            return null;

        return (UserLibraryProviderInstallOperationConfig) libConfig;
    }


    /**
     * Updates web.xml for the given project with MyFaces-specific parameters.
     * (This method is public static so that it may be called by other third
     * party library providers for consistent MyFaces configuration across
     * different JSF library providers)
     * 
     * @param project
     *            IProject whose web.xml is to be updated.
     * 
     * @param monitor
     *            IProgressMonitor
     */
    public static void updateWebXmlForMyfaces (final IProject project, 
                                               final IProgressMonitor monitor)
    {
        final WebXmlUpdater updater = new WebXmlUpdater(project, monitor);
        
        updater.addContextParam("javax.servlet.jsp.jstl.fmt.localizationContext", "resources.application", null);  //$NON-NLS-1$//$NON-NLS-2$
        updater.addContextParam("javax.faces.STATE_SAVING_METHOD", "client", Messages.JSFFacetInstallDelegate_StateSavingMethod); //$NON-NLS-1$ //$NON-NLS-2$
        updater.addContextParam("org.apache.myfaces.ALLOW_JAVASCRIPT", "true", Messages.JSFFacetInstallDelegate_AllowJavascriptDescription); //$NON-NLS-1$ //$NON-NLS-2$
        updater.addContextParam("org.apache.myfaces.PRETTY_HTML", "true", Messages.JSFFacetInstallDelegate_PrettyHtmlDescription); //$NON-NLS-1$ //$NON-NLS-2$
        updater.addContextParam("org.apache.myfaces.DETECT_JAVASCRIPT", "false", null);  //$NON-NLS-1$//$NON-NLS-2$
        updater.addContextParam("org.apache.myfaces.AUTO_SCROLL", "true", Messages.JSFFacetInstallDelegate_AutoScrollDescription); //$NON-NLS-1$ //$NON-NLS-2$
        
        updater.addServlet("faces", "org.apache.myfaces.webapp.MyFacesServlet", "1"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        updater.addServletMapping("faces", "org.apache.myfaces.webapp.MyFacesServlet", "*.jsf");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        updater.addServletMapping("faces", "org.apache.myfaces.webapp.MyFacesServlet", "*.faces");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        
        updater.addListener("org.apache.myfaces.webapp.StartupServletContextListener"); //$NON-NLS-1$
        
//        // Additional, more complex updates
//        if (updater.isJavaEEWebapp())
//        {
//            updater.getProvider().modify(new UpdateWebXmlForMyfacesJavaEE(project, monitor), WebXmlUtils.WEB_XML_PATH);
//        }
//        else if (updater.isJ2EEWebapp())
//        {
//            updater.getProvider().modify(new UpdateWebXmlForMyfacesJ2EE(project, monitor), WebXmlUtils.WEB_XML_PATH);
//        }
    }


    /**
     * Updates web.xml for the given project with SunRI-specific parameters.
     * (This method is public static so that it may be called by other third
     * party library providers for consistent Sun-RI configuration across
     * different JSF library providers)
     *
     * @param project
     *            IProject whose web.xml is to be updated.
     *
     * @param monitor
     *            IProgressMonitor
     */
    public static void updateWebXmlForSunRi (final IProject project,
                                             final IProgressMonitor monitor)
    {
        final WebXmlUpdater updater = new WebXmlUpdater(project, monitor);
        
        updater.addContextParam("javax.faces.STATE_SAVING_METHOD", "client", Messages.JSFFacetInstallDelegate_StateSavingMethod);  //$NON-NLS-1$//$NON-NLS-2$
        updater.addContextParam("javax.servlet.jsp.jstl.fmt.localizationContext", "resources.application", null); //$NON-NLS-1$ //$NON-NLS-2$
        updater.addListener("com.sun.faces.config.ConfigureListener"); //$NON-NLS-1$
        
        updater.addServlet("Faces Servlet", "javax.faces.webapp.FacesServlet", "1");   //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        updater.addServletMapping("Faces Servlet", "javax.faces.webapp.FacesServlet", "*.jsf");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        updater.addServletMapping("Faces Servlet", "javax.faces.webapp.FacesServlet", "*.faces");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    }


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
		final List mappings = new ArrayList();
		final String[] patterns = (String[])config.getProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
		for (final String pattern : patterns)
        {
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
					public void run(final IProgressMonitor monitor_inner) throws CoreException{ 
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
		} catch (final CoreException e) {
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
		
		final IModelProvider provider = JSFUtils.getModelProvider(project);
		final IPath webXMLPath = new Path("WEB-INF").append("web.xml"); //$NON-NLS-1$ //$NON-NLS-2$
		if (JSFUtils12.isWebApp25(provider.getModelObject())) {			
			provider.modify(new UpdateWebXMLForJavaEE(project, config), doesDDFileExist(project, webXMLPath) ? webXMLPath : IModelProvider.FORCESAVE); 
		}
		else {//must be 2.3 or 2.4			
			provider.modify(new UpdateWebXMLForJ2EE(project, config), webXMLPath);
		}

		
		// Check if runtime is MyFaces or Sun-RI
		
	}

	private boolean doesDDFileExist(final IProject project, final IPath webXMLPath) {
		return project.getProjectRelativePath().append(webXMLPath).toFile().exists();		
	}

	private class UpdateWebXMLForJavaEE implements Runnable {
		private final IProject project;
		private final IDataModel config;
		
		UpdateWebXMLForJavaEE(final IProject project, final IDataModel config){
			this.project = project;
			this.config = config;
		}
		
		public void run() {
			final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
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
			final List listOfMappings = getServletMappings(config);
			JSFUtils12.setUpURLMappings(webApp, listOfMappings, servlet);
	
			// setup context params
			JSFUtils12.setupConfigFileContextParamForV2_5(webApp, config);
		}
	}
	
	private class UpdateWebXMLForJ2EE implements Runnable {		
		private final IProject project;
		private final IDataModel config;
		
		UpdateWebXMLForJ2EE(final IProject project, final IDataModel config){
			this.project = project ;
			this.config = config;
		}
		
		public void run() {
			final org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp)ModelProviderManager.getModelProvider(project).getModelObject();
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
			final List listOfMappings = getServletMappings(config);
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
