/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.designtime.context.AbstractDTExternalContextFactory;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.context.IExternalContextFactoryLocator;
import org.eclipse.jst.jsf.designtime.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.designtime.internal.BasicExtensionFactory.ExtensionData;
import org.eclipse.jst.jsf.designtime.internal.view.AbstractDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;

/**
 * Per-web-application manager that manages design time information for a
 * corresponding project.
 * 
 * TODO: migrate to managed singleton
 * 
 * @author cbateman
 * 
 */
public final class DesignTimeApplicationManager
{
    // TODO: load from property file?
    private static final String        PROPERTY_QUALIFIER                               = "org.eclipse.jst.jsf.designtime.internal";                     //$NON-NLS-1$
    private static final String        SESSION_PROPERTY_NAME_PROJECT                    = "DesignTimeApplicationManager";                                //$NON-NLS-1$
    private static final QualifiedName SESSION_PROPERTY_KEY_PROJECT                     = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                SESSION_PROPERTY_NAME_PROJECT);

    private static final String        SESSION_PROPERTY_NAME_FACES_CONTEXT              = "DTFacesContext";                                              //$NON-NLS-1$
    private static final QualifiedName SESSION_PROPERTY_KEY_FACES_CONTEXT               = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                SESSION_PROPERTY_NAME_FACES_CONTEXT);

    private static final String        PERSIST_PROPERTY_NAME_EXTERNAL_CONTEXT_PROVIDER  = "ExternalContextProvider";                                     //$NON-NLS-1$
    private static final QualifiedName PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER   = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                PERSIST_PROPERTY_NAME_EXTERNAL_CONTEXT_PROVIDER);

    private static final String        PERSIST_PROPERTY_NAME_VARIABLE_RESOLVER_PROVIDER = "VariableResolverProvider";                                    //$NON-NLS-1$
    private static final QualifiedName PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER  = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                PERSIST_PROPERTY_NAME_VARIABLE_RESOLVER_PROVIDER);

    private static final String        PERSIST_PROPERTY_NAME_PROPERTY_RESOLVER_PROVIDER = "PropertyResolverProvider";                                    //$NON-NLS-1$
    private static final QualifiedName PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER  = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                PERSIST_PROPERTY_NAME_PROPERTY_RESOLVER_PROVIDER);

    private static final String        PERSIST_PROPERTY_NAME_METHOD_RESOLVER_PROVIDER   = "MethodResolverProvider";                                      //$NON-NLS-1$
    private static final QualifiedName PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER    = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                PERSIST_PROPERTY_NAME_METHOD_RESOLVER_PROVIDER);

    private static final String        PERSIST_PROPERTY_NAME_VIEW_HANDLER               = "ViewHandler";                                                 //$NON-NLS-1$

    private static final String        DEFAULT_EXTERNAL_CONTEXT_ID                      = "org.eclipse.jst.jsf.core.externalcontext.default";            //$NON-NLS-1$

    private static final String        DEFAULT_VARIABLE_RESOLVER_ID                     = "org.eclipse.jst.jsf.core.variableresolver.default.decorative"; //$NON-NLS-1$

    private static final String        DEFAULT_PROPERTY_RESOLVER_ID                     = "org.eclipse.jst.jsf.core.propertyresolver.default.decorative"; //$NON-NLS-1$

    private static final String        DEFAULT_METHOD_RESOLVER_ID                       = "org.eclipse.jst.jsf.core.methodresolver.default";             //$NON-NLS-1$

    private static final String        DEFAULT_VIEW_HANDLER_ID                          = "org.eclipse.jst.jsf.designtime.view.jspviewhandler";          //$NON-NLS-1$

    /**
     * @param project
     * @return the app manager associated with project
     */
    public final static DesignTimeApplicationManager getInstance(
            final IProject project)
    {
        if (!hasJSFDesignTime(project))
        {
            return null;
        }

        try
        {
            synchronized (project)
            {
                DesignTimeApplicationManager manager = (DesignTimeApplicationManager) project
                        .getSessionProperty(SESSION_PROPERTY_KEY_PROJECT);

                if (manager == null)
                {
                    manager = new DesignTimeApplicationManager(project);
                    project.setSessionProperty(SESSION_PROPERTY_KEY_PROJECT,
                            manager);

                    final DesignTimeApplicationManager localManager = manager;
                    localManager._lifecycleListener
                            .addListener(new IResourceLifecycleListener()
                            {
                                public EventResult acceptEvent(
                                        ResourceLifecycleEvent event)
                                {
                                    if (event.getAffectedResource() == project)
                                    {
                                        if (event.getEventType() == ResourceLifecycleEvent.EventType.RESOURCE_INACCESSIBLE
                                                && event.getReasonType() == ResourceLifecycleEvent.ReasonType.RESOURCE_PROJECT_CLOSED)
                                        {
                                            synchronized (project)
                                            {
                                                localManager.dispose();
                                            }
                                            return EventResult.getDisposeAfterEventResult();
                                        }
                                    }
                                    return EventResult.getDefaultEventResult();
                                }
                            });
                }

                // bug 147729: if project was renamed, the project param will be
                // valid, but it will not be in sync with the one for _project
                // unfortunately, since we are using session propertie
                else
                {
                    synchronized (manager)
                    {
                        if (!project.equals(manager._project))
                        {
                            manager._project = project;
                        }
                    }
                }

                return manager;
            }
        }
        catch (final CoreException ce)
        {
            Platform
                    .getLog(JSFCorePlugin.getDefault().getBundle())
                    .log(
                            new Status(
                                    IStatus.ERROR,
                                    JSFCorePlugin.getDefault().getBundle()
                                            .getSymbolicName(),
                                    0,
                                    "Problem loading design time appmanager", new Throwable(ce))); //$NON-NLS-1$
        }

        return null;
    }

    private void dispose()
    {
        if (_isDisposed.compareAndSet(false, true))
        {
            // dispose viewhandler
            IDTViewHandler handler = removeViewHandler();
            
            if (handler != null)
            {
                handler.dispose();
            }
        }
    }

    /**
     * <p>
     * The criteria for a project having a JSF design time are:
     * </p>
     * 
     * <ul>
     * <li>project is non-null.</li>
     * <li>project is accessible (project.isAccessible() == true)</li>
     * <li>project has a JSF facet (this implies that it's dependent facets are
     * also present).</li>
     * </ul>
     * 
     * @param project
     * @return true if project can have a JSF DesignTimeApplicationManager
     *         associated with it. getInstance(project) uses this determine if
     *         should construct an instance for a project.
     */
    public static boolean hasJSFDesignTime(final IProject project)
    {
        return project != null && project.isAccessible()
                && JSFAppConfigUtils.isValidJSFProject(project);
    }

    // instance definition
    // _project must be writable in case the manager needs to be retargetted
    // after a rename/move etc.
    private IProject                             _project;
    private final IExternalContextFactoryLocator _locator;
    // private IDTViewHandler _viewHandler;
    private final Properties                     _properties;
    private final LifecycleListener              _lifecycleListener;
    private final AtomicBoolean                  _isDisposed;

    private DesignTimeApplicationManager(final IProject project)
    {
        _project = project;
        _locator = new MyExternalContextFactoryLocator();
        _properties = loadProperties(_project);
        _isDisposed = new AtomicBoolean();
        _lifecycleListener = new LifecycleListener(_project);
    }

    /**
     * @param file
     *            must not be null
     * @return the faces context for the file or null if not found
     */
    public DTFacesContext getFacesContext(final IFile file)
    {
        if (!hasDTFacesContext(file))
        {
            return null;
        }

        try
        {
            synchronized (file)
            {
                Object context = file
                        .getSessionProperty(SESSION_PROPERTY_KEY_FACES_CONTEXT);
                if (context == null)
                {
                    context = new DTFacesContext(file, _locator);
                    ((DTFacesContext) context).initialize(_lifecycleListener);
                    file.setSessionProperty(SESSION_PROPERTY_KEY_FACES_CONTEXT,
                            context);
                }
                return (DTFacesContext) context;
            }
        }
        catch (final CoreException ce)
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(
                    new Status(IStatus.ERROR, JSFCorePlugin.getDefault()
                            .getBundle().getSymbolicName(), 0,
                            "Problem loading design time facescontext", //$NON-NLS-1$
                            new Throwable(ce)));
        }

        return null;
    }

    /**
     * Only files for which a runtime request context will be generated have a
     * corresponding design time context. This is generally confined to view
     * definition files such as JSP's.
     * 
     * General criteria for a file to have a design time faces context are: -
     * the file is non-null and isAccessible() - the file has designtime view
     * handler (getViewHandler(file) != null) and it supports the content type
     * of file.
     * 
     * getFacesContext uses this to decide whether to generate a context for an
     * IFile.
     * 
     * @param file
     * @return true if file has a design time faces context
     */
    public boolean hasDTFacesContext(final IFile file)
    {
        final IDTViewHandler viewHandler = getViewHandler();

        if (file != null && file.isAccessible() && viewHandler != null
                && viewHandler.supportsViewDefinition(file))
        {
            return true;
        }
        return false;
    }

    /**
     * @return the design time view handler for this webap (project).
     */
    public synchronized IDTViewHandler getViewHandler()
    {
        final String viewHandlerId = getFromProjectSettings(
                PERSIST_PROPERTY_NAME_VIEW_HANDLER, DEFAULT_VIEW_HANDLER_ID);

        if (viewHandlerId != null)
        {
            final AbstractDTViewHandler viewHandler = JSFCorePlugin.getViewHandlers(viewHandlerId).getInstance(
                    _project);
            viewHandler.setLifecycleListener(_lifecycleListener);
            return viewHandler;
        }

        return null;
    }

    /**
     * @return the view handler, removing the instance from any caching
     */
    private synchronized IDTViewHandler removeViewHandler()
    {
        final String viewHandlerId = getFromProjectSettings(
                PERSIST_PROPERTY_NAME_VIEW_HANDLER, DEFAULT_VIEW_HANDLER_ID);

        if (viewHandlerId != null)
        {
            return JSFCorePlugin.getViewHandlers(viewHandlerId).removeInstance(_project);
        }
        return null;
    }

    /**
     * Sets the persistent id on this project that will be used to load the view
     * handler.
     * 
     * @param viewHandlerId
     */
    public synchronized void setViewHandlerId(final String viewHandlerId)
    {
        setProjectSetting(PERSIST_PROPERTY_NAME_VIEW_HANDLER, viewHandlerId);
    }

    /**
     * @param resolverPluginId
     * @throws CoreException
     */
    public synchronized void setExternalContextProvider(
            final String resolverPluginId) throws CoreException
    {
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER,
                resolverPluginId);
    }

    /**
     * @return the id of the active design time external context provider
     */
    public synchronized String getExternalContextProvider()
    {
        return getResolverId(PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER,
                DEFAULT_EXTERNAL_CONTEXT_ID);
    }

    /**
     * @return the designtime variable resolver for this application
     */
    public synchronized AbstractDTVariableResolver getVariableResolver()
    {
        ExtensionData<AbstractDTVariableResolver> extData = null;

        final String id = getResolverId_OLD(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER);
        if (id != null)
        {
            extData = JSFCorePlugin.getVariableResolvers(id);
        }

        if (extData == null)
        {
            extData = JSFCorePlugin
                    .getVariableResolvers(DEFAULT_VARIABLE_RESOLVER_ID);
        }

        return extData.getInstance(_project);
    }

    /**
     * Sets the plugin used to determine the designtime variable resolver. To
     * reset to the default, pass null.
     * 
     * @param resolverPluginId
     * @throws CoreException --
     *             if the setting the new value fails
     */
    public synchronized void setVariableResolverProvider(
            final String resolverPluginId) throws CoreException
    {
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER,
                resolverPluginId);
    }

    /**
     * @return the id of the active design time variable resolver
     */
    public synchronized String getVariableResolverProvider()
    {
        return getResolverId(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER,
                DEFAULT_VARIABLE_RESOLVER_ID);
    }

    /**
     * @return the default property resolver that will be used if no other is
     *         provided. The default property resolver is intended to match the
     *         similar resolver used by the runtime.
     */
    public synchronized AbstractDTPropertyResolver getDefaultPropertyResolver()
    {
        return JSFCorePlugin.getPropertyResolver(DEFAULT_PROPERTY_RESOLVER_ID)
                .getInstance(_project);
    }

    /**
     * @return the designtime property resolver for this application
     */
    public synchronized AbstractDTPropertyResolver getPropertyResolver()
    {
        ExtensionData<AbstractDTPropertyResolver> extData = null;

        final String id = getResolverId_OLD(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER);
        if (id != null)
        {
            extData = JSFCorePlugin.getPropertyResolver(id);
        }

        if (extData == null)
        {
            extData = JSFCorePlugin
                    .getPropertyResolver(DEFAULT_PROPERTY_RESOLVER_ID);
        }

        return extData.getInstance(_project);
    }

    /**
     * @param resolverPluginId
     * @throws CoreException --
     *             if setting the provider fails
     */
    public synchronized void setPropertyResolverProvider(
            final String resolverPluginId) throws CoreException
    {
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER,
                resolverPluginId);
    }

    /**
     * @return the id of the active design time variable resolver
     */
    public synchronized String getPropertyResolverProvider()
    {
        return getResolverId(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER,
                DEFAULT_PROPERTY_RESOLVER_ID);
    }

    /**
     * @return the designtime method resolver for this application
     */
    public synchronized AbstractDTMethodResolver getMethodResolver()
    {
        ExtensionData<AbstractDTMethodResolver> extData = null;

        final String id = getResolverId_OLD(PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER);
        if (id != null)
        {
            extData = JSFCorePlugin.getMethodResolvers(id);
        }

        if (extData == null)
        {
            extData = JSFCorePlugin
                    .getMethodResolvers(DEFAULT_METHOD_RESOLVER_ID);
        }

        return extData.getInstance(_project);
    }

    /**
     * @param resolverPluginId
     * @throws CoreException --
     *             if setting the plugin fails
     */
    public synchronized void setMethodResolverProvider(
            final String resolverPluginId) throws CoreException
    {
        _project
                .setPersistentProperty(
                        PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER,
                        resolverPluginId);

    }

    /**
     * @return the id of the active design time variable resolver
     */
    public synchronized String getMethodResolverProvider()
    {
        return getResolverId(PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER,
                DEFAULT_METHOD_RESOLVER_ID);
    }

    private String getResolverId_OLD(final QualifiedName pluginKey)
    {
        String pluginId = null;

        try
        {
            pluginId = _project.getPersistentProperty(pluginKey);
        }
        catch (final CoreException ce)
        {
            JSFCorePlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0,
                            "Error getting plugin property", //$NON-NLS-1$
                            ce));
            pluginId = null;
            // fall-through and use the default
        }

        return pluginId;
    }

    private String getResolverId(final QualifiedName key,
            final String defaultValue)
    {
        String id = defaultValue;

        try
        {
            final String userId = _project.getPersistentProperty(key);

            if (userId != null)
            {
                id = userId;
            }
        }
        catch (final CoreException ce)
        {
            // do nothing; fall through and return default
        }

        return id;
    }

    private class MyExternalContextFactoryLocator implements
            IExternalContextFactoryLocator
    {
        public AbstractDTExternalContextFactory getFactory()
        {
            ExtensionData<AbstractDTExternalContextFactory> extData = null;

            final String id = getResolverId_OLD(PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER);
            if (id != null)
            {
                extData = JSFCorePlugin.getExternalContextProviders(id);
            }

            if (extData == null)
            {
                extData = JSFCorePlugin
                        .getExternalContextProviders(DEFAULT_EXTERNAL_CONTEXT_ID);
            }

            return extData.getInstance(_project);
        }
    }

    private String getFromProjectSettings(final String key,
            final String defaultValue)
    {
        return _properties.getProperty(key, defaultValue);
    }

    private void setProjectSetting(final String key, final String value)
    {
        _properties.setProperty(key, value);
        storeProperties(_properties);
    }

    private void storeProperties(final Properties properties)
    {
        IFile propFile;
        try
        {
            propFile = getPropsFile(_project);
            if (propFile != null)
            {
                final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                properties.store(outstream, null);
                propFile.setContents(new ByteArrayInputStream(outstream
                        .toByteArray()), true, true, null);
            }
        }
        catch (final CoreException e)
        {
            JSFCorePlugin.log(e, "Problem storing properties");
        }
        catch (final IOException e)
        {
            JSFCorePlugin.log(e, "Problem storing properties");
        }
    }

    private Properties loadProperties(final IProject project)
    {
        final Properties props = new Properties();
        try
        {
            final IFile propFile = getPropsFile(project);

            if (propFile != null)
            {
                props.load(propFile.getContents());
            }
        }
        catch (final CoreException ce)
        {
            JSFCorePlugin.log(ce, "Problem loading properties");
        }
        catch (final IOException ce)
        {
            JSFCorePlugin.log(ce, "Problem loading properties");
        }

        return props;
    }

    private IFile getPropsFile(final IProject project) throws CoreException
    {
        final IFolder folder = project.getFolder(new Path(".settings"));
        if (!folder.exists())
        {
            folder.create(false, true, null);
        }

        final IFile file = folder.getFile(new Path(
                "org.eclipse.jst.jsf.designtime.appmgr.prefs"));

        if (!file.exists())
        {
            file.create(new ByteArrayInputStream(new byte[0]), false, null);
        }

        return file;
    }
}
