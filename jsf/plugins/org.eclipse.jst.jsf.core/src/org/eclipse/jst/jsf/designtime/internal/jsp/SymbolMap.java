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

package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.util.HashMap;

import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Hashmap that ensures only ISymbols are added.  This will be obsoleted when
 * all code goes to Java 5 templating.
 * 
 * @author cbateman
 *
 */
/*package*/ class SymbolMap extends HashMap
{
    /**
     * A default serial version UID. 
     */
    private static final long serialVersionUID = 1L;

    public Object put(Object key, Object value) 
    {
        if (value instanceof ISymbol)
        {
            return super.put(key, value);
        }
        
        throw new ClassCastException("SymbolMap values must be instanceof org.eclipse.jst.jsf.context.symbol.ISymbol");
    }
}
