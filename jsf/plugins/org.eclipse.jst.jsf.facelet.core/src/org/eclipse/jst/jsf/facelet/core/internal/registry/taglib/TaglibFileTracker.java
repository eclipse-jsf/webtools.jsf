package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceTracker;

class TaglibFileTracker extends ResourceTracker<IFile>
{
    private String _uri;
    private TaglibResourceManager _manager;
    private final ILibraryChangeHandler _handler;

    public TaglibFileTracker(final IFile file, final TaglibResourceManager manager,
            final ILibraryChangeHandler handler)
    {
        super(file);
        _manager = manager;
        _manager.addListener(this);
        _handler = handler;
    }

    public String getUri()
    {
        return _uri;
    }

    public final void setUri(final String uri)
    {
        _uri = uri;
    }

    @Override
    public void dispose()
    {
        _manager.removeListener(this);
        _manager = null;
    }

    @Override
    protected void fireResourceInAccessible(final ReasonType reasonType)
    {
        // removed resources kick a remove event
        _handler.removed(_uri, getResource());
    }

    @Override
    protected void fireResourceChanged(final ReasonType reasonType)
    {
        // changed resources kick a change event
        _handler.changed(_uri, getResource());
    }

    @Override
    protected void fireResourceAdded(final IResource affectedResource, final ReasonType reasonType)
    {
        // added resources kick an add event.
        _handler.added(getResource());
    }
}