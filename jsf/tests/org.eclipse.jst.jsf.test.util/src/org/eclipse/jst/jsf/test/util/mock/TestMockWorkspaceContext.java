/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.junit4.TestDataBaseLocation;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceContext;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceRunner;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContext.ZipFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WorkspaceRunner.class)
public class TestMockWorkspaceContext
{
    @WorkspaceContext
    private IWorkspaceContext _wsContext;
    @TestDataBaseLocation
    private File _baseLoc;
    private ZipFileLoader _testZipFileLoader;

    @Before
    public void setUp() throws Exception
    {
        System.out.println(_baseLoc);
        IPath path = new Path(_baseLoc.getAbsolutePath());
        path = path.append("/data/TestProject.zip");
        final File file = path.toFile();
        assertTrue(file.exists());
        _testZipFileLoader = new FileSystemZipLoader(file, "TestProject/");
    }

//    @Test
//    public void testFireWorkspaceEvent()
//    {
//        // TODO:
//        _wsContext.fireWorkspaceEvent(null);
//    }

    @Test
    public void testDispose() throws Exception
    {
        _wsContext.dispose();
    }

    @Test
    public void testGetWorkspace()
    {
        final IWorkspace workspace = _wsContext.getWorkspace();
        assertNotNull(workspace);
        assertSame(workspace, _wsContext.getWorkspace());
    }

    @Test
    public void testGetProject() throws Exception
    {
        final IPath path = new Path("TestProject");
        IProject project = _wsContext.getProject(path);
        assertNull(project);
        // now load the resource
        project = _wsContext.loadProject(path, _testZipFileLoader);
        assertNotNull(project);
    }

    @Test
    public void testGetResource() throws Exception
    {
        final IPath projPath = new Path("TestProject");
        // if we haven't done anything special, then this res shouldn't exist.
        final IPath path = new Path("/WebContent/test.xhtml");
        final IPath fullPath = projPath.append(path);
        IResource resource = _wsContext.getResource(path);
        assertNull(resource);
        // now load the resource; still won't have the resource until it is
        // requested.
        final IProject project = _wsContext.loadProject(projPath,
                _testZipFileLoader);
        assertNotNull(project);

        // TODO: only true for non-plugin
        //        resource = _wsContext.getResource(fullPath);
//        assertNull(resource);
        // now request the resource through the project interface. This should
        // cause it to be known by the context.
        final IFile file = project.getFile(path);
        assertNotNull(file);
        assertEquals(file, _wsContext.getResource(fullPath));
    }

    @Test
    public void testGetFile() throws Exception
    {
        final IPath projPath = new Path("TestProject");
        // if we haven't done anything special, then this res shouldn't exist.
        final IPath path = new Path("/WebContent/test.xhtml");
        final IPath fullPath = projPath.append(path);
        IFile file = _wsContext.getFile(path);
        assertNull(file);
        // now load the resource; still won't have the resource until it is
        // requested.
        final IProject project = _wsContext.loadProject(projPath,
                _testZipFileLoader);
        assertNotNull(project);
        // TODO:
//        file = _wsContext.getFile(fullPath);
//        assertNull(file);
        // now request the resource through the project interface. This should
        // cause it to be known by the context.
        final IFile fileFromProject = project.getFile(path);
        assertNotNull(fileFromProject);
        assertEquals(fileFromProject, _wsContext.getFile(fullPath));
    }

    @Test
    public void testCreateProjectString()
    {
        final IProject project = _wsContext.createProject("Foo");
        assertNotNull(project);
        final IProject project2 = _wsContext.createProject("Foo");
        assertNotNull(project2);
        // two projects created with the same baseId should have unique
        // paths as taken care off by the context.
        assertFalse(project.equals(project2));
    }

    @Test
    public void testCreateProjectIPath()
    {
        final IProject project = _wsContext.createProject(new Path("Foo"));
        assertNotNull(project);
        assertEquals("Foo", project.getName());
    }

    @Test
    public void testLoadProjectIPathZipFile() throws Exception
    {
        final IProject project = _wsContext.loadProject(
                new Path("TestProject1"), _testZipFileLoader);
        assertNotNull(project);
        assertTrue(project.exists());
        final IFile file = project.getFile(new Path("WebContent/test.xhtml"));
        assertNotNull(file);
        assertTrue(file.exists());
        final InputStream contents = file.getContents();
        final ByteArrayOutputStream data = JSFTestUtil
                .loadFromInputStream(contents);
        assertEquals(367, data.toByteArray().length);
    }
}
