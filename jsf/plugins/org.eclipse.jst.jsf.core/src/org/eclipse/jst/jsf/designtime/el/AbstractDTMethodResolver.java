/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.el;

import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * Sub-class of all design time method resolvers
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDTMethodResolver 
{
    /**
     * Returns a symbol encapsulating the method on base with the name
     * methodId
     * 
     * @param base
     * @param methodId
     * @return the symbol for the named methodId or null if not found
     */
    public abstract IMethodSymbol getMethod(IObjectSymbol base, Object methodId);

    
    /**
     * @param base
     * @return all method binding symbols for base
     */
    public abstract ISymbol[] getMethods(IObjectSymbol base);
}
