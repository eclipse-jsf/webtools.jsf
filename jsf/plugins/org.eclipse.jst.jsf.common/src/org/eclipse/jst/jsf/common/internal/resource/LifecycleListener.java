/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.resource;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jst.jsf.common.internal.ITestTracker;
import org.eclipse.jst.jsf.common.internal.ITestTracker.Event;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;

/**
 * Listens to resource changes and fires lifecycle events
 * 
 * @author cbateman
 * 
 */
public class LifecycleListener extends
        AbstractLifecycleListener<ResourceLifecycleEvent, IResourceLifecycleListener, IResource>
        implements IResourceChangeListener
{
    private static boolean ENABLE_TEST_TRACKING = false;
    private static long _seqId;
    private ITestTracker _testTracker; // ==
    final IWorkspace _workspace;

    // null;
    // initialized
    // by
    // setter
    // injection
    /**
     * Initialize an inactive lifecycle listener. A workspace listener will not
     * be installed by this constructor. The object created using this
     * constructor will not fire any events until addResource is called at least
     * once to add a target resource
     * 
     * @param workspace
     *            the workspace to listen to for changes.
     * @throws NullPointerException
     *             if workspace is null.
     */
    public LifecycleListener(final IWorkspace workspace)
    {
        super();
        if (workspace == null)
        {
            throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
        }
        _workspace = workspace;
    }

    /**
     * Create a new lifecycle listener for the res
     * 
     * @param res
     * @param workspace
     *            the workspace to listen to for changes.
     * @throws NullPointerException
     *             if res or workspace is null.
     */
    public LifecycleListener(final IResource res, final IWorkspace workspace)
    {
        this(workspace);
        if (res == null)
        {
            throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
        }
        addResource(res);
    }

    /**
     * @param resources
     * @param workspace
     *            the workspace to listen to for changes.
     * @throws NullPointerException
     *             if resources, a member of resources or workspace is null.
     */
    public LifecycleListener(final List<IResource> resources,
            final IWorkspace workspace)
    {
        this(workspace);
        for (final IResource resource : resources)
        {
            if (resource != null)
            {
                addResource(resource);
            } else
            {
                throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
            }
        }
    }

    @Override
    protected void addSystemChangeListener()
    {
        _workspace.addResourceChangeListener(this);
    }

    @Override
    protected void removeSystemChangeListener()
    {
        _workspace.removeResourceChangeListener(this);
    }

    /**
     * @param testTracker
     */
    public final void setTestTracker(final ITestTracker testTracker)
    {
        _testTracker = testTracker;
    }

    /**
     * @param newValue
     */
    protected final void setEnableTracing(final boolean newValue)
    {
        ENABLE_TEST_TRACKING = newValue;
    }

    /**
     * @param res
     *            a resource you want to receive events for. MUST NOT BE NULL.
     * @throw {@link NullPointerException} if res is null
     */
    public void addResource(final IResource res)
    {
        addLifecycleObject(res);
    }

    public void resourceChanged(final IResourceChangeEvent event)
    {
        final long seqId = _seqId++;
        if (_testTracker != null && ENABLE_TEST_TRACKING)
        {
            _testTracker.fireEvent(Event.START_TRACKING, seqId,
                    "trackMethod_resourceChanged"); //$NON-NLS-1$
        }
        assert (!isDisposed());
        switch (event.getType())
        {
            case IResourceChangeEvent.PRE_CLOSE:
            {
                final IProject proj = (IProject) event.getResource();
                // must use iterator to ensure copy on write behaviour
                for (final IResource res : getLifecycleObjects())
                {
                    if (proj == res || proj == res.getProject())
                    {
                        fireLifecycleEvent(new ResourceLifecycleEvent(this,
                                res, EventType.RESOURCE_INACCESSIBLE,
                                ReasonType.RESOURCE_PROJECT_CLOSED));
                    }
                }
            }
            break;
            case IResourceChangeEvent.PRE_DELETE:
            {
                final IProject proj = (IProject) event.getResource();
                // must use iterator to ensure copy on write behaviour
                for (final IResource res : getLifecycleObjects())
                {
                    // if the resource being tracked is the resource being
                    // deleted,
                    // then fire a resource delete event
                    if (proj == res)
                    {
                        fireLifecycleEvent(new ResourceLifecycleEvent(this,
                                res, EventType.RESOURCE_INACCESSIBLE,
                                ReasonType.RESOURCE_DELETED));
                    }
                    // if the resource being tracked is a resource in the
                    // project being
                    // deleted, then fire a project deleted event
                    else if (proj == res.getProject())
                    {
                        fireLifecycleEvent(new ResourceLifecycleEvent(this,
                                res, EventType.RESOURCE_INACCESSIBLE,
                                ReasonType.RESOURCE_PROJECT_DELETED));
                    }
                }
            }
            break;
            case IResourceChangeEvent.POST_CHANGE:
            {
                for (final IResource res : getLifecycleObjects())
                {
                    IResourceDelta delta = event.getDelta();
                    // only care about post change events to resources
                    // that we are tracking
                    if (delta != null)
                    {
                        delta = delta.findMember(res.getFullPath());
                        if (delta != null)
                        {
                            visit(delta);
                        }
                    }
                }
            }
            break;
            default:
                // do nothing
                // we only handle these three
        }
        if (ENABLE_TEST_TRACKING && _testTracker != null)
        {
            _testTracker.fireEvent(Event.STOP_TRACKING, seqId,
                    "trackMethod_resourceChanged"); //$NON-NLS-1$
        }
    }

    private void visit(final IResourceDelta delta)
    {
        assert (!isDisposed());
        final IResource res = delta.getResource();
        // the wkspace root is a special case since even though
        // it is registered as the target resource, we are interested
        // in new projects created in the root
        if (res.getType() == IResource.ROOT)
        {
            handleWorkspaceRoot(delta);
        } else if (res instanceof IContainer)
        {
            handleContainer(delta, res);
        } else
        {
            handleFile(delta, res);
        }
    }

    private void handleContainer(final IResourceDelta delta, IResource container)
    {
        handleChange(delta, container, container);
        for (final IResourceDelta childDelta : delta.getAffectedChildren())
        {
            if (childDelta.getResource().getType() == IResource.FILE
                    || childDelta.getResource().getType() == IResource.FOLDER)
            {
                handleChange(childDelta, childDelta.getResource(), container);
            }
        }
    }

    private void handleFile(final IResourceDelta delta, final IResource res)
    {
        switch (delta.getKind())
        {
            case IResourceDelta.ADDED:
            case IResourceDelta.REMOVED:
            {
                handleChange(delta, res, res);
            }
            break;
            case IResourceDelta.CHANGED:
            {
                // the contents of the file have changed
                if ((delta.getFlags() & IResourceDelta.CONTENT) != 0)
                {
                    fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                            EventType.RESOURCE_CHANGED,
                            ReasonType.RESOURCE_CHANGED_CONTENTS));
                }
            }
            break;
        }
    }

    private void handleChange(final IResourceDelta delta, final IResource res,
            final IResource interestedResource)
    {
        switch (delta.getKind())
        {
            case IResourceDelta.ADDED:
            {
                handleAdd(delta, res, interestedResource);
            }
            break;
            case IResourceDelta.REMOVED:
            {
                handleRemove(delta, res, interestedResource);
            }
            break;
        }
    }

    private void handleRemove(final IResourceDelta delta, final IResource res,
            final IResource interestedResource)
    {
        if ((delta.getFlags() & IResourceDelta.MOVED_TO) != 0)
        {
            if (res.equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_INACCESSIBLE,
                        ReasonType.RESOURCE_MOVED));
            } else if (res.getParent().equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_INACCESSIBLE,
                        ReasonType.RESOURCE_MOVED_CONTAINER));
            }
        } else
        {
            if (res.equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_INACCESSIBLE,
                        ReasonType.RESOURCE_DELETED));
            } else if (res.getParent().equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_INACCESSIBLE,
                        ReasonType.RESOURCE_DELETED_FROM_CONTAINER));
            }
        }
    }

    private void handleAdd(final IResourceDelta delta, final IResource res,
            final IResource interestedResource)
    {
        if ((delta.getFlags() & IResourceDelta.MOVED_FROM) != 0)
        {
            if (res.equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_ADDED, ReasonType.RESOURCE_MOVED));
            } else if (res.getParent().equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_ADDED,
                        ReasonType.RESOURCE_MOVED_CONTAINER));
            }
        } else
        {
            if (res.equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_ADDED, ReasonType.RESOURCE_ADDED));
            } else if (res.getParent().equals(interestedResource))
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_ADDED,
                        ReasonType.RESOURCE_ADDED_TO_CONTAINER));
            }
        }
    }

    private void handleWorkspaceRoot(final IResourceDelta delta)
    {
        for (final IResourceDelta childDelta : delta
                .getAffectedChildren(IResourceDelta.ADDED))
        {
            final IResource res = childDelta.getResource();
            if ((childDelta.getFlags() & IResourceDelta.OPEN) != 0 &&
            // project was just opened
                    res.getType() == IResource.PROJECT)
            {
                fireLifecycleEvent(new ResourceLifecycleEvent(this, res,
                        EventType.RESOURCE_ADDED, ReasonType.PROJECT_OPENED));
            }
        }
    }
}
