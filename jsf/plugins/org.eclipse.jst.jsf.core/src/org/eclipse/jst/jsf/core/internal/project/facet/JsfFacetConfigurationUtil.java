/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Debajit Adhikary - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;


/**
 * Utility class to provide an easy way to read the jsfFacetConfiguration 
 * extension-point (org.eclipse.jst.jsf.core.jsfFacetConfiguration).

 * @author Debajit Adhikary (Oracle)
 *
 */
public class JsfFacetConfigurationUtil
{
    private static final String JSF_FACES_CONFIG_EXTENSION_ID = "jsfFacetConfiguration"; //$NON-NLS-1$
    private static final String DISABLED_ATTR_NAME = "disabled"; //$NON-NLS-1$
    private static final String TRUE_STRING = "true"; //$NON-NLS-1$


    /**
     * @return True if the disabled attribute is not set to "true".
     */
    public static boolean isJsfFacetConfigurationEnabled ()
    {
        return !isJsfFacetConfigurationDisabled();
    }

    
    /**
     * @return True if disabled="true" is set in the extension point.
     */
    public static boolean isJsfFacetConfigurationDisabled ()
    {
        IExtensionPoint jsfFacetConfigExtensionPoint = JSFCorePlugin.getDefault().getExtension(JSF_FACES_CONFIG_EXTENSION_ID);
        for (final IExtension extension : jsfFacetConfigExtensionPoint.getExtensions())
        {
            for (final IConfigurationElement configElement : extension.getConfigurationElements())
            {
                if (configElement != null && configElement.getName().equals(JSF_FACES_CONFIG_EXTENSION_ID))
                {
                    final String attrValue = configElement.getAttribute(DISABLED_ATTR_NAME);
                    if(attrValue != null)
                    {
                        return attrValue.equalsIgnoreCase(TRUE_STRING);
                    }
                }
            }
        }

        return false;
    }


}
