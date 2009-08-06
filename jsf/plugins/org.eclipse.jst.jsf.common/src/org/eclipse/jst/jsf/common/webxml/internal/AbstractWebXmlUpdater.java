/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.common.webxml.internal;


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
    protected final Object webAppObj;
    protected final IProject project;
    protected final IModelProvider provider;
    protected final IProgressMonitor monitor;


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


    public abstract void addServlet (final String servletName,
                                     final String servletClass,
                                     final String loadOnStartup);


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


    public abstract void addFilter (final String filterName,
                                    final String filterClass);


    /**
     * Removes a filter and its associated mappings from web.xml.
     *
     * @param filterName
     */
    public abstract void removeFilter (final String filterName);


    public abstract void addFilterMapping (final String filterName,
                                           final String filterClass,
                                           final String servletName);


    public abstract void addContextParam (final String paramName,
                                          final String paramValue,
                                          final String description);


    public abstract void addListener (final String listenerClass);
}
