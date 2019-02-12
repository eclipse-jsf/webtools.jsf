/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.validation.ValidationFramework;
import org.osgi.framework.Bundle;

/**
 * Test utility to create project and its files.
 * 
 * @author Yang Liu, Xiao-guang Zhang
 * 
 * @version
 */
public class TestUtil
{
    /**
     * 
     * @param prjname
     * @param path
     *            relative to this plugin's root folder.
     * @return
     * @throws Exception
     */
    public static IProject createProjectFromZip(Bundle bundle, String prjname,
            String path) throws Exception
    {
        URL url = FileLocator.find(bundle, new Path(path), null);
        // if this fails, it most likely that path is wrong.
        Assert.assertNotNull(url);
        return createProjectFromZip(prjname, url);
    }

    public static IProject createProjectFromZip(File file, String prjname)
            throws Exception
    {
        URL url = file.toURL();
        // if this fails, it most likely that path is wrong.
        Assert.assertNotNull(url);
        return createProjectFromZip(prjname, url);
    }

    /**
     * build a project
     * 
     * @param project
     * @param monitor
     */
    public static void buildProject(IProject project, IProgressMonitor monitor)
    {
        try
        {
            project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
        } catch (CoreException e)
        {
            e.printStackTrace(System.err);
        }
    }

    /**
     * expand the zip stream into the specified folder.
     * 
     * @param url
     * @param dir
     * @throws Exception
     */
    private static void expandZip(URL url, IContainer dir) throws Exception
    {
        ZipInputStream zis = null;
        try
        {
            // first, count number of items in zip file
            zis = new ZipInputStream(url.openStream());
            String prefix = getPrefix(zis);
            zis = new ZipInputStream(url.openStream());
            ZipEntry ze = zis.getNextEntry();
            while (ze != null)
            {
                String name = ze.getName();
                if (!name.startsWith(prefix))
                {
                    ze = zis.getNextEntry();
                    continue;
                }
                name = name.substring(prefix.length());
                if (ze.isDirectory())
                {
                    IFolder folder = dir.getFolder(new Path(name));
                    if (!folder.exists())
                    {
                        ensurePath(folder);
                        folder.create(true, true, null);
                    }
                } else
                {
                    IFile file = dir.getFile(new Path(name));
                    ensurePath(file);
                    // use ZipStreamWrapper to prevent zis being closed
                    if (file.exists())
                    {
                        file.setContents(new ZipStreamWrapper(zis),
                                IResource.NONE, null);
                    } else
                    {
                        file.create(new ZipStreamWrapper(zis), true, null);
                    }
                }
                ze = zis.getNextEntry();
            }
        } finally
        {
            try
            {
                if (zis != null)
                    zis.close();
            } catch (Exception ex)
            {
                // ignore
            }
        }
    }

    private static String getPrefix(ZipInputStream zipStream)
            throws IOException
    {
        ZipEntry ze = zipStream.getNextEntry();
        while (ze != null)
        {
            String name = ze.getName();
            if (name != null && name.endsWith(".project") && !ze.isDirectory())
            {
                int index = name.lastIndexOf(".project");
                return name.substring(0, index);
            }
            ze = zipStream.getNextEntry();
        }
        // if we get to here, then nothing to prepend
        return "";
    }

    /**
     * @param file
     * @throws CoreException
     */
    private static void ensurePath(IResource file) throws CoreException
    {
        IContainer container = file.getParent();
        if (!container.exists())
        {
            ensurePath(container);
            ((IFolder) container).create(true, true, null);
        }
    }

    /**
     * this method will copy file from the sourcePath folder of this plugin into
     * the target path related with the destination project.
     * 
     * @param project
     * @param sourcePath
     *            - Source path, must be a relative path to test plugin
     * @param targetPath
     *            - Target path, must be relative path to eclispe project.
     * 
     * @return
     */
    public static IFile copyFile(Bundle bundle, IProject project,
            String targetPath, String sourcePath) throws Exception
    {
        URL url = FileLocator.find(bundle, new Path(sourcePath), null);
        InputStream stream = url.openStream();
        IFile file = null;
        IPath path = new Path(sourcePath);
        if (targetPath != null && targetPath.length() > 0)
        {
            IFolder folder = project.getFolder(targetPath);
            file = folder.getFile(path.lastSegment());
        } else
        {
            file = project.getFile(path.lastSegment());
        }
        if (!file.exists())
        {
            ensurePath(file);
            file.create(stream, true, null);
        } else
        {
            file.setContents(stream, IFile.FORCE, null);
        }
        return file;
    }

    /**
     * this method will create page file from the "pages" folder of this plugin
     * into the webroot folder of the destination project.
     * 
     * @param filePath
     *            - file path, must be relative path to destination project.
     * 
     * @return
     */
    public static IFile createFile(IProject project, String filePath,
            String content) throws Exception
    {
        IFile file = project.getFile(filePath);
        ensurePath(file);
        ByteArrayInputStream stream = new ByteArrayInputStream(
                content.getBytes());
        file.create(stream, true, null);
        return file;
    }

