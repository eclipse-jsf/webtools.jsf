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
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableBasedSelectTagConverter extends SelectTagConverter
{

    private String _inputType;

    /**
     * @param host
     */
    public TableBasedSelectTagConverter(Element host, String inputType)
    {
        super(host);
        this._inputType = inputType;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        // Render a "table" element.
        Element tableEle = createElement(IHTMLConstants.TAG_TABLE);

        // If the "styleClass" is specified, render the value of the "styleClass"
        // attribute as the value of the "class" attribute on the "table" element.
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, tableEle, IHTMLConstants.ATTR_CLASS);

        // If the "style", "border" attributes are specified, pass them thru. 
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLE, tableEle, IHTMLConstants.ATTR_STYLE);
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_BORDER, tableEle, IHTMLConstants.ATTR_BORDER);

        // If the "layout" attribute is specified, and its value is "pageDirection", 
        // render the children elements vertically, otherwise horizontally, in the table. 
        boolean horizontal = true;
        if ("pageDirection".equalsIgnoreCase(hostEle.getAttribute(IJSFConstants.ATTR_LAYOUT)))
        {
            horizontal = false;
        }

        Element itemContainer;
        if (horizontal)
        {
            itemContainer = createElement(IHTMLConstants.TAG_TR);
            tableEle.appendChild(itemContainer);
        }
        else
        {
            itemContainer = tableEle;
        }

        // XXX: If any of the children are an instance of SelectItemGroup, render them as a 
        // nested table.
        // but at design time, we don't know anything about SelectItemGroup. so will not
        // rendering nested table.

        List selectitems = this.getSelectItems(hostEle);

        // for designer, if there is no item, we still want to render one
        if (this.isDesignerMode() && selectitems.isEmpty())
        {
            SelectItemModel item = getDefault(hostEle);
            selectitems.add(item);
        }

        boolean isDisabled = Boolean.TRUE.toString()
                .equalsIgnoreCase(hostEle.getAttribute(IJSFConstants.ATTR_DISABLED));
        boolean isReadonly = Boolean.TRUE.toString()
                .equalsIgnoreCase(hostEle.getAttribute(IJSFConstants.ATTR_READONLY));

        for (int i = 0, size = selectitems.size(); i < size; i++)
        {
            SelectItemModel item = (SelectItemModel) selectitems.get(i);
            // Each of the children are ultimately rendererd as follows. 
            // Render a "label" element. Inside of the "label", render an "input" element 
            // of "type" "checkbox" for each child component.
            Element labelEle = createElement(IHTMLConstants.TAG_LABEL);
            Element inputEle = createElement(IHTMLConstants.TAG_INPUT);
            inputEle.setAttribute(IHTMLConstants.ATTR_TYPE, getInputType());
            if (isDisabled || item.isDisabled())
            {
                inputEle.setAttribute(IHTMLConstants.ATTR_DISABLED, "disabled");
            }
            if (isReadonly)
            {
                inputEle.setAttribute(IHTMLConstants.ATTR_READONLY, "readonly");
            }

            // As an exception to the general 
            // rules about how to handle the "id" attribute, render it as an attribute on 
            // the outer "table" element, the value of which is the clientId of the component 
            // per the rules at the beginning of this specification.The "id" attribute must 
            // not be output on each "input" element.
            if (item.getId() != null)
            {
                labelEle.setAttribute(IHTMLConstants.ATTR_ID, item.getId());
            }

            // The value of the current SelectItem 
            // is rendered as the value of the "value" attribute. If the value of the enclosing 
            // UISelectMany matches the current value, render "checked" as the value of the 
            // "checked" attribute. If the current SelectItem.isDisabled() returns true, 
            // render "disabled" as the value of the "disabled" attribute.
            if (item.getValue() != null)
            {
                inputEle.setAttribute(IHTMLConstants.ATTR_VALUE, item.getValue());
            }
            // XXX: checked and disabled is not handled.

            // Close out the "input" element and render the return value from 
            // SelectItem.getLabel(). Close out the "label" element and any other nested elements.
            Text label = createText(item.getDisplayString());

            labelEle.appendChild(inputEle);
            labelEle.appendChild(label);

            if (horizontal)
            {
                Element td = createElement(IHTMLConstants.TAG_TD);
                td.appendChild(labelEle);
                itemContainer.appendChild(td);
            }
            else
            {
                Element tr = createElement(IHTMLConstants.TAG_TR);
                Element td = createElement(IHTMLConstants.TAG_TD);
                tr.appendChild(td);
                td.appendChild(labelEle);
                itemContainer.appendChild(tr);
            }
        }

        return (tableEle);
    }

    protected String getInputType()
    {
        return _inputType;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
