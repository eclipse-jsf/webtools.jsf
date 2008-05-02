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
 * A base class for symbol resolver factories.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractStructuredDocumentSymbolResolverFactory implements
        IStructuredDocumentSymbolResolverFactory
{
    /**
     * @see org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory#getSymbolContextResolver(org.eclipse.jst.jsf.context.IModelContext)
     */
    public abstract ISymbolContextResolver getSymbolContextResolver(IModelContext context);
}
