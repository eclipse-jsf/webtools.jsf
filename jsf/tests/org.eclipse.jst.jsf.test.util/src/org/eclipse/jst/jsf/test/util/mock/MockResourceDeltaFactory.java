package org.eclipse.jst.jsf.test.util.mock;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public class MockResourceDeltaFactory
{
    public MockResourceDelta createSimpleFileChange(final IFile file)
    {
        return newChangeDelta(file, IResourceDelta.CHANGED |IResourceDelta.CONTENT);
    }

    public MockResourceDelta createSimpleFileAdded(final IFile file)
    {
        return newAddDelta(file, IResourceDelta.ADDED);
    }

    public MockResourceDelta createSimpleFolderAdded(final IFolder folder)
    {
        return newAddDelta(folder, IResourceDelta.ADDED);
    }

    public MockResourceDelta createSimpleFileRemoved(final IFile file)
    {
        return newRemoveDelta(file, IResourceDelta.REMOVED);
    }

    public MockResourceDelta createSimpleFolderRemoved(final IFolder folder)
    {
        return newRemoveDelta(folder, IResourceDelta.REMOVED);
    }

    /**
     * @param startLocation
     * @param endLocation
     * @param movedType Either IResourceDelta.MOVED_FROM or IResourceDelta.MOVED_TO
     * @return the mock delta
     */
    public MockResourceDelta createFileMoved(final IFile startLocation,
            final IFile endLocation, final int movedType)
    {
        //TODO: need to implement multiple deltas
        throw new UnsupportedOperationException();
//        if (movedType != IResourceDelta.MOVED_FROM && movedType != IResourceDelta.MOVED_TO)
//        {
//            throw new IllegalArgumentException("movedType must be MOVED_FROM or MOVED_TO");
//        }
//        return new MockResourceDelta(endLocation, startLocation,
//                IResourceDelta.CHANGED | movedType, Collections.EMPTY_LIST);
    }

    @SuppressWarnings("unchecked")
    protected MockResourceDelta newChangeDelta(final IResource resource, final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(resource, resource, kind,
                Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }

    @SuppressWarnings("unchecked")
    protected MockResourceDelta newAddDelta(final IResource resource, final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(resource, null, kind,
                Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }
    
    @SuppressWarnings("unchecked")
    protected MockResourceDelta newRemoveDelta(final IResource resource, final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(null, resource, kind,
                Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }

    /**
     * @return a delta that doesn't represent a change itself but has descendants
     * that do.
     */
    public MockResourceDelta createPathToChangeDelta(final IResource res, final List<MockResourceDelta> descendants)
    {
        return new MockResourceDelta(res,res,0, descendants);
    }
    
    protected MockResourceDelta createWorkspaceRootedDeltaTo(final IResource res, final MockResourceDelta leafRes)
    {
        MockResourceDelta curDelta = leafRes;
        IContainer parent = res.getParent();
        while (parent != null)
        {
            curDelta = createPathToChangeDelta(parent, Collections.singletonList(curDelta));
            parent = parent.getParent();
        }
        return curDelta;
    }
}
