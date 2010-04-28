package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;

/**
 * A JSF resource that corresponds to an object in the workspace.
 * @author cbateman
 *
 */
public class WorkspaceJSFResource extends JSFResource implements IWorkspaceJSFResourceFragment
{
    private final IResource _res;

    /**
     * @param id
     * @param res
     * @param contentTypeResolver 
     */
    public WorkspaceJSFResource(final ResourceIdentifier id, final IResource res, final ContentTypeResolver contentTypeResolver)
    {
        super(id, contentTypeResolver);
        _res = res;
    }

    public final IResource getResource()
    {
        return _res;
    }

    
    public final boolean isAccessible()
    {
        return _res.isAccessible();
    }
}
