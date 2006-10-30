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

import org.eclipse.jst.pagedesigner.actions.single.SelectEditPartAction;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.DeleteHColumnHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.DeleteHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.InsertHColumnHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.InsertHeaderFooterAction;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableDeleteColumnCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableDeleteColumnHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableDeleteHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableInsertColumnCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableInsertColumnHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml.DataTableInsertHeaderCommand;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.DeleteHColumnHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.DeleteHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.InsertHColumnHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.InsertHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.util.DataTableUtil;
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
public class DataTableElementEdit extends DefaultJSFHTMLElementEdit
{
    public static Action     action     = new Action()
                                        {
                                        };
    private final static int FAKE_INDEX = -10;

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager, org.w3c.dom.Element)
     */
    public void fillContextMenu(IMenuManager contextMenu, Element ele)
    {
        super.fillContextMenu(contextMenu, ele);

        final IMenuManager tableMenu = new MenuManager(JSFUIPlugin.getResourceString("ElementEdit.Submenu.DataTable"));//$NON-NLS-1$
        final ElementEditPart editPart = (ElementEditPart) ((IDOMElement) ele).getAdapterFor(EditPart.class);
        // ok, we passed the checking, now let's create the actions.
        tableMenu.add(action);
        tableMenu.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                tableMenu.removeAll();
                fillTableMenu(editPart, FAKE_INDEX, tableMenu);
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

        DataTableUtil util = new DataTableUtil(table);
        Element columnParent = util.findHColumnParent(node);

        final int cellColumn = util.getColumnIndex(columnParent);

        final IMenuManager tableMenu = new MenuManager(JSFUIPlugin.getResourceString("ElementEdit.Submenu.DataTable"));//$NON-NLS-1$

        // ok, we passed the checking, now let's create the actions.
        tableMenu.add(action);
        tableMenu.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                tableMenu.removeAll();
                fillTableMenu(elePart, cellColumn, tableMenu);
            }
        });

        contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER, tableMenu);
        return true;

    }

    /**
     * @param elePart
     * @param cellColumn
     * @param tableMenu
     */
    private void fillTableMenu(ElementEditPart elePart, int cellColumn, IMenuManager tableMenu)
    {
        SelectEditPartAction action = new SelectEditPartAction(JSFUIPlugin
                .getResourceString("ElementEdit.Submenu.DataTable.SelectTable"), elePart);//$NON-NLS-1$
        tableMenu.add(action);

        tableMenu.add(new Separator());

        {
            InsertRowColumnAction insertColumnBeforeAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DataTable.InsertColumnBefore"), //$NON-NLS-1$
                    elePart, cellColumn, false, true);
            tableMenu.add(insertColumnBeforeAction);

            InsertRowColumnAction insertColumnAfterAction = new InsertRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DataTable.InsertColumnAfter"),//$NON-NLS-1$ 
                    elePart, cellColumn, false, false);
            tableMenu.add(insertColumnAfterAction);

            tableMenu.add(new Separator());
        }

        {
            DeleteRowColumnAction deleteColumnAction = new DeleteRowColumnAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DataTable.DeleteColumn"), //$NON-NLS-1$
                    elePart, cellColumn, false);
            tableMenu.add(deleteColumnAction);
            tableMenu.add(new Separator());
        }

        {
            InsertHeaderFooterAction headerAction = new InsertHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.InsertTableHeader"), elePart, true);//$NON-NLS-1$
            tableMenu.add(headerAction);

            InsertHeaderFooterAction footerAction = new InsertHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.InsertTableFooter"), elePart, false);//$NON-NLS-1$
            tableMenu.add(footerAction);

            DeleteHeaderFooterAction delHeaderAction = new DeleteHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DeleteTableHeader"), elePart, true);//$NON-NLS-1$
            tableMenu.add(delHeaderAction);

            DeleteHeaderFooterAction delFooterAction = new DeleteHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DeleteTableFooter"), elePart, false);//$NON-NLS-1$
            tableMenu.add(delFooterAction);
            tableMenu.add(new Separator());
        }

        {
            InsertHColumnHeaderFooterAction hColHeaderAction = new InsertHColumnHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.InsertColumnHeader"),//$NON-NLS-1$
                    elePart, true);
            tableMenu.add(hColHeaderAction);

            InsertHColumnHeaderFooterAction hColFooterAction = new InsertHColumnHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.InsertColumnFooter"),//$NON-NLS-1$
                    elePart, false);
            tableMenu.add(hColFooterAction);

            DeleteHColumnHeaderFooterAction delColHeader = new DeleteHColumnHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DeleteColumnHeader"),//$NON-NLS-1$
                    elePart, true);
            tableMenu.add(delColHeader);

            DeleteHColumnHeaderFooterAction delColFooter = new DeleteHColumnHeaderFooterAction(JSFUIPlugin
                    .getResourceString("ElementEdit.Submenu.DeleteColumnFooter"),//$NON-NLS-1$
                    elePart, false);
            tableMenu.add(delColFooter);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
     */
    public void createEditPolicies(ElementEditPart part)
    {
        part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new DataTableResizePolicy(part));
    }

    static class DataTableResizePolicy extends ElementResizableEditPolicy
    {
        ElementEditPart _part;

        public DataTableResizePolicy(ElementEditPart part)
        {
            _part = part;
        }

        /* (non-Javadoc)
         * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getCommand(org.eclipse.gef.Request)
         */
        public Command getCommand(Request request)
        {
            IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) _part.getViewer();
            Element dataTable = (Element) _part.getIDOMNode();
            if (request instanceof TableResizeRequest)
            {
                // TODO: FIXME:
            }
            else if (request instanceof TableInsertRequest)
            {
                TableInsertRequest tableInsertRequest = (TableInsertRequest) request;
                if (tableInsertRequest.isRow())
                {
                    // don't support insert row.
                    return null;
                }
                else
                {
                    int index = tableInsertRequest.getIndex() + (tableInsertRequest.isBefore() ? 0 : 1);
                    return new DataTableInsertColumnCommand(viewer, dataTable, index);
                }
            }
            else if (request instanceof InsertHeaderFooterRequest)
            {
                InsertHeaderFooterRequest req = (InsertHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                return new DataTableInsertHeaderCommand(viewer, dataTable, isHeader);
            }
            else if (request instanceof DeleteHeaderFooterRequest)
            {
                DeleteHeaderFooterRequest req = (DeleteHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                return new DataTableDeleteHeaderCommand(viewer, dataTable, isHeader);
            }
            else if (request instanceof InsertHColumnHeaderFooterRequest)
            {
                InsertHColumnHeaderFooterRequest req = (InsertHColumnHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                return new DataTableInsertColumnHeaderCommand(viewer, dataTable, isHeader);
            }
            else if (request instanceof DeleteHColumnHeaderFooterRequest)
            {
                DeleteHColumnHeaderFooterRequest req = (DeleteHColumnHeaderFooterRequest) request;
                boolean isHeader = req.isHeader();
                return new DataTableDeleteColumnHeaderCommand(viewer, dataTable, isHeader);
            }
            else if (request instanceof TableRowColumnDeleteRequest)
            {
                TableRowColumnDeleteRequest deleteReq = (TableRowColumnDeleteRequest) request;
                if (deleteReq.isRow())
                {
                    return null;
                }
                else
                {
                    return new DataTableDeleteColumnCommand(viewer, dataTable, deleteReq.getIndex());
                }
            }
            return super.getCommand(request);
        }
    }
}
