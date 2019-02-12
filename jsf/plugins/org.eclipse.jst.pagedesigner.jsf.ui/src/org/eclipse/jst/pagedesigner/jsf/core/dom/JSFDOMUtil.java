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
package org.eclipse.jst.pagedesigner.jsf.core.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFDOMUtil
{

    /**
     * @param parentEle 
     * @param name 
     * @return the facet element or null if not found
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
                if (name.equals(ele.getAttribute("name"))) //$NON-NLS-1$
                {
                    return ele;
                }
            }
        }
        return null;
    }

    /**
     * @param node
     * @return true if node is a JSF column
     */
    public static boolean isHColumn(Node node)
    {
        if (node instanceof Element)
        {
            Element ele = (Element) node;
            if ("column".equalsIgnoreCase(ele.getLocalName()) && ITLDConstants.URI_JSF_HTML.equals(CMUtil.getElementNamespaceURI(ele))) //$NON-NLS-1$
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @param panelGridEle
     * @return the list of UI component children
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

    /**
     * @param ele
     * @return true if ele is a facet
     */
    public static boolean isFacet(Element ele)
    {
        // XXX: here we are not checking namespace! for error tolerant.
        return "facet".equals(ele.getLocalName()); //$NON-NLS-1$
    }

    /**
     * @param ele 
     * @return true if the local name of ele is "param"
     */
    public static boolean isUIParameter(Element ele)
    {
        return "param".equals(ele.getLocalName()); //$NON-NLS-1$
    }

}
