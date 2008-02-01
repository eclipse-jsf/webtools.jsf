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

package org.eclipse.jst.jsf.context.structureddocument.internal.impl;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jst.jsf.context.structureddocument.AbstractStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory2;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author cbateman
 *
 */
public class StructuredDocumentContextFactory extends AbstractStructuredDocumentContextFactory
			 implements IStructuredDocumentContextFactory, IStructuredDocumentContextFactory2
{
	/* static attributes */
	private static StructuredDocumentContextFactory  INSTANCE;

	/**
	 * @param supportedDelegateTypes
	 */
	public StructuredDocumentContextFactory(Class[]  supportedDelegateTypes)
	{
	    super(supportedDelegateTypes);
	}
	
	/**
	 * @return an instance (possibly shared) of the this factory
	 */
	public synchronized static StructuredDocumentContextFactory getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new StructuredDocumentContextFactory();
		}
		
		return INSTANCE;
	}
	
	
	/**
	 * Construct the factory
	 */
	protected StructuredDocumentContextFactory()
	{
		super(new Class[] {IStructuredDocumentContextFactory.class});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory#getContext(org.eclipse.jface.text.ITextViewer, int)
	 */
	public final IStructuredDocumentContext getContext(ITextViewer textViewer, int documentPosition) 
	{
		// first see if I know how to make one
		IStructuredDocumentContext  context = internalGetContext(textViewer, documentPosition);
		
		// if I don't know, ask my delegates
		if (context == null)
		{
			context = delegateGetContext(textViewer, documentPosition);
		}
		
		return context;
	}

	private IStructuredDocumentContext internalGetContext(ITextViewer textViewer, int documentPosition)
	{
		if (textViewer instanceof StructuredTextViewer)
		{
			IDocument document = ((StructuredTextViewer)textViewer).getDocument();
			return internalGetContext(document, documentPosition);
		}
		
		return null;
	}
	
	private IStructuredDocumentContext internalGetContext(IDocument document, int documentPosition)
	{
		if (document instanceof IStructuredDocument)
		{
			return new DefaultStructuredDocumentContext((IStructuredDocument)document, documentPosition);
		}
		
		return null;
	}
	
	private IStructuredDocumentContext delegateGetContext(ITextViewer textViewer, int documentPosition)
	{
		synchronized(_delegates)
		{
			for (final Iterator it = _delegates.iterator(); it.hasNext();)
			{
				IStructuredDocumentContextFactory delegateFactory = (IStructuredDocumentContextFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
				IStructuredDocumentContext context = delegateFactory.getContext(textViewer, documentPosition);
				
				if (context != null)
				{
					return context;
				}
			}
			
			return null;
		}
	}

	/**
	 * @see org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory#getContext(org.eclipse.jface.text.IDocument, int)
	 */
	public IStructuredDocumentContext getContext(IDocument document, int documentPosition) {
		// first see if I know how to make one
		IStructuredDocumentContext  context = internalGetContext(document, documentPosition);
		
		// if I don't know, ask my delegates
		if (context == null)
		{
			context = delegateGetContext(document, documentPosition);
		}
		
		return context;
	}
	
	private IStructuredDocumentContext delegateGetContext(IDocument document, int documentPosition)
	{
		synchronized(_delegates)
		{
			for (final Iterator it = _delegates.iterator(); it.hasNext();)
			{
				IStructuredDocumentContextFactory delegateFactory = (IStructuredDocumentContextFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
				IStructuredDocumentContext context = delegateFactory.getContext(document, documentPosition);
				
				if (context != null)
				{
					return context;
				}
			}
			
			return null;
		}
	}

    public IStructuredDocumentContext getContext(IDocument document, Node node) 
    {
        // first see if I know how to make one
        IStructuredDocumentContext  context = internalGetContext(document, node);
        
        // if I don't know, ask my delegates
        if (context == null)
        {
            context = delegateGetContext(document, node);
        }
        
        return context;

    }
    


	private IStructuredDocumentContext internalGetContext(IDocument document, Node node)
    {
        if (document instanceof IStructuredDocument)
        {
            final IStructuredDocument sDoc = (IStructuredDocument) document;
            if (node instanceof IndexedRegion)
            {
                final int position = ((IndexedRegion)node).getStartOffset();
                return new DefaultStructuredDocumentContext(sDoc, position);
            }
            else if (node instanceof IDOMAttr)
            {
                IDOMAttr  attr = (IDOMAttr) node;
                final int position = attr.getValueRegionStartOffset();
                return new DefaultStructuredDocumentContext(sDoc, position);
            }
        }
        
        return null;
    }
    
    private IStructuredDocumentContext delegateGetContext(IDocument document, Node node)
    {
        synchronized(_delegates)
        {
            for (final Iterator it = _delegates.iterator(); it.hasNext();)
            {
                IStructuredDocumentContextFactory delegateFactory = (IStructuredDocumentContextFactory) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory.class);
                IStructuredDocumentContext context = delegateFactory.getContext(document, node);
                
                if (context != null)
                {
                    return context;
                }
            }
            
            return null;
        }
    }
    
    public IStructuredDocumentContext getContext(Element element) 
    {
        // first see if I know how to make one
        IStructuredDocumentContext  context = internalGetContext(element);
        
        // if I don't know, ask my delegates
        if (context == null)
        {
            context = delegateGetContext(element);
        }
        
        return context;

    }
    
	private IStructuredDocumentContext internalGetContext(Element element) {
		if (element instanceof IDOMNode){
			final IDOMNode node = (IDOMNode)element;
			final IStructuredDocument sDoc = node.getStructuredDocument();
            final int position = node.getStartOffset();
            
            return new DefaultStructuredDocumentContext(sDoc, position);            
        }
		
		return null;
	}
	
    private IStructuredDocumentContext delegateGetContext(Element element) {
        synchronized(_delegates)
        {
            for (final Iterator it = _delegates.iterator(); it.hasNext();)
            {
                IStructuredDocumentContextFactory2 delegateFactory = (IStructuredDocumentContextFactory2) ((IAdaptable) it.next()).getAdapter(IStructuredDocumentContextFactory2.class);
                if (delegateFactory != null)
                {
	                IStructuredDocumentContext context = delegateFactory.getContext(element);
	                
	                if (context != null)
	                {
	                    return context;
	                }
                }
            }
            
            return null;
        }
	}


}
