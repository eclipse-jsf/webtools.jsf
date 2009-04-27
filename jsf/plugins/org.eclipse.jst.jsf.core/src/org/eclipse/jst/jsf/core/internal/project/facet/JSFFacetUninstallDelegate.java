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

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.Servlet;
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

			try {
			    if (jsfFacetConfigurationEnabled)
			    {
    				//Before we do any de-configuration, verify that web.xml is available for update
    				IModelProvider provider = JSFUtils.getModelProvider(project);
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
    				uninstallJSFReferencesFromWebApp(project, monitor);
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
			final IProgressMonitor monitor) {
		
		IModelProvider provider = JSFUtils.getModelProvider(project);
		Object webAppObj = provider.getModelObject();
		if (webAppObj != null) {
			IPath ddPath = new Path("WEB-INF").append("web.xml"); //$NON-NLS-1$ //$NON-NLS-2$
			if (isJavaEEWebApp(webAppObj)){		
				WebApp webApp = (WebApp)webAppObj;
				Servlet servlet = JSFUtils12.findJSFServlet(webApp);
				if (servlet == null)
					return;

				provider.modify(new RemoveJSFFromJavaEEWebAppOperation(project), ddPath);
			} else {//2.3 or 2.4 web app
				org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp)webAppObj;
				org.eclipse.jst.j2ee.webapplication.Servlet servlet = JSFUtils11.findJSFServlet(webApp);
				if (servlet == null)
					return;

				provider.modify(new RemoveJSFFromJ2EEWebAppOperation(project), ddPath);			
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
		
		RemoveJSFFromJavaEEWebAppOperation(final IProject project){
			this._project = project;			
		}
		
		public void run() {
			WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(_project).getModelObject();
			Servlet servlet = JSFUtils12.findJSFServlet(webApp);
			
			// remove faces url mappings
			JSFUtils12.removeURLMappings(webApp, servlet);
			// remove context params
			removeJSFContextParams(webApp, servlet);
			// remove servlet
			removeJSFServlet(webApp, servlet);
			
		}
		private void removeJSFContextParams(final WebApp webApp, final Servlet servlet) {
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				ParamValue cp = (ParamValue) it.next();
				if (cp.getParamName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
					webApp.getContextParams().remove(cp);
					break;
				}
			}
		}

		private void removeJSFServlet(final WebApp webApp, final Servlet servlet) {		
			webApp.getServlets().remove(servlet);
		}
		
	}
	
	static class RemoveJSFFromJ2EEWebAppOperation implements Runnable {
		private IProject _project;		
		
		RemoveJSFFromJ2EEWebAppOperation(final IProject project){
			this._project = project;
		}
		
		public void run() {
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) ModelProviderManager.getModelProvider(_project).getModelObject();
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = JSFUtils11.findJSFServlet(webApp);

			// remove faces url mappings
			JSFUtils11.removeURLMappings(webApp, servlet);
			// remove context params
			removeJSFContextParams(webApp, servlet);
			// remove servlet
			removeJSFServlet(webApp, servlet);
			
		}
		private void removeJSFContextParams(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet) {
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				org.eclipse.jst.j2ee.common.ParamValue cp = (org.eclipse.jst.j2ee.common.ParamValue) it.next();
				if (cp.getName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
					webApp.getContextParams().remove(cp);
					break;
				}
			}
		}

		private void removeJSFServlet(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet) {
			webApp.getServlets().remove(servlet);
		}
		
	}

}
