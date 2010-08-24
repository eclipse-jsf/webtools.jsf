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
import java.util.List;

import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.context.IDelegatingFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.designtime.context.AbstractDTExternalContextFactory;
import org.eclipse.jst.jsf.designtime.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.designtime.internal.BasicExtensionFactory;
import org.eclipse.jst.jsf.designtime.internal.DecoratableExtensionFactory;
import org.eclipse.jst.jsf.designtime.internal.BasicExtensionFactory.ExtensionData;
import org.eclipse.jst.jsf.designtime.internal.resolver.ViewBasedTaglibResolverFactory;
import org.eclipse.jst.jsf.designtime.internal.view.AbstractDTViewHandler;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.BundleContext;

/**
 * JSF Core plugin.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class JSFCorePlugin extends WTPPlugin
{
    /**
     * The plugin id
     */
    public static final String             PLUGIN_ID = "org.eclipse.jst.jsf.core"; // org.eclipse.jst.jsf.core.internal.JSFCorePlugin"; //$NON-NLS-1$
    // //$NON-NLS-1$

    /**
     * The extension point id (plugin relative) for the tag registry factory provider.
     */
    public static final String             TAG_REGISTRY_FACTORY_PROVIDER_ID = "tagRegistryFactory"; //$NON-NLS-1$

    // The shared instance.
    private static JSFCorePlugin           plugin;

    private IPreferenceStore               preferenceStore;

    private ViewBasedTaglibResolverFactory _tagLibResolverFactory;

    /**
     * The constructor.
     */
    public JSFCorePlugin()
    {
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation
     * 
     * @param context
     * @throws Exception
     */
    @Override
    public void start(final BundleContext context) throws Exception
    {
        super.start(context);
        final IStructuredDocumentContextResolverFactory2 factory = IStructuredDocumentContextResolverFactory2.INSTANCE;
        if (factory instanceof IDelegatingFactory)
        {
            _tagLibResolverFactory = new ViewBasedTaglibResolverFactory();
            ((IDelegatingFactory) factory)
            .addFactoryDelegate(_tagLibResolverFactory);
        }
        else
        {
            log("Error adding tag resolver delegate", new Throwable()); //$NON-NLS-1$
        }
    }

    /**
     * This method is called when the plug-in is stopped
     * 
     * @param context
     * @throws Exception
     */
    @Override
    public void stop(final BundleContext context) throws Exception
    {
        super.stop(context);

        final IStructuredDocumentContextResolverFactory2 factory = IStructuredDocumentContextResolverFactory2.INSTANCE;

        if (factory instanceof IDelegatingFactory
                && _tagLibResolverFactory != null)
        {
            ((IDelegatingFactory) factory)
            .removeFactoryDelegate(_tagLibResolverFactory);
        }
        plugin = null;
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static JSFCorePlugin getDefault()
    {
        return plugin;
    }

    /**
     * @param e
     * @param msg
     */
    public static void log(final Exception e, final String msg)
    {
        final IStatus logStatus = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e);
        JSFCorePlugin currentPlugin = getDefault();
        if (currentPlugin != null)
        {
            final ILog log = currentPlugin.getLog();
            if (log != null)
            {
                log.log(logStatus);
            }
        }
        System.err.println(logStatus);
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity
     *            Severity (IStatus constant) of log entry
     * @param message
     *            Human-readable message describing log entry
     * @param ex
     *            Throwable instance (can be null)
     */
    public static void log(final int severity, final String message,
            final Throwable ex)
    {
    	Status logObject = new Status(severity, PLUGIN_ID, IStatus.OK, message, ex);
    	JSFCorePlugin default1 = getDefault();
    	if (default1 != null)
    	{
    		ILog log = default1.getLog();
    		if (log != null)
    		{
    			log.log(logObject);
    			return;
    		}
    	}
    	System.err.println(logObject.toString());
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity
     *            Severity (IStatus constant) of log entry
     * @param message
     *            Human-readable message describing log entry
     */
    public static void log(final int severity, final String message)
    {
        log(severity, message, null);
    }

    /**
     * Logs a message for this plugin
     * 
     * @param message
     * @param t
     */
    public static void log(final String message, final Throwable t)
    {
        final ILog log = plugin.getLog();
        log.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(),
                0, message, t));
    }

    @Override
    public String getPluginID()
    {
        return PLUGIN_ID;
    }

    /**
     * @param id 
     * @return all registered symbol source providers
     */
    public synchronized static ExtensionData<AbstractDTVariableResolver> getVariableResolvers(final String id)
    {
        checkAndInitVariableResolverFactory();
        return _variableResolverFactory.getExtensions().get(id);
    }

    /**
     * @param forName
     * @return the ids of variable resolvers for 'forName'.
     */
    public synchronized static List<String> getVariableResolversForName(
            final String forName)
    {
        checkAndInitVariableResolverFactory();
        return Collections.unmodifiableList(_variableResolverFactory
                .getIdsForName(forName));
    }

    /**
     * @return true if there any decorative resolvers in the system.  This
     * is provided so that decorative variable resolvers can avoid expensive
     * calculations looking for these resolvers when there are none.
     */
    public synchronized static boolean hasDecorativeVariableResolvers()
    {
        checkAndInitVariableResolverFactory();
        return _variableResolverFactory.getNumDecorativeResolvers() > 0;
    }
    
    private synchronized static void checkAndInitVariableResolverFactory()
    {
        if (_variableResolverFactory == null)
        {
            _variableResolverFactory = new DecoratableExtensionFactory<AbstractDTVariableResolver>(
                    getDefault().getBundle(), VARIABLE_RESOLVER_EXT_POINT_NAME,
                    VARIABLE_RESOLVER_ELEMENT_NAME, false);
        }
    }

    private static DecoratableExtensionFactory<AbstractDTVariableResolver> _variableResolverFactory;
    private final static String                                            VARIABLE_RESOLVER_EXT_POINT_NAME = "variableresolver"; //$NON-NLS-1$
    private final static String                                            VARIABLE_RESOLVER_ELEMENT_NAME   = "variableresolver"; //$NON-NLS-1$

    /**
     * @param id 
     * @return a map of all registered property resolvers by id
     */
    public synchronized static ExtensionData<AbstractDTPropertyResolver> getPropertyResolver(final String id)
    {
        checkAndInitPropertyFactory();
        return _propertyResolverFactory.getExtensions().get(id);
    }

    private synchronized static void checkAndInitPropertyFactory()
    {
        if (_propertyResolverFactory == null)
        {
            _propertyResolverFactory = new DecoratableExtensionFactory<AbstractDTPropertyResolver>(
                    getDefault().getBundle(), PROPERTY_RESOLVER_EXT_POINT_NAME,
                    PROPERTY_RESOLVER_ELEMENT_NAME, false);
        }
    }

    /**
     * @param forName
     * @return the ids of variable resolvers for 'forName'.
     */
    public synchronized static List<String> getPropertyResolversForName(
            final String forName)
    {
        checkAndInitVariableResolverFactory();
        return Collections.unmodifiableList(_propertyResolverFactory
                .getIdsForName(forName));
    }

    /**
     * @return true if there an decorating resolvers
     */
    public synchronized static boolean hasDecorativePropertyResolvers()
    {
        checkAndInitVariableResolverFactory();
        return _propertyResolverFactory.getNumDecorativeResolvers() > 0;
    }
    
    private static DecoratableExtensionFactory<AbstractDTPropertyResolver> _propertyResolverFactory;
    private final static String                                     PROPERTY_RESOLVER_EXT_POINT_NAME = "propertyresolver"; //$NON-NLS-1$
    private final static String                                     PROPERTY_RESOLVER_ELEMENT_NAME   = "propertyresolver"; //$NON-NLS-1$

    /**
     * @param id 
     * @return a map of all registered method resolvers by id
     */
    public synchronized static ExtensionData<AbstractDTMethodResolver> getMethodResolvers(final String id)
    {
        checkAndInitMethodResolverFactory();
        return _methodResolverFactory.getExtensions().get(id);
    }

    private synchronized static void checkAndInitMethodResolverFactory()
    {
        if (_methodResolverFactory == null)
        {
            _methodResolverFactory = new BasicExtensionFactory<AbstractDTMethodResolver>(
                    getDefault().getBundle(), METHOD_RESOLVER_EXT_POINT_NAME,
                    METHOD_RESOLVER_ELEMENT_NAME, false);
        }
    }
    private static BasicExtensionFactory<AbstractDTMethodResolver> _methodResolverFactory;
    private final static String                                   METHOD_RESOLVER_EXT_POINT_NAME = "methodresolver"; //$NON-NLS-1$
    private final static String                                   METHOD_RESOLVER_ELEMENT_NAME   = "methodresolver"; //$NON-NLS-1$

    /**
     * @param id 
     * @return a map of all registered external context providers by id
     */
    public synchronized static ExtensionData<AbstractDTExternalContextFactory> getExternalContextProviders(final String id)
    {
        checkAndInitExternalContextFactory();
        return _externalContextResolverFactory.getExtensions().get(id);
    }

    private synchronized static void checkAndInitExternalContextFactory()
    {
        if (_externalContextResolverFactory == null)
        {
            _externalContextResolverFactory = new BasicExtensionFactory<AbstractDTExternalContextFactory>(
                    getDefault().getBundle(), EXTERNAL_CONTEXT_EXT_POINT_NAME,
                    EXTERNAL_CONTEXT_ELEMENT_NAME, false);
        }
    }
    
    private static BasicExtensionFactory<AbstractDTExternalContextFactory> _externalContextResolverFactory;
    private final static String                                           EXTERNAL_CONTEXT_EXT_POINT_NAME = "externalcontext"; //$NON-NLS-1$
    private final static String                                           EXTERNAL_CONTEXT_ELEMENT_NAME   = "externalcontext"; //$NON-NLS-1$

    /**
     * @param id 
     * @return a map of all registered external context providers by id
     */
    public synchronized static ExtensionData<AbstractDTViewHandler> getViewHandlers(final String id)
    {
        checkAndInitViewHandler();
        return _viewHandlerFactory.getExtensions().get(id);
    }

    private synchronized static void checkAndInitViewHandler()
    {
        if (_viewHandlerFactory == null)
        {
            _viewHandlerFactory = new BasicExtensionFactory<AbstractDTViewHandler>(
                    getDefault().getBundle(), VIEWHANDLER_EXT_POINT_NAME,
                    VIEWHANDLER_ELEMENT_NAME, true);
        }
    }

    private static BasicExtensionFactory<AbstractDTViewHandler> _viewHandlerFactory;
    private final static String                                VIEWHANDLER_EXT_POINT_NAME = "viewhandler"; //$NON-NLS-1$
    private final static String                                VIEWHANDLER_ELEMENT_NAME   = "viewhandler"; //$NON-NLS-1$

    /**
     * @return the preference store for this bundle TODO: this is copied from
     *         AbstractUIPlugin; need to upgrade to new IPreferencesService
     */
    public synchronized IPreferenceStore getPreferenceStore()
    {
        // Create the preference store lazily.
        if (this.preferenceStore == null)
        {
            this.preferenceStore = new ScopedPreferenceStore(
                    new InstanceScope(), getBundle().getSymbolicName());

        }
        return this.preferenceStore;
    }

    /**
     * @param name
     * @return the extension point called name for this bundle
     */
    public IExtensionPoint getExtension(final String name)
    {
        return Platform.getExtensionRegistry().getExtensionPoint(
                plugin.getBundle().getSymbolicName(), name);
    }
}
