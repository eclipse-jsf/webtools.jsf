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

/**
 * This ref position use a parent node as reference. And provide whether this
 * location is at the beginning of its parent or last of its parent.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DOMRefPosition2 implements IDOMRefPosition {
	Node _parentNode;

	boolean _last;

	/**
	 * @param parent 
	 * @param last 
	 */
	public DOMRefPosition2(Node parent, boolean last) {
		_parentNode = parent;
		_last = last;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMRefPosition#getReferenceNode()
	 */
	public Node getReferenceNode() {
		return _parentNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#getSibling(boolean)
	 */
	public Node getSibling(boolean forward) {
		if (forward) {
			if (_last) {
				return null;
			}
            return _parentNode.getFirstChild();
		} else if (_last) {
        	return _parentNode.getLastChild();
        } else {
        	return null;
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#getNextSiblingNode()
	 */
	public Node getNextSiblingNode() {
		return getSibling(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#getPreviousSiblingNode()
	 */
	public Node getPreviousSiblingNode() {
		return getSibling(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#getContainerNode()
	 */
	public Node getContainerNode() {
		return _parentNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#getOffset()
	 */
	public int getOffset() {
		if (!_last) {
			return 0;
		}
        return _parentNode.getChildNodes().getLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#isText()
	 */
	public boolean isText() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.dom.IDOMPosition#handleReplacement(org.w3c.dom.Node,
	 *      org.w3c.dom.Node)
	 */
	public IDOMPosition handleReplacement(Node original, Node replacement) {
		if (original == _parentNode) {
			return new DOMRefPosition2(replacement, _last);
		}
        return this;
	}

}
