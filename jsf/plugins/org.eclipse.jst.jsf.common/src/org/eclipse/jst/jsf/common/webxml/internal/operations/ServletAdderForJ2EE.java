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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE;


/**
 * Runnable to add a servlet to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class ServletAdderForJ2EE implements Runnable
{
    private final IProject project;
    private final String servletName;
    private final String servletClass;
    private final String loadOnStartup;


    /**
     * @param project
     * @param servletName
     * @param servletClass
     * @param loadOnStartup
     */
    public ServletAdderForJ2EE (final IProject project,
                                final String servletName,
                                final String servletClass,
                                final String loadOnStartup)
    {
        this.project = project;
        this.servletName = servletName;
        this.servletClass = servletClass;
        this.loadOnStartup = loadOnStartup;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJ2EE.addServlet(webApp, servletName, servletClass, loadOnStartup);
    }
}
