/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
