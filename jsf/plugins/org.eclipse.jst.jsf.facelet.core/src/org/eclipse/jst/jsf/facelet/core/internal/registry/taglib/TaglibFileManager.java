package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.ContextParamSpecifiedFaceletTaglibLocator.LibraryChangeHandler;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.ContextParamSpecifiedFaceletTaglibLocator.TaglibFileTracker;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.WebappConfiguration.WebappListener;

class TaglibFileManager extends
        ResourceSingletonObjectManager<TaglibFileTracker, IFile>
{
    private final LibraryChangeHandler _handler;
    private final WebappConfiguration _webAppConfiguration;
    private final IResourceChangeListener _newFileListener;

    public TaglibFileManager(final IProject project,
            final LibraryChangeHandler handler,
            final IModelProvider webAppProvider,
            final AbstractVirtualComponentQuery vcQuery,
            final WorkspaceMediator wsMediator)
    {
        super(project.getWorkspace());
        _handler = handler;
        _webAppConfiguration = new WebappConfiguration(project, webAppProvider,
                vcQuery);
        // TODO: fold into LifecycleListener
        _newFileListener = new IResourceChangeListener()
        {
            public void resourceChanged(final IResourceChangeEvent event)
            {
                // if the event is post change && has the same parent
                // project
                if (event.getType() == IResourceChangeEvent.POST_CHANGE
                        && event.getDelta().findMember(project.getFullPath()) != null)
                {
                    wsMediator.runInWorkspaceJob(new IWorkspaceRunnable()
                    {

                        public void run(final IProgressMonitor monitor)
                                throws CoreException
                        {
                            for (final IFile file : _webAppConfiguration
                                    .getFiles())
                            {
                                final IResourceDelta delta = event.getDelta()
                                        .findMember(file.getFullPath());

                                if (delta != null)
                                {
                                    if (delta.getKind() == IResourceDelta.ADDED)
                                    {

                                        _handler.added(file);
                                    }
                                }
                            }
                        }
                    }, "Context param update"); //$NON-NLS-1$
                }
            }
        };

        getWorkspace().addResourceChangeListener(_newFileListener);
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
                    try
                    {
                        tracker = getInstance(file);
                        _handler.removed(tracker._uri, file);
                    } catch (final ManagedObjectException e)
                    {
                        FaceletCorePlugin.log(
                                "While removing for webapp change", e); //$NON-NLS-1$
                    }
                }

                for (final IFile file : event.getAdded())
                {
                    _handler.added(file);
                }
            }
        });
    }

    @Override
    protected TaglibFileTracker createNewInstance(final IFile file)
    {
        return new TaglibFileTracker(file, this, _handler);
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
        getWorkspace().removeResourceChangeListener(_newFileListener);
        super.dispose();
    }
}