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
package org.eclipse.jst.pagedesigner.parts;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jst.pagedesigner.dnd.LocalDropRequest;
import org.eclipse.jst.pagedesigner.dnd.internal.LocalDropEditPolicy;
import org.eclipse.jst.pagedesigner.editpolicies.DragMoveEditPolicy;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationEditPolicy;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.tools.RangeDragTracker;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 */
public abstract class NodeEditPart extends AbstractGraphicalEditPart implements
		INodeAdapter {

	private IDOMDocument _destDocument;

	/**
	 * this method is called from the HTMLEditPartsFactory directly after the
	 * part's creation.
	 * 
	 * @param doc
	 */
	public void setDestDocumentForDesign(IDOMDocument doc) {
		this._destDocument = doc;
	}

	public IDOMDocument getDestDocumentForDesign() {
		if (this._destDocument == null) {
			return (IDOMDocument) this.getIDOMNode().getOwnerDocument();
		}
        return this._destDocument;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(LocalDropRequest.REQ_LOCAL_DROP,
				new LocalDropEditPolicy());
		installEditPolicy(ItemCreationRequest.REQ_ITEM_CREATION,
				new ItemCreationEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new DragMoveEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.sse.model.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type == EditPart.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#addNotify()
	 */
	public void addNotify() {
		Object obj = getModel();
		if (obj instanceof INodeNotifier) {
			((INodeNotifier) obj).addAdapter(this);
		}
		super.addNotify();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#removeNotify()
	 */
	public void removeNotify() {
		super.removeNotify();
		Object obj = getModel();
		if (obj instanceof INodeNotifier) {
			((INodeNotifier) obj).removeAdapter(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		LocationRequest hoverRequest = new LocationRequest();
		hoverRequest.setType(RequestConstants.REQ_SELECTION_HOVER);
		this.eraseTargetFeedback(hoverRequest);
		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		Object obj = getModel();
		if (key == IPropertySource.class) {
			if (obj instanceof INodeNotifier) {
				return ((INodeNotifier) obj)
						.getAdapterFor(IPropertySource.class);
			}
		}

		if (obj instanceof IAdaptable) {
			Object ret = ((IAdaptable) obj).getAdapter(key);
			if (ret != null)
				return ret;
		}
		return super.getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
	 */
	public DragTracker getDragTracker(Request request) {
		return new RangeDragTracker(this);
	}

	/**
	 * @return
	 */
	public IDOMNode getIDOMNode() {
		return ((IDOMNode) getModel());
	}

	/**
	 * if a EditPart don't support caret inside it, and don't can't have child
	 * edit part, then we call it as a widget.
	 * 
	 * @return
	 */
	public boolean isWidget() {
		return false; // child class must override.
	}

	/**
	 * whether this EditPart allow the selection range to have one edge in the
	 * edit part and one edge outside the edit part.
	 * 
	 * @return
	 */
	public boolean allowSelectionRangeAcross() {
		return true;
	}

	/**
	 * @return
	 */
	public boolean isResizable() {
		return false;
	}
}
