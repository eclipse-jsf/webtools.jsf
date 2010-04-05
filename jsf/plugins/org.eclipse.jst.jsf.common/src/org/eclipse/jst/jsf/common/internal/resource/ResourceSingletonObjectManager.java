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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
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
    final Map<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>> _perResourceObjects;
    private final IWorkspace _workspace;

    /**
     * Default constructor
     * @param workspace 
     */
    protected ResourceSingletonObjectManager(final IWorkspace workspace)
    {
        _workspace = workspace;
        _perResourceObjects = new HashMap<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>>();
    }

    /**
     * @return the workspace
     */
    protected final IWorkspace getWorkspace()
    {
        return _workspace;
    }


    /**
     * @return an unmodifiable view on the map of currently managed objects keyed
     * by the resource they are mapped to.
     */
    protected final Map<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>> getPerResourceObjects()
    {
        return Collections.unmodifiableMap(_perResourceObjects);
    }

    @Override
    public final MANAGEDOBJECT getInstance(final RESOURCE key)
    throws ManagedObjectException
    {
        assertNotDisposed();
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
                            "No object available for resource"); //$NON-NLS-1$
                }
                managedResObject = manageResource(key, managedObject);
            }

            runAfterGetInstance(key);
            return (MANAGEDOBJECT) managedResObject.getManagedObject();
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
        assertNotDisposed();
        return _perResourceObjects.containsKey(resource);
    }

    /**
     * @return a copy of the current set of RESOURCE object keys that we
     * are managing singletons for.  Collection is mutable, but as a copy,
     * changes to it do not effect thie object manager.
     */
    public synchronized Collection<RESOURCE> getManagedResources()
    {
        assertNotDisposed();
        return new HashSet(_perResourceObjects.keySet());
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
        final MyLifecycleEventListener<RESOURCE, MANAGEDOBJECT> eventListener = 
            new MyLifecycleEventListener<RESOURCE, MANAGEDOBJECT>(
                this, managedObject, resource);
        listener.addListener(eventListener);

        final ManagedResourceObject<MANAGEDOBJECT> managedResourceObject = new ManagedResourceObject<MANAGEDOBJECT>(
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
     * @return the managed object that has just be disassociated from the resource.
     * The object is not disposed, destroyed or checkpointed before being returned.
     */
    protected final synchronized MANAGEDOBJECT unmanageResource(final RESOURCE resource)
    {
        final ManagedResourceObject managedResourceObject =
            _perResourceObjects.remove(resource);
        final LifecycleListener listener = lazilyGetLifecycleListener();

        if (managedResourceObject != null)
        {
            listener.removeListener(managedResourceObject.getEventListener());
        }

        listener.removeResource(resource);
        return (MANAGEDOBJECT) managedResourceObject.getManagedObject();
    }

    /**
     * Call to register a listener
     * 
     * @param listener
     */
    protected final void addLifecycleEventListener(
            final IResourceLifecycleListener listener)
    {
        assertNotDisposed();
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
            _lifecycleListener = new LifecycleListener(_workspace);
        }
        return _lifecycleListener;
    }

    /*package*/ static class ManagedResourceObject<MANAGEDOBJECT extends IManagedObject>
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

    private static  class MyLifecycleEventListener<RESOURCE extends IResource, MANAGEDOBJECT extends IManagedObject> implements
    IResourceLifecycleListener
    {
        private final RESOURCE      _resource;
        private final MANAGEDOBJECT _managedObject;
        private final ResourceSingletonObjectManager<MANAGEDOBJECT, RESOURCE>  _target;

        private MyLifecycleEventListener(final ResourceSingletonObjectManager<MANAGEDOBJECT, RESOURCE> target,
                final MANAGEDOBJECT managedObject,
                final RESOURCE resource)
        {
            _resource = resource;
            _managedObject = managedObject;
            _target = target;
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
                    _target.unmanageResource(_resource);
                }
            }
            return result;
        }
    }

    
    /** 
     * Unmanages all resources and calls checkpoint and dispose on all managed
     * objects.  After this call, other methods my throw exception is called.
     * 
     * Sub-class may override, but should always call dispose after disposing 
     * their own specialized state.
     */
    @Override
    public void dispose()
    {
        if (_isDisposed.compareAndSet(false, true))
        {
            // TODO: implement a better lock strategy on resource manager
            synchronized (this)
            {
                Map<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>> copy = new HashMap<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>>(
                        getPerResourceObjects());
    
                for (Map.Entry<RESOURCE, ManagedResourceObject<MANAGEDOBJECT>> entry : copy.entrySet())
                {
                    RESOURCE res = entry.getKey();
                    MANAGEDOBJECT unmanagedResource = unmanageResource(res);
                    unmanagedResource.checkpoint();
                    unmanagedResource.dispose();
                }
                _perResourceObjects.clear();
                if (_lifecycleListener != null)
                {
                    _lifecycleListener.dispose();
                }
            }
        }
    }

    @Override
    public void destroy()
    {
        // do nothing by default
    }

    @Override
    public void checkpoint()
    {
        // do nothing by default
    }
}
