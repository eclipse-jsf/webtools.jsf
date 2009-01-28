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
package org.eclipse.jst.pagedesigner.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.PageDesignerTraceOptions;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterFacRegistryReader {
	private static List<IConverterFactory> _handlers = null;

	/**
	 * @return the list of handlers.  The list is not modifiable and will
	 * throw exceptions if it is attempted.
	 */
	public static synchronized List<IConverterFactory> getAllHandlers() {
		if (_handlers == null) {
			_handlers = readAllHandlers();
		}
		return Collections.unmodifiableList(_handlers);

	}

	private static List<IConverterFactory> readAllHandlers()
    {
        final List<IConverterFactory> result = new ArrayList<IConverterFactory>();
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
                .getExtensionPoint(PDPlugin.getPluginId(),
                        IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
        IExtension[] extensions = extensionPoint.getExtensions();

        for (int i = 0; i < extensions.length; i++)
        {
            IExtension ext = extensions[i];
            IConfigurationElement[] tagConverter = ext
                    .getConfigurationElements();

            for (int j = 0; j < tagConverter.length; j++)
            {
                final IConfigurationElement element = tagConverter[j];

                if (element.getName().equals(
                        IJMTConstants.TAG_CONVERTER_FACTORY))
                {
                    element.getAttribute("class"); //$NON-NLS-1$
                    Object obj;
                    try
                    {
                        obj = element.createExecutableExtension("class"); //$NON-NLS-1$

                        if (element.getContributor().getName().startsWith(
                                "org.eclipse.jst")) //$NON-NLS-1$
                        {
                            if (PageDesignerTraceOptions.TRACE_CONVERTERLOAD)
                            {
                                PageDesignerTraceOptions.log("ConverterFacRegistryReader: Appending to list:"+obj.getClass().getName()); //$NON-NLS-1$
                            }
                            // push JSF tools provided ones to the end
                            result.add((IConverterFactory) obj);
                        }
                        // prepend if something outside JSF tools declared it
                        else
                        {
                            if (PageDesignerTraceOptions.TRACE_CONVERTERLOAD)
                            {
                                PageDesignerTraceOptions.log("ConverterFacRegistryReader: Prepending to list:"+obj.getClass().getName()); //$NON-NLS-1$
                            }
                            // this way, adopters can put their overrides
                            // of factories with built-in support like
                            // JSF HTML/CORE will be used first
                            result.add(0, (IConverterFactory) obj);
                        }

                    } 
                    catch (CoreException e)
                    {
                        PDPlugin.log("Problem loading tag converter extension for "+element.toString(), e); //$NON-NLS-1$
                    }
                }
            }
        }

        if (PageDesignerTraceOptions.TRACE_CONVERTERLOAD)
        {
            PageDesignerTraceOptions.log("\nFinal converterFactory list in order:"); //$NON-NLS-1$
            for (final IConverterFactory factory : result)
            {
                PageDesignerTraceOptions.log(factory.getClass().getName());
            }
            PageDesignerTraceOptions.log("\n"); //$NON-NLS-1$
        }
        return result;
    }

}
