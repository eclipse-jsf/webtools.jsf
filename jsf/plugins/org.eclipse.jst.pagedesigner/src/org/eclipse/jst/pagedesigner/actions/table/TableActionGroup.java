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
package org.eclipse.jst.pagedesigner.actions.table;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.actions.single.SelectEditPartAction;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.tableedit.DeleteRowColumnAction;
import org.eclipse.jst.pagedesigner.tableedit.InsertRowColumnAction;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * 
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TableActionGroup extends ActionGroup {
	// TODO: I think there's a pattern emerging...
    private final static Action action = new Action() {
        // create an empty no-op
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		ISelection selection = this.getContext().getSelection();

		EditPart part = findCommonAncesterPart(selection);
		if (part == null) {
			return;
		}

		TableOperationContext context = TableOperationContext
				.getTableOperationContext(part);
		if (context == null) {
			return;
		}

		final ElementEditPart tablePart = context.getTablePart();

		if (tablePart == null || !supportTableActions(tablePart)) {
			return;
		}

		final int cellRow = context.getRowIndex();
		final int cellColumn = context.getColumnIndex();

		String tag = ((IDOMElement) tablePart.getIDOMNode()).getLocalName();
		final IMenuManager tableMenu = new MenuManager(tag);

		// ok, we passed the checking, now let's create the actions.
		tableMenu.add(action);
		tableMenu.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				tableMenu.removeAll();
				fillTableMenu(tablePart, cellRow, cellColumn, tableMenu);
			}
		});
		menu.appendToGroup(PageDesignerActionConstants.GROUP_CONTAINER,
				tableMenu);
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
				PDPlugin
						.getResourceString("TableActionGroup.Submenu.SelectTable"), tablePart);//$NON-NLS-1$
		tableMenu.add(action1);

		tableMenu.add(new Separator());

		{
			InsertRowColumnAction insertRowBeforeAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.InsertRowBefore"),//$NON-NLS-1$
					tablePart, cellRow, true, true);
			tableMenu.add(insertRowBeforeAction);

			InsertRowColumnAction insertRowAfterAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.InsertRowAfter"),//$NON-NLS-1$
					tablePart, cellRow, true, false);
			tableMenu.add(insertRowAfterAction);

			tableMenu.add(new Separator());
		}

		{
			InsertRowColumnAction insertColumnBeforeAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.InsertColumnBefore"),//$NON-NLS-1$
					tablePart, cellColumn, true, true);
			tableMenu.add(insertColumnBeforeAction);

			InsertRowColumnAction insertColumnAfterAction = new InsertRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.InsertColumnAfter"),//$NON-NLS-1$
					tablePart, cellColumn, true, false);
			tableMenu.add(insertColumnAfterAction);

			tableMenu.add(new Separator());
		}

		{
			DeleteRowColumnAction deleteRowAction = new DeleteRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.DeleteRow"),//$NON-NLS-1$
					tablePart, cellRow, true);
			tableMenu.add(deleteRowAction);

			DeleteRowColumnAction deleteColumnAction = new DeleteRowColumnAction(
					PDPlugin
							.getResourceString("TableActionGroup.Submenu.DeleteColumn"),//$NON-NLS-1$
					tablePart, cellColumn, false);
			tableMenu.add(deleteColumnAction);
		}
	}

	/**
	 * @param tablePart
	 * @return
	 */
	private boolean supportTableActions(ElementEditPart tablePart) {
	    // TODO: what's the point of this method?
        // it's only used in one place and always resolves to the same value..
		return true;
	}

	/**
	 * Give a selection, find a single common container node as start for table
	 * related operations.
	 * 
	 * @param selection
	 * @return
	 */
	private EditPart findCommonAncesterPart(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structsel = (IStructuredSelection) selection;
			if (structsel.size() != 1) {
				return null;
			} else if (structsel.getFirstElement() instanceof EditPart) {
				return (EditPart) structsel.getFirstElement();
			} else {
				return null;
			}
		} else if (selection instanceof DesignRange) {
			DesignRange range = (DesignRange) selection;
			if (!range.isValid()) {
				return null;
			}
			if (range.isEmpty()) {
				DesignPosition position = range.getStartPosition();
				if (position.getOffset() == 0) {
					return position.getContainerPart();
				}
                return position.getSiblingEditPart(true);
			}
            return RangeUtil.findCommonAncestor(range);
		} else {
			return null;
		}
	}

}
