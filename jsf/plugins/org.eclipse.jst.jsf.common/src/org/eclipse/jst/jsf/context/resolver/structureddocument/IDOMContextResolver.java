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
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Defines a type of context resolver used to resolve the current context
 * in the XML DOM context.
 * 
 * This interface may be sub-classed or implemented by clients
 * 
 * @author cbateman
 *
 */
public interface IDOMContextResolver extends IDocumentContextResolver 
{
	/**
	 * @return the complete DOM Document for this context or null if it
	 * cannot be resolved.
	 */
	Document	getDOMDocument();
	
	/**
	 * @return the node which the current context is pointing to.
     * If document context is on an attribute will return that attribute
	 */
	Node		getNode();
}
