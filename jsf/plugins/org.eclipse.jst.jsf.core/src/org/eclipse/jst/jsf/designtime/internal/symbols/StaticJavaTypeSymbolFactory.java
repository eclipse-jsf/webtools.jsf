/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.IAdditionalContextSymbolInfo;
import org.eclipse.jst.jsf.designtime.symbols.FileContextUtil;
import org.eclipse.jst.jsf.designtime.symbols.JSFSymbolFactory;

/**
 * Creates symbols using a simple type.
 * 
 * @author cbateman
 * 
 */
public class StaticJavaTypeSymbolFactory extends AbstractContextSymbolFactory
{
    private final JSFSymbolFactory _factory = new JSFSymbolFactory();

    @Override
    protected ISymbol internalCreate(String symbolName, int scope,
            IAdaptable context, List problems,
            IAdditionalContextSymbolInfo additionalInfo)
    {
        if (additionalInfo != null)
        {
            final String signature = additionalInfo.getSymbolTypeSignature();
            if (signature != null)
            {
                final IJavaTypeDescriptor2 descriptor = _factory
                        .createTypeDescriptorFromSignature(signature,
                                getJavaProject(context));
                return _factory.createJavaComponentSymbol(symbolName, descriptor, null);
            }
        }

        // can't create
        return null;
    }

    @Override
    protected ISymbol internalCreate(String symbolName, int scope,
            IAdaptable context, List problems)
    {
        // need additionInfo to create the symbol
        return null;
    }

    @Override
    public boolean supports(IAdaptable context)
    {
        return getJavaProject(context) != null;
    }

    private IJavaProject getJavaProject(final IAdaptable context)
    {
        if (context != null)
        {
            final IStructuredDocumentContext sContext = (IStructuredDocumentContext) context
                    .getAdapter(IStructuredDocumentContext.class);
            if (sContext != null)
            {
                final IProject project = FileContextUtil
                        .getProject((IStructuredDocumentContext) context
                                .getAdapter(IStructuredDocumentContext.class));
                if (project != null)
                {
                    return JavaCore.create(project);
                }
            }
        }
        return null;
    }
}
