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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.IAdditionalContextSymbolInfo;
import org.eclipse.jst.jsf.designtime.symbols.FileContextUtil;
import org.eclipse.jst.jsf.designtime.symbols.JSFSymbolFactory;
import org.eclipse.jst.jsf.validation.internal.appconfig.AppConfigValidationUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @author cbateman
 * 
 */
public class ValueExpressionSymbolFactory extends AbstractContextSymbolFactory
{
    private final JSFSymbolFactory _factory = new JSFSymbolFactory();

    @Override
    protected ISymbol internalCreate(final String symbolName, final int scope,
            final IAdaptable context, final List problems,
            final IAdditionalContextSymbolInfo additionalInfo)
    {
        final Node node = getNode(context);
        final IStructuredDocumentContext sContext = getContext(context);

        final String valueAttrName = additionalInfo
                .getValueExpressionAttributeName();
        if (node != null && valueAttrName != null)
        {
            Element element = null;

            if (node instanceof Element)
            {
                element = (Element) node;
            }
            else
            {
                element = ((Attr) node).getOwnerElement();
            }

            return createSymbol(symbolName, sContext, valueAttrName, element);
        }

        return null;
    }


    private ISymbol createSymbol(final String name,
            final IStructuredDocumentContext sContext,
            final String valueAttrName, Element element)
    {
        if (element instanceof IDOMElement) // implicit null check.
        {
            final Attr attr = element.getAttributeNode(valueAttrName);

            if (attr instanceof IDOMAttr && attr.getValue() != null)
            {
                // TODO: the extract expression method belongs in more
                // generic code
                final String elText = AppConfigValidationUtil
                        .extractELExpression(attr.getValue()).getElText();
                final IFile file = FileContextUtil
                        .deriveIFileFromContext(sContext);
                final IStructuredDocumentContext elContext = IStructuredDocumentContextFactory.INSTANCE
                        .getContext(sContext.getStructuredDocument(), attr);
                if (elText != null && file != null && elContext != null)
                {
                    final ValueType valueExpr = _factory
                            .getValueTypeFromEL(elText, elContext, file);
                    if (valueExpr != null)
                    {
                        final IJavaProject javaProject = getJavaProject(sContext);
                        return _factory.createJavaComponentSymbol(name, valueExpr, null, javaProject);
                    }
                }
            }
        }
        return null;
    }

    
    @Override
    protected ISymbol internalCreate(final String symbolName, final int scope,
            final IAdaptable context, final List problems)
    {
        // need additionalInfo
        return null;
    }

    @Override
    public boolean supports(final IAdaptable context)
    {
        final Node node = getNode(context);
        final IJavaProject javaProject = getJavaProject(context);
        return javaProject != null && (node instanceof Element) || (node instanceof Attr);
    }

    private Node getNode(final IAdaptable context)
    {
        final IStructuredDocumentContext sContext = getContext(context);
        if (sContext != null)
        {
            final IDOMContextResolver domResolver = IStructuredDocumentContextResolverFactory.INSTANCE
                    .getDOMContextResolver(sContext);
            if (domResolver != null)
            {
                return domResolver.getNode();
            }
        }
        return null;
    }

    private IJavaProject getJavaProject(final IAdaptable context)
    {
        final IStructuredDocumentContext sContext = getContext(context);
        
        if (sContext != null)
        {
            final IProject project = FileContextUtil
            .getProject((IStructuredDocumentContext) sContext
                    .getAdapter(IStructuredDocumentContext.class));
            if (project != null)
            {
                return JavaCore.create(project);
            }
        }
        return null;
    }
    
    private IStructuredDocumentContext getContext(final IAdaptable context)
    {
        if (context != null)
        {
            return (IStructuredDocumentContext) context
                    .getAdapter(IStructuredDocumentContext.class);
        }
        return null;
    }
}
