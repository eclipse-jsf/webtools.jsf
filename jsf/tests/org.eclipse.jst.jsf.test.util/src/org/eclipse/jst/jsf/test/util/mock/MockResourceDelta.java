package org.eclipse.jst.jsf.test.util.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.internal.runtime.AdapterManager;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class MockResourceDelta implements IResourceDelta
{
    public enum StateType
    {
        BEFORE_EVENT, AFTER_EVENT
    }

    private final IResource _afterEventResource;
    private final IResource _beforeEventResource;
    private final List<MockResourceDelta> _childDeltas;
    private final int _status;

    protected static int KIND_MASK = 0xFF;

    public MockResourceDelta(final IResource afterEventResource,
            final IResource beforeEventResource, final int status,
            final List<MockResourceDelta> childDeltas)
    {
        super();
        if (afterEventResource == null && beforeEventResource == null)
        {
            throw new IllegalArgumentException();
        }
        _afterEventResource = afterEventResource;
        _beforeEventResource = beforeEventResource;
        _status = status;
        _childDeltas = childDeltas;
    }

    /**
     * @param delta
     * @return a new resource delta that combines delta with my values. If there
     *         are conflicts, delta's value is used.
     */
    public MockResourceDelta merge(final MockResourceDelta delta)
    {
        final List<MockResourceDelta> mergedChildren = new ArrayList<MockResourceDelta>(
                _childDeltas);

        for (final MockResourceDelta mergeMe : delta._childDeltas)
        {
            for (final Iterator<MockResourceDelta> it = mergedChildren
                    .iterator(); it.hasNext();)
            {
                if (it.next().getActiveResource().equals(
                        mergeMe.getActiveResource()))
                {
                    it.remove();
                }
            }
            mergedChildren.add(mergeMe);
        }

        return new MockResourceDelta(delta._afterEventResource,
                delta._beforeEventResource, delta._status, mergedChildren);
    }

    /*
     * @see IResourceDelta#accept(IResourceDeltaVisitor)
     */
    public void accept(IResourceDeltaVisitor visitor) throws CoreException
    {
        accept(visitor, 0);
    }

    /*
     * @see IResourceDelta#accept(IResourceDeltaVisitor, boolean)
     */
    public void accept(IResourceDeltaVisitor visitor, boolean includePhantoms)
            throws CoreException
    {
        accept(visitor, includePhantoms ? IContainer.INCLUDE_PHANTOMS : 0);
    }

    /*
     * @see IResourceDelta#accept(IResourceDeltaVisitor, int)
     */
    public void accept(IResourceDeltaVisitor visitor, int memberFlags)
            throws CoreException
    {
        final boolean includePhantoms = (memberFlags & IContainer.INCLUDE_PHANTOMS) != 0;
        final boolean includeTeamPrivate = (memberFlags & IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS) != 0;
        final boolean includeHidden = (memberFlags & IContainer.INCLUDE_HIDDEN) != 0;
//        int mask = includePhantoms ? ALL_WITH_PHANTOMS : REMOVED | ADDED
//                | CHANGED;
        // if ((getKind() & mask) == 0)
        // return;
        if (!visitor.visit(this))
            return;
        for (final MockResourceDelta childDelta : _childDeltas)
        {
            // quietly exclude team-private, hidden and phantom members unless
            // explicitly included
            if (!includeTeamPrivate && childDelta.isTeamPrivate())
                continue;
            if (!includePhantoms && childDelta.isPhantom())
                continue;
            if (!includeHidden && childDelta.isHidden())
                continue;
            childDelta.accept(visitor, memberFlags);
        }
    }

    /**
     * @see IResourceDelta#findMember(IPath)
     */
    public IResourceDelta findMember(IPath path)
    {
        int segmentCount = path.segmentCount();
        if (segmentCount == 0)
            return this;

        // iterate over the path and find matching child delta
        MockResourceDelta current = this;
        segments: for (int i = 0; i < segmentCount; i++)
        {
            List<MockResourceDelta> currentChildren = current._childDeltas;
            for (int j = 0, jmax = currentChildren.size(); j < jmax; j++)
            {
                if (currentChildren.get(j).getFullPath().lastSegment().equals(
                        path.segment(i)))
                {
                    current = (MockResourceDelta) currentChildren.get(j);
                    continue segments;
                }
            }
            // matching child not found, return
            return null;
        }
        return current;
    }

    /**
     * @see IResourceDelta#getAffectedChildren()
     */
    public IResourceDelta[] getAffectedChildren()
    {
        return getAffectedChildren(ADDED | REMOVED | CHANGED, IResource.NONE);
    }

    /**
     * @see IResourceDelta#getAffectedChildren(int)
     */
    public IResourceDelta[] getAffectedChildren(int kindMask)
    {
        return getAffectedChildren(kindMask, IResource.NONE);
    }

    /*
     * @see IResourceDelta#getAffectedChildren(int, int)
     */
    public IResourceDelta[] getAffectedChildren(int kindMask, int memberFlags)
    {
        final int numChildren = _childDeltas.size();
        // if there are no children, they all match
        if (numChildren == 0)
        {
            return _childDeltas.toArray(new IResourceDelta[0]);
        }
        boolean includePhantoms = (memberFlags & IContainer.INCLUDE_PHANTOMS) != 0;
        boolean includeTeamPrivate = (memberFlags & IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS) != 0;
        boolean includeHidden = (memberFlags & IContainer.INCLUDE_HIDDEN) != 0;
        // reduce INCLUDE_PHANTOMS member flag to kind mask
        if (includePhantoms)
            kindMask |= ADDED_PHANTOM | REMOVED_PHANTOM;

        // first count the number of matches so we can allocate the exact array
        // size
        int matching = 0;
        for (int i = 0; i < numChildren; i++)
        {
            if ((_childDeltas.get(i).getKind() & kindMask) == 0)
                continue;// child has wrong kind
            if (!includePhantoms && _childDeltas.get(i).isPhantom())
                continue;
            if (!includeTeamPrivate && _childDeltas.get(i).isTeamPrivate())
                continue; // child has is a team-private member which are not
            // included
            if (!includeHidden && _childDeltas.get(i).isHidden())
                continue;
            matching++;
        }
        // use arraycopy if all match
        if (matching == numChildren)
        {
            final List<IResourceDelta> copy = new ArrayList<IResourceDelta>(
                    _childDeltas);
            return copy.toArray(new IResourceDelta[0]);
        }
        // create the appropriate sized array and fill it
        IResourceDelta[] result = new IResourceDelta[matching];
        int nextPosition = 0;
        for (int i = 0; i < numChildren; i++)
        {
            if ((_childDeltas.get(i).getKind() & kindMask) == 0)
                continue; // child has wrong kind
            if (!includePhantoms && _childDeltas.get(i).isPhantom())
                continue;
            if (!includeTeamPrivate && _childDeltas.get(i).isTeamPrivate())
                continue; // child has is a team-private member which are not
            // included
            if (!includeHidden && _childDeltas.get(i).isHidden())
                continue;
            result[nextPosition++] = _childDeltas.get(i);
        }
        return result;
    }

    /**
     * @see IResourceDelta#getFlags()
     */
    public int getFlags()
    {
        return _status & ~KIND_MASK;
    }

    private boolean isSet(final int flag)
    {
        return (getFlags() & flag) != 0;
    }

    /**
     * @see IResourceDelta#getFullPath()
     */
    public IPath getFullPath()
    {
        return getActiveResource().getFullPath();
    }

    /**
     * @see IResourceDelta#getKind()
     */
    public int getKind()
    {
        return _status & KIND_MASK;
    }

    public IMarkerDelta[] getMarkerDeltas()
    {
        throw new UnsupportedOperationException(
                "getMarkerDeltas not supported by the mock");
    }

    public IPath getMovedFromPath()
    {
        if (isSet(MOVED_FROM))
        {
            return _beforeEventResource.getFullPath();
        }
        return null;
    }

    public IPath getMovedToPath()
    {
        if (isSet(MOVED_TO))
        {
            return _afterEventResource.getFullPath();
        }
        return null;
    }

    public IPath getProjectRelativePath()
    {
        return _beforeEventResource.getProjectRelativePath();
    }

    public IResource getResource()
    {
        return getActiveResource();
    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter)
    {
        return AdapterManager.getDefault().getAdapter(this, adapter);
    }

    public IResource getActiveResource()
    {
        switch (getStateType())
        {
            case AFTER_EVENT:
                return _afterEventResource;
            case BEFORE_EVENT:
                return _beforeEventResource;
            default:
                throw new IllegalStateException("Should not get here");
        }
    }

    public final List<MockResourceDelta> getChildDeltas()
    {
        return _childDeltas;
    }

    private StateType getStateType()
    {
        if (getKind() == ADDED || getKind() == ADDED_PHANTOM
                || isSet(MOVED_FROM))
        {
            return StateType.AFTER_EVENT;
        }
        return StateType.BEFORE_EVENT;
    }

    /**
     * Returns true if this delta represents a hidden member, and false
     * otherwise.
     */
    protected boolean isHidden()
    {
        // // use old info for removals, and new info for added or changed
        // if ((_status & (REMOVED | REMOVED_PHANTOM)) != 0)
        // return ResourceInfo.isSet(oldInfo.getFlags(),
        // ICoreConstants.M_HIDDEN);
        // return ResourceInfo.isSet(newInfo.getFlags(),
        // ICoreConstants.M_HIDDEN);
        return false;
    }

    /**
     * Returns true if this delta represents a phantom member, and false
     * otherwise.
     */
    protected boolean isPhantom()
    {
        // // use old info for removals, and new info for added or changed
        // if ((_status & (REMOVED | REMOVED_PHANTOM)) != 0)
        // return ResourceInfo.isSet(oldInfo.getFlags(),
        // ICoreConstants.M_PHANTOM);
        // return ResourceInfo.isSet(newInfo.getFlags(),
        // ICoreConstants.M_PHANTOM);
        return false;
    }

    /**
     * Returns true if this delta represents a team private member, and false
     * otherwise.
     */
    protected boolean isTeamPrivate()
    {
        // // use old info for removals, and new info for added or changed
        // if ((_status & (REMOVED | REMOVED_PHANTOM)) != 0)
        // return ResourceInfo.isSet(oldInfo.getFlags(),
        // ICoreConstants.M_TEAM_PRIVATE_MEMBER);
        // return ResourceInfo.isSet(newInfo.getFlags(),
        // ICoreConstants.M_TEAM_PRIVATE_MEMBER);
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("%s [delta]", getActiveResource().getFullPath());
    }

}
