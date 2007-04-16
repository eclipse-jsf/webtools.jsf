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

package org.eclipse.jst.jsf.designtime.el;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * Super-class of all design-time variable resolver.  A design time variable
 * resolver 
 * 
 * Clients may sub-class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDTVariableResolver 
{
    /**
     * @param context
     * @param name
     * @param externalContextKey -- an adapter that identifies the external application
     * context within which to resolve name.  For example, this could be an IFile
     * representing a JavaServer Page in which we want to resolve the symbol name
     * @return the symbol corresponding to name in the faces context or null
     * if not found
     */
    public abstract ISymbol resolveVariable(DTFacesContext context, String name, IAdaptable externalContextKey);
    
    /**
     * @param facesContext
     * @param externalContextKey
     * @return all variables
     */
    public abstract ISymbol[] getAllVariables(DTFacesContext facesContext, 
            IAdaptable externalContextKey);
}
