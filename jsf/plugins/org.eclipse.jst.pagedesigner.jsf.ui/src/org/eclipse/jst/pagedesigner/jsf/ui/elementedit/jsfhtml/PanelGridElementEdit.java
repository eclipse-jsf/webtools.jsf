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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfhtml;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.actions.single.SelectEditPartAction;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.DeleteHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.InsertHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableDeleteHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridDeleteColumnCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridDeleteRowCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridInsertColumnCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridInsertFooterCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridInsertHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.PanelGridInsertRowCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.DeleteHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.InsertHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.util.PanelGridUtil;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.tableedit.DeleteRowColumnAction;
import org.eclipse.jst.pagedesigner.tableedit.InsertRowColumnAction;
import org.eclipse.jst.pagedesigner.tableedit.TableInsertRequest;
import org.eclipse.jst.pagedesigner.tableedit.TableResizeRequest;
import org.eclipse.jst.pagedesigner.tableedit.TableRowColumnDeleteRequest;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridElementEdit extends DefaultJSFHTMLElementEdit
{
    private final static Action  EMPTY_ACTION = new Action()
    {
        // TODO: why?
    };
    private final static int FAKE_INDEX = -10;

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager, org.w3c.dom.Element)
     */
    public void fillContextMenu(IMenuManager contextMenu, Element ele)
    {
        super.fillContextMenu(contextMenu, ele);

        final IMenuManager tableMenu = new MenuManager(JSFUIPlugin.getResourceString("ElementEdit.Submenu.PanelGrid"));//$NON-NLS-1$
        tableMenu.add(EMPTY_ACTION);
        final ElementEditPart gridEditPart = (ElementEditPart) ((IDOMElement) ele).getAdapterFor(EditPart.class);

        // ok, we passed the checking, now let's create the actions.
        tableMenu.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                tableMenu.removeAll();
                fillPanelGridMenu(gridEditPart, FAKE_INDEX, FAKE_INDEX, tableMenu);
            }
        });

        contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER, tableMenu);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContainerContextMenu(org.eclipse.jface.action.IMenuManager, org.eclipse.jst.pagedesigner.parts.ElementEditPart, org.eclipse.jst.pagedesigner.parts.NodeEditPart, org.eclipse.jface.viewers.ISelection)
     */
    public boolean fillContainerContextMenu(IMenuManager contextMenu, final ElementEditPart elePart,
            NodeEditPart nodePart, ISelection innerSelection)
    {
        Element table = (Element) elePart.getModel();
        Node node = (Node) nodePart.getModel();

        Element childEle = null;
        if (node instanceof Element)
        {
            childEle = (Element) node;
        }
        else
        {
            boolean hasElementParent = false;
            Node nodeBackup = node;
            while (!IJSFConstants.TAG_PANELGRID.equalsIgnoreCase(nodeBackup.getParentNode().getLocalName()))
            {
                nodeBackup = nodeBackup.getParentNode();
                if (nodeBackup instanceof Element)
                {
                    hasElementParent = true;
                    break;
                }
            }
            if (!hasElementParent)
            {
                return false;
            }
            childEle = (Element) nodeBackup;
        }

        PanelGridUtil util = new PanelGridUtil(table);
        final int cellRow = util.convertRowIndexFromDomToView(util.getDomRowIndex(childEle));
        final int cellColumn = util.getDomColumnIndex(childEle);

        final IMenuManager tableMenu = new MenuManager(JSFUIPlugin.getResourceString("ElementEdit.Submenu.PanelGrid"));//$NON-NLS-1$
        tableMenu.add(EMPTY_ACTION);

        // ok, we passed the checking, now let's create the actions.
        tableMenu.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                tableMenu.removeAll();
                fillPanelGridMenu(elePart, cellRow, cellColumn, tableMenu);
            }
        });

        contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER, tableMenu);
        return true;

    }

    /**
     * @param elePart
     * @param cellRow
     * @param cellColumn
     * @param tableMenu
     */
    private void fillPanelGridMenu(ElementEditPart elePart, int cellRow, int cellColumn, IMenuManager tableMenu)
    {
        SelectEditPartAction action = new SelectEditPartAction(JSFUIPlugin
                .getResourceString("ElementEdit.Submenu.PanelGrid.SelectTable"), elePart);//$NON-NLS-1$
        tableMenu.add(action);

        tableMenu.add(new Separator());

        {
            InsertRowColumnAction insertRowBeforeAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertRowBefore"), //$NON-NLS-1$
                    elePart, cellRow, true, true);
            tableMenu.add(insertRowBeforeAction);

            InsertRowColumnAction insertRowAfterAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertRowAfter"), //$NON-NLS-1$
                    elePart, cellRow, true, false);
            tableMenu.add(insertRowAfterAction);

            tableMenu.add(new Separator());
        }

        {
            InsertRowColumnAction insertColumnBeforeAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertColumnBefore"), //$NON-NLS-1$
                    elePart, cellColumn, false, true);
            tableMenu.add(insertColumnBeforeAction);

            InsertRowColumnAction insertColumnAfterAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertColumnAfter"), //$NON-NLS-1$
                    elePart, cellColumn, false, false);
            tableMenu.add(insertColumnAfterAction);

            tableMenu.add(new Separator());
        }

        {
            DeleteRowColumnAction deleteRowAction = new DeleteRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.DeleteRow"), //$NON-NLS-1$
                    elePart, cellRow, true);
            tableMenu.add(deleteRowAction);

            DeleteRowColumnAction deleteColumnAction = new DeleteRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.DeleteColumn"), //$NON-NLS-1$
                    elePart, cellColumn, false);
            tableMenu.add(deleteColumnAction);
            tableMenu.add(new Separator());
        }

        {
            InsertHeaderFooterAction headerAction = new InsertHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertHeader"), //$NON-NLS-1$
                    elePart, true);
            tableMenu.add(headerAction);

            InsertHeaderFooterAction footerAction = new InsertHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.InsertFooter"), //$NON-NLS-1$
                    elePart, false);
            tableMenu.add(footerAction);

            DeleteHeaderFooterAction delHeaderAction = new DeleteHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGird.DeleteHeader"), elePart, true);//$NON-NLS-1$
            tableMenu.add(delHeaderAction);

            DeleteHeaderFooterAction delFooterAction = new DeleteHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.PanelGrid.DeleteFooter"), elePart, false);//$NON-NLS-1$
            tableMenu.add(delFooterAction);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
     */
    public void createEditPolicies(ElementEditPart part)
    {
        part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PanelGridResizePolicy(part));
    }

    static class PanelGridResizePolicy extends ElementResizableEditPolicy
    {
        ElementEditPart _part;

        public PanelGridResizePolicy(ElementEditPart part)
        {
            _part = part;
        }

        /* (non-Javadoc)
         * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getCommand(org.eclipse.gef.Request)
         */
        public Command getCommand(Request request)
        {
            IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) _part.getViewer();
            Element panelGrid = (Element) _part.getIDOMNode();
            if (request instanceof TableResizeRequest)
            {
                // TODO: FIXME:
            }
            else if (request instanceof TableInsertRequest)
            {
                TableInsertRequest tableInsertRequest = (TableInsertRequest) request;
                if (tableInsertRequest.isRow())
                {
                    int index = tableInsertRequest.getIndex() + (tableInsertRequest.isBefore() ? 0 : 1);
                    return new PanelGridInsertRowCommand(viewer, panelGrid, index);
                }
                int index = tableInsertRequest.getIndex() + (tableInsertRequest.isBefore() ? 0 : 1);
                return new PanelGridInsertColumnCommand(viewer, panelGrid, index);
            }
            else if (request instanceof InsertHeaderFooterRequest)
            {
                InsertHeaderFooterRequest req = (InsertHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                if (isHeader)
                {
                    return new PanelGridInsertHeaderCommand(viewer, panelGrid);
                }
                return new PanelGridInsertFooterCommand(viewer, panelGrid);
            }
            else if (request instanceof DeleteHeaderFooterRequest)
            {
                DeleteHeaderFooterRequest req = (DeleteHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                return new DataTableDeleteHeaderCommand(viewer, panelGrid, isHeader);
            }
            else if (request instanceof TableRowColumnDeleteRequest)
            {
                TableRowColumnDeleteRequest deleteReq = (TableRowColumnDeleteRequest) request;
                if (deleteReq.isRow())
                {
                    return new PanelGridDeleteRowCommand(viewer, panelGrid, deleteReq.getIndex());
                }
                return new PanelGridDeleteColumnCommand(viewer, panelGrid, deleteReq.getIndex());
            }
            return super.getCommand(request);
        }
    }
}
