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
package org.eclipse.jst.jsf.common.ui.internal.actions;

import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.ui.PlatformUI;

public class LoadSingleHelpAction extends LoadHelpAction {

	public void run() {

		IContext context = HelpSystem.getContext(getContextId());
		if (context != null) {
			IHelpResource[] topics = context.getRelatedTopics();
			if (topics != null && topics.length > 0) {
				PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(
						topics[0].getHref());
			} else {
				PlatformUI.getWorkbench().getHelpSystem().displayHelp(
                        getContextId());
			}
		}

	}

}
