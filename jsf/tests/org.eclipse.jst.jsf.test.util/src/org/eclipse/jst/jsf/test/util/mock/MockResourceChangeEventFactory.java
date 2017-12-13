package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;

public class MockResourceChangeEventFactory
{
    private final MockResourceDeltaFactory _deltaFactory;

    public MockResourceChangeEventFactory(
            final MockResourceDeltaFactory deltaFactory)
    {
        _deltaFactory = deltaFactory;
    }

    public MockResourceChangeEventFactory(final IWorkspaceContext wsContext)
    {
        this(new MockResourceDeltaFactory(wsContext));
    }

    public IResourceChangeEvent createSimpleFileChange(final IFile file,
            final boolean incrementModStamp)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFileChange(file);
        if (incrementModStamp && file instanceof MockFile)
        {
            ((MockResource) file).incrementModStamp();
        }
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createSimpleFileRemove(final IFile file)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFileRemoved(file);
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createSimpleFileAdded(final IFile file)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFileAdded(file);
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createSimpleProjectClosed(
            final IProject project)
    {
        return new MockResourceChangeEvent(project,
                IResourceChangeEvent.PRE_CLOSE, null);
    }

    public IResourceChangeEvent createSimpleProjectDeleted(
            final IProject project)
    {
        return new MockResourceChangeEvent(project,
                IResourceChangeEvent.PRE_DELETE, null);
    }

    public IResourceChangeEvent createSimpleFolderAdded(final IFolder folder)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFolderAdded(folder);
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createSimpleFolderDeleted(final IFolder folder)
    {
        final MockResourceDelta delta = _deltaFactory
                .createSimpleFolderRemoved(folder);
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createRecursiveFolderDeleted(final IFolder folder)
    {
        final MockResourceDelta delta = _deltaFactory
                .createRecursiveFolderRemoved(folder);
        return newPostChangeEvent(delta);
    }

    public IResourceChangeEvent createSimpleFolderRename(final IFolder folder,
            final IFolder newFolderName)
    {
        final MockResourceDelta delta = _deltaFactory.createFolderRename(folder,
                newFolderName);

        return newPostChangeEvent(delta);
    }
    
    public IResourceChangeEvent createSimpleFileRename(final IFile file,
            final IFile newFile)
    {
        final MockResourceDelta delta = _deltaFactory.createFileRename(file, newFile);
        return newPostChangeEvent(delta);
    }

    private MockResourceChangeEvent newPostChangeEvent(final MockResourceDelta delta)
    {
        return new MockResourceChangeEvent(IResourceChangeEvent.POST_CHANGE,
                delta);
    }
}
