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

package org.eclipse.jst.jsf.designtime.internal.provisional;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.provisional.context.AbstractDTExternalContextFactory;
import org.eclipse.jst.jsf.designtime.internal.provisional.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.provisional.context.IExternalContextFactoryLocator;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.AbstractDTVariableResolver;


/**
 * Per-web-application manager that manages design time information for a corresponding
 * project.
 * 
 * Clients should not sub-class.
 * 
 * @author cbateman
 *
 */
public class DesignTimeApplicationManager 
{
    // TODO: load from property file?
    private static final String   PROPERTY_QUALIFIER = "org.eclipse.jst.jsf.designtime.internal";
    private static final String   SESSION_PROPERTY_NAME_PROJECT = "DesignTimeApplicationManager";
    private static final QualifiedName  SESSION_PROPERTY_KEY_PROJECT 
        = new QualifiedName(PROPERTY_QUALIFIER, SESSION_PROPERTY_NAME_PROJECT);
    
    private static final String   SESSION_PROPERTY_NAME_FACES_CONTEXT = "DTFacesContext";
    private static final QualifiedName SESSION_PROPERTY_KEY_FACES_CONTEXT
       =  new QualifiedName(PROPERTY_QUALIFIER, SESSION_PROPERTY_NAME_FACES_CONTEXT);
    
    private static final String   PERSIST_PROPERTY_NAME_EXTERNAL_CONTEXT_PROVIDER =
        "ExternalContextProvider";
    private static final QualifiedName PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER =
        new QualifiedName(PROPERTY_QUALIFIER, PERSIST_PROPERTY_NAME_EXTERNAL_CONTEXT_PROVIDER);
    
    private static final String   PERSIST_PROPERTY_NAME_VARIABLE_RESOLVER_PROVIDER =
        "VariableResolverProvider";
    private static final QualifiedName PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER 
       = new QualifiedName(PROPERTY_QUALIFIER, PERSIST_PROPERTY_NAME_VARIABLE_RESOLVER_PROVIDER);

    private static final String   PERSIST_PROPERTY_NAME_PROPERTY_RESOLVER_PROVIDER =
        "PropertyResolverProvider";
    private static final QualifiedName PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER =
        new QualifiedName(PROPERTY_QUALIFIER, PERSIST_PROPERTY_NAME_PROPERTY_RESOLVER_PROVIDER);

    private static final String    PERSIST_PROPERTY_NAME_METHOD_RESOLVER_PROVIDER =
        "MethodResolverProvider";
    private static final QualifiedName PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER =
        new QualifiedName(PROPERTY_QUALIFIER, PERSIST_PROPERTY_NAME_METHOD_RESOLVER_PROVIDER);

    private static final String   DEFAULT_EXTERNAL_CONTEXT_ID = 
        "org.eclipse.jst.jsf.core.externalcontext.default";
    
    private static final String   DEFAULT_VARIABLE_RESOLVER_ID = 
        "org.eclipse.jst.jsf.core.variableresolver.default";
    
    private static final String   DEFAULT_PROPERTY_RESOLVER_ID =
        "org.eclipse.jst.jsf.core.propertyresolver.default";
        
    private static final String   DEFAULT_METHOD_RESOLVER_ID =
        "org.eclipse.jst.jsf.core.methodresolver.default";
    
