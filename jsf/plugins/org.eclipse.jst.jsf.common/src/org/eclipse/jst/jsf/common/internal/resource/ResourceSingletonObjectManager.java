package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;

/**
 * An object manager that manages a single instanceof an IManagedObject per
 * resource. The manager takes care of ensuring that a managed object is
 * properly disposed when a resource lifecycle event renders it inaccessible
 * (i.e file is deleted, project is closed or delete).
 * 
 * @author cbateman
 * 
 * @param <RESOURCE>
 * @param <MANAGEDOBJECT>
 */
public abstract class ResourceSingletonObjectManager<MANAGEDOBJECT extends IManagedObject, RESOURCE extends IResource>
        extends ObjectManager<MANAGEDOBJECT, RESOURCE>
{
    // lazily initialized
    private LifecycleListener                          _lifecycleListener;
    private final Map<RESOURCE, ManagedResourceObject> _perResourceObjects;

    /**
     * Default constructor
     */
    protected ResourceSingletonObjectManager()
    {
        _perResourceObjects = new HashMap<RESOURCE, ManagedResourceObject>();
    }

    @Override
    public final MANAGEDOBJECT getInstance(final RESOURCE key)
    throws ManagedObjectException
    {
        synchronized(this)
        {
            runBeforeGetInstance(key);
            ManagedResourceObject managedResObject = _perResourceObjects.get(key);
    
            if (managedResObject == null)
            {
                final MANAGEDOBJECT managedObject = createNewInstance(key);
    
                if (managedObject == null)
                {
                    throw new ManagedObjectException(
                            "No object available for resource");
                }
                managedResObject = manageResource(key, managedObject);
            }

            runAfterGetInstance(key);
            return managedResObject.getManagedObject();
        }
    }

    /**
     * @param resource
     * @return a new instance of T associated with S. This operation must not
     *         cache T: a brand new instance is always required. getInstance
     *         will perform caching and resource listening.
     */
    protected abstract MANAGEDOBJECT createNewInstance(RESOURCE resource);

    /**
     * @param resource
     */
    protected void runBeforeGetInstance(final RESOURCE resource)
    {
        // do nothing by default
    }

    /**
     * @param resource
     */
    protected void runAfterGetInstance(final RESOURCE resource)
    {
        // do nothing by default
    }

    /**
     * @param resource
     * @return true if there already exists a managed object associated with
     * the resource
     */
    public synchronized boolean isInstance(RESOURCE resource)
    {
        return _perResourceObjects.containsKey(resource);
    }

    /**
     * @return a copy of the current set of RESOURCE object keys that we
     * are managing singletons for.  Collection is mutable, but as a copy,
     * changes to it do not effect thie object manager.
     */
    public synchronized Collection<RESOURCE> getManagedResources()
    {
        final Set resources = new HashSet(_perResourceObjects.keySet());
        return resources;
    }
    /**
     * Should be called by concrete classes to indicate they have created a new
     * managed object for resource, for which they want to track lifecycle
     * changes.
     * 
     * @param resource
     * @param managedObject
     */
    private synchronized ManagedResourceObject manageResource(final RESOURCE resource,
            final MANAGEDOBJECT managedObject)
    {
        final LifecycleListener listener = lazilyGetLifecycleListener();
        listener.addResource(resource);
        final MyLifecycleEventListener eventListener = new MyLifecycleEventListener(
                managedObject, resource);
        listener.addListener(eventListener);

        final ManagedResourceObject managedResourceObject = new ManagedResourceObject(
                managedObject, eventListener);
        _perResourceObjects.put(resource, managedResourceObject);
        return managedResourceObject;
    }

    /**
     * Stop managing the resource. If resource is the last one, the resource
     * change listener will be removed (it will be added again when next
     * manageResource is called).
     * 
     * @param resource
     */
    protected final synchronized void unmanageResource(final RESOURCE resource)
    {
        final ManagedResourceObject managedResourceObject =
            _perResourceObjects.remove(resource);
        final LifecycleListener listener = lazilyGetLifecycleListener();

        if (managedResourceObject != null)
        {
            listener.removeListener(managedResourceObject.getEventListener());
        }

        listener.removeResource(resource);
    }

    /**
     * Call to register a listener
     * 
     * @param listener
     */
    protected final void addLifecycleEventListener(
            final IResourceLifecycleListener listener)
    {
        final LifecycleListener lifecycleListener = lazilyGetLifecycleListener();
        lifecycleListener.addListener(listener);
    }

    /**
     * Call to remove a listener
     * 
     * @param listener
     */
    protected final void removeLifecycleEventListener(
            final IResourceLifecycleListener listener)
    {
        final LifecycleListener lifecycleListener = lazilyGetLifecycleListener();
        lifecycleListener.removeListener(listener);
    }

    private synchronized LifecycleListener lazilyGetLifecycleListener()
    {
        if (_lifecycleListener == null)
        {
            _lifecycleListener = new LifecycleListener();
        }
        return _lifecycleListener;
    }

    private class ManagedResourceObject
    {
        private final MANAGEDOBJECT            _managedObject;
        private final MyLifecycleEventListener _eventListener;

        private ManagedResourceObject(final MANAGEDOBJECT managedObject,
                final MyLifecycleEventListener eventListener)
        {
            _managedObject = managedObject;
            _eventListener = eventListener;
        }

        public MANAGEDOBJECT getManagedObject()
        {
            return _managedObject;
        }

        public MyLifecycleEventListener getEventListener()
        {
            return _eventListener;
        }
    }

    private class MyLifecycleEventListener implements
    IResourceLifecycleListener
    {
        private final RESOURCE      _resource;
        private final MANAGEDOBJECT _managedObject;

        private MyLifecycleEventListener(final MANAGEDOBJECT managedObject,
                final RESOURCE resource)
        {
            _resource = resource;
            _managedObject = managedObject;
        }

        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            final EventResult result = EventResult.getDefaultEventResult();

            // not interested
            if (!_resource.equals(event.getAffectedResource()))
            {
                return EventResult.getDefaultEventResult();
            }

            if (event.getEventType() == EventType.RESOURCE_INACCESSIBLE)
            {
                try
                {
                    if (event.getReasonType() == ReasonType.RESOURCE_DELETED
                            || event.getReasonType() == ReasonType.RESOURCE_PROJECT_DELETED)
                    {
                        _managedObject.destroy();
                    }
                    else
                    {
                        _managedObject.dispose();
                    }
                }
                // dispose is external code out our control, so make sure
                // unmanage gets called if it blows up.
                finally
                {
                    unmanageResource(_resource);
                }
            }
            return result;
        }
    }
}
