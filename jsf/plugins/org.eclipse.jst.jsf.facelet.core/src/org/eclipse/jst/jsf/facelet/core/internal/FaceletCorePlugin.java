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
package org.eclipse.jst.jsf.facelet.core.internal;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractCompCoreQueryFactory;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractCompCoreQueryFactory.DefaultCompCoreQueryFactory;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FaceletCorePlugin extends EMFPlugin
{

    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final FaceletCorePlugin INSTANCE = new FaceletCorePlugin();

    /**
     * The plug-in ID
     */
    public static final String       PLUGIN_ID = "org.eclipse.jst.jsf.facelet.core"; //$NON-NLS-1$

    // The shared instance
    private static Implementation plugin;


    /**
     * Create the instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletCorePlugin()
    {
        super
          (new ResourceLocator [] 
           {
           });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator()
    {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin()
    {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class Implementation extends EclipsePlugin
    {
        private BundleContext context = null;
        /**
         * Creates an instance.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Implementation()
        {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }

        private AbstractCompCoreQueryFactory compCoreQueryFactory;

        /**
         * @return the query factory
         */
        public AbstractCompCoreQueryFactory getCompCoreQueryFactory()
        {
            synchronized(this)
            {
                if (compCoreQueryFactory == null)
                {
                    List<AbstractCompCoreQueryFactory> extensions = new MyCompCoreFactoryLoader().getExtensions();
                    if (!extensions.isEmpty())
                    {
                        compCoreQueryFactory =  extensions.get(0);
                        
                    }
                    else
                    {
                        this.compCoreQueryFactory = new DefaultCompCoreQueryFactory();
                    }   
                }
                return this.compCoreQueryFactory;
            }
        }
        
        
        @Override
        public void start(BundleContext bundleContext) throws Exception
        {
            super.start(bundleContext);
            this.context = bundleContext;
        }
        
        /**
         * @return the bundle context.
         */
        public BundleContext getBundleContext()
        {
            return this.context;
        }
    }

    private static class MyCompCoreFactoryLoader extends AbstractSimpleClassExtensionRegistryReader<AbstractCompCoreQueryFactory>
    {

        protected MyCompCoreFactoryLoader() {
            super(PLUGIN_ID, "componentCoreQueryFactory", "componentCoreQueryFactory", "class", null); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        @Override
        protected void handleLoadFailure(CoreException ce) {
            FaceletCorePlugin.log(ce);
        }
        
    }
    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Implementation getDefault()
    {
        if (plugin == null)
        {
            return new Implementation();
        }
        return plugin;
    }

    /**
     * @param logMessage
     * @param exception
     */
    public static void log(final String logMessage, final Throwable exception)
    {
        final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
                logMessage, exception);
        getDefault().getLog().log(status);
    }

    /**
     * @param exception
     */
    public static void log(final Throwable exception)
    {
        log("Caught exception", exception); //$NON-NLS-1$
    }

    /**
     * @param logMessage
     * @param exception
     */
    public static void logInfo(final String logMessage, final Throwable exception)
    {
        final IStatus status = new Status(IStatus.INFO, PLUGIN_ID,
                logMessage, exception);
        getDefault().getLog().log(status);
    }
}
