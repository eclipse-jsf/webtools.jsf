package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.resources.IContainer;

/**
 * A concrete JSFResourceContainer that maps to a container object in the
 * workspace.
 * 
 * @author cbateman
 *
 */
public class WorkspaceJSFResourceContainer extends JSFResourceContainer implements IWorkspaceJSFResourceFragment
{

    private final IContainer _container;

    /**
     * @param id
     * @param container
     */
    public WorkspaceJSFResourceContainer(final ResourceFragmentIdentifier id,
            final IContainer container)
    {
        super(id);
        _container = container;
    }

    @Override
    public boolean isAccessible()
    {
        return _container.isAccessible();
    }

    /**
     * @return the container resource
     */
    public IContainer getResource()
    {
        return _container;
    }

}
