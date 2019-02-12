/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * This is when user control+mouse drag. Can also be used in other places.
 * 
 * @author mengbo
 * @version 1.5
 */
public class CloneNodeCommand extends DesignerCommand {
	IDOMPosition _insertPosition;

	Node _originalNode;

	Node _resultNode;

	/**
	 * @param viewer
	 * @param insertionPoint 
	 * @param originalNode 
	 */
	public CloneNodeCommand(IHTMLGraphicalViewer viewer,
			IDOMPosition insertionPoint, Node originalNode) {
		super(
				CommandResources.getString("CloneNodeCommand.Label.CloneNode"), viewer); //$NON-NLS-1$
		this._insertPosition = insertionPoint;
		this._originalNode = originalNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		Node newNode = DOMUtil.cloneNodeDeep(this.getDocument(), _originalNode);
		DOMUtil.insertNode(_insertPosition, newNode);
		_resultNode = newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(_resultNode);
	}

}
