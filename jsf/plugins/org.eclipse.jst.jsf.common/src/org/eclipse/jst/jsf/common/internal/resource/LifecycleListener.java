package org.eclipse.jst.jsf.common.internal.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.jsf.common.internal.ITestTracker;
import org.eclipse.jst.jsf.common.internal.ITestTracker.Event;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;

/**
 * Listens to resource changes and fires lifecycle events
 * 
 * @author cbateman
 *
 */
public class LifecycleListener implements IResourceChangeListener
{
    private final static boolean                            ENABLE_TEST_TRACKING = true;
    private static long                                     _seqId;
    
    private final List<IResource>                           _resources;
    private final List<IResourceLifecycleListener>          _listeners;
    private boolean                                         _isDisposed = false;
    private ITestTracker                                    _testTracker; // == null; initialized by setter injection

    /**
     * Initialize an inactive lifecycle listener.  A workspace listener will not
     * be installed by this constructor.  The object created using this constructor
     * will not fire any events until addResource is called at least once to add
     * a target resource
     */
    public LifecycleListener()
    {
        _resources = new ArrayList<IResource>();
        _listeners = new ArrayList<IResourceLifecycleListener>(1);
    }

    /**
     * Create a new lifecycle listener for the res
     * @param res
     */
    public LifecycleListener(IResource res)
    {
        this();
        _resources.add(res);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    /**
     * @param resources
     */
    public LifecycleListener(List<IResource> resources)
    {
        this();
        _resources.addAll(resources);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }
    

    /**
     * @param testTracker
     */
    public final void setTestTracker(ITestTracker testTracker)
    {
        _testTracker = testTracker;
    }

    /**
     * @param res
     */
    public void addResource(final IResource res)
    {
        synchronized(_resources)
        {
            int preSize = _resources.size();
            if (!_resources.contains(res))
            {
                _resources.add(res);
            }
            
            // if the size of the array was 0
            // and is now greater, make sure the listener is added
            if (preSize == 0
                    && _resources.size() > 0)
            {
                ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
            }
        }
    }

    /**
     * @param res
     */
    public void removeResource(final IResource res)
    {
        synchronized(_resources)
        {
            _resources.remove(res);
            
            // if there are no longer target resources,
            // remove the workspace listener
            if (_resources.size() == 0)
            {
                ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
            }
        }
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
        long seqId = _seqId++;
        
        if (ENABLE_TEST_TRACKING && _testTracker != null)
        {
            _testTracker.fireEvent(Event.START_TRACKING, seqId, "trackMethod_resourceChanged");
        }

        assert(!isDisposed());

        switch(event.getType())
        {
            case IResourceChangeEvent.PRE_CLOSE:
            {
                final IProject proj = (IProject) event.getResource();

                synchronized(_resources)
                {
                    final List<IResource> resources = copyResourceList();
                    for (final IResource res : resources)
                    {
                        if (proj == res || proj == res.getProject())
                        {
                            fireLifecycleEvent(
                                    new ResourceLifecycleEvent(res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_CLOSED));
                        }
                    }
                }
            }
            break;

            case IResourceChangeEvent.PRE_DELETE:
            {
                IProject proj = (IProject) event.getResource();

                synchronized(_resources)
                {
                    final List<IResource> resources = copyResourceList();
                    for (final IResource res : resources)
                    {
                        // if the resource being tracked is the resource being deleted,
                        // then fire a resource delete event
                        if (proj == res)
                        {
                            fireLifecycleEvent(new ResourceLifecycleEvent(res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_DELETED));
                        }
                        // if the resource being tracked is a resource in the project being
                        // deleted, then fire a project deleted event
                        else if (proj == res.getProject())
                        {
                            fireLifecycleEvent(
                                new ResourceLifecycleEvent(res, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_DELETED));
                        }
                    }
                }
            }
            break;

            case IResourceChangeEvent.POST_CHANGE:
            {
                synchronized(_resources)
                {
                    final List<IResource> resources = copyResourceList();
                    for (final IResource res : resources)
                    {
                        // only bother continuing if the resource we are tracking
                        // is not a project since post-change events on projects 
                        // that we care about won't occur
                        if (res.getType() != IResource.PROJECT)
                        {
                            IResourceDelta delta = event.getDelta();
                            
//                            long seqId2 = _seqId++;
//                            if (ENABLE_TEST_TRACKING && _testTracker != null)
//                            {
//                                _testTracker.fireEvent(Event.START_TRACKING, seqId2, "testFindMember");
//                            }
                            // only care about post change events to resources
                            // that we are tracking
                            delta = delta.findMember(res.getFullPath());

                            if (delta != null)
                            {
                                visit(delta);
                            }

//                            if (ENABLE_TEST_TRACKING && _testTracker != null)
//                            {
//                                _testTracker.fireEvent(Event.STOP_TRACKING, seqId2, "testFindMember");
//                            }
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
            _testTracker.fireEvent(Event.STOP_TRACKING, seqId, "trackMethod_resourceChanged");
        }
    }

    private List<IResource> copyResourceList()
    {
        synchronized(_resources)
        {
            List<IResource>  resList = new ArrayList<IResource>(_resources.size());
            resList.addAll(_resources);
            return resList;
        }
    }

    private void fireLifecycleEvent(ResourceLifecycleEvent event)
    {
       List<IResourceLifecycleListener>                copyListeners
           = new ArrayList(_listeners.size());

        // copy the listeners to avoid concurrent modification problems
        // if a listeners removes itself due to an event
        synchronized(_listeners)
        {
            copyListeners.addAll(_listeners);
        }

        boolean  disposeAfter = false;

        for (final IResourceLifecycleListener listener : copyListeners)
        {
           EventResult result = listener.acceptEvent(event);
           disposeAfter |= result.getDisposeAfterEvent();
        }

        if (disposeAfter)
        {
            dispose();
        }
    }

    private void visit(IResourceDelta delta) 
    {
        assert(!isDisposed());

        final IResource res = delta.getResource();

        switch (delta.getKind())
        {
            case IResourceDelta.CHANGED:
            {
                // the contents of the file have changed
                if ((delta.getFlags() & IResourceDelta.CONTENT) != 0)
                {
                    fireLifecycleEvent(
                        new ResourceLifecycleEvent
                            (res, EventType.RESOURCE_CHANGED
                                    , ReasonType.RESOURCE_CHANGED_CONTENTS));
                }
            }
            break;
            case IResourceDelta.REMOVED:
            {
                fireLifecycleEvent(
                    new ResourceLifecycleEvent
                        (res, EventType.RESOURCE_INACCESSIBLE
                                    , ReasonType.RESOURCE_DELETED));
            }
            break;
        }
    }
}
