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
package org.eclipse.jst.pagedesigner.tableedit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;

/**
 * @author mengbo
 * @version 1.5
 */
public class InsertRowColumnAction extends Action {
	private Command _command;

	/**
	 * @param text
	 * @param tablePart 
	 * @param index 
	 * @param isrow 
	 * @param isbefore 
	 */
	public InsertRowColumnAction(String text, EditPart tablePart, int index,
			boolean isrow, boolean isbefore) {
		super(text);

		TableInsertRequest req = new TableInsertRequest(isrow, index, isbefore);
		this._command = tablePart.getCommand(req);
		this.setEnabled(this._command != null && this._command.canExecute());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		_command.execute();
	}
}
