/***************************************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.facesconfig.FacesConfigPlugin;


/**
 * Small convenience class to log messages to plugin's log file and also, if desired,
 * the console. This class should only be used by classes in this plugin. Other
 * plugins should make their own copy, with appropriate ID.
 */
public class Logger {
	private static Plugin fPlugin;
	private static String fPluginId;
	
	/**
	 * Controls whether or not log/trace messages also go to the console.
	 * Normally, leave this as false.  Change to true temporarily, if desired, for debugging.
	 */
	private static boolean displayToConsole = false;

	private static final String TRACEFILTER_LOCATION = "/debug/tracefilter"; //$NON-NLS-1$

	private static final int OK = IStatus.OK; // 0
	private static final int INFO = IStatus.INFO; // 1
	private static final int WARNING = IStatus.WARNING; // 2
	private static final int ERROR = IStatus.ERROR; // 4

	private static final int OK_DEBUG = 200 + OK;
	private static final int INFO_DEBUG = 200 + INFO;
	private static final int WARNING_DEBUG = 200 + WARNING;
	private static final int ERROR_DEBUG = 200 + ERROR;

	/**
	 * Adds message to log.
	 * @param level severity level of the message (OK, INFO, WARNING, ERROR, OK_DEBUG, INFO_DEBUG, WARNING_DEBUG, ERROR_DEBUG)
	 * @param message text to add to the log
	 * @param exception exception thrown
	 */
	protected static void _log(int level, String message, Throwable exception) {
		if (level == OK_DEBUG || level == INFO_DEBUG || level == WARNING_DEBUG || level == ERROR_DEBUG) {
			if (!isDebugging())
				return;
		}

		int severity = IStatus.OK;
		switch (level) {
			case INFO_DEBUG :
			case INFO :
				severity = IStatus.INFO;
				break;
			case WARNING_DEBUG :
			case WARNING :
				severity = IStatus.WARNING;
				break;
			case ERROR_DEBUG :
			case ERROR :
				severity = IStatus.ERROR;
		}
		message = (message != null) ? message : "null"; //$NON-NLS-1$
		Status statusObj = new Status(severity, getPluginId(), severity, message, exception);
		getPlugin().getLog().log(statusObj);
	}

	/**
	 * Prints message to log if category matches /debug/tracefilter option.
	 * @param message text to print
	 * @param category category of the message, to be compared with /debug/tracefilter
	 * @param exception 
	 */
	protected static void _trace(String category, String message, Throwable exception) {
		if (isTracing(category)) {
			message = (message != null) ? message : "null"; //$NON-NLS-1$
			Status statusObj = new Status(IStatus.OK, getPluginId(), IStatus.OK, message, exception);
			getPlugin().getLog().log(statusObj);
		}
	}

	/**
	 * @return true if the plugin for this logger is debugging
	 */
	public static boolean isDebugging() {
		return getPlugin().isDebugging();
	}

	/**
	 * Determines if currently tracing a category
	 * @param category
	 * @return true if tracing category, false otherwise
	 */
	public static boolean isTracing(String category) {
		if (!isDebugging())
			return false;

		String traceFilter = Platform.getDebugOption(getPluginId() + TRACEFILTER_LOCATION);
		if (traceFilter != null) {
			StringTokenizer tokenizer = new StringTokenizer(traceFilter, ","); //$NON-NLS-1$
			while (tokenizer.hasMoreTokens()) {
				String cat = tokenizer.nextToken().trim();
				if (category.equals(cat)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param level
	 * @param message
	 */
	public static void log(int level, String message) {
		_log(level, message, null);
	}

	/**
	 * @param level
	 * @param message
	 * @param exception
	 */
	public static void log(int level, String message, Throwable exception) {
		_log(level, message, exception);
	}
	
	/**
	 * @param source
	 * @param message
	 */
	public static void log(Object source, String message) {
		doLog(source, message, null);
	}

	/**
	 * @param source
	 * @param message
	 * @param throwable
	 */
	public static void log(Object source, String message, Throwable throwable) {
		doLog(source, message, throwable);
	}

	/**
	 * @param source
	 * @param throwable
	 */
	public static void log(Object source, Throwable throwable) {
		doLog(source, null, throwable);
	}
	
	/**
	 * @param message
	 */
	public static void log(String message) {
		doLog(message, null);
	}
	
	private static void doLog(String message, Throwable exception) {
		_log(ERROR, message, exception);
	}
	
	private static void doLog(Object source, String message, Throwable exception) {
		_log(ERROR, getMessageFor(source, message), exception);
	}
	
	private static final String getMessageFor(Object source, String message) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(source);
		if (message != null) {
			buffer.append(": "); //$NON-NLS-1$
			buffer.append(message);
		}
		return buffer.toString();
	}

	/**
	 * @param message
	 * @param exception
	 */
	public static void logException(String message, Throwable exception) {
		_log(ERROR, message, exception);
	}

	/**
	 * @param exception
	 */
	public static void logException(Throwable exception) {
		_log(ERROR, exception.getMessage(), exception);
	}

	/**
	 * @param category
	 * @param message
	 * @param exception
	 */
	public static void traceException(String category, String message, Throwable exception) {
		_trace(category, message, exception);
	}

	/**
	 * @param category
	 * @param exception
	 */
	public static void traceException(String category, Throwable exception) {
		_trace(category, exception.getMessage(), exception);
	}

	/**
	 * @param category
	 * @param message
	 */
	public static void trace(String category, String message) {
		_trace(category, message, null);
	}

	private static Plugin getFacesPlugin() {
		return FacesConfigPlugin.getPlugin();
	}

	private static Plugin getPlugin() {

		if (fPlugin == null) {
			fPlugin = getFacesPlugin();
		}
		return fPlugin;
	}

	/**
	 * @return the plugin id
	 */
	private static String getPluginId() {

		if (fPluginId == null) {
			fPluginId = ((Plugin) (FacesConfigPlugin.getPlugin())).getBundle().getSymbolicName() ;
		}
		return fPluginId;
	}
	
	private static final void doConsole(String message, Throwable throwable) {
		if (displayToConsole) {
			PrintStream out = System.out;
			out.println(message);
			if (throwable != null)
				throwable.printStackTrace(out);
		}
	}
	
	private static final void doTrace(String category, Object source, String message) {
		message = getMessageFor(source, message);
		try {
			_trace(category, message, null);
		} catch (Exception ignored) {
			// Empty block intended.
		} finally {
			doConsole(message, null);
		}
	}
	
	/**
	 * @param category
	 * @param source
	 * @param message
	 */
	public static void trace(String category, Object source, String message) {
		doTrace(category, source, message);
	}

}