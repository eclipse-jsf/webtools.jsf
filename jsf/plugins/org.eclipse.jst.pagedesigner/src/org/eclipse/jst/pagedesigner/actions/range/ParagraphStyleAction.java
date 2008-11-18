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

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.commands.range.Paragraph;
import org.eclipse.jst.pagedesigner.commands.range.ParagraphApplyStyleCommand;
import org.eclipse.jst.pagedesigner.commands.range.ParagraphFinder;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class ParagraphStyleAction extends DesignerToolBarAction {
	private String _tagName;

	private Node _applyingNode;

	/**
	 * @param text
	 * @param name
	 * @param image
	 * @param style
	 */
	public ParagraphStyleAction(String text, String name,
			ImageDescriptor image, int style) {
		super(text, style);
		_tagName = name;
		setImageDescriptor(image);
	}

	/**
	 * @param text
	 * @param node
	 * @param image
	 * @param style
	 */
	public ParagraphStyleAction(String text, Node node, ImageDescriptor image,
			int style) {
		super(text, style);
		_applyingNode = node;
		setImageDescriptor(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.actions.DesignerToolBarAction#isApplied(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected boolean isApplied(DOMRange range) {
		Assert.isTrue(getExpectedTag() != null);
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
			if (containsTag(common)) {
				return true;
			}
            return false;
		}
        return false;
	}

	/**
	 * @param common
	 * @return ??? 
	 */
	protected boolean containsTag(Node common) {
		// the lowest common block parent is the container to apply style.
		if (_applyingNode == null) {
			return common.getNodeName() != null
					&& getExpectedTag().equalsIgnoreCase(
							common.getNodeName().toLowerCase());
		}
        
        String align = ((Element) _applyingNode).getAttribute("align"); //$NON-NLS-1$
        if (!(common instanceof Element)) {
        	return false;
        }
        String cAlign = ((Element) common).getAttribute("align"); //$NON-NLS-1$
        if (align == null || cAlign == null) {
        	return false;
        }
        if (align.equals(cAlign)) {
        	return true;
        }
        return false;
	}

	/**
	 * @return Returns the _expectedTag.
	 */
	public String getExpectedTag() {
		if (_tagName == null) {
			return _applyingNode.getNodeName().toLowerCase();
		}
        return _tagName.toLowerCase();
	}

	/**
	 * @return Returns the _applyingNode.
	 */
	public Element getApplyingNode() {
		if (_applyingNode != null) {
			return (Element) _applyingNode;
		}
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#getCommand()
	 */
	protected Command getCommand() {
		ParagraphApplyStyleCommand command = null;
		if (getApplyingNode() != null) {
			command = new ParagraphApplyStyleCommand(getViewer(),
					getApplyingNode(), null, null);
		} else {
			command = new ParagraphApplyStyleCommand(getViewer(),
					getExpectedTag(), null, null);
		}
		return command;
	}
}
