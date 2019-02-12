/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
 package org.eclipse.jst.jsf.test.util.sanity;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Sanity test for web project test environment
 * 
 * @author cbateman
 *
 */
public class TestWebProjectTestEnvironment extends TestCase 
{
    /**
     * Test project creation
     */
    public void testCreateProject()
    {
//      InternetPlugin iPlugin = InternetPlugin.getInstance();
//      IPreferenceStore store = iPlugin.getPreferenceStore();
        
        WebProjectTestEnvironment testEnv = new WebProjectTestEnvironment("TestProject1");
        testEnv.createProject(false);
        assertTrue(testEnv.isProjectCreated());
        
        IProject project = testEnv.getTestProject();
        
        assertNotNull(project);
        assertTrue(project.isAccessible());
    }
    
    /**
     * Test creating a web project and adding a faces-config.xml file to it
     */
    public void testAddFileToWebRoot()
    {
        WebProjectTestEnvironment testEnv = new WebProjectTestEnvironment("TestProject2");
        testEnv.createProject(false);
        assertTrue(testEnv.isProjectCreated());
        
        try
        {
            testEnv.loadResourceInWebRoot(Activator.getDefault().getBundle(), 
                                       "/testdata/faces-config.xml.data", 
                                       "/WEB-INF/faces-config.xml");
        }
        catch (Exception e)
        {
            fail(e.getLocalizedMessage());
        }
    }

}
