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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class AlignSupport {

	private static final String[] ALIGN_VALUES = new String[] {
			PDPlugin.getResourceString("AlignSupport.ActionLabel.Left"), //$NON-NLS-1$
			PDPlugin.getResourceString("AlignSupport.ActionLabel.Center"), //$NON-NLS-1$
			PDPlugin.getResourceString("AlignSupport.ActionLabel.Right"), //$NON-NLS-1$
			PDPlugin.getResourceString("AlignSupport.ActionLabel.Justify") //$NON-NLS-1$ 
	};

	private static Element[] _nodes = null;

	/**
	 * @param menu
	 * @param viewer
	 */
	public static void createAlignActions(IMenuManager menu,
			IHTMLGraphicalViewer viewer) {
		for (int i = 0; i < ALIGN_VALUES.length; i++) {
			ParagraphStyleAction action = new ParagraphStyleAction(
					ALIGN_VALUES[i], _nodes[i], null, IAction.AS_CHECK_BOX);
			action.setViewer(viewer);
			menu.add(action);
		}
	}

	/**
	 * @param nodes
	 */
	public static void setAlignNodes(Element[] nodes) {
		if (_nodes != nodes) {
			_nodes = nodes;
		}
	}

}
