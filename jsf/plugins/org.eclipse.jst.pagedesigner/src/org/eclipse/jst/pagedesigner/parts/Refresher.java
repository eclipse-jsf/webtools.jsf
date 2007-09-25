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

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class Refresher implements INodeAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return (type == Refresher.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		if (eventType == INodeNotifier.ADD || eventType == INodeNotifier.REMOVE) {
			// for ADD and REMOVE, system will fire CHANGE event, so we ignore
			// the ADD/REMOVE
			// event here.
			return;
		}
		if (notifier instanceof IDOMNode) {
			IDOMNode node = (IDOMNode) notifier;
			// we need to refresh all CSS style adapter of node and its
			// children.
			EditPart part = (EditPart) node.getAdapterFor(EditPart.class);
			if (part instanceof SubNodeEditPart) {
				Node nodeForFigure = ((SubNodeEditPart) part)
						.getNodeForFigure();
				if (nodeForFigure instanceof IDOMNode) {
					refreshChildStyles((IDOMNode) nodeForFigure);
				}
			}

			// we need also find the nearest parent node that has editpart, and
			// refresh it.
			refreshContainingPart(node,
					eventType == INodeNotifier.STRUCTURE_CHANGED);

			part = (EditPart) node.getAdapterFor(EditPart.class);
			if (part != null) {
				((IHTMLGraphicalViewer) part.getViewer()).clearSelectionRange();
			}
		}
	}

	/**
	 * @param node
	 */
	private void refreshContainingPart(IDOMNode node, boolean recursive) {
		if (node.getOwnerDocument() == node) {
			EditPart part = (EditPart) node.getAdapterFor(EditPart.class);
			if (part != null) {
				part.refresh();
			}
		} else {
			while (node != null) {
				EditPart part = (EditPart) node.getAdapterFor(EditPart.class);
				if (part != null) {
					if (part instanceof ElementEditPart) {
						((ElementEditPart) part).refreshModelChange(recursive);
					} else {
						part.refresh();
					}
					return;
				}
				node = (IDOMNode) node.getParentNode();
			}
		}
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
