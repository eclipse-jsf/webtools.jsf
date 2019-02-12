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
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher.MatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathJarFile;
import org.eclipse.jst.jsf.common.internal.resource.JavaCoreMediator;

/**
 * A matching acceptor that provides the jars referenced by a project.
 * 
 * @author cbateman
 * 
 */
public class JarMatchingAcceptor extends
        MatchingAcceptor<IProject, ClasspathJarFile>
{
    private final JavaCoreMediator _javaCoreMediator;

    /**
     * @param javaCoreMediator
     */
    public JarMatchingAcceptor(final JavaCoreMediator javaCoreMediator)
    {
        _javaCoreMediator = javaCoreMediator;
    }

    @Override
    protected Collection<ClasspathJarFile> getInputChildren(
            final IProject project)
    {
        final IJavaProject javaProject = _javaCoreMediator.create(project);
        IPackageFragmentRoot[] roots = null;
        try
        {
            roots = javaProject.getPackageFragmentRoots();
        } catch (final JavaModelException e1)
        {
            JSFCommonPlugin.log(e1);
        }
        if (roots == null || roots.length == 0)
        {
            return Collections.EMPTY_LIST;
        }
        final List<ClasspathJarFile> jars = new ArrayList<ClasspathJarFile>();
        VisitorMatcher<IPackageFragmentRoot, ClasspathJarFile, String> entryMatcher = new VisitorMatcher<IPackageFragmentRoot, ClasspathJarFile, String>(
                "", "", new ClasspathEntryJarMatchingAcceptor(project), //$NON-NLS-1$ //$NON-NLS-2$
                Collections.singletonList(new AlwaysMatcher()));
        for (final IPackageFragmentRoot root : roots)
        {
            try
            {
                jars.addAll(entryMatcher.perform(root));
            } catch (Exception e)
            {
                JSFCommonPlugin.log(e);
            }
        }
        return jars;
    }

    @Override
    protected Collection<ClasspathJarFile> getVisitableChildren(
            final ClasspathJarFile visitType)
    {
        return Collections.EMPTY_LIST;
    }
}
