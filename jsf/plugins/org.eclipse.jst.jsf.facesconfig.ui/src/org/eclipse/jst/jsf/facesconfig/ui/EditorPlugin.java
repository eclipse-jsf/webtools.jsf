/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.Alerts;
import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class EditorPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static EditorPlugin plugin;

	private Logger log;

	private ResourceBundle resourceBundle;

	private static Alerts alerts;

	private URL pluginBase;

	private static IPreferenceStore preferenceStore;

	/**
	 * The constructor.
	 */
	public EditorPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		try {
			// get resource bundle.
			resourceBundle = ResourceBundle
					.getBundle("org.eclipse.jst.jsf.facesconfig.ui.EditorPluginResources");
			alerts = new Alerts(this, resourceBundle);
			pluginBase = getBundle().getEntry("/");

			// set up logging for this plugin and everthing under it.
			log = new Logger(this.getBundle(), resourceBundle);

//			log.info("log.StartMessage", CommonPlugin.getVersion());
		} catch (Exception ee) {
			// only log if the logger was configured correctly.
			if (log != null) {
				log.error("log.msg",
						"Problems starting plug-in Faces Config Editor.", ee);
			}

			throw new CoreException(new Status(IStatus.ERROR, getBundle()
					.getSymbolicName(), IStatus.OK,
					"Problems starting plug-in Faces Config Editor", ee));
		}
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static EditorPlugin getDefault() {
		return plugin;
	}

	/**
	 * get the alerts objects associated with this plugin for alerting the user.
	 * 
	 * @return
	 */
	public static Alerts getAlerts() {
		return alerts;
	}

	/**
	 * Returns a logger for the new class using this plugin for reference.
	 */
	public static Logger getLogger(Class theClass) {
		return getDefault().getRootLogger(); // .getLogger(theClass);
	}

	/**
	 * Returns the plugin's root logger
	 */
	public Logger getRootLogger() {
		return log;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
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
	 * Returns the active workbench window.
	 * 
	 * @return the active workbench window. this can be null but I've never seen
	 *         it.
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		if (getDefault().getWorkbench() == null) {
			return null;
		} else {
			return getDefault().getWorkbench().getActiveWorkbenchWindow();
		}
	}

	public static Shell getActiveShell() {
		Shell shell = null;
		IWorkbenchWindow workbenchWindow = getActiveWorkbenchWindow();
		if (workbenchWindow.getShell() != null) {
			shell = workbenchWindow.getShell();
		} else {
			shell = new Shell();
		}
		return shell;
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
		Image image = (Image) images.get(name);
		if (image == null) {
			try {
				ImageDescriptor id = ImageDescriptor.createFromURL(new URL(
						pluginBase, "icons/" + name));
				images.put(name, id);

				image = images.get(name);
			} catch (MalformedURLException ee) {
				// log.EditorPlugin.image.error=Image {0} not found.
				log.error("log.msg", "log.EditorPlugin.image.error", name, ee);
			}
		}
		return image;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(String path) {
		if (path == null) {
			return null;
		}

		ImageRegistry images = getImageRegistry();
		ImageDescriptor id = (ImageDescriptor) images.getDescriptor(path);
		if (id == null) {
			try {
				id = ImageDescriptor.createFromURL(new URL(pluginBase, "icons/"
						+ path));
				images.put(path, id);
			} catch (MalformedURLException ee) {
				// log.EditorPlugin.image.error=Image {0} not found.
				log.error("log.msg", "log.EditorPlugin.image.error", path, ee);
			}
		}
		return id;
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

	public IPreferenceStore getPreferenceStore() {
		if (null == preferenceStore) {
			preferenceStore = new EditorPreferenceStore();
		}

		return preferenceStore;
	}
}
