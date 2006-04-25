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

/**
 * Command with pre execute checking.
 * 
 * @author Xiao-guang Zhang
 * 
 * 
 */
public interface IPreExecuteCommand {
	/**
	 * Do something pre-execution of the actual command execution.
	 * 
	 * @return - successful to do the pre-execution
	 */
	boolean preExecute();
}
