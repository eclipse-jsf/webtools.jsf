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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Must be sub-classed by all contextSymbolFactory extension point implementors
 * to create context configured symbols
 * 
 * @author cbateman
 *
 */
public abstract class AbstractContextSymbolFactory 
{
    /**
     * @param symbolName -- the symbol name
     * @param scope -- the scope of the symbol
     * @param context -- the context; must be supported (call supports(context))
     * @param problems -- populated with problems found during symbol construction.
     * @return a new ISymbol configured for the name, scope and context or null
     * if the arguments are valid but some other reason a symbol cannot be created.
     * @throws IllegalArgumentException if this method is called with context
     * for which supports(context) == false or if scope does not conform
     * to exactly one of the ISymbolConstants.SYMBOL_SCOPE_* constants
     */
    public final ISymbol  create(String symbolName, int scope, IAdaptable context, List problems)
    {
        if (!supports(context))
        {
            throw new IllegalArgumentException("Unsupported context"); //$NON-NLS-1$
        }
        else if (!ISymbolConstants.isValid(scope))
        {
            throw new IllegalArgumentException("Unsupported symbol constant:"+scope); //$NON-NLS-1$
        }
        
        return internalCreate(symbolName, scope, context, problems);
    }

    /**
     * @param symbolName
     * @param scope
     * @param context
     * @param problems -- see problems arg on create
     * @return a new ISymbol for the name, scope and context
     */
    protected abstract ISymbol internalCreate(String symbolName, int scope, IAdaptable context, List problems);
    
    /**
     * @param context
     * @return true if this factory supports the context
     */
    public abstract boolean  supports(IAdaptable context);
}
