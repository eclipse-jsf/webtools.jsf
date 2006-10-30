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

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class is supposed to be common parent class for the different selectMany tags.
 * 
 * @author mengbo
 * @version 1.5
 */
public abstract class SelectTagConverter extends AbstractTagConverter
{
    /**
     * @param host
     */
    public SelectTagConverter(Element host)
    {
        super(host);
    }

    /**
     * Return a list of SelectItem
     * 
     * @param parent
     * @return
     */
    protected List getSelectItems(Element parent)
    {
        List result = new ArrayList();
        NodeList nl = parent.getChildNodes();
        for (int i = 0, size = nl.getLength(); i < size; i++)
        {
            Node child = nl.item(i);
            if (child instanceof Element)
            {
                Element ele = (Element) child;
                String tagname = ele.getLocalName();
                if (IJSFConstants.TAG_SELECTITEM.equalsIgnoreCase(tagname))
                {
                    SelectItemModel item = new SelectItemModel();
                    item.setDescription(ele.getAttribute(IJSFConstants.ATTR_ITEMDESCRIPTION));
                    item.setLabel(ele.getAttribute(IJSFConstants.ATTR_ITEMLABEL));
                    item.setItemValue(ele.getAttribute(IJSFConstants.ATTR_ITEMVALUE));
                    item.setValue(ele.getAttribute(IJSFConstants.ATTR_VALUE));
                    item.setId(ele.getAttribute(IJSFConstants.ATTR_ID));
                    item.setDisabled("true".equalsIgnoreCase(ele.getAttribute(IJSFConstants.ATTR_ITEMDISABLED)));
                    result.add(item);
                }
                else if (IJSFConstants.TAG_SELECTITEMS.equalsIgnoreCase(tagname))
                {
                    // as selectItems can only be resolved at run time, so we just emulate
                    // as if it map to two selectitem
                    String value = ele.getAttribute(IJSFConstants.ATTR_VALUE);
                    if (value == null || value.length() == 0)
                    {
                        value = IJSFConstants.ATTR_VALUE;
                    }
                    SelectItemModel item1 = new SelectItemModel();
                    item1.setValue(value + "_1");
                    item1.setId(ele.getAttribute(IJSFConstants.ATTR_ID));
                    result.add(item1);
                    SelectItemModel item2 = new SelectItemModel();
                    item2.setValue(value + "_2");
                    item2.setId(ele.getAttribute(IJSFConstants.ATTR_ID));
                    result.add(item2);
                }
            }
        }

        return result;
    }

    public SelectItemModel getDefault(Element parent)
    {
        SelectItemModel item = new SelectItemModel();
        if (parent.getLocalName().equals(IJSFConstants.TAG_SELECTONERADIO))
        {
            item.setLabel("radio");
        }
        else if (parent.getLocalName().equals(IJSFConstants.TAG_SELECTMANYCHECKBOX))
        {
            item.setLabel("checkBox");
        }
        else
        {
            item.setLabel(parent.getLocalName());
        }
        return item;
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
        return true;
    }

}
