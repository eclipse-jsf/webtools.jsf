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
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;

/**
 * @author mengbo
 */
public class DefaultPositionRule implements IPositionRule {

	private ActionData _actionData;

	/**
	 * @param actionData 
	 * 
	 */
	public DefaultPositionRule(ActionData actionData) {
		super();
		if (actionData != null) {
			_actionData = actionData;
		} else {
			_actionData = new ActionData(ActionData.UNKNOWN, new Object());
		}
	}

    /**
     * @return the action data
     */
    protected final ActionData getActionData()
    {
        return _actionData;
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#hasEditableArea(org.w3c.dom.Node)
	 */
	public boolean hasEditableArea(Target target) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.gef.EditPart)
	 */
	public boolean isEditable(Target target) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isTransparent(org.eclipse.gef.EditPart,
	 *      org.eclipse.draw2d.geometry.Point)
	 */
	public boolean canReference(Target target, boolean atRight) {
		return true;
	}

	/**
	 * If container is inEditable and can be referenced, the position is
	 * invalid, otherwise as default the position is valid. (non-Javadoc)
	 */
	public boolean isValidPosition(IDOMPosition position) {
		boolean result = isEditable(new Target(position.getContainerNode()));
		if (result) {
			if (position.getOffset() == 0
					|| position.getOffset() == position.getContainerNode()
							.getChildNodes().getLength()) {
				result = true;
			} else {
				boolean dir;
				Target target = null;
				if (position instanceof DOMRefPosition) {
					target = new Target(((DOMRefPosition) position)
							.getReferenceNode());
					dir = ((DOMRefPosition) position).isForward();
					result = canReference(target, dir);
				}
			}
		}
		return result;
	}
    
    /**
     * @param host
     * @return true if the host edit part is a widget
     */
     public static boolean isWidget(EditPart host) {
        if (host instanceof NodeEditPart) {
            return ((NodeEditPart) host).isWidget();
        }
        return false;
    }
}
