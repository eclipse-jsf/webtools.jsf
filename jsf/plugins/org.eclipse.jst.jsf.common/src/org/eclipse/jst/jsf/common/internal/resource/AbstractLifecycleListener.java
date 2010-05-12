package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * 
 * @author cbateman
 * @param <EVENTTYPE> 
 * @param <LISTENERTYPE>
 * @param <LIFECYCLEOBJECT>
 * 
 */
public abstract class AbstractLifecycleListener<EVENTTYPE extends EventObject, LISTENERTYPE extends ILifecycleListener<EVENTTYPE>, LIFECYCLEOBJECT>
        extends ImmutableLifecycleListener<LISTENERTYPE>
{
    /**
     * Exception (non-localized) error message thrown when a null object is
     * added.
     */
    protected static final String CANNOT_ADD_NULL_RESOURCE = "Cannot add null object"; //$NON-NLS-1$
    static final boolean TRACE_EVENTS;
    static
    {
        TRACE_EVENTS = Boolean.valueOf(
                Platform.getDebugOption(JSFCommonPlugin.PLUGIN_ID
                        + "/debug/lifecyclelistener")).booleanValue();//$NON-NLS-1$
    }
    private final CopyOnWriteArrayList<LISTENERTYPE> _listeners = new CopyOnWriteArrayList<LISTENERTYPE>();
    private final CopyOnWriteArrayList<LIFECYCLEOBJECT> _lifecycleObjects = new CopyOnWriteArrayList<LIFECYCLEOBJECT>();
    private final AtomicBoolean _isDisposed = new AtomicBoolean(false);

    /**
     * Adds listener to the list of objects registered to receive lifecycle
     * events for this resource. Only adds the listener if it is not already in
     * the list.
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true Throws
     * {@link NullPointerException} if listener == null
     * 
     * @param listener
     */
    @Override
    public void addListener(final LISTENERTYPE listener)
    {
        if (isDisposed())
        {
            throw new IllegalStateException();
        }
        if (listener == null)
        {
            throw new NullPointerException("Cannot pass null listener"); //$NON-NLS-1$
        }
        _listeners.addIfAbsent(listener);
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
    @Override
    public void removeListener(final LISTENERTYPE listener)
    {
        if (isDisposed())
        {
            throw new IllegalStateException();
        }
        _listeners.remove(listener);
    }

    /**
     * @param event
     */
    protected void fireLifecycleEvent(final EVENTTYPE event)
    {
        boolean disposeAfter = false;
        if (TRACE_EVENTS)
        {
            System.err.println(event);
        }
        // NOTE: must use iterator through _listeners so that
        // CopyOnWriteArrayList protects us from concurrent modification
        for (final LISTENERTYPE listener : _listeners)
        {
            final EventResult result = listener.acceptEvent(event);
            disposeAfter |= result.getDisposeAfterEvent();
        }
        if (disposeAfter)
        {
            dispose();
        }
    }

    /**
     * @return true if the listener has been disposed
     */
    public boolean isDisposed()
    {
        return _isDisposed.get();
    }

    /**
     * Release the resource change listener
     */
    public final void dispose()
    {
        if (_isDisposed.compareAndSet(false, true))
        {
            // ensure that add/removeResource don't cause races to add or
            // remove the resource listener
            synchronized (_lifecycleObjects)
            {
                // remove first to minimize the chance that the listener will
                // be triggered during the remainder of dispose
                removeSystemChangeListener();
                _lifecycleObjects.clear();
                doDispose();
            }
        }
    }

    /**
     * Sub-class disposal specialization.
     */
    protected void doDispose()
    {
        // do nothing by default;  sub-classes should override to provide their
        // own disposal.
    }

    /**
     * @param object
     */
    protected void addLifecycleObject(final LIFECYCLEOBJECT object)
    {
        if (object == null)
        {
            throw new NullPointerException(CANNOT_ADD_NULL_RESOURCE);
        }
        synchronized (_lifecycleObjects)
        {
            // don't add any resources if we've disposed before acquiring the
            // lock
            if (isDisposed())
            {
                return;
            }
            final int preSize = _lifecycleObjects.size();
            if (!_lifecycleObjects.contains(object))
            {
                _lifecycleObjects.add(object);
            }
            // if the size of the array was 0
            // and is now greater, make sure the listener is added
            if (preSize == 0 && _lifecycleObjects.size() > 0)
            {
                addSystemChangeListener();
            }
        }
    }

    /**
     * If there are no longer resources being targeted, the resource change
     * listener will be removed.
     * 
     * @param res
     */
    public void removeResource(final IResource res)
    {
        synchronized (_lifecycleObjects)
        {
            // don't bother with this stuff if we're disposed.
            if (isDisposed())
            {
                return;
            }
            _lifecycleObjects.remove(res);
            // if there are no longer target resources,
            // remove the workspace listener
            if (_lifecycleObjects.size() == 0)
            {
                removeSystemChangeListener();
            }
        }
    }

    /**
     * @return an iterable of the lifecycle objects being tracked.
     */
    protected Collection<LIFECYCLEOBJECT> getLifecycleObjects()
    {
        return Collections.unmodifiableCollection(_lifecycleObjects);
    }

    /**
     * Add the system change listener that is used to collect events that will
     * be processed into lifecycle change events.
     */
    protected abstract void addSystemChangeListener();

    /**
     * Remove a system change listener added using addSystemChangeListener
     */
    protected abstract void removeSystemChangeListener();
}
