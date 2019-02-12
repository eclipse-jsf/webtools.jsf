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
package org.eclipse.jst.jsf.designtime.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.context.IDTExternalContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class TestDTFacesContext extends TestCase 
{
    private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;
    private WebProjectTestEnvironment _webProjectTestEnv;
    private IFile _testJSP;

    
    private final static String       TESTJSP1_PATH = "testdata1.jsp";
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.us.oracle.com", "80");

         _webProjectTestEnv= new WebProjectTestEnvironment(getProjectName(), JavaFacet.VERSION_1_5, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.5"));
        _webProjectTestEnv.createProject(false);

        _webProjectTestEnv.
        	loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle(),
                              "/testdata/faces-config_2_0.xml.data", 
                              "/WEB-INF/faces-config.xml");
        final IResource res = _webProjectTestEnv.loadResourceInWebRoot(
                DesignTimeTestsPlugin.getDefault().getBundle(),
                "/testdata/testdata1.jsp.data", TESTJSP1_PATH);
        _testJSP = (IFile) res;

        new JDTTestEnvironment(_webProjectTestEnv);

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(
                _webProjectTestEnv);
        _jsfFactedTestEnvironment
                .initialize(IJSFCoreConstants.FACET_VERSION_2_0);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetDTExternalContext() 
    {
        final DTFacesContext facesContext = getFacesContext(_testJSP);
        assertNotNull(facesContext);
        final IDTExternalContext externalContext = facesContext.getDTExternalContext(_testJSP);
        assertNotNull(externalContext);
        assertEquals(0, externalContext.getNoneMap().size());
        // the JSP page define a variable for a dataTable and a loadBundle
        assertEquals(2, externalContext.getRequestMap().size());
        assertEquals(0, externalContext.getSessionMap().size());
        assertEquals(0, externalContext.getApplicationMap().size());
        
        // verify the context path
        assertEquals(getProjectName(), externalContext.getRequestContextPath());
    }

    public void testGetViewRoot() 
    {
        // other tests on viewroot in view.*
        final DTFacesContext facesContext = getFacesContext(_testJSP);
        IViewRootHandle  handle = facesContext.getViewRootHandle();
        final DTUIViewRoot viewRoot = handle.updateViewRoot();
        assertNotNull(viewRoot);
        assertEquals("/"+TESTJSP1_PATH, viewRoot.getViewId());
        
        assertNotNull(viewRoot.getViewMap());
    }

    public void _testAdaptContextObject() {
        final DTFacesContext facesContext = getFacesContext(_testJSP);
        final IResource res = facesContext.adaptContextObject();
        assertEquals(_testJSP, res);
    }

    private DTFacesContext getFacesContext(final IFile jspFile)
    {
        final IProject project = _webProjectTestEnv.getTestProject();
        final DesignTimeApplicationManager manager =
            DesignTimeApplicationManager.getInstance(project);
        
        return  manager.getFacesContext(jspFile);

    }

    private String getProjectName()
    {
        return "TestDesignTimeApplicationManager_" + getName();
    }
}
