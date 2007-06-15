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

package org.eclipse.jst.jsf.context.symbol.source;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Defines a provider that acts as source of symbols.  The
 * implementor is usually context specific
 * 
 * <p><b>Provisional API - subject to change</b></p> 
 * 
 * @author cbateman
 *
 */
public interface ISymbolSourceProvider 
{
    /**
	 * @param context 
     * @param symbolScopeMask - a mask resulting for ORing the SYMBOL_SCOPE_*
     * constants to indicate what scopes to retrieve symbols for.
	 * @return all symbols in context matching symbolScopeMask
	 */
	ISymbol[]  getSymbols(IAdaptable context, int symbolScopeMask);
	
	/**
	 * @param prefix
	 * @param context 
	 * @param symbolScopeMask a mask resulting for ORing the SYMBOL_SCOPE_*
	 * @return the sub-set of getSymbols that conforms
	 * to String.startsWith(prefix) in context matching symbolScopeMask.
	 */
	ISymbol[]  getSymbols(String prefix, IAdaptable context, int symbolScopeMask);
	
	/**
	 * @param context
	 * @return true if this provider has symbols for this context
	 */
	boolean	   isProvider(IAdaptable context);
}
