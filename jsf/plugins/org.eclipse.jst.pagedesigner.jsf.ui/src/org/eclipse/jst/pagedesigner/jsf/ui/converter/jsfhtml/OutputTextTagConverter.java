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
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class OutputTextTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public OutputTextTagConverter(Element host)
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
        // render its value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, spanEle, IHTMLConstants.ATTR_CLASS);
        spanEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);
        
        spanEle.removeAttribute(IJSFConstants.ATTR_VALUE);

        // If the "escape" attribute is not present, or it is present and 
        // its value is "true" all angle brackets should be converted to the 
        // ampersand xx semicolon syntax when rendering the value of the "value" 
        // attribute as the value of the component. If the "escape" attribute is 
        // present and is "false" the value of the component should be rendered 
        // as text without escaping. 
        String value = getValue(hostEle);
        Text	textNode = createText(value);
        spanEle.appendChild(textNode);

        return spanEle;
    }

    /**
     * @param hostEle
     * @return
     */
    private String getValue(Element hostEle)
    {
        String valueAttr = hostEle.getAttribute(IJSFConstants.ATTR_VALUE);
        if (valueAttr == null)
        {
            String name = hostEle.getLocalName();
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return mapValue(valueAttr);
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

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
