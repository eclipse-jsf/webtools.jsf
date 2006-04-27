/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Ian Trimble - added logging methods
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * JSF UI plugin.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class JSFUiPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.ui.internal.JSFUiPlugin"; //$NON-NLS-1$
	//The shared instance.
	private static JSFUiPlugin plugin;

	/**
	 * The constructor.
	 */
	public JSFUiPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
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
	public static JSFUiPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		path = "icons/" + path; //$NON-NLS-1$
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.jst.jsf.ui", path); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.WTPPlugin#getPluginID()
	 */
	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 * @param ex Throwable instance (can be null)
	 */
	public static void log(int severity, String message, Throwable ex) {
		getDefault().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 */
	public static void log(int severity, String message) {
		log(severity, message, null);
	}
}
