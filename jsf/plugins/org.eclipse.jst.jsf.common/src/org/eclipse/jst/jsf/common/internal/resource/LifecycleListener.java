package org.eclipse.jst.jsf.common.internal.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener.EventResult;

/**
 * Listens to resource changes and fires lifecycle events
 * 
 * @author cbateman
 *
 */
public class LifecycleListener implements IResourceChangeListener, IResourceDeltaVisitor
{
    private final IResource                                 _res;
    private final List<IResourceLifecycleListener>          _listeners;
    private boolean                                         _isDisposed = false;

    /**
     * Create a new lifecycle listener for the res
     * @param res
     */
    public LifecycleListener(IResource res)
    {
        _res = res;
        _listeners = new ArrayList<IResourceLifecycleListener>(1);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }
    

    /**
     * Release the resource change listener
     */
    public void dispose()
    {
        if (!_isDisposed)
        {
            // remove first to minimize the chance that the listener will
            // be triggered during the remainder of dispose
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);

            // don't clear the listener list currently because of 
            // concurrent change problems.

            _isDisposed = true;
        }
    }

    /**
     * @return true if the listener has been disposed
     */
    public boolean isDisposed() {
        return _isDisposed;
    }

    /**
     * Adds listener to the list of objects registered to receive 
     * lifecycle events for this resource.  Only adds the listener
     * if it is not already in the list.
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     * 
     * @param listener
     */
    public void addListener(IResourceLifecycleListener listener)
    {
        if (isDisposed())
        {
            throw new IllegalStateException();
        }
        
        synchronized(_listeners)
        {
            if (!_listeners.contains(listener))
            {
                _listeners.add(listener);
            }
        }
    }

    /**
     * Removes listener from the list of registered listeners
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     *
     * @param listener
     */
    public void removeListener(IResourceLifecycleListener listener)
    {
        if (isDisposed())
        {
            throw new IllegalStateException();
        }

        synchronized(_listeners)
        {
            _listeners.remove(listener);
        }
    }

    public void resourceChanged(IResourceChangeEvent event) 
    {
        assert(!isDisposed());

        switch(event.getType())
        {
            case IResourceChangeEvent.PRE_CLOSE:
            {
                IProject proj = (IProject) event.getResource();
                if (proj == _res || proj == _res.getProject())
                {
                    fireLifecycleEvent(new ResourceLifecycleEvent(_res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_CLOSED));
                }
            }
            break;

            case IResourceChangeEvent.PRE_DELETE:
            {
                IProject proj = (IProject) event.getResource();

                // if the resource being tracked is the resource being deleted,
                // then fire a resource delete event
                if (proj == _res)
                {
                    fireLifecycleEvent(new ResourceLifecycleEvent(_res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_DELETED));
                }
                // if the resource being tracked is a resource in the project being
                // deleted, then fire a project deleted event
                else if (proj == _res.getProject())
                {
                    fireLifecycleEvent(new ResourceLifecycleEvent(_res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_DELETED));
                }
            }
            break;

            case IResourceChangeEvent.POST_CHANGE:
            {
                // only bother continuing if the resource we are tracking
                // is not a project since post-change events on projects 
                // that we care about won't occur
                if (_res.getType() != IResource.PROJECT)
                {
                    IResourceDelta delta = event.getDelta();
                    // only care about post change events to resources
                    // that we are tracking
                    delta = delta.findMember(_res.getFullPath());

                    try
                    {
                        if (delta != null)
                        {
                            delta.accept(this);
                        }
                    }
                    catch (CoreException ce)
                    {
                        // can't do anything but log
                        JSFCommonPlugin.log(new Throwable(ce));
                    }
                }
            }
            break;
            // we only handle these three
        }
    }

    private void fireLifecycleEvent(ResourceLifecycleEvent event)
    {
        synchronized(_listeners)
        {
            boolean  disposeAfter = false;

            for (final IResourceLifecycleListener listener : _listeners)
            {
               EventResult result = listener.acceptEvent(event);
               disposeAfter |= result.getDisposeAfterEvent();
            }

            if (disposeAfter)
            {
                dispose();
            }
        }
    }

    public boolean visit(IResourceDelta delta) throws CoreException 
    {
        assert(!isDisposed());

        switch (delta.getKind())
        {
            case IResourceDelta.REMOVED:
            {
                if (_res.equals(delta.getResource()))
                {
                    fireLifecycleEvent(
                        new ResourceLifecycleEvent
                            (_res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_DELETED));
                    // TODO: return false to stop child visits?
                }
            }
        }

        // keep going on children
        return true;
    }
}
