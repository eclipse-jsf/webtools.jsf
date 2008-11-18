/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.context;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessListener;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;

/* package */ final class ViewRootHolder implements IViewRootHandle, Cloneable
{
    private final Object         _recalcViewRootExclusion;
    private final DTFacesContext _facesContext;
    private DTUIViewRoot         _viewRoot;
    private final List<StalenessListener>  _waitingToAdd;
    private final List<StalenessListener>  _waitingToRemove;

    public ViewRootHolder(final DTFacesContext facesContext, final Object recalcViewRootExclusion)
    {
        _facesContext = facesContext;
        _recalcViewRootExclusion = recalcViewRootExclusion;
        _waitingToAdd = new ArrayList<StalenessListener>();
        _waitingToRemove = new ArrayList<StalenessListener>();
    }

    private ViewRootHolder(ViewRootHolder cloneFrom)
    {
        _recalcViewRootExclusion = cloneFrom._recalcViewRootExclusion;
        _facesContext = cloneFrom._facesContext;
        _viewRoot = cloneFrom.getCachedViewRoot();

        // create brand new copies
        _waitingToAdd = new ArrayList<StalenessListener>();
        _waitingToRemove = new ArrayList<StalenessListener>();
    }

    public synchronized DTUIViewRoot getCachedViewRoot()
    {
        return _viewRoot;
    }

    public DTUIViewRoot updateViewRoot()
    {
        DTUIViewRoot viewRoot = null;
        final IResource contextResource = _facesContext.adaptContextObject();
        
        // we create this additional exclusion so that we avoid a
        // situation
        // where two threads enter and start recalculating the same view
        // root in parallel. Only one thread may be working on creating
        // a new view root for a particular view at any given time. Note
        // that due to read/write lock on the value, there is nothing
        // to stop readers from simultaneous getting the old value until
        // the new value is ready and has been set.
        synchronized (_recalcViewRootExclusion)
        {
            viewRoot = getCachedViewRoot();

            if (viewRoot == null || viewRoot.isStale())
            {
                // if the view root hasn't been created, then do so
                // and populate it
                final IDTViewHandler viewHandler = _facesContext
                        .getViewHandler(contextResource);

                if (viewHandler != null)
                {
                    final String viewId = viewHandler.getViewId(
                            _facesContext, contextResource);

                    try
                    {
                        viewRoot = viewHandler.createView(_facesContext,
                                viewId);
                        if (viewRoot != null)
                        {
                            setCachedViewRoot(viewRoot);
                        }
                        
                        // do adds first, let remove trump all
                        for (final StalenessListener addListener : _waitingToAdd)
                        {
                            viewRoot.addListener(addListener);
                        }
                        
                        for (final StalenessListener removeListener : _waitingToRemove)
                        {
                            viewRoot.removeListener(removeListener);
                        }
                    }
                    catch (final ViewHandlerException e)
                    {
                        JSFCorePlugin.log(e,
                                "While creating dt viewroot for viewId: " //$NON-NLS-1$
                                        + viewId);
                    }
                }
            }
        }
        // return the copy. NEVER return _viewRoot directly since we are not
        // synchronized here.
        return viewRoot;
    }

    private synchronized void setCachedViewRoot(
            final DTUIViewRoot newViewRoot)
    {
        if (!Thread.holdsLock(_recalcViewRootExclusion))
        {
            throw new IllegalStateException(
                    "Must hold _recalcViewRootExclusion to modify view root"); //$NON-NLS-1$
        }
        _viewRoot = newViewRoot;
    }

    @Override
    protected synchronized IViewRootHandle clone()
    {
        return new ViewRootHolder(this);
    }

    public void addListener(StalenessListener listener)
    {
        if (_viewRoot != null)
        {
            synchronized(this)
            {
                _viewRoot.addListener(listener);
            }
        }
        else
        {
            // ensure that if we calculating a new view root, then there isn't
            // an issue.
            synchronized(_recalcViewRootExclusion)
            {
                synchronized(this)
                {
                    _waitingToAdd.add(listener);
                }
            }
        }
    }

    public void removeListener(StalenessListener listener)
    {
        DTUIViewRoot viewRoot = null;
        
        synchronized(this)
        {
            viewRoot = _viewRoot;
        }

        if (viewRoot != null)
        {
            synchronized(this)
            {
                viewRoot.removeListener(listener);
            }
        }
        else
        {
            // ensure that if we calculating a new view root, then there isn't
            // an issue.
            // always acquire the recalcViewRootExclusion first
            synchronized(_recalcViewRootExclusion)
            {
                synchronized(this)
                {
                    _waitingToAdd.add(listener);
                }
            }
        }
    }
}