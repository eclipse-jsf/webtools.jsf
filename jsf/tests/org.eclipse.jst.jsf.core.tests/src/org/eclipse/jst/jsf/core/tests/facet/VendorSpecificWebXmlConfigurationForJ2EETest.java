/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.core.tests.facet;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.internal.impl.ServletTypeImpl;
import org.eclipse.jst.jsf.common.webxml.WebXmlUpdater;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJ2EE;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;


/**
 * Tests the web.xml updating functionality used for vendor-specific web.xml
 * configuration
 *
 * @author Debajit Adhikary
 *
 * @see VendorSpecificWebXmlConfigurationForJavaEETest
 *
 */
public class VendorSpecificWebXmlConfigurationForJ2EETest extends TestCase
{
    private static final IProjectFacetVersion WEB_MODULE_VERSION = ProjectFacetsManager.getProjectFacet("jst.web").getVersion("2.4");

    private static final IProjectFacetVersion JAVA_VERSION = JavaFacetUtils.JAVA_50;

    private static final String PROJECT_NAME_PREFIX = "_TEST_PROJECT_NAME_FOR_J2EE";
    private static final String SERVLET_NAME = "_TEST_SERVLET_NAME";
    private static final String SERVLET_CLASS_NAME = "_TEST_SERVLET_CLASS_NAME";
    private static final String SERVLET_LOAD_ON_STARTUP = "1";
    private static final String SERVLET_URL_PATTERN = "*._TEST_SERVLET_URL_PATTERN";
    private static final String CONTEXT_PARAM_NAME = "_TEST_CONTEXT_PARAM_NAME";
    private static final String CONTEXT_PARAM_VALUE = "_TEST_CONTEXT_PARAM_VALUE";
    private static final String CONTEXT_PARAM_DESCRIPTION = "_TEST_CONTEXT_PARAM_DESCRIPTION";
    private static final String LISTENER_CLASS = "_TEST_LISTENER_CLASS";

    private final IProject project;
    private final WebXmlUpdater updater;
    private final WebApp webapp;


    /**
     * @param name
     * @throws Exception
     */
    public VendorSpecificWebXmlConfigurationForJ2EETest (final String name)
    throws Exception
    {
        super(name);

        this.project = createProject(PROJECT_NAME_PREFIX);
        this.updater = new WebXmlUpdater(project, null);
        this.webapp = (WebApp) updater.getProvider().getModelObject();
    }


    private IProject createProject (final String projectName)
    throws Exception
    {
        final WebProjectTestEnvironment testEnv = new WebProjectTestEnvironment(projectName, JAVA_VERSION, WEB_MODULE_VERSION);
        testEnv.createProject(true);
        return testEnv.getTestProject();
    }


    public void testAddServlet () throws Exception
    {
        final IProject project = createProject(PROJECT_NAME_PREFIX + this.getClass().getName() + getName());

        // Write servlet to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsServlet(webapp, SERVLET_NAME, SERVLET_CLASS_NAME));
        updater.addServlet(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_LOAD_ON_STARTUP);

        // Read servlet from web.xml
        final Servlet servlet = WebXmlUtilsForJ2EE.findServlet(webapp, SERVLET_CLASS_NAME);
        assertEquals(SERVLET_NAME, servlet.getServletName());
        assertEquals(SERVLET_CLASS_NAME, ((ServletTypeImpl) servlet.getWebType()).getClassName());
        assertEquals(Integer.parseInt(SERVLET_LOAD_ON_STARTUP), servlet.getLoadOnStartup().intValue());

        //project.delete(true, new NullProgressMonitor());
        System.out.println(JSFTestUtil.safeDelete(project, 3, 500));
    }


    public void testAddContextParam () throws Exception
    {
        final IProject project = createProject(PROJECT_NAME_PREFIX + this.getClass().getName() + getName());

        // Write param to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsContextParam(webapp, CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.addContextParam(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE, CONTEXT_PARAM_DESCRIPTION);

        // Read param from web.xml
        final ParamValue param = WebXmlUtilsForJ2EE.findContextParam(webapp, CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE);
        assertEquals(CONTEXT_PARAM_NAME, param.getName());
        assertEquals(CONTEXT_PARAM_VALUE, param.getValue());
        assertEquals(CONTEXT_PARAM_DESCRIPTION, ((Description) param.getDescriptions().get(0)).getValue());

        project.delete(true, new NullProgressMonitor());
    }


    public void testAddServletMapping () throws Exception
    {
        final IProject project = createProject(PROJECT_NAME_PREFIX + this.getClass().getName() + getName());

        // Write servlet-mapping to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsServletMapping(webapp, SERVLET_NAME, SERVLET_URL_PATTERN));
        updater.addServletMapping(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_URL_PATTERN);

        // Read from web.xml
        final ServletMapping mapping = WebXmlUtilsForJ2EE.findServletMapping(webapp, SERVLET_NAME, SERVLET_URL_PATTERN);
        assertEquals(SERVLET_NAME, mapping.getName());
        assertEquals(SERVLET_URL_PATTERN, mapping.getUrlPattern());

        project.delete(true, new NullProgressMonitor());
    }


    public void testAddListener () throws Exception
    {
        final IProject project = createProject(PROJECT_NAME_PREFIX + this.getClass().getName() + getName());

        // Write to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsListener(webapp, LISTENER_CLASS));
        updater.addListener(LISTENER_CLASS);

        // Read from web.xml
        final Listener listener = WebXmlUtilsForJ2EE.findListener(webapp, LISTENER_CLASS);
        assertEquals(LISTENER_CLASS, listener.getListenerClassName());

        project.delete(true, new NullProgressMonitor());
    }
}
