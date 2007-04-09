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
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class CutNodeCommand extends DesignerCommand {
	private static final String COMMAND_LABEL = CommandResources
			.getString("CutNodeCommand.Label.CutNode"); //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		List parts = getViewer().getSelectedEditParts();
		Vector result = new Vector();
		if (parts.size() > 0) {
			for (int i = 0, n = parts.size(); i < n; i++) {
				EditPart part = (EditPart) parts.get(i);
				Object model = part.getModel();
				if (model instanceof Node) {
					EditValidateUtil.validNode((Node) model);
					Node parent = ((Node) model).getParentNode();
					model = parent.removeChild((Node) model);
					result.add(model);
				}
			}
			setClipboard(result);
		}
	}

	protected ISelection getAfterCommandDesignerSelection() {
		return null;
	}

	/**
	 * @param viewer
	 */
	public CutNodeCommand(IHTMLGraphicalViewer viewer) {
		super(COMMAND_LABEL, viewer);
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
