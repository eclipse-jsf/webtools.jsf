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
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class DOMPosition implements IDOMPosition {
	Node _containerNode;

	int _offset;

	/**
	 * @param containerNode
	 * @param offset
	 */
	public DOMPosition(Node containerNode, int offset) {
		_containerNode = containerNode;
		_offset = offset;
	}

	/**
	 * this is the offset in the DOM tree. When parent node is text node, the
	 * offset if the offset into the actual displayed data of the text node.
	 * 
	 * when parent is not text node, then the offset is the index in
	 * getIDOMNode().getChildNodes()
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return _offset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getNextSiblingNode()
	 */
	public Node getNextSiblingNode() {
		if (isText())
			return null;
		NodeList children = _containerNode.getChildNodes();
		int length = children.getLength();
		if (_offset >= length || _offset < 0)
			return null;
        return children.item(_offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getPreviousSiblingNode()
	 */
	public Node getPreviousSiblingNode() {
		if (isText())
			return null;
		NodeList children = _containerNode.getChildNodes();
		int length = children.getLength();
		if (_offset > length || _offset <= 0)
			return null;
        return children.item(_offset - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getContainerNode()
	 */
	public Node getContainerNode() {
		return _containerNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#isText()
	 */
	public boolean isText() {
		return _containerNode instanceof Text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.range.IDOMPosition#getSibling(boolean)
	 */
	public Node getSibling(boolean forward) {
		if (forward)
			return getNextSiblingNode();
        return getPreviousSiblingNode();
	}

	public IDOMPosition handleReplacement(Node original, Node replacement) {
		if (original == this._containerNode) {
			return new DOMPosition(replacement, this._offset);
		}
        return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "DOMPosition: (" + _containerNode + " : " + _offset + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
