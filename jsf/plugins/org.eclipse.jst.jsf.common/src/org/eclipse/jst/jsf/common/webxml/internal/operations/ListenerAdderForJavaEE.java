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
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE;


/**
 * Runnable to add a listener to web.xml.
 *
 * @author Debajit Adhikary
 *
 */
public class ListenerAdderForJavaEE implements Runnable
{
    private final IProject project;
    private final String listenerClass;


    /**
     * @param project
     * @param listenerClass
     */
    public ListenerAdderForJavaEE (final IProject project,
                                   final String listenerClass)
    {
        this.project = project;
        this.listenerClass = listenerClass;
    }


    public void run ()
    {
        final WebApp webApp = (WebApp) ModelProviderManager.getModelProvider(project).getModelObject();
        
        WebXmlUtilsForJavaEE.addListener(webApp, listenerClass);
    }
}
