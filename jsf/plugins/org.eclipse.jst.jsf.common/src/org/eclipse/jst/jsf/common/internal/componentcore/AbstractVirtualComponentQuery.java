package org.eclipse.jst.jsf.common.internal.componentcore;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Wraps parts of the component core in a way that always PDE-free injection
 * during test.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractVirtualComponentQuery
{
    /**
     * TODO: reconcile with duplicate. This was taken from JSFAppConfigUtils.
     * 
     * Gets an IVirtualFolder instance which represents the root context's web
     * content folder.
     * 
     * @param project
     *            IProject instance for which to get the folder.
     * @return IVirtualFolder instance which represents the root context's web
     *         content folder.
     */
    public abstract IVirtualFolder getWebContentFolder(IProject project);

    /**
     * The default implementation that makes static calls to ComponentCore.
     * @author cbateman
     *
     */
    public static class DefaultVirtualComponentQuery extends
            AbstractVirtualComponentQuery
    {
        @Override
        public IVirtualFolder getWebContentFolder(IProject project)
        {
            IVirtualFolder folder = null;
            IVirtualComponent component = ComponentCore
                    .createComponent(project);
            if (component != null)
            {
                folder = component.getRootFolder();
            }
            return folder;
        }
    }
}
