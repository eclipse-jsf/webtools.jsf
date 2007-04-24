package org.eclipse.jst.jsf.core.tests.tagmatcher;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;



public class BaseTagMatcherTestCase extends TestCase {
    /**
     * The dynamic web project test environment
     */
    protected WebProjectTestEnvironment  _testEnv;
    /**
     * A handle to the Java project test environment
     */
    protected JDTTestEnvironment         _jdtTestEnv;
    /**
     * Name of the test data file containing the JSP source for this test
     */
    protected String                  _srcFileName;
    /**
     * Name of the file and path where the JSP source should be put in the
     * test project
     */
    protected String                  _destFileName;
    
    /**
     * The file handle to the JSP in the workspace
     */
    protected IFile                   _testJSP;
    /**
     * The SSE structured model for the JSP
     */
    protected IStructuredModel        _structuredModel;
    /**
     * The SSE structured document for the JSP
     */
    protected IStructuredDocument     _structuredDocument;

    protected void setUp() throws Exception    
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");
        
        _testEnv = new WebProjectTestEnvironment("ELValidationTest_"+this.getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        _testJSP = (IFile) _testEnv.loadResourceInWebRoot
            (TestsPlugin.getDefault().getBundle(),
                    _srcFileName, _destFileName);

        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();
    }
    
    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }
        _testEnv.getTestProject().close(null);
    }

}
