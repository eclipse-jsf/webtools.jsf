package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.WebappConfiguration.WebappListener;

class TaglibResourceManager extends
        ResourceSingletonObjectManager<TaglibResourceTracker, IResource>
{
    private ILibraryChangeHandler _handler;
    private final WebappConfiguration _webAppConfiguration;
//    private final IResourceChangeListener _newFileListener;

    public TaglibResourceManager(final IProject project,
            final ILibraryChangeHandler handler,
            final WorkspaceMediator wsMediator, 
            final WebappConfiguration webAppConfiguration)
    {
        super(project.getWorkspace());
        _handler = handler;
        _webAppConfiguration = webAppConfiguration;
        // TODO: fold into LifecycleListener
//        _newFileListener = new IResourceChangeListener()
//        {
//            public void resourceChanged(final IResourceChangeEvent event)
//            {
//                // if the event is post change && has the same parent
//                // project
//                if (event.getType() == IResourceChangeEvent.POST_CHANGE
//                        && event.getDelta().findMember(project.getFullPath()) != null)
//                {
//                    wsMediator.runInWorkspaceJob(new HandleFileAddJob(event), "Context param update"); //$NON-NLS-1$
//                }
//            }
//        };

//        getWorkspace().addResourceChangeListener(_newFileListener);
    }

    public List<IFile> getFiles()
    {
        return _webAppConfiguration.getFiles();
    }

    public void initFiles()
    {
        _webAppConfiguration.start();
        _webAppConfiguration.addListener(new WebappListener()
        {
            @Override
            public void webappChanged(final WebappChangeEvent event)
            {
                for (final IFile file : event.getRemoved())
                {
                    TaglibFileTracker tracker;
                    // only fire removals on files that we are already managing: you can't remove
                    // a file that is not there.
                    if (isInstance(file))
                    {
                        tracker = (TaglibFileTracker) unmanageResource(file);
                        _handler.removed(tracker.getUri(), file);
                    }
                }

                for (final IFile file : event.getAdded())
                {
                    _handler.added(file);
                }
            }
        });
        List<IFile> files = _webAppConfiguration.getFiles();
        for (final IFile file : files)
        {
            // add files to ensure we get add events for ones that don't exist.
            try
            {
                getInstance(file);
            } catch (ManagedObjectException e)
            {
                FaceletCorePlugin.log("While starting the taglib resource manager", e); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected TaglibResourceTracker createNewInstance(final IResource resource)
    {
        if (resource.getType() == IResource.FILE)
        {
            return new TaglibFileTracker((IFile) resource, this, _handler);
        }
        throw new IllegalArgumentException();
    }

    public void addListener(final IResourceLifecycleListener listener)
    {
        super.addLifecycleEventListener(listener);
    }

    public void removeListener(final IResourceLifecycleListener listener)
    {
        super.removeLifecycleEventListener(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager
     * #dispose()
     */
    @Override
    public void dispose()
    {
        _webAppConfiguration.dispose();
//        getWorkspace().removeResourceChangeListener(_newFileListener);
        super.dispose();
    }
}