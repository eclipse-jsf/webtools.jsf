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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Move a node from one place to another place.
 * 
 * @author mengbo
 * @version 1.5
 */
public class MoveNodeCommand extends DesignerCommand {
	IDOMPosition _insertPosition;

	Node _originalNode;

	/**
	 * @param label
	 * @param viewer
	 */
	public MoveNodeCommand(IHTMLGraphicalViewer viewer,
			IDOMPosition insertionPoint, Node originalNode) {
		super(
				CommandResources.getString("MoveNodeCommand.Label.MoveNode"), viewer); //$NON-NLS-1$
		this._insertPosition = insertionPoint;
		this._originalNode = originalNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		Node originalParent = _originalNode.getParentNode();
		if (originalParent == null) {
			return;
		}
		// when remove the _originalNode, may affect the insertionPosition.
		if (originalParent == _insertPosition.getContainerNode()) {
			// under same parent, may affect it.
			int insertIndex = _insertPosition.getOffset();
			int nodeIndex = -1;
			NodeList list = originalParent.getChildNodes();
			for (int i = 0, length = list.getLength(); i < length; i++) {
				if (_originalNode == list.item(i)) {
					nodeIndex = i;
				}
			}
			if (nodeIndex == -1) {
				return; // should not happen.
			}
			if (insertIndex < nodeIndex) {
				_insertPosition = new DOMPosition(originalParent, insertIndex);
			} else if (insertIndex == nodeIndex || insertIndex == nodeIndex + 1) {
				// move to same position, do nothing.
				return;
			} else {
				_insertPosition = new DOMPosition(originalParent,
						insertIndex - 1);
			}
		}
		originalParent.removeChild(_originalNode);
		DOMUtil.insertNode(_insertPosition, _originalNode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(_originalNode);
	}
}
