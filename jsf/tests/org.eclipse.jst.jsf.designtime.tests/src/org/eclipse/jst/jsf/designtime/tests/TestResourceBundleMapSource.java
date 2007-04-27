/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSourceFactory;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;

/**
 * Test cases for ResourceBundleMapSource
 * 
 * @author cbateman
 *
 */
public class TestResourceBundleMapSource extends TestCase 
{
    
    private static final String BUNDLE1_PATH = "bundles";
    private static final String BUNDLE1_NAME = "bundle1.properties";
    
//    private static final String BUNDLE2_PATH = "bundles";
//    private static final String BUNDLE2_NAME = "bundle2.properties";

    private IProject   _project1;
    private IProject   _project2;
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        
        _project1 = initProject("BundleResourceTestProject1", 
                                "/testdata/bundle1.resources.data",
                                BUNDLE1_PATH, BUNDLE1_NAME);
        _project2 = initProject("BundleResourceTestProject2", 
                                "/testdata/bundle2.resources.data",
                                BUNDLE1_PATH, BUNDLE1_NAME);
    }

    private IProject initProject(final String projectName, final String dataFilePath,
                                 final String bundlePath, final String bundleName) 
                                    throws Exception
    {
        final ProjectTestEnvironment  testEnv = new ProjectTestEnvironment(projectName);
        testEnv.createProject(false);
        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(testEnv);
        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
                   dataFilePath);
        jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(input.toBytes()), 
                                    bundlePath, bundleName);
        IProject project = testEnv.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());
        return project;
    }
    
    /**
     * Basic sanity check that properties files can be loaded and contain
     * what's expected
     */
    public void testSanity()
    {
        try
        {
            Map  map = ResourceBundleMapSourceFactory.getResourceBundleMapSource(_project1, "bundles.bundle1");
            assertNotNull(map);
            assertEquals(map.size(), 3);
            
            map = ResourceBundleMapSourceFactory.getResourceBundleMapSource(_project2, "bundles.bundle1");
            assertNotNull(map);
            assertEquals(map.size(), 3);
        }
        catch (Exception e)
        {
            fail(e.getLocalizedMessage());
        }
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        _project1.delete(true, null);
        _project2.delete(true, null);
    }

    /**
     * Verify the expected contents of bundle1 in project1
     */
    public void testContentsProject1Bundle1()
    {
        try
        {
            Map  map = ResourceBundleMapSourceFactory.getResourceBundleMapSource(_project1, "bundles.bundle1");
            assertTrue(map.containsKey("prop1"));
            assertEquals("blah", map.get("prop1"));
            assertTrue(map.containsKey("one.dot"));
            assertEquals("blah1", map.get("one.dot"));
            assertTrue(map.containsKey("two.dot.property"));
            assertEquals("blah3", map.get("two.dot.property"));
        }
        catch (Exception e)
        {
            fail(e.getLocalizedMessage());
        }
    }
    
    /**
     * Verify the expected contents of bundle1 in project2
     */
    public void testContentsProject2Bundle1()
    {
        try
        {
            Map  map = ResourceBundleMapSourceFactory.getResourceBundleMapSource(_project2, "bundles.bundle1");
            assertTrue(map.containsKey("x_prop1"));
            assertEquals("x_blah", map.get("x_prop1"));
            assertTrue(map.containsKey("x_one.dot"));
            assertEquals("x_blah1", map.get("x_one.dot"));
            assertTrue(map.containsKey("x_two.dot.property"));
            assertEquals("x_blah3", map.get("x_two.dot.property"));
        }
        catch (Exception e)
        {
            fail(e.getLocalizedMessage());
        }
    }

}
