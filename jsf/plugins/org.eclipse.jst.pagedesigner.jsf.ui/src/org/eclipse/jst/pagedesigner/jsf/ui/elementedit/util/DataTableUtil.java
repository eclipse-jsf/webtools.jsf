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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.util;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author mengbo
 * @version 1.5
 */
public class DataTableUtil
{
    private static final int INVALID_POSITION = -10;
    private Element          _dataTable       = null;

    /**
     * @param dataTable
     */
    public DataTableUtil(Element dataTable)
    {
        this._dataTable = dataTable;
    }

    /**
     * @param column
     * @return the column index for column
     */
    public int getColumnIndex(Element column)
    {
        NodeList nodeList = this._dataTable.getElementsByTagName("h:column"); //$NON-NLS-1$
        if (nodeList != null)
        {
            for (int i = 0, size = nodeList.getLength(); i < size; i++)
            {
                if (column == (Element) nodeList.item(i))
                {
                    return i;
                }
            }
        }
        return INVALID_POSITION;
    }

    /**
     * @param child
     * @return the hcolumn parent of child or null if not found
     */
    public Element findHColumnParent(Node child)
    {
        if (child == null)
        {
            return null;
        }

        Node childBackup = child;
        boolean hasHColumnParent = false;

        if (JSFDOMUtil.isHColumn(child))
        {
            return (Element) child;
        }
        while (!IJSFConstants.TAG_DATATABLE.equalsIgnoreCase(childBackup.getParentNode().getLocalName()))
        {
            childBackup = childBackup.getParentNode();
            String tagName = childBackup.getLocalName();
            if (IJSFConstants.TAG_COLUMN.equalsIgnoreCase(tagName))
            {
                hasHColumnParent = true;
                break;
            }
        }

        if (hasHColumnParent)
        {
            return (Element) childBackup;
        }
        return null;
    }
}
