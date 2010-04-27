package org.eclipse.jst.jsf.common.internal.resource;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.managedobject.AbstractManagedObject;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;

/**
 * A managed object that tracks changes to a resource.
 * 
 * @author cbateman
 * @param <RESTYPE> 
 * 
 */
public abstract class ResourceTracker<RESTYPE extends IResource> extends AbstractManagedObject implements
        IResourceLifecycleListener
{
    private final RESTYPE _resource;
    private final AtomicLong _lastModifiedStamp = new AtomicLong();

    /**
     * @param resource
     */
    public ResourceTracker(final RESTYPE resource)
    {
        super();
        _resource = resource;
        _lastModifiedStamp.set(resource.getModificationStamp());
    }

    /**
     * @return the resource that is being tracked
     */
    public final RESTYPE getResource()
    {
        return _resource;
    }

    /**
     * @return the last modificatino stamp stored for the resource.
     */
    public final long getLastModifiedStamp()
    {
        return _lastModifiedStamp.get();
    }

    public EventResult acceptEvent(final ResourceLifecycleEvent event)
    {
        if (!isInteresting(event))
        {
            return EventResult.getDefaultEventResult();
        }

        final EventType eventType = event.getEventType();

        final ReasonType reasonType = event.getReasonType();
        switch (eventType)
        {
        case RESOURCE_ADDED:
            // added resources kick an add event.
            fireResourceAdded(event.getAffectedResource(), reasonType);
            break;
        case RESOURCE_CHANGED:
            // changed resources kick a change event
            fireResourceChanged(reasonType);
            break;
        case RESOURCE_INACCESSIBLE:
            // removed resources kick a remove event
            fireResourceInAccessible(reasonType);
            break;
        }

        return EventResult.getDefaultEventResult();
    }

    /**
     * @param event
     * @return true if this event is interesting
     */
    protected boolean isInteresting(final ResourceLifecycleEvent event)
    {
        return _resource.equals(event.getAffectedResource());
    }

    
    /**
     * @param reasonType
     */
    protected abstract void fireResourceInAccessible(ReasonType reasonType);

    /**
     * @param reasonType
     */
    protected abstract void fireResourceChanged(ReasonType reasonType);

    /**
     * @param affectedResource 
     * @param reasonType
     */
    protected abstract void fireResourceAdded(IResource affectedResource, ReasonType reasonType);

    @Override
    public void dispose()
    {
        super.dispose();
    }

    @Override
    public void checkpoint()
    {
        // nothing currently persisted
    }

    @Override
    public void destroy()
    {
        // nothing currently persisted
    }

}
