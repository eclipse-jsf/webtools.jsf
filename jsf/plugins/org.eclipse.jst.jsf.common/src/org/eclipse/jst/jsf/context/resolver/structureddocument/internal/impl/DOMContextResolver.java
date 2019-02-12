/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
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

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Resolves DOM-related information in the provided context
 * 
 * @author cbateman
 *
 */
class DOMContextResolver implements IDOMContextResolver 
{
	private final IStructuredDocumentContext		_context;
    //private ITextRegionContextResolver              _regionResolver; // null; lazy initialized if needed
	
	DOMContextResolver(IStructuredDocumentContext context)
	{
		_context = context;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver#getDOMDocument()
	 */
	public Document getDOMDocument() 
	{
        return StructuredDocumentResolverUtil.getDOMDocument((IStructuredDocument)_context.getStructuredDocument());
	}

	public Node getNode()
	{
		IndexedRegion region = StructuredDocumentResolverUtil.getIndexedRegion((IStructuredDocument) _context.getStructuredDocument(), _context.getDocumentPosition());
        
        if (region instanceof Node)
        {
            return (Node) region;
        }
        
        return null;
	}
    

	public boolean canResolveContext(IModelContext modelContext) 
	{
		return modelContext.getAdapter(IStructuredDocumentContext.class) != null;
	}
}
