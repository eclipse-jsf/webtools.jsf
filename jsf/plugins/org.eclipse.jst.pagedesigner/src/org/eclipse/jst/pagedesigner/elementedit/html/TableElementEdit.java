/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit.html;

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
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.actions.single.SelectEditPartAction;
import org.eclipse.jst.pagedesigner.commands.html.TableDeleteColumnCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableDeleteHeaderFooterCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableDeleteRowCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableInsertColumnCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableInsertHeaderFooterCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableInsertRowCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableResizeColumnCommand;
import org.eclipse.jst.pagedesigner.commands.html.TableResizeRowCommand;
import org.eclipse.jst.pagedesigner.dom.html.TableChildElementPosition;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.tableedit.DeleteHeaderFooterAction;
import org.eclipse.jst.pagedesigner.tableedit.DeleteHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.tableedit.DeleteRowColumnAction;
import org.eclipse.jst.pagedesigner.tableedit.InsertHeaderFooterAction;
import org.eclipse.jst.pagedesigner.tableedit.InsertHeaderFooterRequest;
import org.eclipse.jst.pagedesigner.tableedit.InsertRowColumnAction;
import org.eclipse.jst.pagedesigner.tableedit.TableInsertRequest;
import org.eclipse.jst.pagedesigner.tableedit.TableResizableEditPolicy;
import org.eclipse.jst.pagedesigner.tableedit.TableResizeRequest;
import org.eclipse.jst.pagedesigner.tableedit.TableRowColumnDeleteRequest;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableElementEdit extends AbstractElementEdit {

	private final static Action action = new Action() {
        // TODO: what's this for?
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager,
	 *      org.w3c.dom.Element)
	 */
	public void fillContextMenu(IMenuManager contextMenu, Element ele) {

		final ElementEditPart tablePart =
			(ElementEditPart) ((IDOMElement) ele).getAdapterFor(EditPart.class);

		final Element table = (Element) tablePart.getModel();

		final TableChildElementPosition position = new TableUtil(table).getPosition((Node)null);
		final int cellRow = position.getRowIndex();
		final int cellColumn = position.getColumnIndex();

		final IMenuManager tableMenu =
			new MenuManager(PDPlugin.getResourceString("ElementEdit.Submenu.Table")); //$NON-NLS-1$
		tableMenu.add(action);
		tableMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				tableMenu.removeAll();
				fillTableMenu(tablePart, cellRow, cellColumn, tableMenu);
			}
		});

		contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER, tableMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#fillContainerContextMenu(org.eclipse.jface.action.IMenuManager,
	 *      org.eclipse.jst.pagedesigner.parts.ElementEditPart,
	 *      org.eclipse.jst.pagedesigner.parts.NodeEditPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public boolean fillContainerContextMenu(
			IMenuManager contextMenu,
			final ElementEditPart tablePart,
			NodeEditPart nodePart,
			ISelection innerSelection) {

		super.fillContainerContextMenu(contextMenu, tablePart, nodePart, innerSelection);

		final Element table = (Element) tablePart.getModel();
		final Node node = (Node) nodePart.getModel();

		final TableChildElementPosition position = new TableUtil(table).getPosition(node);
		final int cellRow = position.getRowIndex();
		final int cellColumn = position.getColumnIndex();

		final IMenuManager tableMenu =
			new MenuManager(PDPlugin.getResourceString("ElementEdit.Submenu.Table")); //$NON-NLS-1$
		tableMenu.add(action);
		tableMenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				tableMenu.removeAll();
				fillTableMenu(tablePart, cellRow, cellColumn, tableMenu);
			}
		});

		contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER, tableMenu);
		return true;
	}

	/**
	 * @param tablePart
	 * @param cellRow
	 * @param cellColumn
	 * @param tableMenu
	 */
	private void fillTableMenu(ElementEditPart tablePart, int cellRow,
			int cellColumn, IMenuManager tableMenu) {
		SelectEditPartAction action1 = SelectEditPartAction.create(
				PDPlugin.getResourceString("ElementEdit.Submenu.SelectTable"), tablePart);//$NON-NLS-1$
		tableMenu.add(action1);

		tableMenu.add(new Separator());

		{
			InsertRowColumnAction insertRowBeforeAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertRowBefore"),//$NON-NLS-1$
					tablePart, cellRow, true, true);
			tableMenu.add(insertRowBeforeAction);

			InsertRowColumnAction insertRowAfterAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertRowAfter"),//$NON-NLS-1$
					tablePart, cellRow, true, false);
			tableMenu.add(insertRowAfterAction);

			tableMenu.add(new Separator());
		}

		{
			InsertRowColumnAction insertColumnBeforeAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertColumnBefore"),//$NON-NLS-1$
					tablePart, cellColumn, false, true);
			tableMenu.add(insertColumnBeforeAction);

			InsertRowColumnAction insertColumnAfterAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertColumnAfter"),//$NON-NLS-1$
					tablePart, cellColumn, false, false);
			tableMenu.add(insertColumnAfterAction);

			tableMenu.add(new Separator());
		}

		{
			DeleteRowColumnAction deleteRowAction = new DeleteRowColumnAction(
					PDPlugin.getResourceString("ElementEdit.Submenu.DeleteRow"),//$NON-NLS-1$
					tablePart, cellRow, true);
			tableMenu.add(deleteRowAction);

			DeleteRowColumnAction deleteColumnAction = new DeleteRowColumnAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.DeleteColumn"),//$NON-NLS-1$
					tablePart, cellColumn, false);
			tableMenu.add(deleteColumnAction);
			tableMenu.add(new Separator());
		}

		{
			InsertHeaderFooterAction headerAction = new InsertHeaderFooterAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertHeader"), tablePart, true);//$NON-NLS-1$
			tableMenu.add(headerAction);
			InsertHeaderFooterAction footerAction = new InsertHeaderFooterAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.InsertFooter"), tablePart, false);//$NON-NLS-1$
			tableMenu.add(footerAction);
			DeleteHeaderFooterAction delHeaderAction = new DeleteHeaderFooterAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.DeleteHeader"), tablePart, true);//$NON-NLS-1$
			tableMenu.add(delHeaderAction);
			DeleteHeaderFooterAction delFooterAction = new DeleteHeaderFooterAction(
					PDPlugin
							.getResourceString("ElementEdit.Submenu.DeleteFooter"), tablePart, false);//$NON-NLS-1$
			tableMenu.add(delFooterAction);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
	 */
	public void createEditPolicies(ElementEditPart part) {
		part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
				new TableResizePolicy(part));
	}

	static class TableResizePolicy extends TableResizableEditPolicy {
		ElementEditPart _part;

		/**
		 * @param part
		 */
		public TableResizePolicy(ElementEditPart part) {
			_part = part;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getCommand(org.eclipse.gef.Request)
		 */
		public Command getCommand(Request request) {
			IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) _part
					.getViewer();
			Element table = (Element) _part.getIDOMNode();
			if (request instanceof TableResizeRequest) {
				TableResizeRequest trq = (TableResizeRequest) request;
				if (trq.isRow()) {
					return new TableResizeRowCommand(viewer, table, trq
							.getIndex(), trq.getDelta());
				}
                return new TableResizeColumnCommand(viewer, table, trq
                		.getIndex(), trq.getDelta());
			} else if (request instanceof TableInsertRequest) {
				TableInsertRequest tableInsertRequest = (TableInsertRequest) request;
				int index = tableInsertRequest.getIndex()
						+ (tableInsertRequest.isBefore() ? 0 : 1);
				if (tableInsertRequest.isRow()) {
					return new TableInsertRowCommand(viewer, table, index,
							tableInsertRequest.isBefore());
				}
                return new TableInsertColumnCommand(viewer, table, index);
			} else if (request instanceof TableRowColumnDeleteRequest) {
				TableRowColumnDeleteRequest deleteReq = (TableRowColumnDeleteRequest) request;
				if (deleteReq.isRow()) {
					return new TableDeleteRowCommand(viewer, table, deleteReq
							.getIndex());
				}
                return new TableDeleteColumnCommand(viewer, table,
                		deleteReq.getIndex());
			} else if (request instanceof InsertHeaderFooterRequest) {
				InsertHeaderFooterRequest hfRequest = (InsertHeaderFooterRequest) request;
				return new TableInsertHeaderFooterCommand(viewer, table,
						hfRequest.isHeader());
			} else if (request instanceof DeleteHeaderFooterRequest) {
				DeleteHeaderFooterRequest hfRequest = (DeleteHeaderFooterRequest) request;
				return new TableDeleteHeaderFooterCommand(viewer, table,
						hfRequest.isHeader());
			}
			return super.getCommand(request);
		}
	}
}
