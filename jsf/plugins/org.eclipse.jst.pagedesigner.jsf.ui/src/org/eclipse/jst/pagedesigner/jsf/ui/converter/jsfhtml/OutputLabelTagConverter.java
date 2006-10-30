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
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * @author mengbo
 * @version 1.5
 */
public class OutputLabelTagConverter extends AbstractTagConverter
{
    /**
     * @param host
     */
    public OutputLabelTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        Element hostEle = getHostElement();

        Element labelEle = createElement(IHTMLConstants.TAG_LABEL);

        JSFConverterUtil.copyAllAttributes(hostEle, labelEle, null);

        // If the "styleClass" attribute is present, 
        // render its value as the value of the "class" attribute.
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, labelEle, IHTMLConstants.ATTR_CLASS);
        labelEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        //        String value = getValue(hostEle);
        //        Text	textNode = createText(value);
        //        labelEle.appendChild(textNode);

        int index = 0;
        Node child = hostEle.getFirstChild();
        if (hostEle.hasChildNodes())
        {
            for (; child != null; child = child.getNextSibling())
            {
                if (!shouldIgnore(child))
                {
                    if (child instanceof Element && JSFDOMUtil.isUIParameter((Element) child))
                    {
                        // skip
                    }
                    else
                    {
                        addChild(child, new ConvertPosition(labelEle, index++));
                    }
                }
            }
        }
        else
        {
            labelEle.appendChild(createText(getDefaultValue()));
        }

        return labelEle;
    }

    /**
     * @param hostEle
     * @return
     */
    private String getDefaultValue()
    {
        String name = IJSFConstants.TAG_OUTPUTLABEL;
        
        return name.substring(0, 1).toUpperCase() + name.substring(1);
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
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
