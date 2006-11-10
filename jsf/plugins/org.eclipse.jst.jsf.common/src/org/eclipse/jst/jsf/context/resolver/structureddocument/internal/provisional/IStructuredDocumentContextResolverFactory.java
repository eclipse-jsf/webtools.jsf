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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional;

import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl.StructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;


/**
 * A factory for creating context resolvers that work with structured
 * document contexts
 * 
 * This factory may be used but not implemented or extended by clients.
 * TODO:
 * Note that getTextRegionResolver() will be moved to to an internal factory
 * interface and should not be considered part of the provisional API
 * 
 * @author cbateman
 *
 */
public interface IStructuredDocumentContextResolverFactory 
{
	
	/**
	 * The global instance of the factory
	 */
	public static final IStructuredDocumentContextResolverFactory  INSTANCE = StructuredDocumentContextResolverFactory.getInstance();

	/**
	 * @param context
	 * @return a resolver capable of resolving information in context or null
	 * if one cannot be created.
	 */
	IDOMContextResolver	getDOMContextResolver(IStructuredDocumentContext context);
	
	/**
     * **Non-WTP Clients: this method should not be considered API**
     * 
	 * @param context
	 * @return a resolver capable of resolving information in the context or
	 * null if one cannot be created
	 */
	ITextRegionContextResolver getTextRegionResolver(IStructuredDocumentContext context);
	
	/**
	 * @param context
	 * @return a resolver capable of resolving information in the context or
	 * null if one cannot be created
	 */
	IWorkspaceContextResolver  getWorkspaceContextResolver(IStructuredDocumentContext context);
	
    /**
     * @param context
     * @return a resolver capable of resolving information in the context
     * or null one cannot be created
     */
    ITaglibContextResolver getTaglibContextResolver(IStructuredDocumentContext context);
    
    /**
     * @param context
     * @return a resolver capable of resolving information in the context
     * or null if one cannot be created
     */
    IMetadataContextResolver getMetadataContextResolver(IStructuredDocumentContext context);
}
