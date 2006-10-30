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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Delete a column from dataTable.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DataTableDeleteColumnCommand extends DesignerCommand
{

    private Element _dataTable;
    private int     _index;

    /**
     * @param label
     * @param viewer
     */
    public DataTableDeleteColumnCommand(IHTMLGraphicalViewer viewer, Element dataTable, int columnIndex)
    {
        super(CommandResources.getString("DataTableDeleteColumnCommand.Label.DeleteColumn"), viewer); //$NON-NLS-1$
        this._dataTable = dataTable;
        this._index = columnIndex;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._index < 0)
        {
            return false;
        }
        return super.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        NodeList children = _dataTable.getChildNodes();
        int index = 0;
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                if (index++ == this._index)
                {
                    _dataTable.removeChild(node);
                    return;
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(_dataTable);
    }
}
