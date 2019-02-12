/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;

class SetInputRunnable implements Runnable
{
    private final Viewer   _viewer;
    private final IProject _project;

    public SetInputRunnable(IProject project, Viewer viewer)
    {
        super();
        _project = project;
        _viewer = viewer;
    }

    public void run()
    {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
        {
            public void run()
            {
                _viewer.setInput(_project);
            }
        });
    }
}