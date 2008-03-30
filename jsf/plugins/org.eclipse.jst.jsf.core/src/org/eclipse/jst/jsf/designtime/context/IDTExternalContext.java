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

package org.eclipse.jst.jsf.designtime.context;

import java.util.Map;

import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Interface that must be implemented by all design time external contexts
 * 
 * Clients must not implement or sub-class.  Sub-class AbstractDTExternalContext instead.
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
    Map<String, ISymbol> getMapForScope(int scopeMask);

    /**
     * @return a map of ISymbols representing the currently available
     * request scope variables.  Never null, empty if no symbols
     * 
     * Map is unmodifiable (throws exception on mutation operations)
     */
    Map<String, ISymbol> getRequestMap();

    /**
     * @return a map of ISymbols representing the currently available
     * session scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    Map<String, ISymbol> getSessionMap();

    /**
     * @return a map of ISymbols representing the currently available
     * application scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    Map<String, ISymbol> getApplicationMap();
    
    /**
     * @return a map of ISymbols representing the currently available
     * none scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    Map<String, ISymbol> getNoneMap();
    
    /**
     * Servlet 2.3_SRV.4.4: The context path of the application. 
     * 
     * @return the request context path
     */
    String getRequestContextPath();

    /**
     * Find all of the possible url-pattern's in the container that 
     * could match the underlying resource.  Normally this will be single
     * element list.  It will only 
     * 
     * @return
     */
//    List<String> getAllContainerMappings();
}
