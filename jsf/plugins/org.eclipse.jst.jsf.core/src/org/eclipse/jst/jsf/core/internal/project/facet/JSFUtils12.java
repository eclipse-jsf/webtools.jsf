/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Utility file for JSF v1.2 model
 * 
 * @author Gerry Kessler - Oracle
 */
/*package: use JSFUtilFactory*/ class JSFUtils12 extends JSFUtils {

	/**
	 * @param modelProvider 
	 * 
	 */
	protected JSFUtils12(final IModelProvider modelProvider)
    {
        this(JSFVersion.V1_2, modelProvider);
    }

    /**
     * @param jsfVersion
     * @param modelProvider 
     */
    protected JSFUtils12(final JSFVersion jsfVersion, final IModelProvider modelProvider)
    {
        super(jsfVersion, modelProvider);
        if (jsfVersion.compareTo(JSFVersion.V1_2) < 0)
        {
            throw new IllegalArgumentException(
                    "JsfVersion must be at least 1.2"); //$NON-NLS-1$
        }
    }

    /**
     * @param webApp
     * @return Servlet - the JSF Servlet for the specified WebApp or null if not
     *         present
     */
    private Servlet findJSFServlet(final WebApp webApp)
    {
        if (webApp == null)
        {
            return null;
        }

        for (final Servlet servlet : webApp.getServlets())
        {
            if (servlet != null &&
                    servlet.getServletClass() != null
                    && servlet.getServletClass().trim().equals(
                            JSF_SERVLET_CLASS))
            {
                return servlet;
            }
        }

        // if we get to here then we have finished the loop
        // without finding the servlet we're looking for
        return null;
    }

	/**
	 * Creates a stubbed JSF v1.2 configuration file for specified JSF version and path
	 */
	@Override
    public void doVersionSpecificConfigFile(final PrintWriter pw)
    {
        final String QUOTE = new String(new char[]
        { '"' });
        pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + QUOTE + "UTF-8" + QUOTE + "?>\n\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("<faces-config\n"); //$NON-NLS-1$
        pw.write("    " + "xmlns=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://java.sun.com/xml/ns/javaee" + QUOTE + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("    " + "xmlns:xsi=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE //$NON-NLS-1$
                + "\n"); //$NON-NLS-1$
        pw.write("    " //$NON-NLS-1$
                + "xsi:schemaLocation=" //$NON-NLS-1$
                + QUOTE
                + "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_1_2.xsd" //$NON-NLS-1$
                + QUOTE + "\n"); //$NON-NLS-1$
        pw.write("    " + "version=" + QUOTE + "1.2" + QUOTE + ">\n\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        pw.write("</faces-config>\n"); //$NON-NLS-1$
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
	private Servlet createOrUpdateServletRef(final WebApp webApp,
			final IDataModel config, Servlet servlet) {
		
		String displayName = getDisplayName(config);		
		String className = getServletClassname(config);
		
		if (servlet == null){			
			// Create the servlet instance and set up the parameters from data
			// model
			servlet = WebFactory.eINSTANCE.createServlet();
			servlet.setServletName(displayName);
			servlet.setServletClass(className);
			servlet.setLoadOnStartup(Integer.valueOf(1));
			// Add the servlet to the web application model
			webApp.getServlets().add(servlet);

		} else {
			updateServletMappings(webApp, servlet, displayName);
			servlet.setServletName(displayName);
			servlet.setLoadOnStartup(Integer.valueOf(1));
		}
		return servlet;
	}

    private void updateServletMappings(final WebApp webApp,
            final Servlet servlet, final String displayName)
    {
        // update mappings for new name
        ServletMapping mapping = findServletMapping(webApp, servlet);
        if (mapping != null)
        {
            mapping.setServletName(displayName);
        }
    }

    /**
     * Creates servlet-mappings for the servlet for 2.5 WebModules or greated
     * 
     * @param webApp
     * @param urlMappingList
     *            - list of string values to be used in url-pattern for
     *            servlet-mapping
     * @param servlet
     */
    private void setUpURLMappings(final WebApp webApp,
            final List<String> urlMappingList, final Servlet servlet)
    {

        if (urlMappingList.size() > 0)
        {
            ServletMapping mapping = findServletMapping(webApp, servlet);
            if (mapping == null)
            {
                mapping = WebFactory.eINSTANCE
                        .createServletMapping();
                mapping.setServletName(servlet.getServletName());
                webApp.getServletMappings().add(mapping);
            }
            // Add patterns
            for (final String pattern : urlMappingList)
            {
                if (!(doesServletMappingPatternExist(webApp, servlet, pattern)))
                {
                    UrlPatternType urlPattern = JavaeeFactory.eINSTANCE
                            .createUrlPatternType();
                    urlPattern.setValue(pattern);
                    mapping.getUrlPatterns().add(urlPattern);
                }
            }
        }
    }

	private ServletMapping findServletMapping(final WebApp webApp, final Servlet servlet) {
		for (Iterator it=webApp.getServletMappings().iterator();it.hasNext();){
			ServletMapping mapping = (ServletMapping)it.next();
			if (mapping.getServletName() != null && 
					servlet.getServletName() != null &&
					mapping.getServletName().trim().equals(servlet.getServletName().trim()))
				return mapping;
        }
		return null;
    }

    private boolean doesServletMappingPatternExist(final WebApp webApp,
            final Servlet servlet, final String pattern)
    {
        List<ServletMapping> mappings = webApp.getServletMappings();
        String servletName = servlet.getServletName();
        if (servletName != null)
        {
            servletName = servletName.trim();
            for (final ServletMapping mapping : mappings)
            {
                if (mapping != null && 
                        mapping.getServletName() != null && 
                        servletName.equals(mapping.getServletName().trim()))
                {
                    for (final UrlPatternType urlPattern : mapping.getUrlPatterns())
                    {
                        String patternTypeValue = urlPattern.getValue();
                        if (patternTypeValue != null
                                && pattern.equals(patternTypeValue.trim()))
                            return true;
                    }
                }
            }
        }
        return false;
    }
	
	/**
	 * Removes servlet-mappings for servlet using servlet-name for >= 2.5 WebModules.
	 * @param webApp
	 * @param servlet
	 */
	private void removeURLMappings(final  WebApp webApp, final Servlet servlet) {
		List mappings = webApp.getServletMappings();
		String servletName = servlet.getServletName();
		if (servletName != null) {
			servletName = servletName.trim();
			for (int i=mappings.size()-1;i>=0;--i){
				ServletMapping mapping = (ServletMapping)mappings.get(i);
				if (mapping != null && 
						mapping.getServletName() != null && 
						mapping.getServletName().trim()
						.equals(servletName)) {
					mappings.remove(mapping);
				}
			}
		}
	}
	
	/**
	 * Creates or updates config file context-param in v2.5 WebApp if non default configuration file is specified.
	 * @param webApp
	 * @param config
	 */
	private void setupConfigFileContextParamForV2_5(final org.eclipse.jst.javaee.web.WebApp webApp,
			final IDataModel config) {
		// if not default name and location, then add context param
		ParamValue foundCP = null;
		ParamValue cp = null;
		boolean found = false;
		if (!config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH).equals(JSF_DEFAULT_CONFIG_PATH)) {
			// check to see if present
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				cp = (org.eclipse.jst.javaee.core.ParamValue) it.next();
				if (cp != null && 
						cp.getParamName()!= null &&
						cp.getParamName().trim().equals(JSF_CONFIG_CONTEXT_PARAM)) {
					foundCP = cp;
					found = true;
				}
			}
			if (!found) {
				ParamValue pv = JavaeeFactory.eINSTANCE.createParamValue();
				pv.setParamName(JSF_CONFIG_CONTEXT_PARAM);
				pv.setParamValue(config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
				webApp.getContextParams().add(pv);
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
     * @param webApp
     * @return the default file extension from the context param. Default is
     *         "jsp" if no context param
     */
    private String getDefaultSuffix(final WebApp webApp)
    {
        String defaultSuffix = getDefaultDefaultSuffix();
        for (Iterator it = webApp.getContextParams().iterator(); it.hasNext();)
        {
            ParamValue cp = (ParamValue) it.next();
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
        return defaultSuffix;
    }

    
	/**
	 * @param map
	 * @return prefix mapping 
	 */
	private String getPrefixMapping(final ServletMapping map) {
		List urls = map.getUrlPatterns();
		for (Iterator it=urls.iterator();it.hasNext();){
			IPath extPath = new Path(((UrlPatternType)it.next()).getValue());
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
		}
		return null;
	}
	
	/**
	 * @param map
	 * @return extension from map.  Will return null if file extension not found in url patterns.
	 */
	private String getFileExtensionFromMap(final ServletMapping map) {
		List urls = map.getUrlPatterns();
		for (Iterator it=urls.iterator();it.hasNext();){
			IPath extPath = new Path(((UrlPatternType)it.next()).getValue());
			if (extPath != null){
				String ext = extPath.getFileExtension();
				if (ext != null && !ext.equals("")) //$NON-NLS-1$
					return ext;
			}
		}
		return null;
	}

    @Override
    public void updateWebApp(Object webApp, IDataModel config)
    {
        // create or update servlet ref
        Servlet servlet = findJSFServlet((WebApp)webApp);// check to see
                                                            // if already
        
        servlet = createOrUpdateServletRef((WebApp) webApp, config, servlet);

        // init mappings
        final List listOfMappings = getServletMappings(config);
        setUpURLMappings((WebApp)webApp, listOfMappings, servlet);

        // setup context params
        setupConfigFileContextParamForV2_5((WebApp)webApp, config);

    }

    
    @Override
    public void rollbackWebApp(Object webApp)
    {
        Servlet servlet = findJSFServlet((WebApp) webApp);
        if (servlet == null)
        {
            return;
        }
        // remove faces url mappings
        removeURLMappings((WebApp)webApp, servlet);
        // remove context params
        removeJSFContextParams((WebApp)webApp, servlet);
        // remove servlet
        removeJSFServlet((WebApp)webApp, servlet);
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

    @Override
    public IPath getFileUrlPath(Object webAppObj, IResource resource,
            IPath existingURL)
    {
        if (webAppObj instanceof WebApp)
        {
            WebApp webApp =  (WebApp) webAppObj;
            Servlet servlet = findJSFServlet(webApp);
            if (servlet == null)
            {// if no faces servlet, do nothing
                return null;
            }

            final String servletName = servlet.getServletName();

            // if not a JSF page, do nothing
            if (!isJSFPage(resource))
            {
                return null;
            }

            String defaultSuffix = getDefaultSuffix(webApp);
            // is the resource using default_suffix
            String fileExtension = resource.getFileExtension();
            boolean canUseExtensionMapping = fileExtension != null && fileExtension.equalsIgnoreCase(defaultSuffix);

            // if not using default extension and is not a known file extension,
            // then we will abort
            if (!canUseExtensionMapping
                    && !isValidKnownExtension(resource.getFileExtension()))
                return null;

            String foundFileExtension = null;
            for (final ServletMapping map : webApp.getServletMappings())
            {
                if (map != null &&
                        map.getServletName() != null &&
                        map.getServletName().trim().equals(servletName.trim()))
                {
                    foundFileExtension = getFileExtensionFromMap(map);
                    if (foundFileExtension != null && canUseExtensionMapping)
                    {
                        return existingURL.removeFileExtension()
                                .addFileExtension(foundFileExtension);
                    }

                    String foundPrefixMapping = getPrefixMapping(map);
                    if (foundPrefixMapping != null)
                    {
                        return new Path(foundPrefixMapping).append(existingURL);
                    }
                }
            }

            if (!canUseExtensionMapping && foundFileExtension != null)
            {
                // we could prompt user that this may not work...
                // for now we will return the extension mapping
                return existingURL.removeFileExtension().addFileExtension(
                        foundFileExtension);
            }

            // we could, at this point, add a url mapping to the faces servlet,
            // or prompt user that it may be a good idea to add one... ;-
        }
        return null;
    }
    
    
}
