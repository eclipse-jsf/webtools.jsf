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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Utility file for JSF model v1.1
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFUtils11 extends JSFUtils {	
	
	/**
	 * Convenience method for getting writeable WebApp model
	 * @param project
	 * @return WebArtifactEdit
	 * @deprecated - must use IModelProviders
	 */
	public static WebArtifactEdit getWebArtifactEditForWrite(final IProject project) {
		return WebArtifactEdit.getWebArtifactEditForWrite(project);
	}

	/**
	 * Convenience method for getting read-only WebApp model
	 * @param project
	 * @return WebArtifactEdit
	 * @deprecated - must use IModelProviders
	 */
	public static WebArtifactEdit getWebArtifactEditForRead(final IProject project) {
		return WebArtifactEdit.getWebArtifactEditForRead(project);
	}

	/**
	 * @param webApp as Object
	 * @return Servlet - the JSF Servlet for the specified WebApp or null if not present
	 */
	public static Servlet findJSFServlet(final Object webApp) {
		Iterator it = null;
		if (webApp == null)
			return null;
		else if (webApp instanceof WebApp)
			it = ((WebApp)webApp).getServlets().iterator();
		else if (webApp instanceof org.eclipse.jst.javaee.web.WebApp)
			it = ((org.eclipse.jst.javaee.web.WebApp)webApp).getServlets().iterator();
		else
			return null;
		
		while (it.hasNext()) {
            Servlet servlet = (Servlet) it.next();
			if (servlet != null && servlet.getWebType() != null) {
				
				if(	servlet.getWebType().isServletType()) {
					if (((ServletType) servlet.getWebType()).getClassName() != null && 
							((ServletType) servlet.getWebType()).getClassName().trim().equals(
							JSF_SERVLET_CLASS)) {
						return servlet;
					}
				} else if (servlet.getWebType().isJspType()) {
					if (((JSPType) servlet.getWebType()).getJspFile() != null && 
							((JSPType) servlet.getWebType()).getJspFile().trim().equals(
							JSF_SERVLET_CLASS)) {
						return servlet;
					}
				}
			}
		}
        
        // if we get to here then we have finished the loop
        // without finding the servlet we're looking for
		return null;
	}

	/**
	 * Creates a stubbed JSF configuration file for specified JSF version and path
	 * @param jsfVersion
	 * @param configPath
	 */
	public static void createConfigFile(final String jsfVersion, final IPath configPath) {
		FileOutputStream os = null;
		PrintWriter pw = null;
		final String QUOTE = new String(new char[] { '"' });
		try {
			IPath dirPath = configPath.removeLastSegments(1);
			dirPath.toFile().mkdirs();
			File file = configPath.toFile();
			file.createNewFile();
			os = new FileOutputStream(file);
			pw = new PrintWriter(os);
			pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ QUOTE + "UTF-8" + QUOTE + "?>\n\n"); //$NON-NLS-1$ //$NON-NLS-2$

			if (jsfVersion.equals(IJSFCoreConstants.FACET_VERSION_1_1)) 
            { 
				pw.write("<!DOCTYPE faces-config PUBLIC\n"); //$NON-NLS-1$
				pw
						.write("    " //$NON-NLS-1$
								+ QUOTE
								+ "-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.1//EN" //$NON-NLS-1$
								+ QUOTE + "\n"); //$NON-NLS-1$
				pw.write("    " + QUOTE //$NON-NLS-1$
						+ "http://java.sun.com/dtd/web-facesconfig_1_1.dtd" //$NON-NLS-1$
						+ QUOTE + ">\n\n"); //$NON-NLS-1$

				pw.write("<faces-config>\n\n"); //$NON-NLS-1$
				pw.write("</faces-config>\n"); //$NON-NLS-1$
			} 

			pw.close();
			pw = null;
		} catch (FileNotFoundException e) {
			JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorCreatingConfigFile, e);
		} catch (IOException e) {
			JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorCreatingConfigFile, e);
		} finally {
			if (pw != null)
				pw.close();
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					JSFCorePlugin.log(IStatus.ERROR, Messages.JSFUtils_ErrorClosingConfigFile, e);
				}
			}
		}
	}

	/**
	 * Creates servlet reference in WebApp if not present or updates servlet name if found
	 * using the passed configuration.
	 * 
	 * @param webApp
	 * @param config
	 * @param servlet
	 * @return Servlet servlet - if passed servlet was null, will return created servlet
	 */
	public static Servlet createOrUpdateServletRef(final WebApp webApp,
			final IDataModel config, Servlet servlet) {
		
		String displayName 	= getDisplayName(config);
		String className 	= getServletClassname(config);
		
		if (servlet == null){			
			// Create the servlet instance and set up the parameters from data
			// model
			servlet = WebapplicationFactory.eINSTANCE.createServlet();
			servlet.setServletName(displayName);

			ServletType servletType = WebapplicationFactory.eINSTANCE
					.createServletType();
			servletType.setClassName(className);
			servlet.setWebType(servletType);
			servlet.setLoadOnStartup(Integer.valueOf(1));
			// Add the servlet to the web application model
			webApp.getServlets().add(servlet);			
		} else {
			// update
			updateServletMappings(webApp, servlet, servlet.getServletName().trim(), displayName);
			servlet.setServletName(displayName);
			servlet.setLoadOnStartup(Integer.valueOf(1));
		}
		return servlet;
	}

	private static void updateServletMappings(final WebApp webApp, final Servlet servlet,
			final String servletName, final String newServletName) {
		List<ServletMapping> mappings = findServletMappings(webApp, servlet, servletName);
		for (ServletMapping map : mappings){
			map.setName(newServletName);
		}
		
	}

	
	private static List<ServletMapping> findServletMappings(final WebApp webApp, final Servlet servlet, final String servletName) {			
		List<ServletMapping> mappings = new ArrayList<ServletMapping>();
		List<ServletMapping> allMappings = webApp.getServletMappings();			
		for (int i=allMappings.size()-1;i>=0;--i){
			ServletMapping mapping = allMappings.get(i);
			if (mapping != null && 
					mapping.getServlet() != null && 
					mapping.getServlet().getServletName() != null &&
					mapping.getServlet().getServletName().trim().equals(servletName))
				mappings.add(mapping);
		}		
		return mappings;		
	}

	/**
	 * @param webApp as Object
	 * @return true if webApp instanceof org.eclipse.jst.javaee.web.WebApp
	 */
	public static boolean isWebApp25(final Object webApp) {
		if (webApp instanceof org.eclipse.jst.javaee.web.WebApp)
			return true;
		return false;
	}

	/**
	 * Creates servlet-mappings for the servlet
	 * 
	 * @param webApp
	 * @param urlMappingList - list of string values to  be used in url-pattern for servlet-mapping
	 * @param servlet
	 */
	public static void setUpURLMappings(final WebApp webApp, final List urlMappingList,
			final Servlet servlet) {
		// Add mappings
		Iterator it = urlMappingList.iterator();
		while (it.hasNext()) {
			String pattern = (String) it.next();
			if (!(doesServletMappingExist(webApp, servlet, pattern))){
				ServletMapping mapping = WebapplicationFactory.eINSTANCE
						.createServletMapping();
				mapping.setServlet(servlet);
				mapping.setName(servlet.getServletName());
				mapping.setUrlPattern(pattern);
				webApp.getServletMappings().add(mapping);
			}
		}
	}
	

	private static boolean doesServletMappingExist(final WebApp webApp, final Servlet servlet,
			final String pattern) {	
		
		List mappings = webApp.getServletMappings();
		String servletName = servlet.getServletName();
		if (servletName != null) {
			for (int i=mappings.size()-1;i>=0;--i){
				ServletMapping mapping = (ServletMapping)mappings.get(i);
				if (mapping != null && 
						mapping.getServlet() != null && 
						mapping.getServlet().getServletName() != null &&
						mapping.getServlet().getServletName().trim().equals(servletName) &&
						mapping.getUrlPattern() != null && 
						mapping.getUrlPattern().trim().equals(pattern)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes servlet-mappings for servlet using servlet-name.
	 * @param webApp
	 * @param servlet
	 */
	public static void removeURLMappings(final WebApp webApp, final Servlet servlet) {
		List mappings = webApp.getServletMappings();
		String servletName = servlet.getServletName();
		if (servletName != null) {
			for (int i=mappings.size()-1;i>=0;--i){
				ServletMapping mapping = (ServletMapping)mappings.get(i);
				if (mapping != null && 
						mapping.getServlet() != null && 
						mapping.getServlet().getServletName() != null &&
						mapping.getServlet().getServletName().trim()
							.equals(servletName)) {
					mappings.remove(mapping);
				}
			}
		}
	}

	/**
	 * Creates or updates config file context-param in v 2.3 WebApp if non default configuration file is specified.
	 * @param webApp
	 * @param config
	 */
	public static void setupConfigFileContextParamForV2_3(final WebApp webApp,
			final IDataModel config) {
		// if not default name and location, then add context param
		ContextParam cp = null;
		ContextParam foundCP = null;
		boolean found = false;
		if (!config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH).equals(JSF_DEFAULT_CONFIG_PATH)) {
			// check to see if present
			Iterator it = webApp.getContexts().iterator();
			while (it.hasNext()) {
				cp = (ContextParam) it.next();
				if (cp != null &&
						cp.getParamName() != null &&
						cp.getParamName().equals(JSF_CONFIG_CONTEXT_PARAM)) {
					foundCP = cp;
					found = true;
				}
			}
			if (!found) {
				cp = WebapplicationFactory.eINSTANCE.createContextParam();
				cp.setParamName(JSF_CONFIG_CONTEXT_PARAM);
				cp.setParamValue(config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
				webApp.getContexts().add(cp);
			} else {
				cp = foundCP;
				if (cp.getParamValue().indexOf(config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH)) < 0) {
					String curVal = cp.getParamValue();
					String val = config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
					if (curVal != null && !"".equals(curVal.trim())) { //$NON-NLS-1$
						val = curVal + ",\n" + val; //$NON-NLS-1$
					}
					cp.setParamValue(val);
				}
			}
		}
	}
	/**
	 * Creates or updates config file context-param in v2.4 WebApp  if non default configuration file is specified.
	 * @param webApp
	 * @param config
	 */
	public static void setupConfigFileContextParamForV2_4(final WebApp webApp,
			final IDataModel config) {
		// if not default name and location, then add context param
		ParamValue foundCP = null;
		ParamValue cp = null;
		boolean found = false;
		if (!config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH).equals(JSF_DEFAULT_CONFIG_PATH)) {
			// check to see if present
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				cp = (ParamValue) it.next();
				if (cp != null && 
						cp.getName() != null &&
						cp.getName().trim().equals(JSF_CONFIG_CONTEXT_PARAM)) {
					foundCP = cp;
					found = true;
				}
			}
			if (!found) {
				ParamValue pv = CommonFactory.eINSTANCE.createParamValue();
				pv.setName(JSF_CONFIG_CONTEXT_PARAM);
				pv.setValue(config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
				webApp.getContextParams().add(pv);
			} else {
				cp = foundCP;
				if (cp.getValue().indexOf(config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH)) < 0) {
					String curVal = cp.getValue();
					String val = config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
					if (curVal != null && !"".equals(curVal.trim())) { //$NON-NLS-1$
						val = curVal + ",\n" + val; //$NON-NLS-1$
					}
					cp.setValue(val);
				}
			}
		}
	}

	/**
	 * @param webAppObj as Object
	 * @return true if webApp instanceof org.eclipse.jst.j2ee.web.WebApp and versionID == 24
	 */
	public static boolean isWebApp24(final Object webAppObj) {
		if (webAppObj instanceof WebApp &&
				((WebApp)webAppObj).getVersionID() == 24)
			return true;
		return false;
	}
	
	/**
	 * @param webAppObj as Object
	 * @return true if webApp instanceof org.eclipse.jst.j2ee.web.WebApp and versionID == 23
	 */
	public static boolean isWebApp23(final Object webAppObj) {
		if (webAppObj instanceof WebApp &&
				((WebApp)webAppObj).getVersionID() == 23)
			return true;
		return false;
	}
	
	/**
	 * @param webApp
	 * @return the default file extension from the context param.  Default is "jsp" if no context param.
	 */
	public static String getDefaultSuffix(final WebApp webApp) {
		String defaultSuffix = "jsp"; //$NON-NLS-1$
		for (Iterator it = webApp.getContexts().iterator();it.hasNext();) {		
			ContextParam cp = (ContextParam) it.next();		
			if (cp != null &&
					cp.getParamName() != null && 
					cp.getParamName().trim().equals(JSF_DEFAULT_SUFFIX_CONTEXT_PARAM)){				
				String defSuffix = cp.getParamValue();
				if (defSuffix.startsWith(".")) //$NON-NLS-1$
					defSuffix = defSuffix.substring(1);
								
				return defSuffix;
			}
		}
		return defaultSuffix;
	}
	
	/**
	 * @param map
	 * @return prefix mapping.  may return null.
	 */
	public static String getPrefixMapping(final ServletMapping map) {
		IPath extPath = new Path(map.getUrlPattern());
		if (extPath != null){
			String ext = extPath.getFileExtension();
			if (ext == null){
				String lastSeg = extPath.lastSegment();
				if (lastSeg != null && 
						lastSeg.equals("*")) //$NON-NLS-1$
				{
					return extPath.removeLastSegments(1).toString();
				}
				
				return extPath.toString();				
			}
		}
		return null;
	}
	
	/**
	 * @param map
	 * @return extension from map.  Will return null if file extension not found in url patterns.
	 */
	public static String getFileExtensionFromMap(final ServletMapping map) {
		IPath extPath = new Path(map.getUrlPattern());
		if (extPath != null){
			String ext = extPath.getFileExtension();
			if (ext != null && !ext.equals("")) //$NON-NLS-1$
				return ext;
		}
		return null;
	}

}
