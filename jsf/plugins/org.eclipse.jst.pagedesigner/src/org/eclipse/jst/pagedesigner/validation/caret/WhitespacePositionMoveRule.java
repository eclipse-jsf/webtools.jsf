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
package org.eclipse.jst.pagedesigner.validation.caret;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.eclipse.jst.pagedesigner.viewer.EditPartPositionHelper;
import org.w3c.dom.Text;

/**
 * For whitespaces 1. If there is sibling can be reference, then we don't
 * reference whitespace text. 2. Position can't be between whitespace text.
 * 
 * @author mengbo
 */
public class WhitespacePositionMoveRule extends DefaultPositionRule implements
		IMovementRule {

	/**
	 * @param actionData
	 */
	public WhitespacePositionMoveRule(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementRule#canEnter(org.eclipse.gef.EditPart)
	 */
	public boolean allowsMoveIn(Target target) {
		if (EditModelQuery.isTransparentText(target.getNode())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementRule#canMoveOut(org.eclipse.gef.EditPart)
	 */
	public boolean allowsMoveOut(Target target) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#canReference(org.eclipse.jst.pagedesigner.caret.Target)
	 */
	public boolean canReference(Target target, boolean atRight) {
		boolean result = super.canReference(target, atRight);
		if (EditModelQuery.isText(target.getNode())) {
			if (((Text) target.getNode()).getData().length() == 0) {
				result = false;
			} else if (EditModelQuery.isTransparentText(target.getNode())) {
				DesignPosition position = new DesignRefPosition(target
						.getPart(), atRight);
				if (EditPartPositionHelper.getConcretePart(position, atRight) != null) {
					result = true;
				} else {
					EditPart part = EditPartPositionHelper.getNextConcretPart(
							position, atRight);
					EditPart oppPart = EditPartPositionHelper
							.getNextConcretPart(position, !atRight);
					if (part == null) {
						if (oppPart == null) {
							result = true;
						}
					}
					result = false;
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#hasEditableArea(org.eclipse.jst.pagedesigner.caret.Target)
	 */
	public boolean hasEditableArea(Target target) {
		if (EditModelQuery.isTransparentText(target.getNode())) {
			return false;
		}
		return super.hasEditableArea(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.jst.pagedesigner.caret.Target)
	 */
	public boolean isEditable(Target target) {
		if (EditModelQuery.isTransparentText(target.getNode())) {
			return false;
		}
		return super.isEditable(target);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// org.eclipse.jst.pagedesigner.caret.IPositionRule#isValidPosition(org.eclipse.jst.pagedesigner.dom.IDOMPosition)
	// */
	// public boolean isValidPosition(IDOMPosition position)
	// {
	// Node node = null;
	// if (position instanceof DOMRefPosition)
	// {
	// node = ((DOMRefPosition)position).getReferenceNode();
	// } else if (position.isText())
	// {
	// node = position.getContainerNode();
	// } else {
	// return super.isValidPosition(position);
	// }
	// if (EditModelQuery.isTransparentText(position.getContainerNode()))
	// {
	// if (node.getPreviousSibling() != null)
	// {
	// node = node.getPreviousSibling();
	// return _mediator.canReference(new Target(node), true);
	// }
	// else if (node.getNextSibling() != null)
	// {
	// node = node.getNextSibling();
	// return _mediator.canReference(new Target(node), false);
	// }
	// }
	// return super.isValidPosition(position);
	// }
}