    /**
     * this method will get the page file from the "pages" folder of this plugin
     * as a string.
     * 
     * @param path
     *            - related with plugin
     * @return
     * @throws Exception
     */
    public static String getFileAsString(String path) throws Exception
    {
        URL url = FileLocator.find(Activator.getDefault().getBundle(),
                new Path(path), null);
        InputStream stream = url.openStream();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        char[] temp = new char[256];
        int count;
        while ((count = reader.read(temp)) > 0)
        {
            buffer.append(temp, 0, count);
        }
        reader.close();
        stream.close();
        return buffer.toString();
    }

    /**
     * create a project from a zip file.
     * 
     * @param prjname
     * @param zipStream
     * @throws Exception
     */
    private static IProject createProjectFromZip(final String prjname,
            final URL url) throws Exception
    {
        final IProject[] holder = new IProject[1];
        IWorkspaceRunnable r = new IWorkspaceRunnable()
        {
            public void run(IProgressMonitor monitor) throws CoreException
            {
                IProject prj = ResourcesPlugin.getWorkspace().getRoot()
                        .getProject(prjname);
                if (!prj.exists())
                {
                    prj.create(null);
                }
                prj.open(null);
                ValidationFramework.getDefault().suspendValidation(prj, true);
                try
                {
                    expandZip(url, prj);
                } catch (Exception ex)
                {
                    throw new CoreException(new Status(0, Activator.PLUGIN_ID,
                            0, ex.getMessage(), ex));
                }
                holder[0] = prj;
            }
        };
        ResourcesPlugin.getWorkspace().run(r, null);
        return holder[0];
    }

    public static String removeAllWhitespace(String s)
    {
        StringBuffer buffer = new StringBuffer(s.length());
        for (int i = 0, length = s.length(); i < length; i++)
        {
            if (!Character.isWhitespace(s.charAt(i)))
            {
                buffer.append(s.charAt(i));
            }
        }
        return buffer.toString();
    }

    /**
     * remove resource, following schedule rule.
     * 
     * @param prj
     * @throws CoreException
     */
    public static void removeResource(final IResource prj) throws CoreException
    {
        if (prj instanceof IFile)
        {
            ((IFile) prj).delete(true, false, null);
            return;
        }
        Job job = new Job("DeleteProject")
        {
            protected IStatus run(IProgressMonitor monitor)
            {
                try
                {
                    prj.delete(true, monitor);
                } catch (CoreException e)
                {
                    return Status.CANCEL_STATUS;
                }
                return Status.OK_STATUS;
            }
        };
        job.setPriority(Job.SHORT);
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();
        // wait
        try
        {
            job.join();
        } catch (InterruptedException e)
        {
        }
    }

    private final static boolean PROJECT_IS_OPEN = true;

    public static boolean verifyProjectStatus(final IProject project,
            final boolean isOpenCondition, final int waitTimeInMs)
    {
        return TestUtil.waitForCondition(
                createProjectStatusCondition(project, isOpenCondition),
                waitTimeInMs, 20);
    }

    public static TestCondition createProjectStatusCondition(
            final IProject project, final boolean isOpenCondition)
    {
        return new TestCondition()
        {
            @Override
            public boolean test()
            {
                return project.isOpen() == isOpenCondition;
            }
        };
    }

    public static boolean openProject(final IProject project,
            final int waitTimeInMs) throws CoreException
    {
        project.open(null);
        return verifyProjectStatus(project, PROJECT_IS_OPEN, waitTimeInMs);
    }

    public static boolean closeProject(final IProject project,
            final int waitTimeInMs) throws CoreException
    {
        project.close(null);
        return verifyProjectStatus(project, !PROJECT_IS_OPEN, waitTimeInMs);
    }

    public static boolean waitForCondition(final TestCondition condition,
            final int maxTime, final int numIntervals)
    {
        int curIteration = 0;
        int waitPerInterval = maxTime / numIntervals;
        if (waitPerInterval < 1)
        {
            Assert.fail("Your wait interval is less than 1");
        }
        do
        {
            if (condition.test())
            {
                return true;
            }
            try
            {
                Thread.sleep(waitPerInterval);
            } catch (InterruptedException e)
            {
                // ignore.
            }
        } while (curIteration++ < numIntervals);
        // if we get to here, then the condition was not satisified in the
        // time alloted
        return false;
    }

    public static abstract class TestCondition
    {
        public abstract boolean test();
    }

    public static class CompositeTestCondition extends TestCondition
    {
        protected final List<? extends TestCondition> _conditions;

        public CompositeTestCondition(
                final List<? extends TestCondition> conditions)
        {
            super();
            _conditions = Collections
                    .unmodifiableList(new ArrayList<TestCondition>(conditions));
        }

        public void dispose()
        {
            // do nothing by default
        }

        @Override
        public boolean test()
        {
            boolean isSatisfied = true;
            for (final TestCondition condition : _conditions)
            {
                isSatisfied &= condition.test();
            }
            return isSatisfied;
        }
    }
}
