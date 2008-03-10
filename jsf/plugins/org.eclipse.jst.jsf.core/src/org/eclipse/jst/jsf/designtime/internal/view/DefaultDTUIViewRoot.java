package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.common.internal.RunOnCompletionPattern;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * The default view root implementation
 * 
 * @author cbateman
 * 
 */
public class DefaultDTUIViewRoot extends DTUIViewRoot
{
    /**
     * serializable
     */
    private static final long serialVersionUID = -6948413077931237435L;
    //private final DefaultDTViewHandler _viewHandler;
    private final DTFacesContext _context;
    private XMLViewDefnAdapter _viewAdapter;
    private String _viewId;
    private XMLComponentTreeConstructionStrategy _constructionStrategy;

    /**
     * @param context 
     * @param viewHandler
     *            TODO: should we decouple from the viewHandler by creating and
     *            update proxy?
     * @param adapter 
     */
    public DefaultDTUIViewRoot(final DTFacesContext context,
            final IDTViewHandler viewHandler, final XMLViewDefnAdapter adapter)
    {
        // TODO: refactor constants
        super(null, null, new ComponentTypeInfo("javax.faces.ViewRoot",
                "javax.faces.component.UIViewRoot", "javax.faces.ViewRoot",
                null));
        _context = context;
        //_viewHandler = viewHandler;
        _viewAdapter = adapter;
    }

    @Override
    public String getViewId()
    {
        return _viewId;
    }

    @Override
    public void setViewId(final String viewId)
    {
        _viewId = viewId;
    }

    @Override
    public void refresh(final Runnable runAfter)
    {
        if (_constructionStrategy == null)
        {
            _constructionStrategy = new XMLComponentTreeConstructionStrategy(
                    _viewAdapter, _context.adaptContextObject()
                            .getProject());
        }

        final Job refreshTreeJob = new RefreshTreeJob(_constructionStrategy,
                _context, this);
        
        new RunOnCompletionPattern(refreshTreeJob, runAfter).run();
    }

    private static class RefreshTreeJob extends Job
    {
        private final XMLComponentTreeConstructionStrategy _strategy;
        private final DTFacesContext _facesContext;
        private final DTUIViewRoot _root;

        public RefreshTreeJob(
                final XMLComponentTreeConstructionStrategy strategy,
                final DTFacesContext facesContext, final DTUIViewRoot root)
        {
            super("Refresh Tree Job");
            _strategy = strategy;
            _facesContext = facesContext;
            _root = root;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor)
        {
            _strategy.createComponentTree(_facesContext, _root);
            return Status.OK_STATUS;
        }
    }
}
