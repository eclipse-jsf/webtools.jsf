/**
 * 
 */
package org.eclipse.jst.jsf.core.tests.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;

class MockListener implements IResourceLifecycleListener
{
    private List<ResourceLifecycleEvent>   _acceptedEvents = 
        new ArrayList<ResourceLifecycleEvent>();

    public EventResult acceptEvent(ResourceLifecycleEvent event) 
    {
        _acceptedEvents.add(event);
        return new EventResult();
    }

    public void assertAcceptedResourceInaccessible(final IResource res, final ReasonType  reason)
    {
        assertAcceptedEvent(res, EventType.RESOURCE_INACCESSIBLE, reason);
    }

    public void assertNoAcceptedResourceInaccessible(final IResource res, final ReasonType reason)
    {
        assertNoAcceptedEvent(res, EventType.RESOURCE_INACCESSIBLE, reason);
    }

    public void assertNoAcceptedEvent(final IResource res, final EventType eventType, final ReasonType reason)
    {
        for (ResourceLifecycleEvent event : _acceptedEvents)
        {
            if (event.getEventType() == eventType
                    && event.getAffectedResource().equals(res))
            {
                if (reason == event.getReasonType())
                {
                    TestLifecycleListener.fail("Expected not to find RESOURCE_INACCESSIBLE event for resource: "+res.toString());                    }
            }
        }
    }

    public void assertAcceptedEvent(final IResource res, final EventType eventType, final ReasonType reason)
    {
        for (ResourceLifecycleEvent event : _acceptedEvents)
        {
            if (event.getEventType() == eventType
                    && event.getReasonType() == reason
                    && event.getAffectedResource().equals(res))
            {
                if (reason == event.getReasonType())
                {
                    return;
                }
                else
                {
                    // this output is diagnostic and doesn't necessarily
                    // indicate a problem
                    System.out.printf("Expected event found with different result: %s instead of %s", event.getReasonType().toString(), reason.toString());
                }
            }
        }

        // if we get to here then we have failed to find the expected
        // event
        TestLifecycleListener.fail("Expected to find" + eventType + " event, reason "+reason+" for resource: "+res.toString());
    }
}