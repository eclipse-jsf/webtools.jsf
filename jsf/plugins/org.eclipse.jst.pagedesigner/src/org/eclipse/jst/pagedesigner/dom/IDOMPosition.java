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
 * An IDOMPosition represents a position in the DOM tree. There are multiple
 * ways of identify a position in the tree, such as by saying a offset in
 * parent, or before/after a particular node.
 * 
 * NOTE: IDOMPosition and its child class should be implemented as literal. that
 * is, they should not be changed after it is constructed.
 * 
 * @author mengbo
 */
public interface IDOMPosition {
	/**
	 * 
	 * @param forward
	 *            if true, same as getNextSiblingNode(), if false, same as
	 *            getPreviousSiblingNode
	 * @return the sibling
	 */
	public Node getSibling(boolean forward);

	/**
	 * @return the next sibling node
	 */
	public Node getNextSiblingNode();

	/**
	 * @return the previous sibling node
	 */
	public Node getPreviousSiblingNode();

	/**
	 * @return the container node
	 */
	public Node getContainerNode();

	/**
	 * @return the offset
	 */
	public int getOffset();

	/**
	 * @return true if is text
	 */
	public boolean isText();

	/**
	 * "original" has been replace by "replacement" in the model. If this
	 * replacement will affect this IDOMPosition, then this method should return
	 * a new position that is valid after the replacement. If this replacement
	 * won't affect this position, then the original position should be
	 * returned.
	 * 
	 * @param original
	 * @param replacement
	 * @return the dom position
	 */
	public IDOMPosition handleReplacement(Node original, Node replacement);
}
