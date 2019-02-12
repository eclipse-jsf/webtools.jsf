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
package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class SelectEditPartAction extends Action {
    
    /**
     * @param text
     * @param forThisPart
     * @return a convience object when the edit part that needs selection
     * is already known when the action is constructed
     */
    public static SelectEditPartAction create(final String text, final EditPart forThisPart)
    {
        return new SelectEditPartAction(text)
        {
            protected EditPart getNewSelection() {
                return forThisPart;
            }
        };
    }

	/**
	 * @param text
	 */
	protected SelectEditPartAction(String text) {
		super(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
        final EditPart newSelection = getNewSelection();
        newSelection.getViewer().setSelection(new StructuredSelection(newSelection));
	}
    
    /**
     * @return the EditPart onto which selection should be applied.
     */
    protected abstract EditPart getNewSelection();
}
