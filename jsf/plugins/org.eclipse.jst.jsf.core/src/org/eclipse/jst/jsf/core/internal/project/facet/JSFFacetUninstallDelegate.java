/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    Debajit Adhikary - Fixes for bug 255097 ("Request to remove input fields 
 *                       from facet install page")
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF Facet Un-install Delegate for WTP faceted projects.
 *	
 *	<li> removes JSF servlet, servlet-mappings and context-params
 *	<li> leaves JSF configuration files on disk
 *  <li> removes JSF classpath containers
 *  
 */
public final class JSFFacetUninstallDelegate implements IDelegate {

    private final boolean jsfFacetConfigurationEnabled = JsfFacetConfigurationUtil.isJsfFacetConfigurationEnabled();

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.project.facet.core.IDelegate#execute(org.eclipse.core.resources.IProject, org.eclipse.wst.common.project.facet.core.IProjectFacetVersion, java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void execute(final IProject project, final IProjectFacetVersion fv,
			final Object config, final IProgressMonitor monitor) throws CoreException {
		{

			if (monitor != null) {
				monitor.beginTask("", 1); //$NON-NLS-1$
			}

			try 
			{
	            final JSFUtils jsfUtil = new JSFUtilFactory().create(fv, ModelProviderManager.getModelProvider(project));
	            if (jsfUtil == null)
	            {
	                throw new JSFFacetException(NLS.bind(
	                        Messages.Could_Not_GetJSFVersion, fv.toString()));
	            }

			    if (jsfFacetConfigurationEnabled)
			    {
    				//Before we do any de-configuration, verify that web.xml is available for update
    				IModelProvider provider = jsfUtil.getModelProvider();
    				if (provider == null ) {				
    					throw new JSFFacetException(NLS.bind(Messages.JSFFacetUninstallDelegate_ConfigErr, project.getName())); 
    				} else if (!(provider.validateEdit(null, null).isOK())){					
    					throw new JSFFacetException(NLS.bind(Messages.JSFFacetUninstallDelegate_NonUpdateableWebXML, project.getName())); 
    				}
			    }
			    
				// Remove JSF Libraries
				( (JSFFacetUninstallConfig) config ).getLibrariesUninstallDelegate().execute( null );
				
                if (jsfFacetConfigurationEnabled)
                {
    				// remove servlet stuff from web.xml
    				uninstallJSFReferencesFromWebApp(project, monitor, jsfUtil);
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
	}
	
	private void uninstallJSFReferencesFromWebApp(final IProject project,
			final IProgressMonitor monitor, final JSFUtils jsfUtil) {
		
		IModelProvider provider = jsfUtil.getModelProvider();
		Object webAppObj = provider.getModelObject();
        if (webAppObj != null)
        {
            IPath ddPath = new Path("WEB-INF").append("web.xml"); //$NON-NLS-1$ //$NON-NLS-2$
            if (isJavaEEWebApp(webAppObj))
            {
                provider.modify(new RemoveJSFFromJavaEEWebAppOperation(project,
                        jsfUtil), ddPath);
            } else
            {// 2.3 or 2.4 web app
                provider.modify(new RemoveJSFFromJ2EEWebAppOperation(project,
                        jsfUtil), ddPath);
            }
        }
	}

	private boolean isJavaEEWebApp(final Object webAppObj) {
		if (webAppObj instanceof WebApp) 
			return true;

		return false;
		
	}

	static class RemoveJSFFromJavaEEWebAppOperation implements Runnable {
		private IProject _project;
        private JSFUtils _jsfUtil;
		
		RemoveJSFFromJavaEEWebAppOperation(final IProject project, JSFUtils jsfUtil){
			this._project = project;
			this._jsfUtil = jsfUtil;
		}
		
        public void run()
        {
            WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(
                    _project).getModelObject();
            _jsfUtil.rollbackWebApp(webApp);
        }
		
	}
	
	static class RemoveJSFFromJ2EEWebAppOperation implements Runnable {
		private IProject _project;
        private JSFUtils _jsfUtil;		
		
		RemoveJSFFromJ2EEWebAppOperation(final IProject project, JSFUtils jsfUtil){
			this._project = project;
			this._jsfUtil = jsfUtil;
		}
		
		public void run() {
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) ModelProviderManager.getModelProvider(_project).getModelObject();
			_jsfUtil.rollbackWebApp(webApp);
		}
	}

}
