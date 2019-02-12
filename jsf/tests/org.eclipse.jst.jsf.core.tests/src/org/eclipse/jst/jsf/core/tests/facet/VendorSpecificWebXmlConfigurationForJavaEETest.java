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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.jsf.common.webxml.WebXmlUpdater;
import org.eclipse.jst.jsf.common.webxml.WebXmlUtilsForJavaEE;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;


/**
 * Tests the web.xml updating functionality used for vendor-specific web.xml
 * configuration (for Java EE)
 *
 * @author Debajit Adhikary
 *
 * @see VendorSpecificWebXmlConfigurationForJ2EETest
 *
 */
public class VendorSpecificWebXmlConfigurationForJavaEETest extends TestCase
{
    private static final IProjectFacetVersion WEB_MODULE_VERSION = ProjectFacetsManager.getProjectFacet("jst.web").getVersion("2.5");

    private static final IProjectFacetVersion JAVA_VERSION = JavaFacet.VERSION_1_5;

    private static final String PROJECT_NAME_PREFIX = "_TEST_PROJECT_NAME_FOR_JAVA_EE";
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


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        project = createProject(PROJECT_NAME_PREFIX + this.getClass().getName() + getName());
        updater = new WebXmlUpdater(project, new NullProgressMonitor());
    }


    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFTestUtil.safeDelete(project, 3, 500);
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
        // Write servlet to web.xml
        updater.addServlet(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_LOAD_ON_STARTUP);

        // Read servlet from web.xml
        final Servlet servlet = WebXmlUtilsForJavaEE.findServlet(SERVLET_CLASS_NAME, updater.getWebAppForJavaEE());
        assertNotNull(servlet);
        assertEquals(SERVLET_NAME, servlet.getServletName());
        assertEquals(SERVLET_CLASS_NAME, servlet.getServletClass());
        assertEquals(SERVLET_LOAD_ON_STARTUP, servlet.getLoadOnStartup().toString());
    }


    public void testAddContextParam () throws Exception
    {
        // Write param to web.xml
        updater.addServlet(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_LOAD_ON_STARTUP);
        updater.addContextParam(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE, CONTEXT_PARAM_DESCRIPTION);

        // Read param from web.xml
        final ParamValue param = WebXmlUtilsForJavaEE.findContextParam(updater.getWebAppForJavaEE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE);
        assertNotNull(param);
        assertEquals(CONTEXT_PARAM_NAME, param.getParamName());
        assertEquals(CONTEXT_PARAM_VALUE, param.getParamValue());
        assertEquals(CONTEXT_PARAM_DESCRIPTION, ((Description) param.getDescriptions().get(0)).getValue());
    }


    public void testGetContextParamValue ()
    {
        assertFalse(WebXmlUtilsForJavaEE.existsContextParam(updater.getWebAppForJavaEE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.addContextParam(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE, CONTEXT_PARAM_DESCRIPTION);

        final String contextParamValue = updater.getContextParamValue(CONTEXT_PARAM_NAME);
        assertNotNull(contextParamValue);
        assertEquals(CONTEXT_PARAM_VALUE, contextParamValue);
    }


    public void testGetContextParamValuesAsList ()
    {
        assertFalse(WebXmlUtilsForJavaEE.existsContextParam(updater.getWebAppForJavaEE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        final String delimiter = ";";
        final String paramValue = CONTEXT_PARAM_VALUE + "1" + delimiter + CONTEXT_PARAM_VALUE + "2" + delimiter + CONTEXT_PARAM_VALUE + "3";
        updater.addContextParam(CONTEXT_PARAM_NAME, paramValue, CONTEXT_PARAM_DESCRIPTION);

        final List<String> expectedList = Arrays.asList(CONTEXT_PARAM_VALUE + "1", CONTEXT_PARAM_VALUE + "2", CONTEXT_PARAM_VALUE + "3");
        final List<String> actualList = updater.getContextParamValuesAsList(CONTEXT_PARAM_NAME, delimiter);
        assertEquals(expectedList, actualList);
    }


    public void testSetContextParamValue ()
    {
        assertFalse(WebXmlUtilsForJavaEE.existsContextParam(updater.getWebAppForJavaEE(), CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE));
        updater.setContextParamValue(CONTEXT_PARAM_NAME, CONTEXT_PARAM_VALUE); // Adds new context param
        assertEquals(CONTEXT_PARAM_VALUE, updater.getContextParamValue(CONTEXT_PARAM_NAME));

        final String updatedValue = CONTEXT_PARAM_VALUE + "updated";
        assertFalse(updater.getContextParamValue(CONTEXT_PARAM_NAME).equals(updatedValue));

        updater.setContextParamValue(CONTEXT_PARAM_NAME, updatedValue);
        assertEquals(updatedValue, updater.getContextParamValue(CONTEXT_PARAM_NAME));
    }


    public void testAddServletMapping () throws Exception
    {
        // Write servlet-mapping to web.xml
        updater.addServlet(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_LOAD_ON_STARTUP);
        updater.addServletMapping(SERVLET_NAME, SERVLET_CLASS_NAME, SERVLET_URL_PATTERN);

        // Read from web.xml
        final ServletMapping mapping = WebXmlUtilsForJavaEE.findServletMapping(updater.getWebAppForJavaEE(), SERVLET_NAME, SERVLET_URL_PATTERN);
        assertNotNull(mapping);
        assertEquals(SERVLET_NAME, mapping.getServletName());
        assertEquals(SERVLET_URL_PATTERN, ((UrlPatternType) mapping.getUrlPatterns().get(0)).getValue());
    }


    public void testAddListener () throws Exception
    {
        // Write to web.xml
        updater.addListener(LISTENER_CLASS);

        // Read from web.xml
        final Listener listener = WebXmlUtilsForJavaEE.findListener(updater.getWebAppForJavaEE(), LISTENER_CLASS);
        assertNotNull(listener);
        assertEquals(LISTENER_CLASS, listener.getListenerClass());
    }
}
