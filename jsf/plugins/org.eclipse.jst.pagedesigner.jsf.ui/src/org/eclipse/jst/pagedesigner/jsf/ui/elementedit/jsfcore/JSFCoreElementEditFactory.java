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

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFCoreElementEditFactory implements IElementEditFactory
{

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
     */
    public IElementEdit createElementEdit(Element element)
    {
        String tagname = element.getLocalName();
        if (IJSFConstants.TAG_LOADBUNDLE.equalsIgnoreCase(tagname))
        {
            return new LoadBundleElementEdit();
        }
        else if(IJSFConstants.TAG_FACET.equalsIgnoreCase(tagname))
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
        return IJMTConstants.URI_JSF_CORE;
    }
}
