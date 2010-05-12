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

import java.util.EventObject;

import org.eclipse.core.resources.IResource;

/**
 * @author cbateman
 * 
 */
public class ResourceLifecycleEvent extends EventObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -8692801944833729L;

    /**
     * the type of lifecycle event this enum is not closed and may add new
     * fields in the future
     */
    public enum EventType
    {
        /**
         * Indicates that the resource is no longer accessible (as testable with
         * IResource.isAccessible). The reasonType will indicate why.
         */
        RESOURCE_INACCESSIBLE,

        /**
         * Indicates that the resource being tracked has changed in some way,
         * use ReasonType to determine specifics
         */
        RESOURCE_CHANGED,

        /**
         * Indicates that the resource being tracked hass been added. Use
         * ReasonType to determine specifics.
         */
        RESOURCE_ADDED;
    }

    /**
     * encodes the cause of the event if the event type provides one this enum
     * is not closed and may add new fields in the future
     */
    public enum ReasonType
    {
        /**
         * The resource was deleted from the workspace, this event is pre change
         * if the event is project and post change otherwise
         */
        RESOURCE_DELETED,
        /**
         * The resource was delete from it's container. This fired when the
         * PARENT of the resource being deleted is registered for lifecycle
         * events (i.e. _affectedResource.getParent() == registeredResource)
         */
        RESOURCE_DELETED_FROM_CONTAINER,
        /**
         * The resource's project was deleted. This event is pre-change. Note
         * that if the tracked resource is a project, RESOURCE_DELETED will be
         * fired, not this event.
         */
        RESOURCE_PROJECT_DELETED,
        /**
         * The resource's project was closed. This event is pre-change
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
         * Occurs when a non-project resource is added to a container. This is
         * fired if the resource being added was in the list of resources
         * registered for lifecycle events (i.e. _affectedResource ==
         * registeredResource).
         */
        RESOURCE_ADDED,
        /**
         * Occurs when a non-project resource is added to a container. This is
         * fired if the PARENT of the resource being added was in the resources
         * registered for lifecycle events (i.e. _affectedResource.getParent() =
         * registeredResource)
         */
        RESOURCE_ADDED_TO_CONTAINER,
        /**
         * Occurs when a resource has becomes added or inaccessible due to a
         * move operation. This event is fired when the resource being moved is
         * the one of interest.
         */
        RESOURCE_MOVED,
        /**
         * Occurs when a resource has becomes added or inaccessible due to a
         * move operation. This event is fired when the PARENT of resource being
         * moved is the one of interest.
         */
        RESOURCE_MOVED_CONTAINER
    }

    private final IResource _affectedResource;
    private final EventType _eventType;
    private final ReasonType _reasonType;

    /**
     * @param source 
     * @param affectedResource
     * @param eventType
     * @param reasonType
     */
    public ResourceLifecycleEvent(final LifecycleListener source,
            final IResource affectedResource,
            final EventType eventType, final ReasonType reasonType)
    {
        super(source);
        _affectedResource = affectedResource;
        _eventType = eventType;
        _reasonType = reasonType;
    }

    /**
     * @return the affected resource
     */
    public IResource getAffectedResource()
    {
        return _affectedResource;
    }

    /**
     * @return the event that has occurred
     */
    public EventType getEventType()
    {
        return _eventType;
    }

    /**
     * @return the cause of the event
     */
    public ReasonType getReasonType()
    {
        return _reasonType;
    }

    @Override
    public String toString()
    {
        return String.format(
                "ResourceLifecycleEvent: Res = %s, Event=%s, Reason=%s", //$NON-NLS-1$
                getAffectedResource(), getEventType(), getReasonType());
    }
}
