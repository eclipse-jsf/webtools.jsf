package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;

/**
 * @author cbateman
 * 
 */
public class WorkspaceJSFResourceLocator extends AbstractJSFResourceLocator
{
    private final AbstractVirtualComponentQuery _vcQuery;
    private final ContentTypeResolver _contentTypeResolver;
    private WorkspaceResourceManager _workspaceResourceManager;

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     * @param vcQuery
     * @param contentTypeResolver
     * @param workspace
     */
    public WorkspaceJSFResourceLocator(
            final String id,
            final String displayName,
            final List<IJSFResourceFragment> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final AbstractVirtualComponentQuery vcQuery,
            final ContentTypeResolver contentTypeResolver,
            final IWorkspace workspace)
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
     * @param workspace
     */
    public WorkspaceJSFResourceLocator(
            final List<IJSFResourceFragment> noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList,
            final AbstractVirtualComponentQuery vcQuery,
            final ContentTypeResolver contentTypeResolver,
            final IWorkspace workspace)
    {
        this(
                "", "", noResultValue, mutableListenerList, vcQuery, contentTypeResolver, workspace); //$NON-NLS-1$//$NON-NLS-2$
    }

    @Override
    public void start(final IProject initialContext)
    {
        _workspaceResourceManager = new WorkspaceResourceManager(
                initialContext, _vcQuery, this, _contentTypeResolver);
        _workspaceResourceManager.initResources();
        super.start(initialContext);
    }

    @Override
    protected List<IJSFResourceFragment> doLocate(final IProject project)
    {
        return _workspaceResourceManager.getJSFResources();
    }

}
