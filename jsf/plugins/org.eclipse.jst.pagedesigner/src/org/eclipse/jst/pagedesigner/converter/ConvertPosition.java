/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.converter;

import org.w3c.dom.Node;

/**
 * This class is used to locate a position for child model nodes.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ConvertPosition {
	private final Node _parentNode;

	private final int _index;

	/**
	 * @param parent \
	 * @param index 
	 * 
	 */
	public ConvertPosition(Node parent, int index) {
		this._parentNode = parent;
		this._index = index;
	}

	/**
	 * @return the parent node
	 */
	public Node getParentNode() {
		return _parentNode;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return _index;
	}
}
