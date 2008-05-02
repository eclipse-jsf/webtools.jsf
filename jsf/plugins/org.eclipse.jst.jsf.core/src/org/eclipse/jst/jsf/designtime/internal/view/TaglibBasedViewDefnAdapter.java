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
package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.w3c.dom.Element;

/**
 * A view defintion adapter for XML-based view definition formats that use 
 * a well defined tag library system, with associated ITagRegistry, to define
 * tag handlers.  Most common view handlers (JSP, Facelets) should use this.
 * 
 * @author cbateman
 *
 */
public abstract class TaglibBasedViewDefnAdapter extends XMLViewDefnAdapter
{
    private final ITagRegistry        _tagRegistry;
    
    /**
     * @param tagRegistry
     */
    protected TaglibBasedViewDefnAdapter(final ITagRegistry tagRegistry)
    {
        _tagRegistry = tagRegistry;
    }
    
    /**
     * @param node
     * @param document
     * @return the tag element for node or null if none.
     */
    @Override
    protected ITagElement findTagElement(final Element node,
            final IDocument document)
    {
        final String uri = getNamespace(node, document);
        final String tagName = node.getLocalName();

        if (uri != null)
        {
            final Namespace lib = getTagRegistry().getTagLibrary(uri);

            if (lib != null)
            {
                return findTagElement(tagName, lib);
            }
        }
        return null;
    }

    /**
     * @return the tag registry
     */
    protected final ITagRegistry getTagRegistry()
    {
        return _tagRegistry;
    }
}
