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
 * Runnable to add a context-param to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class ContextParamAdderForJavaEE implements Runnable
{
    private final IProject project;
    private final String paramName;
    private final String paramValue;
    private final String description;


    /**
     * @param project
     * @param paramName
     * @param paramValue
     * @param description
     */
    public ContextParamAdderForJavaEE (final IProject project,
                                       final String paramName,
                                       final String paramValue,
                                       final String description)
    {
        this.project = project;
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.description = description;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJavaEE.addContextParam(webApp, paramName, paramValue, description);
    }
}
