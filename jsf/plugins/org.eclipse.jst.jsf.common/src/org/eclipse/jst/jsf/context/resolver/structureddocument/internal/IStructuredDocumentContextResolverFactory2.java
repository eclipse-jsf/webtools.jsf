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
package org.eclipse.jst.jsf.context.resolver.structureddocument.internal;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
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

    /**
     * @param context
     * @return a more generic way of resolving workspace context through IModelContext.  Allows for more flexible
     * resolution through non-SSE models.
     */
    IWorkspaceContextResolver getWorkspaceContextResolver2(IModelContext context);
    
    /**
     * @param <T> resolver type
     * @param context
     * @param clazz
     * @return resolver of type T
     */
    <T> T getResolver(IStructuredDocumentContext context, Class<T> clazz);

    /**
     * @param context
     * @return a resolver for xml nodes in a model context
     */
    IXMLNodeContextResolver getXMLNodeContextResolver(IModelContext context);
}
