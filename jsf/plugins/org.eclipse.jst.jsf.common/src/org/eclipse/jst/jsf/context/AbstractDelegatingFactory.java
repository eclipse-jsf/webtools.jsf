/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
        	synchronized(_delegates)
        	{
        		_delegates.addIfAbsent(delegate);
        		if (_delegates.size() > 1)
        		{
        			List<IAdaptable> delegates = new ArrayList<IAdaptable>(_delegates);

        			Collections.sort(delegates, new Comparator()
        			{
						public int compare(Object delegate1, Object delegate2) 
						{
							final Class<?>  clazz1 = delegate1.getClass();
							final Class<?>  clazz2 = delegate2.getClass();
							
							Package package1 = clazz1.getPackage();
							Package package2 = clazz2.getPackage();
							boolean package1IsOSS = package1.getName().startsWith("org.eclipse.jst"); //$NON-NLS-1$
							boolean package2IsOSS = package2.getName().startsWith("org.eclipse.jst"); //$NON-NLS-1$
							
							if (package1IsOSS && !package2IsOSS)
							{
								// sort the oss one after the non-oss one
								return 1;
							}
							else if (!package1IsOSS && package2IsOSS)
							{
								return -1;
							}
							
							// otherwise they are either both oss or both non-oss, so just
							// sort canonically by name.
							return clazz1.getName().compareTo(clazz2.getName());
						}
        			});
        			_delegates.clear();
        			_delegates.addAll(delegates);
        		}
        	}
        }
    }

    /**
     * @see org.eclipse.jst.jsf.context.IDelegatingFactory#removeFactoryDelegate(org.eclipse.core.runtime.IAdaptable)
     */
    public final boolean removeFactoryDelegate(final IAdaptable delegate)
    {
        synchronized(_delegates)
        {
            return _delegates.remove(delegate);
        }
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

    /**
     * @return an iterator to _delegates which is retrieved in a synchronized block to protect against
     * access during sorting.
     */
    protected final Iterator<IAdaptable> getDelegatesIterator()
    {
        synchronized(_delegates)
        {
            return _delegates.iterator();
        }
    }
}
