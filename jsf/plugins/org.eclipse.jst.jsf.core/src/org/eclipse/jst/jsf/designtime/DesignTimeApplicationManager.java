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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.core.JSFVersion;
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
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

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
    private static final String SETTINGS_DIR_NAME = ".settings"; //$NON-NLS-1$
    private static final String ORG_ECLIPSE_JST_JSF_DESIGNTIME_APPMGR_PREFS = "org.eclipse.jst.jsf.designtime.appmgr.prefs"; //$NON-NLS-1$

    private static final String        PROPERTY_QUALIFIER                               = "org.eclipse.jst.jsf.designtime.internal";                     //$NON-NLS-1$
    private static final String        SESSION_PROPERTY_NAME_PROJECT                    = "DesignTimeApplicationManager";                                //$NON-NLS-1$
    private static final QualifiedName SESSION_PROPERTY_KEY_PROJECT                     = new QualifiedName(
                                                                                                PROPERTY_QUALIFIER,
                                                                                                SESSION_PROPERTY_NAME_PROJECT);

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

    private static final String        PRE_20_DEFAULT_VIEW_HANDLER_ID                          = "org.eclipse.jst.jsf.designtime.view.jspviewhandler";          //$NON-NLS-1$
    private static final String        JSF_20_DEFAULT_VIEW_HANDLER_ID = "org.eclipse.jst.jsf.facelet.core.html.viewhandler"; //$NON-NLS-1$

    private static final Object        GET_INSTANCE_LOCK = new Object();

    /**
     * @param project
     * @return the app manager associated with project
     */
    public static DesignTimeApplicationManager getInstance(
            final IProject project)
    {
        if (!hasJSFDesignTime(project))
        {
            return null;
        }

        try
        {
            synchronized (GET_INSTANCE_LOCK)
            {
                DesignTimeApplicationManager manager = (DesignTimeApplicationManager) project
                        .getSessionProperty(SESSION_PROPERTY_KEY_PROJECT);

                if (manager != null && !project.equals(manager._project))
                {
                    if (!manager._isDisposed.get())
                    {
                        manager.dispose();
                    }
                    // bug 147729: pretend we starting with a new project (we kind of are)
                    manager = null;
                }

                if (manager == null)
                {
                    manager = new DesignTimeApplicationManager(project);
                    project.setSessionProperty(SESSION_PROPERTY_KEY_PROJECT,
                            manager);
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
                                    "Problem loading design time appmanager", new Exception(ce))); //$NON-NLS-1$
        }

        return null;
    }

