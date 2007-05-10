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

/**
 * A tag wrapper for a JSP tag.
 * 
 * @author cbateman
 *
 */
/*package*/ class JSPTagIdentifier extends TagIdentifier 
{
    private final String _uri;
    private final String _tagName;
    
    /**
     * @param uri
     * @param tagName
     */
    public JSPTagIdentifier(final String uri, final String tagName)
    {
        _uri = uri;
        _tagName = tagName;
    }
    public String getTagName() {
        return _tagName;
    }

    public String getUri() {
        return _uri;
    }

    public boolean isJSPTag() {
        return true;
    }
}
