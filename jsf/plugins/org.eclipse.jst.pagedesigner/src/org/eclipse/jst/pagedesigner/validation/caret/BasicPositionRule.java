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

import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.w3c.dom.Node;

/**
 * Deal with widget.
 * 
 * @author mengbo
 */
/*package*/ class BasicPositionRule extends DefaultPositionRule 
{
    private final IPositionMediator _mediator;

	/**
	 * @param mediator
	 * @param actionData 
	 */
	public BasicPositionRule(IPositionMediator mediator, ActionData actionData) {
	    super(actionData);
        _mediator = mediator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#hasEditableArea(org.eclipse.gef.EditPart)
	 */
	public boolean hasEditableArea(Target target) {
		if (target == null || target.getPart() == null) {
			return false;
		}
		if (!EditValidateUtil.validNode(target.getNode())) {
			return false;
		}
		if (DefaultPositionRule.isWidget(target.getPart())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.gef.EditPart)
	 */
	public boolean isEditable(Target target) {
		if (target.getPart() == null) {
			return false;
		}
		Node node = target.getNode();

		// text is depending on parent.
		if (EditModelQuery.isText(node)) {
			return _mediator.isEditable(new Target(node.getParentNode()));
		}
		String name = node.getLocalName();
		// Name is null, the node should not be Element
		if (name == null && !EditModelQuery.isDocument(node)
				&& !EditModelQuery.isText(node)) {
			return false;
		}
		// if is widget, return false;
		if (DefaultPositionRule.isWidget(target.getPart())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#canStopHere(org.w3c.dom.Node)
	 */
	public boolean canReference(Target target, boolean atRight) {
		Node node = target.getNode();
		if (target.getPart() == null || node.getNodeType() != Node.ELEMENT_NODE
				&& !EditModelQuery.isText(node)) {
			return false;
		}
		return true;
	}
}
