/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
