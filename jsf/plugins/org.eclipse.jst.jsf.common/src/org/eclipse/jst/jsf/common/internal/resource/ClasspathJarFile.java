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
package org.eclipse.jst.jsf.common.internal.resource;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * Represents a jar that is available on the classpath.  Encapsulates the jar
 * as well as information about where it is provided from.
 * 
 * The jar itself may have been deleted in which case, exists() will return false.
 * 
 * @author cbateman
 *
 */
public class ClasspathJarFile
{
    private final IProject _project;
    private final IPath _iPath;

    /**
     * @param project
     * @param iPath 
     */
    public ClasspathJarFile(final IProject project, final IPath iPath)
    {
        _project = project;
        _iPath = iPath;
    }

    /**
     * @return the project that this jar file is being referenced from.   Note that this
     * is not necessarily the same as the project that owns it (if it is in the
     * workspace) and a jar file may be referenced by more than one project.
     */
    public IProject getProject()
    {
        return _project;
    }

    /**
     * @return the class path entry
     */
    public IPath getPath()
    {
        return _iPath;
    }
    
    /**
     * @return a new JarFile for the underlying jar.  Caller MUST close the jar when
     * done.
     */
    public JarFile getJarFile()
    {
        JarFile jarFileFromCPE = null;
        try
        {
            jarFileFromCPE = getJarFileFromCPE(_iPath, _project.getWorkspace());
        } catch (final IOException e)
        {
            JSFCommonPlugin.log(e);
        }
        return jarFileFromCPE;
    }
    
    /**
     * TODO: Merge into JSFAppConfigUtils.
     * 
     * @param entry
     * @param workspace 
     * @return
     */
    private JarFile getJarFileFromCPE(final IPath entry, final IWorkspace workspace)
            throws IOException
    {
        IPath path = _iPath;
        if (path.getFileExtension() != null
                && path.getFileExtension().length() > 0)
        {
            final IWorkspaceRoot workspaceRoot = workspace.getRoot();
            if (path.getDevice() == null
                    && workspaceRoot.getProject(path.segment(0))
                            .exists())
            {
                path = workspaceRoot.getFile(path)
                        .getLocation();
            }
            final String libraryPathString = path.toString();
            final File file = new File(libraryPathString);
            if (file.exists())
            {
                return new JarFile(file);
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        
        if (! (obj instanceof ClasspathJarFile))
        {
            return false;
        }
        
        ClasspathJarFile other = (ClasspathJarFile) obj;
        return _project.equals(other._project) && _iPath.equals(other._iPath);
    }

    @Override
    public int hashCode()
    {
        return _project.hashCode() ^ _iPath.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("ClasspathJarFile for project=%s, entry=%s", _project, _iPath); //$NON-NLS-1$
    }

}
