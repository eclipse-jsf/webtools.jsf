/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

/**
 * Command with pre execute checking.
 * 
 * @author Xiao-guang Zhang
 * 
 * 
 */
/*package*/ interface IPreExecuteCommand {
	/**
	 * Do something pre-execution of the actual command execution.
	 * 
	 * @return - successful to do the pre-execution
	 */
	boolean preExecute();
}
