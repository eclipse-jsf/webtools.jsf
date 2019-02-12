/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.DeleteHeaderFooterRequest;


/**
 * @author mengbo
 * @version 1.5
 */
public class DeleteHeaderFooterAction extends Action
{
    private Command _command;

    /**
     * @param text
     * @param editPart 
     * @param isHeader 
     */
    public DeleteHeaderFooterAction(String text, EditPart editPart, boolean isHeader)
    {
        super(text);
        DeleteHeaderFooterRequest req = new DeleteHeaderFooterRequest(text, isHeader);
        this._command = editPart.getCommand(req);
        this.setEnabled(this._command != null && this._command.canExecute());
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        _command.execute();
    }
}
