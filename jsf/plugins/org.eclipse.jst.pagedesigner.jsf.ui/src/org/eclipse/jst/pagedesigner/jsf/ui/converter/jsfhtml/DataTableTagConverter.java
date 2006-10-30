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
public class DataTableTagConverter extends AbstractTagConverter
{
    private static final String FACET_NAME_HEADER = "header";

    /**
     * @param host
     */
    public DataTableTagConverter(Element host)
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

        // Renders an HTML "table" element compliant with the HTML 401 specification.
        Element tableEle = createElement(IHTMLConstants.TAG_TABLE);

        // Any pass-through attributes are also rendered on the "table" element.
        JSFConverterUtil.copyAllAttributes(hostEle, tableEle, null);

        // Please consult the javadoc for UIData to supplement this specification.
        // If the "styleClass" attribute is specified, render its value as the value
        // of the "class" attribute on the "table" element.
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, tableEle, IHTMLConstants.ATTR_CLASS);
        tableEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        List columns = findUIColumns(hostEle);

        // rendering the thead
        convertTHeader(hostEle, tableEle, columns, true);

        convertTBody(hostEle, tableEle, columns);
        // rendering the tfoot
        // Follow the same process as for the header, except replace "header" with
        // "footer", "th" with "td", "thead" with "tfoot", and "headerClass" with
        // "footerClass". Do not render any "scope" attribute for the footer.
        convertTHeader(hostEle, tableEle, columns, false);

        return tableEle;
    }

    /**
     * @param hostEle
     * @return
     */
    private List findUIColumns(Element hostEle)
    {
        List result = new ArrayList();
        Node child = hostEle.getFirstChild();
        if (child != null)
        {

            while (child != null)
            {
                if (child instanceof Element)
                {
                    Element ele = (Element) child;
                    // XXX: we are not handling namespace here
                    if (IJSFConstants.TAG_COLUMN.equals(ele.getLocalName()))
                    {
                        result.add(ele);
                    }
                }
                child = child.getNextSibling();
            }
        }
        return result;
    }

    protected void convertTBody(Element hostEle, Element tableEle, List columns)
    {
        // Rendering the table body
        Element tbodyEle = createElement(IHTMLConstants.TAG_TBODY);
        tableEle.appendChild(tbodyEle);
        // Render a "tbody" element. Keep track of the result of the "rows" property
        // on the UIData component. Keep track of the number of rows we have rendered
        // so far.
        // Iterate through the rows. Set the "rowIndex" property of the UIDatacomponent
        // to be correct as we iterate through the rows.
        // Stop rendering children and close out the "tbody" element if the "rowAvailable"
        // property of the UIData returned false.

        // XXX: we are only rendering one row.
        // Output a "tr" element.
        Element trEle = createElement(IHTMLConstants.TAG_TR);
        tbodyEle.appendChild(trEle);

        // Output the value of the "rowClasses" per the attribute description below.
        String rowClasses = tableEle.getAttribute(IJSFConstants.ATTR_ROWCLASSES);
        if (rowClasses != null)
        {
            // as we are only rendering one row, so we only get the first rowclass
            StringTokenizer tokenizer = new StringTokenizer(rowClasses, ", ");
            if (tokenizer.hasMoreTokens())
            {
                trEle.setAttribute(IHTMLConstants.ATTR_CLASS, tokenizer.nextToken());
            }
        }

        // --------------------------------- move this part into ColumnsTagConverter. ------------------------------
        String columnClassesAttr = hostEle.getAttribute(IJSFConstants.ATTR_COLUMNCLASSES);
        List columnClasses = new ArrayList();
        if (columnClassesAttr != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(columnClassesAttr, ", ");
            while (tokenizer.hasMoreTokens())
            {
                columnClasses.add(tokenizer.nextToken());
            }
        }
        // --- create column's td in column tag converter, here
        // For each UIColumn child, output a "td" element, attaching the value of the
        // "columnClasses" attribute of the UIData component per the attribute description below.
        // Recursively encode each child of each UIColumn child. Close out the "td" element.
        // When done with the row, close out the "tr" element. When done with all the rows,
        // close out the "tbody" element.
        for (int i = 0, size = columns.size(); i < size; i++)
        {
            Element column = (Element) columns.get(i);
            addChild(column, new ConvertPosition(trEle, i));
            //            Element tdEle = createElement("td");
            //            if (i < columnClasses.size())
            //            {
            //                tdEle.setAttribute("class", (String) columnClasses.get(i));
            //            }
            //
            //            trEle.appendChild(tdEle);
            //            // put the "h:column" under td.
            //            addChild(column, new ConvertPosition(tdEle, 0));
        }
    }

    /**
     * @param hostEle
     * @param tableEle
     * @param header true means header, false means footer
     */
    protected void convertTHeader(Element hostEle, Element tableEle, List columns, boolean header)
    {
        // If the UIData component has a "header" facet, or any of the child UIColumn
        // components has a "header" facet, render a "thead" element.
        Element facetEle = JSFDOMUtil.findFacet(hostEle, header ? FACET_NAME_HEADER : "footer");
        boolean hasColHeader = false;
        for (int i = 0, size = columns.size(); i < size; i++)
        {
            Element uicolumnEle = (Element) columns.get(i);
            Element columnFacet = JSFDOMUtil.findFacet(uicolumnEle, header ? FACET_NAME_HEADER : "footer");
            if (columnFacet != null)
            {
                hasColHeader = true;
                break;
            }
        }

        if (facetEle == null && !hasColHeader)
        {
            return;
        }
        Element theadEle = createElement(header ? IHTMLConstants.TAG_THEAD : IHTMLConstants.TAG_TFOOT);
        tableEle.appendChild(theadEle);

        // If the UIData component has a "header" facet, encode its contents inside of
        // "tr" and "th" elements, respectively.
        if (facetEle != null)
        {
            Element trEle = createElement(IHTMLConstants.TAG_TR);
            theadEle.appendChild(trEle);
            Element thEle = createElement(header ? IHTMLConstants.TAG_TH : IHTMLConstants.TAG_TD);
            trEle.appendChild(thEle);
            // Output the value of the "headerClass" attribute of the UIData component,
            // if present, as the value of the "class" attribute on the "th".
            ConverterUtil.copyAttribute(hostEle, header ? IJSFConstants.ATTR_HEADERCLASS
                    : IJSFConstants.ATTR_FOOTERCLASS, thEle, IHTMLConstants.ATTR_CLASS);
            // Output the number of child UIColumn components of theUIData component as
            // the value of the "colspan" attribute on the "th".
            if (columns.size() > 0)
            {
                thEle.setAttribute(IHTMLConstants.ATTR_COLSPAN, String.valueOf(columns.size()));
            }

            addChild(facetEle, new ConvertPosition(thEle, 0));
        }
        // Output "colgroup" as the value of the "scope" attribute on the "th" element.

        // If any of the child UIColumn components has a "header" facet render a "tr"
        // element.
        if (hasColHeader)
        {
            Element trEle = createElement(IHTMLConstants.TAG_TR);
            theadEle.appendChild(trEle);

            for (int i = 0, size = columns.size(); i < size; i++)
            {
                Element uicolumnEle = (Element) columns.get(i);
                Element columnFacet = JSFDOMUtil.findFacet(uicolumnEle, header ? FACET_NAME_HEADER : "footer");
                Element thEle = createElement(header ? IHTMLConstants.TAG_TH : IHTMLConstants.TAG_TD);
                trEle.appendChild(thEle);
                if (columnFacet != null)
                {
                    // For eachUIColumn that actually has a "header" facet, render it inside of
                    // a "th" element.
                    addChild(columnFacet, new ConvertPosition(thEle, 0));
                }
                else
                {
                    // Columns that don't have a "header" facet cause an empty "th" element to be
                    // rendered.
                }

                // Output the value of the "headerClass" attribute of the UIData component,
                // if present, as the value of the "class" attribute on the "th".
                ConverterUtil.copyAttribute(hostEle, header ? IJSFConstants.ATTR_HEADERCLASS
                        : IJSFConstants.ATTR_FOOTERCLASS, thEle, IHTMLConstants.ATTR_CLASS);

                // Output "col" as the value of the "colgroup" attribute on the "th" element.
            }
        }
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
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return false;
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
