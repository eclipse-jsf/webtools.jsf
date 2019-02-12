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
 * Runnable to add a servlet-mapping to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class ServletMappingAdderForJ2EE implements Runnable
{
    private final IProject project;
    private final String servletName;
    private final String servletClass;
    private final String urlPatternString;


    /**
     * @param project
     * @param servletName
     * @param servletClass
     * @param urlPatternString
     */
    public ServletMappingAdderForJ2EE (final IProject project,
                                       final String servletName,
                                       final String servletClass,
                                       final String urlPatternString)
    {
        this.project = project;
        this.servletName = servletName;
        this.servletClass = servletClass;
        this.urlPatternString = urlPatternString;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJ2EE.addServletMapping(webApp, servletName, servletClass, urlPatternString);
    }
}
