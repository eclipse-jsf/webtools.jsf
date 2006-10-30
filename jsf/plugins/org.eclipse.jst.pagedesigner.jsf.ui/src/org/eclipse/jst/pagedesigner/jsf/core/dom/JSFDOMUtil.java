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
package org.eclipse.jst.pagedesigner.jsf.core.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.utils.CMUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFDOMUtil
{

    /**
     * @param panelGridEle
     * @param string
     * @return
     */
    public static Element findFacet(Element parentEle, String name)
    {
        NodeList childnodes = parentEle.getChildNodes();
        for (int i=0, size=childnodes.getLength(); i<size; i++)
        {
            Node node = childnodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && JSFDOMUtil.isFacet((Element)node))
            {
                Element ele = (Element) node;
                if (name.equals(ele.getAttribute("name")))
                {
                    return ele;
                }
            }
        }
        return null;
    }

    public static boolean isHColumn(Node node)
    {
        if (node instanceof Element)
        {
            Element ele = (Element) node;
            if ("column".equalsIgnoreCase(ele.getLocalName()) && IJMTConstants.URI_JSF_HTML.equals(CMUtil.getElementNamespaceURI(ele)))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @param panelGridEle
     * @return
     */
    public static List getUIComponentChildren(Element panelGridEle)
    {
        // XXX: temp implementation. Later may move this out to be shared.
        // currently just returning all children Element. Need to filter out things like non JSF UI component
        List list = new ArrayList();
        NodeList childnodes = panelGridEle.getChildNodes();
        for (int i=0, size=childnodes.getLength(); i<size; i++)
        {
            Node node = childnodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                if (JSFDOMUtil.isFacet((Element) node))
                {
                    continue;
                }
                list.add(node);
            }
        }
        return list;
    }

    public static boolean isFacet(Element ele)
    {
        // XXX: here we are not checking namespace! for error tolerant.
        return "facet".equals(ele.getLocalName());
    }

    /**
     * @param child
     * @return
     */
    public static boolean isUIParameter(Element ele)
    {
        return "param".equals(ele.getLocalName());
    }

}
