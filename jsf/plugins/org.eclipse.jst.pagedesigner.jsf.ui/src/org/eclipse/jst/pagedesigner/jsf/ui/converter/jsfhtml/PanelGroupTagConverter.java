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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfhtml;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGroupTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public PanelGroupTagConverter(Element host)
    {
        super(host);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        // If the "styleClass" or "style" attributes are present,
        // render a "span" element.
        // XXX: for page designer to have the panelGroup to always take up space,
        // we use "div".
        Element spanEle = createElement(IHTMLConstants.TAG_DIV);

        // If the "style" attribute is present, pass it thru.
        // XXX: we are passing all the attributes through, since other attribute
        // don't conflict with html attributes.
        JSFConverterUtil.copyAllAttributes(hostEle, spanEle, null);

        // If the "styleClass" attribute is present,
        // render its value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, spanEle, IHTMLConstants.ATTR_CLASS);
        spanEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        copyChildren(hostEle, spanEle);
        return spanEle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isWidget()
     */
    public boolean isWidget()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
