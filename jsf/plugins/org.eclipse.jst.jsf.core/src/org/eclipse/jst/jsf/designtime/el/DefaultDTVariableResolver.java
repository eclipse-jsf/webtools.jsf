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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.context.IDTExternalContext;
import org.eclipse.jst.jsf.designtime.symbols.DefaultBeanSymbolSourceProvider;
import org.eclipse.jst.jsf.designtime.symbols.DefaultBuiltInSymbolProvider;

/**
 * A design time proxy for the runtime VariableResolver.  This is used to
 * resolve the first element of a var.prop.prop2 type of sub-expression in
 * a JSF EL expression
 * 
 * Clients may sub-class
 *  
 * @author cbateman
 *
 */
public class DefaultDTVariableResolver extends AbstractDTVariableResolver
{
    /**
     * Tries to mirror the JSF 1.1 runtime VariableResolver
     * 
     * @see org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver#resolveVariable(org.eclipse.jst.jsf.designtime.context.DTFacesContext, java.lang.String, org.eclipse.core.runtime.IAdaptable)
     */
    public ISymbol resolveVariable(DTFacesContext context, String name, IAdaptable externalContextKey)
    {
        // check implicits first
        final DefaultBuiltInSymbolProvider builtins =
            DefaultBuiltInSymbolProvider.getInstance();

        ISymbol  symbol = builtins.getSymbol(name, externalContextKey, ISymbolConstants.SYMBOL_SCOPE_ALL);

        if (symbol != null)
        {
            return symbol;
        }
        
        // next check the scope maps from request up to application
        final IDTExternalContext externalContext = 
            context.getDTExternalContext(externalContextKey);
        
        if (externalContext == null)
        {
            // TODO: try to find bean here?
            return null;
        }
        
        symbol = externalContext.getRequestMap().get(name);
        
        // check request scope
        if (symbol == null)
        {
            symbol = externalContext.getSessionMap().get(name);
            
            // then check session scope
            if (symbol == null)
            {
                symbol = externalContext.getApplicationMap().get(name);
                
                // if the symbol is not found at any scope, then look for a
                // a bean.
                if (symbol == null)
                {
                    final DefaultBeanSymbolSourceProvider beanProvider =
                        DefaultBeanSymbolSourceProvider.getInstance();
                    
                    symbol = beanProvider.getSymbol(name, externalContextKey, 
                                             ISymbolConstants.SYMBOL_SCOPE_ALL);
                }
            }
        }
        
        return symbol;
    }
    
    /**
     * @param facesContext
     * @param externalContextKey
     * @return all variables
     */
    public ISymbol[] getAllVariables(DTFacesContext facesContext, 
                                     IAdaptable externalContextKey)
    {
        final List  allSymbols = new ArrayList();

        addBuiltins(allSymbols, externalContextKey);

        final IDTExternalContext externalContext =  
            facesContext.getDTExternalContext(externalContextKey);

        if (externalContext != null)
        {
            addExternalContextSymbols(allSymbols, externalContext);
        }

        addBeanSymbols(allSymbols, externalContextKey);
        
        return (ISymbol[]) allSymbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }
        
    /**
     * Adds the built-in symbols to the list.  This behaviour is standarized and should
     * not be overriden in general.  However, you may wish to change the default
     * built-in symbol provider with your own.
     * 
     * @param list
     * @param externalContextKey
     */
    protected  void addBuiltins(final List list, final IAdaptable externalContextKey)
    {
        // check implicits first
        final DefaultBuiltInSymbolProvider builtins =
            DefaultBuiltInSymbolProvider.getInstance();

        list.addAll(Arrays.asList(builtins.getSymbols(externalContextKey, 
                                     ISymbolConstants.SYMBOL_SCOPE_ALL)));
    }
    
    /**
     * Simulate resolution of symbols from the request, session, application and none
     * scope maps.  Use a symbol provider instead if you simply want to add
     * new symbols for a tag variable or other symbol source.
     * 
     * @param list
     * @param externalContext
     */
    protected void addExternalContextSymbols(final List list, 
                                           final IDTExternalContext externalContext)
    {
        if (externalContext != null)
        {
            final ISymbol[] externalContextSymbols =
                    externalContext.getMapForScope
                  (ISymbolConstants.SYMBOL_SCOPE_ALL).values().
                                    toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
            list.addAll(Arrays.asList(externalContextSymbols));
        }
    }

    /**
     * Gets all the bean symbols.  If you wish to override it would be advisable
     * to look at and/or sub-class the default bean symbol source provider
     * 
     * @param list
     * @param externalContextKey
     */
    protected void addBeanSymbols(final List list, final IAdaptable externalContextKey)
    {
        final DefaultBeanSymbolSourceProvider beanProvider =
            DefaultBeanSymbolSourceProvider.getInstance();

        final ISymbol[] beanSymbols = 
            beanProvider.getSymbols(externalContextKey, 
                                    ISymbolConstants.SYMBOL_SCOPE_ALL);

        list.addAll(Arrays.asList(beanSymbols));
    }
}
