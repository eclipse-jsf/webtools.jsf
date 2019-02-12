/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.ui.internal.tagregistry.ProjectTracker.ProjectTrackingListener.Reason;

/**
 * Tracks the active JSF projects in the workspace, maintaining a list of valid
 * projects and firing events when it changes
 * 
 * @author cbateman
 * 
 */
class ProjectTracker
{
    private final IWorkspaceRoot                                _root;
    private final LifecycleListener                             _lifecycleListener;
    private final CopyOnWriteArrayList<ProjectTrackingListener> _myListeners;
    private Set<IProject>                                       _validProjects;
    private final ResourceChangeListener                        _resourceChangeListener;
    private ProjectAdvisor                                      _projectAdvisor;

    public ProjectTracker(final IWorkspaceRoot root, final ProjectAdvisor projectAdvisor)
    {
        _root = root;
        _lifecycleListener = new LifecycleListener(ResourcesPlugin.getWorkspace());
        _resourceChangeListener = new ResourceChangeListener();
        _myListeners = new CopyOnWriteArrayList<ProjectTrackingListener>();
        if (projectAdvisor != null)
        {
            _projectAdvisor = projectAdvisor;
        }
        else
        {
            _projectAdvisor = DEFAULT_ADVISOR;
        }
    }

    public void startTracking()
    {
        _lifecycleListener.addResource(_root);

        _validProjects = new HashSet<IProject>();

        for (final IProject project : _root.getProjects())
        {
            if (_projectAdvisor.shouldTrack(project))
            {
                _validProjects.add(project);
                _lifecycleListener.addResource(project);
            }
        }
        // do this last, to ensure that any "simulataneous" events are handled
        // by our listener only after everything is initialized.
        _lifecycleListener.addListener(_resourceChangeListener);
    }

    public Set<IProject> getProjects()
    {
        final Set<IProject> projects = new HashSet<IProject>();
        synchronized (this)
        {
            projects.addAll(_validProjects);
        }
        return projects;
    }

    private synchronized void addProject(final IProject project)
    {
        if (_projectAdvisor.shouldTrack(project))
        {
            synchronized (this)
            {
                _validProjects.add(project);
                _lifecycleListener.addResource(project);
            }
            fireChangeEvent(project, Reason.ADDED);
        }
    }

    private void removeProject(final IProject project)
    {
        synchronized (this)
        {
            _validProjects.remove(project);
            _lifecycleListener.removeResource(project);
        }
        fireChangeEvent(project, Reason.REMOVED);
    }

    public void addListener(ProjectTrackingListener listener)
    {
        _myListeners.addIfAbsent(listener);
    }

    public void removeListener(ProjectTrackingListener listener)
    {
        _myListeners.remove(listener);
    }

    private void fireChangeEvent(final IProject project,
            ProjectTrackingListener.Reason reason)
    {
        for (final ProjectTrackingListener listener : _myListeners)
        {
            listener.projectsChanged(project, reason);
        }
    }

    public void dispose()
    {
        _lifecycleListener.dispose();
        _validProjects.clear();
        _myListeners.clear();
    }

    private class ResourceChangeListener implements IResourceLifecycleListener
    {
        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            final IResource res = event.getAffectedResource();

            // only interested if is affecting one of my resources

            // if the root is the source, check if a projected has been added
            // or opened
            if (event.getEventType() == EventType.RESOURCE_ADDED
                    && event.getReasonType() == ReasonType.PROJECT_OPENED
                    && res.getType() == IResource.PROJECT)
            {
                handleNewProject((IProject) res);
            }
            else if (_validProjects.contains(res)
                    && event.getEventType() == EventType.RESOURCE_INACCESSIBLE)
            {
                handleProjectClosed((IProject) res);
            }
            return EventResult.getDefaultEventResult();
        }

        private void handleNewProject(final IProject project)
        {
            addProject(project);
        }

        private void handleProjectClosed(final IProject project)
        {
            removeProject(project);
        }
    }

    public static class ProjectTrackingListener
    {
        public enum Reason
        {
            /**
             * Reason for change is a project added
             */
            ADDED,
            /**
             * Reason for change is a project removed
             */
            REMOVED
        }

        protected void projectsChanged(final IProject project, Reason reason)
        {
            // do nothing by default
        }
    }

    private static final ProjectAdvisor DEFAULT_ADVISOR = new ProjectAdvisor()
                                                        {
                                                            @Override
                                                            public boolean shouldTrack(
                                                                    IProject project)
                                                            {
                                                                return true;
                                                            }
                                                        };

    public abstract static class ProjectAdvisor
    {
        public abstract boolean shouldTrack(final IProject project);
    }
}
