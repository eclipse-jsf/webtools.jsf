/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;

/**
 * Web.xml access for JEE applications (web version 2.5 and above)
 */
public class JEEUtils {
	/**
	 * Finds and returns a Servlet definition, or null if servlet is not defined.
	 * 
	 * @param webApp
	 * @param servletName
	 * @return Servlet or null
	 */
    public static Servlet findServlet(final WebApp webApp, String servletName)
    {
        if (webApp == null)
        {
            return null;
        }

        for (final Servlet servlet : webApp.getServlets())
        {
            if (servlet != null &&
                    servlet.getServletClass() != null
                    && servlet.getServletClass().trim().equals(servletName))
            {
                return servlet;
            }
        }

        // if we get to here then we have finished the loop
        // without finding the servlet we're looking for
        return null;
    }
    
	/**
	 * Creates servlet reference in WebApp if not present or updates servlet name if found
	 * using the passed configuration.
	 * 
	 * @param webApp
	 * @param displayName
	 * @param className
	 * @param servlet
	 * @return Servlet servlet - if passed servlet was null, will return created servlet
	 */
	public static Servlet createOrUpdateServletRef(final WebApp webApp,
			String displayName, String className, Servlet servlet) {
		
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

    /**
     * Updates servlet mapping
     * 
     * @param webApp
     * @param servlet
     * @param displayName
     */
    public static void updateServletMappings(final WebApp webApp,
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
     * Finds mapping for given servlet
     * 
     * @param webApp
     * @param servlet
     * @return List of mappings
     */
    public static ServletMapping findServletMapping(final WebApp webApp, final Servlet servlet) {
		for (Iterator it=webApp.getServletMappings().iterator();it.hasNext();){
			ServletMapping mapping = (ServletMapping)it.next();
			if (mapping.getServletName() != null && 
					servlet.getServletName() != null &&
					mapping.getServletName().trim().equals(servlet.getServletName().trim()))
				return mapping;
        }
		return null;
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
    public static void setUpURLMappings(final WebApp webApp,
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

	/**
	 * Checks whether given mapping exists for the given servlet
	 * @param webApp
	 * @param servlet
	 * @param pattern
	 * @return true or false
	 */
    public static boolean doesServletMappingPatternExist(final WebApp webApp,
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
	public static void removeURLMappings(final  WebApp webApp, final Servlet servlet) {
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
	 * Removes servlet definition
	 * @param webApp
	 * @param servlet
	 */
    public static void removeServlet(final WebApp webApp, final Servlet servlet) {     
        webApp.getServlets().remove(servlet);
    }
    
    /**
     * Removes context-param
     * @param webApp
     * @param paramName
     */
    public static void removeContextParam(final WebApp webApp, String paramName) {
        Iterator it = webApp.getContextParams().iterator();
        while (it.hasNext()) {
            ParamValue cp = (ParamValue) it.next();
            if (cp.getParamName().equals(paramName)) {
                webApp.getContextParams().remove(cp);
                break;
            }
        }
    }
    
	/**
	 * Creates or updates context-param
	 * @param webApp
	 * @param paramName
	 * @param paramValue
	 */
	public static void setupContextParam(final WebApp webApp, String paramName, String paramValue) {
		// if not default name and location, then add context param
		ParamValue foundCP = null;
		ParamValue cp = null;
		boolean found = false;
		// check to see if present
		Iterator it = webApp.getContextParams().iterator();
		while (it.hasNext()) {
			cp = (org.eclipse.jst.javaee.core.ParamValue) it.next();
			if (cp != null && 
					cp.getParamName()!= null &&
					cp.getParamName().trim().equals(paramName)) {
				foundCP = cp;
				found = true;
			}
		}
		if (!found) {
			ParamValue pv = JavaeeFactory.eINSTANCE.createParamValue();
			pv.setParamName(paramName);
			pv.setParamValue(paramValue);
			webApp.getContextParams().add(pv);
		} else {
			cp = foundCP;
			if (cp.getParamValue().indexOf(paramValue) < 0) {
				String curVal = cp.getParamValue();
				String val = paramValue;
				if (curVal != null && !"".equals(curVal.trim())) { //$NON-NLS-1$
					val = curVal + ",\n" + val; //$NON-NLS-1$
				}
				cp.setParamValue(val);
			}
		}
	}

    /**
     * @param webApp
     * @param paramName
     * @return context-param value or null if context-param is not found
     */
    public static String getContextParam(final WebApp webApp, String paramName)
    {
        for (Iterator it = webApp.getContextParams().iterator(); it.hasNext();)
        {
            ParamValue cp = (ParamValue) it.next();
			if (cp != null && 
					cp.getParamName()!= null &&
					cp.getParamName().trim().equals(paramName)) {
				return cp.getParamValue();
            }
        }
        return null;
    }
    
	/**
	 * @param map
	 * @return extension from map.  Will return null if file extension not found in url patterns.
	 */
	public static String getFileExtensionFromMap(final ServletMapping map) {
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
	
	/**
	 * @param map
	 * @return prefix mapping 
	 */
	public static String getPrefixMapping(final ServletMapping map) {
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
}
