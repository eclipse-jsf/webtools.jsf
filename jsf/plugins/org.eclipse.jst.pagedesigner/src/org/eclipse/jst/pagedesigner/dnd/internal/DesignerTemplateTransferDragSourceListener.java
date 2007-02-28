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
package org.eclipse.jst.pagedesigner.dnd.internal;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;

/**
 * @author mengbo
 */
public class DesignerTemplateTransferDragSourceListener extends
		TemplateTransferDragSourceListener {

	/**
	 * @param viewer
	 */
	public DesignerTemplateTransferDragSourceListener(EditPartViewer viewer) {
		super(viewer);
	}

	protected Object getTemplate() {
		Object object = super.getTemplate();
		if (object == null) {
			List selection = getViewer().getSelectedEditParts();
			if (selection.size() == 1) {
				EditPart editpart = (EditPart) getViewer()
						.getSelectedEditParts().get(0);
				Object model = editpart.getModel();
				if (model instanceof TagToolPaletteEntry) {
					return model;
				}
			}
		}
		return null;
	}
}
