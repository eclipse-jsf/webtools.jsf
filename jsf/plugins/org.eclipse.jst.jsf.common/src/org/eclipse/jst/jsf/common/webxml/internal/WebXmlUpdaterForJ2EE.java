/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.common.webxml.internal;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtils;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ContextParamAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterMapperAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterRemoverForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ListenerAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletMappingAdderForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletRemoverForJavaEE;


/**
 * @author Debajit Adhikary
 *
 */
public class WebXmlUpdaterForJ2EE extends AbstractWebXmlUpdater
{
    private final WebApp webApp;


    /**
     * @param webAppObj
     * @param project
     * @param provider
     * @param monitor
     */
    public WebXmlUpdaterForJ2EE (final Object webAppObj,
                                 final IProject project,
                                 final IModelProvider provider,
                                 final IProgressMonitor monitor)
    {
        super(webAppObj, project, provider, monitor);
        this.webApp = (WebApp) webAppObj;
    }


    @Override
    public void addServlet (final String servletName,
                            final String servletClass,
                            final String loadOnStartup)
    {
        provider.modify(new ServletAdderForJ2EE(project, servletName, servletClass, loadOnStartup), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addServletMapping (final String servletName,
                                   final String servletClass,
                                   final String urlPattern)
    {
        provider.modify(new ServletMappingAdderForJ2EE(project, servletName, servletClass, urlPattern), WebXmlUtils.WEB_XML_PATH);
    }

    // TODO: needs fixing
    @Override
    public void removeServlet (final String servletClassName)
    {
        final Servlet servlet = WebXmlUtilsForJ2EE.findServlet(webApp, servletClassName);
        if (servlet == null)
            throw new IllegalArgumentException("Cannot find servlet named \"" + servletClassName + "\"");  //$NON-NLS-1$//$NON-NLS-2$

        provider.modify(new ServletRemoverForJavaEE(project, servletClassName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addFilter (final String filterName,
                           final String filterClass)
    {
        provider.modify(new FilterAdderForJ2EE(project, filterName, filterClass), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void removeFilter (final String filterClassName)
    {
        final Filter filter = WebXmlUtilsForJ2EE.findFilter(webApp, filterClassName);
        if (filter == null)
            throw new IllegalArgumentException("Cannot find filter named \"" + filterClassName + "\"");  //$NON-NLS-1$//$NON-NLS-2$

        provider.modify(new FilterRemoverForJavaEE(project, filterClassName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addFilterMapping (final String filterName,
                                  final String filterClass,
                                  final String servletName)
    {
        provider.modify(new FilterMapperAdderForJ2EE(project, filterName, filterClass, servletName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public String getContextParamValue (final String paramName)
    {
        return WebXmlUtilsForJ2EE.getContextParamValue(webApp, paramName);
    }


    @Override
    public List<String> getContextParamValuesAsList(String paramName, String valuesDelimiterRegex)
    {
        return WebXmlUtilsForJ2EE.getContextParamValuesAsList(webApp, paramName, valuesDelimiterRegex);
    }


    @Override
    public void setContextParamValue(String paramName, String paramValue)
    {
        WebXmlUtilsForJ2EE.setContextParamValue(webApp, paramName, paramValue);
    }


    @Override
    
    public void addContextParam (final String paramName,
                                 final String paramValue,
                                 final String description)
    {
        provider.modify(new ContextParamAdderForJ2EE(project, paramName, paramValue, description), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addListener (final String listenerClass)
    {
        provider.modify(new ListenerAdderForJ2EE(project, listenerClass), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public Object getWebApp()
    {
        return webApp;
    }
}

