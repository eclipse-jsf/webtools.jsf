/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.facelet.core.internal.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory.TagRegistryIdentifier;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter.DTELExpression;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;
import org.eclipse.jst.jsf.facelet.core.internal.registry.FaceletRegistryManager.MyRegistryFactory;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Facelet view utilities
 * 
 * @author cbateman
 */
public final class ViewUtil
{
    private static final String HTMLSOURCE_CONTENT_TYPE_ID = "org.eclipse.wst.html.core.htmlsource"; //$NON-NLS-1$
    private static final String XMLNS = "xmlns"; //$NON-NLS-1$

    /**
     * Warning! This call can be very expensive.  Cache results whenever
     * possible.
     * 
     * @param project
     * @param uri
     * @return the tld document for uri in project or null if not found
     */
    public static TLDDocument findTLDDocument(final IProject project,
            final String uri)
    {
        final ITaglibRecord[] tldrecs = TaglibIndex
                .getAvailableTaglibRecords(project.getFullPath());

        for (final ITaglibRecord rec : tldrecs)
        {
            final String matchUri = rec.getDescriptor().getURI();
            if (uri.equals(matchUri))
            {
                final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
                return (TLDDocument) factory.createCMDocument(rec);
            }
        }
        return null;
    }

    /**
     * @param attributes
     * @return the set of uri's that declared in attributes
     */
    public static Set<Attr> getDeclaredNamespaces(final NamedNodeMap attributes)
    {
        final Set<Attr> alreadyUsed = new HashSet<Attr>();
        for (int i = 0; i < attributes.getLength(); i++)
        {
            final Node node = attributes.item(i);
            if (XMLNS.equals(node.getNodeName())
                    || XMLNS.equals(node.getPrefix()))
            {
                final String attrValue = node.getNodeValue();

                if (attrValue != null && !"".equals(attrValue.trim()) //$NON-NLS-1$
                        && node instanceof Attr)
                {
                    alreadyUsed.add((Attr) node);
                }
            }
        }

        return alreadyUsed;
    }

    /**
     * @param attrSet
     * @param value
     * @return true if attrSet contains an attribute whose value is <i>value</i>
     */
    public static boolean hasAttributeValue(final Set<Attr> attrSet,
            final String value)
    {
        for (final Attr attr : attrSet)
        {
            if (value.equals(attr.getValue()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param project
     * @return the html source type tag registry for project
     */
    public static ITagRegistry getHtmlTagRegistry(final IProject project)
    {
        final IContentType contentType = Platform.getContentTypeManager()
                .getContentType(HTMLSOURCE_CONTENT_TYPE_ID);
        final TagRegistryIdentifier id = new TagRegistryIdentifier(project,
                contentType);
        final ITagRegistry tagRegistry = CompositeTagRegistryFactory
                .getInstance().getRegistry(id);
        return tagRegistry;
    }

    /**
     * @param doc
     * @return all of the prefixed namespaces defined in doc
     */
    public static Map<String, PrefixEntry> getDocumentNamespaces(
            final Document doc)
    {
        final Map<String, PrefixEntry> namespaces = new HashMap<String, PrefixEntry>();

        final Element rootElement = doc.getDocumentElement();

        if (rootElement != null)
        {
            final NamedNodeMap attrs = rootElement.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++)
            {
                final Attr a = (Attr) attrs.item(i);
                final PrefixEntry ns = PrefixEntry.parseNamespace(a);
                if (ns != null)
                {
                    namespaces.put(ns._prefix, ns);
                }
            }
        }
        return namespaces;
    }

    /**
     * @param file
     * @return true if the file is a Facelet VDL file
     */
    public static boolean isFaceletVDLFile(final IFile file)
    {
        final DesignTimeApplicationManager instance = DesignTimeApplicationManager.getInstance(file.getProject());
        if (instance != null)
        {
            final IDTViewHandler viewHandler = instance.getViewHandler();
            if (viewHandler != null)
            {
                return viewHandler.supportsViewDefinition(file);
            }
        }
        return false;
    }

    /**
     * @param project
     * @return the facelet tag registry for the project or null if none.
     */
    public static ITagRegistry getTagRegistry(final IProject project)
    {
        final MyRegistryFactory factory = new MyRegistryFactory();

        ITagRegistry registry = null;
        try
        {
            registry = factory.createTagRegistry(project);
        }
        catch (final TagRegistryFactoryException e)
        {
            // fall-through
        }
        return registry;
    }
    /**
     * Encapsulates a single namespace/prefix use declaration in an XML document
     * @author cbateman
     *
     */
    public static class PrefixEntry
    {
        private final String _uri;
        private final String _prefix;

        /**
         * @param attr
         * @return the prefix entry for attr or null
         */
        public static PrefixEntry parseNamespace(final Attr attr)
        {
            final String prefix = attr.getPrefix();

            if (XMLNS.equals(prefix))
            {
                final String prefixName = attr.getLocalName();
                if (prefixName != null)
                {
                    final String uri = attr.getNodeValue();

                    if (uri != null)
                    {
                        return new PrefixEntry(uri, prefixName);
                    }
                }
            }

            return null;
        }

        /**
         * @param uri
         * @param prefix
         */
        public PrefixEntry(final String uri, final String prefix)
        {
            _uri = uri;
            _prefix = prefix;
        }

        /**
         * @return the namespace uri
         */
        public final String getUri()
        {
            return _uri;
        }

        /**
         * @return the namespace prefix
         */
        public final String getPrefix()
        {
            return _prefix;
        }

        @Override
        public int hashCode()
        {
            return _uri.hashCode();
        }

        @Override
        public boolean equals(final Object obj)
        {
            return _uri.equals(obj);
        }
    }
    /**
     * @param genericContext
     * @return the el expression from the context or null if none found.
     */
    public static DTELExpression getDTELExpression(IModelContext genericContext) {
        final IStructuredDocumentContext context = (IStructuredDocumentContext) genericContext
                .getAdapter(IStructuredDocumentContext.class);

//        if (context == null)
//        {
//            throw new ViewHandlerException(Cause.EL_NOT_FOUND);
//        }

        final ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE
            .getTextRegionResolver(context);

        if (resolver != null)
        {
            final String regionType = resolver.getRegionType();
            int startOffset = resolver.getStartOffset();
            int relativeOffset = context.getDocumentPosition() - startOffset;
            
            if (DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE.equals(regionType))
            {
                final String attributeText = resolver.getRegionText();
                int elOpenIdx = attributeText.indexOf("#"); //$NON-NLS-1$
                
                if (elOpenIdx >= 0 && elOpenIdx < relativeOffset
                        && elOpenIdx+1 < attributeText.length()
                        && attributeText.charAt(elOpenIdx+1) == '{')
                {
                    // we may have a hit
                    int elCloseIdx = attributeText.indexOf('}', elOpenIdx+1);
                    if (elCloseIdx  != -1)
                    {
                        final IStructuredDocumentContext elContext =
                            IStructuredDocumentContextFactory.INSTANCE.getContext(
                                    context.getStructuredDocument(), resolver
                                    .getStartOffset()+elOpenIdx+2);
                        final String elText = attributeText.substring(
                                elOpenIdx + 2, elCloseIdx);
                        return new DTELExpression(elContext, elText);
                    }
                }
            }
        }

        return null;
    }

}
