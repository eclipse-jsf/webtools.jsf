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
    
    /**
     * True if handling of JSP TagIndex changes are being traced
     */
    public static final boolean TRACE_JSPTAGREGISTRY_CHANGES;

    /**
     * True if the TLD registry manager tracing is enabled
     */
    public static final boolean TRACE_TLDREGISTRYMANAGER;

    private static final String KEY_DEBUG_ENABLED = "/debug";
    private static final String KEY_DESIGNTIME = "/designtime";
    private static final String KEY_DESIGNTIME_VIEW = KEY_DESIGNTIME + "/view";
    private static final String KEY_VIEW_JSPTAGINTROSPECTOR = KEY_DESIGNTIME_VIEW
            + "/jsptagintrospection";
    private static final String KEY_VIEW_JSPTAGREGISTRY = KEY_DESIGNTIME_VIEW
            + "/jsptagregistry";
    private static final String KEY_VIEW_JSPTAGREGISTRY_CHANGES =
        KEY_VIEW_JSPTAGREGISTRY + "/changes";
    private static final String KEY_VIEW_TLDREGISTRYMANAGER =
        KEY_DESIGNTIME_VIEW + "/tldregistrymanager";
    
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
            TRACE_JSPTAGREGISTRY_CHANGES = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGREGISTRY_CHANGES, false);
            TRACE_TLDREGISTRYMANAGER = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_TLDREGISTRYMANAGER, false);
        }
        else
        {
            TRACE_JSPTAGINTROSPECTOR = false;
            TRACE_JSPTAGREGISTRY = false;
            TRACE_JSPTAGREGISTRY_CHANGES = false;
            TRACE_TLDREGISTRYMANAGER = false;
        }
    }

    /**
     * @param message
     */
    public static void log(final String message)
    {
        System.out.println(message);
    }
    
    /**
     * @param msg A short label placed before the trace of t to show the source
     * @param t
     */
    public static void log(final String msg, final Throwable t)
    {
        System.out.printf("%s: Exception Trace:\n\n",msg);
        t.printStackTrace(System.out);
        System.out.print("\n\n\n");
    }

    private JSFCoreTraceOptions()
    {
        // no instantiation
    }
}
