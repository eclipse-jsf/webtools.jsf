/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
