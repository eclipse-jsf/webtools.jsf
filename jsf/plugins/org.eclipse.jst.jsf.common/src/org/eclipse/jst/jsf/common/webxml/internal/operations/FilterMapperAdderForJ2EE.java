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
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE;


/**
 * Runnable to add a filter-mapping to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class FilterMapperAdderForJ2EE implements Runnable
{
    private final IProject project;
    private final String filterName;
    private final String filterClass;
    private final String servletName;


    public FilterMapperAdderForJ2EE (final IProject project,
                               final String filterName,
                               final String filterClass,
                               final String servletName)
    {
        this.project = project;
        this.filterName = filterName;
        this.filterClass = filterClass;
        this.servletName = servletName;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJ2EE.addFilterMapping(webApp, filterName, filterClass, servletName);
    }
}
