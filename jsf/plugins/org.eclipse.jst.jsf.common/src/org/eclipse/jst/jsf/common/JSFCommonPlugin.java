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
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolSourceProviderFactory;
import org.osgi.framework.Bundle;

/**
 * This is the central singleton for the My edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class JSFCommonPlugin extends EMFPlugin {

	/**
	 * Plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.common";

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright 2006 Oracle";

    private static List  registeredSymbolSourceProviders;
    private static Map   registeredSymbolFactories;
    
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
    public static void log(final Exception e, final String msg) {
        final ILog log = getPlugin().getLog();

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
        return (ISymbolSourceProvider[]) 
            registeredSymbolSourceProviders.toArray(new ISymbolSourceProvider[0]);
    }
    
    /**
     * @return all registered symbol source providers
     */
    public synchronized static List getSymbolSourceProviders()
    {
        if (registeredSymbolSourceProviders == null)
        {
            registerProviders();
            if (registeredSymbolSourceProviders == null)
            {
                throw new AssertionError("registerProviders failed");
            }
        }
        return Collections.unmodifiableList(registeredSymbolSourceProviders);
    }
    
    private static void registerProviders()
    {
        final IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(plugin.getBundle().getSymbolicName(), Implementation.SYMBOL_SOURCE_EXT_ID);
        final IExtension[] extensions = point.getExtensions();
        registeredSymbolSourceProviders = new ArrayList(extensions.length);

        for (int i = 0; i < extensions.length; i++)
        {
            final IExtension extension = extensions[i];
            final IConfigurationElement[] elements = 
                extension.getConfigurationElements();
            final String bundleId = extension.getContributor().getName();
            
            for (int j = 0; j < elements.length; j++)
            {
                final IConfigurationElement element = elements[j];
                if ("symbolSourceId".equals(element.getName())
                        && element.getAttribute("factory") != null)
                {
                    String factoryClassName = element.getAttribute("factory");
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
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol provider extension point",e));
                        }
                    }
                }
            }
        }
    }

    /**
     * @return the list of registed symbol factories
     */
    public synchronized static Map getSymbolFactories()
    {
        if (registeredSymbolFactories == null)
        {
            registerSymbolFactories();
            if (registeredSymbolFactories == null)
            {
                throw new AssertionError("registerProviders failed");
            }
        }
        return Collections.unmodifiableMap(registeredSymbolFactories);
    }
    
    private static void registerSymbolFactories()
    {
        final IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(plugin.getBundle().getSymbolicName(), Implementation.SYMBOL_FACTORY_EXT_ID);
        final IExtension[] extensions = point.getExtensions();
        registeredSymbolFactories = new HashMap(extensions.length);

        for (int i = 0; i < extensions.length; i++)
        {
            final IExtension extension = extensions[i];
            final IConfigurationElement[] elements = 
                extension.getConfigurationElements();
            final String bundleId = extension.getContributor().getName();
            
            for (int j = 0; j < elements.length; j++)
            {
                final IConfigurationElement element = elements[j];
                if ("contextSymbolFactory".equals(element.getName())
                        && element.getAttribute("factory") != null)
                {
                    final String factoryClassName = element.getAttribute("factory");
                    final String factoryId = element.getAttribute("factoryId");
                    final Bundle bundle = Platform.getBundle(bundleId);
                    
                    if (bundle != null)
                    {
                        try
                        {
                            Class factoryClass = bundle.loadClass(factoryClassName);
                            
                            AbstractContextSymbolFactory factory = 
                                (AbstractContextSymbolFactory) factoryClass.newInstance();

                            registeredSymbolFactories.put(factoryId, factory);
                        }
                        catch (Exception e)
                        {
                            plugin.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, "Error loading symbol factory extension point",e));
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
        IStatus status = new Status(IStatus.ERROR, getPlugin().getSymbolicName(), 0,  "Caught exception", t);
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
        public static final  String   SYMBOL_SOURCE_EXT_ID = "symbolSourceProvider";
        /**
         * Local identifier for the symbol factory extension point
         */
        public static final  String   SYMBOL_FACTORY_EXT_ID = "contextSymbolFactory";
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
}
