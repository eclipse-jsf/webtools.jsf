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

package org.eclipse.jst.jsf.context.structureddocument.internal.provisional;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jst.jsf.context.structureddocument.internal.impl.StructuredDocumentContextFactory;
import org.w3c.dom.Node;


/**
 * A factory method for constructing context objects related to
 * SSE structured documents.
 *
 * May be sub-classed or implemented by clients
 * 
 * @author cbateman
 *
 */
public interface IStructuredDocumentContextFactory
{
	/**
	 * The singleton instance of the factory
	 */
	public static final IStructuredDocumentContextFactory  INSTANCE = 
        StructuredDocumentContextFactory.getInstance();
	
	/**
	 * 
	 * @param textViewer -- the text viewer where the context exists
	 * @param documentPosition -- the absolute position of the context relative to textViewer
	 * @return a context for a given text viewer and document position within the
	 * document model of that text viewer or null if one cannot be determined.
	 */
	IStructuredDocumentContext getContext(ITextViewer textViewer, int documentPosition);
	
	/**
	 * @param document -- the document
	 * @param documentPosition -- the absolute position of the context relative to the document
	 * @return a context for the document model or null if one cannot be determined
	 */
	IStructuredDocumentContext getContext(IDocument document, int documentPosition);
    
    /**
     * @param document -- the document model
     * @param node -- the DOM node within the document
     * @return a context for the document model and dom node or null if one cannot
     * be determined
     */
    IStructuredDocumentContext getContext(IDocument document, Node node);
}
