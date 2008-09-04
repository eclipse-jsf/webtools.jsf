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
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.context.DTJSPExternalContext;
import org.eclipse.jst.jsf.designtime.context.IDTExternalContext;
import org.eclipse.jst.jsf.designtime.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.designtime.el.DefaultDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.internal.view.DefaultDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class TestDesignTimeApplicationManager extends TestCase {
    private IFile _testJSP;
    private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;
    private WebProjectTestEnvironment _webProjectTestEnv;
    private JDTTestEnvironment        _jdtTestEnv;

    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.us.oracle.com", "80");

         _webProjectTestEnv= new WebProjectTestEnvironment(
                "TestDesignTimeApplicationManager" + getName());
        _webProjectTestEnv.createProject(false);

        _jdtTestEnv = new JDTTestEnvironment(_webProjectTestEnv);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(),
                "/testdata/bundle1.resources.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(
                input.toBytes()), "bundles", "bundle1.properties");

        final IResource res = _webProjectTestEnv.loadResourceInWebRoot(
                DesignTimeTestsPlugin.getDefault().getBundle(),
                "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(
                _webProjectTestEnv);
        _jsfFactedTestEnvironment
                .initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetFacesContext() 
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());

        final DTFacesContext facesContext = manager.getFacesContext(_testJSP);
        assertNotNull(facesContext);
        
        // exercise the default locator
        final IDTExternalContext externalContext = 
            facesContext.getDTExternalContext(_testJSP);
        assertNotNull(externalContext);
        assertEquals(_testJSP, facesContext.adaptContextObject());
    }

    public void testBug147729() throws Exception
    {
        // if the project is renamed, it is actually moved.  This causes
        // the IProject to have it's persistent props seemlessly copied,
        // but the DesignTimeApplicationManager was not being correctly
        // updated
        DesignTimeApplicationManager manager = 
            DesignTimeApplicationManager.getInstance(_webProjectTestEnv.getTestProject());
        assertNotNull(manager.getPropertyResolver());

        manager.setPropertyResolverProvider("my.test.blah");
        assertEquals("my.test.blah", manager.getPropertyResolverProvider());

        final RenameSupport renameSupport =
            RenameSupport.create(_jdtTestEnv.getJavaProject(), "RenamedProject"+getName(), RenameSupport.UPDATE_REFERENCES);
        renameSupport.perform(new Shell(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());

        assertFalse(_jdtTestEnv.getJavaProject().getProject().isAccessible());
        final IProject project = 
            ResourcesPlugin.getWorkspace().getRoot().getProject("RenamedProject"+getName());

        assertTrue(project.isAccessible());

        manager = DesignTimeApplicationManager.getInstance(project);
        assertNotNull(manager);
        // ensure that the resolver provider persistent key gets
        // transferred after the rename
        assertEquals("my.test.blah", manager.getPropertyResolverProvider());
    }

    public void testGetVariableResolver()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
            .getInstance(_testJSP.getProject());
        final AbstractDTVariableResolver  variableResolver = 
            manager.getVariableResolver();
        assertNotNull(variableResolver);
    }

    public void testGetPropertyResolver()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        final AbstractDTPropertyResolver propertyResolver = manager
                .getPropertyResolver();
        assertNotNull(propertyResolver);
    }
    
    public void testGetMethodResolver()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        final AbstractDTMethodResolver methodResolver = manager
                .getMethodResolver();
        assertNotNull(methodResolver);
    }
    
    public void testFileWithNoDT()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
            .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        final IFile file1 = _testJSP.getProject().getFile(new Path("fakefile1"));
        // file object exists but underlying IResource is not
        assertNotNull(file1);
        assertFalse(file1.isAccessible());

        // therefore, there will be no dt for this file
        assertFalse(manager.hasDTFacesContext(file1));
        assertNull(manager.getFacesContext(file1));
    }
    
    public void testGetDefaultPropertyResolver()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
            .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        final AbstractDTPropertyResolver defaultPropertyResolver = 
            manager.getDefaultPropertyResolver();
        assertNotNull(defaultPropertyResolver);
        assertTrue(defaultPropertyResolver instanceof DefaultDTPropertyResolver);
    }

    public void testGetExternalContextProvider()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // default value
        assertEquals("org.eclipse.jst.jsf.core.externalcontext.default"
                , manager.getExternalContextProvider());
    }
    
    public void testGetMethodResolverProvider()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // default value
        assertEquals("org.eclipse.jst.jsf.core.methodresolver.default",
                manager.getMethodResolverProvider());
    }
    
    public void testGetVariableResolverProvider()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // default value
        assertEquals("org.eclipse.jst.jsf.core.variableresolver.default.decorative",
                manager.getVariableResolverProvider());
    }

    public void testSetVariableResolverProvider() throws Exception
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // get the default variable resolver
        final AbstractDTVariableResolver variableResolver = manager.getVariableResolver();
        
        manager.setVariableResolverProvider("foo.bar.variableResolver");
        // default value
        assertEquals("foo.bar.variableResolver"
                , manager.getVariableResolverProvider());
        
        // this should return the same var resolver, since the id is false 
        // and an alternative won't be found
        assertEquals(variableResolver, manager.getVariableResolver());
    }

    public void testSetPropertyResolverProvider() throws Exception
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // get the default property resolver
        final AbstractDTPropertyResolver propertyResolver = manager.getPropertyResolver();
        
        manager.setPropertyResolverProvider("foo.bar.propertyResolver");
        // default value
        assertEquals("foo.bar.propertyResolver"
                , manager.getPropertyResolverProvider());
        
        // this should return the same property resolver, since the id is false 
        // and an alternative won't be found
        assertEquals(propertyResolver, manager.getPropertyResolver());
    }
    
    public void testSetMethodResolverProvider() throws Exception
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // get the default method resolver
        final AbstractDTMethodResolver methodResolver = manager.getMethodResolver();
        
        manager.setMethodResolverProvider("foo.bar.methodResolver");
        // default value
        assertEquals("foo.bar.methodResolver"
                , manager.getMethodResolverProvider());
        
        // this should return the same method resolver, since the id is false 
        // and an alternative won't be found
        assertEquals(methodResolver, manager.getMethodResolver());
    }

    public void testSetExternalContextProvider() throws Exception
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // get the default external context
        final DTFacesContext facesContext = manager.getFacesContext(_testJSP);
        final IDTExternalContext externalContext = 
            facesContext.getDTExternalContext(_testJSP);
        assertTrue(externalContext instanceof DTJSPExternalContext);

        manager.setExternalContextProvider("foo.bar.externalContextLocator");
        // default value
        assertEquals("foo.bar.externalContextLocator"
                , manager.getExternalContextProvider());

        // this should return the same external context, since the id is false 
        // and an alternative won't be found
        assertTrue(facesContext.getDTExternalContext(_testJSP) instanceof DTJSPExternalContext);
    }

    public void testGetPropertyResolverProvider()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        // default value
        assertEquals(
                "org.eclipse.jst.jsf.core.propertyresolver.default.decorative",
                manager.getPropertyResolverProvider());
    }

    public void testGetViewHandler()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());
        assertNotNull(manager);

        IDTViewHandler viewHandler = manager.getViewHandler();
        assertNotNull(viewHandler);
        assertTrue(viewHandler instanceof DefaultDTViewHandler);
    }

    public void testSetViewHandler() throws Exception
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
            .getInstance(_testJSP.getProject());
        assertNotNull(manager);
        
        final IFile settingsFile = getSettingsFile(_testJSP.getProject());
        assertFalse(settingsFile.isAccessible());
        
        manager.setViewHandlerId("foobar.chicken.on.a.bun");
        assertTrue(settingsFile.isAccessible());
        
        Properties props = new Properties();
        props.load(settingsFile.getContents());
        assertEquals("foobar.chicken.on.a.bun", props.getProperty("ViewHandler"));
    }

    public void testSetAndReloadSettings() throws Exception
    {
        final IProject project = _testJSP.getProject();
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(project);
        assertNotNull(manager);

        final IFile settingsFile = getSettingsFile(project);
        assertFalse(settingsFile.isAccessible());

        manager.setViewHandlerId("foobar.chicken.on.a.bun");
        assertTrue(settingsFile.isAccessible());

        Properties props = new Properties();
        props.load(settingsFile.getContents());
        assertEquals("foobar.chicken.on.a.bun", props.getProperty("ViewHandler"));
        
        // now, close and reopen the project
        project.close(null);
        assertFalse(project.isOpen());
        project.open(null);
        assertTrue(project.isOpen());

        final DesignTimeApplicationManager newManager = DesignTimeApplicationManager
                .getInstance(project);
        assertNotNull(newManager.getViewHandler());
    }

    private static IFile getSettingsFile(final IProject project)
    {
        return project.getFolder(new Path(".settings")).getFile("org.eclipse.jst.jsf.designtime.appmgr.prefs");
    }
    
    // public void testSetExternalContextProvider()
    // {
    // final DesignTimeApplicationManager manager =
    // DesignTimeApplicationManager.getInstance(_testJSP.getProject());
    //
    // manager.setExternalContextProvider(resolverPluginId);
    // }
    //
    // public void testGetExternalContextProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testSetVariableResolverProvider() {
    // //fail("Not yet implemented");
    // }
    // public void testSetPropertyResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testSetMethodResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
}
