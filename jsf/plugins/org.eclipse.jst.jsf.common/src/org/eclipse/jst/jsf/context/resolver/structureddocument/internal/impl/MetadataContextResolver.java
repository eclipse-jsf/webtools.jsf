/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
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
    private final IStructuredDocumentContextResolverFactory2 _factory;
    
    /**
     * @param factory 
     * @param context
     */
    public MetadataContextResolver(final IStructuredDocumentContextResolverFactory2 factory,
            final IStructuredDocumentContext context)
    {
        _factory = factory;
        _context = context;
    }
    
    public List getPropertyValue(final String key) 
    {
        final DOMContextResolver domResolver = new DOMContextResolver(_context);
        final ITaglibContextResolver  tagResolver =
            _factory.getTaglibContextResolverFromDelegates(_context);
        final Node curNode = domResolver.getNode();
        final IMetaDataDomainContext mdContext = getMDContext(_context);
        if (curNode instanceof Attr)
        {
            final Attr attribute = (Attr) curNode;
            final Element  element = attribute.getOwnerElement();
            final String uri = tagResolver.getTagURIForNodeName(element);
            			
    		final ITaglibDomainMetaDataQuery query = (ITaglibDomainMetaDataQuery)MetaDataQueryFactory.getInstance().createQuery(mdContext);
            final Trait trait = query.getQueryHelper().getTrait(uri, element.getLocalName()+"/"+attribute.getLocalName(),  key); //$NON-NLS-1$
//            final ITaglibDomainMetaDataModelContext mdContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
//            Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(mdContext, element.getLocalName()+"/"+attribute.getLocalName(), key); //$NON-NLS-1$
            if( trait != null )
            {
            	return TraitValueHelper.getValueAsListOfStrings(trait);
            }
            
        }
        else if (curNode instanceof Element)
        {
            final Element  element = (Element) curNode;
            final String uri = tagResolver.getTagURIForNodeName(element);
            
    		final ITaglibDomainMetaDataQuery query = (ITaglibDomainMetaDataQuery)MetaDataQueryFactory.getInstance().createQuery(mdContext);
    		final Trait trait = query.getQueryHelper().getTrait(uri, element.getLocalName(),  key);
//            final ITaglibDomainMetaDataModelContext mdContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
//            Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(mdContext, element.getLocalName(), key);
            if( trait != null )
            {
            	return TraitValueHelper.getValueAsListOfStrings(trait);
            }
        }
        
        return Collections.EMPTY_LIST;
    }

    private IMetaDataDomainContext getMDContext(
			final IStructuredDocumentContext context) {

        final WorkspaceContextResolver wsResolver = new WorkspaceContextResolver(context);
        final IResource res = wsResolver.getResource();
        if (res instanceof IFile)
        	return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext((IFile)res);

        return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(wsResolver.getProject());

	}

	public boolean canResolveContext(IModelContext modelContext) {
        return (modelContext.getAdapter(IStructuredDocumentContext.class) != null);
    }

}
