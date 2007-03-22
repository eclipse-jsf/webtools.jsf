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

import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.ConverterUtil;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class SelectBasedTagConverter extends SelectTagConverter
{

    /**
     * @param host
     */
    public SelectBasedTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        // Render an HTML "select" element.
        Element selectEle = createElement(IHTMLConstants.TAG_SELECT);

        // Render the clientId of the component as the value of the "name" attribute.
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_ID, selectEle, IHTMLConstants.ATTR_NAME);

        // XXX: handle ignore
        JSFConverterUtil.copyAllAttributes(hostEle, selectEle, null);
        
//      If the "styleClass" attribute is specified, render its value as the value of 
        // the "class" attribute on the "select" element.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, selectEle, IHTMLConstants.ATTR_CLASS);
        selectEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // child class may generated different multiple and size attribute
        handleMultipleAndSize(hostEle, selectEle);

        // next generated the options under the <select>, so they can also be displayed.
        List selectItems = this.getSelectItems(hostEle);
        for (int i=0, size=selectItems.size(); i<size; i++)
        {
            SelectItemModel item = (SelectItemModel) selectItems.get(i);
            Element option = createElement(IHTMLConstants.TAG_OPTION);
            option.setAttribute(IHTMLConstants.ATTR_VALUE, item.getItemValue());
            Text textNode = createText(item.getDisplayString());
            option.appendChild(textNode);
            selectEle.appendChild(option);
        }

        return (selectEle);
    }

    /**
     * @param hostEle
     * @param selectEle
     */
    protected abstract void handleMultipleAndSize(Element hostEle, Element selectEle);

}
