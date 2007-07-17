package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.jsp.StartupHandler;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.ResourceUtil;

/**
 * Some basic coverage of the editor open listener registered by the
 * StartupHandler
 * 
 * @author cbateman
 *
 */
public class TestStartupHandler extends TestCase 
{
	private IFile _testJSP1;
	private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

	protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDTJSPExternalContext"+getName());
        projectTestEnvironment.createProject(false);

        JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        IResource res = projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP1 = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);	
    }

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	public void testLaunchEditor() throws Exception
	{
		final IWorkbenchPage  curPage = 
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		final IEditorPart editor =
			IDE.openEditor
				(curPage
				, _testJSP1);
		assertNotNull(editor);
		final IEditorPart foundEditor = ResourceUtil.findEditor(curPage, _testJSP1);
		assertEquals(editor, foundEditor);
		
		StartupHandler handler;
	}
}
