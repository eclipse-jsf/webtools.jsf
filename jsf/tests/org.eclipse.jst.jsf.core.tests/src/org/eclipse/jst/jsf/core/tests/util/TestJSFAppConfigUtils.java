package org.eclipse.jst.jsf.core.tests.util;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Unit test for JSFAppConfigUtils
 * 
 * @author cbateman
 *
 */
public class TestJSFAppConfigUtils extends TestCase 
{

    protected void setUp() throws Exception {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
    }

    public void testIsJSFProject()
    {
        WebProjectTestEnvironment env = createTestEnv(IJSFCoreConstants.FACET_VERSION_1_1, getName());
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject()));
    }
    
    public void testIsNotJSFProject()
    {
        WebProjectTestEnvironment env = createTestEnv(null, getName());
        assertFalse(JSFAppConfigUtils.isValidJSFProject(env.getTestProject()));
    }
    
    public void testIsJSFProjectByVersion()
    {
        WebProjectTestEnvironment env = createTestEnv(IJSFCoreConstants.FACET_VERSION_1_1,getName()+"1");
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(),IJSFCoreConstants.JSF_VERSION_1_0));
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(),IJSFCoreConstants.JSF_VERSION_1_1));
        assertFalse(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(), IJSFCoreConstants.JSF_VERSION_1_2));
        
        env = createTestEnv(IJSFCoreConstants.FACET_VERSION_1_2, getName()+"2");
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(),IJSFCoreConstants.JSF_VERSION_1_0));
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(),IJSFCoreConstants.JSF_VERSION_1_1));
        assertTrue(JSFAppConfigUtils.isValidJSFProject(env.getTestProject(), IJSFCoreConstants.JSF_VERSION_1_2));
    }
    
    private WebProjectTestEnvironment createTestEnv(String facetVersion, String name)
    {
        WebProjectTestEnvironment testEnv = 
            new WebProjectTestEnvironment(this.getClass().getName()+"_"+name);
        testEnv.createProject();
        assertNotNull(testEnv);       
        assertNotNull(testEnv.getTestProject());
        assertTrue(testEnv.getTestProject().isAccessible());

        if (facetVersion != null)
        {
            JSFFacetedTestEnvironment jsfFacedEnv = 
                new JSFFacetedTestEnvironment(testEnv);
            
            try
            {
                jsfFacedEnv.initialize(facetVersion);
            }
            catch (CoreException ce)
            {
                // propagate
                throw new RuntimeException(ce);
            }
        }

        return testEnv;
    }
}
