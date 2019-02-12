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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public final class MockJarProvider
{
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