package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * A base class for symbol resolver factories.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractStructuredDocumentSymbolResolverFactory implements
        IStructuredDocumentSymbolResolverFactory
{
    /**
     * @see org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory#getSymbolContextResolver(org.eclipse.jst.jsf.context.IModelContext)
     */
    public abstract ISymbolContextResolver getSymbolContextResolver(IModelContext context);
}
