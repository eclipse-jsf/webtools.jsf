/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.w3c.dom.Element;

/**
 * A tag wrapper for a DOM element.  This element may or may not be a JSP tag,
 * but must be contained in a DOM tree.
 * 
 * @author cbateman
 *
 */
/*package*/ class DocumentTagIdentifier extends TagIdentifier 
{
    private final Element  _element;
    
    /**
     * @param element
     */
    public DocumentTagIdentifier(Element element)
    {
        _element = element;
    }
    
    public String getTagName() 
    {
        return _element.getLocalName();
    }

    public String getUri() {
        String uri = CMUtil.getElementNamespaceURI(_element);
        
        // give the content model priority
        if (uri == null)
        {
            uri = _element.getNamespaceURI();
        }
        
        return uri;
    }

    public boolean isJSPTag() {
        CMElementDeclaration elemDecl = getElementDeclaration();
        
        if (elemDecl != null)
        {
            return CMUtil.isJSP(elemDecl);
        }
        
        return false;
    }

    /**
     * @return the element declaration for this tag
     */
    protected final CMElementDeclaration getElementDeclaration()
    {
        return CMUtil.getElementDeclaration(_element);
    }
}
