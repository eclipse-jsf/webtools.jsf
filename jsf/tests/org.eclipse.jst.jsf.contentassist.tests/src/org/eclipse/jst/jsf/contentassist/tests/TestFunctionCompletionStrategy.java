package org.eclipse.jst.jsf.contentassist.tests;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Basic unit test for class FunctionCompletionStrategy
 * @author cbateman
 *
 */
public class TestFunctionCompletionStrategy extends TestCase {
    private WebProjectTestEnvironment       _testEnv;
    
    protected void setUp() throws Exception 
    {
        super.setUp();

        _testEnv = new WebProjectTestEnvironment(getClass().getName()+"_"+getName());
        _testEnv.createProject();
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());
        
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize();
        
        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/faces-config_bug149743.xml.data", 
                                      "/WEB-INF/faces-config.xml");
        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/bug_149743.jsp.data",
                                      "/bug_149743.jsp");
        
        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(), 
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
    }

    /**
     * Sanity check
     */
//    public void testSanity()
//    {
//    }

}
