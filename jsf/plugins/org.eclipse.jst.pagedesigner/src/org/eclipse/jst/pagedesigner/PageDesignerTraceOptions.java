/*******************************************************************************
 * Copyright (c) 2001, 2013 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner;

import org.eclipse.core.runtime.Platform;

/**
 * Defines that standard runtime trace options for debugging. See .options file
 * for definitions.
 * 
 * NOT API
 * 
 * @author cbateman
 * 
 */
public final class PageDesignerTraceOptions
{
    /**
     * True if debug tracing is enabled. Other tracing cannot be enabled unless
     * this is enabled.
     */
    public static final boolean ENABLED;

    /**
     * True if tag converter ext load tracing is enabled
     */
    public static final boolean TRACE_CONVERTERLOAD;
    /**
     * True if tag converter selection tracing is enabled
     */
    public static final boolean TRACE_CONVERTERSELECT;
    /**
     * True if tag element edit ext load tracing is enabled
     */
    public static final boolean TRACE_ELEMENTEDITLOAD;
    /**
     * True if tag element edit selection tracing is enabled
     */
    public static final boolean TRACE_ELEMENTEDITSELECTION;

    private static final String KEY_DEBUG_ENABLED = "/debug"; //$NON-NLS-1$
    private static final String KEY_CONVERTER = KEY_DEBUG_ENABLED+"/converter"; //$NON-NLS-1$
    private static final String KEY_CONVERTER_LOAD = KEY_CONVERTER + "/load"; //$NON-NLS-1$
    private static final String KEY_CONVERTER_SELECTION = KEY_CONVERTER + "/selection"; //$NON-NLS-1$

    private static final String KEY_ELEMENTEDIT = KEY_DEBUG_ENABLED+"/elementedit"; //$NON-NLS-1$
    private static final String KEY_ELEMENTEDIT_LOAD = KEY_ELEMENTEDIT + "/load"; //$NON-NLS-1$
    private static final String KEY_ELEMENTEDIT_SELECTION = KEY_ELEMENTEDIT + "/selection"; //$NON-NLS-1$

    
    static
    {
        final String pluginId = PDPlugin.getPluginId();
        
        ENABLED = getBooleanOption(pluginId
                + KEY_DEBUG_ENABLED);
		if (ENABLED) {
			TRACE_CONVERTERLOAD = getBooleanOption(pluginId
					+ KEY_CONVERTER_LOAD);
			TRACE_CONVERTERSELECT = getBooleanOption(pluginId
					+ KEY_CONVERTER_SELECTION);
			TRACE_ELEMENTEDITLOAD = getBooleanOption(pluginId
					+ KEY_ELEMENTEDIT_LOAD);
			TRACE_ELEMENTEDITSELECTION = getBooleanOption(pluginId
					+ KEY_ELEMENTEDIT_SELECTION);
		} else {
			TRACE_CONVERTERLOAD = false;
			TRACE_CONVERTERSELECT = false;
			TRACE_ELEMENTEDITLOAD = false;
			TRACE_ELEMENTEDITSELECTION = false;
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

    private PageDesignerTraceOptions()
    {
        // no instantiation
    }
}
