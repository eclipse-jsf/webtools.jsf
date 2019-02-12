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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.IMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.ClasspathEntryJarMatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.JarMatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener.ClasspathLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.IJarLocator.JarChangeEvent.Type;

/**
 * A default jar provider that traverses a project and returns all found jars on
 * the classpath that a list of matcher criteria.
 * 
 * @author cbateman
 * 
 */
public class DefaultJarLocator extends AbstractJarLocator
{
    private static final String DISPLAY_NAME = "Default Jar Provider"; //$NON-NLS-1$
    private static final String ID = DefaultJarLocator.class.getCanonicalName();
    private final VisitorMatcher<IProject, ClasspathJarFile, String> _matcher;
    private ClasspathEntryLifecycleListener _classpathEntryListener;
    private final JavaCoreMediator _javaCoreMediator;

    /**
     * @param javaCoreMediator
     */
    public DefaultJarLocator(final JavaCoreMediator javaCoreMediator)
    {
        this(Collections.singletonList(new AlwaysMatcher()), javaCoreMediator);
    }

    /**
     * @param matchers
     * @param javaCoreMediator
     */
    public DefaultJarLocator(final List<? extends IMatcher> matchers,
            final JavaCoreMediator javaCoreMediator)
    {
        super(ID, DISPLAY_NAME);
        _matcher = new VisitorMatcher<IProject, ClasspathJarFile, String>(ID,
                DISPLAY_NAME, new JarMatchingAcceptor(javaCoreMediator),
                matchers);
        _javaCoreMediator = javaCoreMediator;
        _classpathEntryListener = new ClasspathEntryLifecycleListener(
                _javaCoreMediator);
    }

    @Override
    public void start(final IProject project)
    {
        _classpathEntryListener.addLifecycleObject(project);
        _classpathEntryListener.addListener(new IClasspathLifecycleListener()
        {
            public EventResult acceptEvent(final ClasspathLifecycleEvent event)
            {
                final IJavaElement affectedElement = event.getAffectedElement();
                final List<ClasspathJarFile>  affectedJarFiles = new ArrayList<ClasspathJarFile>();
                if (isInteresting(project, affectedElement, event))
                {
                    Type jarEventType = null;
                    switch (event.getType())
                    {
                        case ADDED:
                            jarEventType = Type.JAR_ADDED;
                            affectedJarFiles.addAll(getChangedJars((IPackageFragmentRoot) affectedElement));
                        break;
                        case REMOVED:
                            jarEventType = Type.JAR_REMOVED;
                            affectedJarFiles.addAll(getChangedJars((IPackageFragmentRoot) affectedElement));
                        break;
                        case REMOVED_DELTA:
                            jarEventType = Type.JAR_REMOVED;
                            IResource res = event.getAffectedResource();
                            if (res.getType() == IResource.FILE &&
                                    "jar".equals(res.getFileExtension())) //$NON-NLS-1$
                            {
                                IPath path = res.getLocation();
                                if (path != null)
                                {
                                    affectedJarFiles.add(new ClasspathJarFile(project, path));
                                }
                            }
                        break;
                    }

                    if (jarEventType != null && !affectedJarFiles.isEmpty())
                    {
                        for (final ClasspathJarFile changedJar : affectedJarFiles)
                        {
                            fireChangeEvent(new JarChangeEvent(
                                    DefaultJarLocator.this, jarEventType,
                                    changedJar));
                        }
                    }
                }
                return EventResult.getDefaultEventResult();
            }

            private Collection<? extends ClasspathJarFile> getChangedJars(
                    final IPackageFragmentRoot affectedElement)
            {
                try
                {
                    return new VisitorMatcher<IPackageFragmentRoot, ClasspathJarFile, String>(
                            "", "", new ClasspathEntryJarMatchingAcceptor(project), //$NON-NLS-1$ //$NON-NLS-2$
                            Collections.singletonList(new AlwaysMatcher()))
                            .find(affectedElement);
                } catch (final JavaModelException e)
                {
                    return Collections.EMPTY_LIST;
                } catch (final Exception e)
                {
                    return Collections.EMPTY_LIST;
                }
            }
        });
        super.start(project);
    }

    private boolean isInteresting(final IProject project,
            final IJavaElement affectedElement, final ClasspathLifecycleEvent event)
    {
        return 
            // first filter out events that aren't related to the project we care about.
            (affectedElement != null
                && affectedElement.getJavaProject() != null
                && project
                        .equals(affectedElement.getJavaProject().getProject()))
                // then filter down to only events impacting frag roots
                // or jar file deletions
                && (affectedElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT
                        || (affectedElement.getElementType() == IJavaElement.JAVA_PROJECT
                                    && event.getAffectedResource() != null
                                    && event.getType() == ClasspathLifecycleEvent.Type.REMOVED_DELTA));
    }

    @Override
    public void stop()
    {
        _classpathEntryListener.dispose();
        super.stop();
    }

    @Override
    protected Collection<? extends ClasspathJarFile> doLocate(
            final IProject project)
    {
        try
        {
            return _matcher.find(project);
        } catch (final Exception e)
        {
            JSFCommonPlugin
                    .log(e, "While getting jars for project: " + project); //$NON-NLS-1$
            return getNoResult();
        }
    }
}
