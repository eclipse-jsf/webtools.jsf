package org.eclipse.jst.jsf.designtime.internal.resolver;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.w3c.dom.Node;

/**
 * Defines a resolver that can resolve the ITagElement information from the
 * structured document region context.
 *
 */
public interface ITagElementResolver extends IDocumentContextResolver {
	
	/**
	 * @param node
	 * @return ITagElement 
	 */
	public ITagElement getTagElement(final Node node);
	
	public boolean canResolveContext(final IModelContext modelContext);
}
