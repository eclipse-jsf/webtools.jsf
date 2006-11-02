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
	 * Currently (11/21/05) this will only work if file extension is .jsf 
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL#getFileURL(org.eclipse.core.resources.IResource, org.eclipse.core.runtime.IPath)
	 */
	public IPath getFileURL(IResource resource, IPath existingURL) {
		WebArtifactEdit webAppEdit = null;

		//temporarily, we will filter on jsp file extensions only.
		if (!resource.getFileExtension().equalsIgnoreCase("jsp")) //$NON-NLS-1$
			return null;
		
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
					
					Iterator mappings = servlet.getMappings().iterator();
					ServletMapping map = null;
					while (mappings.hasNext()){
						map = (ServletMapping)mappings.next();
						
						String foundFileExtension = getFileExtensionFromMap(map);
						if (foundFileExtension != null)
							return existingURL.removeFileExtension().addFileExtension(foundFileExtension);

						String foundDirectoryMapping = getDirectoryMapping(map);
						if (foundDirectoryMapping != null){						
							return new Path(foundDirectoryMapping).append(existingURL); 
						}
					}					
				}
			}
			return null;
		} finally {
			if (webAppEdit != null)
				webAppEdit.dispose();
		}
	}
	
	private String getDirectoryMapping(ServletMapping map) {
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
