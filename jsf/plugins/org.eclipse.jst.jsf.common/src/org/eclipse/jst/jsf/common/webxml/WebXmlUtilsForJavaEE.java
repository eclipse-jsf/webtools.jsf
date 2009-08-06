/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.common.webxml;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;


/**
 * Web.xml editing utilities for Java EE.
 *
 * @author Debajit Adhikary
 *
 */
public class WebXmlUtilsForJavaEE
{
    public static void addServlet (final WebApp webapp,
                                   final String servletName,
                                   final String servletClass,
                                   final String loadOnStartup)
    {
        if (existsServlet(webapp, servletName, servletClass))
            return;

        // Create new servlet
        final Servlet servlet = WebFactory.eINSTANCE.createServlet();
        servlet.setServletName(servletName);
        servlet.setServletClass(servletClass);
        servlet.setLoadOnStartup(loadOnStartup);

        webapp.getServlets().add(servlet);
    }


    public static boolean existsServlet (final WebApp webapp,
                                         final String servletName,
                                         final String servletClass)
    {
        return findServlet(webapp, servletName, servletClass) != null;
    }


    public static Servlet findServlet (final WebApp webapp,
                                       final String servletName,
                                       final String servletClass)
    {
        for (final Object s : webapp.getServlets())
        {
            final Servlet servlet = (Servlet) s;
            if (servlet.getServletName().equals(servletName) && servlet.getServletClass().equals(servletClass))
            {
                return servlet;
            }
        }

        return null;
    }


    public static Servlet findServlet (final String servletName,
                                       final WebApp webApp)
    {
        for (final Object servlet : webApp.getServlets())
        {
            if (((Servlet) servlet).getServletClass().trim().equals(servletName))
                return (Servlet) servlet;
        }

        return null;
    }


    public static void removeServlet (final WebApp webApp,
                                      final Servlet servlet)
    {
        webApp.getServlets().remove(servlet);
    }


    public static void addServletMapping (final WebApp webApp,
                                          final String servletName,
                                          final String servletClass,
                                          final String urlPatternString)
    {
        if (existsServletMapping(webApp, servletName, urlPatternString))
            return;

        // Create new servlet mapping.
        final ServletMapping servletMapping = WebFactory.eINSTANCE.createServletMapping();
        servletMapping.setServletName(servletName);
        servletMapping.getUrlPatterns().add(createUrlPattern(urlPatternString));

        webApp.getServletMappings().add(servletMapping);
    }


    public static boolean existsServletMapping (final WebApp webApp,
                                                final String servletName,
                                                final String urlPatternString)
    {
        return findServletMapping(webApp, servletName, urlPatternString) != null;
    }


    public static ServletMapping findServletMapping (final WebApp webApp,
                                                     final String servletName,
                                                     final String urlPatternString)
    {
        for (final Object mapping : webApp.getServletMappings())
        {
            final ServletMapping servletMappingToCheck = (ServletMapping) mapping;
            if (servletMappingToCheck.getServletName().trim().equals(servletName))
            {
                // We found a servlet with the same name. Check for urls
                for (final Object pattern : servletMappingToCheck.getUrlPatterns())
                    if (((UrlPatternType) pattern).getValue().equals(urlPatternString))
                        return servletMappingToCheck;
            }
        }

        return null;
    }


    public static void removeServletMappings (final WebApp webApp,
                                              final Servlet servlet)
    {
        final List mappings = webApp.getServletMappings();
        String servletName = servlet.getServletName();

        if (servletName != null)
        {
            servletName = servletName.trim();
            for (int i = mappings.size() - 1; i >= 0; --i)
            {
                final ServletMapping mapping = (ServletMapping) mappings.get(i);
                if (mapping != null && mapping.getServletName() != null && mapping.getServletName().trim().equals(servletName))
                {
                    mappings.remove(mapping);
                }
            }
        }
    }


    public static void addFilter (final WebApp webapp,
                                  final String filterName,
                                  final String filterClass)
    {
        if (existsFilter(webapp, filterName, filterClass))
            return;

        webapp.getFilters().add(createFilter(filterName, filterClass));
    }


    public static Filter createFilter (final String filterName,
                                       final String filterClass)
    {
        final Filter filter = WebFactory.eINSTANCE.createFilter();
        filter.setFilterName(filterName);
        filter.setFilterClass(filterClass);
        return filter;
    }


    public static boolean existsFilter (final WebApp webapp,
                                        final String filterName,
                                        final String filterClass)
    {
        return findFilter(webapp, filterName, filterClass) != null;
    }


    public static Filter findFilter (final WebApp webapp,
                                     final String filterName,
                                     final String filterClass)
    {
        for (final Object f : webapp.getFilters())
        {
            final Filter filter = (Filter) f;
            if (filter.getFilterName().trim().equals(filterName) && filter.getFilterClass().trim().equals(filterClass))
            {
                return filter;
            }
        }

        return null;
    }


