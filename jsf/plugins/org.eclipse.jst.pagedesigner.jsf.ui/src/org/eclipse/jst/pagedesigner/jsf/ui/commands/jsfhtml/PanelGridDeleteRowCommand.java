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
package org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * Remove a row from panel grid
 * 
 * @author mengbo
 * @version 1.5
 */
public class PanelGridDeleteRowCommand extends DesignerCommand
{
    Element _panelGridEle;
    int     _rowIndex;

    /**
     * @param viewer
     * @param panelGrid 
     * @param index 
     */
    public PanelGridDeleteRowCommand(IHTMLGraphicalViewer viewer, Element panelGrid, int index)
    {
        super(CommandResources.getString("PanelGridDeleteRowCommand.Label.DeleteRow"), viewer); //$NON-NLS-1$
        this._panelGridEle = panelGrid;
        this._rowIndex = index;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        // Remove the row from panelGrid is a little complex. Since this row could
        // be header or footer.
        boolean hasHeaderRow = (JSFDOMUtil.findFacet(_panelGridEle, "header") != null); //$NON-NLS-1$

        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, "columns", 1); //$NON-NLS-1$
        if (columns < 1)
        {
            columns = 1;
        }

        List children = JSFDOMUtil.getUIComponentChildren(_panelGridEle);
        int numRows = (children.size() + columns - 1) / columns;

        int removeRow = _rowIndex;

        if (hasHeaderRow)
        {
            if (removeRow == 0)
            {
                // remove the header row.
                removeHeader();
                formatNode(_panelGridEle);
                return;
            }
            removeRow--;
        }
        if (removeRow < numRows)
        {
            List toRemove = new ArrayList();
            int max = columns * (removeRow + 1);
            max = Math.min(max, children.size());
            for (int i = (columns * removeRow); i < max; i++)
            {
                toRemove.add(children.get(i));
            }
            for (int i = 0, size = toRemove.size(); i < size; i++)
            {
                _panelGridEle.removeChild((Node) toRemove.get(i));
            }
            formatNode(_panelGridEle);
        }
        else
        {
            // must be footer.
            removeFooter();
            formatNode(_panelGridEle);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._rowIndex < 0)
        {
            return false;
        }
        return super.canExecute();
    }

    /**
     * 
     */
    private void removeFooter()
    {
        Element ele = JSFDOMUtil.findFacet(_panelGridEle, "footer"); //$NON-NLS-1$
        if (ele != null)
        {
            _panelGridEle.removeChild(ele);
        }
    }

    /**
     * 
     */
    private void removeHeader()
    {
        Element ele = JSFDOMUtil.findFacet(_panelGridEle, "header"); //$NON-NLS-1$
        if (ele != null)
        {
            _panelGridEle.removeChild(ele);
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return this.toDesignSelection(this._panelGridEle);
    }
}
