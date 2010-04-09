package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * @author cbateman
 * 
 */
public class WorkspaceJSFResourceLocator extends AbstractJSFResourceLocator
{
    private final AbstractVirtualComponentQuery _vcQuery;
    private final ContentTypeResolver _contentTypeResolver;

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     * @param vcQuery 
     * @param contentTypeResolver 
     */
    public WorkspaceJSFResourceLocator(
            final String id,
            final String displayName,
            final List<JSFResource> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final AbstractVirtualComponentQuery vcQuery,
            final ContentTypeResolver contentTypeResolver)
    {
        super(id, displayName, noResultValue, mutableListenerList);
        _vcQuery = vcQuery;
        _contentTypeResolver = contentTypeResolver;
    }

    /**
     * @param noResultValue
     * @param mutableListenerList
     * @param vcQuery
     * @param contentTypeResolver 
     */
    public WorkspaceJSFResourceLocator(
            final List<JSFResource> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final AbstractVirtualComponentQuery vcQuery, final ContentTypeResolver contentTypeResolver)
    {
        this(
                "", "", noResultValue, mutableListenerList, vcQuery, contentTypeResolver); //$NON-NLS-1$//$NON-NLS-2$
    }

    @Override
    protected List<JSFResource> doLocate(final IProject project)
    {
        final List<JSFResource> located = new ArrayList<JSFResource>();
        final IVirtualFolder webContentFolder = _vcQuery
                .getWebContentFolder(project);
        if (webContentFolder != null
                && webContentFolder.getUnderlyingFolder().isAccessible())
        {
            final IFolder folder = webContentFolder.getUnderlyingFolder()
                    .getFolder(new Path("resources")); //$NON-NLS-1$
            if (folder != null && folder.isAccessible())
            {
                final VisitorMatcher<IContainer, IResource, String> matcher = new VisitorMatcher<IContainer, IResource, String>(
                        "", "", //$NON-NLS-1$ //$NON-NLS-2$
                        new FileMatchingAcceptor(), Collections
                                .singletonList(new AlwaysMatcher()));
                try
                {
                    final IPath containerPath = folder.getFullPath();
                    final ResourceIdentifierFactory factory = new ResourceIdentifierFactory();
                    final Collection<? extends IResource> foundResources = matcher
                            .find(folder);
                    for (final IResource res : foundResources)
                    {
                        final IPath fullPath = res.getFullPath()
                                .makeRelativeTo(containerPath);
                        located.add(new WorkspaceJSFResource(factory
                                .createLibraryResource(fullPath.toString()),
                                res, _contentTypeResolver));
                    }
                } catch (final Exception e)
                {
                    JSFCorePlugin
                            .log(e,
                                    "While trying to locate JSF resources in the workspace"); //$NON-NLS-1$
                }
            }
        }
        return located;
    }

}
