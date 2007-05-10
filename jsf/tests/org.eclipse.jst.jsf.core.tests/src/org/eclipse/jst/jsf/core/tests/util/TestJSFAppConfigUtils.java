/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.util;

import junit.framework.TestCase;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
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
        testEnv.createProject(true);
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
            } catch (ExecutionException e) {
                // propagate
                throw new RuntimeException(e);
            }
        }

        return testEnv;
    }
}
