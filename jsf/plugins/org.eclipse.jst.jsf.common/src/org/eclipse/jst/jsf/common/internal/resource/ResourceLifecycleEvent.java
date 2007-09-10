package org.eclipse.jst.jsf.common.internal.resource;

import org.eclipse.core.resources.IResource;

/**
 * @author cbateman
 *
 */
public class ResourceLifecycleEvent 
{
    /**
     * the type of lifecycle event
     * this enum is not closed and may add new fields in the future
     */
    public enum EventType
    {
        /**
         * Indicates that the resource is no longer accessible (as testable with
         * IResource.isAccessible).  The reasonType will indicate why.
         */
        RESOURCE_INACCESSIBLE;
    }
    
    /**
     * encodes the cause of the event if the event type provides one
     * this enum is not closed and may add new fields in the future
     */
    public enum ReasonType
    {
        /**
         * The resource was deleted from the workspace,
         * this event is pre change if the event is project
         * and post change otherwise
         */
        RESOURCE_DELETED,
        /**
         * The resource's project was deleted.  This event is 
         * pre-change.  Note that if the tracked resource is 
         * a project, RESOURCE_DELETED will be fired, not this
         * event.
         */
        RESOURCE_PROJECT_DELETED,
        /**
         * The resource's project was closed.  This event is pre-change
         */
        RESOURCE_PROJECT_CLOSED
    }
    
    private final IResource   _affectedResource;
    private final EventType   _eventType;
    private final ReasonType  _reasonType;
    
    /**
     * @param affectedResource
     * @param eventType
     * @param reasonType
     */
    public ResourceLifecycleEvent(IResource affectedResource, EventType eventType, ReasonType reasonType)
    {
        _affectedResource = affectedResource;
        _eventType = eventType;
        _reasonType = reasonType;
    }

    /**
     * @return the affected resource
     */
    public IResource getAffectedResource() {
        return _affectedResource;
    }

    /**
     * @return the event that has occurred
     */
    public EventType getEventType() {
        return _eventType;
    }

    /**
     * @return the cause of the event
     */
    public ReasonType getReasonType() {
        return _reasonType;
    }
}
