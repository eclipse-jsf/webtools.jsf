/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
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
				// Remove JSF Libraries
				removeJSFLibaries(project, fv, monitor);

				// remove servlet stuff from web.xml
				uninstallJSFReferencesFromWebApp(project, monitor);

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

	/**
	 * Removes JSF Lib CP Containers from project
	 * @param project
	 * @param monitor
	 */
	private void removeJSFLibaries(final IProject project, final IProjectFacetVersion fv, final IProgressMonitor monitor) {
		 final IJavaProject jproj = JavaCore.create(project);
		 List keptEntries = new ArrayList();
		 try {
			IClasspathEntry[] entries = jproj.getRawClasspath();
			  keptEntries = new ArrayList();
			 for (int i=0;i<entries.length;i++){
				 IClasspathEntry entry = entries[i];
				 if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER && 
						 ! entry.getPath().segment(0)
						 	.equals(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID))
					 keptEntries.add(entry);
			 }
		} catch (JavaModelException e) {
			JSFCorePlugin.log(e, "Cannot get classpath entries to remove JSF Libraries for: "+project.getName()); //$NON-NLS-1$
		}
		 
		 if (keptEntries.size() > 0){
			 try {
				jproj.setRawClasspath((IClasspathEntry[])keptEntries.toArray(new IClasspathEntry[0]), monitor);
			} catch (JavaModelException e) {
				JSFCorePlugin.log(e, "Exception occured while removing JSF Libraries during JSF Facet uninstall"); //$NON-NLS-1$
			}
		 }	
		
		try {
			ClasspathHelper.removeClasspathEntries(project, fv);				
		} catch (CoreException e) {
			JSFCorePlugin.log(IStatus.ERROR, "Unable to remove server supplied implementation from the classpath.", e);//$NON-NLS-1$
		}
	}
	
	private void uninstallJSFReferencesFromWebApp(final IProject project,
			final IProgressMonitor monitor) {
		
		IModelProvider provider = ModelProviderManager.getModelProvider(project);
		Object webAppObj = provider.getModelObject();
		if (webAppObj != null) {
			if (isJavaEEWebApp(webAppObj)){		
				WebApp webApp = (WebApp)webAppObj;
				Servlet servlet = JSFUtils12.findJSFServlet(webApp);
				if (servlet == null)
					return;

				provider.modify(new RemoveJSFFromJavaEEWebAppOperation(webApp, servlet), new Path("WEB-INF").append("web.xml")); //$NON-NLS-1$ //$NON-NLS-2$
			} else {//2.3 or 2.4 web app
				org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp)webAppObj;
				org.eclipse.jst.j2ee.webapplication.Servlet servlet = JSFUtils11.findJSFServlet(webApp);
				if (servlet == null)
					return;

				provider.modify(new RemoveJSFFromJ2EEWebAppOperation(webApp, servlet), new Path("WEB-INF").append("web.xml")); //$NON-NLS-1$ //$NON-NLS-2$				
			}
		}

	}

	private boolean isJavaEEWebApp(final Object webAppObj) {
		if (webAppObj instanceof WebApp) 
			return true;

		return false;
		
	}

	static class RemoveJSFFromJavaEEWebAppOperation implements Runnable {
		private WebApp _webApp;
		private Servlet _servlet;
		
		RemoveJSFFromJavaEEWebAppOperation(final WebApp webApp, final Servlet servlet){
			this._webApp = webApp;
			this._servlet = servlet;
		}
		
		public void run() {
			// remove faces url mappings
			JSFUtils12.removeURLMappings(_webApp, _servlet);
			// remove context params
			removeJSFContextParams(_webApp, _servlet);
			// remove servlet
			removeJSFServlet(_webApp, _servlet);
			
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
		private org.eclipse.jst.j2ee.webapplication.WebApp _webApp;
		private org.eclipse.jst.j2ee.webapplication.Servlet _servlet;
		
		RemoveJSFFromJ2EEWebAppOperation(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet){
			this._webApp = webApp;
			this._servlet = servlet;
		}
		
		public void run() {
			// remove faces url mappings
			JSFUtils11.removeURLMappings(_webApp, _servlet);
			// remove context params
			removeJSFContextParams(_webApp, _servlet);
			// remove servlet
			removeJSFServlet(_webApp, _servlet);
			
		}
		private void removeJSFContextParams(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet) {
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				ParamValue cp = (ParamValue) it.next();
				if (cp.getParamName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
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
