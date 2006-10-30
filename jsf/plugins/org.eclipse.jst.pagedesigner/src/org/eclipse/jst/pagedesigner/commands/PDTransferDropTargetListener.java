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

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemDescriptor;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.swt.dnd.Transfer;

/**
 * @author mengbo
 */
public class PDTransferDropTargetListener extends
		AbstractTransferDropTargetListener {
	/**
	 * @param viewer
	 * @param xfer
	 */
	public PDTransferDropTargetListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		ItemCreationRequest request = new ItemCreationRequest();
		PaletteItemDescriptor itemDescriptor = (PaletteItemDescriptor) TemplateTransfer
				.getInstance().getObject();
		request.setItemDescriptor(itemDescriptor);
		request.setLocation(getDropLocation());
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		ItemCreationRequest request = (ItemCreationRequest) getTargetRequest();
		PaletteItemDescriptor itemDescriptor = (PaletteItemDescriptor) TemplateTransfer
				.getInstance().getObject();
		request.setItemDescriptor(itemDescriptor);
		request.setLocation(getDropLocation());
	}

}
