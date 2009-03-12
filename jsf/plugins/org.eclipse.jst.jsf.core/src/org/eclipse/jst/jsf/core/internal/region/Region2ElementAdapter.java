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
package org.eclipse.jst.jsf.core.internal.region;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.jsf.common.dom.ElementDOMAdapter;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Adapt a region object for DOM element information. Does not implement entire
 * DOM interface. Object is lazily initialized and not thread-safe.
 * 
 * @author cbateman
 * 
 */
public class Region2ElementAdapter extends ElementDOMAdapter
{
    private final RegionProcessorStrategy _regionParser;

    /**
     * Create a new adapter for this region. If the region does not contain
     * enough information to construct a meaningful adapter, it will throw
     * NoElementException. The adapter is lazily initialized and won't process
     * the region until the first call to a get method is made.
     * 
     * @param region
     * @throws NoElementException
     */
    public Region2ElementAdapter(final ITextRegion region)
            throws NoElementException
    {
        _regionParser = new RegionProcessorStrategy(region);
    }

    @Override
    public Map<String, Region2AttrAdapter> getAttributes()
    {
        return _regionParser.getAttributes();
    }

    @Override
    public String getNamespace()
    {
        return _regionParser.getNamespace();
    }

    @Override
    public String getLocalName()
    {
        return _regionParser.getLocalName();
    }

    @Override
    public String getNodeName()
    {
        return _regionParser.getNodeName();
    }

    @Override
    public String getPrefix()
    {
        return _regionParser.getPrefix();
    }

    /**
     * @return the structured document context
     */
    public IStructuredDocumentContext getDocumentContext()
    {
        return _regionParser._context;
    }

    /**
     * @return an ITextRegion containing absolute offset information for this element
     */
    public ITextRegionCollection getTextRegion()
    {
        return _regionParser._region;
    }

    /**
     * @return the element
     */
    public Element getElement()
    {
        return (Element) _regionParser._node;
    }

    private class RegionProcessorStrategy
    {
        private final ITextRegionCollection _region;

        private Map<String, Region2AttrAdapter> _attributes;
        private Node                        _node;
        private IStructuredDocumentContext  _context;

        RegionProcessorStrategy(final ITextRegion region) throws NoElementException
        {
            if (!(region instanceof ITextRegionCollection)
                    || ((ITextRegionCollection) region).getFirstRegion()
                            .getType() != DOMRegionContext.XML_TAG_OPEN)
            {
                throw new NoElementException(
                        "Region is not a collection with an open tag"); //$NON-NLS-1$
            }

            _region = (ITextRegionCollection) region;

            _context =
                       IStructuredDocumentContextFactory.INSTANCE.getContext(
                               ((IStructuredDocumentRegion) _region)
                                       .getParentDocument(), _region
                                       .getStartOffset());

            if (_context == null)
            {
                throw new NoElementException(
                        "Couldn't acquire structured document context"); //$NON-NLS-1$
            }

            final IDOMContextResolver resolver =
                                                 IStructuredDocumentContextResolverFactory.INSTANCE
                                                         .getDOMContextResolver(_context);

            if (resolver == null)
            {
                throw new NoElementException("Couldn't acquire dom resolver"); //$NON-NLS-1$
            }

            _node = resolver.getNode();

            if (_node == null)
            {
                throw new NoElementException("Couldn't get Node from region"); //$NON-NLS-1$
            }
        }

        public String getLocalName()
        {
            return _node.getLocalName();
        }

        public String getNodeName()
        {
            return _node.getNodeName();
        }

        public String getPrefix()
        {
            return _node.getPrefix();
        }

        public String getNamespace()
        {
            final ITaglibContextResolver tagLibResolver =
                              IStructuredDocumentContextResolverFactory2.INSTANCE
                                      .getTaglibContextResolverFromDelegates(_context);

            if (tagLibResolver != null)
            {
                return tagLibResolver.getTagURIForNodeName(_node);
            }

            return null;
        }

        public Map<String, Region2AttrAdapter>  getAttributes()
        {
            mapAttributesOnlyOnce();
            return Collections.unmodifiableMap(_attributes);
        }

        private void mapAttributesOnlyOnce()
        {
            // only once
            if (_attributes != null)
                return;

            _attributes = Collections.EMPTY_MAP;

            final NamedNodeMap attributes = _node.getAttributes();

            if (attributes != null)
            {
                final int numAttrs = attributes.getLength();
                _attributes = new HashMap<String, Region2AttrAdapter>(
                        (int) (numAttrs / 0.75f) + 1, 0.75f);
                for (int i = 0; i < numAttrs; i++)
                {
                    final Node nodeAttr = attributes.item(i);
                    Region2AttrAdapter attr = new Region2AttrAdapter(
                            Region2ElementAdapter.this, nodeAttr);
                    _attributes.put(attr.getLocalName(), attr);
                }
            }
        }
    }
    
    /**
     * Indicates that construction of this adapter failed because an IRegion
     * didn't have sufficient information (or didn't represent an element at
     * all)
     * 
     * @author cbateman
     * 
     */
    public static class NoElementException extends Exception
    {
        /**
         * serializable id
         */
        private static final long serialVersionUID = -4479154049727036580L;

        private NoElementException(final String reason)
        {
            super(reason);
        }
    }
}
