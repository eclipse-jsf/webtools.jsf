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
 * This validator is used for caret positioning when user do drag & drop on the
 * screen.
 * 
 * @author mengbo
 * TODO: is this class complete?
 */
public class DnDPositionValidator extends DefaultPositionValidator {
    //TODO: not used
//	private static DnDPositionValidator _instance;
//
//	private ActionData _data;

	/**
	 * @param actionData
	 */
	public DnDPositionValidator(ActionData actionData) {
		super(actionData);
	}

    /**
     * Override to make adding rules public on Dnd validators
     */
    public void addRule(IValidationRule rule) 
    {
        super.addRule(rule);
    }
    
    
}
