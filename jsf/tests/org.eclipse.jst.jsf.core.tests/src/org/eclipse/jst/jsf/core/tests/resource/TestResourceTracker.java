package org.eclipse.jst.jsf.core.tests.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceTracker;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockFile;
import org.eclipse.jst.jsf.test.util.mock.MockProject;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestResourceTracker
{

    private static class TestableResourceTracker extends
            ResourceTracker<IResource>
    {
        private boolean _inAccessibleFired;
        private ReasonType _inAccessibleReason;
        private boolean _changeFired;
        private ReasonType _changeReason;
        private boolean _addFired;
        private ReasonType _addReason;

        public TestableResourceTracker(final IResource resource)
        {
            super(resource);
        }

        @Override
        protected void fireResourceInAccessible(final ReasonType reasonType)
        {
            _inAccessibleFired = true;
            _inAccessibleReason = reasonType;
        }

        @Override
        protected void fireResourceChanged(final ReasonType reasonType)
        {
            _changeFired = true;
            _changeReason = reasonType;
        }

        @Override
        protected void fireResourceAdded(final IResource affectedResource,
                final ReasonType reasonType)
        {
            _addFired = true;
            _addReason = reasonType;
        }

        public final boolean isInAccessibleFired()
        {
            return _inAccessibleFired;
        }

        public final ReasonType getInAccessibleReason()
        {
            return _inAccessibleReason;
        }

        public final boolean isChangeFired()
        {
            return _changeFired;
        }

        public final ReasonType getChangeReason()
        {
            return _changeReason;
        }

        public final boolean isAddFired()
        {
            return _addFired;
        }

        public final ReasonType getAddReason()
        {
            return _addReason;
        }

    }

    private MockResourceChangeEventFactory _eventFactory;
    private MockWorkspaceContext _wsContext;
    private MockFile _testResource;
    private TestableResourceTracker _resourceTracker;
    private LifecycleListener _lifecycleListener;
    private MockFile _uninterestedInRes;

    @Before
    public void setUp() throws Exception
    {
        _eventFactory = new MockResourceChangeEventFactory();
        _wsContext = new MockWorkspaceContext();
        final MockProject createProject = _wsContext.createProject(new Path(
                "TestResourceTracker_Project"));
        _testResource = (MockFile) createProject
                .getFile("/WebContent/resources/foo/resource/somelib/foo.xhtml");
        _uninterestedInRes = (MockFile) createProject
                .getFile("/WebContent/resources/foo/resource/uninterestedInMe/foo.xhtml");
        _resourceTracker = new TestableResourceTracker(_testResource);
        _lifecycleListener = new LifecycleListener(_wsContext.getWorkspace());
        _lifecycleListener.addListener(_resourceTracker);
        _lifecycleListener.addResource(_testResource);
        _lifecycleListener.addResource(_uninterestedInRes);
    }

    @After
    public void tearDown()
    {
        _resourceTracker.dispose();
    }

    @Test
    public void testGetResource()
    {
        assertSame(_testResource, _resourceTracker.getResource());
    }

    @Test
    public void testGetLastModifiedStamp()
    {
        assertEquals(0, _resourceTracker.getLastModifiedStamp());
    }

    @Test
    public void testFireResourceInAccessible()
    {
        final IResourceChangeEvent event = _eventFactory
                .createSimpleProjectDeleted((MockProject) _testResource
                        .getProject());
        _wsContext.fireWorkspaceEvent(event);
        assertTrue(_resourceTracker.isInAccessibleFired());
        assertEquals(ReasonType.RESOURCE_PROJECT_DELETED, _resourceTracker
                .getInAccessibleReason());
        assertFalse(_resourceTracker.isAddFired());
        assertFalse(_resourceTracker.isChangeFired());
    }

    @Test
    public void testFireResourceChanged()
    {
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileChange(_testResource, true);
        _wsContext.fireWorkspaceEvent(event);
        assertTrue(_resourceTracker.isChangeFired());
        assertEquals(ReasonType.RESOURCE_CHANGED_CONTENTS, _resourceTracker
                .getChangeReason());
        assertFalse(_resourceTracker.isAddFired());
        assertFalse(_resourceTracker.isInAccessibleFired());
    }

    @Test
    public void testFireResourceAdded()
    {
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileAdded(_testResource);
        _wsContext.fireWorkspaceEvent(event);
        assertTrue(_resourceTracker.isAddFired());
        assertEquals(ReasonType.RESOURCE_ADDED, _resourceTracker.getAddReason());
        assertFalse(_resourceTracker.isInAccessibleFired());
        assertFalse(_resourceTracker.isChangeFired());
    }

    @Test
    public void testResourceFilter()
    {
        final IResourceChangeEvent event = _eventFactory
                .createSimpleFileChange(_uninterestedInRes, true);
        _wsContext.fireWorkspaceEvent(event);
        assertFalse(_resourceTracker.isInAccessibleFired());
        assertFalse(_resourceTracker.isAddFired());
        assertFalse(_resourceTracker.isChangeFired());
    }
}
