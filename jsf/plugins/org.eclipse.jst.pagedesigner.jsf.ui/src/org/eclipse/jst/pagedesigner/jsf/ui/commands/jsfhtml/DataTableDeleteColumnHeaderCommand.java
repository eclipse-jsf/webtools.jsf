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
 * @author mengbo
 * @version 1.5
 */
public class DataTableDeleteColumnHeaderCommand extends DesignerCommand
{
    boolean         _header;   // true means header, false means footer.
    private Element _dataTable;

    /**
     * @param viewer
     * @param dataTable 
     * @param header 
     */
    public DataTableDeleteColumnHeaderCommand(IHTMLGraphicalViewer viewer, Element dataTable, boolean header)
    {
        super(header ? CommandResources.getString("DataTableDeleteColumnHeaderCommand.Label.DeleteColumnHeader") : //$NON-NLS-1$
        CommandResources.getString("DataTableDeleteColumnHeaderCommand.Label.DeleteColumnFooter"), viewer); //$NON-NLS-1$ 
        this._header = header;
        this._dataTable = dataTable;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        int num = 0;
        NodeList children = _dataTable.getChildNodes();
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                Element column = (Element) node;
                if (hasHeader(column))
                {
                    num++;
                    break;
                }
            }
        }
        if (num == 0)
        {
            return false;
        }
        return super.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        NodeList children = _dataTable.getChildNodes();
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                Element column = (Element) node;
                if (hasHeader(column))
                {
                    Element hf = JSFDOMUtil.findFacet(column, _header ? "header" : "footer"); //$NON-NLS-1$ //$NON-NLS-2$
                    column.removeChild(hf);
                }
            }
        }
        formatNode(this._dataTable);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(_dataTable);
    }

    /**
     * @param column
     * @param _header2
     * @return
     */
    private boolean hasHeader(Element column)
    {
        return JSFDOMUtil.findFacet(column, _header ? "header" : "footer") != null; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
