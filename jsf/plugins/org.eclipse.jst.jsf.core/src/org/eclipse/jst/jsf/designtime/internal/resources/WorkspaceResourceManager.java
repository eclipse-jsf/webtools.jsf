package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.FileMatchingAcceptor;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceManager;
import org.eclipse.jst.jsf.common.internal.resource.ResourceTracker;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent.CHANGE_TYPE;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Resource Manager that tracks JSF resources in a workspace.
 * 
 * @author cbateman
 * 
 */
public class WorkspaceResourceManager extends ResourceManager<IResource>
{
    private final AbstractVirtualComponentQuery _vcQuery;
    private final IProject _project;
    private final AbstractJSFResourceLocator _locator;
    private final ContentTypeResolver _contentTypeResolver;
    private final ResourceIdentifierFactory _factory;

    /**
     * @param project
     * @param vcQuery
     * @param locator
     * @param contentTypeResolver
     */
    public WorkspaceResourceManager(final IProject project,
            final AbstractVirtualComponentQuery vcQuery,
            final AbstractJSFResourceLocator locator,
            final ContentTypeResolver contentTypeResolver)
    {
        super(project.getWorkspace());
        _project = project;
        _vcQuery = vcQuery;
        _locator = locator;
        _contentTypeResolver = contentTypeResolver;
        _factory = new ResourceIdentifierFactory();
    }

    @Override
    protected JSFResourceTracker createNewInstance(final IResource resource)
    {
        return new JSFResourceTracker(resource);
    }

    @Override
    public void initResources()
    {
        final IFolder folder = getRootResourceFolder();

        if (folder != null && folder.isAccessible())
        {
            try
            {
                track(folder.getParent(), folder);
            } catch (final Exception e1)
            {
                JSFCorePlugin
                        .log(e1,
                                "While trying to locate JSF resources in the workspace"); //$NON-NLS-1$
            }

            final VisitorMatcher<IContainer, IResource, String> matcher = 
                new VisitorMatcher<IContainer, IResource, String>(
                    "", "", //$NON-NLS-1$ //$NON-NLS-2$
                    new FileMatchingAcceptor(), Collections
                            .singletonList(new AlwaysMatcher()));
            try
            {
                final Collection<? extends IResource> foundResources = matcher
                        .find(folder);
                for (final IResource res : foundResources)
                {
                    track(folder, res);
                }
            } catch (final Exception e)
            {
                JSFCorePlugin
                        .log(e,
                                "While trying to locate JSF resources in the workspace"); //$NON-NLS-1$
            }
        }
    }

    private IWorkspaceJSFResourceFragment trackRootRelative(final IResource res)
            throws ManagedObjectException, InvalidIdentifierException
    {
        return track(getRootResourceFolder(), res);
    }

    private IWorkspaceJSFResourceFragment track(
            final IContainer containerFolder, final IResource res)
            throws ManagedObjectException, InvalidIdentifierException
    {
        final IPath containerPath = containerFolder.getFullPath();

        final IPath fullPath = res.getFullPath().makeRelativeTo(containerPath);
        // cause the resource to get tracked
        final JSFResourceTracker tracker = (JSFResourceTracker) getInstance(res);
        IWorkspaceJSFResourceFragment jsfRes = null;
        if (res.getType() == IResource.FILE)
        {
            jsfRes = new WorkspaceJSFResource(_factory
                    .createLibraryResource(fullPath.toString()), res,
                    _contentTypeResolver);
        } else
        {
            jsfRes = new WorkspaceJSFResourceContainer(_factory
                    .createLibraryFragment(res.getName()), (IContainer) res);
        }
        tracker.setJsfResource(jsfRes);
        addLifecycleEventListener(tracker);
        return jsfRes;
    }

    /**
     * @return the root folder for resources in the workspace.
     */
    public IFolder getRootResourceFolder()
    {
        final IVirtualFolder webContentFolder = _vcQuery
                .getWebContentFolder(_project);
        if (webContentFolder != null
                && webContentFolder.getUnderlyingFolder().isAccessible())
        {
            return webContentFolder.getUnderlyingFolder().getFolder(
                    new Path("resources")); //$NON-NLS-1$
        }
        return null;
    }

    @Override
    public List<IResource> getResources()
    {
        return new ArrayList(getManagedResources());
    }

    /**
     * @return the jsf resource currently known. List is a copy but the
     *         contained JSFResource references are not.
     */
    public List<IJSFResourceFragment> getJSFResources()
    {
        final List<IJSFResourceFragment> jsfResources = new ArrayList<IJSFResourceFragment>();
        final Map<IResource, ManagedResourceObject<ResourceTracker<IResource>>> jsfResourceTrackers = getPerResourceObjects();

        for (final Map.Entry<IResource, ManagedResourceObject<ResourceTracker<IResource>>> entry : jsfResourceTrackers
                .entrySet())
        {
            jsfResources.add(((JSFResourceTracker) entry.getValue()
                    .getManagedObject()).getJsfResource());
        }
        return jsfResources;
    }

    private class JSFResourceTracker extends ResourceTracker<IResource>
    {
        private IWorkspaceJSFResourceFragment _jsfResource;

        public JSFResourceTracker(final IResource resource)
        {
            super(resource);
        }

        @Override
        protected void fireResourceInAccessible(final ReasonType reasonType)
        {
            removeLifecycleEventListener(this);
            _locator.fireChangeEvent(new JSFResourceChangedEvent(_locator,
                    _jsfResource, null, CHANGE_TYPE.REMOVED));
        }

        @Override
        protected void fireResourceChanged(final ReasonType reasonType)
        {
            _locator.fireChangeEvent(new JSFResourceChangedEvent(_locator,
                    _jsfResource, _jsfResource, CHANGE_TYPE.CHANGED));
        }

        @Override
        protected void fireResourceAdded(final IResource affectedResource,
                final ReasonType reasonType)
        {
            final IContainer parent = affectedResource.getParent();
            if (parent != null && parent.isAccessible())
            {
                try
                {
                    final IWorkspaceJSFResourceFragment newJsfRes = trackRootRelative(affectedResource);
                    final JSFResourceChangedEvent event = new JSFResourceChangedEvent(
                            _locator, null, newJsfRes, CHANGE_TYPE.ADDED);
                    _locator.fireChangeEvent(event);
                } catch (final ManagedObjectException e)
                {
                    JSFCorePlugin.log(e,
                            "While adding new resource " + affectedResource); //$NON-NLS-1$
                } catch (final InvalidIdentifierException e)
                {
                    JSFCorePlugin.log(e,
                            "While adding new resource " + affectedResource); //$NON-NLS-1$
                }
            }
        }

        @Override
        protected boolean isInteresting(final ResourceLifecycleEvent event)
        {
            switch (event.getEventType())
            {
                case RESOURCE_ADDED:
                    return getResource() instanceof IContainer
                            && event.getAffectedResource().getParent().equals(
                                    getResource());
                default:
                    return super.isInteresting(event);
            }
        }

        public final IWorkspaceJSFResourceFragment getJsfResource()
        {
            return _jsfResource;
        }

        public final void setJsfResource(
                final IWorkspaceJSFResourceFragment jsfResource)
        {
            _jsfResource = jsfResource;
        }
    }
}
