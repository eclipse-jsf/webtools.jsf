/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;

/**
 * This class is used to open the cheat sheet based on the configuration
 * parameter.
 * 
 * @author mengbo
 */
public class LoadCheatSheetAction extends Action implements
		IExecutableExtension {
	private String _cheatSheetName = null;

	/**
	 * 
	 */
	public LoadCheatSheetAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		if (_cheatSheetName != null) {
			OpenCheatSheetAction action = new OpenCheatSheetAction(
					_cheatSheetName);
			action.run();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		_cheatSheetName = config.getAttribute("actionparameters"); //$NON-NLS-1$
	}

	/**
	 * set the cheatSheetName name to open.
	 * 
	 * @param cheatSheetName
	 */
	public void setCheatSheetName(String cheatSheetName) {
		_cheatSheetName = cheatSheetName;
	}
}
