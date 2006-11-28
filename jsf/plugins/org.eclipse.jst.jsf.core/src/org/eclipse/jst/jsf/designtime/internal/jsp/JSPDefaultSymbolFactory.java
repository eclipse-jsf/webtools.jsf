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

package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author cbateman
 *
 */
public class JSPDefaultSymbolFactory extends AbstractContextSymbolFactory 
{
    protected ISymbol internalCreate(String symbolName,int scope,IAdaptable context,List problems) 
    {
        final IStructuredDocumentContext sContext =
            (IStructuredDocumentContext) 
                context.getAdapter(IStructuredDocumentContext.class);
        
        final IDOMContextResolver domResolver = 
            IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(sContext);
        
        if (domResolver != null)
        {
            final Node curNode = domResolver.getNode();
            
            if (curNode instanceof Attr)
            {
                final Attr attr = (Attr) curNode;
                final Node owningElement = attr.getOwnerElement();
                
                if (owningElement != null)
                {
                    return handleSymbolCreation(symbolName, sContext, attr, owningElement, problems);
                }
            }
        }
        
        return null;
    }

    
    private ISymbol handleSymbolCreation(final String symbolName, 
                                         final IStructuredDocumentContext context,
                                         final Attr attr, 
                                         final Node owningElement,
                                         final List problems)
    {
        final ITaglibContextResolver resolver = 
            IStructuredDocumentContextResolverFactory.INSTANCE.getTaglibContextResolver(context);
        
        
        if (resolver == null
                || !resolver.canResolveContext(context))
        {
            return null;
        }
        
        final String uri = resolver.getTagURIForNodeName(owningElement);
        
        // process core taglib
        if ("http://java.sun.com/jsf/core".equals(uri))
        {
            final String elementName = owningElement.getLocalName();
            final String attrName = attr.getName();
            
            if ("loadBundle".equals(elementName))
            {
                if ("var".equals(attrName))
                {
                    final NamedNodeMap attrMap = owningElement.getAttributes();
                    final Node baseNameNode = attrMap.getNamedItem("basename");

                    
                    if (baseNameNode != null)
                    {
                        try
                        {
                            final IWorkspaceContextResolver wkspaceResolver =
                                IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);
                            IProject project = wkspaceResolver.getProject();
                            
                            if (project == null)
                            {
                                throw new RuntimeException("Error acquiring project");
                            }
                            
                            final Map source = 
                                new ResourceBundleMapSource(project, baseNameNode.getNodeValue());
                            final IMapTypeDescriptor typeDesc = 
                                SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
                            typeDesc.setMapSource(source);
                            final IComponentSymbol symbol = 
                                SymbolFactory.eINSTANCE.createIComponentSymbol();
                            symbol.setName(symbolName);
                            symbol.setTypeDescriptor(typeDesc);
                            symbol.setDetailedDescription("Resource bundle map for bundle <i>"+baseNameNode.getNodeValue()+"</i>");
                            
                            return symbol;
                        }
                        catch (Exception e)
                        {
                            problems.add(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0,"Error creating loadBundle variable", e));
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.source.AbstractContextSymbolFactory#supports(org.eclipse.core.runtime.IAdaptable)
     */
    public boolean supports(IAdaptable context) 
    {
        return context.getAdapter(IStructuredDocumentContext.class) != null;
    }
}
