/*******************************************************************************
 * Copyright (c) 2012, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

class OutlineTargetListenerReader
{
    private final static String CLASS = "class"; //$NON-NLS-1$
    
    private static List<TransferDropTargetListener> _listeners;
    
    public static synchronized List<TransferDropTargetListener> getListeners()
    {
        if (_listeners == null)
        {
            _listeners = readListeners();
        }
        return Collections.unmodifiableList(_listeners);
    }

    private static List<TransferDropTargetListener> readListeners()
    {
        List<TransferDropTargetListener> listeners = new ArrayList<TransferDropTargetListener>();
        
        IExtensionPoint extensionPoint = 
            Platform.getExtensionRegistry()
                .getExtensionPoint(PDPlugin.getPluginId(),
                                   IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
        IExtension[] extensions = extensionPoint.getExtensions();

        for (int i=0; i<extensions.length; i++)
        {
            IExtension ext = extensions[i];
            IConfigurationElement[] pageDesignerElements = ext.getConfigurationElements();
            
            for (int j=0; j<pageDesignerElements.length; j++)
            {
                final IConfigurationElement pageDesignerElement = pageDesignerElements[j];
                if (pageDesignerElement.getName().equals(IJMTConstants.OUTLINE_TARGET_LISTENER))
                {
                    pageDesignerElement.getAttribute(CLASS);
                    Object object;
                    try
                    {
                        object = pageDesignerElement.createExecutableExtension(CLASS);
                        if (object instanceof TransferDropTargetListener)
                            
                        {
                            listeners.add((TransferDropTargetListener)object);
                        }
                    }
                    catch (CoreException e)
                    {
                        PDPlugin.log("Problem loading transfer drop target listener for " +  //$NON-NLS-1$
                                pageDesignerElement, e);
                    }
                }
            }
        }
        return listeners;
    }
    
    private OutlineTargetListenerReader()
    {
        throw new IllegalStateException("Should not be instantiated!"); //$NON-NLS-1$
    }

}
