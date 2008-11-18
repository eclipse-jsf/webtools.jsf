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

import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class ParagraphUnapplyStyleCommand extends ApplyStyleCommand {
	private String _groupTags[];

	/**
	 * @param viewer
	 * @param tags
	 * @param property
	 * @param value
	 */
	public ParagraphUnapplyStyleCommand(IHTMLGraphicalViewer viewer,
			String[] tags, String property, String value) {
		super(viewer, "", property, value); //$NON-NLS-1$
		_groupTags = tags;
	}

	/**
	 * @param viewer
	 * @param node
	 * @param property
	 * @param value
	 */
	public ParagraphUnapplyStyleCommand(IHTMLGraphicalViewer viewer,
			Element node, String property, String value) {
		super(viewer, node, property, value);
	}

	private DOMRange removeExistingStyles(IDOMPosition start, IDOMPosition end) {
		Node common = null;
		if (EditModelQuery.isSame(start, end)) {
			ParagraphFinder finder = new ParagraphFinder(start);
			Paragraph p = finder.getParagraph(start);
			start = p.getStart();
			end = p.getEnd();
			common = p.getLowestContainer();
		} else {
			common = EditModelQuery.getInstance().getCommonAncestor(start, end);
		}
		// Here we insert some code to avoid creating tags duplicated. but these
		// are not the entire cases.
		// if (Arrays.asList(_groupTags).contains(common.getNodeName()))
		if (EditModelQuery.containItem(_groupTags, common, true)) {
			start = DOMPositionHelper.toDOMRefPosition(start);
			end = DOMPositionHelper.toDOMRefPosition(end);
			Node parent = common.getParentNode();
			EditModelQuery.copyChildren(common, parent);
			common.getParentNode().removeChild(common);
			return new DOMRange(start, end);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange range) {
		return removeExistingStyles(range.getStartPosition(), range
				.getEndPosition());
	}
}
