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
package org.eclipse.jst.pagedesigner.elementedit;

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
public class ElementEditFacRegistryReader {
	private static List<IElementEditFactory> _handlers = null;

	/**
	 * @return all available handers for the ext-pt.  List is not
	 * modifiable
	 */
	public static synchronized List<IElementEditFactory> getAllHandlers() {
		if (_handlers == null) {
			_handlers = readAllHandlers();
		}
		return Collections.unmodifiableList(_handlers);
	}

	private static List<IElementEditFactory> readAllHandlers()
    {
        List<IElementEditFactory> result = new ArrayList<IElementEditFactory>();
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
                .getExtensionPoint(PDPlugin.getPluginId(),
                        IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
        IExtension[] extensions = extensionPoint.getExtensions();

        for (int i = 0; i < extensions.length; i++)
        {
            IExtension ext = extensions[i];
            IConfigurationElement[] elementEditElement = ext
                    .getConfigurationElements();

            for (int j = 0; j < elementEditElement.length; j++)
            {
                final IConfigurationElement element = elementEditElement[j];
                if (element.getName().equals(
                        IJMTConstants.ELEMENT_EDIT_FACTORY))
                {
                    elementEditElement[j].getAttribute("class"); //$NON-NLS-1$
                    Object obj;
                    try
                    {
                        obj = elementEditElement[j]
                                .createExecutableExtension("class"); //$NON-NLS-1$

                        // TODO: we need a policy based solution here,
                        // but this will do for now
                        if (obj instanceof IElementEditFactory)
                        {
                            if (element.getContributor().getName().startsWith("org.eclipse.jst")) //$NON-NLS-1$
                            {
                                if (PageDesignerTraceOptions.TRACE_ELEMENTEDITLOAD)
                                {
                                    PageDesignerTraceOptions.log("ElementEditFacRegistryReader: Appending to list:"+obj.getClass().getName()); //$NON-NLS-1$
                                }
                                // push JSF tools provided ones to the end
                                result.add((IElementEditFactory) obj);
                            }
                            // prepend if something outside JSF tools declared it
                            else
                            {
                                if (PageDesignerTraceOptions.TRACE_ELEMENTEDITLOAD)
                                {
                                    PageDesignerTraceOptions.log("ElementEditFacRegistryReader: Prepending to list:"+obj.getClass().getName()); //$NON-NLS-1$
                                }
                                // this way, adopters can put their overrides
                                // of factories with built-in support like
                                // JSF HTML/CORE will be used first
                                result.add(0, (IElementEditFactory) obj);
                            }
                        }
                    } catch (CoreException e)
                    {
                        PDPlugin.log("Problem loading element edit extension for "+element.toString(), e); //$NON-NLS-1$
                    }
                }
            }
        }
        

        if (PageDesignerTraceOptions.TRACE_ELEMENTEDITLOAD)
        {
            PageDesignerTraceOptions.log("\nFinal elementEditFactory list in order:"); //$NON-NLS-1$
            for (final IElementEditFactory factory : result)
            {
                PageDesignerTraceOptions.log(factory.getClass().getName());
            }
            PageDesignerTraceOptions.log("\n"); //$NON-NLS-1$
        }

        return result;
    }

}
