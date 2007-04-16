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

package org.eclipse.jst.jsf.context.resolver.structureddocument;

import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Defines a context resolver that resolves tag library information
 * for a particular context.
 * 
 * This interface may be sub-classed or implemented by clients
 * 
 * @author cbateman
 *
 */
public interface ITaglibContextResolver extends IDocumentContextResolver 
{
    /**
     * @param node 
     * @return the URI corresponding to the taglib that defines the 
     * node in the context document or null if it cannot be found
     */
    String getTagURIForNodeName(Node node);
    
	/**
	 * @param uri
	 * @return the tag prefix used in the context document for
	 * the provided uri or null if it cannot be found.
	 */
	String	 getTagPrefixForURI(String uri);
	
	/**
	 * A list all tags in the document context that are called
	 * tagName defined in the namespace indicated by uri
	 * 
	 * @param uri
	 * @param tagName
	 * @return a (possibly empty) list of tags -- must never
	 * be null.  Indicate none found with an empty list.
	 */
	NodeList getTagsByNamespaceURI(String uri, String tagName);
    
    /**
     * @param node
     * @return true if node has a tag library associated with it in this context
     */
    public boolean hasTag(Node node);
}
