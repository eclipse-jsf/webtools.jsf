package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.jsp.JSPModelProcessor;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CloseResourceAction;
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
	private WebProjectTestEnvironment _projectTestEnvironment;
	private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

	protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        _projectTestEnvironment = 
            new WebProjectTestEnvironment("TestStartupHandler_"+getName());
        _projectTestEnvironment.createProject(false);

        JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(_projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        IResource res = _projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP1 = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(_projectTestEnvironment);
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
		
		JSPModelProcessor  processor = JSPModelProcessor.get(_testJSP1);
		// should have a second reference due to the open editor
		assertEquals(2, processor.getRefCount());
		
		curPage.closeEditor(foundEditor, false);
		
		// closing the editor part should decrement the refcount
		assertEquals(1, processor.getRefCount());
		
		JSPModelProcessor.dispose(_testJSP1);
		assertEquals(0, processor.getRefCount());
	}
	
	/**
	 * Ensure that if an editor with a JSPModelProcessor attached is closed
	 * through the action of closing the enclosing IProject, then StartupHandler
	 * detects this and correctly disposes the model processor.
	 * 
	 * @throws Exception
	 */
	public void testBug196760() throws Exception
	{
		final IWorkbenchPage  curPage = 
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		final IEditorPart editor =	IDE.openEditor(curPage, _testJSP1);
		assertNotNull(editor);
		final IEditorPart foundEditor = ResourceUtil.findEditor(curPage, _testJSP1);
		assertEquals(editor, foundEditor);

		final JSPModelProcessor  processor = JSPModelProcessor.get(_testJSP1);
		// should have a second reference due to the open editor
		assertEquals(2, processor.getRefCount());

		// close project with with user action
		final CloseResourceAction action =
			new CloseResourceAction(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell())
			{
				public void superRun()
				{
					super.run();
					
					int numTries = 0;
					
					while(_projectTestEnvironment.getTestProject().isOpen())
					{
						try {
							numTries++;
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// fall through
						}

						if (numTries > 10)
						{
							throw new RuntimeException("Number of tries exceeeded 10");
						}
					}
				}

				@Override
				public void run() 
				{
					PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
					{
						public void run() 
						{
							superRun();
						}
					});
				}
			};

		action.selectionChanged(new StructuredSelection(_projectTestEnvironment.getTestProject()));
		action.run();

		// if the editor is closed due a project close, ensure that the processor is properly
		// disposed
		assertFalse(_projectTestEnvironment.getTestProject().isOpen());
		JSPModelProcessor.dispose(_testJSP1);
		assertEquals(0, processor.getRefCount());
	}
}
