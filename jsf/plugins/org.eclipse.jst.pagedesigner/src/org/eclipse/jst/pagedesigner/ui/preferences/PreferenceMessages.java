/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.ui.preferences;

import org.eclipse.osgi.util.NLS;

/*package*/ class PreferenceMessages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.ui.preferences.PreferenceMessages"; //$NON-NLS-1$
    /**
     * See PreferenceMessages.properties
     */
    public static String PDPreferences_description;
    /**
     * See PreferenceMessages.properties
     */
    public static String EditorPreferences_LABEL_CSSArtificalCellPadding;
    /**
     * See PreferenceMessages.properties
     */
    public static String EditorPreferences_LABEL_CSSEnableAbsolutePositioning;
    /**
     * See PreferenceMessages.properties
     */
    public static String EditorPreferences_LABEL_HidePreviewPageForContentTypes;

    private PreferenceMessages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }

}
