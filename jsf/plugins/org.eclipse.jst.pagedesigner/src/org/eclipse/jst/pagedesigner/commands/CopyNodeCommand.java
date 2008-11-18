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
import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.w3c.dom.Node;

/**
 * As copy operation won't change anything in the current document, so it is not
 * extending from DesignerCommand.
 * 
 * @author mengbo
 */
public class CopyNodeCommand extends Command {
	private IHTMLGraphicalViewer _viewer;

	/**
	 * @param viewer
	 */
	public CopyNodeCommand(IHTMLGraphicalViewer viewer) {
		super(""); //$NON-NLS-1$
		this._viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	public void execute() {
		List parts = _viewer.getSelectedEditParts();
		Vector result = new Vector();
		if (parts.size() > 0) {
			for (int i = 0, n = parts.size(); i < n; i++) {
				EditPart part = (EditPart) parts.get(i);
				Object model = part.getModel();
				if (model instanceof Node) {
					EditValidateUtil.validNode((Node) model);
					result.add(((Node) model).cloneNode(true));
				}
			}
			setClipboard(result);
		}
	}

	private void setClipboard(Vector result) {
		Node[] nodes = (Node[]) result.toArray(new Node[result.size()]);
		StringBuffer sb = new StringBuffer();
		for (int i = 0, size = nodes.length; i < size; i++) {
			DOMUtil.nodeToString(nodes[i], sb);
		}
		// TemplateTransfer.getInstance().setObject(result);
		Clipboard board = new Clipboard(_viewer.getControl().getDisplay());
		board.setContents(new Object[] { result, sb.toString() },
				new Transfer[] { TemplateTransfer.getInstance(),
						TextTransfer.getInstance() });
	}
}
