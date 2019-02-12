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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfcore;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.ConverterUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class ViewTagConverter extends AbstractTagConverter
{
    /**
     * @param host
     */
    public ViewTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element result = createElement(IHTMLConstants.TAG_DIV);
        copyChildren(getHostElement(), result);
//        if(!isPreviewMode())
//        {
//            result.setAttribute(IHTMLConstants.ATTR_STYLE,"margin:10px;");
//        }
        if(!isPreviewMode() && ConverterUtil.isEmptyContainer(getHostElement()))
        {
            result.appendChild(ConverterUtil.createDescriptionElement(getDestDocument(),null));
        }
        return result;
    }
    	

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
     */
    public boolean isWidget()
    {
        return false;
    }
}
