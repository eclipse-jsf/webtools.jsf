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
