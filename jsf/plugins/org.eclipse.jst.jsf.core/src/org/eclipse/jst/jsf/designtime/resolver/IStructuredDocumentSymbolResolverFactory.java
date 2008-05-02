/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * A factory for symbol context resolvers.
 * 
 * Clients may use but implement this interface.  To implement, sub-class
 * AbstractStructuredDocumentSymbolResolverFactory
 * 
 * @author cbateman
 *
 */
public interface IStructuredDocumentSymbolResolverFactory
{
    /**
     * @param context
     * @return a new instance of symbol resolver for context
     */
    public ISymbolContextResolver getSymbolContextResolver(IModelContext context);
}
