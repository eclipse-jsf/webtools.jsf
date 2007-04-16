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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal;

import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * Defines a resolver that can resolve context information in the
 * structured document region context.
 * 
 * Unless otherwise stated, the context region is the most specific
 * region (leaf) in which the current context is contained.
 * 
 * Note: this interface may use internal/provisional types.
 * 
 * @author cbateman
 *
 */
public interface ITextRegionContextResolver extends IDocumentContextResolver 
{
	/**
	 * @return the text for the context region
	 */
	String getRegionText();
	
	/**
	 * @return the document relative offset of the start of the region
	 * in which the context in is contained or -1 if the offset cannot be determined.
	 */
	int getStartOffset();
	
	/**
	 * @return the document relative offset of the end of the region
	 * in which the context is contained or -1 if the offset cannot be determined
	 */
	int getEndOffset();
	
	/**
	 * @return the length of the most specific region in which the context
	 * is contained or -1 if the length cannot be determined.
	 */
	int getLength();
	
	/**
	 * @return the most specific TextRegion type for the current context  
	 */
	String  getRegionType();
	
	/**
	 * @return the path to the most specific TextRegtion for the current context
	 * note that this does not include the actual region for the current region.
	 * That is returned by getRegionType(). 
	 *
	 */
	String[]  getRegionTypePath();
	
	
	/**
	 * The offset into the structured document will be set to be
	 * the start offset of the previous region
	 * 
	 * @return a context for the previous sibling of this context's
	 * text region parent or null if no previous sibling exists
	 */
	IStructuredDocumentContext getPreviousContext();
	
	/**
	 * The offset into the structured document will be set to be
	 * the start offset of the next region
	 * 
	 * @return a context for the next sibling of this context's
	 * text region parent or null if no next sibling exists
	 */
	IStructuredDocumentContext getNextContext();
	
	/**
	 * Relative path works backwards from the current context.  So if the
	 * path to current context is /a/b/c/d/e then:
	 * 
	 * matchesRelative({"e"}) == true
	 * matchesRelative({"d", "e"}) == true
	 * matchesRelative({"c", "d", "e"}) == true
	 * matchesRelative({"b", "c", "d", "e"}) == true
	 * matchesRelative({"a", "b", "c", "d", "e"}) == true
	 * 
	 * because in every case the path matches working backward from the current
	 * context 
	 * 
	 * but
	 * 
	 * matchesRelative({a}) == false because context/.. == e not a
	 * 
	 * TODO: what does path = new String[0] mean?
	 * 
	 * @param path
	 * @return true if the relative path matches the path to the current context
	 * working relative to the current context.
	 */
	boolean   matchesRelative(String[] path);
	
	/**
	 * Absolute path works forward from the root of the document to the 
	 * current context.  So if the path to the current context is /a/b/c/d/e then:
	 * 
	 * matchesAbsolute({"a", "b", "c", "d", "e"}) == true
	 * 
	 * but 
	 * 
	 * matchesAbsolute({"b", "c", "d", "e"}) == true because starting from the root
	 * you cannot follow /b/c/d/e to the context.
	 * 
	 * @param path
	 * @return true if the absolute path from the root of the document 
	 * matches the current context
	 */
	boolean   matchesAbsolute(String[] path);
}
