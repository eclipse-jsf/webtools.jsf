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
 * This validtor is used for inline editing, caret positioning with mouse.
 * 
 * @author mengbo
 */
public class InlineEditingPositionMediator extends DefaultPositionValidator {

	/**
	 * @param actionData
	 */
	public InlineEditingPositionMediator(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.DefaultPositionValidator#initRules()
	 */
	protected void initRules() {
		super.initRules();
		addRule(new IEPanelgridPositionRule(_actionData));
	}
}
