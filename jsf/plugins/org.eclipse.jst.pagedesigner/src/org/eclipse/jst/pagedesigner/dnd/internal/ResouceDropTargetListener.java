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

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.jst.pagedesigner.dnd.LocalDropRequest;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;

/**
 * The drop target listener for DnD from Windows explorer.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ResouceDropTargetListener extends
		AbstractTransferDropTargetListener {

	/**
	 * @param viewer
	 */
	public ResouceDropTargetListener(EditPartViewer viewer) {
		super(viewer, FileTransfer.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		LocalDropRequest request = new LocalDropRequest();
		request.setLocation(getDropLocation());
		request.setLocalObject(getCurrentLocalObject());
		return request;
	}

	private Object getCurrentLocalObject() {
		Object result = null;
		try {
			Object data = ((FileTransfer) getTransfer())
					.nativeToJava(getCurrentEvent().currentDataType);
			if (data instanceof String[]) {
				result = ((String[]) data)[0];
			}
		} catch (Exception e) {
			// Don't know the tag type.
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		LocalDropRequest dropRequest = (LocalDropRequest) getTargetRequest();
		dropRequest.setLocation(getDropLocation());
		dropRequest.setLocalObject(getCurrentLocalObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#setCurrentEvent(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void setCurrentEvent(DropTargetEvent currentEvent) {
		super.setCurrentEvent(currentEvent);
		if (currentEvent != null) {
			if (currentEvent.detail != DND.DROP_NONE) {
				currentEvent.detail = DND.DROP_COPY;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetListener#dragOperationChanged(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dragOperationChanged(DropTargetEvent event) {
		// switch the insert or update
		if ((event.detail & (DND.DROP_COPY)) != 0) {
			LocalDropEditPolicy.setCheckUpdate(false);
		} else {
			LocalDropEditPolicy.setCheckUpdate(true);
		}
		super.dragOperationChanged(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void drop(DropTargetEvent event) {
		getViewer().getControl().setFocus();
		super.drop(event);
		LocalDropEditPolicy.setCheckUpdate(true);
	}

}
