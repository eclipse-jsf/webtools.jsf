package org.eclipse.jst.jsf.common.internal.resource;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Mediates between a client and certain workspace interface.  This is allows
 * us to decouple from the IWorkspace interface, especially for testing.
 * 
 * @author cbateman
 *
 */
public class WorkspaceMediator
{
    /**
     * @param runnable
     * @param name
     */
    public void runInWorkspaceJob(final IWorkspaceRunnable runnable, final String name)
    {
        new WorkspaceJob(name)
        {
            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor)
                    throws CoreException
            {
                runnable.run(monitor);
                return Status.OK_STATUS;
            }
            
        }.schedule();
    }
}
