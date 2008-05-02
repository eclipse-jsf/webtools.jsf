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
package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;


/**
 * An abstract that should sub-classed by clients wishing to implement
 * the ISymbolContextResolver interface
 * 
 * @author cbateman
 *
 */
public abstract class AbstractSymbolContextResolver implements ISymbolContextResolver 
{
    public abstract ISymbol[] getAllVariables();

    public abstract IMethodSymbol getMethod(IObjectSymbol base, Object methodName);

    public abstract ISymbol[] getMethods(IObjectSymbol base);

    public abstract ISymbol[] getProperties(ISymbol symbol);

    public abstract ISymbol getProperty(ISymbol symbol, Object propertyName);
    
    public abstract ISymbol getVariable(String name);

    public abstract boolean canResolveContext(IModelContext modelContext);

    /**
     * Default implementation
     * 
     * We should avoid returning true unless we are positive that changing
     * to this context won't change the way symbols are resolved.
     */
    public boolean hasSameResolution(IModelContext modelContext)
    {
        return false;
    }
    
}
