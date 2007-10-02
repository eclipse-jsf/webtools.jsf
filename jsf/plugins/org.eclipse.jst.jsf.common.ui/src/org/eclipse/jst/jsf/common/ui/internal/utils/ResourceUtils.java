/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;

/**
 * Give easy access to a resource bundle.
 * 
 * @author mengbo
 */
public abstract class ResourceUtils {
	private static Logger _log = JSFUICommonPlugin.getLogger(ResourceUtils.class);
	/**
	 * the resource bundle accessible by all children
	 */
	protected ResourceBundle _resources;

	/**
	 * Empty Constructor.
	 */
	protected ResourceUtils() {
        // restrict instantiation
	}

	/**
	 * Easy way to set the bundle and get a fatal log messages and an alert if
	 * the resource bundle is not found.
	 * 
	 * @param resource
	 * @param bundleLocation
	 */
	protected void setBundle(ResourceBundle resource, String bundleLocation) {
		_resources = resource;
		if (_resources == null) {
			// log.ResourceUtils=Missing Resource Bundle "{0}".
			_log.error("log.ResourceUtils", bundleLocation);
			// pluginName=Web Application Development Common
			JSFUICommonPlugin.getAlerts().error("pluginName", "log.ResourceUtils",
					bundleLocation);
		}
	}

	/**
	 * Get the property defined in the resource bundle for the given key. This
	 * property should be an integer. If none is defined
	 * (MissingResourceException), return the default.
	 * 
	 * @param key
	 *            the key in the resource bundle.
	 * @param defaultValue
	 *            default int to return if no value is found for the key.
	 * @return the integer value for key or defaultValue if none
	 */
	public int getValue(String key, int defaultValue) {
		String stringValue = getString(key);
		if (stringValue != null) {
			try {
				return Integer.parseInt(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	/**
	 * Get the property defined in the resource bundle for the given key. This
	 * property should be an long. If none is defined
	 * (MissingResourceException), return the default.
	 * 
	 * @param key
	 *            the key in the resource bundle.
	 * @param defaultValue
	 *            default long to return if no value is found for the key.
	 * @return the long value for key or defaultValue if none
	 */
	public long getValue(String key, long defaultValue) {
		String stringValue = getString(key);
		if (stringValue != null) {
			try {
				return Long.parseLong(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	/**
	 * Get the property defined in the resource bundle for the given key. This
	 * property should be boolean ("true" of "false"). If none is defined
	 * (MissingResourceException), return the default.
	 * 
	 * @param key
	 *            the key in the resource bundle.
	 * @return true if there is a resource corresponding to key
	 */
	public boolean isResource(String key) {
		return getString(key).equals("" + true);
	}

	/**
	 * Get the property defined in the resource bundle for the given key. If
	 * none is defined (MissingResourceException), return null.
	 * 
	 * @param key
	 *            the key in the resource bundle.
	 * @return the string value for key or key if not found
	 */
	public String getString(String key) {
		try {
			return _resources.getString(key);
		} catch (MissingResourceException ee) {
			return key;
		}
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @param arg1
	 *            the second argument.
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object arg0, Object arg1) {
		Object[] args = new Object[2];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @param arg1
	 *            the second argument.
	 * @param arg2
	 *            the third argument.
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object arg0, Object arg1, Object arg2) {
		Object[] args = new Object[3];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @param arg1
	 *            the second argument.
	 * @param arg2
	 *            the third argument.
	 * @param arg3
	 *            the forth argument.
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object arg0, Object arg1, Object arg2,
			Object arg3) {
		Object[] args = new Object[4];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		args[3] = arg3;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @param arg1
	 *            the second argument.
	 * @param arg2
	 *            the third argument.
	 * @param arg3
	 *            the forth argument.
	 * @param arg4
	 *            the forth argument.
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object arg0, Object arg1, Object arg2,
			Object arg3, Object arg4) {
		Object[] args = new Object[5];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		args[3] = arg3;
		args[4] = arg4;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param args
	 *            an array of arguments
	 * @return the formated string with the argument inline.
	 */
	public String getString(String key, Object[] args) {
		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Use in the try-finally idiom for inputStream to ensure close
	 * and suppress exceptions on close
	 * @param inputStream
	 */
	public static void ensureClosed(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				// Ignore
			}
		}

	}
}
