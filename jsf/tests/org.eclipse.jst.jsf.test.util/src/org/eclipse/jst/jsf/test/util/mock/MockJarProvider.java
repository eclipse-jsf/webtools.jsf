package org.eclipse.jst.jsf.test.util.mock;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.resource.IJarProvider;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public final class MockJarProvider implements IJarProvider
{
    private final List<JarFile> _jarFiles;

    public MockJarProvider(final JarFile jarFile)
    {
        this(Collections.singletonList(jarFile));
    }

    public MockJarProvider(final MockProject project) throws CoreException
    {
        final List<JarFile> jarFiles = new ArrayList<JarFile>();
        project.accept(new IResourceVisitor()
        {
            public boolean visit(final IResource resource) throws CoreException
            {
                if (resource.getType() == IResource.FILE
                        && "jar".equals(resource.getFullPath()
                                .getFileExtension()))
                {
                    final JarFile jarFile = getJar((IFile) resource);
                    jarFiles.add(jarFile);
                }
                return true;
            }
        });
        _jarFiles = jarFiles;
    }

    public MockJarProvider(final List<JarFile> jarFiles)
    {
        _jarFiles = jarFiles;
    }

    public Collection<JarFile> getJars(final IProject project)
    {
        return _jarFiles;
    }

    public static JarFile getJar(final String fileName)
            throws AssertionFailedError
    {
        final File javaFile = new File(fileName);
        assertTrue(javaFile.exists());
        JarFile jarFile;
        try
        {
            jarFile = new JarFile(javaFile);
        } catch (final IOException e)
        {
            final AssertionFailedError error = new AssertionFailedError();
            error.setStackTrace(e.getStackTrace());
            throw error;
        }
        return jarFile;
    }

    public static JarFile getJar(final IFile jarFile)
    {
        FileOutputStream outStream = null;
        try
        {
            final InputStream inStream = jarFile.getContents();
            final File tempFile = File.createTempFile(jarFile.getName() + "_"
                    + System.currentTimeMillis(), null);
            tempFile.deleteOnExit();
            outStream = new FileOutputStream(tempFile);
            JSFTestUtil.saveToFileSystem(inStream, tempFile.toURI());
            return new JarFile(tempFile);
        } catch (final CoreException e)
        {
            throw new AssertionFailedError(e.getLocalizedMessage());
        } catch (final IOException e)
        {
            throw new AssertionFailedError(e.getLocalizedMessage());
        } finally
        {
            if (outStream != null)
            {
                try
                {
                    outStream.close();
                } catch (final IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}