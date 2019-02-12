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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener.ClasspathLifecycleEvent;

/**
 * @author cbateman
 */
public class ClasspathEntryLifecycleListener
        extends
        AbstractLifecycleListener<IClasspathLifecycleListener.ClasspathLifecycleEvent, IClasspathLifecycleListener, IProject>
        implements IElementChangedListener
{
    private final JavaCoreMediator _mediator;

    /**
     * Initialize an inactive lifecycle listener. A classpath listener will not
     * be installed by this constructor. The object created using this
     * constructor will not fire any events until addClasspathEntry is called at
     * least once to add a target resource
     * 
     * @param mediator
     *            the mediator to use to access JavaCore operations
     * @throws NullPointerException
     *             if mediator is null.
     */
    public ClasspathEntryLifecycleListener(final JavaCoreMediator mediator)
    {
        if (mediator == null)
        {
            throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
        }
        _mediator = mediator;
    }

    /**
     * Create a new lifecycle listener for the res
     * 
     * @param entry
     * @param mediator
     *            the workspace to listen to for changes.
     * @throws NullPointerException
     *             if res or workspace is null.
     */
    public ClasspathEntryLifecycleListener(final IProject entry,
            final JavaCoreMediator mediator)
    {
        this(mediator);
        if (entry == null)
        {
            throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
        }
        addLifecycleObject(entry);
    }

    public void elementChanged(final ElementChangedEvent event)
    {
        // we are only interested in package fragment roots that are listening
        // for
        if (event.getType() == ElementChangedEvent.POST_CHANGE)
        {
            handlePostChangeEvent(event);
        }
    }

    private void handlePostChangeEvent(final ElementChangedEvent event)
    {
        final IJavaElementDelta delta = event.getDelta();
        new DeltaAcceptor().accept(new DeltaAcceptor.DeltaVisitor()
        {
            public void visit(final IJavaElementDelta visitDelta)
            {
                // we are only interested in package_fragment_root's
                final IJavaElement element = visitDelta.getElement();
                // only interested if a project in our entries list owns the
                // element.
                final IJavaProject javaProject = element.getJavaProject();
                if (javaProject == null
                        || !getLifecycleObjects().contains(
                                javaProject.getProject()))
                {
                    return;
                }
                if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT)
                {
                    handlePackageFragmentRoot(visitDelta);
                } else if (element.getElementType() == IJavaElement.JAVA_PROJECT
                        && visitDelta.getResourceDeltas() != null)
                {
                    handleProject(visitDelta);
                }
                // need to test the flags only for package fragment roots
            }

            private void handleProject(final IJavaElementDelta visitDelta)
            {
                for (final IResourceDelta resDelta : visitDelta
                        .getResourceDeltas())
                {
                    try
                    {
                        resDelta.accept(new IResourceDeltaVisitor()
                        {
                            public boolean visit(final IResourceDelta resDelta2)
                                    throws CoreException
                            {
                                if (resDelta2.getKind() == IResourceDelta.REMOVED
                                        && resDelta2.getResource().getType() == IResource.FILE
                                        && "jar".equals(resDelta2.getResource().getFileExtension())) //$NON-NLS-1$
                                {
                                    fireLifecycleEvent(new ClasspathLifecycleEvent(
                                            ClasspathEntryLifecycleListener.this,
                                            visitDelta.getElement(),
                                            ClasspathLifecycleEvent.Type.REMOVED_DELTA,
                                            resDelta2.getResource()));
                                }
                                return true;
                            }
                        });
                    } catch (final CoreException e)
                    {
                        JSFCommonPlugin.log(e);
                    }
                }
            }

            private void handlePackageFragmentRoot(
                    final IJavaElementDelta visitDelta)
            {
                final int flags = visitDelta.getFlags();
                switch (visitDelta.getKind())
                {
                    case IJavaElementDelta.ADDED:
                    {
                        fireLifecycleEvent(new ClasspathLifecycleEvent(
                                ClasspathEntryLifecycleListener.this,
                                visitDelta.getElement(),
                                ClasspathLifecycleEvent.Type.ADDED));
                    }
                    break;
                    case IJavaElementDelta.REMOVED:
                    {
                        fireLifecycleEvent(new ClasspathLifecycleEvent(
                                ClasspathEntryLifecycleListener.this,
                                visitDelta.getElement(),
                                ClasspathLifecycleEvent.Type.REMOVED));
                    }
                    break;
                    case IJavaElementDelta.CHANGED:
                        if ((flags & IJavaElementDelta.F_ADDED_TO_CLASSPATH) != 0)
                        {
                            fireLifecycleEvent(new ClasspathLifecycleEvent(
                                    ClasspathEntryLifecycleListener.this,
                                    visitDelta.getElement(),
                                    ClasspathLifecycleEvent.Type.ADDED));
                        } else if ((flags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) != 0)
                        {
                            fireLifecycleEvent(new ClasspathLifecycleEvent(
                                    ClasspathEntryLifecycleListener.this,
                                    visitDelta.getElement(),
                                    ClasspathLifecycleEvent.Type.REMOVED));
                        } else if ((flags & IJavaElementDelta.F_REORDER) != 0)
                        {
                            // TODO: how important is the case of a classpath
                            // reordering?
                        }
                    break;
                }
            }
        }, delta);
    }

    @Override
    protected void addSystemChangeListener()
    {
        _mediator.addElementChangedListener(this);
    }

    @Override
    protected void removeSystemChangeListener()
    {
        _mediator.removeElementChangeListener(this);
    }

    private static class DeltaAcceptor
    {
        public interface DeltaVisitor
        {
            public void visit(final IJavaElementDelta delta);
        }

        public void accept(final DeltaVisitor visitor,
                final IJavaElementDelta delta)
        {
            visitor.visit(delta);
            for (final IJavaElementDelta childDelta : delta
                    .getAffectedChildren())
            {
                accept(visitor, childDelta);
            }
        }
    }
}
