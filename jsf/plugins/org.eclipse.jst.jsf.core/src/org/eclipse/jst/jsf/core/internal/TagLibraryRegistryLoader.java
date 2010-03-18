/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;

/**
 * Loads and caches extension for the tagRegistry extension point.
 * 
 * @author cbateman
 * 
 */
/* package */class TagLibraryRegistryLoader
{
    private final static String              EXTENSION_ID = "tagregistry"; //$NON-NLS-1$
    private final static String              ELEMENT_NAME = "tagRegistry"; //$NON-NLS-1$

    private static Set<ITagRegistryFactoryInfo> _extensions;

    /**
     * @return the list of handlers. The list is not modifiable and will throw
     *         exceptions if it is attempted.
     */
    public static synchronized Set<ITagRegistryFactoryInfo> getAllHandlers()
    {
        if (_extensions == null)
        {
            _extensions = readAllHandlers();
        }
        return Collections.unmodifiableSet(_extensions);

    }

    private static Set<ITagRegistryFactoryInfo> readAllHandlers()
    {
        Set<ITagRegistryFactoryInfo> result = new HashSet<ITagRegistryFactoryInfo>();
        IExtensionPoint extensionPoint = JSFCorePlugin.getDefault()
                .getExtension(EXTENSION_ID);
        IExtension[] extensions = extensionPoint.getExtensions();

        for (int i = 0; i < extensions.length; i++)
        {
            IExtension ext = extensions[i];
            IConfigurationElement[] configElements = ext
                    .getConfigurationElements();

            for (int j = 0; j < configElements.length; j++)
            {
                final IConfigurationElement element = configElements[j];
                if (ELEMENT_NAME.equals(element.getName()))
                {
                    try
                    {
                        final TagRegistryFactoryInfo extension = new TagRegistryFactoryInfo(
                                element);
                        result.add(extension);
                    }
                    catch (CoreException e)
                    {
                        JSFCorePlugin.log(e,
                                "Error loading tag registry extension: " //$NON-NLS-1$
                                        + element.getContributor().getName()
                                        + "." + element.getAttribute("id")); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }
        return result;
    }
}
