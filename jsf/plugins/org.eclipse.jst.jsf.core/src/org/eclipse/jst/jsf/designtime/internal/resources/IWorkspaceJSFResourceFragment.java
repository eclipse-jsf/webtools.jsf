package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.resources.IResource;

/**
 * A JSF fragment whose design time object(s) of interest are in the workspace
 * @author cbateman
 *
 */
public interface IWorkspaceJSFResourceFragment extends IJSFResourceFragment
{

    /**
     * @return the corresponding design time workspace resource.
     */
    public IResource getResource();

}
