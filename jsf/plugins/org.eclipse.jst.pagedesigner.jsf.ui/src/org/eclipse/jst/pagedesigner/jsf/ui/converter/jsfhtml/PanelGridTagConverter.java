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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridTagConverter extends AbstractTagConverter
{
    /**
     * @param host
     */
    public PanelGridTagConverter(Element host)
    {
        super(host);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.visualtag.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element panelGridEle = this.getHostElement();

        //  Renders an HTML "table" element, conforming to the rules in the HTML 401 specification.
        Element tableEle = createElement(IHTMLConstants.TAG_TABLE);

        // Render the pass-through attributes in the table below.
        // TODO: need handle ignored attributes here. But as they don't conflict
        // with standard table attributes, so its ok that we leave it later.
        JSFConverterUtil.copyAllAttributes(panelGridEle, tableEle, null);
        
        // If the "styleClass" attribute is specified, render its value as the value of the "class" attribute
        JSFConverterUtil.copyAttribute(panelGridEle, IJSFConstants.ATTR_STYLECLASS, tableEle, IHTMLConstants.ATTR_CLASS);
        tableEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        int columns = DOMUtil.getIntAttributeIgnoreCase(panelGridEle, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }

        // Render the "header" facet, if present, inside of "thead", "tr", and "th" elements, nested in that order.
        Element facet = JSFDOMUtil.findFacet(panelGridEle, "header");
        if (facet != null)
        {
            // we need render a thead here
            Element theadEle = createElement(IHTMLConstants.TAG_THEAD);
            tableEle.appendChild(theadEle);
            Element trEle = createElement(IHTMLConstants.TAG_TR);
            theadEle.appendChild(trEle);
            Element thEle = createElement(IHTMLConstants.TAG_TH);
            trEle.appendChild(thEle);
            // If the "headerClass" attribute is specifed, render its value as the value of the "class" attribute on
            // the "th" element.
            String headerClass = panelGridEle.getAttribute(IJSFConstants.ATTR_HEADERCLASS);
            if (headerClass != null && headerClass.length() > 0)
            {
                thEle.setAttribute(IHTMLConstants.ATTR_CLASS, headerClass);
            }
            // Render the value of the "columns" attribute as the value of the "colspan" attribute on the "th" element.
            thEle.setAttribute(IHTMLConstants.ATTR_COLSPAN, String.valueOf(columns));
            addChild(facet, new ConvertPosition(thEle, 0));
        }

        // Render the "footer" facet if present, using similar logic to the rendering of
        // the "header", but replacing "thead" with "tfoot", "th" with "td", and "headerClass"
        // with "footerClass".
        facet = JSFDOMUtil.findFacet(panelGridEle, "footer");
        if (facet != null)
        {
            // we need render a thead here
            Element theadEle = createElement(IHTMLConstants.TAG_TFOOT);
            tableEle.appendChild(theadEle);
            Element trEle = createElement(IHTMLConstants.TAG_TR);
            theadEle.appendChild(trEle);
            Element tdEle = createElement(IHTMLConstants.TAG_TD);
            trEle.appendChild(tdEle);
            // If the "headerClass" attribute is specifed, render its value as the value of the "class" attribute on
            // the "th" element.
            String footerClass = panelGridEle.getAttribute(IJSFConstants.ATTR_FOOTERCLASS);
            if (footerClass != null && footerClass.length() > 0)
            {
                tdEle.setAttribute(IHTMLConstants.ATTR_CLASS, footerClass);
            }
            // Render the value of the "columns" attribute as the value of the "colspan" attribute on the "th" element.
            tdEle.setAttribute(IHTMLConstants.ATTR_COLSPAN, String.valueOf(columns));
            addChild(facet, new ConvertPosition(tdEle, 0));
        }

        List rowclasses = new ArrayList();
        String rowclassesattr = panelGridEle.getAttribute(IJSFConstants.ATTR_ROWCLASSES);
        if (rowclassesattr != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(rowclassesattr, ", ");
            while (tokenizer.hasMoreTokens())
            {
                rowclasses.add(tokenizer.nextToken());
            }
        }

        List columnclasses = new ArrayList();
        String columnclassattr = panelGridEle.getAttribute(IJSFConstants.ATTR_COLUMNCLASSES);
        if (columnclassattr != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(columnclassattr, ", ");
            while (tokenizer.hasMoreTokens())
            {
                columnclasses.add(tokenizer.nextToken());
            }
        }

        // Render the children of the UIPanel component inside of a "tbody" element.
        // Render the children based on the value of the "columns" attribute, creating a new
        // row each time a "columns" worth of children have been rendered. Each child is rendered
        // inside of a "td" element. If a child has "rendered==false" it is not rendered,
        // and the column counter must not be incremented.
        Element tbody = createElement(IHTMLConstants.TAG_TBODY);
        tableEle.appendChild(tbody);

        List uipanelChildren = JSFDOMUtil.getUIComponentChildren(panelGridEle);
        Element currentTr = null;
        int nextRow = 0; // for rowclasses
        for (int i = 0; i < uipanelChildren.size(); i++)
        {
            int columnIndex = i % columns;
            if (columnIndex == 0)
            {
                currentTr = createElement(IHTMLConstants.TAG_TR);
                if (!rowclasses.isEmpty())
                {
                    currentTr.setAttribute(IHTMLConstants.ATTR_CLASS, (String) rowclasses.get(nextRow));
                    nextRow = (nextRow + 1) % rowclasses.size();
                }
                tbody.appendChild(currentTr);
            }
            Element uichild = (Element) uipanelChildren.get(i);
            Element td = createElement(IHTMLConstants.TAG_TD);
            if (columnIndex < columnclasses.size())
            {
                td.setAttribute(IHTMLConstants.ATTR_CLASS, (String) columnclasses.get(columnIndex));
            }
            currentTr.appendChild(td);
            this.addChild(uichild, new ConvertPosition(td, 0));
        }

        return (tableEle);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isWidget()
     */
    public boolean isWidget()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needTableDecorator()
     */
    public boolean needTableDecorator()
    {
        return true;
    }
}
