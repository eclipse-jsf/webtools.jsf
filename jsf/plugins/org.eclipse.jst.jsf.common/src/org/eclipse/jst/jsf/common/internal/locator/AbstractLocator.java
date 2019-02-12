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
package org.eclipse.jst.jsf.common.internal.locator;

import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener.LocatorChangeEvent;

/**
 * The abstract base class of all ILocator implementations.
 * 
 * @author cbateman
 *
 * @param <LOCATORTYPE>
 * @param <CONTEXTTYPE>
 * @param <IDTYPE>
 */
public abstract class AbstractLocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>
        implements ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>
{
    /**
     * The default value used for "no result".
     */
    protected static final Object   DEFAULT_NO_RESULT_VALUE = null;
    
    private final CopyOnWriteArrayList<ILocatorChangeListener> _listeners;
    private final LOCATORTYPE _noResultValue;
    private final IDTYPE _id;
    private final String _displayName;
    private boolean _isStarted;

    private Exception _startTrace;

    /**
     * Available for sub-classes that want to use reasonable defaults and only provide
     * mandatory data.
     * 
     * No result value is null.
     * A new instance of CopyOnWriteArrayList is used and held private.
     * 
     * @param id 
     * @param displayName 
     * 
     */
    public AbstractLocator(final IDTYPE id, final String displayName)
    {
        this(id,
             displayName,
             null,
             new CopyOnWriteArrayList<ILocatorChangeListener>());
    }

    /**
     * Available for sub-classes to manually inject dependencies.
     * 
     * @param id 
     * @param displayName 
     * @param noResultValue 
     * @param mutableListenerList
     */
    protected AbstractLocator(
            final IDTYPE id,
            final String displayName,
            final LOCATORTYPE noResultValue,
            final CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList)
    {
        _id = id;
        _displayName = displayName;
        _listeners = mutableListenerList;
        _noResultValue = noResultValue;
    }

    public final LOCATORTYPE perform(final CONTEXTTYPE context)
            throws Exception
    {
        return locate(context);
    }

    public LOCATORTYPE getNoResult()
    {
        return _noResultValue;
    }

    public IDTYPE getId()
    {
        return _id;
    }

    public String getDisplayName()
    {
        return _displayName;
    }

    public LOCATORTYPE locate(final CONTEXTTYPE context)
    {
        if (isStarted())
        {
            return doLocate(context);
        }
        throw new IllegalStateException("Locator not started"); //$NON-NLS-1$
    }
    

    /**
     * @param context
     * @return the located type.
     */
    protected abstract LOCATORTYPE doLocate(CONTEXTTYPE context);

    public void start(final CONTEXTTYPE initialContext)
    {
        if (isStarted())
        {
            throw new IllegalStateException("Locator was already started", _startTrace); //$NON-NLS-1$
        }

        // set the started flag
        setStarted(true);
        _startTrace = new Exception("Locator was started on this trace"); //$NON-NLS-1$
    }

    public void stop()
    {
        // set the started flag
        // clear all listeners
        _listeners.clear();
        setStarted(false);
        _startTrace = null;
    }

    /**
     * @param listener
     */
    public  void addListener(final ILocatorChangeListener listener)
    {
        _listeners.addIfAbsent(listener);
    }

    /**
     * @param listener
     */
    public void removeListener(final ILocatorChangeListener listener)
    {
        _listeners.remove(listener);
    }
    
    /**
     * @param event
     */
    protected void fireChangeEvent(final LocatorChangeEvent event)
    {
        for (final ILocatorChangeListener listener : _listeners)
        {
            listener.changed(event);
        }
    }

    public final boolean isStarted()
    {
        return _isStarted;
    }

    public boolean canStart()
    {
        if (isStarted())
        {
            return false;
        }
        return true;
    }

    /**
     * @param newValue
     */
    protected final void setStarted(final boolean newValue)
    {
        _isStarted = newValue;
    }
}
