/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.PageDesignerTraceOptions;
import org.eclipse.jst.pagedesigner.elementedit.html.HTMLElementEditFactory;
import org.eclipse.jst.pagedesigner.elementedit.jsp.JSPElementEditFactory;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ElementEditFactoryRegistry
{
    List _factories = new ArrayList();

    private static ElementEditFactoryRegistry _instance;

    /**
	 *
	 */
    private ElementEditFactoryRegistry()
    {
        final List<IElementEditFactory> facs = ElementEditFacRegistryReader
                .getAllHandlers();

        if (facs != null)
        {
            for (final IElementEditFactory fac : facs)
            {
                addFactory(fac);
            }
        }
        addFactory(new HTMLElementEditFactory());
        addFactory(new JSPElementEditFactory());
    }

    /**
     * Add a factory to list available.
     *
     * @param fac
     */
    public void addFactory(final IElementEditFactory fac)
    {
        _factories.add(fac);
    }

    /**
     * @param tagIdentifier
     * @return an IElementEdit constructed for the tag uniquely identified by
     *         the ns uri (tag uri for JSP tags) and tagName (element name) or
     *         null if the system can't create one.
     */
    public IElementEdit createElementEdit(final TagIdentifier tagIdentifier)
    {
        final String uri = tagIdentifier.getUri();

        // first round, match uri
        for (int i = 0, size = _factories.size(); i < size; i++)
        {
            final IElementEditFactory fac = (IElementEditFactory) _factories.get(i);
            final String facuri = fac.getSupportedURI();
            if (facuri != null && facuri.equalsIgnoreCase(uri))
            {
                final IElementEdit elementEdit = fac.createElementEdit(tagIdentifier);
                if (elementEdit != null)
                {
                    if (PageDesignerTraceOptions.TRACE_ELEMENTEDITSELECTION)
                    {
                        PageDesignerTraceOptions.log("ElementEditFactoryRegistry: first loop, "+ //$NON-NLS-1$
                                String.format("Selected %s for uri=%s, tagname=%s", elementEdit.getClass().getName(),uri, tagIdentifier.getTagName())); //$NON-NLS-1$
                    }
                    return elementEdit;
                }
            }
        }
        // second round
        for (int i = 0, size = _factories.size(); i < size; i++)
        {
            final IElementEditFactory fac = (IElementEditFactory) _factories.get(i);
            final String facuri = fac.getSupportedURI();
            if (facuri == null)
            {
                final IElementEdit elementEdit = fac.createElementEdit(tagIdentifier);
                if (elementEdit != null)
                {
                    if (PageDesignerTraceOptions.TRACE_ELEMENTEDITSELECTION)
                    {
                        PageDesignerTraceOptions.log("ElementEditFactoryRegistry: second loop, "+ //$NON-NLS-1$
                                String.format("Selected %s for uri=%s, tagname=%s", elementEdit.getClass().getName(),uri, tagIdentifier.getTagName())); //$NON-NLS-1$
                    }
                    return elementEdit;
                }
            }
        }

        if (PageDesignerTraceOptions.TRACE_ELEMENTEDITSELECTION)
        {
            PageDesignerTraceOptions.log("ElementEditFactoryRegistry: no element edit found, returning null for "+ //$NON-NLS-1$
                    String.format("uri=%s, name=%s", tagIdentifier.getUri(), tagIdentifier.getTagName())); //$NON-NLS-1$
        }

        return null;
    }

    /**
     * Convenience method for createElementEdit(uri, tagName) that takes a tag
     * element.
     *
     * @param ele
     * @return an element edit
     */
    public IElementEdit createElementEdit(final Element ele)
    {
        final TagIdentifier tagIdentifier = TagIdentifierFactory
                .createDocumentTagWrapper(ele);
        return createElementEdit(tagIdentifier);
    }

    /**
     * @return the single instance of the factory registry
     */
    public synchronized static ElementEditFactoryRegistry getInstance()
    {
        if (_instance == null)
        {
            _instance = new ElementEditFactoryRegistry();
        }
        return _instance;
    }
}
