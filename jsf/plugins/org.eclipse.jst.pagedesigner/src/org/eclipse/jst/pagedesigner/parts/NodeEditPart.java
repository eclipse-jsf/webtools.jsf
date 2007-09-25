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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.dnd.LocalDropRequest;
import org.eclipse.jst.pagedesigner.dnd.internal.LocalDropEditPolicy;
import org.eclipse.jst.pagedesigner.editpolicies.DragMoveEditPolicy;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationEditPolicy;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.tools.RangeDragTracker;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public abstract class NodeEditPart extends AbstractGraphicalEditPart implements
		INodeAdapter {

	private IDOMDocument _destDocument;
	private boolean      _isDragActive;
    
	/**
	 * this method is called from the HTMLEditPartsFactory directly after the
	 * part's creation.
	 * 
	 * @param doc
	 */
	public void setDestDocumentForDesign(IDOMDocument doc) {
		this._destDocument = doc;
	}

	/**
	 * @return to owner document of this part's model node
	 */
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
	 * @return the model node as an IDOMNode
	 */
	public IDOMNode getIDOMNode() {
		return ((IDOMNode) getModel());
	}

    /**
     * @return the model node as a node
     */
    public Node getDOMNode()
    {
        return ((Node)getModel());
    }
    
    /**
     * @return if this edit part's model is an Element, then returns
     * the tag identifier. Otherwise, null.
     */
    public TagIdentifier getTagIdentifier()
    {
        Node node = getDOMNode();
        
        if (node instanceof Element)
        {
            return TagIdentifierFactory.createDocumentTagWrapper(((Element)node));
        }
        
        return null;
    }
    
	/**
	 * if a EditPart don't support caret inside it, and don't can't have child
	 * edit part, then we call it as a widget.
	 * 
	 * @return true if this part represents a widget
	 */
	public boolean isWidget() {
		return false; // child class must override.
	}

	/**
	 * whether this EditPart allow the selection range to have one edge in the
	 * edit part and one edge outside the edit part.
	 * 
	 * @return true if selection range across is allowed
	 */
	public boolean allowSelectionRangeAcross() {
		return true;
	}

	/**
	 * @return true if this part is resizable
	 */
	public boolean isResizable() {
		return false;
	}
    
    /**
     * @return true if drag is active on this part
     */
    public boolean isDragActive() {
        return _isDragActive;
    }
    
    /**
     * @param newValue
     */
    public void setDragActive(boolean newValue)
    {
        _isDragActive = newValue;
    }
    
    /**
     * @param mouseLocation
     * @return this edit part's cursor or null if this edit part
     * does not wish to specify a specific cursor (the default
     * should be used).  
     * Note that this is only called when no drag tracker is active,
     * when the mouse enters the edit part with the RangeSelectionTool
     * and the current state is INITIAL.  This allows the editpart to
     * specify a custom mouse-over tool cursor
     * TODO: perhaps getCursor is not a specific enough a name
     */
    public Cursor getCursor(Point mouseLocation)
    {
        return null;
    }
}
