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
        RESOURCE_INACCESSIBLE,
        
        /**
         * Indicates that the resource being tracked has changed in some
         * way, use ReasonType to determine specifics
         */
        RESOURCE_CHANGED,
        
        /**
         * Indicates that the resource being tracked hass been added.  Use
         * ReasonType to determine specifics.
         */
        RESOURCE_ADDED;
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
        RESOURCE_PROJECT_CLOSED,
        /**
         * Occurs when the contents of a non-project resource has changed 
         */
        RESOURCE_CHANGED_CONTENTS,
        
        /**
         * Occurs when a project resource is added
         */
        PROJECT_OPENED,
        /**
         * Occurs when a non-project resource is added.
         */
        RESOURCE_ADDED,
        /**
         * Occurs when a resource was has been moved.  This results
         * in a remove and add for the moved resource, plus a similar
         * pair for all its children if it is a container.
         */
        RESOURCE_MOVED
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