//    private void checkAndMaybeUpdateProject(final IProject project)
//    {
//    	boolean needsPropertyStore = false;
//
//    	synchronized(this)
//    	{
//    		if (!project.equals(_project))
//    		{
//    			_lifecycleListener.removeResource(_project);
//    			_project = project;
//    			_lifecycleListener.addResource(_project);
//    			_properties.setProject(project);
//    			needsPropertyStore = true;
//    		}
//    	}
//    	
//    	if (needsPropertyStore)
//    	{
//    		_properties.store();
//    	}
//    }

    private synchronized void dispose()
    {
        if (_isDisposed.compareAndSet(false, true))
        {
            // dispose viewhandler
            removeViewHandler();
            _lifecycleListener.dispose();
            _facesContexts.clear();
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
    private final IProject                       _project;
    private final IExternalContextFactoryLocator _locator;
    private final LifecycleListener              _lifecycleListener;
    private final AtomicBoolean                  _isDisposed;
    private final ViewHandlerManager             _viewHandlerManager;
    private final Map<IFile, DTFacesContext>     _facesContexts;

    private DesignTimeApplicationManager(final IProject project)
    {
        _project = project;
        _locator = new MyExternalContextFactoryLocator();
        _isDisposed = new AtomicBoolean();
        _lifecycleListener = new LifecycleListener(_project, ResourcesPlugin.getWorkspace());
        _lifecycleListener.addListener(new IResourceLifecycleListener()
        {
            public EventResult acceptEvent(final ResourceLifecycleEvent event)
            {
                if (event.getAffectedResource() == _project)
                {
                    if (event.getEventType() == ResourceLifecycleEvent.EventType.RESOURCE_INACCESSIBLE
                            && event.getReasonType() == ResourceLifecycleEvent.ReasonType.RESOURCE_PROJECT_CLOSED)
                    {
                        dispose();
                    }
                }
                return EventResult.getDefaultEventResult();
            }
        });

        final PropertyFileManager properties = new PropertyFileManager(_project);

        _viewHandlerManager = new ViewHandlerManager(properties);
        _facesContexts = new HashMap<IFile, DTFacesContext>();
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @param file
     *            must not be null
     * @return the faces context for the file or null if not found
     */
    public DTFacesContext getFacesContext(final IFile file)
    {
        checkIsDisposed();
        if (!hasDTFacesContext(file))
        {
            return null;
        }

        synchronized (_facesContexts)
        {
            DTFacesContext context = _facesContexts.get(file);
            if (context == null)
            {
                context = new DTFacesContext(file, _locator);
                context.initialize(_lifecycleListener);
                _facesContexts.put(file, context);
            }
            return context;
        }
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
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
        checkIsDisposed();
        final IDTViewHandler viewHandler = getViewHandler();

        if (file != null && file.isAccessible() && viewHandler != null
                && viewHandler.supportsViewDefinition(file))
        {
            return true;
        }
        return false;
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the design time view handler for this webapp (project).
     */
    public IDTViewHandler getViewHandler()
    {
    	checkIsDisposed();
      /* NOTE: it is critical that view handler calls _NEVER_ take the 
         DesignTimeApplicationManager lock.  let ViewHandlerManager manage it
         instead.*/
        return _viewHandlerManager.getViewHandler(_project, _lifecycleListener);
    }

    /**
     * Remove and dispose of any currently registered view handler
     */
    private void removeViewHandler()
    {
        /* NOTE: it is critical that view handler calls _NEVER_ take the 
        DesignTimeApplicationManager lock.  let ViewHandlerManager manage it
        instead.*/
        _viewHandlerManager.removeViewHandler(_project);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * Sets the persistent id on this project that will be used to load the view
     * handler.
     * 
     * @param viewHandlerId
     */
    public void setViewHandlerId(final String viewHandlerId)
    {
        checkIsDisposed();
        /* NOTE: it is critical that view handler calls _NEVER_ take the 
        DesignTimeApplicationManager lock.  let ViewHandlerManager manage it
        instead.*/
        _viewHandlerManager.setViewHandlerId(_project, viewHandlerId);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @param resolverPluginId
     * @throws CoreException
     */
    public synchronized void setExternalContextProvider(
            final String resolverPluginId) throws CoreException
    {
        checkIsDisposed();
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER,
                resolverPluginId);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the id of the active design time external context provider
     */
    public synchronized String getExternalContextProvider()
    {
    	checkIsDisposed();
        return getResolverId(PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER,
                DEFAULT_EXTERNAL_CONTEXT_ID);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the designtime variable resolver for this application
     */
    public synchronized AbstractDTVariableResolver getVariableResolver()
    {
        checkIsDisposed();
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
     * Method is thread-safe and may block the caller.
     * 
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
        checkIsDisposed();
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER,
                resolverPluginId);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the id of the active design time variable resolver
     */
    public synchronized String getVariableResolverProvider()
    {
    	checkIsDisposed();
        return getResolverId(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER,
                DEFAULT_VARIABLE_RESOLVER_ID);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the default property resolver that will be used if no other is
     *         provided. The default property resolver is intended to match the
     *         similar resolver used by the runtime.
     */
    public synchronized AbstractDTPropertyResolver getDefaultPropertyResolver()
    {
        checkIsDisposed();
        return JSFCorePlugin.getPropertyResolver(DEFAULT_PROPERTY_RESOLVER_ID)
                .getInstance(_project);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the designtime property resolver for this application
     */
    public synchronized AbstractDTPropertyResolver getPropertyResolver()
    {
        checkIsDisposed();

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
     * Method is thread-safe and may block the caller.
     * 
     * @param resolverPluginId
     * @throws CoreException --
     *             if setting the provider fails
     */
    public synchronized void setPropertyResolverProvider(
            final String resolverPluginId) throws CoreException
    {
        checkIsDisposed();
        _project.setPersistentProperty(
                PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER,
                resolverPluginId);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the id of the active design time variable resolver
     */
    public synchronized String getPropertyResolverProvider()
    {
        checkIsDisposed();
        return getResolverId(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER,
                DEFAULT_PROPERTY_RESOLVER_ID);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the designtime method resolver for this application
     */
    public synchronized AbstractDTMethodResolver getMethodResolver()
    {
    	checkIsDisposed();
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
     * Method is thread-safe and may block the caller.
     * 
     * @param resolverPluginId
     * @throws CoreException --
     *             if setting the plugin fails
     */
    public synchronized void setMethodResolverProvider(
            final String resolverPluginId) throws CoreException
    {
        checkIsDisposed();
        _project
                .setPersistentProperty(
                        PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER,
                        resolverPluginId);
    }

    /**
     * Method is thread-safe and may block the caller.
     * 
     * @return the id of the active design time variable resolver
     */
    public synchronized String getMethodResolverProvider()
    {
        checkIsDisposed();
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

    private void checkIsDisposed()
    {
        // TODO: need to add isDisposed to this and throw an exception
        // for now, we just log what's happening to aid debugging
        if (_isDisposed.get())
        {
            JSFCorePlugin
                    .log(
                            "A call to a disposed DesignTimeApplicationManager was attempted", //$NON-NLS-1$
                            new Throwable(
                                    "This exception is only to record a stack trace")); //$NON-NLS-1$
        }
    }

    private static class ViewHandlerManager
    {
        private final PropertyFileManager _propertyFileManager;

        ViewHandlerManager(final PropertyFileManager properties)
        {
            _propertyFileManager = properties;
        }

        public synchronized void removeViewHandler(final IProject project)
        {
            final String viewHandlerId = getHandlerId(project);

            if (viewHandlerId != null)
            {
                final ExtensionData<AbstractDTViewHandler> extData =
                    JSFCorePlugin.getViewHandlers(viewHandlerId);
                
                if (extData != null)
                {
                    final AbstractDTViewHandler handler = extData.removeInstance(project);
                    if (handler != null)
                    {
                        handler.dispose();
                    }
                }
            }
        }

        protected String getHandlerId(final IProject project)
        {
            IProjectFacetVersion projectFacet = JSFAppConfigUtils.getProjectFacet(project);
            if (projectFacet != null)
            {
                JSFVersion projectVersion = JSFVersion.valueOfFacetVersion(projectFacet);
                
                String defaultHandler = PRE_20_DEFAULT_VIEW_HANDLER_ID;
                
                // starting with JSF 2.0 a new view handler that first
                // processes as Facelet and then delegates to JSP is
                // used by default
                // TODO: check the web.xml flag that reverts things to 1.2 defaults
                if (projectVersion.compareTo(JSFVersion.V2_0) >= 0)
                {
                    defaultHandler = JSF_20_DEFAULT_VIEW_HANDLER_ID;
                }
                final String viewHandlerId = _propertyFileManager
                        .getProperty(PERSIST_PROPERTY_NAME_VIEW_HANDLER,
                               defaultHandler);
                return viewHandlerId;
            }
            return null;
        }
        

        public synchronized IDTViewHandler getViewHandler(
                final IProject project, final LifecycleListener listener)
        {
            
            final String viewHandlerId = getHandlerId(project);

            if (viewHandlerId != null)
            {
                ExtensionData<AbstractDTViewHandler> viewHandlers = JSFCorePlugin
                        .getViewHandlers(viewHandlerId);
                if (viewHandlers == null)
                {
                    viewHandlers = JSFCorePlugin
                            .getViewHandlers(PRE_20_DEFAULT_VIEW_HANDLER_ID);
                }

                final AbstractDTViewHandler viewHandler = viewHandlers
                        .getInstance(project);
                viewHandler.setLifecycleListener(listener);
                return viewHandler;
            }
            return null;
        }

        public void setViewHandlerId(final IProject project,
                final String viewHandlerId)
        {
            // remove any previous handler before the id is lost
            removeViewHandler(project);
            _propertyFileManager.setProperty(
                    PERSIST_PROPERTY_NAME_VIEW_HANDLER, viewHandlerId);
        }
    }
    
    private static class PropertyFileManager
    {
        private final Properties                     _properties;
        private IProject                             _project;

        PropertyFileManager(final IProject project)
        {
            _project = project;
            _properties = new Properties();
            load(_project, _properties);
        }

        private synchronized IProject getProject()
        {
            return _project;
        }

        public void setProperty(final String key, final String value) {
            _properties.setProperty(key, value);
            store();
        }

        public String getProperty(final String key, final String defaultValue) {
            return _properties.getProperty(key, defaultValue);
        }

        public void store()
        {
            final IWorkspaceRunnable storeJob = new IWorkspaceRunnable()
            {
                public void run(IProgressMonitor monitor) throws CoreException {
                    IFile propFile;

                    try {
                        propFile = getPropsFile(getProject());
                        if (propFile != null) {
                            final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                            // properties will thread-safe save to the array
                            // stream
                            // no further locking is needed since we have
                            // already have a lock
                            // on the workspace sub-tree for the settings file
                            // if this job
                            // is running.
                            _properties.store(outstream, null);
                            propFile.setContents(new ByteArrayInputStream(
                                    outstream.toByteArray()), true, true, null);
                        }
                    } catch (final CoreException e) {
                        JSFCorePlugin.log(e, "Problem storing properties"); //$NON-NLS-1$
                    } catch (final IOException e) {
                        JSFCorePlugin.log(e, "Problem storing properties"); //$NON-NLS-1$
                    }
                }
            };

            final IWorkspace workspace = ResourcesPlugin.getWorkspace();
            
            try
            {
                // need to lock the project tree, since may need to create
                // .settings directory
                workspace.run(storeJob, getProject(), IWorkspace.AVOID_UPDATE,
                        null);
            } catch (CoreException ce)
            {
                JSFCorePlugin.log(ce, "Problem storing properties"); //$NON-NLS-1$
            }
        }

        private static void load(final IProject project,
                final Properties properties)
        {
            try
            {
                final IFile propFile = getPropsFileHandle(project);

                if (propFile != null && propFile.isAccessible())
                {
                    InputStream inStream = null;

                    try
                    {
                        inStream = propFile.getContents();
                        properties.load(inStream);
                    } 
                    finally
                    {
                        if (inStream != null)
                        {
                            inStream.close();
                        }
                    }
                }
            } 
            catch (final CoreException ce)
            {
                JSFCorePlugin.log(ce, "Problem loading properties"); //$NON-NLS-1$
            } 
            catch (final IOException ce)
            {
                JSFCorePlugin.log(ce, "Problem loading properties"); //$NON-NLS-1$
            }
        }

        private static IFile getPropsFile(final IProject project) throws CoreException
        {
            final IFolder folder = project.getFolder(new Path(SETTINGS_DIR_NAME));
            if (!folder.exists())
            {
                folder.create(false, true, null);
            }

            final IFile file = folder.getFile(new Path(ORG_ECLIPSE_JST_JSF_DESIGNTIME_APPMGR_PREFS));

            if (!file.exists())
            {
                file.create(new ByteArrayInputStream(new byte[0]), false, null);
            }

            return file;
        }
        
        /**
         * @param project
         * @return the file handle for the properties file.  Doesn't create the
         * resource if it doesn't exist
         */
        private static IFile getPropsFileHandle(final IProject project)
        {
            return project.getFile(new Path(SETTINGS_DIR_NAME).append(ORG_ECLIPSE_JST_JSF_DESIGNTIME_APPMGR_PREFS));
        }
    }
}
