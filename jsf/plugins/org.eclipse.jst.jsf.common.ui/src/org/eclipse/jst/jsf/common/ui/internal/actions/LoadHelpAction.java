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
package org.eclipse.jst.jsf.common.ui.internal.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

/**
 * This class is used to open the help page for a configuration param.
 * 
 * @author mengbo
 */
public class LoadHelpAction extends Action implements IExecutableExtension {
	private String _helpContextId = null;

	/**
	 * 
	 */
	public LoadHelpAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		IContext context = HelpSystem.getContext(_helpContextId);
		if (context != null) {
			IHelpResource[] topics = context.getRelatedTopics();
			if (topics != null && topics.length == 1) {
				PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(
						topics[0].getHref());
			} else {
				PlatformUI.getWorkbench().getHelpSystem().displayHelp(
						_helpContextId);
			}
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
		_helpContextId = config.getAttribute("actionparameters"); //$NON-NLS-1$
	}
}
