package org.eclipse.jst.jsf.core.tests.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockFile;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class FastLifecycleListenerTests
{
    private MockWorkspaceContext _wsContext;
    private MockResourceChangeEventFactory _factory;
    private MockProject _project;
    private MockFile _file;

    @Before
    public void setUp() throws Exception
    {
        _wsContext = new MockWorkspaceContext();
        _project = _wsContext.createProject("SomeTestProject");
        _file = (MockFile) _project.getFile("myfile.txt");
        _factory = new MockResourceChangeEventFactory();
    }

    @Test
    public void testAddRemoveListener()
    {
        final LifecycleListener listener = new LifecycleListener(_file,
                _wsContext.getWorkspace());
        final MyListener tester = new MyListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory.createSimpleFileChange(
                _file, true);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_CHANGED,
                ReasonType.RESOURCE_CHANGED_CONTENTS);
        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

    @Test
    public void testAddRemoveResource()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyListener tester = new MyListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory.createSimpleFileChange(
                _file, true);
        tester.fireAndExpectNull(event);
        assertEquals(0, _wsContext.getWorkspace().getListeners().size());

        // now add the resource and verify the event
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_CHANGED,
                ReasonType.RESOURCE_CHANGED_CONTENTS);
        assertEquals(1, _wsContext.getWorkspace().getListeners().size());

        // remove it again and verify no event.
        listener.removeResource(_file);
        tester.fireAndExpectNull(event);
        assertEquals(0, _wsContext.getWorkspace().getListeners().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testDispose_AddListener()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        listener.dispose();
        listener.addListener(new MyListener(_wsContext));
    }

    @Test(expected = IllegalStateException.class)
    public void testDispose_RemoveListener()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        listener.dispose();
        listener.removeListener(new MyListener(_wsContext));
    }

    @Test
    public void testDispose_AddRemoveResource()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        listener.dispose();
        listener.addResource(_file);
        listener.removeResource(_file);
    }

    @Test
    public void testFileChangedEvents()
    {
        final LifecycleListener listener = new LifecycleListener(_file,
                _wsContext.getWorkspace());
        final MyListener tester = new MyListener(_wsContext, listener);
        IResourceChangeEvent event = _factory.createSimpleFileChange(_file,
                true);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_CHANGED,
                ReasonType.RESOURCE_CHANGED_CONTENTS);

        event = _factory.createSimpleFileRemove(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_DELETED);

        event = _factory.createSimpleFileAdded(_file);

        // we are listening for the file so this fire an added
        tester.fireAndExpect(event, _file, EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_ADDED);
        // now remove the file and expect null
        listener.removeResource(_file);
        tester.fireAndExpectNull(event);

        // now add the file's parent and fire again
        listener.addResource(_file.getParent());
        tester.fireAndExpect(event, _file, EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_ADDED);
    }

    @Test
    public void testProjectChangeEvents_ProjectClosed()
    {
        final LifecycleListener listener = new LifecycleListener(_project,
                _wsContext.getWorkspace());
        final MyListener tester = new MyListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleProjectClosed(_project);
        tester.fireAndExpect(event, _project, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_PROJECT_CLOSED);

        // remove the project and add the file
        listener.removeResource(_project);
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    @Test
    public void testProjectChangeEvents_ProjectDeleted()
    {
        final LifecycleListener listener = new LifecycleListener(_project,
                _wsContext.getWorkspace());
        final MyListener tester = new MyListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleProjectDeleted(_project);
        tester.fireAndExpect(event, _project, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_PROJECT_CLOSED);

        // remove the project and add the file
        listener.removeResource(_project);
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    private static class MyListener implements IResourceLifecycleListener
    {
        private ResourceLifecycleEvent _event;
        private MockWorkspaceContext  _wsContext;

        public MyListener(final MockWorkspaceContext wsContext)
        {
            _wsContext = wsContext;
        }

        public MyListener(final MockWorkspaceContext wsContext, final LifecycleListener listener)
        {
            listener.addListener(this);
            _wsContext = wsContext;
        }

        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            _event = event;
            return EventResult.getDefaultEventResult();
        }

        public void fireAndExpect(final IResourceChangeEvent fire,
                final IResource expectedResource, final EventType eventType,
                final ReasonType reasonType)
        {
            fireEvent(fire);
            assertNotNull(_event);
            assertSame(expectedResource, _event.getAffectedResource());
        }

        public void fireAndExpectNull(final IResourceChangeEvent fire)
        {
            fireEvent(fire);
            assertNull(_event);
        }

        protected void fireEvent(final IResourceChangeEvent event)
        {
            _event = null;
            _wsContext.fireWorkspaceEvent(event);
        }
    }
}
