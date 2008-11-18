/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
     * True if tag introspector performance tracing is enabled
     */
    public static final boolean TRACE_JSPTAGINTROSPECTOR_PERF;

    /**
     * True if the JSP tag registry tracing is enabled
     */
    public static final boolean TRACE_JSPTAGREGISTRY;
    
    /**
     * True if handling of JSP TagIndex changes are being traced
     */
    public static final boolean TRACE_JSPTAGREGISTRY_CHANGES;

    /**
     * True if the JSP persistent jsp tag resolver strategy is being traced.
     */
    public static final boolean TRACE_JSPTAGPERSISTENCE;

    /**
     * True if the TLD registry manager tracing is enabled
     */
    public static final boolean TRACE_TLDREGISTRYMANAGER;
    
    
    /**
     * True if the TLD registry performance tracing is enabled
     */
    public static final boolean TRACE_JSPTAGREGISTRY_PERF;

    private static final String KEY_DEBUG_ENABLED = "/debug"; //$NON-NLS-1$
    private static final String KEY_DESIGNTIME = "/designtime"; //$NON-NLS-1$
    private static final String KEY_DESIGNTIME_VIEW = KEY_DESIGNTIME + "/view"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGINTROSPECTOR = KEY_DESIGNTIME_VIEW
            + "/jsptagintrospection"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGINTROSPECTOR_PERF = 
        KEY_VIEW_JSPTAGINTROSPECTOR + "/perf"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGREGISTRY = KEY_DESIGNTIME_VIEW
            + "/jsptagregistry"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGPERSISTENCE = KEY_DESIGNTIME_VIEW
            + "/jsptagpersistence"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGREGISTRY_CHANGES =
        KEY_VIEW_JSPTAGREGISTRY + "/changes"; //$NON-NLS-1$
    private static final String KEY_VIEW_JSPTAGREGISTRY_PERF =
        KEY_VIEW_JSPTAGREGISTRY + "/perf"; //$NON-NLS-1$
    private static final String KEY_VIEW_TLDREGISTRYMANAGER =
        KEY_DESIGNTIME_VIEW + "/tldregistrymanager"; //$NON-NLS-1$

    
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
            TRACE_JSPTAGREGISTRY_PERF = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGREGISTRY_PERF, false);
            TRACE_JSPTAGINTROSPECTOR_PERF = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGINTROSPECTOR_PERF, false);
            TRACE_JSPTAGPERSISTENCE = debugOptions.getBooleanOption(
                    JSFCorePlugin.PLUGIN_ID + KEY_VIEW_JSPTAGPERSISTENCE, false);
        }
        else
        {
            TRACE_JSPTAGINTROSPECTOR = false;
            TRACE_JSPTAGREGISTRY = false;
            TRACE_JSPTAGREGISTRY_CHANGES = false;
            TRACE_TLDREGISTRYMANAGER = false;
            TRACE_JSPTAGREGISTRY_PERF = false;
            TRACE_JSPTAGINTROSPECTOR_PERF = false;
            TRACE_JSPTAGPERSISTENCE = false;
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
        System.out.printf("%s: Exception Trace:\n\n",msg); //$NON-NLS-1$
        t.printStackTrace(System.out);
        System.out.print("\n\n\n"); //$NON-NLS-1$
    }

    private JSFCoreTraceOptions()
    {
        // no instantiation
    }
}
