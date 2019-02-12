/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfhtml;

import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class SelectOneMenuTagConverter extends SelectBasedTagConverter
{

    /**
     * @param host
     */
    public SelectOneMenuTagConverter(Element host)
    {
        super(host);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.jsfhtml.SelectBasedTagConverter#handleMultipleAndSize(org.w3c.dom.Element, org.w3c.dom.Element)
     */
    protected void handleMultipleAndSize(Element hostEle, Element selectEle)
    {
        // always not multiple
        selectEle.removeAttribute("multiple"); //$NON-NLS-1$

        selectEle.setAttribute("size", "1"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
