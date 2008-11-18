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
package org.eclipse.jst.jsf.designtime.internal.view.model;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.common.internal.RunOnCompletionPattern;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * The abstract registry that all implementations of ITagRegistry should
 * sub-class.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractTagRegistry implements ITagRegistry
{
    private final List<ITagRegistryListener> _listeners;
    private final AtomicBoolean              _isDisposed = new AtomicBoolean(
                                                                 false);

    /**
     * 
     */
    protected AbstractTagRegistry()
    {
        super();
        _listeners = new CopyOnWriteArrayList<ITagRegistryListener>();
    }

    public final void addListener(final ITagRegistryListener listener)
    {
        if (!_listeners.contains(listener))
        {
            _listeners.add(listener);
        }
    }

    public final void removeListener(final ITagRegistryListener listener)
    {
        _listeners.remove(listener);
    }

    /**
     * @param event
     */
    protected final void fireEvent(final TagRegistryChangeEvent event)
    {
        for (final ITagRegistryListener listener : _listeners)
        {
            try
            {
                listener.registryChanged(event);
            }
            catch (final Exception e)
            {
                JSFCorePlugin.log(new Exception(e),
                        "During change event notification"); //$NON-NLS-1$
            }
        }
    }

    public abstract Collection<? extends Namespace> getAllTagLibraries();

    public abstract Namespace getTagLibrary(String uri);

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.ITagRegistry#isDisposed()
     */
    public final boolean isDisposed()
    {
        return _isDisposed.get();
    }

    /**
     * 
     */
    public final void destroy()
    {
        cleanupPersistentState();
        dispose();
    }
    
    /**
     * Called by destroy before it calls dispose.  Sub-class should
     * invalidate, and ideally delete, any persistent state. 
     * 
     * NOTE: DO NOT call dispose functionality from this call.  The framework
     * will call dispose() immediately after cleanupPersistentState.
     */
    protected abstract void cleanupPersistentState();

    /**
     * Must be factories or caches when disposing the registry.  Instances
     * must implement doDispose to provide their specific disposal operations.
     */
    public final void dispose()
    {
        // latch on the isDisposed flag so this block can only ever
        // execute once
        if (_isDisposed.compareAndSet(false, true))
        {
            fireEvent(new TagRegistryChangeEvent(this,
                TagRegistryChangeEvent.EventType.REGISTRY_DISPOSED));
            doDispose();
        }
    }

    /**
     * Implement with instance specific disposal operations.  Do do not fire
     * the REGISTRY_DISPOSED event from this method (it is done in dispose.
     * 
     * Implementer must assume that this method be called at most once and that
     * it is error to access the registry instance after it is called 
     */
    protected abstract void doDispose();

    public final void refresh(final Runnable runAfter, final boolean flushCaches)
    {
        final Job refreshJob = getRefreshJob(flushCaches);

        final RunOnCompletionPattern runPattern = new RunOnCompletionPattern(
                refreshJob, runAfter);
        runPattern.run();
    }

    /**
     * @param flushCaches 
     * @return a job that, when run, will perform the registry refresh.  Job
     * must do any necessary synchronizing of internal state.
     */
    protected abstract Job getRefreshJob(final boolean flushCaches);
}
