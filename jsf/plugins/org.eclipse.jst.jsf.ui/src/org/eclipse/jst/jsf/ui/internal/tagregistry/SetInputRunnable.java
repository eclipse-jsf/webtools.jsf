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