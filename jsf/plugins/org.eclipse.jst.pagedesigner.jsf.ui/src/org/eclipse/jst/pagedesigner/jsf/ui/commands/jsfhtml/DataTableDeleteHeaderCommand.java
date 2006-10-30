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


/**
 * @author mengbo
 * @version 1.5
 */
public class DataTableDeleteHeaderCommand extends DesignerCommand
{
    /**
     * true means header,false means footer
     */
    boolean         _isHeader;
    private Element _dataTable;

    public DataTableDeleteHeaderCommand(IHTMLGraphicalViewer viewer, Element dataTable, boolean isHeader)
    {
        super(isHeader ? CommandResources.getString("DataTableDeleteHeaderCommand.Label.DeleteHeader") : //$NON-NLS-1$
        CommandResources.getString("DataTableDeleteHeaderCommand.Label.DeleteFooter"), viewer); //$NON-NLS-1$ 
        this._isHeader = isHeader;
        this._dataTable = dataTable;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._isHeader)
        {
            boolean hasHeader = (JSFDOMUtil.findFacet(this._dataTable, "header") != null); //$NON-NLS-1$
            if (!hasHeader)
            {
                return false;
            }
        }
        else
        {
            boolean hasFooter = (JSFDOMUtil.findFacet(this._dataTable, "footer") != null); //$NON-NLS-1$
            if (!hasFooter)
            {
                return false;
            }
        }
        return super.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        String type = (this._isHeader) ? "header" : "footer"; //$NON-NLS-1$ //$NON-NLS-2$
        Element ele = JSFDOMUtil.findFacet(this._dataTable, type);
        if (ele != null)
        {
            this._dataTable.removeChild(ele);
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
}
