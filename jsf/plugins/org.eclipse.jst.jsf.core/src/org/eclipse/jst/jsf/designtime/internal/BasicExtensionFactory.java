/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.el.IInstancePerProjectResolver;
import org.osgi.framework.Bundle;

/**
 * @author cbateman
 * @param <EXTENSIONTYPE>
 * 
 */
public class BasicExtensionFactory<EXTENSIONTYPE>
{
    private final Bundle                              _bundle;
    private final String                              _extName;
    private Map<String, ExtensionData<EXTENSIONTYPE>> _registeredResolvers;
    private final String                              _elementName;
    private final ExtensionDataFactory                _extensionDataFactory;
    private final boolean _alwaysPerProject;

    /**
     * @param bundle
     * @param extName
     * @param elementName
     * @param alwaysPerProject 
     */
    public BasicExtensionFactory(final Bundle bundle, final String extName,
            final String elementName, final boolean alwaysPerProject)
    {
        _bundle = bundle;
        _extName = extName;
        _elementName = elementName;
        _extensionDataFactory = new ExtensionDataFactory<EXTENSIONTYPE>();
        _alwaysPerProject = alwaysPerProject;
    }

    /**
     * @return the resolvers. Lazily initialized.
     */
    public final Map<String, ExtensionData<EXTENSIONTYPE>> getExtensions()
    {
        if (_registeredResolvers == null)
        {
            _registeredResolvers = loadRegisteredExtensions();
        }

        return _registeredResolvers;
    }

    /**
     * @return the map of extensions by id.
     */
    protected Map<String, ExtensionData<EXTENSIONTYPE>> loadRegisteredExtensions()
    {
        final Map<String, ExtensionData<EXTENSIONTYPE>> resolvers = new HashMap<String, ExtensionData<EXTENSIONTYPE>>();

        final IExtensionPoint point = Platform.getExtensionRegistry()
                .getExtensionPoint(_bundle.getSymbolicName(), _extName);

        final IExtension[] extensions = point.getExtensions();

        for (final IExtension extension : extensions)
        {
            final IConfigurationElement[] elements = extension
                    .getConfigurationElements();

            for (final IConfigurationElement element : elements)
            {
                if (_elementName.equals(element.getName())
                        && element.getAttribute("class") != null //$NON-NLS-1$
                        && element.getAttribute("id") != null) //$NON-NLS-1$
                {
                    final ExtensionData extData = processExtension(element, _alwaysPerProject);

                    if (extData != null)
                    {
                        resolvers.put(extData.getId(), extData);
                    }
                }
            }
        }
        return resolvers;
    }

    /**
     * @param element
     * @param alwaysPerProject 
     * @return the extension data for the extension or null if can't be created
     */
    protected ExtensionData processExtension(final IConfigurationElement element, final boolean alwaysPerProject)
    {
        return _extensionDataFactory.createExtensionData(element, alwaysPerProject);
    }

    /**
     * @author cbateman
     * 
     * @param <EXTENSIONTYPE>
     */
    protected static class ExtensionDataFactory<EXTENSIONTYPE>
    {
        ExtensionData createExtensionData(final IConfigurationElement element,
                final boolean alwaysPerProject)
        {
            if (isPerProjectExtension(element, alwaysPerProject))
            {
                return new PerProjectExtensionData<EXTENSIONTYPE>(element);
            }
            return new SingleInstanceExtensionData<EXTENSIONTYPE>(element);
        }

        static boolean isPerProjectExtension(final IConfigurationElement element, final boolean alwaysPerProject)
        {
            if (alwaysPerProject)
            {
                return true;
            }
            final String flag = element.getAttribute("instancePerProject"); //$NON-NLS-1$
            // must check for null for backward compatibility, since
            // this attribute wasn't part of the original.
            if (flag != null)
            {
                return Boolean.valueOf(flag).booleanValue();
            }
            return false;
        }
    }

    /**
     * @param <EXTENSIONTYPE>
     */
    protected static class PerProjectExtensionData<EXTENSIONTYPE> extends
            ExtensionData<EXTENSIONTYPE>
    {
        private Map<IProject, EXTENSIONTYPE> _extensions;

        /**
         * @param element
         */
        protected PerProjectExtensionData(IConfigurationElement element)
        {
            super(element);
        }

        @Override
        public EXTENSIONTYPE getInstance(final IProject project)
        {
            if (!project.isAccessible())
            {
                _extensions.remove(project);
                return null;
            }

            if (_extensions == null)
            {
                _extensions = new HashMap<IProject, EXTENSIONTYPE>();
            }

            EXTENSIONTYPE instance = _extensions.get(project);

            if (instance == null)
            {
                try
                {
                    instance = createInstance();
                    if (instance instanceof IInstancePerProjectResolver)
                    {
                        ((IInstancePerProjectResolver) instance)
                                .setProject(project);
                    }
                    _extensions.put(project, instance);
                }
                catch (Exception e)
                {
                    JSFCorePlugin.log(e,
                            "Instantiating extension class for id: " + getId()); //$NON-NLS-1$
                }
            }
            return instance;
        }

        @Override
        public EXTENSIONTYPE removeInstance(IProject project)
        {
            if (_extensions != null)
            {
                return _extensions.remove(project);
            }
            return null;
        }
    }

    /**
     * @param <EXTENSIONTYPE>
     * 
     */
    protected static class SingleInstanceExtensionData<EXTENSIONTYPE> extends
            ExtensionData<EXTENSIONTYPE>
    {
        private EXTENSIONTYPE _instance;

        /**
         * @param element
         */
        protected SingleInstanceExtensionData(IConfigurationElement element)
        {
            super(element);
        }

        @Override
        public EXTENSIONTYPE getInstance(final IProject project)
        {
            if (_instance == null)
            {
                try
                {
                    _instance = createInstance();
                }
                catch (CoreException e)
                {
                    JSFCorePlugin.log(e,
                            "Instantiating extension class for id: " + getId()); //$NON-NLS-1$
                }
            }
            return _instance;
        }

        @Override
        public EXTENSIONTYPE removeInstance(IProject project)
        {
            final EXTENSIONTYPE removedItem = _instance;
            _instance = null;
            return removedItem;
        }
    }

    /**
     * Extension data
     * 
     * @param <EXTENSIONTYPE>
     * 
     */
    public abstract static class ExtensionData<EXTENSIONTYPE>
    {
        private final IConfigurationElement _element;

        /**
         * @param element
         */
        protected ExtensionData(final IConfigurationElement element)
        {
            super();
            _element = element;
        }

        /**
         * @return the unique id of the variable resolver extension
         */
        public String getId()
        {
            return _element.getAttribute("id"); //$NON-NLS-1$
        }

        /**
         * @param project
         * @return the instance. project may be ignored if there is only one
         *         global instance for this factory.
         */
        public abstract EXTENSIONTYPE getInstance(final IProject project);

        /**
         * @param project
         * @return the extension for the project, removing from any internal
         *         caching. May return null if getInstance was never called for
         *         project.
         */
        public abstract EXTENSIONTYPE removeInstance(final IProject project);

        /**
         * @return the resolver
         * @throws CoreException
         */
        protected EXTENSIONTYPE createInstance() throws CoreException
        {
            return (EXTENSIONTYPE) _element.createExecutableExtension("class"); //$NON-NLS-1$
        }
    }
}
