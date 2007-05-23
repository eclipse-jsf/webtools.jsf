package org.eclipse.jst.jsf.context.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * The parent of all IDocumentContextResolver implementations.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDocumentContextResolver implements
        IDocumentContextResolver {

    public abstract boolean canResolveContext(IModelContext modelContext);
}
