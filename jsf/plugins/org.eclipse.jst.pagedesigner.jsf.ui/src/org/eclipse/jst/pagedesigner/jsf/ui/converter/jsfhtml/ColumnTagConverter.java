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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class ColumnTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public ColumnTagConverter(Element host)
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
        // we render all non-facet (header, footer) sub elements of this element
        Element resultEle = createElement(IHTMLConstants.TAG_TD);
        // --- create column's td in column tag converter, this should not be final solution, we might need to use
        // referece column figure in td later.

        Node parent = hostEle.getParentNode();
        int offset;
        if (parent != null && parent.getLocalName().equals(IJSFConstants.TAG_DATATABLE))
        {
            String columnClassesAttr = ((Element) parent).getAttribute(IJSFConstants.ATTR_COLUMNCLASSES);
            List columnClasses = new ArrayList();
            if (columnClassesAttr != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(columnClassesAttr, ", "); //$NON-NLS-1$
                while (tokenizer.hasMoreTokens())
                {
                    columnClasses.add(tokenizer.nextToken());
                }
            }
            offset = EditModelQuery.getInstance().getSameTypeNodeIndex(hostEle);
            if (offset < columnClasses.size())
            {
                resultEle.setAttribute(ICSSPropertyID.ATTR_CLASS, (String) columnClasses.get(offset));
            }
        }
        if (EditModelQuery.getInstance().hasNonTransparentChild(hostEle, new String[] 
        {
                IJSFConstants.TAG_FACET
        }
        ))
        {
            Node child = hostEle.getFirstChild();
            int index = 0;
            while (child != null)
            {
                if (!(child instanceof Element)
                        || !JSFDOMUtil.isFacet((Element) child))
                {
                    addChild(child, new ConvertPosition(resultEle, index++));
                }
                child = child.getNextSibling();
            }
        }
        else
        {
            Node child = createText(" "); //$NON-NLS-1$
            resultEle.appendChild(child);
        }
        return resultEle;
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

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        Element hostEle = getHostElement();
        Node parent = hostEle.getParentNode();
        if (parent != null && parent.getLocalName().equals(IJSFConstants.TAG_DATATABLE))
        {
            return false;
        }
        return true;
    }
}
