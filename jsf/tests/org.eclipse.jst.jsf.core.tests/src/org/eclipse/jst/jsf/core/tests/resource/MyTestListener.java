package org.eclipse.jst.jsf.core.tests.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;

class MyTestListener extends AbstractTestListener<ResourceLifecycleEvent> implements IResourceLifecycleListener
{
    private final List<EventData> _events = new ArrayList<EventData>();
    private final MockWorkspaceContext _wsContext;

    public MyTestListener(final MockWorkspaceContext wsContext)
    {
        _wsContext = wsContext;
    }

    public MyTestListener(final MockWorkspaceContext wsContext,
            final LifecycleListener listener)
    {
        listener.addListener(this);
        _wsContext = wsContext;
    }

    public EventResult acceptEvent(final ResourceLifecycleEvent event)
    {
        _events.add(new EventData(event));
        return EventResult.getDefaultEventResult();
    }

    public void fireAndExpect(final IResourceChangeEvent fire,
            final List<EventData> expectedData)
    {
        fireEvent(fire);
        assertEquals(expectedData.size(), _events.size());
        for (final EventData data : expectedData)
        {
            data.assertContainedIn(_events);
        }
    }

    public void fireAndExpect(final IResourceChangeEvent fire,
            final IResource expectedResource, final EventType eventType,
            final ReasonType reasonType)
    {
        fireAndExpect(fire, Collections.singletonList(new EventData(
                expectedResource, eventType, reasonType)));
    }

    public void fireAndExpectNull(final IResourceChangeEvent fire)
    {
        fireEvent(fire);
        assertTrue(_events.isEmpty());
    }

    protected void fireEvent(final IResourceChangeEvent event)
    {
        _events.clear();
        _wsContext.fireWorkspaceEvent(event);
    }

    public static class EventData
    {
        private final IResource _expectedResource;
        private final EventType _eventType;
        private final ReasonType _reasonType;

        public EventData(final IResource expectedResource,
                final EventType eventType, final ReasonType reasonType)
        {
            super();
            _expectedResource = expectedResource;
            _eventType = eventType;
            _reasonType = reasonType;
        }

        public EventData(final ResourceLifecycleEvent event)
        {
            this(event.getAffectedResource(), event.getEventType(), event.getReasonType());
        }

        public final IResource getExpectedResource()
        {
            return _expectedResource;
        }

        public final EventType getEventType()
        {
            return _eventType;
        }

        public final ReasonType getReasonType()
        {
            return _reasonType;
        }

        public void assertContainedIn(final List<EventData> events)
        {
            for (final EventData event : events)
            {
                if (isEqual(event))
                {
                    return;
                }
            }
            Assert.fail("Event not found in list: "+this.toString());
        }

        public boolean isEqual(final EventData eventData)
        {
            return (_expectedResource.equals(eventData.getExpectedResource())
                    &&( _eventType == eventData.getEventType())
                    && (_reasonType == eventData.getReasonType()));
        }
        
        public String toString()
        {
            return String.format("Resource: %s, Event Type: %s, ReasonType: %s",
                    _expectedResource, _eventType, _reasonType);
        }
    }
}
