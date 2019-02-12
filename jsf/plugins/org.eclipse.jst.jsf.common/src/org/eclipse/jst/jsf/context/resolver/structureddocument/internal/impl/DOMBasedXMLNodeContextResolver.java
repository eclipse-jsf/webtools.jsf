/*******************************************************************************
 * Copyright (c) 2013, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IXMLNodeContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory2;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A default resolver based on the dom context resolver.
 *
 */
public class DOMBasedXMLNodeContextResolver implements IXMLNodeContextResolver
{
    private IDOMContextResolver delegate;

    /**
     * @param delegate
     */
    public DOMBasedXMLNodeContextResolver(final IDOMContextResolver delegate)
    {
        this.delegate = delegate;
    }
    
    public IXMLNodeContextResolver getParentNodeResolver()
    {
        Node node = this.delegate.getNode();
        Element parentNode = null;
        if (node instanceof Attr)
        {
            parentNode = ((Attr)node).getOwnerElement();
        }
        else if (node instanceof Element)
        {
            parentNode = (Element) ((Element)node).getParentNode();
        }

        if (parentNode != null)
        {
            IStructuredDocumentContext context = IStructuredDocumentContextFactory2.INSTANCE.getContext(parentNode);
            if (context != null)
            {
                IDOMContextResolver domContextResolver = IStructuredDocumentContextResolverFactory2.INSTANCE.getDOMContextResolver(context);
                if (domContextResolver != null)
                {
                    return new DOMBasedXMLNodeContextResolver(domContextResolver);
                }
            }
            
        }
        return null;
    }

    public boolean isAttribute()
    {
        return this.delegate.getNode() instanceof Attr;
    }

    public String getValue()
    {
        Node node = this.delegate.getNode();
        if (node instanceof Attr)
        {
            return ((Attr)node).getValue();
        }
        return null;
    }

    public String getLocalName()
    {
        Node node = this.delegate.getNode();
        if (node instanceof Attr)
        {
            return ((Attr)node).getLocalName();
        }
        else if (node instanceof Element)
        {
            return ((Element)node).getLocalName();
        }
        return null;
    }

    public String getNamespaceURI()
    {
        Node node = this.delegate.getNode();
        if (node != null)
        {
            return node.getNamespaceURI();
        }
        return null;
    }

    public void setContext(IModelContext context)
    {
        // ignore since this resolver is just delegating to a dom context.
    }
}
