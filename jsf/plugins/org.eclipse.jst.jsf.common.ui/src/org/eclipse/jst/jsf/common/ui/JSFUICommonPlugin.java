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

import org.eclipse.core.resources.IWorkspace;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * The main plugin class to be used in the desktop.
 */
public class JSFUICommonPlugin extends AbstractUIPlugin implements ICommonConstants {
	// Properties contains general properties and defaults to preferences.
	private static final String PROPERTIES = "default.properties";

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
					"Requires eclipse version 3.x", null));
		}

		try {
			// get resource bundle.
			_resourceBundle = ResourceBundle
					.getBundle("org.eclipse.jst.jsf.common.ui.CommonResources");
			_alerts = new Alerts(this, _resourceBundle);

			// get properties.
			_properties = new Properties();
			InputStream input = null;
			_pluginBase = getBundle().getEntry("/");
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
								"log.msg",
								"Problems starting plug-in Web Application Development Common.",
								ee);
			}

			throw new CoreException(
					new Status(
							IStatus.ERROR,
							getBundle().getSymbolicName(),
							IStatus.OK,
							"Problems starting plug-in Web Application Development Common",
							ee));
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static JSFUICommonPlugin getDefault() {
		return _plugin;
	}

	/**
	 * get the alerts objects associated with this plugin for alerting the user.
	 * 
	 * @return
	 */
	public static Alerts getAlerts() {
		return _alerts;
	}

	/**
	 * Returns a logger for the new class using this plugin for reference.
	 */
    // TODO: theClass is never used!!
	public static Logger getLogger(Class theClass) {
		return getDefault().getRootLogger();
	}

	/**
	 * Returns the plugin's root logger
	 */
	public Logger getRootLogger() {
		return _log;
	}

	/**
	 * Returns this plugin's unique identifier
	 * 
	 * @retun this plugin's unique identifier
	 * 
	 */
	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return _resourceBundle;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
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
	 */
	public ResourceBundle getPluginDecriptorBundle() {
		return Platform.getResourceBundle(getDefault().getBundle());
	}

	/**
	 * Returns the plugin's default properties. These are normally used for
	 * default preferences.
	 */
	public Properties getProperties() {
		return _properties;
	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated dispaly. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() {
		Display display;
		display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
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
						_pluginBase, "icons/" + name));
				images.put(name, id);

				image = images.get(name);
			} catch (MalformedURLException ee) {
				// log.CommonPlugin.image.error=Image {0} not found.
				_log.error("log.msg", "log.CommonPlugin.image.error", name, ee);
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
						"icons/" + name));
				images.put(name, id);
			} catch (MalformedURLException ee) {
				// log.CommonPlugin.image.error=Image {0} not found.
				_log.error("log.msg", "log.CommonPlugin.image.error", name, ee);
			}
		}
		return id;
	}

	/**
	 * Read a file resource. The file should contain any partial path and the
	 * filename from the plugin base. The caller is responsible for closing the
	 * file.
	 */
	public InputStream readFile(String file) throws MalformedURLException,
			IOException {
		return (new URL(_pluginBase, file)).openStream();
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
	 * Returns the active workbench window.
	 * 
	 * @return the active workbench window. this can be null but I've never seen
	 *         it.
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		if (getDefault().getWorkbench() == null) {
			return null;
		}
        return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Returns the active workbench page. Note that the active page may not be
	 * the one that the user perceives as active in some situations so this
	 * method of obtaining the activate page should only be used if no other
	 * method is available.
	 * 
	 * @return the active workbench page
	 */
	public static IWorkbenchPage getActivePage() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window == null) {
			return null;
		}
		return window.getActivePage();
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
				.getProperty(P_CONSOLE_LOG_LEVEL, "ERROR")));

		store.setDefault(P_ECLIPSE_LOGGING, _properties.getProperty(
				P_ECLIPSE_LOGGING, Boolean.TRUE.toString()));
		store.setDefault(P_ECLIPSE_LOG_LEVEL, strToIntLogLevel(_properties
				.getProperty(P_ECLIPSE_LOG_LEVEL, "ERROR")));

		store.setDefault(P_FILE_LOGGING, _properties.getProperty(
				P_FILE_LOGGING, Boolean.FALSE.toString()));
		store.setDefault(P_FILE_LOG_LEVEL, strToIntLogLevel(_properties
				.getProperty(P_FILE_LOG_LEVEL, "ERROR")));
		store.setDefault(P_FILE_PATH, _properties.getProperty(P_FILE_PATH, ""));
		store.setDefault(P_FILE_CLEAR, _properties.getProperty(P_FILE_CLEAR,
				Boolean.TRUE.toString()));
		store.setDefault(P_FILE_ROLLOVER_FREQUENCY, _properties.getProperty(
				P_FILE_ROLLOVER_FREQUENCY, "DAILY"));
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
		if (str.equalsIgnoreCase("DEBUG")) {
			return DEBUG_LEVEL;
		}
		if (str.equalsIgnoreCase("INFO")) {
			return INFO_LEVEL;
		}
		if (str.equalsIgnoreCase("WARN")) {
			return WARN_LEVEL;
		}
		if (str.equalsIgnoreCase("FATAL")) {
			return FATAL_LEVEL;
		}
		return ERROR_LEVEL;
	}

	/**
	 * Converts the rollover frequency from string to int. The frequency
	 * defaults to DAILY.
	 * 
	 * @param str
	 *            String representation of rollover frequency
	 * @return integer representation of rollover frequency
	 */
	public int strToIntFrequency(String str) {
		if (str == null) {
			return DAILY_FREQ;
		}
		if (str.equalsIgnoreCase("WEEKLY")) {
			return WEEKLY_FREQ;
		}
		if (str.equalsIgnoreCase("MONTHLY")) {
			return MONTHLY_FREQ;
		}
		return DAILY_FREQ;
	}
}
