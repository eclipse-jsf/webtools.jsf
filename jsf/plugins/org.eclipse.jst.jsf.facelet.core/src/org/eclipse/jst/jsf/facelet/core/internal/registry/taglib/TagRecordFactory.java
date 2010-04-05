/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jem.internal.proxy.core.ICallbackRegistry;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributor;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
import org.eclipse.jem.internal.proxy.ide.IDERegistration;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.ELProxyContributor;
import org.eclipse.jst.jsf.facelet.core.internal.registry.ServletBeanProxyContributor;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib;

/**
 * Creates new tag records for a project.
 *
 */
public class TagRecordFactory
{
    private final ProxyFactoryRegistry _registry;
    private final IProject _project;

    /**
     * @param project
     */
    public TagRecordFactory(final IProject project)
    {
        _project = project;
        ProxyFactoryRegistry registry = NULL_REGISTRY;
        try
        {
            registry = createProxyRegistry(_project);
        } catch (final Exception e)
        {
            FaceletCorePlugin.log("While creatinng proxy", e); //$NON-NLS-1$
        }
        _registry = registry;
    }

    /**
     * @param taglibDefn
     * @return the new tag record
     */
    public IFaceletTagRecord createRecords(final FaceletTaglib taglibDefn)
    {
        IFaceletTagRecord retValue = null;

        if (taglibDefn instanceof FaceletLibraryClassTagLib)
        {
            if (_registry != NULL_REGISTRY)
            {
                final LibraryClassBasedTagRecord record = new LibraryClassBasedTagRecord(
                        _registry, (FaceletLibraryClassTagLib) taglibDefn,
                        _project);
                try
                {
                    record.initURI();
                    retValue = record;
                } catch (CoreException e)
                {
                    FaceletCorePlugin
                            .log("While creating record: " + record, e); //$NON-NLS-1$
                }
            }
        } else if (taglibDefn instanceof FaceletXMLDefnTaglib)
        {
            throw new UnsupportedOperationException();
            // final XMLBasedTagRecord record = new XMLBasedTagRecord(
            // (FaceletXMLDefnTaglib) taglibDefn);
            // retValue = record;
        } else
        {
            final XMLBasedTagRecord record = new XMLBasedTagRecord(taglibDefn);
            retValue = record;
        }
        return retValue;
    }

    /**
     * Dispose the registry.
     */
    public void dispose()
    {
        if (_registry != null && _registry != NULL_REGISTRY)
        {
            SafeRunner.run(new ISafeRunnable()
            {
                
                public void run() throws Exception
                {
                    _registry.terminateRegistry(false);
                }
                
                public void handleException(Throwable e)
                {
                    FaceletCorePlugin.log("While creatinng proxy", e); //$NON-NLS-1$
                }
            });
        }
    }

    private static ProxyFactoryRegistry createProxyRegistry(
            final IProject project) throws CoreException
    {
        final IConfigurationContributor[] contributor = new IConfigurationContributor[]
        { new ServletBeanProxyContributor(JSFVersion.V1_1),
                new ELProxyContributor(project) };

        return IDERegistration.startAnImplementation(contributor, false,
                project, project.getName(), FaceletCorePlugin.PLUGIN_ID,
                new NullProgressMonitor());
    }

    private final static NullProxyFactoryRegistry NULL_REGISTRY = new NullProxyFactoryRegistry();

    private static class NullProxyFactoryRegistry extends ProxyFactoryRegistry
    {

        @Override
        public ICallbackRegistry getCallbackRegistry()
        {
            throw new UnsupportedOperationException("This is null proxy"); //$NON-NLS-1$
        }

        @Override
        protected void registryTerminated(final boolean wait)
        {
            throw new UnsupportedOperationException("This is null proxy"); //$NON-NLS-1$
        }
    }
}