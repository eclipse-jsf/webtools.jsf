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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

/**
 * Defines a type of factory that can have delegate factories to help it
 * create an appropriate instance type.  The general contract for implementors
 * is:
 * 
 * 1) You must follow the isValidDelegate contract.
 * 2) You should try to create an instance yourself and only delegate
 *    if cannot do it yourself.  Deviations from this rule must be
 *    clearly documented to ensure clients understand how the factory works.
 * 
 * Clients may implement this interface
 * 
 * @author cbateman
 *
 */
public interface IDelegatingFactory 
{
	/** -- Delegates */
	/** If this factory is asked to construct but does not know how to,
	 *  it will ask each of its registered delegates to do so in the order
	 *  they were added.
	 */
	
	/**
	 * Adds delgate to the end of the list of factory delegates if the list
	 * does not already contain it. 
	 * 
	 * @param delegate
	 * @throws ClassCastException if delegate does not implement an expected
	 * interface.  Each implementor can define what delegates are valid
	 * based on the isValidDelegate() and getValidDelegates contracts
	 */
	void addFactoryDelegate(IAdaptable  delegate);
	
	
	/**
	 * @param delegate
	 * @return true if delegate was removed, false if delegate wasn't in
	 * the list of delegates
	 */
	boolean removeFactoryDelegate(IAdaptable delegate);
	
	/**
	 * @return a list of Class objects that represent the interfaces
	 * that may be passed to addFactoryDelegate.  Implementor should 
	 * return at least one supported class.
	 */
	List  getValidDelegateTypes();
	
	
	/**
	 * The return value of this method should conform to the following contract:
	 * 
	 * Let v = getValidDelegates.  Then isValidDelegate should return true
	 * only if the set of v contains a Class for which delegate.getAdapter(Class)
	 * returns a non-null value.
	 * 
	 * @param delegate
	 * @return true if delegate is supported, false otherwise.
	 */
	boolean isValidDelegate(IAdaptable delegate);
}
