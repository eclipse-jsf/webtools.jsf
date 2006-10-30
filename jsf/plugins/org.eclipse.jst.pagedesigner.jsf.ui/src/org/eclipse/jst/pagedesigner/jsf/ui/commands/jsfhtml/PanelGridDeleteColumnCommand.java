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
 * This is for delete a column in panelGrid
 * 
 * @author mengbo
 * @version 1.5
 */
public class PanelGridDeleteColumnCommand extends DesignerCommand
{

    private int     _columnIndex;
    private Element _panelGridEle;

    /**
     * @param label
     * @param viewer
     */
    public PanelGridDeleteColumnCommand(IHTMLGraphicalViewer viewer, Element panelGrid, int columnIndex)
    {
        super(CommandResources.getString("PanelGridDeleteColumnCommand.Label.DeleteColumn"), viewer); //$NON-NLS-1$
        this._panelGridEle = panelGrid;
        this._columnIndex = columnIndex;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        //if user chose header or footer,then disable the delete column action
        if (this._columnIndex < 0)
        {
            return false;
        }
        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, "columns", 1); //$NON-NLS-1$
        if (columns < 1)
        {
            columns = 1;
        }
        return columns > 1;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, "columns", 1); //$NON-NLS-1$
        if (columns < 1)
        {
            columns = 1;
        }

        if (_columnIndex >= columns)
        {
            // should not happen
            // TODO: log.
            return;
        }
        List children = JSFDOMUtil.getUIComponentChildren(_panelGridEle);
        List toRemove = new ArrayList();
        for (int i = _columnIndex; i < children.size(); i += columns)
        {
            toRemove.add(children.get(i));
        }
        for (int i = 0, size = toRemove.size(); i < size; i++)
        {
            _panelGridEle.removeChild((Node) toRemove.get(i));
        }

        _panelGridEle.setAttribute("columns", String.valueOf(columns - 1)); //$NON-NLS-1$
        formatNode(_panelGridEle);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(this._panelGridEle);
    }
}
