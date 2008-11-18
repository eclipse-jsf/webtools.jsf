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
package org.eclipse.jst.pagedesigner.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOMRefPosition is another way to identify a position in the document tree. It
 * has the advantage against the DOMPosition, that even something change in the
 * document, DOMRefPosition may still reference the correct position.
 * 
 * @author mengbo
 */
public class DOMRefPosition implements IDOMRefPosition {
	Node _refNode;

	boolean _forward;

	/**
	 * @param refNode
	 * @param forward
	 *            true means the position after refNode. false means the
	 *            position before refNode
	 */
	public DOMRefPosition(Node refNode, boolean forward) {
		_refNode = refNode;
		_forward = forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getSibling(boolean)
	 */
	public Node getSibling(boolean forward) {
		if (forward != _forward)
        {
		    return _refNode;
        }
		if (forward)
        {
			return _refNode.getNextSibling();
        }
        return _refNode.getPreviousSibling();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getNextSiblingNode()
	 */
	public Node getNextSiblingNode() {
		return getSibling(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getPreviousSiblingNode()
	 */
	public Node getPreviousSiblingNode() {
		return getSibling(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getContainerNode()
	 */
	public Node getContainerNode() {
		return _refNode.getParentNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getOffset()
	 */
	public int getOffset() {
		Node parent = _refNode.getParentNode();
		if (parent == null) {
			return _forward ? 1 : 0;
		}
        NodeList list = parent.getChildNodes();
        for (int i = 0, n = list.getLength(); i < n; i++) {
        	if (list.item(i) == _refNode) {
        		return _forward ? (i + 1) : i;
        	}
        }
        // should not happen.
        return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#isText()
	 */
	public boolean isText() {
		return false;
	}

	public IDOMPosition handleReplacement(Node original, Node replacement) {
		if (this._refNode == original) {
			return new DOMRefPosition(replacement, this._forward);
		}
        return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "DOMRefPosition: (" + (_forward ? "after " : "before ") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ _refNode + ")"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMRefPosition#getReferenceNode()
	 */
	public Node getReferenceNode() {
		return _refNode;
	}

	/**
	 * @return true if is forward
	 */
	public boolean isForward() {
		return _forward;
	}
}
