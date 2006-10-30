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
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.ConverterUtil;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * @author mengbo
 * @version 1.5
 */
public class OutputLinkTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public OutputLinkTagConverter(Element host)
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

        Element aEle = createElement(IHTMLConstants.TAG_A);

        // If the "style" attribute is present, pass it thru.
        // XXX: we are passing all the attributes through, since other attribute
        // don't conflict with html attributes.
        JSFConverterUtil.copyAllAttributes(hostEle, aEle, null);

        // If the "styleClass" attribute is present,
        // render its value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, aEle, IHTMLConstants.ATTR_CLASS);
        aEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // value should be mapped to href, but it won't affect visual
        // so use "#"
        aEle.setAttribute(IHTMLConstants.ATTR_HREF, "#");

        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_VALUE, aEle, IHTMLConstants.ATTR_HREF);
        aEle.removeAttribute(IJSFConstants.ATTR_VALUE);

        // Extend the container border a little to make it selectable
        if (!isPreviewMode())
        {
            String style = aEle.getAttribute(IHTMLConstants.ATTR_STYLE);
            style = "padding:0.3em;" + style;
            aEle.setAttribute(IHTMLConstants.ATTR_STYLE, style);
        }

        int index = 0;
        Node child = hostEle.getFirstChild();
        if (hostEle.hasChildNodes())
        {
            for (; child != null; child = child.getNextSibling())
            {
                if (!shouldIgnore(child))
                {
                    if (child instanceof Element && JSFDOMUtil.isUIParameter((Element) child))
                    {
                        // skip
                    }
                    else
                    {
                        addChild(child, new ConvertPosition(aEle, index++));
                    }
                }
            }
        }
        if (ConverterUtil.isEmptyContainer(hostEle) && !hostEle.hasAttribute(IJSFConstants.ATTR_VALUE))
        {
            aEle.appendChild(createText(getDefaultValue()));
        }
        return aEle;
    }

    private String getDefaultValue()
    {
        String name = IJSFConstants.TAG_OUTPUTLINK;

        return name.substring(0, 1).toUpperCase() + name.substring(1);
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
