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
package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;

/**
 * @author mengbo
 * @version 1.5
 */
public class SelectEditPartAction extends Action {
	ElementEditPart _part;

	/**
	 * @param text
	 */
	public SelectEditPartAction(String text, ElementEditPart part) {
		super(text);
		this._part = part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		_part.getViewer().setSelection(new StructuredSelection(_part));
	}
}
