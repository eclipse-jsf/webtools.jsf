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

package org.eclipse.jst.jsf.designtime.internal.provisional.context;

import java.util.Map;

/**
 * Interface that must be implemented by all design time external contexts
 * 
 * Clients may implement but should not sub-class
 * 
 * @author cbateman
 *
 */
public interface IDTExternalContext 
{
    /**
     * @param scopeMask -- the scope for which to return the symbol map
     * @return a map of ISymbols representing the currently available
     * scope variables.  Never null, empty if no symbols
     * 
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public abstract Map getMapForScope(int scopeMask);

    /**
     * @return a map of ISymbols representing the currently available
     * request scope variables.  Never null, empty if no symbols
     * 
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public abstract Map getRequestMap();

    /**
     * @return a map of ISymbols representing the currently available
     * session scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public abstract Map getSessionMap();

    /**
     * @return a map of ISymbols representing the currently available
     * application scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public abstract Map getApplicationMap();
    
    /**
     * @return a map of ISymbols representing the currently available
     * none scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public abstract Map getNoneMap();
}
