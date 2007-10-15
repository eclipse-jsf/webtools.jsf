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

import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class Paragraph {
	private DOMRange _range;

	/**
	 * @param start
	 * @param end
	 */
	public Paragraph(IDOMPosition start, IDOMPosition end) {
		_range = new DOMRange(start, end);
	}

	/**
	 * @return the lowest container node
	 */
	public Node getLowestContainer() {
		return EditModelQuery.getInstance().getCommonAncestor(
				_range.getStartPosition(), _range.getEndPosition());
	}

	/**
	 * @return Returns the _end.
	 */
	public final IDOMPosition getStart() {
		return _range.isOrdered() ? _range.getStartPosition() : _range
				.getEndPosition();
	}

	/**
	 * @return Returns the _start.
	 */
	public final IDOMPosition getEnd() {
		return _range.isOrdered() ? _range.getEndPosition() : _range
				.getStartPosition();
	}
}
