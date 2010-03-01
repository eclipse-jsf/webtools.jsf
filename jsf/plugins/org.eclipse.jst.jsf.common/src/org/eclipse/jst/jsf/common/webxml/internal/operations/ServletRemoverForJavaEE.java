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


package org.eclipse.jst.jsf.common.webxml.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE;


/**
 * Removes a servlet and its associated mappings from web.xml
 *
 * @author Debajit Adhikary
 *
 */
public class ServletRemoverForJavaEE implements Runnable
{
    private final IProject project;
    private final String servletClassName;


    /**
     * @param project
     * @param servletClassName
     */
    public ServletRemoverForJavaEE (final IProject project,
                                    final String servletClassName)
    {
        this.project = project;
        this.servletClassName = servletClassName;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        final Servlet servlet = WebXmlUtilsForJavaEE.findServlet(servletClassName, webApp);

        WebXmlUtilsForJavaEE.removeServletMappings(webApp, servlet);
        WebXmlUtilsForJavaEE.removeServlet(webApp, servlet);
    }
}
