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


package org.eclipse.jst.jsf.common.webxml;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.internal.impl.WebAppImpl;
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


    public void addServletMapping (final String servletName,
                                   final String servletClass,
                                   final String urlPattern)
    {
        updater.addServletMapping(servletName, servletClass, urlPattern);
    }


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


    public void addFilterMapping (final String filterName,
                                  final String filterClass,
                                  final String servletName)
    {
        updater.addFilterMapping(filterName, filterClass, servletName);
    }


    public void addContextParam (final String paramName,
                                 final String paramValue,
                                 final String description)
    {
        updater.addContextParam(paramName, paramValue, description);
    }


    public void addListener (final String listenerClass)
    {
        updater.addListener(listenerClass);
    }


    private AbstractWebXmlUpdater initUpdater()
    {
        final Object webAppObj = ModelProviderManager.getModelProvider(project).getModelObject();

        if (webAppObj != null)
        {
            if (webAppObj instanceof WebAppImpl) // Java EE
                return new WebXmlUpdaterForJavaEE(webAppObj, project, getProvider(), monitor);
            else if (webAppObj instanceof org.eclipse.jst.j2ee.webapplication.internal.impl.WebAppImpl) // J2EE
                return new WebXmlUpdaterForJ2EE(webAppObj, project, getProvider(), monitor);
        }

        // Control should never come here
        throw new IllegalArgumentException("Unable to resolve WebApp object for updating web.xml"); //$NON-NLS-1$
    }
}