    public static Filter findFilter (final WebApp webApp,
                                     final String filterClassName)
    {
        for (final Object filter : webApp.getFilters())
        {
            if (((Filter) filter).getFilterClass().trim().equals(filterClassName))
                return (Filter) filter;
        }

        return null;
    }


    public static void removeFilter (final WebApp webApp,
                                     final Filter filter)
    {
        webApp.getFilters().remove(filter);
    }


    public static void addFilterMapping (final WebApp webapp,
                                         final String filterName,
                                         final String servletName)
    {
        if (existsFilterMapping(webapp, filterName, servletName))
            return;

        webapp.getFilterMappings().add(createFilterMapping(filterName, servletName));
    }


    public static FilterMapping createFilterMapping (final String filterName,
                                                     final String servletName)
    {
        final FilterMapping filterMapping = WebFactory.eINSTANCE.createFilterMapping();
        filterMapping.setFilterName(filterName);
        filterMapping.getServletNames().add(servletName);

        return filterMapping;
    }


    public static boolean existsFilterMapping (final WebApp webapp,
                                               final String filterName,
                                               final String servletName)
    {
        return findFilterMapping(webapp, filterName, servletName) != null;
    }


    public static FilterMapping findFilterMapping (final WebApp webapp,
                                                   final String filterName,
                                                   final String servletName)
    {
        for (final Object fm : webapp.getFilterMappings())
        {
            final FilterMapping filterMapping = (FilterMapping) fm;

            if (filterMapping.getFilterName().trim().equals(filterName) && filterMapping.getServletNames().contains(servletName))
            {
                return filterMapping;
            }
        }

        return null;
    }


    public static void removeFilterMappings (final WebApp webApp,
                                             final Filter filter)
    {
        final List mappings = webApp.getFilterMappings();
        String filterName = filter.getFilterName();

        if (filterName != null)
        {
            filterName = filterName.trim();
            for (int i = mappings.size() - 1; i >= 0; --i)
            {
                final FilterMapping mapping = (FilterMapping) mappings.get(i);
                if (mapping != null && mapping.getFilterName() != null && mapping.getFilterName().trim().equals(filterName))
                {
                    mappings.remove(mapping);
                }
            }
        }
    }


    public static void addContextParam (final WebApp webApp,
                                        final String paramName,
                                        final String paramValue,
                                        final String description)
    {
        if (existsContextParam(webApp, paramName, paramValue))
            return;

        webApp.getContextParams().add(createContextParam(paramName, paramValue, description));
    }


    public static ParamValue createContextParam (final String paramName,
                                                 final String paramValue,
                                                 final String descriptionString)
    {
        final ParamValue param = JavaeeFactory.eINSTANCE.createParamValue();
        param.setParamName(paramName);
        param.setParamValue(paramValue);

        if (descriptionString != null)
        {
            final Description description = JavaeeFactory.eINSTANCE.createDescription();
            description.setValue(descriptionString);
            param.getDescriptions().add(description);
        }


        return param;
    }


    public static boolean existsContextParam (final WebApp webApp,
                                              final String paramName,
                                              final String paramValue)
    {
        return findContextParam(webApp, paramName, paramValue) != null;
    }


    public static ParamValue findContextParam (final WebApp webApp,
                                               final String paramName,
                                               final String paramValue)
    {
        for (final Object param : webApp.getContextParams())
        {
            final ParamValue contextParam = (ParamValue) param;
            if (contextParam.getParamName().equals(paramName) && contextParam.getParamValue().equals(paramValue))
                return contextParam;
        }

        return null;
    }


    public static void addListener (final WebApp webapp,
            final String listenerClass)
    {
        if (existsListener(webapp, listenerClass))
            return;

        // Create new listener
        final Listener listener = JavaeeFactory.eINSTANCE.createListener();
        listener.setListenerClass(listenerClass);

        webapp.getListeners().add(listener);
    }


    public static boolean existsListener (final WebApp webapp,
                                          final String listenerClass)
    {
        return findListener(webapp, listenerClass) != null;
    }


    public static Listener findListener (final WebApp webapp,
                                         final String listenerClass)
    {
        for (final Object listener : webapp.getListeners())
            if (((Listener) listener).getListenerClass().equals(listenerClass))
                return (Listener) listener;

        return null;
    }


    public static UrlPatternType createUrlPattern (final String urlPatternString)
    {
        final UrlPatternType urlPattern = JavaeeFactory.eINSTANCE.createUrlPatternType();
        urlPattern.setValue(urlPatternString);
        return urlPattern;
    }
}
