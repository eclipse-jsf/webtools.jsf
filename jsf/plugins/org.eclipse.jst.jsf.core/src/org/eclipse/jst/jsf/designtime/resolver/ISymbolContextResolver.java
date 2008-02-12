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

package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Resolves symbols and possible symbols in a given context
 * 
 * May NOT be sub-classed or implemented by clients.
 * Sub-class AbstractSymbolContextResolver to implement.
 * 
 * @author cbateman
 *
 */
public interface ISymbolContextResolver extends IDocumentContextResolver
{
    /**
     * Gets the most appropriate symbol in the current context
     * based on name.  Most "appropriate" may take into account
     * scoping and other rules.
     * 
     * @param name
     * @return a symbol
     */
    ISymbol  getVariable(String name);

    /**
     * @return all valid symbols in the current context
     */
    ISymbol[] getAllVariables();

    /**
     * @param symbol
     * @param propertyName
     * @return the property symbol called propertyName or null if not found
     */
    ISymbol getProperty(ISymbol symbol, Object propertyName);

    /**
     * @param symbol
     * @return all properties of symbol
     */
    ISymbol[]  getProperties(ISymbol symbol);

    /**
     * @param base
     * @param methodName
     * @return the method of base matching methodName or null if not found
     */
    IMethodSymbol getMethod(IObjectSymbol base, Object methodName);

    /**
     * @param base
     * @return all methods belonging to base
     */
    ISymbol[] getMethods(IObjectSymbol base);
    
    /**
     * This is meant to be used things like caching to determine if caches
     * need to be flushed for different contexts.  If you are in doubt, always
     * return false.
     * 
     * NOTE: this method should not be used to determine if symbols previously
     * returned by this resolver have changed to do changes in the underlying
     * application such as change to a managed bean.  This method only needs
     * to check if what variable, properties and methods may have resolved
     * different, because of the context itself.
     * 
     * @param modelContext
     * @since 3.0
     * @return true if the instance of this resolver would resolve everything
     * exactly the same with this modelContext.  False otherwise.
     */
    boolean hasSameResolution(final IModelContext modelContext);
}
