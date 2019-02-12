/*******************************************************************************
 * Copyright (c) 2006, 2013 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfhtml;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFHTMLJCPElementEditFactory implements IElementEditFactory
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.w3c.dom.Element)
     */
    public IElementEdit createElementEdit(final TagIdentifier tagIdentifier)
    {
        if (IJSFConstants.TAG_IDENTIFIER_DATA_TABLE_JCP.isSameTagType(tagIdentifier))
        {
            return new DataTableElementEdit();
        }
        else if (IJSFConstants.TAG_IDENTIFIER_PANEL_GRID_JCP.isSameTagType(tagIdentifier))
        {
            return new PanelGridElementEdit();
        }
        else if (IJSFConstants.TAG_IDENTIFIER_COLUMN_JCP.isSameTagType(tagIdentifier))
        {
            return new ColumnElementEdit();
        }
        return new DefaultJSFHTMLElementEdit();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#getSupportedURI()
     */
    public String getSupportedURI()
    {
        return ITLDConstants.URI_JSF_HTML_JCP;
    }

}
