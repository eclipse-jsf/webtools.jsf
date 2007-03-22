/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfcore;

import org.eclipse.jst.jsf.common.internal.provisional.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFCoreElementEditFactory implements IElementEditFactory
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
     */
    public IElementEdit createElementEdit(TagIdentifier tagIdentifier)
    {
        if (IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE.isSameTagType(tagIdentifier))
        {
            return new LoadBundleElementEdit();
        }
        else if(IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(tagIdentifier))
        {
            return new FacetElementEdit();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
     */
    public String getSupportedURI()
    {
        return ITLDConstants.URI_JSF_CORE;
    }
}
