package org.eclipse.jst.jsf.ui.internal.component;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessEvent;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessListener;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * The top-level input model for a design time component tree
 * 
 * @author cbateman
 * 
 */
/* package */class DTJSFViewModel
{
    private final IStructuredDocument        _document;
    private final IProject                   _project;
    private final IFile                      _file;
    private final IStructuredDocumentContext _context;
    private final AtomicBoolean              _alreadyUpdating = new AtomicBoolean(
                                                                      false);
    private Runnable                         _runnable;
    private final IViewRootHandle            _viewRootHandle;
    private StalenessListener                _stalenessListener;

    /**
     * @param document
     * @throws IllegalArgumentException
     *             if document cannot be used to initialize a component tree.
     */
    public DTJSFViewModel(final IStructuredDocument document)
    {
        _document = document;
        _context = IStructuredDocumentContextFactory.INSTANCE.getContext(
                _document, -1);

        final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getWorkspaceContextResolver(_context);

        if (resolver == null)
        {
            throw new IllegalArgumentException();
        }

        _project = resolver.getProject();
        _file = (IFile) resolver.getResource();

        if (_project == null || _file == null)
        {
            throw new IllegalArgumentException();
        }
        
        final DTFacesContext facesContext = getFacesContext();
        _viewRootHandle = facesContext.getViewRootHandle();
    }

    /**
     * Acquire and initialize the component tree root for the document
     * 
     * @param runnable
     */
    public void init(final Runnable runnable)
    {
        _runnable = runnable;
        _viewRootHandle.addListener(getOrCreateListener());
    }
    
    public void dispose()
    {
        if (_stalenessListener != null)
        {
            _viewRootHandle.removeListener(_stalenessListener);
        }
        
        synchronized(this)
        {
            _runnable = null;
        }
    }

    /**
     * @return the structured document that this view model was created for.
     */
    public final IStructuredDocument getDocument()
    {
        return _document;
    }

    public final IProject getProject()
    {
        return _project;
    }

    public final String getViewId()
    {
        final DTFacesContext facesContext = getFacesContext();
        final IDTViewHandler viewHandler = DTAppManagerUtil
                .getViewHandler(_project);
        if (facesContext != null && viewHandler != null)
        {
            return viewHandler.getViewId(facesContext, _file);
        }
        return null;
    }

    private DTFacesContext getFacesContext()
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(_project);

        if (manager != null)
        {
            return manager.getFacesContext(_file);
        }
        return null;
    }

    public void update()
    {
        final DTFacesContext facesContext = getFacesContext();

        if (facesContext != null)
        {
            // latch the alreadyRunning flag; job will unset to false
            if (_alreadyUpdating.compareAndSet(false, true))
            {
                final Job job = new UpdateRootAndRefreshJob(_viewRootHandle);

                job.addJobChangeListener(new JobChangeAdapter()
                {
                    @Override
                    public void done(final IJobChangeEvent event)
                    {
                        // always reset the updating flag
                        _alreadyUpdating.set(false);
                        if (event.getResult().isOK())
                        {
                            synchronized(DTJSFViewModel.this)
                            {
                                if (_runnable != null)
                                {
                                    _runnable.run();
                                }
                            }
                        }
                    }
                });

                job.schedule();
            }
        }
    }

    /**
     * @return the design time view root or null
     */
    public DTUIViewRoot getRoot()
    {
        final DTFacesContext facesContext = getFacesContext();

        if (facesContext != null)
        {
            final DTUIViewRoot viewRoot = _viewRootHandle.getCachedViewRoot();

            // if our copy is uninit or null, kick off an update
            if (viewRoot == null || viewRoot.isStale())
            {
                update();
            }

            return viewRoot;
        }
        return null;
    }

    private StalenessListener getOrCreateListener()
    {
        if (_stalenessListener == null)
        {
            _stalenessListener = new StalenessListener()
            {
                @Override
                protected void stalenessChanged(StalenessEvent event)
                {
                    switch (event.getChangeType())
                    {
                        case VIEW_DEFN_CHANGED:
                        {
                            update();
                        }
                        case VIEW_DEFN_DELETED:
                        case VIEW_DEFN_PROJECT_CLOSED:
                        {
                            DTUIViewRoot root = _viewRootHandle
                                    .getCachedViewRoot();
                            if (root != null)
                            {
                                root.removeListener(_stalenessListener);
                            }
                        }
                    }
                }
            };
        }
        return _stalenessListener;
    }

    private static class UpdateRootAndRefreshJob extends Job
    {
        private final IViewRootHandle _viewRootHandle;

        public UpdateRootAndRefreshJob(final IViewRootHandle viewRootHandle)
        {
            super("Updating view root");
            _viewRootHandle = viewRootHandle;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor)
        {
            _viewRootHandle.updateViewRoot();
            return Status.OK_STATUS;
        }
    }
}
