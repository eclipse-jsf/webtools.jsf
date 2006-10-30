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
	 * 
	 */
	public DOMRange(IDOMPosition p1, IDOMPosition p2) {
		_start = p1;
		_end = p2;
	}

	public IDOMPosition getStartPosition() {
		return _start;
	}

	public IDOMPosition getEndPosition() {
		return _end;
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return _start.getContainerNode() == _end.getContainerNode()
				&& _start.getOffset() == _end.getOffset();
	}

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
