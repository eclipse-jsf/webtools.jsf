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
package org.eclipse.jst.pagedesigner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.Alerts;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class PDPlugin extends AbstractUIPlugin {
	private static final String ICONS_LIB_PATH = "icons"; //$NON-NLS-1$

	//private static final boolean ROOT_PLUGIN = false;

	// The shared instance.
	private static PDPlugin _plugin;

	private static Logger _log;

	private static Alerts _alerts;

	private ResourceBundle _resourceBundle;

	private Properties _properties;

	private URL _pluginBase;

	/**
	 * The constructor.
	 */
	public PDPlugin() {
		super();
		_plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		try {
			// get resource bundle.
			_resourceBundle = ResourceBundle
					.getBundle(IJMTConstants.ROOT_RESOURCEBUNDLE);
			_alerts = new Alerts(this, _resourceBundle);

			// get properties.
			_properties = new Properties();
			InputStream input = null;
			_pluginBase = getBundle().getEntry("/"); //$NON-NLS-1$
			try {
				input = (new URL(_pluginBase, IJMTConstants.DEFAULT_PROPERTIES))
						.openStream();
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
	 * @return the default plugin
	 */
	public static PDPlugin getDefault() {
		return _plugin;
	}

	/**
	 * get the alerts objects associated with this plugin for alerting the user.
	 * 
	 * @return the Alerts object
	 */
	public static Alerts getAlerts() {
		return _alerts;
	}

	/**
	 * Returns a logger for the new class using this plugin for reference.
	 * @param theClass
	 * @return the Logger object associated with theClass
	 */
	public static Logger getLogger(Class theClass) {
		if (getDefault() != null && getDefault().getRootLogger() != null) {
			return getDefault().getRootLogger();
		}
		return null;
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
	 * @return the plugin id
	 */
	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * @return the plugin's resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return _resourceBundle;
	}

	/**
	 * @param key 
	 * @return the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = PDPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * @return the plugin's descriptor's resource bundle,
	 */
	public ResourceBundle getPluginDecriptorBundle() {
		return Platform.getResourceBundle(getDefault().getBundle());
	}

	/**
	 * @return the plugin's default properties. These are normally used for
	 * default preferences.
	 */
	public Properties getProperties() {
		return _properties;
	}

	/**
	 * @return the standard display to be used. The method first checks, if the
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
	 * @return the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns a shared image for the given name.  Image must exist in icons folder of pagedesigner plugin.
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
			getImageDescriptor(name);		
			image = images.get(name);
		}
		return image;
	}

	/**
	 * Returns a shared ImageDescriptor for the given name
	 * <p>
	 * Note: ImageDescriptor returned from this method will be automatically
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
			InputStream stream = null;
			try {//check existance of file
				URL url = new URL(_pluginBase,
						ICONS_LIB_PATH + "/" + name); //$NON-NLS-1$
				
				//doing the following to check existance... exception will be caught and null descriptor returned
				stream = url.openStream();
				stream.close();
				
				id = ImageDescriptor.createFromURL(url);
				images.put(name, id);
			} catch (IOException e1) {
				_log.info("Unable to create ImageDescriptor for: "+name, e1); //$NON-NLS-1$
				return null;
			} 
		}
		return id;
	}

	/**
	 * Read a file resource. The file should contain any partial path and the
	 * filename from the plugin base. The caller is responsible for closing the
	 * file.
	 * @param file 
	 * @return the input stream for the file
	 * @throws MalformedURLException 
	 * @throws IOException 
	 */
	public InputStream readFile(String file) throws MalformedURLException,
			IOException {
		return (new URL(_pluginBase, file)).openStream();
	}

	/**
	 * @param file
	 * @return the File associate with the name 'file'
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public File getFile(String file) throws MalformedURLException, IOException {
		return new File((new URL(_pluginBase, file)).getPath());
	}

	/**
	 * @return the plugin's install location with the leading "/" removed
	 * and normalized to the the os string.
	 */
	public static Path getInstallLocation() {
		try {
			URL url = getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
			String s1 = FileLocator.resolve(url).getFile();
			if (s1.startsWith("/")) //$NON-NLS-1$
			{
				s1 = s1.substring(1);
			}
			s1 = (new Path(s1)).toOSString();
			String s;
			if (s1.endsWith(File.separator)) {
				s = s1;
			} else {
				s = s1 + File.separator;
			}
			return new Path(s);
		} catch (Exception exception) {
			_log.error("Error.PDPlugin.Installation.15", exception); //$NON-NLS-1$
			return null;
		}
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
		// initialize any preferences for this plugin.
	}

	/**
	 * @return the active workbench Shell. Used for some funciton need IShell
	 * Parameter.
	 */
	public static Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		IWorkbenchWindow[] windows = getDefault().getWorkbench()
				.getWorkbenchWindows();
		if (windows.length > 0) {
			return windows[0].getShell();
		}
		return null;
	}

	/**
	 * @return the active display.
	 */
	public static Display getDisplay() {
		Shell shell = getActiveWorkbenchShell();
		if (shell != null) {
			return shell.getDisplay();
		}
        return Display.getDefault();
	}

	/**
	 * @return current active project.
	 */
	public static IProject getCurrentProject() {
		IProject curProject = null;
		IEditorPart editor = PDPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		IFile inputFile = null;
		if (input instanceof IFileEditorInput) {
			inputFile = ((IFileEditorInput) input).getFile();
			curProject = inputFile.getProject();
		}
		return curProject;
	}
	
	/**
	 * Log status using the default plugin logger
	 * @param status
	 */
	public static void log(IStatus status)
	{
	    ILog log = getDefault().getLog();
	    log.log(status);
	}
	
	/**
	 * @param message
	 * @param excp
	 */
	public static void log(String message, Throwable excp)
	{
	    Status status = new Status(IStatus.ERROR, getPluginId(), message, excp);
	    log(status);
	}
}
