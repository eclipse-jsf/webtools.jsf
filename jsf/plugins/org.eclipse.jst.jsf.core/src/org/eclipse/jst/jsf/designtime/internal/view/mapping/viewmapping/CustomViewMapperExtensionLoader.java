/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.ICustomViewMapper;

/**
 * @author cbateman
 * 
 */
public class CustomViewMapperExtensionLoader
{
    private final static String                   EXTENSION_ID = "customViewMapper"; //$NON-NLS-1$
    private final static String                   ELEMENT_NAME = "customViewMapper"; //$NON-NLS-1$

    private static Map<String, ICustomViewMapper> _extensions;

    /**
     * @param id
     * @return the list of handlers. The list is not modifiable and will throw
     *         exceptions if it is attempted.
     */
    public static synchronized ICustomViewMapper getCustomViewMapper(
            final String id)
    {
        if (_extensions == null)
        {
            _extensions = Collections.unmodifiableMap(readAllHandlers());
        }
        return _extensions.get(id);
    }

    private static Map<String, ICustomViewMapper> readAllHandlers()
    {
        final Map<String, ICustomViewMapper> handlers = new HashMap<String, ICustomViewMapper>();
        final IExtensionPoint extensionPoint = JSFCorePlugin.getDefault()
                .getExtension(EXTENSION_ID);
        final IExtension[] extensions = extensionPoint.getExtensions();

        for (final IExtension ext : extensions)
        {
            final IConfigurationElement[] configElements = ext
                    .getConfigurationElements();

            for (final IConfigurationElement element : configElements)
            {
                if (ELEMENT_NAME.equals(element.getName()))
                {
                    try
                    {
                        final String pluginId = element.getContributor()
                                .getName();
                        final String name = element.getAttribute("id"); //$NON-NLS-1$
                        final Object customHandler = element
                                .createExecutableExtension("class"); //$NON-NLS-1$
                        if (customHandler instanceof ICustomViewMapper
                                && name != null)
                        {
                            handlers.put(pluginId + "." + name, //$NON-NLS-1$
                                    (ICustomViewMapper) customHandler);
                        }
                        else
                        {
                            JSFCorePlugin.log(
                                    "Error loading tag registry extension: " //$NON-NLS-1$
                                            + element.getContributor().getName()
                                            + "." + element.getAttribute("id") //$NON-NLS-1$ //$NON-NLS-2$
                                      , new Throwable("No exception: stack trace only")); //$NON-NLS-1$
                        }
                    }
                    catch (final CoreException e)
                    {
                        JSFCorePlugin.log(e,
                                "Error loading tag registry extension: " //$NON-NLS-1$
                                        + element.getContributor().getName()
                                        + "." + element.getAttribute("id")); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }
        return handlers;
    }
}
