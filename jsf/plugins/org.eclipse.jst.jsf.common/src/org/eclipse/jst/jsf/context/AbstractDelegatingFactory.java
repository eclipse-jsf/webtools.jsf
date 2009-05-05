/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.IAdaptable;

/**
 * An abstract implementation of the IDelegatingFactory interface
 * 
 * Clients may extend this class.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractDelegatingFactory implements IDelegatingFactory
{

    /* instance attributes */

    /**
     * the list of registered factory delegates
     */
    protected final CopyOnWriteArrayList<IAdaptable> _delegates;

    private final List<Class> _supportedDelegates;

    /**
     * @param supportedDelegateTypes
     *            -- populates the list of classes used by the isValidDelegate
     *            contract
     */
    protected AbstractDelegatingFactory(final Class[] supportedDelegateTypes)
    {
        _delegates = new CopyOnWriteArrayList<IAdaptable>();

        final List<Class> supportedTypes = new ArrayList<Class>();
        supportedTypes.addAll(Arrays.asList(supportedDelegateTypes));
        _supportedDelegates = Collections.unmodifiableList(supportedTypes);
    }

    /**
     * @see org.eclipse.jst.jsf.context.IDelegatingFactory#addFactoryDelegate(org.eclipse.core.runtime.IAdaptable)
     */
    public final void addFactoryDelegate(final IAdaptable delegate)
    {
        if (isValidDelegate(delegate))
        {
            _delegates.addIfAbsent(delegate);
        }
    }

    /**
     * @see org.eclipse.jst.jsf.context.IDelegatingFactory#removeFactoryDelegate(org.eclipse.core.runtime.IAdaptable)
     */
    public final boolean removeFactoryDelegate(final IAdaptable delegate)
    {
        return _delegates.remove(delegate);
    }

    /**
     * @see org.eclipse.jst.jsf.context.IDelegatingFactory#getValidDelegateTypes()
     */
    public final List<Class> getValidDelegateTypes()
    {
        return _supportedDelegates;
    }

    /**
     * @see org.eclipse.jst.jsf.context.IDelegatingFactory#isValidDelegate(org.eclipse.core.runtime.IAdaptable)
     */
    public final boolean isValidDelegate(final IAdaptable delegate)
    {
        for (final Class clazz : _supportedDelegates)
        {
            // if the delegate supports one of the valid delegate classes
            // via adaptation, then it is a valid delegate
            if (delegate.getAdapter(clazz) != null)
            {
                return true;
            }
        }

        // if no found, delegate is not supported
        return false;
    }
}
