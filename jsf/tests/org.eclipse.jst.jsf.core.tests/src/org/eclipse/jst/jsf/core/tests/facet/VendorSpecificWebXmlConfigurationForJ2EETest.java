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


package org.eclipse.jst.jsf.core.tests.facet;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
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

    private static final IProjectFacetVersion JAVA_VERSION = JavaFacet.VERSION_1_5;

    private static final String SERVLET_NAME = "_TEST_SERVLET_NAME";
    private static final String SERVLET_CLASS_NAME = "_TEST_SERVLET_CLASS_NAME";
    private static final String SERVLET_LOAD_ON_STARTUP = "1";
    private static final String SERVLET_URL_PATTERN = "*._TEST_SERVLET_URL_PATTERN";
    private static final String CONTEXT_PARAM_NAME = "_TEST_CONTEXT_PARAM_NAME";
    private static final String CONTEXT_PARAM_VALUE = "_TEST_CONTEXT_PARAM_VALUE";
    private static final String CONTEXT_PARAM_DESCRIPTION = "_TEST_CONTEXT_PARAM_DESCRIPTION";
    private static final String LISTENER_CLASS = "_TEST_LISTENER_CLASS";

    private IProject project;
    private WebXmlUpdater updater;

    private IProject createProject (final String projectName)
    throws Exception
    {
        final WebProjectTestEnvironment testEnv = new WebProjectTestEnvironment(projectName, JAVA_VERSION, WEB_MODULE_VERSION);
        assertTrue(testEnv.createProject(true));
        return testEnv.getTestProject();
    }


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        this.project = createProject(this.getClass().getName() + "_" + getName());
        this.updater = new WebXmlUpdater(project, null);
    }


    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        System.out.println(JSFTestUtil.safeDelete(project, 3, 500));
    }


    public void testAddServlet () throws Exception
    {
        setupServlet();
    }


    private void setupServlet()
    {
        // Write servlet to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsServlet(updater.getWebAppForJ2EE(), SERVLET_NAME, SERVLET_CLASS_NAME));
        updater.addServlet(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_LOAD_ON_STARTUP);

        // Read servlet from web.xml
        final Servlet servlet = WebXmlUtilsForJ2EE.findServlet(updater.getWebAppForJ2EE(), SERVLET_CLASS_NAME);
        assertEquals(SERVLET_NAME, servlet.getServletName());
        assertEquals(SERVLET_CLASS_NAME, ((ServletTypeImpl) servlet.getWebType()).getClassName());
        assertEquals(Integer.parseInt(SERVLET_LOAD_ON_STARTUP), servlet.getLoadOnStartup().intValue());
    }


    public void testAddContextParam () throws Exception
    {
        // Write param to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsContextParam(updater.getWebAppForJ2EE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.addContextParam(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE, CONTEXT_PARAM_DESCRIPTION);

        // Read param from web.xml
        final ParamValue param = WebXmlUtilsForJ2EE.findContextParam(updater.getWebAppForJ2EE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE);
        assertEquals(CONTEXT_PARAM_NAME, param.getName());
        assertEquals(CONTEXT_PARAM_VALUE, param.getValue());
        assertEquals(CONTEXT_PARAM_DESCRIPTION, ((Description) param.getDescriptions().get(0)).getValue());
    }


    public void testGetContextParamValue ()
    {
        assertFalse(WebXmlUtilsForJ2EE.existsContextParam(updater.getWebAppForJ2EE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.addContextParam(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE, CONTEXT_PARAM_DESCRIPTION);

        final String contextParamValue = updater.getContextParamValue(CONTEXT_PARAM_NAME);
        assertNotNull(contextParamValue);
        assertEquals(CONTEXT_PARAM_VALUE, contextParamValue);
    }


    public void testGetContextParamValuesAsList ()
    {
        assertFalse(WebXmlUtilsForJ2EE.existsContextParam(updater.getWebAppForJ2EE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        final String delimiter = ";";
        final String paramValue = CONTEXT_PARAM_VALUE + "1" + delimiter + CONTEXT_PARAM_VALUE + "2" + delimiter + CONTEXT_PARAM_VALUE + "3";
        updater.addContextParam(CONTEXT_PARAM_NAME, paramValue, CONTEXT_PARAM_DESCRIPTION);

        final List<String> expectedList = Arrays.asList(CONTEXT_PARAM_VALUE + "1", CONTEXT_PARAM_VALUE + "2", CONTEXT_PARAM_VALUE + "3");
        final List<String> actualList = updater.getContextParamValuesAsList(CONTEXT_PARAM_NAME, delimiter);
        assertEquals(expectedList, actualList);
    }


    public void testSetContextParamValue ()
    {
        assertFalse(WebXmlUtilsForJ2EE.existsContextParam(updater.getWebAppForJ2EE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.setContextParamValue(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE); // Adds new context param
        assertEquals(CONTEXT_PARAM_VALUE, updater.getContextParamValue(CONTEXT_PARAM_NAME));

        final String updatedValue = CONTEXT_PARAM_VALUE + "updated";
        assertFalse(updater.getContextParamValue(CONTEXT_PARAM_NAME).equals(updatedValue));

        updater.setContextParamValue(CONTEXT_PARAM_NAME, updatedValue);
        assertEquals(updatedValue, updater.getContextParamValue(CONTEXT_PARAM_NAME));
    }


    public void testAddServletMapping () throws Exception
    {
        setupServlet();
        // Write servlet-mapping to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsServletMapping(updater.getWebAppForJ2EE(), SERVLET_NAME, SERVLET_URL_PATTERN));
        updater.addServletMapping(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_URL_PATTERN);

        // Read from web.xml
        final ServletMapping mapping = WebXmlUtilsForJ2EE.findServletMapping(updater.getWebAppForJ2EE(), SERVLET_NAME, SERVLET_URL_PATTERN);
        assertEquals(SERVLET_NAME, mapping.getName());
        assertEquals(SERVLET_URL_PATTERN, mapping.getUrlPattern());
    }


    public void testAddListener () throws Exception
    {
        // Write to web.xml
        assertFalse(WebXmlUtilsForJ2EE.existsListener(updater.getWebAppForJ2EE(), LISTENER_CLASS));
        updater.addListener(LISTENER_CLASS);

        // Read from web.xml
        final Listener listener = WebXmlUtilsForJ2EE.findListener(updater.getWebAppForJ2EE(), LISTENER_CLASS);
        assertEquals(LISTENER_CLASS, listener.getListenerClassName());
    }
}
