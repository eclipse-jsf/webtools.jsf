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
 * A IDOMRefPosition locate a position by reference a node in the dom tree. It
 * mean a position after node, before a node, or before anything in a node, or
 * after anything in a node.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IDOMRefPosition extends IDOMPosition {
	/**
	 * 
	 * @return the reference (wrapped) node
	 */
	public Node getReferenceNode();
}
