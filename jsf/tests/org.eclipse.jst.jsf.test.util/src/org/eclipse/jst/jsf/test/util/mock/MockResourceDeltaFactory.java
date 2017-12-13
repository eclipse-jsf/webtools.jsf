package org.eclipse.jst.jsf.test.util.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;

public class MockResourceDeltaFactory
{
    // private final MockWorkspaceContext _wsContext;
    public MockResourceDeltaFactory(final IWorkspaceContext wsContext)
    {
        // _wsContext = wsContext;
    }

    private void checkResourceValid(final IResource res)
    {
        if (res == null)
        {
            throw new IllegalArgumentException();
        }
    }

    public MockResourceDelta createSimpleFileChange(final IFile file)
    {
        checkResourceValid(file);
        return newChangeDelta(file, IResourceDelta.CHANGED
                | IResourceDelta.CONTENT);
    }

    public MockResourceDelta createSimpleFileAdded(final IFile file)
    {
        return createSimpleFileAdded(file, IResourceDelta.ADDED);
    }

    public MockResourceDelta createSimpleFileAdded(final IFile file,
            final int kind)
    {
        checkResourceValid(file);
        return newAddDelta(file, kind);
    }

    public MockResourceDelta createSimpleFolderAdded(final IFolder folder)
    {
        return createSimpleFolderAdded(folder, IResourceDelta.ADDED);
    }

    public MockResourceDelta createSimpleFolderAdded(final IFolder folder,
            final int kind)
    {
        checkResourceValid(folder);
        final MockResourceDelta root = newAddDelta(folder, kind);
        // MockResourceDelta folderAdd = (MockResourceDelta) root
        // .findMember(folder.getFullPath());
        //
        // if (folderAdd == null)
        // {
        // throw new IllegalStateException();
        // }
        //
        // // TODO: add recursive folders
        // try
        // {
        // for (final IResource child : folder.members())
        // {
        // if (child.getType() == IResource.FILE)
        // {
        // MockResourceDelta newAddDelta = new MockResourceDelta(child, null,
        // kind, Collections.EMPTY_LIST);
        // folderAdd.getChildDeltas().add(newAddDelta);
        // }
        // }
        // } catch (CoreException e)
        // {
        // throw new RuntimeException(e);
        // }
        return root;
    }

    public MockResourceDelta createSimpleFileRemoved(final IFile file)
    {
        return createSimpleFileRemoved(file, IResourceDelta.REMOVED);
    }

    public MockResourceDelta createSimpleFileRemoved(final IFile file,
            final int kind)
    {
        checkResourceValid(file);
        return newRemoveDelta(file, kind);
    }

    public MockResourceDelta createSimpleFolderRemoved(final IFolder folder)
    {
        return createSimpleFolderRemoved(folder, IResourceDelta.REMOVED);
    }

    public MockResourceDelta createSimpleFolderRemoved(final IFolder folder,
            final int kind)
    {
        checkResourceValid(folder);
        return newRemoveDelta(folder, kind);
    }

    public MockResourceDelta createRecursiveFolderRemoved(final IFolder folder)
    {
        checkResourceValid(folder);
        return newRemoveDeltaRecursive(folder, IResourceDelta.REMOVED);
    }

