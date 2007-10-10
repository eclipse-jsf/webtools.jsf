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
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
/*package*/ class BasicMovementRule extends DefaultMovementRule {

	/**
	 * @param actionData
	 */
	public BasicMovementRule(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IMovementRule#allowsMoveIn(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean allowsMoveIn(Target target) {
		if (_actionData.getActionType() == ActionData.INLINE_EDIT) {
			Node node = target.getNode();
			return EditModelQuery.isText(node) || node.hasChildNodes();
		}
		return super.allowsMoveIn(target);
	}
}
