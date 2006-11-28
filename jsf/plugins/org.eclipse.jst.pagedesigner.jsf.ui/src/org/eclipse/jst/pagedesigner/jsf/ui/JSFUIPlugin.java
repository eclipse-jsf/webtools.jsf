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
            _resourceBundle = ResourceBundle.getBundle("org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPluginResources");
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
        _pluginBase = getBundle().getEntry("/");
    }

    /**
     * get the alerts objects associated with this plugin for alerting the user.
     * @return
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
     */
    public static JSFUIPlugin getDefault()
    {
        return _plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
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
                        IFileFolderConstants.FOLDER_ICONS + "/" + name));
                images.put(name, id);

                image = images.get(name);
            }
            catch (MalformedURLException ee)
            {
                _logger.error("Error.JSFUIPlugin", name, ee); //$NON-NLS-2$
            }
        }
        return image;
    }
}
