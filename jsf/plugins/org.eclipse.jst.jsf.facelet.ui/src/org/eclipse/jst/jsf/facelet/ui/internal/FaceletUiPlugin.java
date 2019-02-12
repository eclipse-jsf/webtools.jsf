/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.facelet.ui.internal;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.designtime.internal.BasicExtensionFactory;
import org.eclipse.jst.jsf.designtime.internal.BasicExtensionFactory.ExtensionData;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;
import org.eclipse.jst.jsf.facelet.ui.internal.validation.AbstractFaceletValidationStrategy;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FaceletUiPlugin extends AbstractUIPlugin
{

    /**
     * The plug-in ID
     */
    public static final String     PLUGIN_ID = "org.eclipse.jst.jsf.facelet.ui";

    // The shared instance
    private static FaceletUiPlugin plugin;

    private final Object extensionLock = new Object();
    private BasicExtensionFactory<AbstractFaceletValidationStrategy>  faceletValidationStrategyExtension;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
     * )
     */
    @Override
    public void start(final BundleContext context) throws Exception
    {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
     * )
     */
    @Override
    public void stop(final BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static FaceletUiPlugin getDefault()
    {
        return plugin;
    }

    /**
     * @param e
     */
    public static void log(final TagRegistryFactoryException e)
    {
        getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "", e));
    }

    
    /**
     * @return the validation strategy extension data
     */
    public Map<String, ExtensionData<AbstractFaceletValidationStrategy>> getValidationStrategy()
    {
        synchronized (this.extensionLock) {
            if (this.faceletValidationStrategyExtension == null)
            {
                this.faceletValidationStrategyExtension = new BasicExtensionFactory<AbstractFaceletValidationStrategy>(this.getBundle(), "faceletValidator", "validationStrategy", false);
            }
            return this.faceletValidationStrategyExtension.getExtensions();
        }
    }
}
