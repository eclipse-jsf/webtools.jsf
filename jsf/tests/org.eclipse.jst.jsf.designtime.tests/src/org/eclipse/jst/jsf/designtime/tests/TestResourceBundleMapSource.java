/*******************************************************************************
 * Copyright (c) 2006, 2010 Oracle Corporation.
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
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
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

    // private static final String BUNDLE2_PATH = "bundles";
    // private static final String BUNDLE2_NAME = "bundle2.properties";

    private IProject            _project1;
    private IProject            _project2;
    private IProject            _project3;
    private IFile               _bundle1;

    // private IFile _bundle2;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        List<IResource> resources =
            initProject("BundleResourceTestProject1_" + getName(),
                    "/testdata/bundle1.resources.data", BUNDLE1_PATH,
                    BUNDLE1_NAME);
        _bundle1 = (IFile) resources.get(0);
        _project1 = (IProject) resources.get(1);

        resources =
            initProject("BundleResourceTestProject2_" + getName(),
                    "/testdata/bundle2.resources.data", BUNDLE1_PATH,
                    BUNDLE1_NAME);
        // _bundle2 = (IFile) resources.get(0);
        _project2 = (IProject) resources.get(1);

        resources =
            initProject("BundleResourceTestProject3_" + getName(),
                    "/testdata/bundle1.resources.data", BUNDLE1_PATH,
                    BUNDLE1_NAME);
        _project3 = (IProject) resources.get(1);
    }

    private List<IResource> initProject(final String projectName,
            final String dataFilePath, final String bundlePath,
            final String bundleName) throws Exception
            {
        final List<IResource> resources = new ArrayList<IResource>();
        final ProjectTestEnvironment testEnv =
            new ProjectTestEnvironment(projectName);
        testEnv.createProject(false);
        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(testEnv);
        final TestFileResource input = new TestFileResource();
        input
        .load(DesignTimeTestsPlugin.getDefault().getBundle(),
                dataFilePath);
        resources.add(jdtTestEnv.addResourceFile("src",
                new ByteArrayInputStream(input.toBytes()), bundlePath,
                bundleName));

        final IProject project = testEnv.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());
        resources.add(project);
        return resources;
            }

    /**
     * Basic sanity check that properties files can be loaded and contain what's
     * expected
     */
    public void testSanity() throws Exception
    {
        Map<?, ?> map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project1, "bundles.bundle1");
        assertNotNull(map);
        assertEquals(map.size(), 3);

        map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project2, "bundles.bundle1");
        assertNotNull(map);
        assertEquals(map.size(), 3);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFTestUtil.safeDelete(_project1, 25, 100);
        JSFTestUtil.safeDelete(_project2, 25, 100);
        JSFTestUtil.safeDelete(_project3, 25, 100);
    }

    /**
     * Verify the expected contents of bundle1 in project1
     */
    public void testContentsProject1Bundle1() throws Exception
    {
        final Map<?, ?> map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project1, "bundles.bundle1");
        assertTrue(map.containsKey("prop1"));
        assertEquals("blah", map.get("prop1"));
        assertTrue(map.containsKey("one.dot"));
        assertEquals("blah1", map.get("one.dot"));
        assertTrue(map.containsKey("two.dot.property"));
        assertEquals("blah3", map.get("two.dot.property"));
    }

    /**
     * Verify the expected contents of bundle1 in project2
     */
    public void testContentsProject2Bundle1() throws Exception
    {
        final Map<?, ?> map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project2, "bundles.bundle1");
        assertTrue(map.containsKey("x_prop1"));
        assertEquals("x_blah", map.get("x_prop1"));
        assertTrue(map.containsKey("x_one.dot"));
        assertEquals("x_blah1", map.get("x_one.dot"));
        assertTrue(map.containsKey("x_two.dot.property"));
        assertEquals("x_blah3", map.get("x_two.dot.property"));
    }

    /**
     * Regression test of https://bugs.eclipse.org/bugs/show_bug.cgi?id=196452.
     * 
     * This verifies that if the bundle file is deleted then a reload on the
     * bundle correctly cleans up the map
     * 
     * @throws CoreException
     * @throws IOException
     * @throws JavaModelException
     */
    public void testBundleDelete() throws JavaModelException, IOException,
    CoreException
    {
        // test the initial state, before outside meddling
        final Map<?, ?> map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project1, "bundles.bundle1");
        assertTrue(map.containsKey("prop1"));
        assertEquals("blah", map.get("prop1"));
        assertTrue(map.containsKey("one.dot"));
        assertEquals("blah1", map.get("one.dot"));
        assertTrue(map.containsKey("two.dot.property"));
        assertEquals("blah3", map.get("two.dot.property"));

        // now fiddle with the file
        final File bundleFile = _bundle1.getLocation().toFile();
        assertTrue(bundleFile.delete());
        _bundle1.refreshLocal(IResource.DEPTH_INFINITE, null);

        // the condition we are testing for is that the file is no
        // longer accessible but the map tolerates this by becoming empty
        assertFalse(_bundle1.isAccessible());

        // the map should now return empty and querying keys should
        // return null
        assertTrue(map.isEmpty());
        assertNull(map.get("prop1"));
        assertNull(map.get("one.dot"));
        assertNull(map.get("two.dot.property"));
    }

    /**
     * Regression test of https://bugs.eclipse.org/bugs/show_bug.cgi?id=196452.
     * 
     * This verifies that if the bundle file is modified outside of Eclipse,
     * then a reload on the bundle doesn't throw an out of sync exception.
     * 
     * @throws CoreException
     * @throws IOException
     * @throws JavaModelException
     */
    public void testProjectCloseCleanup() throws Exception
    {
        // test the initial state, before outside meddling
        Map<?, ?> map =
            ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                    _project3, "bundles.bundle1");
        assertTrue(map.containsKey("prop1"));
        assertEquals("blah", map.get("prop1"));
        assertTrue(map.containsKey("one.dot"));
        assertEquals("blah1", map.get("one.dot"));
        assertTrue(map.containsKey("two.dot.property"));
        assertEquals("blah3", map.get("two.dot.property"));

        // now close the project
        // this will trigger a NOT_ACCESSIBLE event on the project
        _project3.close(null);

        // map should now be safely disposed
        assertEquals(0, map.size());

        boolean expectedExceptionThrown = false;

        try
        {
            map =
                ResourceBundleMapSourceFactory.getResourceBundleMapSource(
                        _project3, "bundles.bundle1");
        }
        catch (final CoreException ce)
        {
            // this code should run since _project3 is closed
            // and therefore inaccessible
            expectedExceptionThrown = true;
        }

        assertTrue(expectedExceptionThrown);

    }
}
