/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * @author cbateman
 *
 */
public abstract class AbstractElementEditFactory implements IElementEditFactory 
{
    private String _supportedUri;
    
    /**
     * @param uri
     */
    protected AbstractElementEditFactory(final String uri)
    {
        _supportedUri = uri;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.eclipse.jst.jsf.common.dom.TagIdentifier)
     */
    public abstract IElementEdit createElementEdit(TagIdentifier tag); 

    public String getSupportedURI() 
    {
        return _supportedUri;
    }

}
