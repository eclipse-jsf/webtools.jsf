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
package org.eclipse.jst.jsf.common.internal.finder.acceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher.MatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathJarFile;

/**
 * Accepts all the jars underlying a classpath entry.
 * 
 * @author cbateman
 * 
 */
public class ClasspathEntryJarMatchingAcceptor extends
        MatchingAcceptor<IPackageFragmentRoot, ClasspathJarFile>
{
    private final IProject project;

    /**
     * @param project
     */
    public ClasspathEntryJarMatchingAcceptor(final IProject project)
    {
        super();
        this.project = project;
    }

    @Override
    protected Collection<? extends ClasspathJarFile> getInputChildren(
            final IPackageFragmentRoot inputType)
    {
        final List<ClasspathJarFile> jarFiles = new ArrayList<ClasspathJarFile>();
        if (inputType.isArchive())
        {
            jarFiles.add(new ClasspathJarFile(project, inputType.getPath()));
        }
        // if (inputType.exists())
        // {
        // List<ClasspathJarFile> jarFiles = new ArrayList<ClasspathJarFile>();
        // switch (inputType.getEntryKind())
        // {
        // // this entry describes a source root in its project
        // case IClasspathEntry.CPE_SOURCE:
        // break;
        // // - this entry describes a folder or JAR containing
        // // binaries
        // case IClasspathEntry.CPE_LIBRARY:
        // {
        // jarFiles.add(new ClasspathJarFile(project, inputType));
        // }
        // break;
        // // - this entry describes another project
        // case IClasspathEntry.CPE_PROJECT:
        // // {
        // // final IPath pathToProject = entry.getPath();
        // // IWorkspace wkspace = ResourcesPlugin.getWorkspace();
        // // IResource res =
        // // wkspace.getRoot().findMember(pathToProject);
        // // if (res instanceof IProject)
        // // {
        // // tagLibsFound.addAll();
        // // }
        // // }
        // break;
        // // - this entry describes a project or library indirectly
        // // via a
        // // classpath variable in the first segment of the path *
        // case IClasspathEntry.CPE_VARIABLE:
        // break;
        // // - this entry describes set of entries referenced
        // // indirectly
        // // via a classpath container
        // case IClasspathEntry.CPE_CONTAINER:
        // break;
        // }
        // }
        return jarFiles;
    }

    @Override
    protected Collection<? extends ClasspathJarFile> getVisitableChildren(
            final ClasspathJarFile visitType)
    {
        return Collections.EMPTY_LIST;
    }
}
