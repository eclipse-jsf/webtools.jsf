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
     * Preference used to set modes for the page designer when displayed in a
     * sash editor.
     */
    public static final String SASH_EDITOR_MODE_PREF = PDPreferences.class
            .getName()
            + ".sash_editor_mode"; //$NON-NLS-1$
    /**
     * The number of pixels of artificial cell padding to use Default = 0
     */
    public final static String CSS_USE_ARTIFICAL_CELL_PADDING = PDPreferences.class
            .getName()
            + ".CSSArtificalCellPadding"; //$NON-NLS-1$
    /**
     * Whether or not to enable absolute positioning Default = false
     */
    public final static String CSS_ENABLE_ABSOLUTE_POSITIONING = PDPreferences.class
            .getName()
            + ".CSSEnableAbsolutePositioning"; //$NON-NLS-1$
    /**
     * The default value for absolute positioning enablement
     */
    public static final boolean DEFAULT_CSS_ENABLE_ABSOLUTE_POSITIONING = false;
    /**
     * The default value for artificial cell padding.
     */
    public static final int DEFAULT_CSS_USE_ARTIFICIAL_CELL_PADDING = 4;

    private static IPreferenceStore getPreferenceStore()
    {
        return PDPlugin.getDefault().getPreferenceStore();
    }

    /**
     * @return the css absolute positioning enablement flag.
     */
    public boolean isCssAbsolutePositioningEnabled()
    {
        final IPreferenceStore store = getPreferenceStore();
        return store.getBoolean(CSS_ENABLE_ABSOLUTE_POSITIONING);
    }

    /**
     * @return the css artificial cell padding preference
     */
    public int getCssArtificialCellPadding()
    {
        final IPreferenceStore store = getPreferenceStore();
        return store.getInt(CSS_USE_ARTIFICAL_CELL_PADDING);
    }

    @Override
    public void initializeDefaultPreferences()
    {
        // Set default HTML editor split vertical (i.e. with top and bottom
        // pane)
        IPreferenceStore store = getPreferenceStore();
        store.setDefault(SASH_EDITOR_MODE_PREF, HTMLEditor.MODE_SASH_VERTICAL);
        store.setDefault(CSS_USE_ARTIFICAL_CELL_PADDING,
                DEFAULT_CSS_USE_ARTIFICIAL_CELL_PADDING);
        store.setDefault(CSS_ENABLE_ABSOLUTE_POSITIONING,
                DEFAULT_CSS_ENABLE_ABSOLUTE_POSITIONING);
    }
}