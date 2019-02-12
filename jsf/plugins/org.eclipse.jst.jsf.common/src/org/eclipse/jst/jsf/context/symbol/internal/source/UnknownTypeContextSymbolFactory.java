/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.source;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.InitializedSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.IAdditionalContextSymbolInfo;

/**
 * Returns an unknown component symbol.
 * 
 * @author cbateman
 * 
 */
public final class UnknownTypeContextSymbolFactory extends
AbstractContextSymbolFactory
{
    private final InitializedSymbolFactory _factory = new InitializedSymbolFactory();

    @Override
    protected ISymbol internalCreate(final String symbolName, final int scope,
            final IAdaptable context, final List problems)
    {
        return internalCreate(symbolName, scope, context, null);
    }

    @Override
    protected ISymbol internalCreate(final String symbolName, final int scope,
            final IAdaptable context, final List problems,
            final IAdditionalContextSymbolInfo info)
    {
        return _factory.createUnknownComponentSymbol(symbolName,
                ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
    }
    @Override
    public boolean supports(final IAdaptable context)
    {
        return true;
    }
}
