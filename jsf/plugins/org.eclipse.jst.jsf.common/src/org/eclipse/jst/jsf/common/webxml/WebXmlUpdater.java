/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.common.webxml;


import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.webxml.internal.AbstractWebXmlUpdater;
import org.eclipse.jst.jsf.common.webxml.internal.WebXmlUpdaterForJ2EE;
import org.eclipse.jst.jsf.common.webxml.internal.WebXmlUpdaterForJavaEE;


/**
 * Facade for web.xml updater classes WebXmlUpdaterForJavaEE,
 * WebXmlUpdaterForJ2EE, and AbstractWebXmlUpdater. Utility class to make
 * editing web.xml easy.
 *
 * @author Debajit Adhikary
 *
 */
public class WebXmlUpdater
{
    private final IProject project;
    private final IProgressMonitor monitor;
    private final AbstractWebXmlUpdater updater;
    private final IModelProvider provider;

    /**
     * @param project
     * @param monitor
     */
    public WebXmlUpdater (final IProject project,
                          final IProgressMonitor monitor)
    {
        this.project = project;
        this.monitor = monitor;
        this.provider = ModelProviderManager.getModelProvider(project);
        this.updater = initUpdater();
    }


    /**
     * @return WebApp object associated with this WebXmlUpdater object
     */
    public WebApp getWebAppForJavaEE ()
    {
        return (WebApp) updater.getWebApp();
    }


    /**
     * @return WebApp object associated with this WebXmlUpdater object
     */
    public org.eclipse.jst.j2ee.webapplication.WebApp getWebAppForJ2EE ()
    {
        return (org.eclipse.jst.j2ee.webapplication.WebApp) updater.getWebApp();
    }


    /**
     * @return Provider object for the webapp this WebXmlUpdater is associated
     *         with. This may be used by clients to perform additional complex 
     *         updates to web.xml using getProvider.modify(new Runnable(), ...) 
     */
    public IModelProvider getProvider()
    {
        return provider;
    }


    /**
     * @return True if this webapp is a Java EE app.
     */
    public boolean isJavaEEWebapp ()
    {
        return updater instanceof WebXmlUpdaterForJavaEE;
    }


    /**
     * @return True if this webapp is a J2EE app
     */
    public boolean isJ2EEWebapp ()
    {
        return updater instanceof WebXmlUpdaterForJ2EE;
    }


    /**
     * @param servletName
     * @param servletClass
     * @param loadOnStartup
     */
    public void addServlet (final String servletName,
                            final String servletClass,
                            final String loadOnStartup)
    {
        updater.addServlet(servletName, servletClass, loadOnStartup);
    }


    /**
     * @param servletClassName
     */
    public void removeServlet (final String servletClassName)
    {
        updater.removeServlet(servletClassName);
    }


    /**
     * @param servletName
     * @param servletClass
     * @param urlPattern
     */
    public void addServletMapping (final String servletName,
                                   final String servletClass,
                                   final String urlPattern)
    {
        updater.addServletMapping(servletName, servletClass, urlPattern);
    }


    /**
     * @param filterName
     * @param filterClass
     */
    public void addFilter (final String filterName,
                           final String filterClass)
    {
        updater.addFilter(filterName, filterClass);
    }


    /**
     * @param filterName
     */
    public void removeFilter (final String filterName)
    {
        updater.removeFilter(filterName);
    }


    /**
     * @param filterName
     * @param filterClass
     * @param servletName
     */
    public void addFilterMapping (final String filterName,
                                  final String filterClass,
                                  final String servletName)
    {
        updater.addFilterMapping(filterName, filterClass, servletName);
    }


    /**
     * @param paramName
     *            Name of context param
     * @return Value of given context param.
     */
    public String getContextParamValue (final String paramName)
    {
        return updater.getContextParamValue(paramName);
    }


    /**
     * @param paramName
     *            Name of context param
     * @param valuesDelimiterRegex
     *            Delimiter string for values
     * @return Values of given context param as a list. This is useful when the
     *         multiple values are demarcated by a delimiter string.
     * 
     */
    public List<String> getContextParamValuesAsList (final String paramName,
                                                     final String valuesDelimiterRegex)
    {
        return updater.getContextParamValuesAsList(paramName, valuesDelimiterRegex);
    }


    /**
     * Sets the value of the given context param name to the given value. If
     * paramName is not found, a new context-param is created with the given
     * paramName and paramValue.
     * 
     * @param paramName
     * @param paramValue
     */
    public void setContextParamValue (final String paramName,
                                      final String paramValue)
    {
        updater.setContextParamValue(paramName, paramValue);
    }


    /**
     * @param paramName
     * @param paramValue
     * @param description
     */
    public void addContextParam (final String paramName,
                                 final String paramValue,
                                 final String description)
    {
        updater.addContextParam(paramName, paramValue, description);
    }


    /**
     * @param listenerClass
     */
    public void addListener (final String listenerClass)
    {
        updater.addListener(listenerClass);
    }


    private AbstractWebXmlUpdater initUpdater()
    {
        final Object webAppObj = ModelProviderManager.getModelProvider(project).getModelObject();

        if (webAppObj != null)
        {
            if (webAppObj instanceof WebApp) // Java EE
                return new WebXmlUpdaterForJavaEE(webAppObj, project, getProvider(), monitor);
            else if (webAppObj instanceof org.eclipse.jst.j2ee.webapplication.WebApp) // J2EE
                return new WebXmlUpdaterForJ2EE(webAppObj, project, getProvider(), monitor);
        }

        // Control should never come here
        throw new IllegalArgumentException("Unable to resolve WebApp object for updating web.xml"); //$NON-NLS-1$
    }
}
