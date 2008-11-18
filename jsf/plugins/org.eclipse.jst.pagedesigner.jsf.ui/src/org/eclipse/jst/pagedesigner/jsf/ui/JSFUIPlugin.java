/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.Alerts;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class JSFUIPlugin extends AbstractUIPlugin
{
    //The shared instance.
    private static JSFUIPlugin _plugin;
    //Resource bundle.
    private ResourceBundle     _resourceBundle;
    private URL                _pluginBase;

    private static Logger      _logger;
    private static Alerts      _alerts;

    /**
     * The constructor.
     */
    public JSFUIPlugin()
    {
        super();
        _plugin = this;
        try
        {
            _resourceBundle = ResourceBundle.getBundle("org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPluginResources"); //$NON-NLS-1$
        }
        catch (MissingResourceException x)
        {
            _resourceBundle = null;
        }
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
        _alerts = new Alerts(this, _resourceBundle);
        _logger = JSFUICommonPlugin.getLogger(JSFUIPlugin.class);
//        _logger.setResourceBundle(_resourceBundle);
        _pluginBase = getBundle().getEntry("/"); //$NON-NLS-1$
    }

    /**
     * get the alerts objects associated with this plugin for alerting the user.
     * @return the alerts
     */
    public static Alerts getAlerts()
    {
        return _alerts;
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception
    {
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * @return the default plugin
     */
    public static JSFUIPlugin getDefault()
    {
        return _plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     * @param key 
     * @return the resource string for key or 'key' if not found
     */
    public static String getResourceString(String key)
    {
        ResourceBundle bundle = JSFUIPlugin.getDefault().getResourceBundle();
        try
        {
            return (bundle != null) ? bundle.getString(key) : key;
        }
        catch (MissingResourceException e)
        {
            return key;
        }
    }

    /**
     * Returns the plugin's resource bundle,
     * @return the resource bundle
     */
    public ResourceBundle getResourceBundle()
    {
        return _resourceBundle;
    }

    /**
     * Return an image from the path
     * @param name
     * @return Image
     */
    public Image getImage(String name)
    {
        if (name == null)
        {
            return null;
        }

        ImageRegistry images = getImageRegistry();
        Image image = images.get(name);
        if (image == null)
        {
            try
            {
                ImageDescriptor id = ImageDescriptor.createFromURL(new URL(_pluginBase,
                        IFileFolderConstants.FOLDER_ICONS + "/" + name)); //$NON-NLS-1$
                images.put(name, id);

                image = images.get(name);
            }
            catch (MalformedURLException ee)
            {
                _logger.error("Error.JSFUIPlugin", name, ee); //$NON-NLS-1$
            }
        }
        return image;
    }

    /**
     * Log message and Throwable by severity.
     * 
     * @param severity Severity (use appropriate IStatus constant).
     * @param message Message to be logged.
     * @param exception Throwable instance to be logged.
     */
    public static void log(int severity, String message, Throwable exception) {
    	ILog log = getDefault().getLog();
    	IStatus status = new Status(
    			severity,
    			"org.eclipse.jst.pagedesigner.jsf.ui", //$NON-NLS-1$
    			message,
    			exception);
    	log.log(status);
    }

    /**
     * Log message by severity.
     * 
     * @param severity Severity (use an IStatus constant).
     * @param message Message to be logged.
     */
    public static void log(int severity, String message) {
    	ILog log = getDefault().getLog();
    	IStatus status = new Status(
    			severity,
    			"org.eclipse.jst.pagedesigner.jsf.ui", //$NON-NLS-1$
    			message);
    	log.log(status);
    }

}
