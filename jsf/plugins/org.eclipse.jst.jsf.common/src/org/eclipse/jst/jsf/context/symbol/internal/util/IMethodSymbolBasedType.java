/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.util;

import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;

/**
 * 
 * @author cbateman
 *
 */
public class IMethodSymbolBasedType extends MethodType 
{
    private final IMethodSymbol _methodSymbol;
    
    /**
     * @param methodSymbol
     */
    public IMethodSymbolBasedType(IMethodSymbol  methodSymbol) 
    {
        super(methodSymbol.getName(), methodSymbol.getSignature());
        _methodSymbol = methodSymbol;
    }

    /**
     * @return the method symbol
     */
    public IMethodSymbol getSymbol()
    {
        return _methodSymbol;
    }
}
