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


import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE.findFilter;
import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE.removeFilter;
import static org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE.removeFilterMappings;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.WebApp;


/**
 * Removes a filter and its associated mappings from web.xml
 *
 * @author Debajit Adhikary
 *
 */
public class FilterRemoverForJavaEE implements Runnable
{
    private final IProject project;
    private final String filterClassName;


    /**
     * @param project
     * @param filterClassName
     */
    public FilterRemoverForJavaEE (final IProject project,
                                   final String filterClassName)
    {
        this.project = project;
        this.filterClassName = filterClassName;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        final Filter filter = findFilter(webApp, filterClassName);

        removeFilterMappings(webApp, filter);
        removeFilter(webApp, filter);
    }
}
