package org.eclipse.jst.jsf.core.tests.sanity;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

import junit.framework.TestCase;

/**
 * This is a sanity test for the utility calss JSFFactedTestEnvironment and is
 * not mean to be run as part of the application test suite.
 * 
 * @author cbateman
 *
 */
public class TestJSFFacetedTestEnvironment extends TestCase 
{
    /**
     * Test creation of a basic JSF 1.1 faceted test environment
     * @throws Exception
     */
    public void testVersion_1_1_Facet() throws Exception
    {
        WebProjectTestEnvironment  webTestEnv = new WebProjectTestEnvironment(getName());
        webTestEnv.createProject();
        
        JSFFacetedTestEnvironment  testEnv = new JSFFacetedTestEnvironment(webTestEnv);
        testEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }
    
    /**
     * Test creation of a basic JSF 1.1 faceted test environment
     * @throws Exception
     */
    public void testVersion_1_2_Facet() throws Exception
    {
        WebProjectTestEnvironment  webTestEnv = new WebProjectTestEnvironment(getName());
        webTestEnv.createProject();
        
        JSFFacetedTestEnvironment  testEnv = new JSFFacetedTestEnvironment(webTestEnv);
        testEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_2);
    }
}
