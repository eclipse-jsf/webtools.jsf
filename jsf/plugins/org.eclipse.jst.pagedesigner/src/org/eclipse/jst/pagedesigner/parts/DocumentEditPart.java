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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.FlowPage;
import org.eclipse.wst.css.core.internal.event.ICSSStyleListener;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSModel;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSSelector;
import org.eclipse.wst.html.core.internal.htmlcss.StyleListener;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 */
public class DocumentEditPart extends NodeEditPart implements StyleListener,
		ICSSStyleListener {
	boolean _refreshing = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		List list = new ArrayList();
		Node model = (Node) getModel();
		if (model == null) {
			return list;
		}

		NodeList children1 = model.getChildNodes();
		for (int i = 0, n = children1.getLength(); i < n; i++) {
			Node node = children1.item(i);
			if (node.getNodeType() != Node.TEXT_NODE
					&& node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			list.add(node);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		FlowPage f = new FlowPage();
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		refresh();
	}

    /**
     * @param recursive
     */
    public void refresh(final boolean recursive)
    {
        if (!recursive)
        {
            refresh();
        }
        else
        {
            refreshVisuals();
            refreshChildren(recursive);
            refreshSourceConnections();
            refreshTargetConnections();

        }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#refresh()
	 */
	public void refresh() {
		if (_refreshing) {
			return;
		}
		_refreshing = true;
		try {
			super.refresh();
		} finally {
			_refreshing = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
	 */
	protected void refreshChildren() {
	    refreshChildren(false);
	}

    /**
     * @param recursive
     */
    protected void refreshChildren(final boolean recursive)
    {
        super.refreshChildren();
        List children1 = getChildren();
        for (int i = 0, size = children1.size(); i < size; i++)
        {
            final EditPart editPart = (EditPart) children1.get(i);
            if (editPart instanceof ElementEditPart)
            {
                ((ElementEditPart)editPart).refresh(recursive);
            }
            else
            {
                editPart.refresh();
            }
        }
    }

	// protected void removeChildVisual(EditPart childEditPart)
	// {
	// if (childEditPart instanceof SubNodeEditPart)
	// {
	// Node node = ((SubNodeEditPart) childEditPart).getNodeForFigure();
	// if (node != null)
	// {
	// getDestDocumentForDesign().removeChild(node);
	// }
	// }
	// super.removeChildVisual(childEditPart);
	// }
	//    
	// protected void addChildVisual(EditPart childEditPart, int index)
	// {
	// if (childEditPart instanceof SubNodeEditPart)
	// {
	// Node node = ((SubNodeEditPart) childEditPart).getNodeForFigure();
	// if (node != null)
	// {
	// NodeList nodeList = getDestDocumentForDesign().getChildNodes();
	// if (nodeList.getLength() > index)
	// {
	// getDestDocumentForDesign().insertBefore(node, nodeList.item(index));
	// }
	// else
	// {
	// getDestDocumentForDesign().appendChild(node);
	// }
	// }
	// }
	// super.addChildVisual(childEditPart, index);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.html.core.internal.htmlcss.StyleListener#styleChanged()
	 */
	public void styleChanged() {
		// refresh the whole document when style change (<style> or <link>)
		this.refreshStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.css.core.internal.event.ICSSStyleListener#styleChanged(org.eclipse.wst.css.core.internal.provisional.document.ICSSModel,
	 *      org.eclipse.wst.css.core.document.ICSSSelector[],
	 *      org.eclipse.wst.css.core.document.ICSSSelector[], java.lang.String)
	 */
	public void styleChanged(ICSSModel srcModel, ICSSSelector[] removed,
			ICSSSelector[] added, String media) {
		if ((removed != null && removed.length > 0) || added != null
				&& added.length > 0) {
			this.refreshStyle();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.css.core.internal.event.ICSSStyleListener#styleUpdate(org.eclipse.wst.css.core.internal.provisional.document.ICSSModel)
	 */
	public void styleUpdate(ICSSModel srcModel) {
		this.refreshStyle();
	}

	/**
	 * 
	 */
	private void refreshStyle() {
		List childParts = this.getChildren();
		for (Iterator iter = childParts.iterator(); iter.hasNext();) {
			EditPart part = (EditPart) iter.next();
			if (part instanceof ElementEditPart) {
				IDOMNode node = (IDOMNode) ((ElementEditPart) part)
						.getNodeForFigure();
				if (node != null) {
					refreshChildStyles(node);
				}
			}
		}
		getFigure().revalidate();
		// getFigure().repaint();
	}

	/**
	 * @param node
	 */
	private void refreshChildStyles(IDOMNode node) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0, size = childNodes.getLength(); i < size; i++) {
			refreshChildStyles((IDOMNode) childNodes.item(i));
		}
		if (node instanceof IDOMElement) {
			// only refresh style on element.
			ICSSStyle a = (ICSSStyle) node.getAdapterFor(ICSSStyle.class);
			if (a != null) {
				a.reset();
			}
		}
	}
}
