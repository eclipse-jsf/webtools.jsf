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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public final class WorkNode {
	private WorkNode parent;

	private WorkNode previous;

	private WorkNode next;

	private Node node;

	private final int pos[];

	private boolean isRoot;

	/**
	 * @param node
	 * @param pos1
	 * @param pos2
	 */
	public WorkNode(Node node, final int pos1, final int pos2) {
		this.pos = resoveOffsets(node, pos1, pos2);
		this.node = node;
	}

	/**
	 * @return Returns the node.
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            The node to set.
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the offsets
	 */
	int[] getQualifiedOffsets() {
		int result[] = new int[] { getPosOffsets()[0], getPosOffsets()[1] };
		result[0] = result[0] < 0 ? 0 : result[0];
		int length = EditModelQuery.getNodeLenth(node);
		result[0] = result[0] > length ? length : result[0];
		result[1] = result[1] < 0 ? 0 : result[1];
		result[1] = result[1] > length ? length : result[1];
		return result;
	}

	/**
	 * @return Returns the pos.
	 */
	public int[] getPosOffsets() {
		return pos;
	}

	/**
	 * @return Returns the isRoot.
	 */
	public boolean isRoot() {
		return isRoot;
	}

	/**
	 * @param isRoot
	 *            The isRoot to set.
	 */
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	/**
	 * @return Returns the next.
	 */
	public WorkNode getNext() {
		return next;
	}

	/**
	 * @param next
	 *            The next to set.
	 */
	public void setNext(WorkNode next) {
		this.next = next;
	}

	/**
	 * @return Returns the parent.
	 */
	public WorkNode getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            The parent to set.
	 */
	public void setParent(WorkNode parent) {
		this.parent = parent;
	}

	/**
	 * @return Returns the previous.
	 */
	public WorkNode getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            The previous to set.
	 */
	public void setPrevious(WorkNode previous) {
		this.previous = previous;
	}

	private int[] resoveOffsets(Node node1, int pos1, int pos2) {
		int left = EditModelQuery.getNodeStartIndex(node1);
		return new int[] { pos1 - left, pos2 - left };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Node:").append(node).append(" pos[]:").append(pos); //$NON-NLS-1$ //$NON-NLS-2$
		return sb.toString();
	}

	boolean isWholeSelected() {
		int start = EditModelQuery.getNodeStartIndex(node);
		int end = EditModelQuery.getNodeEndIndex(node);
		return getQualifiedOffsets()[0] <= 0
				&& getQualifiedOffsets()[1] >= end - start;
	}
}
