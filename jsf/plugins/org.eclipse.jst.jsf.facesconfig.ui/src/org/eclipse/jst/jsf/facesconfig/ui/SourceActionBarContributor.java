/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.SubActionBars;
import org.eclipse.wst.xml.ui.internal.actions.ActionContributorXML;

/**
 * @author hmeng
 */

public class SourceActionBarContributor extends ActionContributorXML {

	private SubActionBars sourceActionBars;

	public void init(IActionBars bars) {
		super.init(bars);
		sourceActionBars = new SubActionBars(bars);

	}

	public void dispose() {
		if (sourceActionBars != null) {
			sourceActionBars.dispose();
		}
		super.dispose();
	}

	public void active(boolean active) {
		IActionBars rootBars = getActionBars();
		if (active) {
			sourceActionBars.activate();
		} else {
			sourceActionBars.deactivate(true);
		}
		rootBars.clearGlobalActionHandlers();
		if (active) {
			Map handlers = sourceActionBars.getGlobalActionHandlers();
			if (handlers != null) {
				Set keys = handlers.keySet();
				for (Iterator iter = keys.iterator(); iter.hasNext();) {
					String id = (String) iter.next();
					rootBars.setGlobalActionHandler(id, (IAction) handlers
							.get(id));
				}
			}
		}
		rootBars.updateActionBars();
	}

}
