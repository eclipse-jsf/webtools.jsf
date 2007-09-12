package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
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

    protected void tearDown() throws Exception {
        super.tearDown();
    }

//    public void testGetInstance() {
//        // fail("Not yet implemented");
//    }

    public void testGetFacesContext() {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_testJSP.getProject());

        final DTFacesContext facesContext = manager.getFacesContext(_testJSP);
        assertNotNull(facesContext);
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

        RenameSupport renameSupport =
            RenameSupport.create(_jdtTestEnv.getJavaProject(), "RenamedProject"+getName(), RenameSupport.UPDATE_REFERENCES);
        renameSupport.perform(new Shell(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());

        assertFalse(_jdtTestEnv.getJavaProject().getProject().isAccessible());
        IProject project = 
            ResourcesPlugin.getWorkspace().getRoot().getProject("RenamedProject"+getName());
        
        assertTrue(project.isAccessible());
        
        manager = DesignTimeApplicationManager.getInstance(project);
        assertNotNull(manager);
        // ensure that the resolver provider persistent key gets
        // transferred after the rename
        assertEquals("my.test.blah", manager.getPropertyResolverProvider());
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
    // public void testGetVariableResolver() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testSetVariableResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testGetVariableResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testGetPropertyResolver() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testSetPropertyResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testGetPropertyResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testGetMethodResolver() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testSetMethodResolverProvider() {
    // //fail("Not yet implemented");
    // }
    //
    // public void testGetMethodResolverProvider() {
    // //fail("Not yet implemented");
    // }
}