    /**
     * @param project
     * @return the app manager associated with project
     */
    public final static DesignTimeApplicationManager getInstance(IProject project)
    {
        if (project == null)
        {
            return null;
        }
        
        try
        {
            synchronized(project)
            {
                Object manager = 
                    project.getSessionProperty(SESSION_PROPERTY_KEY_PROJECT);
    
                if (manager == null)
                {
                    manager = new DesignTimeApplicationManager(project);
                    project.setSessionProperty(SESSION_PROPERTY_KEY_PROJECT, manager);
                }
                
                return (DesignTimeApplicationManager) manager;
            }
        }
        catch (CoreException ce)
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getBundle().getSymbolicName(), 0, "Problem loading design time appmanager", new Throwable(ce)));
        }
        
        return null;
    }
    
    // instance definition
    private final IProject                      _project;
    
    private DesignTimeApplicationManager(IProject project)
    {
        _project = project;
    }
    
    /**
     * @param file
     * @return the faces context for the file or null if not found
     */
    public DTFacesContext getFacesContext(IFile file)
    {
        try
        {
            synchronized(file)
            {
                Object context = file.getSessionProperty(SESSION_PROPERTY_KEY_FACES_CONTEXT);
                
                if (context == null)
                {
                    // TODO: change to non-anonymous class
                    IExternalContextFactoryLocator  locator = 
                        new IExternalContextFactoryLocator()
                    {
                        public AbstractDTExternalContextFactory getFactory() 
                        {
                            return (AbstractDTExternalContextFactory) 
                                     getResolver(PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER,
                                           JSFCorePlugin.getExternalContextProviders(),
                                           DEFAULT_EXTERNAL_CONTEXT_ID);
                        }
                    };
                    
                    context = new DTFacesContext(locator);
                    file.setSessionProperty(SESSION_PROPERTY_KEY_PROJECT, context);
                }
                
                return (DTFacesContext) context;
            }
        }
        catch (CoreException ce)
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle())
                .log(new Status(IStatus.ERROR, 
                        JSFCorePlugin.getDefault().getBundle().getSymbolicName(), 
                        0, "Problem loading design time facescontext", 
                        new Throwable(ce)));
        }
        
        return null;
    }
    
    /**
     * @param resolverPluginId
     * @throws CoreException
     */
    public synchronized void setExternalContextProvider(final String resolverPluginId)
                                throws CoreException
    {
        _project.setPersistentProperty
            (PERSIST_PROPERTY_KEY_EXTERNAL_CONTEXT_PROVIDER, resolverPluginId);
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
        return (AbstractDTVariableResolver) getResolver(PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER,
                                                        JSFCorePlugin.getVariableResolvers(),
                                                        DEFAULT_VARIABLE_RESOLVER_ID);
    }
    
    /**
     * Sets the plugin used to determine the designtime variable resolver.  To 
     * reset to the default, pass null.
     * 
     * @param resolverPluginId
     * @throws CoreException -- if the setting the new value fails
     */
    public synchronized void setVariableResolverProvider(final String resolverPluginId)
                                throws CoreException
    {
        _project.setPersistentProperty
            (PERSIST_PROPERTY_KEY_VARIABLE_RESOLVER_PROVIDER, resolverPluginId);
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
     * @return the designtime property resolver for this application 
     */
    public synchronized AbstractDTPropertyResolver getPropertyResolver()
    {
        return (AbstractDTPropertyResolver) getResolver(PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER,
                JSFCorePlugin.getPropertyResolvers(),
                DEFAULT_PROPERTY_RESOLVER_ID);
    }
    
    /**
     * @param resolverPluginId
     * @throws CoreException -- if setting the provider fails
     */
    public synchronized void setPropertyResolverProvider(final String resolverPluginId)
                                                  throws CoreException
    {
        _project.setPersistentProperty
            (PERSIST_PROPERTY_KEY_PROPERTY_RESOLVER_PROVIDER, resolverPluginId);       
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
        return (AbstractDTMethodResolver) getResolver(PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER,
                JSFCorePlugin.getMethodResolvers(),
                DEFAULT_METHOD_RESOLVER_ID);
    }

    /**
     * @param resolverPluginId
     * @throws CoreException -- if setting the plugin fails
     */
    public synchronized void setMethodResolverProvider(final String resolverPluginId)
                                            throws CoreException
    {
        _project.setPersistentProperty
            (PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER, resolverPluginId);       

    }
    
    /**
     * @return the id of the active design time variable resolver
     */
    public synchronized String getMethodResolverProvider()
    {
        return getResolverId(PERSIST_PROPERTY_KEY_METHOD_RESOLVER_PROVIDER,
                             DEFAULT_METHOD_RESOLVER_ID);
    }

    
    private Object getResolver(final QualifiedName pluginKey,
                               final Map   registry,
                               final String defaultId)
    {
        String pluginId = defaultId;
        
        try
        {
           pluginId =
                _project.getPersistentProperty(pluginKey);
           
           // if don't have the plugin in the registry, then 
           // revert to default
           if (pluginId == null)
           {
               pluginId = defaultId;
           }
           else if (registry.get(pluginId) == null)
           {
               JSFCorePlugin.getDefault().getLog().log(
                       new Status(IStatus.WARNING, JSFCorePlugin.PLUGIN_ID,
                                   0, "Plugin: "+pluginId+" not found",
                                   new Throwable()));
               pluginId = defaultId;
           }
        }
        catch (CoreException ce)
        {
            JSFCorePlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID,
                                0, "Error getting plugin property",
                                ce));
            pluginId = defaultId;
            // fall-through and use the default
        }

        return registry.get(pluginId);
    }
    
    private String getResolverId(final QualifiedName key, final String defaultValue)
    {
        String id = defaultValue; 

        try
        {
            final String userId = 
                _project.getPersistentProperty(key);
            
            if (userId != null)
            {
                id = userId;
            }
        }
        catch (CoreException ce)
        {
            // do nothing; fall through and return default
        }

        return id;
    }
}
