package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;

public class MockResourceChangeEvent implements IResourceChangeEvent
{
    private final MockResourceDelta _delta;
    private final IResource _resource;
    private final int _type;

    /**
     * @param resource
     * @param type
     * @param delta
     */
    public MockResourceChangeEvent(final IResource resource, final int type, final MockResourceDelta delta)
    {
        _delta = delta;
        _resource = resource;
        _type = type;
    }
    public MockResourceChangeEvent(final int type, final MockResourceDelta delta)
    {
        this(null, type, delta);
    }

    public IMarkerDelta[] findMarkerDeltas(final String type, final boolean includeSubtypes)
    {
        throw new UnsupportedOperationException();
    }

    public int getBuildKind()
    {
        throw new UnsupportedOperationException();
    }

    public IResourceDelta getDelta()
    {
        return _delta;
    }

    public IResource getResource()
    {
        return _resource;
    }

    public Object getSource()
    {
        throw new UnsupportedOperationException();
    }

    public int getType()
    {
        return _type;
    }

}
