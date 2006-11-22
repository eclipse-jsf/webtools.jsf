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
package org.eclipse.jst.pagedesigner.actions.container;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.ui.actions.ActionGroup;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class ContainerActionGroup extends ActionGroup {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		ISelection selection = this.getContext().getSelection();

		EditPart part = findCommonAncesterPart(selection);
		if (!(part instanceof NodeEditPart)) {
			return;
		}

		NodeEditPart original = (NodeEditPart) part;
		// start from the parent of part
		while (part.getParent() instanceof ElementEditPart) {
			ElementEditPart elementEditPart = (ElementEditPart) part
					.getParent();

			IElementEdit elementEdit = elementEditPart.getElementEdit();
			if (elementEdit != null) {
				boolean filled = elementEdit.fillContainerContextMenu(menu,
						elementEditPart, original, selection);
				if (filled) {
					break;
				}
			}

			part = part.getParent();
		}
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
				if (position.getOffset() == 0
						|| position.getContainerNode() instanceof Text) {
					return position.getContainerPart();
				}
                return position.getSiblingEditPart(false);
			}
            return RangeUtil.findCommonAncestor(range);
		} else {
			return null;
		}
	}
}
