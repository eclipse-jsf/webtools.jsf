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
import org.w3c.dom.Element;

/**
 * This is for inputHidden, inputSecret, inputText.
 * 
 * @author mengbo
 * @version 1.5
 */
public class InputTagConverter extends AbstractTagConverter
{

    private String _inputType;

    /**
     * @param host
     */
    public InputTagConverter(Element host, String inputType)
    {
        super(host);
        _inputType = inputType;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();
        // Renders an HTML "input" element of "type" "text".
        Element inputEle = createElement(IHTMLConstants.TAG_INPUT);
        JSFConverterUtil.copyAllAttributes(hostEle, inputEle, null);
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, inputEle, IHTMLConstants.ATTR_CLASS);
        inputEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        inputEle.setAttribute(IHTMLConstants.ATTR_TYPE, getInputType());

        inputEle.removeAttribute(IJSFConstants.ATTR_VALUE);
        inputEle.setAttribute(IJSFConstants.ATTR_VALUE, getValue(hostEle));

        return inputEle;
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

    /**
     * @return
     */
    private String getInputType()
    {
        return _inputType;
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
