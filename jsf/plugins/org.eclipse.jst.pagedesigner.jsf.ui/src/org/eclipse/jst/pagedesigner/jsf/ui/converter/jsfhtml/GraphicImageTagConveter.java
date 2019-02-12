/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfhtml;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.ConverterUtil;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class GraphicImageTagConveter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public GraphicImageTagConveter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();
        // Renders an HTML "img" element. 
        Element imgEle = createElement(IHTMLConstants.TAG_IMG);

        JSFConverterUtil.copyAllAttributes(hostEle, imgEle, null);

        // If the "styleClass" attribute is specified, render its value as 
        // the value of the "class" attribute. 
        ConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, imgEle, IHTMLConstants.ATTR_CLASS);
        imgEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // Render the clientId as the value of the "id" attribute. 
        // Render the value of the component as the value of the "src" 
        // attribute, after passing it to the getResourceUR() method 
        // of the ViewHandler  for this application, and passing the 
        // result through the encodeResourceURL() method of the 
        // ExternalContext. 
        String src = getSrc(hostEle);
        if (src != null)
        {
            imgEle.setAttribute(IHTMLConstants.ATTR_SRC, src);
        }
        return imgEle;
    }

    /**
     * @param hostEle
     * @return
     */
    private String getSrc(Element hostEle)
    {
        // TODO: need revisit the spec for special URL converting
        // for this element
        String value = hostEle.getAttribute(IJSFConstants.ATTR_VALUE);
        if (value == null)
        {
            // Context-relative URL to retrieve the resource associated 
            // with this component. This is an alias for the "value" 
            // property.
            value = hostEle.getAttribute(IJSFConstants.ATTR_URL);
        }
        if (value != null)
        {
            value = mapValue(value);
            value = mapURL(value);
        }
        return value;
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

}
