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


package org.eclipse.jst.jsf.facesconfig.ui.preference;

import org.eclipse.osgi.util.NLS;

/**
 * The NLS message manager for PreferenceMessages
 * 
 */
public final class PreferenceMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facesconfig.ui.preference.PreferenceMessages";

	private PreferenceMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
	}

	/**
	 * see PreferenceMessages.properties
	 */
	public static String CanvasPreferenceTab_LABEL_UseSystemColors;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_BackgroundColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_Canvas;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_GridHeight;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_GridLineColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_GridWidth;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_IconLabelFont;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_IconGroup;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_InputPortColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_OutputPortColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineGroup;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_ShowLineLabels;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineLabelColor;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineLabelFont;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineRouting;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LineWidth;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_SnapToGeometry;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_SnapToGrid;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_UndoStackSize;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Direct;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Manhattan;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Manual;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_LabelPlacement;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Top;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Bottom;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Left;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Right;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferenceTab_LABEL_IconSize;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Small;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Medium;

    /**
     * see PreferenceMessages.properties
     */
	public static String CanvasPreferences_LABEL_Large;

    /**
     * label property for check box that selects whether or not
     * the introduction section editor should be loaded.
     */
    public static String EditorPreferences_LABEL_ShowIntroEditor;

}
