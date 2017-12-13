package org.eclipse.jst.jsf.test.util.mock;

import java.util.List;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;

/**
 * An IWorkspaceContext object that has the ability to simulate workspace change
 * events at test time.
 * 
 * @author cbateman
 *
 */
public interface IWorkspaceContextWithEvents extends IWorkspaceContext
{
    public abstract void fireWorkspaceEvent(final IResourceChangeEvent event);
    public abstract List<IResourceChangeListener> getListeners();
    public abstract List<IResourceChangeListener> getListeners(List<Class<? extends IResourceChangeListener>> includeTypes);
}
