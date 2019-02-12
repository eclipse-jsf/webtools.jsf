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


package org.eclipse.jst.jsf.common.webxml.internal;


import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;


/**
 * Abstract class to define web-xml editing interface. Users may use the 
 * WebXmlUpdater facade class for convenient web.xml editing.
 * 
 * @author Debajit Adhikary
 *
 */
public abstract class AbstractWebXmlUpdater
{
    /**
     * the web app object.  JavaEE has own type and J2EE has another.  They are not object compatible.
     */
    protected final Object webAppObj;
    /**
     * the project
     */
    protected final IProject project;
    /**
     * the model provider used to modify the model
     */
    protected final IModelProvider provider;
    /**
     * the progress monitor
     */
    protected final IProgressMonitor monitor;


    /**
     * @param webAppObj
     * @param project
     * @param provider
     * @param monitor
     */
    public AbstractWebXmlUpdater (final Object webAppObj,
                                  final IProject project,
                                  final IModelProvider provider,
                                  final IProgressMonitor monitor)
    {
        this.webAppObj = webAppObj;
        this.project = project;
        this.provider = provider;
        this.monitor = monitor;
    }


    /**
     * @param servletName
     * @param servletClass
     * @param loadOnStartup
     */
    public abstract void addServlet (final String servletName,
                                     final String servletClass,
                                     final String loadOnStartup);


    /**
     * @param servletName
     * @param servletClass
     * @param urlPattern
     */
    public abstract void addServletMapping (final String servletName,
                                            final String servletClass,
                                            final String urlPattern);


    /**
     * Removes a servlet and its associated mappings from web.xml.
     *
     * @param servletClassName
     *            Fully qualified classname of servlet class to remove.
     */
    public abstract void removeServlet (final String servletClassName);


    /**
     * @param filterName
     * @param filterClass
     */
    public abstract void addFilter (final String filterName,
                                    final String filterClass);


    /**
     * Removes a filter and its associated mappings from web.xml.
     *
     * @param filterName
     */
    public abstract void removeFilter (final String filterName);


    /**
     * @param filterName
     * @param filterClass
     * @param servletName
     */
    public abstract void addFilterMapping (final String filterName,
                                           final String filterClass,
                                           final String servletName);

    /**
     * @param paramName
     *            Name of context param
     * @return Value of given context param.
     */
    public abstract String getContextParamValue (final String paramName);


    /**
     * @param paramName
     *            Name of context param
     * @param valuesDelimiterRegex
     *            Delimiter string for values
     * @return Values of given context param as a list. This is useful when the
     *         multiple values are demarcated by a delimiter string.
     */
    public abstract List<String> getContextParamValuesAsList (final String paramName,
                                                              final String valuesDelimiterRegex);


    /**
     * Sets the value of the given context param name to the given value. If
     * paramName is not found, a new context-param is created with the given
     * paramName and paramValue.
     *
     * @param paramName
     * @param paramValue
     */
    public abstract void setContextParamValue (final String paramName,
                                               final String paramValue);


    /**
     * @param paramName
     * @param paramValue
     * @param description
     */
    public abstract void addContextParam (final String paramName,
                                          final String paramValue,
                                          final String description);


    /**
     * @param listenerClass
     */
    public abstract void addListener (final String listenerClass);


    /**
     * @return The WebApp object associated with this updater
     */
    public abstract Object getWebApp ();
}
