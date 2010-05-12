package org.eclipse.jst.jsf.core.tests.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jst.jsf.common.internal.resource.ClasspathEntryLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener.ClasspathLifecycleEvent;
import org.eclipse.jst.jsf.test.util.mock.java.MockJDTWorkspaceContext;

class ClasspathTestListener extends
        AbstractTestListener<ClasspathLifecycleEvent> implements
        IClasspathLifecycleListener
{
    private final List<EventData> _events = new ArrayList<EventData>();
    private final MockJDTWorkspaceContext _jdtContext;

    public ClasspathTestListener(final MockJDTWorkspaceContext jdtContext)
    {
        _jdtContext = jdtContext;
    }

    public ClasspathTestListener(final MockJDTWorkspaceContext jdtContext,
            final ClasspathEntryLifecycleListener listener)
    {
        this(jdtContext);
        listener.addListener(this);
    }

    public void fireAndExpect(final ElementChangedEvent fire,
            final List<EventData> expectedData)
    {
        fireEvent(fire);
        assertEquals(expectedData.size(), _events.size());
        for (final EventData data : expectedData)
        {
            data.assertContainedIn(_events);
        }
    }

    public void fireAndExpect(final ElementChangedEvent fire,
            final IJavaElement javaElement, final ClasspathLifecycleEvent.Type type)
    {
        fireAndExpect(fire, Collections.singletonList(new EventData(
                javaElement, type)));
    }

    public void fireAndExpectNull(final ElementChangedEvent fire)
    {
        fireEvent(fire);
        assertTrue(_events.isEmpty());
    }

    protected void fireEvent(final ElementChangedEvent event)
    {
        _events.clear();
        _jdtContext.fireElementChangedEvent(event);
    }

    public static class EventData
    {
        private final IJavaElement  _affectedElement;
        private final ClasspathLifecycleEvent.Type  _type;

        // private final EventType _eventType;
        // private final ReasonType _reasonType;
        public EventData(final IJavaElement affectedElement, final ClasspathLifecycleEvent.Type type)
        {
            super();
            _affectedElement = affectedElement;
            _type = type;
        }

        public EventData(final ClasspathLifecycleEvent event)
        {
            this(event.getAffectedElement(), event.getType());
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
            Assert.fail("Event not found in list: " + this.toString());
        }

        public boolean isEqual(final EventData eventData)
        {
            return (_affectedElement.equals(eventData._affectedElement)
                        && _type == eventData._type);
        }

        @Override
        public String toString()
        {
            return String.format(
                    "Classpath Entry: %s",
                    _affectedElement.toString());
        }
    }

    @Override
    public EventResult acceptEvent(final ClasspathLifecycleEvent event)
    {
        _events.add(new EventData(event));
        return EventResult.getDefaultEventResult();
    }
}
