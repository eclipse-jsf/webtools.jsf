package org.eclipse.jst.jsf.core.internal;

import org.eclipse.osgi.framework.debug.FrameworkDebugOptions;
import org.eclipse.osgi.service.debug.DebugOptions;

/**
 * Defines that standard runtime trace options for debugging. See .options file
 * for definitions.
 * 
 * @author cbateman
 * 
 */
public final class JSFCoreTraceOptions
{
    /**
     * True if debug tracing is enabled. Other tracing cannot be enabled unless
     * this is enabled.
     */
    public static final boolean ENABLED;

    /**
     * True if tag introspector tracing is enabled
     */
    public static final boolean TRACE_JSPTAGINTROSPECTOR;

    /**
     * True if the JSP tag registry tracing is enabled
     */
    public static final boolean TRACE_JSPTAGREGISTRY;

    private static final String KEY_DEBUG_ENABLED = "/debug";
    private static final String KEY_DESIGNTIME = "/designtime";
    private static final String KEY_DESIGNTIME_VIEW = KEY_DESIGNTIME + "/view";
    private static final String KEY_VIEW_JSPTAGINTROSPECTOR = KEY_DESIGNTIME_VIEW
            + "/jsptagintrospection";
    private static final String KEY_VIEW_JSPTAGREGISTRY = KEY_DESIGNTIME_VIEW
            + "/jsptagregistry";
    static
    {
        final DebugOptions debugOptions = FrameworkDebugOptions.getDefault();

        ENABLED = debugOptions != null
                && debugOptions.getBooleanOption(JSFCorePlugin.PLUGIN_ID
                        + KEY_DEBUG_ENABLED, false);

        if (ENABLED)
        {
            TRACE_JSPTAGINTROSPECTOR = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGINTROSPECTOR, false);
            TRACE_JSPTAGREGISTRY = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGREGISTRY, false);
        }
        else
        {
            TRACE_JSPTAGINTROSPECTOR = false;
            TRACE_JSPTAGREGISTRY = false;
        }
    }

    /**
     * @param message
     */
    public static void log(final String message)
    {
        System.out.println(message);
    }

    private JSFCoreTraceOptions()
    {
        // no instantiation
    }
}
