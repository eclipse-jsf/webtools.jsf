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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.commands.range.Paragraph;
import org.eclipse.jst.pagedesigner.commands.range.ParagraphFinder;
import org.eclipse.jst.pagedesigner.commands.range.ParagraphUnapplyStyleCommand;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class NoneParagraphStyleAction extends ParagraphStyleAction {

	private String[] _applyingTags;

	/**
	 * @param text
	 * @param tags
	 * @param image
	 * @param style
	 */
	public NoneParagraphStyleAction(String text, String[] tags,
			ImageDescriptor image, int style) {
		super(text, "", image, style); //$NON-NLS-1$
		_applyingTags = tags;
	}

	/**
	 * @param text
	 * @param node
	 * @param image
	 * @param style
	 */
	public NoneParagraphStyleAction(String text, Node node,
			ImageDescriptor image, int style) {
		super(text, node, image, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.actions.DesignerToolBarAction#isApplied(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected boolean isApplied(DOMRange range) {
		if (range != null) {
			boolean ordered = range.isOrdered();
			IDOMPosition start = ordered ? range.getStartPosition() : range
					.getEndPosition();
			IDOMPosition end = ordered ? range.getEndPosition() : range
					.getStartPosition();
			Node common = null;
			if (EditModelQuery.isSame(range)) {
				ParagraphFinder finder = new ParagraphFinder(start);
				Paragraph p = finder.getParagraph(start);
				common = p.getLowestContainer();
			} else {
				common = EditModelQuery.getInstance().getCommonAncestor(start,
						end);
			}
			// the lowest common block parent is the container to apply style.
			if (EditModelQuery.hasAncestor(common, _applyingTags, true)) {
				return false;
			}
            return true;
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#getCommand()
	 */
	protected Command getCommand() {
		ParagraphUnapplyStyleCommand command = new ParagraphUnapplyStyleCommand(
				getViewer(), _applyingTags, null, null);
		return command;
	}
}
