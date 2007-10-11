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
package org.eclipse.jst.pagedesigner.commands;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.SubNodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * A command to switch the selection
 *
 */
public class SwitchSelectionCommand extends Command {
	IHTMLGraphicalViewer _viewer;

	/**
	 * @param viewer
	 */
	public SwitchSelectionCommand(IHTMLGraphicalViewer viewer) {
		_viewer = viewer;
	}

	public void execute() {
		if (_viewer.isInRangeMode()) {
			ISelection selection = _viewer.getSelection();
			if (selection instanceof DesignRange) {
				DesignPosition posStart = ((DesignRange) selection)
						.getStartPosition();
				DesignPosition posEnd = ((DesignRange) selection)
						.getEndPosition();
				if (EditValidateUtil.validPosition(posStart)
						&& EditValidateUtil.validPosition(posEnd)) {
					Node ancestor = EditModelQuery.getInstance()
							.getCommonAncestor(posStart.getContainerNode(),
									posEnd.getContainerNode());
					if (ancestor instanceof Text) {
						ancestor = ancestor.getParentNode();
					}
					if (ancestor instanceof Element) {
						EditPart part = Target.resolvePart(ancestor);
						if (part instanceof SubNodeEditPart) {
							_viewer.select(part);
						}
					}
				}
			}
		} else {
			List parts = _viewer.getSelectedEditParts();
			if (parts.size() > 0) {
				EditPart parent = ((EditPart) parts.get(0)).getParent();
				if (!(parent instanceof DocumentEditPart)) {
					_viewer.select(parent);
				}
			}
		}
	}
}
