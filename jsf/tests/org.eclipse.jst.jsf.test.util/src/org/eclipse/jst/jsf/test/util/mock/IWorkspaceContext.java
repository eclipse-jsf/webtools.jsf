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

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.osgi.framework.Bundle;

/**
 * @author cbateman
 * 
 */
public interface IWorkspaceContext
{
    public abstract void init() throws Exception;
    public abstract void dispose() throws Exception;

    public abstract IWorkspace getWorkspace();

    /**
     * @param path
     * @return a resource for path only if it exists null otherwise. This is a
     *         departure from normal ws handle ops that always answer non-null
     *         and you have to check if something exists.
     */
    public abstract IResource getResource(final IPath path);

    /**
     * Fully equivalent to (IFile) getResource(path).
     * 
     * @param path
     * @return the IFile or null if none for path.
     * @throws ClassCastException
     *             if path exists but doesn't not point to a file.
     */
    public abstract IFile getFile(final IPath path);

    /**
     * Equivalent to (IProject) getResource(path).
     * 
     * @param path
     * @return the project for the path or null if it isn't currently in the
     *         context.
     */
    public abstract IProject getProject(final IPath path);

    /**
     * @return a mock project with a generated name that is guaranteed not to
     *         conflict with any that already exist in this context.
     */
    public abstract IProject createProject(final String baseId);

    public abstract IProject createProject(final IPath path);

    public IFile attachFile(IProject project, IPath projectRelativePath,
            File file) throws Exception;

    /**
     * @param path
     * @param zipFileLoader
     * @return an IProject loaded from zipFileLoader into the path provided.
     * @throws Exception
     */
    public abstract IProject loadProject(final IPath path,
            final ZipFileLoader zipFileLoader) throws Exception;

    /**
     * @param projectPath the path of the new project
     * @param bundle the plugin containing the zip
     * @param pathToZip relative to the plugin's root folder
     * @return an IProject loaded from a zip in the plugin and 
     *         into the path provided.
     * @throws Exception
     */
    public abstract IProject loadProject(final IPath projectPath,
            final Bundle bundle, final String pathIntoZip) throws Exception;

    /**
     * Method that implementers may choose to noop.
     * 
     * @param project
     * @throws Exception 
     */
    public abstract void ensureAllMembers(final IProject project) throws Exception;
    public abstract static class ZipFileLoader
    {
        public abstract ZipFile getZipFile() throws ZipException, IOException;
        public abstract File  getFile();
        public abstract String getPathInZip();
    }
}