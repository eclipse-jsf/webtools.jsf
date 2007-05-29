/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Ian Trimble - JSFLibraryRegistry work
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.designtime.context.AbstractDTExternalContextFactory;
import org.eclipse.jst.jsf.designtime.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * JSF Core plugin.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class JSFCorePlugin extends WTPPlugin {
	/**
	 * The plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.core";//org.eclipse.jst.jsf.core.internal.JSFCorePlugin"; //$NON-NLS-1$

	// The shared instance.
	private static JSFCorePlugin plugin;
	
    private IPreferenceStore  preferenceStore;

	/**
	 * The constructor.
	 */
	public JSFCorePlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 * @param context 
	 * @throws Exception 
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 * @param context 
	 * @throws Exception 
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 * @return the shared instance
	 */
	public static JSFCorePlugin getDefault() {
		return plugin;
	}

	/**
	 * @param e
	 * @param msg
	 */
	public static void log(final Exception e, final String msg) {
		final ILog log = getDefault().getLog();

		log.log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e));
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 * @param ex Throwable instance (can be null)
	 */
	public static void log(int severity, String message, Throwable ex) {
		getDefault().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
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
     * Logs a message for this plugin
     * 
     * @param message
     * @param t
     */
    public static void log(String message, Throwable t)
    {
        ILog log = plugin.getLog();
        log.log(
           new Status(
             IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, message, t));
    }
    
	public String getPluginID() {
		return PLUGIN_ID;
	}

    /**
     * @return all registered symbol source providers
     */
    public synchronized static Map<String, AbstractDTVariableResolver> getVariableResolvers()
    {
        if (_registeredVariableResolvers == null)
        {
            registerVariableResolverProviders();
            if (_registeredVariableResolvers == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableMap(_registeredVariableResolvers);
    }
    
    private static Map<String, AbstractDTVariableResolver>    _registeredVariableResolvers;
    private final static String VARIABLE_RESOLVER_EXT_POINT_NAME = "variableresolver"; //$NON-NLS-1$
    
    private static void registerVariableResolverProviders()
    {
        _registeredVariableResolvers = new HashMap();
        loadRegisteredExtensions(VARIABLE_RESOLVER_EXT_POINT_NAME,
                                _registeredVariableResolvers,
                                VARIABLE_RESOLVER_EXT_POINT_NAME);
    }
    
    /**
     * @return a map of all registered property resolvers by id
     */
    public synchronized static Map<String, AbstractDTPropertyResolver> getPropertyResolvers()
    {
        if (_registeredPropertyResolvers == null)
        {
            registerPropertyResolverProviders();
            if (_registeredPropertyResolvers == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableMap(_registeredPropertyResolvers);
    }
    
    private static Map<String, AbstractDTPropertyResolver>    _registeredPropertyResolvers;
    private final static String PROPERTY_RESOLVER_EXT_POINT_NAME = 
                                                             "propertyresolver"; //$NON-NLS-1$
    
    private static void registerPropertyResolverProviders()
    {
        _registeredPropertyResolvers = new HashMap();
        loadRegisteredExtensions(PROPERTY_RESOLVER_EXT_POINT_NAME,
                                _registeredPropertyResolvers,
                                PROPERTY_RESOLVER_EXT_POINT_NAME);
    }
    
    
    /**
     * @return a map of all registered method resolvers by id
     */
    public synchronized static Map<String, AbstractDTMethodResolver> getMethodResolvers()
    {
        if (_registeredMethodResolvers == null)
        {
            registerMethodResolverProviders();
            if (_registeredMethodResolvers == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableMap(_registeredMethodResolvers);
    }

    private static Map<String, AbstractDTMethodResolver>     _registeredMethodResolvers;
    private final static String METHOD_RESOLVER_EXT_POINT_NAME = 
                                                               "methodresolver"; //$NON-NLS-1$
    
    private static void registerMethodResolverProviders()
    {
        _registeredMethodResolvers = new HashMap();
        loadRegisteredExtensions(METHOD_RESOLVER_EXT_POINT_NAME,
                _registeredMethodResolvers,
                METHOD_RESOLVER_EXT_POINT_NAME);

    }

    /**
     * @return a map of all registered external context providers by id
     */
    public synchronized static Map<String, AbstractDTExternalContextFactory> getExternalContextProviders()
    {
        if (_registeredExternalContextProviders == null)
        {
            registerExternalContextProviders();
            if (_registeredExternalContextProviders == null)
            {
                throw new AssertionError("registerProviders failed"); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableMap(_registeredExternalContextProviders);
    }
    
    private static Map<String, AbstractDTExternalContextFactory>     _registeredExternalContextProviders;
    private final static String EXTERNAL_CONTEXT_EXT_POINT_NAME = 
                                                               "externalcontext"; //$NON-NLS-1$

    private static void registerExternalContextProviders()
    {
        _registeredExternalContextProviders = new HashMap();
        loadRegisteredExtensions(EXTERNAL_CONTEXT_EXT_POINT_NAME,
                                 _registeredExternalContextProviders,
                                 EXTERNAL_CONTEXT_EXT_POINT_NAME);
    }
    
    private static <ResolverProvider> void loadRegisteredExtensions(final String extName,
                                                 final Map<String, ResolverProvider>    registry,
                                                 final String elementName)
    {
        final IExtensionPoint point = Platform.getExtensionRegistry().
        getExtensionPoint(plugin.getBundle().getSymbolicName(), 
                extName);
        final IExtension[] extensions = point.getExtensions();

        for (int i = 0; i < extensions.length; i++)
        {
            final IExtension extension = extensions[i];
            final IConfigurationElement[] elements = 
                extension.getConfigurationElements();
            final String bundleId = extension.getContributor().getName();
            
            for (int j = 0; j < elements.length; j++)
            {
                final IConfigurationElement element = elements[j];
                if (elementName.equals(element.getName())
                        && element.getAttribute("class") != null //$NON-NLS-1$
                        && element.getAttribute("id") != null) //$NON-NLS-1$
                {
                    final String factoryClassName = element.getAttribute("class"); //$NON-NLS-1$
                    final String id = element.getAttribute("id"); //$NON-NLS-1$
                    final Bundle bundle = Platform.getBundle(bundleId);
                    
                    if (bundle != null)
                    {
                        try
                        {
                            final Class factoryClass = 
                                bundle.loadClass(factoryClassName);
                            
                            final Object variableResolver= 
                                factoryClass.newInstance();
    
                            registry.put(id, (ResolverProvider) variableResolver);
                        }
                        catch (Exception e)
                        {
                            final ILog        logger_ = getDefault().getLog();
                            logger_.log(new Status(IStatus.ERROR, plugin.getBundle()
                                    .getSymbolicName(), 0, 
                                    "Error loading resolver provider extension point",e)); //$NON-NLS-1$
                        }
                    }
                }
            }
        }
    }
    
    /**
     * @return the preference store for this bundle
     * TODO: this is copied from AbstractUIPlugin; need to upgrade to new IPreferencesService
     */
    public IPreferenceStore getPreferenceStore() {
        // Create the preference store lazily.
        if ( this.preferenceStore == null) {
            this.preferenceStore = new ScopedPreferenceStore(new InstanceScope(),getBundle().getSymbolicName());

        }
        return this.preferenceStore;
    }
}
