package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;

class TaglibFileTracker extends TaglibResourceTracker
{
    private final IFile _file;
    private String _uri;
    private final AtomicLong _lastModifiedStamp = new AtomicLong();
    private TaglibResourceManager _manager;
    private final ILibraryChangeHandler _handler;

    public TaglibFileTracker(final IFile file, final TaglibResourceManager manager,
            final ILibraryChangeHandler handler)
    {
        _manager = manager;
        _manager.addListener(this);
        _file = file;
        _lastModifiedStamp.set(file.getModificationStamp());
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
    public EventResult acceptEvent(final ResourceLifecycleEvent event)
    {
        if (!_file.equals(event.getAffectedResource()))
        {
            return EventResult.getDefaultEventResult();
        }

        final EventType eventType = event.getEventType();

        switch (eventType)
        {
        case RESOURCE_ADDED:
            // added resources kick an add event.
            _handler.added(_file);
            break;
        case RESOURCE_CHANGED:
            // changed resources kick a change event
            _handler.changed(_uri, _file);
            break;
        case RESOURCE_INACCESSIBLE:
            // removed resources kick a remove event
            _handler.removed(_uri, _file);
            break;
        }

        return EventResult.getDefaultEventResult();
    }
    
//    private final static class HandleFileAddJob implements IWorkspaceRunnable
//    {
//        private final IResourceChangeEvent _event;
//        private List<IFile> _files;
//        private ILibraryChangeHandler   _handler;
//        
//        private HandleFileAddJob(final IResourceChangeEvent event, final List<IFile> files,
//                final ILibraryChangeHandler handler)
//        {
//            _event = event;
//            _files = files;
//            _handler = handler;
//        }
//
//        public void run(final IProgressMonitor monitor)
//                throws CoreException
//        {
//            for (final IFile file : _files)
//            {
//                final IResourceDelta delta = _event.getDelta()
//                        .findMember(file.getFullPath());
//
//                if (delta != null)
//                {
//                    if (delta.getKind() == IResourceDelta.ADDED)
//                    {
//                        _handler.added(file);
//                    }
//                }
//            }
//        }
//    }
}