/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProviderFactory;
import org.osgi.framework.Bundle;

/**
 * This is the central singleton for the My edit plugin.
 * <!-- begin-user-doc -->
 * This class is not API.
 * <!-- end-user-doc -->
 * @generated
 */
public final class JSFCommonPlugin extends EMFPlugin {

	/**
	 * Plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.common"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

    private static List<ISymbolSourceProviderFactory>  registeredSymbolSourceProviders;
    private static Map<String,AbstractContextSymbolFactory>   registeredSymbolFactories;
    
    private final static String     FACTORY_ATTRIBUTE_NAME = "factory"; //$NON-NLS-1$
    private final static String     FACTORY_ATTRIBUTE_ID_NAME = "factoryId";  //$NON-NLS-1$
    private final static String     SYMBOL_SOURCE_ID = "symbolSourceId";  //$NON-NLS-1$
    private final static String     CONTEXT_SYMBOL_FACTORY =  "contextSymbolFactory"; //$NON-NLS-1$
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final JSFCommonPlugin INSTANCE = new JSFCommonPlugin();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JSFCommonPlugin() {
		super
		  (new ResourceLocator [] {
		   });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
    public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
    public static Implementation getPlugin() {
		return plugin;
	}

    /**
     * @param e
     * @param msg
     */
    public static void log(final Throwable e, final String msg) {
        Implementation plugin2 = getPlugin();
        if (plugin2 != null)
        {
            final ILog log = getPlugin().getLog();
            if (log != null)
            {
                log.log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e));
                return;
            }
        }
        // if plugin not active, dump to stderr
        System.err.print(msg+": "); //$NON-NLS-1$
        if (e != null)
        {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity Severity (IStatus constant) of log entry
     * @param message Human-readable message describing log entry
     * @param ex Throwable instance (can be null)
     */
    public static void log(int severity, String message, Throwable ex) {
        getPlugin().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity Severity (IStatus constant) of log entry
     * @param message Human-readable message describing log entry
     */
    public static void log(int severity, String message) {
        log(severity, message, null);
    }
    /**
     * @return all registered symbol source providers
     */
    public static ISymbolSourceProvider[]  getSymbolSourceProvider()
    {
        return registeredSymbolSourceProviders.toArray(new ISymbolSourceProvider[0]);
    }
    
    /**
     * @return all registered symbol source providers
     */
    public synchronized static List<ISymbolSourceProviderFactory> getSymbolSourceProviders()
    {
        if (registeredSymbolSourceProviders == null)
        {
            registerProviders();
            if (registeredSymbolSourceProviders == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableList(registeredSymbolSourceProviders);
    }
    
    private static void registerProviders()
    {
        final IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(plugin.getBundle().getSymbolicName(), Implementation.SYMBOL_SOURCE_EXT_ID);
        final IExtension[] extensions = point.getExtensions();
        registeredSymbolSourceProviders = new ArrayList<ISymbolSourceProviderFactory>(extensions.length);

        for (int i = 0; i < extensions.length; i++)
        {
            final IExtension extension = extensions[i];
            final IConfigurationElement[] elements = 
                extension.getConfigurationElements();
            final String bundleId = extension.getContributor().getName();
            
            for (int j = 0; j < elements.length; j++)
            {
                final IConfigurationElement element = elements[j];
                if (SYMBOL_SOURCE_ID.equals(element.getName())
                        && element.getAttribute(FACTORY_ATTRIBUTE_NAME) != null)
                {
                    String factoryClassName = element.getAttribute(FACTORY_ATTRIBUTE_NAME);
                    final Bundle bundle = Platform.getBundle(bundleId);
                    
                    if (bundle != null)
                    {
                        try
                        {
                            Class factoryClass = bundle.loadClass(factoryClassName);
                            
                            ISymbolSourceProviderFactory factory = 
                                (ISymbolSourceProviderFactory) factoryClass.newInstance();

                            registeredSymbolSourceProviders.add(factory);
                        }
                        catch (Exception e)
                        {
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol provider extension point",e)); //$NON-NLS-1$
                        }
                    }
                }
            }
        }
    }

    /**
     * @return the list of registed symbol factories
     */
    public synchronized static Map<String, AbstractContextSymbolFactory> getSymbolFactories()
    {
        if (registeredSymbolFactories == null)
        {
            registerSymbolFactories();
            if (registeredSymbolFactories == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableMap(registeredSymbolFactories);
    }
    
    private static void registerSymbolFactories()
    {
        final IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(plugin.getBundle().getSymbolicName(), Implementation.SYMBOL_FACTORY_EXT_ID);
        final IExtension[] extensions = point.getExtensions();
        registeredSymbolFactories = new HashMap<String, AbstractContextSymbolFactory>(extensions.length);

        for (int i = 0; i < extensions.length; i++)
        {
            final IExtension extension = extensions[i];
            final IConfigurationElement[] elements = 
                extension.getConfigurationElements();
            final String bundleId = extension.getContributor().getName();
            
            for (int j = 0; j < elements.length; j++)
            {
                final IConfigurationElement element = elements[j];
                if (CONTEXT_SYMBOL_FACTORY.equals(element.getName())
                        && element.getAttribute(FACTORY_ATTRIBUTE_NAME) != null)
                {
                    final String factoryClassName = element.getAttribute(FACTORY_ATTRIBUTE_NAME);
                    final String factoryId = element.getAttribute(FACTORY_ATTRIBUTE_ID_NAME);
                    final Bundle bundle = Platform.getBundle(bundleId);
                    
                    if (bundle != null)
                    {
                        try {
                            Class factoryClass = bundle.loadClass(factoryClassName);
                        
                            AbstractContextSymbolFactory factory = 
                                (AbstractContextSymbolFactory) factoryClass.newInstance();
                            registeredSymbolFactories.put(factoryId, factory);
                        } catch (InstantiationException e) {
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol factory extension point",e)); //$NON-NLS-1$
                        } catch (IllegalAccessException e) {
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol factory extension point",e)); //$NON-NLS-1$
                        } catch (ClassNotFoundException e) {
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol factory extension point",e)); //$NON-NLS-1$
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Logs an exception in this plugin to the error log
     * 
     * @param t
     */
    public static void log(Throwable t)
    {
        ILog log = getPlugin().getLog();
        IStatus status = new Status(IStatus.ERROR, getPlugin().getSymbolicName(), 0,  "Caught exception", t); //$NON-NLS-1$
        log.log(status);
    }

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static class Implementation extends EclipsePlugin 
    {
        /**
         * Name of the symbolSourceProvider ext point
         */
        public static final  String   SYMBOL_SOURCE_EXT_ID = "symbolSourceProvider"; //$NON-NLS-1$
        /**
         * Local identifier for the symbol factory extension point
         */
        public static final  String   SYMBOL_FACTORY_EXT_ID = "contextSymbolFactory"; //$NON-NLS-1$
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
	}
    
	
	/**
	 * Utility method returning class from class name using classloader from specified bundle
	 * @param className
	 * @param bundleId
	 * @return Class
	 */
	public static Class<?> loadClass(String className, String bundleId) {
		Class<?> aClass = null;
		try {
			if (bundleId != null){
				Bundle bundle = Platform.getBundle(bundleId);
				if (bundle != null){
					aClass = bundle.loadClass(className);
				}
			}
		} catch (ClassNotFoundException e) {
			log(e);
		}
		return aClass;
	}
}
