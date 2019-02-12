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
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE;


/**
 * Runnable to add a filter-mapping to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class FilterMapperAdderForJavaEE implements Runnable
{
    private final IProject project;
    private final String filterName;
    private final String servletName;


    /**
     * @param project
     * @param filterName
     * @param servletName
     */
    public FilterMapperAdderForJavaEE (final IProject project,
                                       final String filterName,
                                       final String servletName)
    {
        this.project = project;
        this.filterName = filterName;
        this.servletName = servletName;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJavaEE.addFilterMapping(webApp, filterName, servletName);
    }
}
