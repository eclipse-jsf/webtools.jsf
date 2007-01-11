package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * TODO: isn't there a workbench registry action for this?
 * @author cbateman
 *
 */
public class ShowViewAction extends Action {
    private static Logger _log = PDPlugin.getLogger(PropertiesViewAction.class);
    private final String _viewId;
    
    public ShowViewAction(String text, String viewId) 
    {
        _viewId = viewId;
        setText(text); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        try {
            getPage().showView(_viewId);
        } catch (PartInitException e) {
            _log.info("Open the view: "+_viewId, e);
        }

    }

    private IWorkbenchPage getPage() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        return window.getActivePage();
    }
}
