package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;

public class MockWorkspaceMediator extends WorkspaceMediator
{

    @Override
    public void runInWorkspaceJob(IWorkspaceRunnable runnable, String name)
    {
        try
        {
            runnable.run(new NullProgressMonitor());
        } catch (CoreException e)
        {
            throw new RuntimeException(e);
        }
    }

}
