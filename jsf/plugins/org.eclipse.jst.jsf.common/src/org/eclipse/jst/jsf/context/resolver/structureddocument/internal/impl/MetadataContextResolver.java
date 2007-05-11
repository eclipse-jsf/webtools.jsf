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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A default implementation of meta-data context resolver
 * 
 * @author cbateman
 *
 */
class MetadataContextResolver implements IMetadataContextResolver 
{
    private final IStructuredDocumentContext        _context;
    
    /**
     * @param context
     */
    public MetadataContextResolver(IStructuredDocumentContext context)
    {
        _context = context;
    }
    
    public List getPropertyValue(final String key) 
    {
        final DOMContextResolver domResolver = new DOMContextResolver(_context);
        final WorkspaceContextResolver wsResolver = new WorkspaceContextResolver(_context);
        final TaglibContextResolver  tagResolver =
            new TaglibContextResolver(_context);
        
        final Node curNode = domResolver.getNode();
        
        if (curNode instanceof Attr)
        {
            final Attr attribute = (Attr) curNode;
            final Element  element = attribute.getOwnerElement();
            final String uri = tagResolver.getTagURIForNodeName(element);
            final IProject project = wsResolver.getProject();
            
            final IMetaDataModelContext mdContext = MetaDataQueryHelper.createTagLibraryDomainMetaDataModelContext(project, uri);
            Trait trait = MetaDataQueryHelper.getTrait(mdContext, element.getLocalName()+"/"+attribute.getLocalName(), key); //$NON-NLS-1$
            return TraitValueHelper.getValueAsListOfStrings(trait);
//            return
//                CMAnnotationHelper.
//                    getCMAttributeProperties(uri, 
//                                             element.getLocalName(), 
//                                             attribute.getLocalName(),
//                                             key);
            
        }
        else if (curNode instanceof Element)
        {
            final Element  element = (Element) curNode;
            final String uri = tagResolver.getTagURIForNodeName(element);
            final IProject project = wsResolver.getProject();
            
            final IMetaDataModelContext mdContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, uri);
            Trait trait = MetaDataQueryHelper.getTrait(mdContext, element.getLocalName(), key);
            return TraitValueHelper.getValueAsListOfStrings(trait);
//            return
//                CMAnnotationHelper.
//                    getCMElementProperties(uri, 
//                             element.getLocalName(), 
//                             key);
        }
        
        return Collections.EMPTY_LIST;
    }

    public boolean canResolveContext(IModelContext modelContext) {
        return (modelContext.getAdapter(IStructuredDocumentContext.class) != null);
    }

}
