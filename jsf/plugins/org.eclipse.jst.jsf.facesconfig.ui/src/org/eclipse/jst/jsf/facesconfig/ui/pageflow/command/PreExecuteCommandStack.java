/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

/**
 * Before the command stack called execute to do the actual model modification,
 * it will call the command's IPreExecuteCommand inteface to check the command
 * can be execute or not.
 * 
 * 
 * @author Xiao-guang Zhang
 */
public class PreExecuteCommandStack extends CommandStack {
	/**
	 * 
	 */
	public PreExecuteCommandStack() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
	 */
	public void execute(Command command) {
		if (command == null || !command.canExecute()) {
			return;
		}
		if (command instanceof IPreExecuteCommand) {
			if (!((IPreExecuteCommand) command).preExecute()) {
				return;
			}
		}
		super.execute(command);
	}
}
