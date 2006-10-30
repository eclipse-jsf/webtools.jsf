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
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 */
public class ShowAllAction extends Action {
	Preferences _preferences = null;

	/**
	 * @param pluginPreferences
	 */
	public ShowAllAction(Preferences pluginPreferences) {
		super(PDPlugin.getResourceString("ShowAllAction.ActionLabel.ShowAll")); //$NON-NLS-1$
		_preferences = pluginPreferences;
		boolean showAll = _preferences
				.getBoolean(IJMTConstants.PREF_PALETTE_SHOW_ALL);
		this.setChecked(showAll);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		boolean showAll = _preferences
				.getBoolean(IJMTConstants.PREF_PALETTE_SHOW_ALL);
		// toggle the show all status. preference will fire out change event,
		// and palettes
		// receive this event will refresh
		_preferences.setValue(IJMTConstants.PREF_PALETTE_SHOW_ALL, !showAll);
		this.setChecked(!showAll);
	}
}
