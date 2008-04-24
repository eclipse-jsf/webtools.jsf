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
package org.eclipse.jst.jsf.common.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.Alerts;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * The main plugin class to be used in the desktop.
 */
public class JSFUICommonPlugin extends AbstractUIPlugin {
	// Properties contains general properties and defaults to preferences.
	private static final String PROPERTIES = "default.properties"; //$NON-NLS-1$
	// preferences will
	// contain this string
	// in the key.

	private static final String P_CONSOLE_LOGGING = "console.logging.on"; //$NON-NLS-1$

	private static final String P_CONSOLE_LOG_LEVEL = "console.logging.max.level"; //$NON-NLS-1$

	private static final String P_ECLIPSE_LOGGING = "eclipse.logging.on"; //$NON-NLS-1$

	private static final String P_ECLIPSE_LOG_LEVEL = "eclipse.logging.max.level"; //$NON-NLS-1$

	private static final String P_FILE_LOGGING = "file.logging.on"; //$NON-NLS-1$

	private static final String P_FILE_LOG_LEVEL = "file.logging.max.level"; //$NON-NLS-1$

	private static final String P_FILE_PATH = "file.logging.path"; //$NON-NLS-1$

	private static final String P_FILE_CLEAR = "file.logging.startup.clear"; //$NON-NLS-1$

	private static final String P_FILE_ROLLOVER_FREQUENCY = "file.logging.rollover.frequency"; //$NON-NLS-1$

	private static final int DEBUG_LEVEL = 0;

	private static final int INFO_LEVEL = 1;

	private static final int WARN_LEVEL = 2;

	private static final int ERROR_LEVEL = 3;

	private static final int FATAL_LEVEL = 4;
	
	// What version of the platform are we on.
	private static boolean TWO_DOT_ONE;

	private static boolean THREE_DOT;

	private static String _version;

	// The shared instance.
	private static JSFUICommonPlugin _plugin;

	private Logger _log;

	private ResourceBundle _resourceBundle;

	private Properties _properties;

	private URL _pluginBase;

	private static Alerts _alerts;

	/**
	 * The constructor.
	 */
	public JSFUICommonPlugin() {
		super();
		_plugin = this;
		versionCheck();
	}

	private void versionCheck() {
		String version = (String) ResourcesPlugin.getPlugin().getBundle()
				.getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);
		Version identifier = new Version(version);

		TWO_DOT_ONE = ((identifier.getMajor() == 2) && (identifier
				.getMinor() == 1));
		THREE_DOT = (identifier.getMajor() == 3);
		_version = identifier.toString();
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// This plugin is for 3.0 only, do a version check.
		if (isThreeDot() == false) {
			throw new CoreException(new Status(IStatus.ERROR, getBundle()
					.getSymbolicName(), IStatus.OK,
					"Requires eclipse version 3.x", null)); //$NON-NLS-1$
		}

