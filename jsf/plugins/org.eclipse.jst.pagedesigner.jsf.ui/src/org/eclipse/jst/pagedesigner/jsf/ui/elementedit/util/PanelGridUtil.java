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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.util;

import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.w3c.dom.Element;

import org.eclipse.jst.pagedesigner.utils.DOMUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridUtil
{
    private static final int INVALID_POSITION = -10;
    private Element          _panelGrid       = null;

    public PanelGridUtil(Element panelGrid)
    {
        this._panelGrid = panelGrid;
    }

    public int convertRowIndexFromDomToView(int domIndex)
    {
        boolean hasHeaderRow = (JSFDOMUtil.findFacet(this._panelGrid, "header") != null);
        boolean hasFooterRow = (JSFDOMUtil.findFacet(this._panelGrid, "footer") != null);
        if (!hasFooterRow)
        {
            return domIndex;
        }
        if (domIndex == 0)
        {
            return domIndex;
        }
        if (hasHeaderRow)
        {
            if (domIndex > 1)
            {
                return domIndex - 1;
            }
        }
        else
        {
            if (domIndex > 0)
            {
                return domIndex - 1;
            }
        }
        //must be footer
        int uiRows = getUIRowCount();
        return domIndex + uiRows;
    }

    public int getDomRowIndex(Element cell)
    {
        boolean hasHeaderRow = (JSFDOMUtil.findFacet(this._panelGrid, "header") != null);
        boolean hasFooterRow = (JSFDOMUtil.findFacet(this._panelGrid, "footer") != null);
        //if cell is header or footer
        boolean isFacet = JSFDOMUtil.isFacet(cell);
        Element parent = (Element) cell.getParentNode();
        boolean isParentFacet = JSFDOMUtil.isFacet(parent);
        if (isParentFacet)
        {
            cell = parent;
        }
        if (isFacet || isParentFacet)
        {
            String attrName = cell.getAttribute("name");
            if ("header".equalsIgnoreCase(attrName))
            {
                return 0;
            }
            else if ("footer".equalsIgnoreCase(attrName))
            {
                if (hasHeaderRow)
                {
                    return 1;
                }
                return 0;
            }
        }

        int columns = DOMUtil.getIntAttributeIgnoreCase(this._panelGrid, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }
        int pos = getPosition(cell);
        int rowIndex = pos / columns;

        if (hasHeaderRow)
        {
            rowIndex++;
        }
        if (hasFooterRow)
        {
            rowIndex++;
        }

        return rowIndex;
    }

    public int getDomColumnIndex(Element cell)
    {
        int columns = DOMUtil.getIntAttributeIgnoreCase(this._panelGrid, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }
        int pos = getPosition(cell);
        //if position is at header or footer,then insert column action should be disabled
        if (pos == INVALID_POSITION)
        {
            return pos;
        }

        int columnIndex = pos % columns;
        return columnIndex;
    }

    public int getUIRowCount()
    {
        int columns = DOMUtil.getIntAttributeIgnoreCase(this._panelGrid, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }
        List children = JSFDOMUtil.getUIComponentChildren(this._panelGrid);
        int numRows = (children.size() + columns - 1) / columns;

        return numRows;
    }

    private int getPosition(Element cell)
    {
        List children = JSFDOMUtil.getUIComponentChildren(this._panelGrid);
        int size = children.size();
        int i = 0;
        for (i = 0; i < size; i++)
        {
            if (cell == children.get(i))
            {
                break;
            }
        }
        if (i == size)
        {
            return INVALID_POSITION;
        }
        return i;
    }

}
