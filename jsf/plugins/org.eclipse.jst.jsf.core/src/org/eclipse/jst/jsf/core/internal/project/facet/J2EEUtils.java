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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;

/**
 * Web.xml access for J2EE applications (web version 2.3 and 2.4)
 */
public class J2EEUtils {

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
                                    .getClassName().trim().equals(servletName))
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
     * Creates servlet reference in WebApp if not present or updates servlet
     * name if found using the passed configuration.
     * 
     * @param webApp
     * @param displayName
     * @param className
     * @param servlet
     * @return Servlet servlet - if passed servlet was null, will return created
     *         servlet
     */
    public static Servlet createOrUpdateServletRef(final WebApp webApp,
            String displayName, String className, Servlet servlet)
    {
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
        List<ServletMapping> mappings = findServletMappings(webApp, servlet);
        for (ServletMapping map : mappings)
        {
            map.setName(displayName);
        }

    }
    
    /**
     * Finds mapping for given servlet
     * 
     * @param webApp
     * @param servlet
     * @return List of mappings
     */
	public static List<ServletMapping> findServletMappings(final WebApp webApp, final Servlet servlet) {
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

	/**
	 * Checks whether given mapping exists for the given servlet
	 * @param webApp
	 * @param servlet
	 * @param pattern
	 * @return true or false
	 */
	public static boolean doesServletMappingExist(final WebApp webApp, final Servlet servlet,
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
        if ("2.3".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            Iterator it = webApp.getContexts().iterator();
            while (it.hasNext()) 
            {
                final ContextParam cp = (ContextParam) it.next();
                if (cp.getParamName().equals(paramName)) 
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
                if (cp.getName().equals(paramName)) {
                    webApp.getContextParams().remove(cp);
                    break;
                }
            }
        }
    }
    
    
	/**
	 * Creates or updates context-param in v 2.3 WebApp
	 * @param webApp
	 * @param paramName
	 * @param paramValue
	 */
	public static void setupContextParamForV2_3(final WebApp webApp, String paramName, String paramValue) {
			
		// if not default name and location, then add context param
		ContextParam cp = null;
		ContextParam foundCP = null;
		boolean found = false;
		// check to see if present
		Iterator it = webApp.getContexts().iterator();
		while (it.hasNext()) {
			cp = (ContextParam) it.next();
			if (cp != null &&
					cp.getParamName() != null &&
					cp.getParamName().equals(paramName)) {
				foundCP = cp;
				found = true;
			}
		}
		if (!found) {
			cp = WebapplicationFactory.eINSTANCE.createContextParam();
			cp.setParamName(paramName);
			cp.setParamValue(paramValue);
			webApp.getContexts().add(cp);
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
	 * Creates or updates context-param in v 2.4 WebApp
	 * @param webApp
	 * @param paramName
	 * @param paramValue
	 */
	public static void setupContextParamForV2_4(final WebApp webApp, String paramName, String paramValue) {
		// if not default name and location, then add context param
		ParamValue foundCP = null;
		ParamValue cp = null;
		boolean found = false;
		// check to see if present
		Iterator it = webApp.getContextParams().iterator();
		while (it.hasNext()) {
			cp = (ParamValue) it.next();
			if (cp != null && 
					cp.getName() != null &&
					cp.getName().trim().equals(paramName)) {
				foundCP = cp;
				found = true;
			}
		}
		if (!found) {
			ParamValue pv = CommonFactory.eINSTANCE.createParamValue();
			pv.setName(paramName);
			pv.setValue(paramValue);
			webApp.getContextParams().add(pv);
		} else {
			cp = foundCP;
			if (cp.getValue().indexOf(paramValue) < 0) {
				String curVal = cp.getValue();
				String val = paramValue;
				if (curVal != null && !"".equals(curVal.trim())) { //$NON-NLS-1$
					val = curVal + ",\n" + val; //$NON-NLS-1$
				}
				cp.setValue(val);
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
        if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)//shouldn't have to do it this way, but that's the way it goes 119442
        {
            setupContextParamForV2_3(webApp, paramName, paramValue);
        }
        else if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_4_ID)
        {
            setupContextParamForV2_4(webApp, paramName, paramValue);
        }
        else
        {
            throw new IllegalArgumentException("Invalid argument: "+webApp.getVersionID()); //$NON-NLS-1$
        }
	}
	
    /**
     * @param webApp
     * @param paramName
     * @return context-param value or null if context-param is not found
     */
    public static String getContextParam(final WebApp webApp, String paramName)
    {
        if ("2.3".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            for (Iterator it = webApp.getContexts().iterator(); it.hasNext();)
            {
                ContextParam cp = (ContextParam) it.next();
    			if (cp != null &&
    					cp.getParamName() != null &&
    					cp.getParamName().trim().equals(paramName)) {
                        return cp.getParamValue();
                }
            }
        }
        else if ("2.4".equals(webApp.getVersion())) //$NON-NLS-1$
        {
            for (Iterator it = webApp.getContextParams().iterator(); it.hasNext();)
            {
                ParamValue cp = (ParamValue) it.next();
    			if (cp != null && 
    					cp.getName() != null &&
    					cp.getName().trim().equals(paramName)) {
                    return cp.getValue();
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
    public static String getFileExtensionFromMap(final ServletMapping map)
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
    
    /**
     * @param map
     * @return prefix mapping. may return null.
     */
    public static String getPrefixMapping(final ServletMapping map)
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
}
