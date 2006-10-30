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
package org.eclipse.jst.pagedesigner.validation.caret;

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Node;

/**
 * In some place, wen can't do inline editing, like the area between td, in
 * panelGrid, etc. In these places we can't place caret
 * 
 * @author mengbo
 */
public class IEPanelgridPositionRule extends DefaultPositionRule {
	public IEPanelgridPositionRule(IPositionMediator mediator,
			ActionData actionData) {
		super(mediator, actionData);
	}

	/**
	 * PanelGrid is not editable.
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.gef.EditPart)
	 */
	public boolean isEditable(Target target) {
		Node node = target.getNode();
		return !EditModelQuery.isChild(new String[] {
				IJSFConstants.TAG_PANELGRID, IJSFConstants.TAG_DATATABLE },
				node, false, false);
	}

	public boolean hasEditableArea(Target target) {
		Node node = target.getNode();
		return !EditModelQuery.isChild(new String[] {
				IJSFConstants.TAG_PANELGRID, IJSFConstants.TAG_DATATABLE },
				node, false, false);
	}
}
