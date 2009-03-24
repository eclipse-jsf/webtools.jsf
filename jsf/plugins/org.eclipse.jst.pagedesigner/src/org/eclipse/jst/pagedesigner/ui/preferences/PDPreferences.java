/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;

/**
 * Utility class for handling preferences related to the Web Page Editor plug-in.
 * <br>
 * <p><b>Provisional API - subject to change</b></p>
 */
public class PDPreferences extends AbstractPreferenceInitializer {

	/**
	 * Preference used to set modes for the page designer when displayed
	 * in a sash editor.
	 */
	public static final String SASH_EDITOR_MODE_PREF = 
		PDPreferences.class.getName() + ".sash_editor_mode"; //$NON-NLS-1$

	private static IPreferenceStore getPreferenceStore() {
		return PDPlugin.getDefault().getPreferenceStore();
	}
	
	@Override
	public void initializeDefaultPreferences() {
		// Set default HTML editor split vertical (i.e. with top and bottom pane)
		IPreferenceStore store = getPreferenceStore();
		store.setDefault(SASH_EDITOR_MODE_PREF, HTMLEditor.MODE_SASH_VERTICAL);
	}
}