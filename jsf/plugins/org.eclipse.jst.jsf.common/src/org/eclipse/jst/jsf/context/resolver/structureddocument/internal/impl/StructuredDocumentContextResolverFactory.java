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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * Implements a factory for creating context resolvers for structured document
 * contexts.
 * 
 * @author cbateman
 * 
 */
public class StructuredDocumentContextResolverFactory extends
AbstractDelegatingFactory implements
IStructuredDocumentContextResolverFactory
{
    /* static attributes */
    private static StructuredDocumentContextResolverFactory INSTANCE;

    /**
     * @return an instance (possibly shared) of the this factory
     */
    public synchronized static StructuredDocumentContextResolverFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new StructuredDocumentContextResolverFactory();
        }

        return INSTANCE;
    }

    /**
     * Constructor
     */
    protected StructuredDocumentContextResolverFactory()
    {
        super(new Class[]
                        { IStructuredDocumentContextResolverFactory.class });
    }

    /**
     * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getDOMContextResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
     */
    public IDOMContextResolver getDOMContextResolver(
            final IStructuredDocumentContext context)
    {
        IDOMContextResolver resolver = internalGetDOMResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetDOMResolver(context);
        }

        return resolver;
    }

    private IDOMContextResolver internalGetDOMResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new DOMContextResolver(context);
        }

        return null;
    }

    private IDOMContextResolver delegateGetDOMResolver(
            final IStructuredDocumentContext context)
    {
        synchronized (_delegates)
        {
            for (final IAdaptable adaptable : _delegates)
            {

                final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) adaptable
                .getAdapter(IStructuredDocumentContextResolverFactory.class);

                if (delegateFactory != null)
                {
                    final IDOMContextResolver contextResolver = delegateFactory
                    .getDOMContextResolver(context);

                    if (contextResolver != null)
                    {
                        return contextResolver;
                    }
                }
            }

            return null;
        }
    }

    /**
     * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getTextRegionResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
     */
    public ITextRegionContextResolver getTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        ITextRegionContextResolver resolver = internalGetTextRegionResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetTextRegionResolver(context);
        }

        return resolver;
    }

    private ITextRegionContextResolver internalGetTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new TextRegionContextResolver(context);
        }

        return null;
    }

    private ITextRegionContextResolver delegateGetTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        synchronized (_delegates)
        {
            for (final Object element : _delegates)
            {
                final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) element)
                .getAdapter(IStructuredDocumentContextResolverFactory.class);
                final ITextRegionContextResolver contextResolver = delegateFactory
                .getTextRegionResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }

            return null;
        }
    }

    public IWorkspaceContextResolver getWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        IWorkspaceContextResolver resolver = internalGetWorkspaceContextResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetWorkspaceContextResolver(context);
        }

        return resolver;

    }

    private IWorkspaceContextResolver internalGetWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new WorkspaceContextResolver(context);
        }

        return null;
    }

    private IWorkspaceContextResolver delegateGetWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        synchronized (_delegates)
        {
            for (final Object element : _delegates)
            {
                final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) element)
                .getAdapter(IStructuredDocumentContextResolverFactory.class);
                final IWorkspaceContextResolver contextResolver = delegateFactory
                .getWorkspaceContextResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }

            return null;
        }
    }

    public ITaglibContextResolver getTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        // check the delegats first
        ITaglibContextResolver resolver = delegateGetTaglibContextResolver(context);

        if (resolver == null)
        {
            resolver = internalGetTaglibContextResolver(context);
        }

        return resolver;
    }

    private ITaglibContextResolver internalGetTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new TaglibContextResolver(context);
        }

        return null;
    }

    private ITaglibContextResolver delegateGetTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        synchronized (_delegates)
        {
            for (final IAdaptable adapter : _delegates)
            {
                final IStructuredDocumentContextResolverFactory delegateFactory =
                    (IStructuredDocumentContextResolverFactory) adapter
                    .getAdapter(IStructuredDocumentContextResolverFactory.class);

                if (delegateFactory != null)
                {
                    final ITaglibContextResolver contextResolver = delegateFactory
                    .getTaglibContextResolver(context);

                    if (contextResolver != null)
                    {
                        return contextResolver;
                    }

                }
            }

            return null;
        }
    }

    public IMetadataContextResolver getMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        IMetadataContextResolver resolver = internalGetMetadataContextResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetMetadataContextResolver(context);
        }

        return resolver;
    }

    private IMetadataContextResolver internalGetMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new MetadataContextResolver(context);
        }

        return null;
    }

    private IMetadataContextResolver delegateGetMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        synchronized (_delegates)
        {
            for (final Object element : _delegates)
            {
                final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) element)
                .getAdapter(IStructuredDocumentContextResolverFactory.class);
                final IMetadataContextResolver contextResolver = delegateFactory
                .getMetadataContextResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }

            return null;
        }
    }
}
