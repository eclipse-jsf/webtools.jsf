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

import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Resolves symbols and possible symbols in a given context
 * 
 * May NOT be sub-classed or implemented by clients.
 * Sub-class SymbolContextResolver.
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
}
