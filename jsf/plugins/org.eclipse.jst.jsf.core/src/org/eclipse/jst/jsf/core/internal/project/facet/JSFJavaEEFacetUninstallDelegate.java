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

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;

/**
 * JSF v1.2 Facet Uninstall Delegate for WTP faceted projects.
 *	
 *	<li> removes JSF servlet, servlet-mappings and context-params
 *  
 */
public class JSFJavaEEFacetUninstallDelegate extends AbstractJSFFacetUninstallDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.project.facet.AbstractJSFFacetUninstallDelegate#uninstallJSFReferencesFromWebApp(org.eclipse.core.resources.IProject, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void uninstallJSFReferencesFromWebApp(IProject project,
			IProgressMonitor monitor) {
		
		IModelProvider provider = ModelProviderManager.getModelProvider(project);
		Object webAppObj = provider.getModelObject();
		if (webAppObj != null && JSFUtils12.isWebApp25(webAppObj)){
			WebApp webApp = (WebApp)webAppObj;
			Servlet servlet = JSFUtils12.findJSFServlet(webApp);
			if (servlet == null)
				return;

			provider.modify(new RemoveJSFFromWebAppOperation(webApp, servlet), new Path("WEB-INF").append("web.xml")); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}

	private void removeJSFContextParams(WebApp webApp, Servlet servlet) {
		Iterator it = webApp.getContextParams().iterator();
		while (it.hasNext()) {
			ParamValue cp = (ParamValue) it.next();
			if (cp.getParamName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
				webApp.getContextParams().remove(cp);
				break;
			}
		}
	}

	private void removeJSFServlet(WebApp webApp, Servlet servlet) {
		webApp.getServlets().remove(servlet);
	}

	class RemoveJSFFromWebAppOperation implements Runnable {
		private WebApp webApp;
		private Servlet servlet;
		
		RemoveJSFFromWebAppOperation(WebApp webApp, Servlet servlet){
			this.webApp = webApp;
			this.servlet = servlet;
		}
		
		public void run() {
			// remove faces url mappings
			JSFUtils12.removeURLMappings(webApp, servlet);
			// remove context params
			removeJSFContextParams(webApp, servlet);
			// remove servlet
			removeJSFServlet(webApp, servlet);
			
		}
		
	}
}
