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

    private PreferenceMessages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }

}
