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

package org.eclipse.jst.jsf.core.internal.launch;

import java.util.Iterator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;

/**
 * Extends the FileURL extension-point so that a JSF JSP page
 * can have it's URL mapped to the Faces Servlet using the servlet-mapping 
 * specified in the web.xml file
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class JSFFileURL implements FileURL {

	/**
	 * If this is a JSP page, this will return a URL using the first valid servlet-mapping to the Faces Servlet
	 * if found.   If the faces servlet is not defined in the web.xml or there is no servlet-mapping, this will return null.
	 * 
	 * The resource's file extension is compared with the javax.faces.DEFAULT_SUFFIX context-param if set.   If the same, then 
	 * extension mapping will be used if found in the web.xml.  If not set, "jsp" default is assumed.   If resource does not have the default
	 * file extension, then it must have either: jsp,jspx, or jsf file extensions in order to be considered for launch.
	 * 
	 * This will not guarentee a page to be run.  The user will still need a properly configured server with valid URL mappings and
	 * context-params.
	 * 
	 * TODO: We could detect and insert or just warn for context-params and url mappings to run.  However, this would better detected during a JSF App validation step.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL#getFileURL(org.eclipse.core.resources.IResource, org.eclipse.core.runtime.IPath)
	 */
	public IPath getFileURL(IResource resource, IPath existingURL) {
		WebArtifactEdit webAppEdit = null;
		
		try {
			//is this is a Faces Project with a Faces Servlet?
			//we will not check to see if facet is installed.  
			// check to see if this resource is a JSF page (currently always true) and then,
			//if servlet is present, we will change the url based on first mapping found 
			webAppEdit = JSFUtils.getWebArtifactEditForRead(resource.getProject());
			if (webAppEdit != null){
				WebApp webApp = webAppEdit.getWebApp();
				if (webApp != null){
					Servlet servlet = JSFUtils.findJSFServlet(webAppEdit.getWebApp());
					if (servlet == null)//if no faces servlet, do nothing
						return null;
					
					//if not a JSF page, do nothing
					if (!isJSFPage(resource))
						return null;
					
					String defaultSuffix = getDefaultSuffix(webApp);
					//is the resource using default_suffix
					boolean canUseExtensionMapping = resource.getFileExtension().equalsIgnoreCase(defaultSuffix);
					
					//if not using default extension and is not a known file extension, then we will abort
					if (! canUseExtensionMapping && ! isValidKnownExtension(resource.getFileExtension()))
						return null;
					
					Iterator mappings = servlet.getMappings().iterator();
					ServletMapping map = null;
					String foundFileExtension = null;
					while (mappings.hasNext()){
						map = (ServletMapping)mappings.next();
						
						foundFileExtension = getFileExtensionFromMap(map);
						if (foundFileExtension != null && canUseExtensionMapping) {
							return existingURL.removeFileExtension().addFileExtension(foundFileExtension);
						}
							
						String foundPrefixMapping = getPrefixMapping(map);
						if (foundPrefixMapping != null){						
							return new Path(foundPrefixMapping).append(existingURL); 
						}
						
					}
					
					if (! canUseExtensionMapping && foundFileExtension != null){
						//we could prompt user that this may not work...
						//for now we will return the extension mapping
						return existingURL.removeFileExtension().addFileExtension(foundFileExtension);
					}
					
					//we could, at this point, add a url mapping to the faces servlet, or prompt user that it may be a good idea to add one... ;-
				}
			}
			return null;
		} finally {
			if (webAppEdit != null)
				webAppEdit.dispose();
		}
	}
	
	private boolean isValidKnownExtension(String fileExtension) {
		if ((	fileExtension.equalsIgnoreCase("jsp") ||  //$NON-NLS-1$
				fileExtension.equalsIgnoreCase("jspx") ||  //$NON-NLS-1$
				fileExtension.equalsIgnoreCase("jsf"))) //$NON-NLS-1$
			return true;

		return false;
	}

	private String getDefaultSuffix(WebApp webApp) {
		String defaultSuffix = "jsp"; //$NON-NLS-1$
		for (Iterator it = webApp.getContexts().iterator();it.hasNext();) {		
			ContextParam cp = (ContextParam) it.next();		
			if (cp.getParamName().equals(JSFUtils.JSF_DEFAULT_SUFFIX_CONTEXT_PARAM)){				
				String defSuffix = cp.getParamValue();
				if (defSuffix.startsWith(".")) //$NON-NLS-1$
					defSuffix = defSuffix.substring(1);
								
				return defSuffix;
			}
		}
		return defaultSuffix;
	}
	
	private String getPrefixMapping(ServletMapping map) {
		IPath extPath = new Path(map.getUrlPattern());
		if (extPath != null){
			String ext = extPath.getFileExtension();
			if (ext == null){
				String lastSeg = extPath.lastSegment();
				if (lastSeg.equals("*")) //$NON-NLS-1$
				{
					return extPath.removeLastSegments(1).toString();
				}
				
				return extPath.toString();				
			}
		}
		return null;
	}
	
	private String getFileExtensionFromMap(ServletMapping map) {
		IPath extPath = new Path(map.getUrlPattern());
		if (extPath != null){
			String ext = extPath.getFileExtension();
			if (ext != null && !ext.equals("")) //$NON-NLS-1$
				return ext;
		}
		return null;
	}
	
	private boolean isJSFPage(IResource resource) {
		// currently always return true.
		// need to find quick way of determining whether this is a JSF JSP Page
		return true;
	}
}
