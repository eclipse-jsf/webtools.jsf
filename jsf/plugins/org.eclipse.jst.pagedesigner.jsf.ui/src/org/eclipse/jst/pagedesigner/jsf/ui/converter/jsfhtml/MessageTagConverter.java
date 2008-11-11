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
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.eclipse.jst.pagedesigner.jsf.ui.util.JSFUIPluginResourcesUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class MessageTagConverter extends AbstractTagConverter
{
    private static final String MESSAGE_CONTENT = JSFUIPluginResourcesUtil.getInstance().getString(
                                                        "MessageTagConverter.defaultValue");
    private static final String MESSAGE_TOOLTIP = JSFUIPluginResourcesUtil.getInstance().getString(
                                                        "MessageTagConverter.defaultTooltip");

    /**
     * @param host
     */
    public MessageTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        // If the "styleClass" or "style" attributes are present, 
        // render a "span" element. 
        // XXX: to make things simpler, we always create a span
        Element spanEle = createElement(IHTMLConstants.TAG_SPAN);

        // If the "style" attribute is present, pass it thru.
        // XXX: we are passing all the attributes through, since other attribute
        // don't conflict with html attributes.
        JSFConverterUtil.copyAllAttributes(hostEle, spanEle, null);

        // If the "styleClass" attribute is present, 
        // output the value of the "styleClass" attribute as the value of the "class" attribute on the "span" element
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, spanEle, IHTMLConstants.ATTR_CLASS);
        spanEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        String fatalStyle = hostEle.getAttribute(IJSFConstants.ATTR_FATALSTYLE);
        String errorStyle = hostEle.getAttribute(IJSFConstants.ATTR_ERRORSTYLE);
        String warnStyle = hostEle.getAttribute(IJSFConstants.ATTR_WARNSTYLE);
        String infoStyle = hostEle.getAttribute(IJSFConstants.ATTR_INFOSTYLE);
        String fatalClass = hostEle.getAttribute(IJSFConstants.ATTR_FATALCLASS);
        String errorClass = hostEle.getAttribute(IJSFConstants.ATTR_ERRORCLASS);
        String warnClass = hostEle.getAttribute(IJSFConstants.ATTR_WARNCLASS);
        String infoClass = hostEle.getAttribute(IJSFConstants.ATTR_INFOCLASS);

        if ((!"".equals(fatalStyle)) && (fatalStyle != null))
        {
            spanEle.setAttribute(IHTMLConstants.ATTR_STYLE, fatalStyle);
        }
        else if ((!"".equals(errorStyle)) && (errorStyle != null))
        {
            spanEle.setAttribute(IHTMLConstants.ATTR_STYLE, errorStyle);
        }
        else if ((!"".equals(warnStyle)) && (warnStyle != null))
        {
            spanEle.setAttribute(IHTMLConstants.ATTR_STYLE, warnStyle);
        }
        else if ((!"".equals(infoStyle)) && (infoStyle != null))
        {
            spanEle.setAttribute(IHTMLConstants.ATTR_STYLE, infoStyle);
        }

        if ((!"".equals(fatalClass)) && (fatalClass != null))
        {
            spanEle.setAttribute(IJSFConstants.ATTR_STYLECLASS, fatalClass);
        }
        else if ((!"".equals(errorClass)) && (errorClass != null))
        {
            spanEle.setAttribute(IJSFConstants.ATTR_STYLECLASS, errorClass);
        }
        else if ((!"".equals(warnClass)) && (warnClass != null))
        {
            spanEle.setAttribute(IJSFConstants.ATTR_STYLECLASS, warnClass);
        }
        else if ((!"".equals(infoClass)) && (infoClass != null))
        {
            spanEle.setAttribute(IJSFConstants.ATTR_STYLECLASS, infoClass);
        }
        spanEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        //If the "tooltip" attribute is present with the value of "true", and so does 
        //the "showSummary",then output the "summary" as the value of the "title" attribute on the "span". 
        String hasToolTip = hostEle.getAttribute(IJSFConstants.ATTR_TOOLTIP);
        String showSummary = hostEle.getAttribute(IJSFConstants.ATTR_SHOWSUMMARY);
        if ("true".equalsIgnoreCase(hasToolTip))
        {
            if ("true".equalsIgnoreCase(showSummary))
            {
                spanEle.setAttribute("title", MESSAGE_TOOLTIP);
            }
        }
        Text text = createText(MESSAGE_CONTENT);
        spanEle.appendChild(text);

        return spanEle;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
     */
    public boolean isWidget()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
