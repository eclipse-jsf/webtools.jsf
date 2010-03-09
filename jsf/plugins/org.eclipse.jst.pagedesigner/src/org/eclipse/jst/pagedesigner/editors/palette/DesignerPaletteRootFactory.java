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

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * Factory for creating DesignerPaletteRoots
 * @author mengbo 
 */
public class DesignerPaletteRootFactory {
	/** Default palette size. */
	private static final int DEFAULT_PALETTE_SIZE = 125;

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "DesignerPaletteRootFactory.Location"; //$NON-NLS-1$

	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "DesignerPaletteRootFactory.Size"; //$NON-NLS-1$

	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "DesignerPaletteRootFactory.State"; //$NON-NLS-1$

	/**
	 * Return a {@link FlyoutPreferences} instance used to save/load the preferences of
	 * a flyout palette.
	 * @return FlyoutPreferences
	 */
	public static FlyoutPreferences createPalettePreferences() {
		// set default flyout palette preference values, in case the preference
		// store
		// does not hold stored values for the given preferences
		getPreferenceStore().setDefault(PALETTE_DOCK_LOCATION, -1);
		getPreferenceStore().setDefault(PALETTE_STATE, -1);
		getPreferenceStore().setDefault(PALETTE_SIZE, DEFAULT_PALETTE_SIZE);

		return new FlyoutPreferences() {
			public int getDockLocation() {
				return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
			}

			public int getPaletteState() {
				return getPreferenceStore().getInt(PALETTE_STATE);
			}

			public int getPaletteWidth() {
				return getPreferenceStore().getInt(PALETTE_SIZE);
			}

			public void setDockLocation(int location) {
				getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
			}

			public void setPaletteState(int state) {
				getPreferenceStore().setValue(PALETTE_STATE, state);
			}

			public void setPaletteWidth(int width) {
				getPreferenceStore().setValue(PALETTE_SIZE, width);
			}
		};
	}

	/**
	 * Returns the preference store for the PDPlugin.
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore()
	 */
	private static IPreferenceStore getPreferenceStore() {
		return PDPlugin.getDefault().getPreferenceStore();
	}

//	/**
//	 * Creates the PaletteRoot and adds all palette elements. Use this factory
//	 * method to create a new palette for your graphical editor.
//	 * <p>
//	 * <i>Note: cannot support facelets</i>
//	 * @param project 
//	 * @return a new PaletteRoot
//	 * @deprecated - use createPaletteRoot(IFile file)
//	 */
//	public static PaletteRoot createPaletteRoot(IProject project) {
//		PaletteItemManager manager = PaletteItemManager.getInstance(project);
//		if (manager == null) {
//			return null;
//		}
//		manager.reset();
//		PaletteRoot palette = new DesignerPaletteRoot(manager);
//		return palette;
//	}
	
	/**
	 * Creates the PaletteRoot and adds all palette elements. Use this factory
	 * method to create a new palette for your graphical editor.
	 * @param file 
	 * @return a new PaletteRoot
	 */
	public static PaletteRoot createPaletteRoot(final IFile file) {
		return new DesignerPaletteRoot(file);
	}
}
