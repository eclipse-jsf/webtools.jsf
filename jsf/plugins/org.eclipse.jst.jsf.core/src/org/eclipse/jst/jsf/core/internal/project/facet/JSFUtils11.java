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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Utility file for JSF model v1.1
 * 
 * @author Gerry Kessler - Oracle
 */
/*package use JSFUtilFactory*/ class JSFUtils11 extends JSFUtils {	
	
	/**
	 * @param modelProvider 
	 */
	protected JSFUtils11(final IModelProvider modelProvider)
    {
        super(JSFVersion.V1_1, modelProvider);
    }

    /**
     * @param webApp
     *            as Object
     * @return Servlet - the JSF Servlet for the specified WebApp or null if not
     *         present
     */
    private Servlet findJSFServlet(final WebApp webApp)
    {
        if (webApp == null)
        {
            return null;
        }

        final Iterator it = webApp.getServlets().iterator();

        while (it.hasNext())
        {
            final Servlet servlet = (Servlet) it.next();
            if (servlet != null && servlet.getWebType() != null)
            {
                if (servlet.getWebType().isServletType())
                {
                    if (((ServletType) servlet.getWebType()).getClassName() != null
                            && ((ServletType) servlet.getWebType())
                                    .getClassName().trim().equals(
                                            JSF_SERVLET_CLASS))
                    {
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
     * Creates a stubbed JSF configuration file for specified JSF version and
     * path
     */
    @Override
    public void doVersionSpecificConfigFile(final PrintWriter pw)
    {
        final String QUOTE = new String(new char[]
        { '"' });
        pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + QUOTE + "UTF-8" + QUOTE + "?>\n\n"); //$NON-NLS-1$ //$NON-NLS-2$

        pw.write("<!DOCTYPE faces-config PUBLIC\n"); //$NON-NLS-1$
        pw.write("    " //$NON-NLS-1$
                + QUOTE
                + "-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.1//EN" //$NON-NLS-1$
                + QUOTE + "\n"); //$NON-NLS-1$
        pw.write("    " + QUOTE //$NON-NLS-1$
                + "http://java.sun.com/dtd/web-facesconfig_1_1.dtd" //$NON-NLS-1$
                + QUOTE + ">\n\n"); //$NON-NLS-1$

        pw.write("<faces-config>\n\n"); //$NON-NLS-1$
        pw.write("</faces-config>\n"); //$NON-NLS-1$
    }

    /**
     * Creates servlet reference in WebApp if not present or updates servlet
     * name if found using the passed configuration.
     * 
     * @param webApp
     * @param config
     * @param servlet
     * @return Servlet servlet - if passed servlet was null, will return created
     *         servlet
     */
    private Servlet createOrUpdateServletRef(final WebApp webApp,
            final IDataModel config, Servlet servlet)
    {
 
        String displayName = getDisplayName(config);
        String className = getServletClassname(config);

        if (servlet == null)
        {
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
        } 
        else
        {
            // update
            updateServletMappings(webApp, servlet, displayName);
            servlet.setServletName(displayName);
            servlet.setLoadOnStartup(Integer.valueOf(1));
        }
        return servlet;
    }

    private void updateServletMappings(final WebApp webApp,
            final Servlet servlet, final String displayName)
    {
        List<ServletMapping> mappings = findServletMappings(webApp, servlet);
        for (ServletMapping map : mappings)
        {
            map.setName(displayName);
        }

    }

	private List<ServletMapping> findServletMappings(final WebApp webApp, final Servlet servlet) {
	    String servletName = servlet.getServletName();
	    servletName = servletName != null ? servletName.trim() : servletName;
	    final List<ServletMapping> mappings = new ArrayList<ServletMapping>();
		final List<ServletMapping> allMappings = webApp.getServletMappings();
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
	 * Creates servlet-mappings for the servlet
	 * 
	 * @param webApp
	 * @param urlMappingList - list of string values to  be used in url-pattern for servlet-mapping
	 * @param servlet
	 */
	private void setUpURLMappings(final WebApp webApp, final List urlMappingList,
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
	

	private boolean doesServletMappingExist(final WebApp webApp, final Servlet servlet,
			final String pattern) {	
		
		List mappings = webApp.getServletMappings();
		String servletName = servlet.getServletName();
		if (servletName != null) 
		{
		    final Iterator it = mappings.iterator();
			while(it.hasNext())
			{
				ServletMapping mapping = (ServletMapping) it.next();
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
	private void removeURLMappings(final WebApp webApp, final Servlet servlet) {
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
	private void setupConfigFileContextParamForV2_3(final WebApp webApp,
			final IDataModel config) {
		// if not default name and location, then add context param
		ContextParam cp = null;
		ContextParam foundCP = null;
		boolean found = false;
        final String stringProperty = 
            config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
        if (stringProperty != null &&
                !stringProperty.equals(JSF_DEFAULT_CONFIG_PATH)) {
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
				cp.setParamValue(stringProperty);
				webApp.getContexts().add(cp);
			} else {
				cp = foundCP;
				if (cp.getParamValue().indexOf(stringProperty) < 0) {
					String curVal = cp.getParamValue();
					String val = stringProperty;
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
	private void setupConfigFileContextParamForV2_4(final WebApp webApp,
			final IDataModel config) {
		// if not default name and location, then add context param
		ParamValue foundCP = null;
		ParamValue cp = null;
		boolean found = false;
		final String stringProperty = 
		    config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
        if (stringProperty != null &&
                !stringProperty.equals(JSF_DEFAULT_CONFIG_PATH)) {
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
				pv.setValue(stringProperty);
				webApp.getContextParams().add(pv);
			} else {
				cp = foundCP;
				if (cp.getValue().indexOf(stringProperty) < 0) {
					String curVal = cp.getValue();
					String val = stringProperty;
					if (curVal != null && !"".equals(curVal.trim())) { //$NON-NLS-1$
						val = curVal + ",\n" + val; //$NON-NLS-1$
					}
					cp.setValue(val);
				}
			}
		}
	}

    /**
     * @param webApp
     * @return the default file extension from the context param. Default is
     *         "jsp" if no context param.
     */
    private String getDefaultSuffix(final WebApp webApp)
    {
        String defaultSuffix = getDefaultDefaultSuffix();
        if ("2.3".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            for (Iterator it = webApp.getContexts().iterator(); it.hasNext();)
            {
                ContextParam cp = (ContextParam) it.next();
                if (cp != null)
                {
                    final String paramName = cp.getParamName();
                    final String suffix = calculateSuffix(paramName, cp
                            .getParamValue());
                    if (suffix != null)
                    {
                        return suffix;
                    }
                }
            }
        }
        else if ("2.4".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            for (Iterator it = webApp.getContextParams().iterator(); it.hasNext();)
            {
                ParamValue cp = (ParamValue) it.next();
                if (cp != null)
                {
                    final String paramName = cp.getName();
                    final String suffix = calculateSuffix(paramName, cp
                            .getValue());
                    if (suffix != null)
                    {
                        return suffix;
                    }
                }
            }
        }
        return defaultSuffix;
    }

    /**
     * @param map
     * @return prefix mapping. may return null.
     */
    private String getPrefixMapping(final ServletMapping map)
    {
        final String urlPattern = map.getUrlPattern();
        if (urlPattern != null && urlPattern.trim().length() != 0)
        {
            IPath extPath = new Path(urlPattern);
            if (extPath != null)
            {
                String ext = extPath.getFileExtension();
                if (ext == null)
                {
                    String lastSeg = extPath.lastSegment();
                    if (lastSeg != null && lastSeg.equals("*")) //$NON-NLS-1$
                    {
                        return extPath.removeLastSegments(1).toString();
                    }

                    return extPath.toString();
                }
            }
        }
        return null;
    }

    /**
     * @param map
     * @return extension from map. Will return null if file extension not found
     *         in url patterns.
     */
    private String getFileExtensionFromMap(final ServletMapping map)
    {
        final String urlPattern = map.getUrlPattern();
        if (urlPattern != null
                && urlPattern.trim().length() != 0)
        {
            IPath extPath = new Path(map.getUrlPattern());
            if (extPath != null)
            {
                String ext = extPath.getFileExtension();
                if (ext != null && ext.trim().length() != 0)
                {
                    return ext;
                }
            }
        }
        return null;
    }

    @Override
    public void updateWebApp(Object webApp, IDataModel config)
    {
        // create or update servlet ref
        Servlet servlet = findJSFServlet((WebApp) webApp);// check to see
                                                            // if already
                                                            // present
        
        
        servlet = createOrUpdateServletRef((WebApp) webApp, config, servlet);

        // init mappings
        final List listOfMappings = getServletMappings(config);
        setUpURLMappings((WebApp) webApp, listOfMappings, servlet);

        // setup context params
        setupContextParams((WebApp) webApp, config);
    }

    private void setupContextParams(WebApp webApp, final IDataModel config) {
        if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)//shouldn't have to do it this way, but that's the way it goes 119442
        {
            setupConfigFileContextParamForV2_3(webApp, config);
        }
        else if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_4_ID)
        {
            setupConfigFileContextParamForV2_4(webApp, config);
        }
        else
        {
            throw new IllegalArgumentException("Invalid argument: "+webApp.getVersionID()); //$NON-NLS-1$
        }
    }

    @Override
    public void rollbackWebApp(Object webApp)
    {
        org.eclipse.jst.j2ee.webapplication.Servlet servlet = findJSFServlet((WebApp) webApp);
        if (servlet == null)
        {
            return;
        }
        // remove faces url mappings
        removeURLMappings((WebApp) webApp, servlet);
        // remove context params
        removeJSFContextParams((WebApp) webApp, servlet);
        // remove servlet
        removeJSFServlet((WebApp) webApp, servlet);
    }

    private void removeJSFContextParams(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet) {
        if ("2.3".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            Iterator it = webApp.getContexts().iterator();
            while (it.hasNext()) 
            {
                final ContextParam cp = (ContextParam) it.next();
                if (JSFUtils.JSF_CONFIG_CONTEXT_PARAM.equals(cp.getParamName())) 
                {
                    webApp.getContexts().remove(cp);
                    break;
                }
            }
        }
        else if ("2.4".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            Iterator it = webApp.getContextParams().iterator();
            while (it.hasNext()) 
            {
                ParamValue cp = (ParamValue) it.next();
                if (cp.getName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
                    webApp.getContextParams().remove(cp);
                    break;
                }
            }
        }
        // otherwise do nothing
    }

    private void removeJSFServlet(final org.eclipse.jst.j2ee.webapplication.WebApp webApp, final org.eclipse.jst.j2ee.webapplication.Servlet servlet) {
        webApp.getServlets().remove(servlet);
    }

    @Override
    public IPath getFileUrlPath(Object webAppObj, IResource resource,
            IPath existingURL)
    {
        if (webAppObj instanceof WebApp)
        {
            final WebApp webApp = (WebApp)webAppObj;
            final Servlet servlet = findJSFServlet(webApp);
            // if no faces servlet, do nothing
            if (servlet == null)
            {
                return null;
            }

            //if not a JSF page, do nothing
            if (!isJSFPage(resource))
            {
                return null;
            }

            final String defaultSuffix = getDefaultSuffix(webApp);
            //is the resource using default_suffix
            final String fileExtension = resource.getFileExtension();
            final boolean canUseExtensionMapping = 
                fileExtension != null && fileExtension.equalsIgnoreCase(defaultSuffix);

            //if not using default extension and is not a known file extension, then we will abort
            if (! canUseExtensionMapping && ! isValidKnownExtension(fileExtension))
            {
                return null;
            }

            Iterator mappings = servlet.getMappings().iterator();
            ServletMapping map = null;
            String foundFileExtension = null;
            String foundPrefixMapping = null;
            while (mappings.hasNext())
            {
                map = (ServletMapping)mappings.next();

                foundFileExtension = getFileExtensionFromMap(map);
                if (foundFileExtension != null && canUseExtensionMapping) 
                {
                    return existingURL.removeFileExtension().addFileExtension(foundFileExtension);
                }
                
                if (foundPrefixMapping == null)
                {
                    foundPrefixMapping = getPrefixMapping(map);
                }
            }
            if (foundPrefixMapping != null)
            {
                return new Path(foundPrefixMapping).append(existingURL); 
            }
            if (! canUseExtensionMapping && foundFileExtension != null){
                //we could prompt user that this may not work...
                //for now we will return the extension mapping
                return existingURL.removeFileExtension().addFileExtension(foundFileExtension);
            }
            
            //we could, at this point, add a url mapping to the faces servlet, or prompt user that it may be a good idea to add one... ;-
        }
        return null;
    }
}
