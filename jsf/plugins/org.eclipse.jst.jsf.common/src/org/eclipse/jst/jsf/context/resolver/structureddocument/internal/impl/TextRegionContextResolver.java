/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;

/*package*/ class TextRegionContextResolver implements ITextRegionContextResolver {

	private final IStructuredDocumentContext		_context;
	
	/*package*/ TextRegionContextResolver(IStructuredDocumentContext context)
	{
		_context = context;
	}
	
	
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getRegionText()
	 */
	public String getRegionText() 
	{
		final ITextRegionCollection parent = getParentRegion();
		String text = null;
		
		if (parent != null)
		{
			ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
			    text = parent.getText(me);
			}
		}
	
		return text;
	}

	
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getEndOffset()
	 */
	public int getEndOffset() {
		final ITextRegionCollection parent = getParentRegion();
		int endOffset = -1;
		if (parent != null)
		{
			ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
			    endOffset =  parent.getEndOffset(me);
			}
		}
		
		return endOffset;
	}


	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getLength()
	 */
	public int getLength() {
		final ITextRegionCollection parent = getParentRegion();
		int length = -1;
		if (parent != null)
		{
			ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
			    length =  me.getLength();
			}
		}
		
		return length;

	}


	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getStartOffset()
	 */
	public int getStartOffset() 
	{
		final ITextRegionCollection parent = getParentRegion();
		int startOffset = -1;
		if (parent != null)
		{
			ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
			    startOffset =  parent.getStartOffset(me);
			}
		}
		
		return startOffset;
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getRegionType()
	 */
	public String getRegionType() 
	{
		final ITextRegionCollection  parent = getParentRegion();
		String  regionType = null;
		
		if (parent != null)
		{
			ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			
			if (me != null)
			{
				regionType = me.getType();
			}
		}
		
		return regionType;
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getRegionTypePath()
	 */
	public String[] getRegionTypePath() {
		final ITextRegion[]  path = createPathToContext();
		final String[] typePath = new String[path.length];
		
		for (int i = 0; i < path.length; i++)
		{
			typePath[i] = path[i].getType();
		}
		
		return typePath;
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getNextContext()
	 */
	public IStructuredDocumentContext getNextContext() 
	{
		ITextRegionCollection  parent = getParentRegion();
		IStructuredDocumentContext newContext = null;
		
		if (parent != null)
		{
			final ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
    			ITextRegionList regions = parent.getRegions();
    			ITextRegion nextRegion = null;
    			for (final Iterator it = regions.iterator(); it.hasNext();)
    			{
    				if (it.next() == me
    						&& it.hasNext())
    				{
    					nextRegion = (ITextRegion) it.next();
    				}
    			}
    			
    			if (nextRegion != null)
    			{
    				// use the first position offset in the next region
    				final int documentPosition = parent.getStartOffset(nextRegion);
    				newContext = 
    					IStructuredDocumentContextFactory.INSTANCE.getContext(_context.getStructuredDocument(), documentPosition);
    			}
			}
		}
		
		return newContext;
	}


	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#getPreviousContext()
	 */
	public IStructuredDocumentContext getPreviousContext() 
	{
		ITextRegionCollection  parent = getParentRegion();
		IStructuredDocumentContext newContext = null;
		
		if (parent != null)
		{
			final ITextRegion me = parent.getRegionAtCharacterOffset(_context.getDocumentPosition());
			if (me != null)
			{
    			ITextRegionList regions = parent.getRegions();
    			final Iterator it = regions.iterator();
    			ITextRegion  prevRegion = null;
    			ITextRegion  nextRegion = null;
    			
    			SEARCH_LOOP: while (it.hasNext())
    			{
    				nextRegion = (ITextRegion) it.next();
    
    				if (nextRegion == me)
    				{
    					break SEARCH_LOOP;
    				}
    				
    				prevRegion = nextRegion;
    			}
    			
    			if (prevRegion != null)
    			{
    				// use the last position offset in the prev region
    				final int documentPosition = parent.getStartOffset(prevRegion);
    				newContext = 
    					IStructuredDocumentContextFactory.INSTANCE.getContext(_context.getStructuredDocument(), documentPosition);
    			}
			}
		}
		
		return newContext;
	}

	private ITextRegionCollection getParentRegion()
	{
		final ITextRegion[] path = createPathToContext();
		ITextRegionCollection parent = null;
		
		if (path.length > 0)
		{
			parent =  (ITextRegionCollection) path[path.length-1];
		}
		
		return parent;
	}
	
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#matchesRelative(java.lang.String[])
	 */
	public boolean matchesRelative(String[] path) {
		final ITextRegion[]  myPath = createPathToContext();
		return matchPath(myPath, path);
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver#matchesAbsolute(java.lang.String[])
	 */
	public boolean matchesAbsolute(String[] path) 
	{
		final ITextRegion[]  myPath = createPathToContext(); 
		// only works if myPath and path are the same length
		return (myPath.length == path.length)
					&& matchPath(myPath, path);
	}

	private boolean matchPath(ITextRegion[] myPath, String[] path)
	{
		boolean matches = false;
		
		// only makes sense if path is a containing subset of myPath
		if (path.length <= myPath.length)
		{
			matches = true;
			SEARCH_LOOP:
			for (int i = 1; i <= path.length; i++)
			{
				if (!path[path.length-i].equals(myPath[myPath.length-i].getType()))
				{
					matches = false;
					break SEARCH_LOOP;
				}
			}
		}
	
		return matches;
	}
	
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.IContextResolver#canResolveContext(org.eclipse.jst.jsf.context.IModelContext)
	 */
	public boolean canResolveContext(IModelContext modelContext) 
	{
		return (modelContext.getAdapter(IStructuredDocumentContext.class) != null);
	}

	private ITextRegion[] createPathToContext()
	{
		final List  regionPath = new ArrayList();
		
		final IStructuredDocument doc = (IStructuredDocument) _context.getStructuredDocument();

		ITextRegion  container = doc.getRegionAtCharacterOffset(_context.getDocumentPosition());

		while(container instanceof ITextRegionCollection)
		{
			regionPath.add(container);
			container = ((ITextRegionCollection)container).getRegionAtCharacterOffset(_context.getDocumentPosition());
		}
		
		return (ITextRegion[]) regionPath.toArray(new ITextRegion[0]);
	}
}
