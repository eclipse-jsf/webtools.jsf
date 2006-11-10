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

package org.eclipse.jst.jsf.context.internal.provisional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

/**
 * An abstract implementation of the IDelegatingFactory interface
 * 
 * Clients may extend this class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDelegatingFactory implements IDelegatingFactory {

	/* instance attributes */
	
	/**
	 * the list of registered factory delegates
	 */
	protected 	final List		_delegates;
	
	private 	final List		_supportedDelegates;

	/**
	 * @param supportedDelegateTypes -- populates the list of classes used
	 * by the isValidDelegate contract
	 */
	protected AbstractDelegatingFactory(Class[]  supportedDelegateTypes)
	{
		_delegates = new ArrayList();
		
		final List supportedTypes = new ArrayList();
		supportedTypes.addAll(Arrays.asList(supportedDelegateTypes));
		_supportedDelegates = Collections.unmodifiableList(supportedTypes);
	}

	/**
	 * @see org.eclipse.jst.jsf.context.internal.provisional.IDelegatingFactory#addFactoryDelegate(org.eclipse.core.runtime.IAdaptable)
	 */
	public void addFactoryDelegate(IAdaptable delegate) 
	{
		synchronized(_delegates)
		{
			
			if (!_delegates.contains(delegate)
					&& isValidDelegate(delegate))
			{
				_delegates.add(delegate);
			}
		}
	}

	/**
	 * @see org.eclipse.jst.jsf.context.internal.provisional.IDelegatingFactory#removeFactoryDelegate(org.eclipse.core.runtime.IAdaptable)
	 */
	public boolean removeFactoryDelegate(IAdaptable delegate) 
	{
		synchronized(_delegates)
		{
			return _delegates.remove(delegate);
		}	
	}

	/**
	 * @see org.eclipse.jst.jsf.context.internal.provisional.IDelegatingFactory#getValidDelegateTypes()
	 */
	public List getValidDelegateTypes() 
	{
		return _supportedDelegates;
	}

	/**
	 * @see org.eclipse.jst.jsf.context.internal.provisional.IDelegatingFactory#isValidDelegate(org.eclipse.core.runtime.IAdaptable)
	 */
	public boolean isValidDelegate(IAdaptable delegate) 
	{
		for (final Iterator it = _supportedDelegates.iterator(); it.hasNext();)
		{
			final Class clazz = (Class) it.next();
			
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
