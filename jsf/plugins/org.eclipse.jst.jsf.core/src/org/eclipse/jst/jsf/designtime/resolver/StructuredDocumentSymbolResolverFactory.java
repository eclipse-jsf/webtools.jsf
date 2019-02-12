/*******************************************************************************
 * Copyright (c) 2006, 2009 Oracle Corporation.
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

package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * Factory create resolver capable of resolving symbols in a structured document
 * 
 * Clients may not sub-class.
 * 
 * @author cbateman
 * 
 */
public final class StructuredDocumentSymbolResolverFactory extends
        AbstractDelegatingFactory implements
        IStructuredDocumentSymbolResolverFactory
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
            INSTANCE = new StructuredDocumentSymbolResolverFactory(new Class[]
            { IStructuredDocumentSymbolResolverFactory.class });
        }

        return INSTANCE;
    }

    private StructuredDocumentSymbolResolverFactory(
            Class[] supportedDelegateTypes)
    {
        super(supportedDelegateTypes);
        for (final IAdaptable delegate : SymbolContextResolverReader
                .getAllHandlers())
        {
            addFactoryDelegate(delegate);
        }
    }

    /**
     * @param context
     * @return a new instance of symbol resolver for context
     */
    public ISymbolContextResolver getSymbolContextResolver(IModelContext context)
    {
        ISymbolContextResolver resolver = delegateGetSymbolContextResolver(context);

        if (resolver == null)
        {
            resolver = internalGetSymbolContextResolver(context);
        }

        return resolver;
    }

    private ISymbolContextResolver internalGetSymbolContextResolver(
            IModelContext context)
    {
        if (context instanceof IStructuredDocumentContext
                && ((IStructuredDocumentContext) context)
                        .getStructuredDocument() instanceof IStructuredDocument)
        {
            return new SymbolContextResolver(
                    (IStructuredDocumentContext) context);
        }

        return null;
    }

    private ISymbolContextResolver delegateGetSymbolContextResolver(
            IModelContext context)
    {
        for (final IAdaptable adaptable : _delegates)
        {
            final IStructuredDocumentSymbolResolverFactory delegateFactory = 
                (IStructuredDocumentSymbolResolverFactory) adaptable
                    .getAdapter(IStructuredDocumentSymbolResolverFactory.class);
            ISymbolContextResolver symbolContextResolver = delegateFactory
                    .getSymbolContextResolver(context);

            if (symbolContextResolver != null)
            {
                return symbolContextResolver;
            }
        }
        return null;
    }
}
