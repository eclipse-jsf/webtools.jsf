package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestDesignTimeApplicationManager extends TestCase 
{
	private IFile 						_testJSP;
	private JSFFacetedTestEnvironment 	_jsfFactedTestEnvironment;


	protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDesignTimeApplicationManager"+getName());
        projectTestEnvironment.createProject(false);

        final JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        final IResource res = 
        	projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetInstance() {
		//fail("Not yet implemented");
	}

	public void testGetFacesContext() 
	{
		final DesignTimeApplicationManager  manager = 
			DesignTimeApplicationManager.getInstance(_testJSP.getProject());
		
		final DTFacesContext facesContext = manager.getFacesContext(_testJSP);
		assertNotNull(facesContext);
	}

//	public void testSetExternalContextProvider() 
//	{
//		final DesignTimeApplicationManager  manager = 
//			DesignTimeApplicationManager.getInstance(_testJSP.getProject());
//
//		manager.setExternalContextProvider(resolverPluginId);
//	}
//
//	public void testGetExternalContextProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetVariableResolver() {
//		//fail("Not yet implemented");
//	}
//
//	public void testSetVariableResolverProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetVariableResolverProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetPropertyResolver() {
//		//fail("Not yet implemented");
//	}
//
//	public void testSetPropertyResolverProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetPropertyResolverProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetMethodResolver() {
//		//fail("Not yet implemented");
//	}
//
//	public void testSetMethodResolverProvider() {
//		//fail("Not yet implemented");
//	}
//
//	public void testGetMethodResolverProvider() {
//		//fail("Not yet implemented");
//	}
}
