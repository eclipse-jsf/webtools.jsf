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
 * @author mengbo
 */
public class DOMRange {
	IDOMPosition _start;

	IDOMPosition _end;

	/**
	 * @param p1
	 * @param p2
	 */
	public DOMRange(IDOMPosition p1, IDOMPosition p2) {
		_start = p1;
		_end = p2;
	}

	/**
	 * @return the start position
	 */
	public IDOMPosition getStartPosition() {
		return _start;
	}

	/**
	 * @return the end position
	 */
	public IDOMPosition getEndPosition() {
		return _end;
	}

	/**
	 * @return true if is empty
	 */
	public boolean isEmpty() {
		return _start.getContainerNode() == _end.getContainerNode()
				&& _start.getOffset() == _end.getOffset();
	}

	/**
	 * @return true if is ordered
	 */
	public boolean isOrdered() {
		Node common = DOMUtil.findCommonAncester(_start.getContainerNode(),
				_end.getContainerNode());
		if (common == null) {
			return true;
		}
		IDOMPosition s = moveUp(_start, common);
		IDOMPosition e = moveUp(_end, common);
		return e.getOffset() >= s.getOffset();
	}

	private IDOMPosition moveUp(IDOMPosition p, Node ancester) {
		while (p.getContainerNode() != ancester) {
			p = new DOMRefPosition(p.getContainerNode(), false);
		}
		return p;
	}
}
