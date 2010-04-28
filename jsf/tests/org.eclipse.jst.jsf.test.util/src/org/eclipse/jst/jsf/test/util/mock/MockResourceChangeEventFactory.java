package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeEvent;

public class MockResourceChangeEventFactory
{
    private final MockResourceDeltaFactory _deltaFactory;

    public MockResourceChangeEventFactory(
            final MockResourceDeltaFactory deltaFactory)
    {
        _deltaFactory = deltaFactory;
    }

    public MockResourceChangeEventFactory()
    {
        this(new MockResourceDeltaFactory());
    }

    public IResourceChangeEvent createSimpleFileChange(final MockFile file,
            final boolean incrementModStamp)
    {
        final MockResourceDelta delta = _deltaFactory.createSimpleFileChange(file);
        if (incrementModStamp)
        {
            file.incrementModStamp();
        }
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }

    public IResourceChangeEvent createSimpleFileRemove(final MockFile file)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFileRemoved(file);
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }

    public IResourceChangeEvent createSimpleFileAdded(final MockFile file)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFileAdded(file);
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }

    public IResourceChangeEvent createSimpleProjectClosed(
            final MockProject project)
    {
        return new MockResourceChangeEvent(project,
                IResourceChangeEvent.PRE_CLOSE, null);
    }

    public IResourceChangeEvent createSimpleProjectDeleted(
            final MockProject project)
    {
        return new MockResourceChangeEvent(project,
                IResourceChangeEvent.PRE_DELETE, null);
    }

    public IResourceChangeEvent createSimpleFolderAdded(final IFolder folder)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFolderAdded(folder);
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }

    public IResourceChangeEvent createSimpleFolderDeleted(final IFolder folder)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFolderRemoved(folder);
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }
}
