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

import java.util.List;

import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class InlineEditingNavigationMediator extends
		InlineEditingPositionMediator implements IMovementMediator {

	/**
	 * @param actionData
	 */
	public InlineEditingNavigationMediator(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.DefaultPositionValidator#initRules(org.eclipse.jst.pagedesigner.caret.ActionData)
	 */
	protected void initRules() {
		super.initRules();
		this.addRule(new ContainerMoveInAndOutRule(_actionData));
		this.addRule(new BasicMovementRule(_actionData));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementValidator#allowsMoveIn(org.eclipse.gef.EditPart)
	 */
	public boolean allowsMoveIn(Target target) {
		boolean result = true;
		List _rules = getRules();
		for (int i = 0, n = _rules.size(); i < n; i++) {
			Object rule = _rules.get(i);
			if (rule instanceof IMovementRule) {
				result &= ((IMovementRule) rule).allowsMoveIn(target);
			} else if (rule instanceof IPositionRule) {
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
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementValidator#allowsMoveOut(org.eclipse.gef.EditPart)
	 */
	public boolean allowsMoveOut(Target target) {
		boolean result = true;
		List _rules = getRules();
		for (int i = 0, n = _rules.size(); i < n; i++) {
			Object rule = _rules.get(i);
			if (rule instanceof IMovementRule) {
				result &= ((IMovementRule) rule).allowsMoveOut(target);
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	/**
	 * @param target 
	 * @return ? 
	 */
	public EditPart getRootConstainedEditableContainer(Target target) {
		// if (EditModelQuery.isDocument(target.getNode()))
		// {
		// return target.getPart();
		// }
		EditPart part = target.getPart();
		while (part != null) {
			if (hasEditableArea(target) && !allowsMoveOut(target)) {
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

	/**
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator#getConstainedEditableContainer(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public EditPart getConstainedEditableContainer(Target target) {
		EditPart part = target.getPart();
		while (part != null) {
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

	/**
	 * @param position 
	 * @param p 
	 * @param viewer 
	 * @return the constrained editable container or null if none found for the arguments
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator#getConstainedEditableContainer(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public EditPart getConstainedEditableContainer(DesignPosition position,
			Point p, GraphicalViewer viewer) {
		Rectangle rect = new Rectangle(p.x, p.y, 1, 1);
		Viewport port = ((IHTMLGraphicalViewer) viewer).getViewport();

		Point viewLocation = port.getViewLocation();
		Point lastLocation = viewLocation.getCopy();
		new ExposeHelper((IHTMLGraphicalViewer) viewer).exposeArea(rect);
		viewLocation = port.getViewLocation();
		Dimension offset = lastLocation.getDifference(viewLocation);

		p.translate(offset.width, offset.height);
		EditPart part = viewer.findObjectAt(p);
		if (part != null && !(part instanceof ScalableRootEditPart)) {
			while (part != null) {
				Target target = new Target(part);
				if (hasEditableArea(target)) {
					if (allowsMoveIn(target)
							|| EditModelQuery.isChild(target.getNode(),
									position.getContainerNode())) {
						break;
					}
				}
				part = part.getParent();
			}
			if (part instanceof DocumentEditPart
					&& RootContainerPositionRule
							.hasBasicContainers((Document) part.getModel())) {
				Node node = RootContainerPositionRule
						.getBasicContainer((Document) part.getModel());
				part = Target.resolvePart(node);
			}
			return part;
		}
        return null;
	}
}
