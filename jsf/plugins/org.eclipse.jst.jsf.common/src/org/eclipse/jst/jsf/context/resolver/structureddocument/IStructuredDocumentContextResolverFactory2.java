package org.eclipse.jst.jsf.context.resolver.structureddocument;

import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * An additional factory interface for context resolvers.
 * 
 * @author cbateman
 *
 */
public interface IStructuredDocumentContextResolverFactory2 extends
        IStructuredDocumentContextResolverFactory
{
    /**
     * The global instance of the factory
     */
    public static final IStructuredDocumentContextResolverFactory2  INSTANCE = 
        (IStructuredDocumentContextResolverFactory2) IStructuredDocumentContextResolverFactory.INSTANCE;

    /**
     * Same as getTaglibContextResolver but checks delegate factory first.
     * 
     * @param context
     * @return a resolver capable of resolving information in the context
     * or null one cannot be created
     */
    ITaglibContextResolver getTaglibContextResolverFromDelegates(IStructuredDocumentContext context);

}
