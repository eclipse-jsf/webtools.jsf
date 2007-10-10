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

/**
 * @author mengbo
 */
/*package*/ class DefaultMovementRule implements IMovementRule {
	ActionData _actionData;

	/**
	 * @param actionData 
	 * 
	 */
	public DefaultMovementRule(ActionData actionData) {
		super();
		_actionData = actionData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IMovementRule#allowsMoveIn(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean allowsMoveIn(Target target) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IMovementRule#allowsMoveOut(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean allowsMoveOut(Target target) {
		return true;
	}

}
