package org.eclipse.jst.jsf.context.structureddocument;

import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;

/**
 * Abstract implementation of IStructuredDocumentContextFactory that must be used
 * by all implementers of new factories for document contexts.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractStructuredDocumentContextFactory extends AbstractDelegatingFactory {

    /**
     * @param supportedDelegateTypes
     */
    protected AbstractStructuredDocumentContextFactory(Class[] supportedDelegateTypes)
    {
        super(supportedDelegateTypes);
    }
}
