package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.jsp.JSPModelProcessor;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestJSPModelProcessor extends TestCase 
{
    private IFile _testJSP1;
    private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestJSPModelProcessor_"+getName());
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

    @Override
    protected void tearDown() throws Exception {
    }

    public void testGetAndDispose() throws Exception
    {
        JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);
        assertEquals(1, processor.getRefCount());
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(0, processor.getRefCount());
    }

    public void testGetAndDisposeMultiple() throws Exception
    {
        JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);
        assertEquals(1, processor.getRefCount());

        // get again
        processor = JSPModelProcessor.get(_testJSP1);
        assertEquals(2, processor.getRefCount());
        // and again
        processor = JSPModelProcessor.get(_testJSP1);
        assertEquals(3, processor.getRefCount());
        // and dispose once
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(2, processor.getRefCount());

        // and reacquire
        processor = JSPModelProcessor.get(_testJSP1);
        assertEquals(3, processor.getRefCount());

        // dispose twice
        JSPModelProcessor.dispose(_testJSP1);
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(1, processor.getRefCount());

        // one final dispose
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(0, processor.getRefCount());

        // exceed by one
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(0, processor.getRefCount());

        // acquire
        processor = JSPModelProcessor.get(_testJSP1);
        assertEquals(1, processor.getRefCount());

        // dispose single reference
        JSPModelProcessor.dispose(_testJSP1);
        assertEquals(0, processor.getRefCount());
    }

    public void testGetMapForScope() throws Exception
    {
        // if we not refreshed yet, then should be no symbols
        JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);

        try
        {
            Map<Object, ISymbol> scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
            assertTrue(scopeMap.isEmpty());

            scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_SESSION_STRING);
            assertTrue(scopeMap.isEmpty());

            scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_APPLICATION_STRING);
            assertTrue(scopeMap.isEmpty());

            scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_NONE_STRING);
            assertTrue(scopeMap.isEmpty());
        }
        finally
        {
            JSPModelProcessor.dispose(_testJSP1);
            assertEquals(0, processor.getRefCount());
        }
    }

    public void testRefreshAndGet() throws Exception
    {
        // if we not refreshed yet, then should be no symbols
        JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);

        try
        {
            Map<Object, ISymbol> scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
            assertTrue(scopeMap.isEmpty());

            processor.refresh(false);

            // after refresh we should have a symbol for the loadBundle and the dataTable
            scopeMap = 
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
            assertFalse(scopeMap.isEmpty());
            assertEquals(2, scopeMap.size());
        }
        finally
        {
            JSPModelProcessor.dispose(_testJSP1);
            assertEquals(0, processor.getRefCount());
        }
    }

    public void testFileDeletion_RegressionBug199480() throws Exception
    {
        // Regression for bug 199480
        // ensure that the deletion of a resource with a JSPModelProcessor
        // on it without an editor close event is still disposed of due
        // to the resource change event.
        // if we not refreshed yet, then should be no symbols
        JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);

        _testJSP1.delete(true, null);
        // file is deleted, so the processor should dispose itself on the 
        // resource change event
        assertTrue(processor.isDisposed());
        assertEquals(0, processor.getRefCount());
        processor.refresh(true);
    }
}
