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
package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.w3c.dom.Element;

/**
 * Factory creating tag identifiers
 * 
 * @author cbateman
 *
 */
public final class TagIdentifierFactory 
{
    /**
     * Create a tag identifier based on a uri and tagName
     * 
     * @param uri
     * @param tagName
     * @return a new tag identifier for (uri, tagName)
     */
    public static TagIdentifier createJSPTagWrapper(final String uri, final String tagName)
    {
        return new JSPTagIdentifier(uri, tagName);
    }
    
    /**
     * @param element
     * @return a tag identifier based on a DOM element
     */
    public static TagIdentifier createDocumentTagWrapper(final Element element)
    {
        return new DocumentTagIdentifier(element);
    }
    
    /**
     * @param elementDecl
     * @return a tag identifier that bridges a TLDElementDeclaration
     */
    public static TagIdentifier createTLDTagWrapper(final TLDElementDeclaration elementDecl)
    {
        if (!(elementDecl.getOwnerDocument() instanceof TLDDocument))
        {
            throw new IllegalArgumentException("Element decl must have a tlddoc for a parent: "+elementDecl.toString()); //$NON-NLS-1$
        }
        return new TLDTagIdentifier(elementDecl);
    }
    
    private TagIdentifierFactory()
    {
        // static class, no external instantiation
    }
}

