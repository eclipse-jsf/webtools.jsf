package org.eclipse.jst.jsf.core.tests.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceContext;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceRunner;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContextWithEvents;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(WorkspaceRunner.class)
@Category(NoPluginEnvironment.class)
public class FastLifecycleListenerTests
{
    @WorkspaceContext
    private IWorkspaceContextWithEvents _wsContext;
    private MockResourceChangeEventFactory _factory;
    private IProject _project;
    private IFile _file;

    @Before
    public void setUp() throws Exception
    {
        _project = _wsContext.createProject("SomeTestProject");
        _file = _project.getFile("myfile.txt");
        _factory = new MockResourceChangeEventFactory(_wsContext);
    }

    @Test
    public void testAddRemoveListener()
    {
        final LifecycleListener listener = new LifecycleListener(_file,
                _wsContext.getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
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
        final List<Class<? extends IResourceChangeListener>> includeListeners = 
            new ArrayList<Class<? extends IResourceChangeListener>>();
        includeListeners.add(LifecycleListener.class);
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory.createSimpleFileChange(
                _file, true);
        tester.fireAndExpectNull(event);
        assertEquals(0, _wsContext.getListeners(includeListeners).size());

        // now add the resource and verify the event
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_CHANGED,
                ReasonType.RESOURCE_CHANGED_CONTENTS);
        assertEquals(1, _wsContext.getListeners(includeListeners).size());

        // remove it again and verify no event.
        listener.removeResource(_file);
        tester.fireAndExpectNull(event);
        assertEquals(0, _wsContext.getListeners(includeListeners).size());
    }

    @Test(expected = IllegalStateException.class)
    public void testDispose_AddListener()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        listener.dispose();
        listener.addListener(new MyTestListener(_wsContext));
    }

    @Test(expected = IllegalStateException.class)
    public void testDispose_RemoveListener()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        listener.dispose();
        listener.removeListener(new MyTestListener(_wsContext));
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
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
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
                ReasonType.RESOURCE_ADDED_TO_CONTAINER);
    }

    @Test
    public void testProjectChangeEvents_ProjectClosed()
    {
        final LifecycleListener listener = new LifecycleListener(_project,
                _wsContext.getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleProjectClosed(_project);
        tester.fireAndExpect(event, _project, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_PROJECT_CLOSED);

        // remove the project and add the file
        listener.removeResource(_project);
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_PROJECT_CLOSED);
    }

    @Test
    public void testProjectChangeEvents_ProjectDeleted()
    {
        final LifecycleListener listener = new LifecycleListener(_project,
                _wsContext.getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleProjectDeleted(_project);
        tester.fireAndExpect(event, _project, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_DELETED);

        // remove the project and add the file
        listener.removeResource(_project);
        listener.addResource(_file);
        tester.fireAndExpect(event, _file, EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_PROJECT_DELETED);
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullTo_Workspace_Constructor()
    {
        new LifecycleListener(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullToResource_Workspace_Constructor()
    {
        boolean failed = false;
        try
        {
            new LifecycleListener((IResource) null, _wsContext.getWorkspace());
        } catch (final NullPointerException npe)
        {
            failed = true;
        }
        assertTrue(failed);

        failed = false;

        try
        {
            new LifecycleListener(_file, null);
        } catch (final NullPointerException npe)
        {
            failed = true;
        }

        assertTrue(failed);

        new LifecycleListener((IResource) null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullToListResource_Workspace_Constructor()
    {
        boolean failed = false;
        try
        {
            new LifecycleListener((List<IResource>) null, _wsContext
                    .getWorkspace());
        } catch (final NullPointerException npe)
        {
            failed = true;
        }
        assertTrue(failed);

        failed = false;
        try
        {
            new LifecycleListener(new ArrayList<IResource>(), null);
        } catch (final NullPointerException npe)
        {
            failed = true;
        }

        assertTrue(failed);

        failed = false;
        try
        {
            List<IResource> resources = new ArrayList<IResource>();
            resources.add(_file);
            resources.add(null);
            new LifecycleListener(resources, _wsContext.getWorkspace());
        } catch (NullPointerException npe)
        {
            failed = true;
        }

        assertTrue(failed);
        new LifecycleListener((IResource) null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullToAddResource()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext.getWorkspace());
        listener.addResource(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPassNullToAddListener()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext.getWorkspace());
        listener.addListener(null);
    }

}
