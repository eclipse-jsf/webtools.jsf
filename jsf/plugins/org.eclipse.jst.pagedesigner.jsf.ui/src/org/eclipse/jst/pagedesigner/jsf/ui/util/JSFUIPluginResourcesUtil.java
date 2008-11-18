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
package org.eclipse.jst.pagedesigner.jsf.ui.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;

/**
 * @author mengbo
 */
public class JSFUIPluginResourcesUtil extends ResourceUtils
{
    private final static String             bundleName = "org.eclipse.jst.pagedesigner.jsf.ui.JSFUIResources"; //$NON-NLS-1$
    private static Logger                   _log       = JSFUICommonPlugin.getLogger(JSFUIPluginResourcesUtil.class);
    private static JSFUIPluginResourcesUtil _resource;                                                          // singleton

    private JSFUIPluginResourcesUtil()
    {
        try
        {
            _resources = ResourceBundle.getBundle(bundleName);
        }
        catch (MissingResourceException e)
        {
            _log.error("Error in getting show messages resource bundle file", e); //$NON-NLS-1$
        }
    }

    /**
     * @return the singleton instance
     */
    public static JSFUIPluginResourcesUtil getInstance()
    {
        if (_resource == null)
        {
            _resource = new JSFUIPluginResourcesUtil();
        }
        return _resource;
    }
}
