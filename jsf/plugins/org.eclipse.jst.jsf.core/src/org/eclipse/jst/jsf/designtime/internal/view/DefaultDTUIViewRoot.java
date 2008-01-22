package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DefaultDTViewHandler.DefaultViewDefnAdapterFactory;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.w3c.dom.Node;

/**
 * The default view root implementation
 * 
 * @author cbateman
 * 
 */
class DefaultDTUIViewRoot extends DTUIViewRoot
{
    /**
     * serializable
     */
    private static final long serialVersionUID = -6948413077931237435L;
    private final DefaultDTViewHandler _viewHandler;
    private final DTFacesContext _context;
    private DefaultViewDefnAdapterFactory _viewDefnAdapterFactory;
    private String _viewId;
    private XMLComponentTreeConstructionStrategy _constructionStrategy;

    /**
     * @param viewHandler
     *            TODO: should we decouple from the viewHandler by creating and
     *            update proxy?
     */
    DefaultDTUIViewRoot(final DTFacesContext context,
            final DefaultDTViewHandler viewHandler)
    {
        // TODO: refactor constants
        super(null, null, new ComponentTypeInfo("javax.faces.ViewRoot",
                "javax.faces.component.UIViewRoot", "javax.faces.ViewRoot",
                null));
        _context = context;
        _viewHandler = viewHandler;
        try
        {
            _viewDefnAdapterFactory =
            // TODO: not extensible
            (DefaultViewDefnAdapterFactory) _viewHandler
                    .getViewMetadataAdapterFactory(context);
        }
        catch (final ViewHandlerException e)
        {
            JSFCorePlugin.log(e, "Trying to get view adapter factory");
        }
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
            final IViewDefnAdapter<Node, IDocument> adapter = _viewDefnAdapterFactory
                    .createAdapter(_context, _viewId);
            _constructionStrategy = new XMLComponentTreeConstructionStrategy(
                    (XMLViewDefnAdapter) adapter, _context.adaptContextObject()
                            .getProject());
        }

        final Job refreshTreeJob = new RefreshTreeJob(_constructionStrategy,
                _context, this);

        if (runAfter != null)
        {
            refreshTreeJob.addJobChangeListener(new JobChangeAdapter()
            {
                @Override
                public void done(final IJobChangeEvent event)
                {
                    if (event.getResult().isOK())
                    {
                        runAfter.run();
                    }
                }
            });
        }

        refreshTreeJob.schedule();

        if (runAfter == null)
        {
            try
            {
                refreshTreeJob.join();
            }
            catch (final InterruptedException e)
            {
                JSFCorePlugin.log(e, "Joining on tree refresh job");
            }
        }
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
