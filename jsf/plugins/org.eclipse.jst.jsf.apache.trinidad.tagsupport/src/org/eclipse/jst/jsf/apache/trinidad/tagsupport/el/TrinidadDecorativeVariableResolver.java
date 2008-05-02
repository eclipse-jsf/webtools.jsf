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
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.el;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.designtime.el.IDecorativeResolver;
import org.eclipse.jst.jsf.designtime.symbols.JSFSymbolFactory;

/**
 * A decorative dt variable resolver that shadows Trinidad's runtime resolver.
 * 
 * @author cbateman
 * 
 */
public class TrinidadDecorativeVariableResolver extends
        AbstractDTVariableResolver implements IDecorativeResolver
{
    private final static JSFSymbolFactory _symbolFactory                = new JSFSymbolFactory();

    public final static String            PAGE_FLOW_SCOPE_VARIABLE_NAME = "pageFlowScope"; //$NON-NLS-1$
    public final static String            PROCESS_SCOPE_VARIABLE_NAME   = "processScope"; //$NON-NLS-1$

    public final static String            REQUEST_CONTEXT_VARIABLE_NAME = "requestContext"; //$NON-NLS-1$
    public final static String            REQUEST_CONTEXT_CLASS_NAME    = "org.apache.myfaces.trinidad.context.RequestContext"; //$NON-NLS-1$

    @Override
    public ISymbol[] getAllVariables(final DTFacesContext facesContext,
            final IAdaptable externalContextKey)
    {
        final Map<String, ISymbol> symbols = createSymbols(facesContext);
        return symbols.values().toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    @Override
    public ISymbol resolveVariable(final DTFacesContext context,
            final String name, final IAdaptable externalContextKey)
    {
        return createSymbols(context).get(name);
    }

    private Map<String, ISymbol> createSymbols(final DTFacesContext context)
    {
        final IResource res = context.adaptContextObject();

        if (res != null)
        {
            final IProject project = res.getProject();
            if (project != null)
            {
                final Map<String, ISymbol> symbols = new HashMap<String, ISymbol>();

                final ISymbol requestContextVar = _symbolFactory
                        .createBeanOrUnknownInstanceSymbol(project,
                                REQUEST_CONTEXT_CLASS_NAME,
                                PAGE_FLOW_SCOPE_VARIABLE_NAME,
                                ERuntimeSource.OTHER_LITERAL);
                symbols.put(REQUEST_CONTEXT_VARIABLE_NAME, requestContextVar);
                
                // both pageFlowScope and processScope return the same variable
                // TODO: we may be able to populate this map
                final ISymbol pageFlowVar = _symbolFactory
                        .createUnknownInstanceSymbol(
                                PAGE_FLOW_SCOPE_VARIABLE_NAME,
                                ERuntimeSource.OTHER_LITERAL);
                symbols.put(PAGE_FLOW_SCOPE_VARIABLE_NAME, pageFlowVar);
                final ISymbol processScopeVar = _symbolFactory
                .createUnknownInstanceSymbol(
                        PROCESS_SCOPE_VARIABLE_NAME,
                        ERuntimeSource.OTHER_LITERAL);
                symbols.put(PROCESS_SCOPE_VARIABLE_NAME, processScopeVar);
                return symbols;
            }
        }
        return Collections.emptyMap(); 
    }
}
