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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.parts.SubNodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingNavigationMediator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class DeleteNodeCommand extends DesignerCommand {
	private static final String COMMAND_LABEL = CommandResources
			.getString("DeleteNodeCommand.Label.DeleteNode"); //$NON-NLS-1$

	private DesignRange _prevRange;

	/**
	 * @param viewer
	 */
	public DeleteNodeCommand(IHTMLGraphicalViewer viewer) {
		super(COMMAND_LABEL, viewer);
	}

	protected void preExecute() {
		_prevRange = _viewer.getRangeSelection();
		DesignPosition position = findObjectModePosition();
		_prevRange = new DesignRange(position, position);
		super.preExecute();
	}

	private DesignPosition findObjectModePosition() {
		DesignPosition result = null;
		IMovementMediator validator = new InlineEditingNavigationMediator(
				new ActionData(ActionData.KEYBOARD_NAVAGATION, null));
		DesignPosition position = getCurrentObjectPosition();
		if (position != null) {
			if (!validator.isValidPosition(position)) {
				result = null;
			} else {
				result = position;
			}
		}
		return result;
	}

	private DesignPosition getCurrentObjectPosition() {
		DesignRange result = null;
		if (_viewer.isInRangeMode()) {
			result = _viewer.getRangeSelection();
		} else {
			List parts = _viewer.getSelectedEditParts();
			if (parts.size() > 0) {
				EditPart selection = (EditPart) parts.get(0);
				if (selection instanceof SubNodeEditPart) {
					DesignPosition position = new DesignRefPosition(selection,
							false);
					position = DOMPositionHelper.toDesignPosition(EditHelper
							.ensureDOMPosition(DOMPositionHelper
									.toDOMPosition(position)));
					result = new DesignRange(position, position);
				}
			}
		}
		return result != null && result.isValid() ? result.getEndPosition()
				: null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		List parts = getViewer().getSelectedEditParts();
		for (int i = 0, n = parts.size(); i < n; i++) {
			EditPart part = (EditPart) parts.get(i);
			Object model = part.getModel();
			if (model instanceof Node) {
				EditValidateUtil.validNode((Node) model);
				Node parent = ((Node) model).getParentNode();
				model = parent.removeChild((Node) model);
			}
		}
	}

	protected ISelection getAfterCommandDesignerSelection() {
		if (_prevRange.isValid()) {
			return _prevRange;
		}
        return null;
	}
}
