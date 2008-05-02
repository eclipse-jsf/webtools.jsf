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
package org.eclipse.jst.jsf.designtime.context;

import java.util.Collections;
import java.util.Map;

import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;

/**
 * The parent of all IDTExternalContext implementations
 * 
 * Clients must sub-class to create IDTExternalContext implementations
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDTExternalContext implements IDTExternalContext 
{
    
    /**
     * Return a map of available symbols in each of the masked scopes
     * indexed by symbol name
     * 
     * Called by {@link #getMapForScope(int)} to satify the interface.
     * If null is returned {@link #getMapForScope(int)} will automatically
     * return an EMPTY_MAP.
     * 
     * @param scopeMask
     * @return the map of symbols
     */
    protected abstract Map<String, ISymbol> doGetMapForScope(int scopeMask);
    
    
    public final  Map<String, ISymbol> getMapForScope(final int scopeMask)
    {
        Map<String, ISymbol> mapForScope = doGetMapForScope(scopeMask);
        
        if (mapForScope == null)
        {
            mapForScope = Collections.EMPTY_MAP;
        }
        else
        {
            mapForScope = Collections.unmodifiableMap(mapForScope);
        }
        return mapForScope;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getRequestMap()
     */
    public final Map<String, ISymbol> getRequestMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getSessionMap()
     */
    public final Map<String, ISymbol> getSessionMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_SESSION);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getApplicationMap()
     */
    public final Map<String, ISymbol> getApplicationMap()
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_APPLICATION);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getNoneMap()
     */
    public final Map<String, ISymbol> getNoneMap() 
    {
        return getMapForScope(ISymbolConstants.SYMBOL_SCOPE_NONE);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.context.IDTExternalContext#getRequestContextPath()
     */
    public String getRequestContextPath() 
    {
        // API: do we need to support default behaviour here?
        return null;
    }
}
