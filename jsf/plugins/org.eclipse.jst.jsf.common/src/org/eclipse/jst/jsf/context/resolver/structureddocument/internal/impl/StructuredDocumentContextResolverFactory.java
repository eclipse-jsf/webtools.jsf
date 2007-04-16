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

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
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
	private static StructuredDocumentContextResolverFactory  INSTANCE;

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
		super(new Class[] {IStructuredDocumentContextResolverFactory.class});
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getDOMContextResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public IDOMContextResolver getDOMContextResolver(IStructuredDocumentContext context) 
	{
        IDOMContextResolver  resolver = internalGetDOMResolver(context);
        
        if (resolver == null)
        {
            resolver = delegateGetDOMResolver(context);
        }
        
        return resolver;
    }
    private IDOMContextResolver internalGetDOMResolver(IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new DOMContextResolver(context);
        }
        
        return null;
    }
    
    private IDOMContextResolver delegateGetDOMResolver(IStructuredDocumentContext context)
    {
        synchronized(_delegates)
        {
            for (final Iterator it = _delegates.iterator(); it.hasNext();)
            {
                IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
                IDOMContextResolver contextResolver = delegateFactory.getDOMContextResolver(context);
                
                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
            
            return null;
        }
    }

    
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getTextRegionResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public ITextRegionContextResolver getTextRegionResolver(IStructuredDocumentContext context) 	
	{
		ITextRegionContextResolver  resolver = internalGetTextRegionResolver(context);
		
		if (resolver == null)
		{
			resolver = delegateGetTextRegionResolver(context);
		}
		
		return resolver;
	}
	private ITextRegionContextResolver internalGetTextRegionResolver(IStructuredDocumentContext context)
	{
		if (context.getStructuredDocument() instanceof IStructuredDocument)
		{
			return new TextRegionContextResolver(context);
		}
		
		return null;
	}
	
	private ITextRegionContextResolver delegateGetTextRegionResolver(IStructuredDocumentContext context)
	{
		synchronized(_delegates)
		{
			for (final Iterator it = _delegates.iterator(); it.hasNext();)
			{
				IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
				ITextRegionContextResolver contextResolver = delegateFactory.getTextRegionResolver(context);
				
				if (contextResolver != null)
				{
					return contextResolver;
				}
			}
			
			return null;
		}
	}
	
	public IWorkspaceContextResolver getWorkspaceContextResolver(IStructuredDocumentContext context)
	{
		IWorkspaceContextResolver  resolver = internalGetWorkspaceContextResolver(context);
		
		if (resolver == null)
		{
			resolver = delegateGetWorkspaceContextResolver(context);
		}
		
		return resolver;

	}
	
	private IWorkspaceContextResolver internalGetWorkspaceContextResolver(IStructuredDocumentContext context)
	{
		if (context.getStructuredDocument() instanceof IStructuredDocument)
		{
			return new WorkspaceContextResolver(context);
		}
		
		return null;
	}
	
	private IWorkspaceContextResolver delegateGetWorkspaceContextResolver(IStructuredDocumentContext context)
	{
		synchronized(_delegates)
		{
			for (final Iterator it = _delegates.iterator(); it.hasNext();)
			{
				IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
				IWorkspaceContextResolver contextResolver = delegateFactory.getWorkspaceContextResolver(context);
				
				if (contextResolver != null)
				{
					return contextResolver;
				}
			}
			
			return null;
		}
	}

    public ITaglibContextResolver getTaglibContextResolver(IStructuredDocumentContext context) {
        ITaglibContextResolver  resolver = internalGetTaglibContextResolver(context);
        
        if (resolver == null)
        {
            resolver = delegateGetTaglibContextResolver(context);
        }
        
        return resolver;
    }
    
    private ITaglibContextResolver internalGetTaglibContextResolver(IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new TaglibContextResolver(context);
        }
        
        return null;
    }
    
    private ITaglibContextResolver delegateGetTaglibContextResolver(IStructuredDocumentContext context)
    {
        synchronized(_delegates)
        {
            for (final Iterator it = _delegates.iterator(); it.hasNext();)
            {
                IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
                ITaglibContextResolver contextResolver = delegateFactory.getTaglibContextResolver(context);
                
                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
            
            return null;
        }
    }

    public IMetadataContextResolver getMetadataContextResolver(IStructuredDocumentContext context) {
        IMetadataContextResolver  resolver = internalGetMetadataContextResolver(context);
        
        if (resolver == null)
        {
            resolver = delegateGetMetadataContextResolver(context);
        }
        
        return resolver;
    }
    
    private IMetadataContextResolver internalGetMetadataContextResolver(IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new MetadataContextResolver(context);
        }
        
        return null;
    }
    
    private IMetadataContextResolver delegateGetMetadataContextResolver(IStructuredDocumentContext context)
    {
        synchronized(_delegates)
        {
            for (final Iterator it = _delegates.iterator(); it.hasNext();)
            {
                final IStructuredDocumentContextResolverFactory delegateFactory = 
                    (IStructuredDocumentContextResolverFactory) 
                        ((IAdaptable) it.next()).getAdapter
                            (IStructuredDocumentContextFactory.class);
                final IMetadataContextResolver contextResolver = 
                    delegateFactory.getMetadataContextResolver(context);
                
                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
            
            return null;
        }
    }
}
