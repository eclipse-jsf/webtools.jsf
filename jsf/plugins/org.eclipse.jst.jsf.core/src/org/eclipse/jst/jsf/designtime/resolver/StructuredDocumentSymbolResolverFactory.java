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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * Factory create resolver capable of resolving symbols in a structured document
 * 
 * @author cbateman
 *
 */
public final class StructuredDocumentSymbolResolverFactory extends
        AbstractDelegatingFactory 
{
    private static StructuredDocumentSymbolResolverFactory INSTANCE;
    
    /**
     * @return the singleton factory instance
     */
    public final static synchronized StructuredDocumentSymbolResolverFactory getInstance()
    {
        if (INSTANCE == null)
        {
            // no delegates supported
            INSTANCE = new StructuredDocumentSymbolResolverFactory(new Class[0]);
        }
        
        return INSTANCE;
    }
    
    private StructuredDocumentSymbolResolverFactory(Class[] supportedDelegateTypes) 
    {
        super(supportedDelegateTypes);
    }

    /**
     * @param context
     * @return a new instance of symbol resolver for context
     */
    public ISymbolContextResolver getSymbolContextResolver(IStructuredDocumentContext context) {
        ISymbolContextResolver  resolver = internalGetSymbolContextResolver(context);
        
        if (resolver == null)
        {
            resolver = delegateGetSymbolContextResolver();
        }
        
        return resolver;
    }
    
    private ISymbolContextResolver internalGetSymbolContextResolver(IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new SymbolContextResolver(context);
        }
        
        return null;
    }
    
    private ISymbolContextResolver delegateGetSymbolContextResolver()
    {
        // no delegates currently supported
        return null;
    }
}
