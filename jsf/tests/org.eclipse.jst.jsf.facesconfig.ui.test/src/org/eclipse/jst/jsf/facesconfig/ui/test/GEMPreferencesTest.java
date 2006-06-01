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

package org.eclipse.jst.jsf.facesconfig.ui.test;

import java.util.Iterator;

import junit.framework.Assert;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author sfshi
 * 
 */
public class GEMPreferencesTest extends FacesConfigEditorTest {

	private String GEM_PREFERENCE_ID = "org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences";

	private static class PreferenceDialogWrapper extends PreferenceDialog {
		public PreferenceDialogWrapper(Shell parentShell,
				PreferenceManager manager) {
			super(parentShell, manager);
		}

		protected boolean showPage(IPreferenceNode node) {
			return super.showPage(node);
		}
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences#GEMPreferences()}.
	 */
	public void testGEMPreferences() {

		PreferenceDialog dialog = null;
		PreferenceManager manager = PlatformUI.getWorkbench()
				.getPreferenceManager();
		dialog = new PreferenceDialogWrapper(getShell(), manager);
		dialog.create();

		for (Iterator iterator = manager.getElements(
				PreferenceManager.PRE_ORDER).iterator(); iterator.hasNext();) {
			IPreferenceNode node = (IPreferenceNode) iterator.next();
			if (node.getId().equals(GEM_PREFERENCE_ID)) {
				((PreferenceDialogWrapper) dialog).showPage(node);
			}
		}
		Assert.assertNotNull(dialog);
		dialog.setBlockOnOpen(false);
		dialog.open();

		Assert.assertTrue(dialog.getSelectedPage() instanceof GEMPreferences);
		GEMPreferences page = (GEMPreferences) dialog.getSelectedPage();
		assertNotNull(page);
		// there is no much open mehtods for testing....
	}

	protected static Shell getShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}
