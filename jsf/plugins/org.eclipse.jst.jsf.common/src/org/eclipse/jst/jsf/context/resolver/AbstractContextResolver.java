package org.eclipse.jst.jsf.context.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * The super-class of all context resolver impls.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractContextResolver implements IContextResolver {

    public abstract boolean canResolveContext(IModelContext modelContext); 
}
