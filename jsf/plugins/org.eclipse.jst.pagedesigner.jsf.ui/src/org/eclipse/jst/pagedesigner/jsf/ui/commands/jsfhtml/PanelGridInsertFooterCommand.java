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

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridInsertFooterCommand extends DesignerCommand
{
    private Element _panelGrid;

    public PanelGridInsertFooterCommand(IHTMLGraphicalViewer viewer, Element panelGrid)
    {
        super(CommandResources.getString("PanelGridInsertFooterCommand.Label.InsertFooter"), viewer); //$NON-NLS-1$
        this._panelGrid = panelGrid;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        boolean hasHeader = (JSFDOMUtil.findFacet(this._panelGrid, "footer") != null); //$NON-NLS-1$
        if (hasHeader)
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
        Document doc = this._panelGrid.getOwnerDocument();
        Element facet = doc.createElement(IJSFConstants.TAG_FACET);
        facet.setPrefix("f"); //$NON-NLS-1$
        facet.setAttribute(IJSFConstants.ATTR_NAME, "footer"); //$NON-NLS-1$
        Element outputText = doc.createElement(IJSFConstants.TAG_OUTPUTTEXT);
        outputText.setPrefix("h"); //$NON-NLS-1$
        outputText.setAttribute(IJSFConstants.ATTR_VALUE, "Footer"); //$NON-NLS-1$
        facet.appendChild(outputText);
        List list = JSFDOMUtil.getUIComponentChildren(this._panelGrid);
        if (list != null && list.size() > 0)
        {
            this._panelGrid.insertBefore(facet, (Element) list.get(0));
        }
        else
        {
            this._panelGrid.insertBefore(facet, null);
        }
        formatNode(this._panelGrid);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(this._panelGrid);
    }
}
