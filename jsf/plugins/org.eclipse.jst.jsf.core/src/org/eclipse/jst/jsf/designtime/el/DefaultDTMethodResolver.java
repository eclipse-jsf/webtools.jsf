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

import java.util.Iterator;

import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * This resolver has no direct analogue at runtime.  JSF Components implement
 * method bindings at runtime in a runtime specific way.  But at design time
 * we have no direct DTComponent so instead we use this resolver to resolve
 * an IInstanceSymbol and method name to an IMethodSymbol (if one exists).
 * 
 * Clients may sub-class
 * 
 * @author cbateman
 *
 */
public class DefaultDTMethodResolver extends AbstractDTMethodResolver
{
    /**
     * Returns a symbol encapsulating the method on base with the name
     * methodId
     * 
     * @param base
     * @param methodId
     * @return the symbol for the named methodId or null if not found
     */
    public IMethodSymbol getMethod(IObjectSymbol base, Object methodId)
    {
        for (final Iterator it = base.getTypeDescriptor().getMethods().iterator(); it.hasNext();)
        {
            IMethodSymbol element = (IMethodSymbol) it.next();
            if (element.getName().equals(methodId))
            {
                return element;
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.designtime.el.AbstractDTMethodResolver#getMethods(org.eclipse.jst.jsf.context.symbol.IInstanceSymbol)
     */
    public ISymbol[] getMethods(IObjectSymbol base) 
    {
        return (ISymbol[])
            base.getTypeDescriptor().getMethods().
                toArray(new ISymbol[0]);
    }
}
