package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.ResourceManager;
import org.eclipse.jst.jsf.common.internal.resource.ResourceTracker;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.WebappConfiguration.WebappListener;

class TaglibResourceManager extends ResourceManager<IFile>
{
    private ILibraryChangeHandler _handler;
    private final WebappConfiguration _webAppConfiguration;

    public TaglibResourceManager(final IProject project,
            final ILibraryChangeHandler handler,
            final WorkspaceMediator wsMediator, 
            final WebappConfiguration webAppConfiguration)
    {
        super(project.getWorkspace());
        _handler = handler;
        _webAppConfiguration = webAppConfiguration;
    }

    @Override
    public List<IFile> getResources()
    {
        return _webAppConfiguration.getFiles();
    }

    @Override
    public void initResources()
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
    protected ResourceTracker createNewInstance(final IResource resource)
    {
        if (resource.getType() == IResource.FILE)
        {
            return new TaglibFileTracker((IFile) resource, this, _handler);
        }
        throw new IllegalArgumentException();
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
        super.dispose();
    }
}