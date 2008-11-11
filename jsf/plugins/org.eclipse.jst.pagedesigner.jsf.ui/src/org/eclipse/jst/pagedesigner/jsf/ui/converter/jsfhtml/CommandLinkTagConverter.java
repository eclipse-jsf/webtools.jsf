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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
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
 * @deprecated Use DTTagConverter meta-data instead
 */
public class CommandLinkTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public CommandLinkTagConverter(Element host)
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
        // Render an HTML "a" anchor element that acts like a form submit button when clicked.
        Element aEle = createElement(IHTMLConstants.TAG_A);

        JSFConverterUtil.copyAllAttributes(hostEle, aEle, null);

        // If the "styleClass" attribute is specified, render its value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, aEle, IHTMLConstants.ATTR_CLASS);

        aEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // Render "#" as the value of the "href" attribute.
        aEle.setAttribute(IHTMLConstants.ATTR_HREF, "#");
        // Extend the container border a little to make it selectable
        if (!isPreviewMode())
        {
            String style = aEle.getAttribute(IHTMLConstants.ATTR_STYLE);
            style = "padding:0.3em;" + style;
            aEle.setAttribute(IHTMLConstants.ATTR_STYLE, style);
        }

        int childNodeIndex = 0;
        // Render the current value of the component as the link text if it
        // is specified.
        String value = hostEle.getAttribute(IJSFConstants.ATTR_VALUE);
        if (value != null && value.length() > 0)
        {
            value = mapValue(value);
            aEle.appendChild(createText(value));
            childNodeIndex = 1;
        }
        // generate children
        // Render any non-UIParameter children as normal inside of the "a" element.
        // These will appear as the link text.

        if (hostEle.hasChildNodes())
        {
            Node child = hostEle.getFirstChild();
            for (; child != null; child = child.getNextSibling())
            {
                if (!shouldIgnore(child))
                {
                    if (child instanceof Element
                            && (JSFDOMUtil.isUIParameter((Element) child) || isActionListener((Element) child)))
                    {
                        // skip
                    }
                    else
                    {
                        addChild(child, new ConvertPosition(aEle, childNodeIndex++));
                    }
                }
            }
        }
        if (ConverterUtil.isEmptyContainer(hostEle) && !hostEle.hasAttribute(IJSFConstants.ATTR_VALUE))
        {
            aEle.appendChild(getDestDocument().createTextNode(IJSFConstants.TAG_COMMANDLINK));
        }

        return aEle;
    }
    
    private boolean isActionListener(Element element)
    {
        if(element != null && IJSFConstants.TAG_ACTIONLISTENER.equalsIgnoreCase(element.getLocalName()))
        {
            return true;
        }
        return false;
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
