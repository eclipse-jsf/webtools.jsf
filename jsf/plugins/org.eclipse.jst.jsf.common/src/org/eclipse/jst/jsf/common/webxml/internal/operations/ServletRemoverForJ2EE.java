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


package org.eclipse.jst.jsf.common.webxml.internal.operations;

import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE.findServlet;
import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE.removeServlet;
import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE.removeServletMappings;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * Removes a servlet and its associated mappings from web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class ServletRemoverForJ2EE implements Runnable
{
    private final IProject project;
    private final String servletClassName;


    /**
     * @param project
     * @param servletClassName
     */
    public ServletRemoverForJ2EE (final IProject project,
                                  final String servletClassName)
    {
        this.project = project;
        this.servletClassName = servletClassName;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        final Servlet servlet = findServlet(webApp, servletClassName);

        removeServletMappings(webApp, servlet);
        removeServlet(webApp, servlet);
    }
}
