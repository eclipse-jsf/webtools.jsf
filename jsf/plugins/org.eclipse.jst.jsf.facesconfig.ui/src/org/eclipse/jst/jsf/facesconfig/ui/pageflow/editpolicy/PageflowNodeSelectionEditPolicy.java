/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

/**
 * Handles selection of Node. Primary selection is denoted by highlight and a
 * focus rectangle. Normal selection is denoted by highlight only.
 * 
 * @author xgzhang
 */
public class PageflowNodeSelectionEditPolicy extends NonResizableEditPolicy {

//	private PageflowNodeFigure getFigure() {
//		PageflowNodeEditPart part = (PageflowNodeEditPart) getHost();
//		return ((PageflowNodeFigure) part.getFigure());
//	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#hideSelection()
	 */
	protected void hideSelection() {
		super.hideSelection();
		// getFigure().setSelected(false);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	protected void showPrimarySelection() {
		super.showPrimarySelection();
		// getFigure().setSelected(true);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	protected void showSelection() {
		super.showSelection();
		// getFigure().setSelected(true);

	}
}
