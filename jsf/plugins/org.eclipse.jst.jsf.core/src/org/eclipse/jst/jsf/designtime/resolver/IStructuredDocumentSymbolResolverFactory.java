package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * A factory for symbol context resolvers.
 * 
 * Clients may use but implement this interface.  To implement, sub-class
 * AbstractStructuredDocumentSymbolResolverFactory
 * 
 * @author cbateman
 *
 */
public interface IStructuredDocumentSymbolResolverFactory
{
    /**
     * @param context
     * @return a new instance of symbol resolver for context
     */
    public ISymbolContextResolver getSymbolContextResolver(IModelContext context);
}
