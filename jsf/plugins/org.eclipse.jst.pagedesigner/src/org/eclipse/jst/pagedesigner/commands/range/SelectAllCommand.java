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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.gef.commands.Command;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingNavigationMediator;
import org.eclipse.jst.pagedesigner.validation.caret.JSFRootContainerPositionRule;
import org.eclipse.jst.pagedesigner.validation.caret.RootContainerPositionRule;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Called in response to a Ctrl-A-style select all action
 *
 */
public class SelectAllCommand extends Command {
	private IHTMLGraphicalViewer _viewer;

	/**
	 * @param label
	 * @param viewer
	 */
	public SelectAllCommand(String label, IHTMLGraphicalViewer viewer) {
		super(label);
		_viewer = viewer;
	}

	public void execute() {
		Node document = _viewer.getModel().getDocument();
		IMovementMediator validator = new InlineEditingNavigationMediator(
				new ActionData(ActionData.KEYBOARD_NAVAGATION, null));
		Node htmlRoot = RootContainerPositionRule
				.getBasicContainer((Document) document);
		Node jsfRoot = JSFRootContainerPositionRule
				.getBasicContainer((Document) document, 3);
		Node root;
		if (htmlRoot != null && jsfRoot != null) {
			if (EditModelQuery.isChild(htmlRoot, jsfRoot)) {
				root = htmlRoot;
			} else if (EditModelQuery.isChild(jsfRoot, htmlRoot)) {
				root = jsfRoot;
			} else {
				root = htmlRoot;
			}
		} else {
			if (htmlRoot != null) {
				root = htmlRoot;
			} else if (jsfRoot != null) {
				root = jsfRoot;
			} else {
				root = document;
			}
		}
		IDOMPosition position1, position2;
		// if (root.hasChildNodes())
		// {
		// Node first = root.getFirstChild();
		// position1 = new DOMRefPosition(first, false);
		// Node last = root.getLastChild();
		// position2 = new DOMRefPosition(last, true);
		// }
		// else
		// {
		position1 = new DOMPosition(root, 0);
		position2 = new DOMPosition(root, root.getChildNodes().getLength());
		// }
		if (!validator.isValidPosition(position1)) {
			position1 = EditHelper.moveToNextEditPosition(position1, true,
					validator);
		}
		if (!validator.isValidPosition(position2)) {
			position2 = EditHelper.moveToNextEditPosition(position2, false,
					validator);
		}
		if (EditValidateUtil.validPosition(position1)
				&& EditValidateUtil.validPosition(position2)) {
			_viewer.setRange(DOMPositionHelper.toDesignPosition(position1),
					DOMPositionHelper.toDesignPosition(position2));
		}
	}

}