		try {
			// get resource bundle.
			_resourceBundle = ResourceBundle
					.getBundle("org.eclipse.jst.jsf.common.ui.CommonResources"); //$NON-NLS-1$
			_alerts = new Alerts(this, _resourceBundle);

			// get properties.
			_properties = new Properties();
			InputStream input = null;
			_pluginBase = getBundle().getEntry("/"); //$NON-NLS-1$
			try {
				input = (new URL(_pluginBase, PROPERTIES)).openStream();
				_properties.load(input);
			} finally {
				try {
					input.close();
				} catch (IOException ee)// NOPMD
				{
					// nothing to do when IOException throwed in closing files.
				}
			}

			// set up logging for this plugin and everthing under it.
			_log = new Logger(this.getBundle(), _resourceBundle);

			// NOTE: add in any other plugin code statup HERE!!!!

			// log.CommonPlugin=Web Application Development Common Plugin
			// initialized on eclipse version {0}.

			// log.info("log.CommonPlugin", version);

		} catch (Exception ee) {
			// only log if the logger was configured correctly.
			if (_log != null) {
				_log
						.error(
								"log.msg", //$NON-NLS-1$
								"Problems starting plug-in Web Application Development Common.", //$NON-NLS-1$
								ee);
			}

			throw new CoreException(
					new Status(
							IStatus.ERROR,
							getBundle().getSymbolicName(),
							IStatus.OK,
							"Problems starting plug-in Web Application Development Common", //$NON-NLS-1$
							ee));
		}
	}

	/**
	 * Returns the shared instance.
	 * @return the plugin instance
	 */
	public static JSFUICommonPlugin getDefault() {
		return _plugin;
	}

	/**
	 * get the alerts objects associated with this plugin for alerting the user.
	 * 
	 * @return the alerts object
	 */
	public static Alerts getAlerts() {
		return _alerts;
	}

	/**
	 * Returns a logger for the new class using this plugin for reference.
	 * @param theClass 
	 * @return the logger
	 */
    // TODO: theClass is never used!!
	public static Logger getLogger(Class theClass) {
		return getDefault().getRootLogger();
	}

	/**
	 * Returns the plugin's root logger
	 * @return the root logger
	 */
	public Logger getRootLogger() {
		return _log;
	}

	/**
	 * Returns this plugin's unique identifier
	 * 
	 * @return this plugin's unique identifier
	 * 
	 */
	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * Returns the plugin's resource bundle,
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return _resourceBundle;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 * @param key 
	 * @return the resource string
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = JSFUICommonPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's descriptor's resource bundle,
	 * @return the bundle
	 */
	public ResourceBundle getPluginDecriptorBundle() {
		return Platform.getResourceBundle(getDefault().getBundle());
	}

	/**
	 * Returns the plugin's default properties. These are normally used for
	 * default preferences.
	 * @return the properties
	 */
	public Properties getProperties() {
		return _properties;
	}

	/**
	 * Returns a shared image for the given name
	 * <p>
	 * Note: Images returned from this method will be automitically disposed of
	 * when this plug-in shuts down. Callers must not dispose of these images
	 * themselves.
	 * </p>
	 * 
	 * @param name
	 *            the image name found in /icons (with extension)
	 * @return the image, null on error or not found.
	 */
	public Image getImage(String name) {
		if (name == null) {
			return null;
		}

		ImageRegistry images = getImageRegistry();
		Image image = images.get(name);
		if (image == null) {
			try {
				ImageDescriptor id = ImageDescriptor.createFromURL(new URL(
						_pluginBase, "icons/" + name)); //$NON-NLS-1$
				images.put(name, id);

				image = images.get(name);
			} catch (MalformedURLException ee) {
				// log.CommonPlugin.image.error=Image {0} not found.
				_log.error("log.msg", "log.CommonPlugin.image.error", name, ee); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return image;
	}

	/**
	 * Returns a shared ImageDescriptor for the given name
	 * <p>
	 * Note: ImageDescriptor returned from this method will be automitically
	 * disposed of when this plug-in shuts down. Callers must not dispose of
	 * these ImageDescriptor themselves.
	 * </p>
	 * 
	 * @param name
	 *            the ImageDescriptor name found in /icons (with extension)
	 * @return the ImageDescriptor, null on error or not found.
	 */
	public ImageDescriptor getImageDescriptor(String name) {
		if (name == null) {
			return null;
		}

		ImageRegistry images = getImageRegistry();
		ImageDescriptor id = images.getDescriptor(name);
		if (id == null) {
			try {
				id = ImageDescriptor.createFromURL(new URL(_pluginBase,
						"icons/" + name)); //$NON-NLS-1$
				images.put(name, id);
			} catch (MalformedURLException ee) {
				// log.CommonPlugin.image.error=Image {0} not found.
				_log.error("log.msg", "log.CommonPlugin.image.error", name, ee); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return id;
	}

	/**
	 * Is this eclipse version 2.1
	 * 
	 * @return true if version is 2.1
	 */
	public static boolean isTwoDotOne() {
		return TWO_DOT_ONE;
	}

	/**
	 * Is this eclipse version 3.x
	 * 
	 * @return true if version is 3.x
	 */
	public static boolean isThreeDot() {
		return THREE_DOT;
	}

	/**
	 * get the eclipse version
	 * 
	 * @return version string.
	 */
	public static String getVersion() {
		return _version;
	}



	/**
	 * Initializes the preference controls to the default values. These values
	 * are used the first time the preference page is displayed or when the user
	 * presses the Defaults button in the preferences page.
	 */
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		// NOTE: no logging can happen here since we haven't initialized logging
		// yet because it
		// depends on preferences!!!!

		store.setDefault(P_CONSOLE_LOGGING, _properties.getProperty(
				P_CONSOLE_LOGGING, Boolean.TRUE.toString()));
		store.setDefault(P_CONSOLE_LOG_LEVEL, strToIntLogLevel(_properties
				.getProperty(P_CONSOLE_LOG_LEVEL, "ERROR"))); //$NON-NLS-1$

		store.setDefault(P_ECLIPSE_LOGGING, _properties.getProperty(
				P_ECLIPSE_LOGGING, Boolean.TRUE.toString()));
		store.setDefault(P_ECLIPSE_LOG_LEVEL, strToIntLogLevel(_properties
				.getProperty(P_ECLIPSE_LOG_LEVEL, "ERROR"))); //$NON-NLS-1$

		store.setDefault(P_FILE_LOGGING, _properties.getProperty(
				P_FILE_LOGGING, Boolean.FALSE.toString()));
		store.setDefault(P_FILE_LOG_LEVEL, strToIntLogLevel(_properties
				.getProperty(P_FILE_LOG_LEVEL, "ERROR"))); //$NON-NLS-1$
		store.setDefault(P_FILE_PATH, _properties.getProperty(P_FILE_PATH, "")); //$NON-NLS-1$
		store.setDefault(P_FILE_CLEAR, _properties.getProperty(P_FILE_CLEAR,
				Boolean.TRUE.toString()));
		store.setDefault(P_FILE_ROLLOVER_FREQUENCY, _properties.getProperty(
				P_FILE_ROLLOVER_FREQUENCY, "DAILY")); //$NON-NLS-1$
	}

	/**
	 * Converts the log level from string to int. The level defaults to
	 * ERROR_LEVEL.
	 * 
	 * @param str
	 *            String representation of log level
	 * @return integer representation of log level
	 */
	private int strToIntLogLevel(String str) {
		if (str == null) {
			return ERROR_LEVEL;
		}
		if (str.equalsIgnoreCase("DEBUG")) { //$NON-NLS-1$
			return DEBUG_LEVEL;
		}
		if (str.equalsIgnoreCase("INFO")) { //$NON-NLS-1$
			return INFO_LEVEL;
		}
		if (str.equalsIgnoreCase("WARN")) { //$NON-NLS-1$
			return WARN_LEVEL;
		}
		if (str.equalsIgnoreCase("FATAL")) { //$NON-NLS-1$
			return FATAL_LEVEL;
		}
		return ERROR_LEVEL;
	}

}
