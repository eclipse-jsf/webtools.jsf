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
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class CommandButtonTagConverter extends AbstractTagConverter
{
    private List   _nonVisualChildren = Collections.EMPTY_LIST;
    
    /**
     * @param host
     */
    public CommandButtonTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        // Renders an HTML "input" element.
        Element inputEle = createElement(IHTMLConstants.TAG_INPUT);

        // pass throught attributes
        JSFConverterUtil.copyAllAttributes(hostEle, inputEle, null);
        
//      If the "styleClass" attribute is specified, render its 
        // value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, inputEle, IHTMLConstants.ATTR_CLASS);
        
        inputEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // next handles input type
        // "image" attribute:  Absolute or relative URL of the image to be 
        // displayed for this button. If specified, this "input" element will be 
        // of type "image". Otherwise, it will be of the type specified by the 
        // "type" property with a label specified by the "value" property.

        String imageAttr = hostEle.getAttribute(IJSFConstants.ATTR_IMAGE);
        String type;
        if (imageAttr == null || imageAttr.length() == 0)
        {
            type = hostEle.getAttribute(IJSFConstants.ATTR_TYPE);
            if (type == null || type.length() == 0)
            {
                type = "submit";	// default type
            }
        }
        else
        {
            type = "image";
            // copy the image attribute as the src attribute
            String src = mapURL(imageAttr);
            inputEle.setAttribute(IHTMLConstants.ATTR_SRC, src);
        }
        // overwrite the "type" attribute
        inputEle.setAttribute(IHTMLConstants.ATTR_TYPE, type);       

        String value = hostEle.getAttribute(IJSFConstants.ATTR_VALUE);
        if (value != null && value.length() > 0)
        {
            value = mapValue(value);
            inputEle.setAttribute(IHTMLConstants.ATTR_VALUE, value);
        }

        // get non-visual children
        // a button only has non-visual children, so
        // all Element child nodes are considered such
        if (hostEle.hasChildNodes())
        {
            _nonVisualChildren = new ArrayList();
            
            NodeList list = hostEle.getChildNodes();
            
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    _nonVisualChildren.add(node);
                }
            }
        }
        
        // Render the clientId of the component as the value of the "name" attribute. 
        // Render the current value of the component as the value of the "value" attribute. 

        return inputEle;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isWidget()
     */
    public boolean isWidget()
    {
        return true;
    }

    public List getNonVisualChildren() {
        return _nonVisualChildren;
    }

}
