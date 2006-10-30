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
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class UnapplyStyleCommand extends ApplyStyleCommand {
	/**
	 * @param viewer
	 * @param tag
	 * @param property
	 * @param value
	 */
	public UnapplyStyleCommand(IHTMLGraphicalViewer viewer, String tag,
			String property, String value) {
		super(viewer, tag, property, value);
	}

	/**
	 * @param viewer
	 * @param node
	 * @param property
	 * @param value
	 */
	public UnapplyStyleCommand(IHTMLGraphicalViewer viewer, Element node,
			String property, String value) {
		super(viewer, node, property, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange range) {
		if (range == null || range.isEmpty()) {
			return null;
		}

		boolean ordered = range.isOrdered();
		IDOMPosition start = ordered ? range.getStartPosition() : range
				.getEndPosition();
		IDOMPosition end = ordered ? range.getEndPosition() : range
				.getStartPosition();

		Node common = DOMUtil.findCommonAncester(start.getContainerNode(), end
				.getContainerNode());
		if (common == null) {
			// should not happen.
			return null;
		}

		if (common instanceof Text) {
			doTextNodeStyleApply((Text) common, start.getOffset(), end
					.getOffset());
		}

		return null;
	}

	/**
	 * @param start
	 * @param end
	 * @param common
	 */
	private DOMRange doTextNodeStyleApply(Text textNode, int startOffset,
			int endOffset) {
		return null;
	}
}