    @SuppressWarnings("unchecked")
    protected MockResourceDelta newChangeDelta(final IResource resource,
            final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(resource,
                resource, kind, Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }

    @SuppressWarnings("unchecked")
    protected MockResourceDelta newAddDelta(final IResource resource,
            final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(resource, null,
                kind, Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }

    @SuppressWarnings("unchecked")
    protected MockResourceDelta newRemoveDelta(final IResource resource,
            final int kind)
    {
        final MockResourceDelta delta = new MockResourceDelta(null, resource,
                kind, Collections.EMPTY_LIST);
        return createWorkspaceRootedDeltaTo(resource, delta);
    }

    private MockResourceDelta newRemoveDeltaRecursive(final IFolder folder,
            final int kind)
    {
        final MockResourceDelta delta = recursiveContainerRemoved(folder, kind);
        return createWorkspaceRootedDeltaTo(folder, delta);
    }

    private MockResourceDelta recursiveContainerRemoved(
            final IResource rootRes, final int kind)
    {
        List<MockResourceDelta> childDeltas = new ArrayList<MockResourceDelta>();
        try
        {
            if (rootRes instanceof IContainer)
            {
                for (final IResource res : ((IContainer) rootRes).members())
                {
                    childDeltas.add(recursiveContainerRemoved(res, kind));
                }
            }
            return new MockResourceDelta(null, rootRes, kind, childDeltas);
        } catch (CoreException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return a delta that doesn't represent a change itself but has
     *         descendants that do.
     */
    public MockResourceDelta createPathToChangeDelta(final IResource res,
            final List<MockResourceDelta> descendants)
    {
        return new MockResourceDelta(res, res, 0, descendants);
    }

    protected MockResourceDelta createWorkspaceRootedDeltaTo(
            final IResource res, final MockResourceDelta leafRes)
    {
        MockResourceDelta curDelta = leafRes;
        IContainer parent = res.getParent();
        while (parent != null)
        {
            curDelta = createPathToChangeDelta(parent,
                    Collections.singletonList(curDelta));
            parent = parent.getParent();
        }
        return curDelta;
    }

    public MockResourceDelta createFileMove(final IFile file,
            final IFile newFile)
    {
        final MockResourceDelta fileAddEvent = createSimpleFileAdded(newFile,
                IResourceDelta.ADDED | IResourceDelta.MOVED_FROM);
        final MockResourceDelta fileRemoveEvent = createSimpleFileRemoved(file,
                IResourceDelta.REMOVED | IResourceDelta.MOVED_TO);
        return merge(file.getWorkspace().getRoot(), fileAddEvent,
                fileRemoveEvent);
    }

    public MockResourceDelta createFileRename(final IFile file,
            final IFile newFile)
    {
        Assert.assertEquals(file.getParent(), newFile.getParent());
        return createFileMove(file, newFile);
    }

    public MockResourceDelta createFolderRename(final IFolder folder,
            final IFolder newFolder)
    {
        Assert.assertEquals(folder.getParent(), newFolder.getParent());
        return createFolderMove(folder, newFolder);
    }

    public MockResourceDelta createFolderMove(final IFolder folder,
            final IFolder newFolder)
    {
        final MockResourceDelta fileAddEvent = createSimpleFolderAdded(
                newFolder, IResourceDelta.ADDED | IResourceDelta.MOVED_FROM);
        final MockResourceDelta fileRemoveEvent = createSimpleFolderRemoved(
                folder, IResourceDelta.REMOVED | IResourceDelta.MOVED_TO);
        return merge(folder.getWorkspace().getRoot(), fileAddEvent,
                fileRemoveEvent);
    }

    private MockResourceDelta merge(final IWorkspaceRoot root,
            final MockResourceDelta... deltas)
    {
        final MergeVisitor visitor = new MergeVisitor(root);
        for (final MockResourceDelta delta : deltas)
        {
            try
            {
                delta.accept(visitor);
            } catch (final CoreException e)
            {
                throw new RuntimeException(e);
            }
        }
        return visitor.getNewDelta();
    }

    private final static class MergeVisitor implements IResourceDeltaVisitor
    {
        private final Map<IResource, MockResourceDelta> _merged = new HashMap<IResource, MockResourceDelta>();
        private final IWorkspaceRoot _root;

        private MergeVisitor(final IWorkspaceRoot root)
        {
            _root = root;
        }

        public boolean visit(final IResourceDelta addDelta)
                throws CoreException
        {
            final IResource res = addDelta.getResource();
            if (res == null)
            {
                throw new IllegalArgumentException();
            }
            MockResourceDelta curDelta = _merged.get(res);
            if (curDelta != null)
            {
                curDelta = curDelta.merge((MockResourceDelta) addDelta);
            } else
            {
                curDelta = (MockResourceDelta) addDelta;
            }
            _merged.put(res, curDelta);
            return true;
        }

        public final MockResourceDelta getNewDelta()
        {
            final MockResourceDelta newDelta = new MockResourceDelta(_root,
                    _root, 0, new ArrayList<MockResourceDelta>());
            addAllChildren(newDelta);
            return newDelta;
        }

        private void addAllChildren(final MockResourceDelta newDelta)
        {
            final IResource activeRes = newDelta.getResource();
            if (activeRes == null)
            {
                throw new NullPointerException();
            }
            for (final Map.Entry<IResource, MockResourceDelta> entry : _merged
                    .entrySet())
            {
                final IResource key = entry.getKey();
                if (key == null)
                {
                    throw new NullPointerException();
                }
                if (activeRes.equals(key.getParent()))
                {
                    final MockResourceDelta value = entry.getValue();
                    addAllChildren(value);
                    MockResourceDelta mergeChild = null;
                    for (final MockResourceDelta checkChild : newDelta
                            .getChildDeltas())
                    {
                        if (value.getActiveResource().equals(
                                checkChild.getActiveResource()))
                        {
                            mergeChild = checkChild;
                            break;
                        }
                    }
                    if (mergeChild != null)
                    {
                        newDelta.getChildDeltas().remove(mergeChild);
                        newDelta.getChildDeltas().add(mergeChild.merge(value));
                    } else
                    {
                        newDelta.getChildDeltas().add(value);
                    }
                }
            }
        }
    }
}
