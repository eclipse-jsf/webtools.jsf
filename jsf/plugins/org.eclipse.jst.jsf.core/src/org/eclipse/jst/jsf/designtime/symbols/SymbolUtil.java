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

package org.eclipse.jst.jsf.designtime.symbols;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Utilities for symbol handling
 * 
 * @author cbateman
 *
 */
public final class SymbolUtil 
{
    /**
     * @param symbols
     * @param name
     * @return the symbol in symbols matching name or null if not there
     */
    public static ISymbol  findSymbolByName(final ISymbol[] symbols, final String name)
    {
        for (int i = 0; i < symbols.length; i++)
        {
            if (name.equals(symbols[i].getName()))
            {
                return symbols[i];
            }
        }
        
        // if not found, return null
        return null;
    }
    
    /**
     * @param allSymbols
     * @param prefix
     * @return an array of symbols filtered based on name.startsWith(prefix)
     */
    public static ISymbol[]  filterSymbolsByPrefix(final ISymbol[] allSymbols, final String prefix) 
    {
        List   filteredSymbols = new ArrayList(allSymbols.length);
        for (int i = 0; i < allSymbols.length; i++)
        {
            if (allSymbols[i].getName().startsWith(prefix))
            {
                filteredSymbols.add(allSymbols[i]);
            }
        }
        
        return (ISymbol[]) filteredSymbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }
    
    private SymbolUtil()
    {
        // no external instantiation
    }
}
