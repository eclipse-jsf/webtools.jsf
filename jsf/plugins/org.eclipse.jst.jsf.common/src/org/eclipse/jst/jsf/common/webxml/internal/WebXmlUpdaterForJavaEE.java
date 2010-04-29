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

import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE.findFilter;
import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE.findServlet;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtils;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ContextParamAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterMapperAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.FilterRemoverForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ListenerAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletMappingAdderForJavaEE;
import org.eclipse.jst.jsf.common.webxml.internal.operations.ServletRemoverForJavaEE;


/**
 * @author Debajit Adhikary
 *
 */
public class WebXmlUpdaterForJavaEE extends AbstractWebXmlUpdater
{
    private final WebApp webApp;


    /**
     * @param webAppObj
     * @param project
     * @param provider
     * @param monitor
     */
    public WebXmlUpdaterForJavaEE (final Object webAppObj,
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
        provider.modify(new ServletAdderForJavaEE(project, servletName, servletClass, loadOnStartup), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void removeServlet (final String servletClassName)
    {
        final Servlet servlet = findServlet(servletClassName, webApp);
        if (servlet == null)
            throw new IllegalArgumentException("Cannot find servlet named \"" + servletClassName + "\"");  //$NON-NLS-1$//$NON-NLS-2$

        provider.modify(new ServletRemoverForJavaEE(project, servletClassName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addServletMapping (final String servletName,
                                   final String servletClass,
                                   final String urlPattern)
    {
        provider.modify(new ServletMappingAdderForJavaEE(project, servletName, servletClass, urlPattern), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addFilter (final String filterName,
                           final String filterClass)
    {
        provider.modify(new FilterAdderForJavaEE(project, filterName, filterClass), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void removeFilter (final String filterClassName)
    {
        final Filter filter = findFilter(webApp, filterClassName);
        if (filter == null)
            throw new IllegalArgumentException("Cannot find filter named \"" + filterClassName + "\"");  //$NON-NLS-1$//$NON-NLS-2$

        provider.modify(new FilterRemoverForJavaEE(project, filterClassName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addFilterMapping (final String filterName,
                                  final String filterClass,
                                  final String servletName)
    {
        provider.modify(new FilterMapperAdderForJavaEE(project, filterName, servletName), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public String getContextParamValue (final String paramName)
    {
        return WebXmlUtilsForJavaEE.getContextParamValue(webApp, paramName);
    }


    @Override
    public List<String> getContextParamValuesAsList (final String paramName,
                                                    final String valuesDelimiterRegex)
    {
        return WebXmlUtilsForJavaEE.getContextParamValuesAsList(webApp, paramName, valuesDelimiterRegex);
    }

    
    @Override
    public void setContextParamValue(String paramName, String paramValue)
    {
        WebXmlUtilsForJavaEE.setContextParamValue(webApp, paramName, paramValue);
    }


    @Override
    public void addContextParam (final String paramName,
                                 final String paramValue,
                                 final String description)
    {
        provider.modify(new ContextParamAdderForJavaEE(project, paramName, paramValue, description), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public void addListener (final String listenerClass)
    {
        provider.modify(new ListenerAdderForJavaEE(project, listenerClass), WebXmlUtils.WEB_XML_PATH);
    }


    @Override
    public Object getWebApp()
    {
        return webApp;
    }
}
