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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class DefaultPositionValidator implements IPositionMediator {
	private List _rules = new ArrayList();

	ActionData _actionData;

	/**
	 * @return Returns the _actionData.
	 */
	public ActionData getActionData() {
		return _actionData;
	}

	/**
	 * 
	 */
	protected DefaultPositionValidator(ActionData actionData) {
		_actionData = actionData;
		initRules();
	}

	protected void initRules() {
		_rules.clear();
		_rules.add(new BasicPositionRule(this, _actionData));
		_rules.add(new IETablePositionRule(this, _actionData));
		_rules.add(new RootContainerPositionRule(this, _actionData));
		_rules.add(new JSFRootContainerPositionRule(this, _actionData));
		_rules.add(new WhitespacePositionMoveRule(this, _actionData));
	}

	/**
	 * @return Returns the _rules.
	 */
	public List getRules() {
		return _rules;
	}

	/**
	 * @param _rules
	 *            The _rules to set.
	 */
	protected void setRules(List rules) {
		_rules = rules;
	}

	protected void addRule(IValidationRule rule) {
		_rules.add(rule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#hasEditableArea(org.w3c.dom.Node)
	 */
	public boolean hasEditableArea(Target target) {
		boolean result = true;
		List rules = getRules();
		for (int i = 0, n = rules.size(); i < n; i++) {
			Object rule = rules.get(i);
			if (rule instanceof IPositionRule) {
				result &= ((IPositionRule) rule).hasEditableArea(target);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#isEditable(org.w3c.dom.Node)
	 */
	public boolean isEditable(Target target) {
		Node node = target.getNode();
		boolean result = true;
		List rules = getRules();
		for (int i = 0, n = rules.size(); i < n; i++) {
			Object rule = rules.get(i);
			if (rule instanceof IPositionRule) {
				result &= ((IPositionRule) rule).isEditable(new Target(node));
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#isValidPosition(org.eclipse.jst.pagedesigner.viewer.DesignPosition)
	 */
	public boolean isValidPosition(DesignPosition position) {
		return isValidPosition(DOMPositionHelper.toDOMPosition(position));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#isValidPosition(org.eclipse.jst.pagedesigner.dom.IDOMPosition)
	 */
	public boolean isValidPosition(IDOMPosition position) {
		position = EditHelper.ensureDOMPosition(position);
		boolean refLeft = true, refRight = true, result = true;
		if (position == null) {
			return false;
		}
		List rules = getRules();
		for (int i = 0, n = rules.size(); i < n; i++) {
			Object rule = rules.get(i);
			if (rule instanceof IPositionRule) {
				// editable?
				result &= ((IPositionRule) rule).isEditable(new Target(position
						.getContainerNode()));
				if (result) {
					if (!position.isText()) {

						// ref1?
						Node node = EditModelQuery.getInstance().getSibling(
								position, true);
						if (node != null & refLeft) {
							refLeft &= ((IPositionRule) rule).canReference(
									new Target(node), false);
						}
						// ref2?
						node = EditModelQuery.getInstance().getSibling(
								position, false);
						if (node != null & refRight) {
							refRight = ((IPositionRule) rule).canReference(
									new Target(node), true);
						}
						if (!(refLeft | refRight)) {
							result = false;
							break;
						}
					}
				} else {
					break;
				}
				// }
			}
		}
		return (result & (refLeft | refRight));
	}

	/**
	 * Adjust the position to an editable area.
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#getEditableContainer(org.eclipse.gef.EditPart)
	 */
	public EditPart getEditableContainer(Target target) {
		EditPart part = target.getPart();
		if (hasEditableArea(target)) {
			return target.getPart();
		}
		while (part != null && !(part instanceof DocumentEditPart)) {
			if (hasEditableArea(target)) {
				break;
			}
			part = part.getParent();
			target = new Target(part);
		}
		if (part instanceof DocumentEditPart
				&& RootContainerPositionRule.hasBasicContainers((Document) part
						.getModel())) {
			Node node = RootContainerPositionRule
					.getBasicContainer((Document) part.getModel());
			part = Target.resolvePart(node);
		}
		return part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IValidator#canReference(org.w3c.dom.Node)
	 */
	public boolean canReference(Target target, boolean atRight) {
		boolean result = true;
		List rules = getRules();
		for (int i = 0, n = rules.size(); i < n; i++) {
			Object rule = rules.get(i);
			if (rule instanceof IPositionRule) {
				result &= ((IPositionRule) rule).canReference(target, atRight);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}
}
