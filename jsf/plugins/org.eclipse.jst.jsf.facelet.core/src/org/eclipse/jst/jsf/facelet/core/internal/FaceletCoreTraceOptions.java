package org.eclipse.jst.jsf.facelet.core.internal;

import org.eclipse.core.runtime.Platform;

/**
 * Defines that standard runtime trace options for debugging. See .options file
 * for definitions.
 * 
 * @author cbateman
 * 
 */
public final class FaceletCoreTraceOptions
{
    /**
     * True if debug tracing is enabled. Other tracing cannot be enabled unless
     * this is enabled.
     */
    public static final boolean ENABLED;

    /**
     * True if the registry manager tracing is enabled
     */
    public static final boolean TRACE_REGISTRYMANAGER;

    /**
     * True if the facet installer is being traced.
     */
    public static final boolean TRACE_FACETINSTALLDELEGATE;
    /**
     * True if the facet uninstaller is being traced
     */
    public static final boolean TRACE_FACETUNINSTALLDELEGATE;
    /**
     * True if the base facet change delegate is being traced
     */
    public static final boolean TRACE_FACETCHANGEDELEGATE;

    private static final String KEY_DEBUG_ENABLED = "/debug"; //$NON-NLS-1$
//    private static final String KEY_VIEW_TAGREGISTRY = "/jsptagregistry";
//    private static final String KEY_VIEW_JSPTAGREGISTRY_CHANGES =
//        KEY_VIEW_TAGREGISTRY + "/changes";
    private static final String KEY_VIEW_REGISTRYMANAGER = "/registrymanager"; //$NON-NLS-1$
    private static final String KEY_FACETINSTALLDELEGATE = "/facetinstalldelegate"; //$NON-NLS-1$
    private static final String KEY_FACETUNINSTALLDELEGATE = "facetuninstalldelegate"; //$NON-NLS-1$
    private static final String KEY_FACETCHANGEDELEGATE = "facetchangedelegate"; //$NON-NLS-1$

    static
    {
        ENABLED = getBooleanOption(FaceletCorePlugin.PLUGIN_ID
                        + KEY_DEBUG_ENABLED);

        if (ENABLED)
        {
            TRACE_REGISTRYMANAGER = getBooleanOption(
                    FaceletCorePlugin.PLUGIN_ID + KEY_VIEW_REGISTRYMANAGER);
            TRACE_FACETINSTALLDELEGATE = getBooleanOption(
                    FaceletCorePlugin.PLUGIN_ID + KEY_FACETINSTALLDELEGATE);
            TRACE_FACETUNINSTALLDELEGATE = getBooleanOption(
                    FaceletCorePlugin.PLUGIN_ID + KEY_FACETUNINSTALLDELEGATE);
            TRACE_FACETCHANGEDELEGATE = getBooleanOption(
                    FaceletCorePlugin.PLUGIN_ID + KEY_FACETCHANGEDELEGATE);
        }
        else
        {
            TRACE_REGISTRYMANAGER = false;
            TRACE_FACETINSTALLDELEGATE = false;
            TRACE_FACETUNINSTALLDELEGATE = false;
            TRACE_FACETCHANGEDELEGATE = false;
        }
    }
    
    private static boolean getBooleanOption(String key)
    {
    	Boolean enabled = Boolean.valueOf(Platform.getDebugOption(key));
    	return enabled != null ? enabled.booleanValue() : false;
    }

    /**
     * @param message
     */
    public static void log(final String message)
    {
        System.out.println(message);
    }

    private FaceletCoreTraceOptions()
    {
        // no instantiation
    }
}
