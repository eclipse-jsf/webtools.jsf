/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.Messages;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * A per-resource singleton manager for TLDTagRegistry's.
 * 
 * @author cbateman
 * 
 */
public final class TLDRegistryManager extends
        ResourceSingletonObjectManager<TLDTagRegistry, IProject>
{
    // STATIC
    private final static String JST_WEB_MODULE = "jst.web"; //$NON-NLS-1$
    private static TLDRegistryManager INSTANCE;

    /**
     * @return the singleton instance
     */
    private static TLDRegistryManager getGlobalManager()
    {
        synchronized (TLDRegistryManager.class)
        {
            if (INSTANCE == null)
            {
                if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
                {
                    JSFCoreTraceOptions
                            .log("TLDRegistryManager: Initializing TLDRegistryManager singleton"); //$NON-NLS-1$
                }
                INSTANCE = new TLDRegistryManager();
            }

            return INSTANCE;
        }
    }

    private TLDRegistryManager()
    {
        super(ResourcesPlugin.getWorkspace());
        final ISaveParticipant participant = new MyWorkspaceSaveParticipant();
        
        try
        {
            ResourcesPlugin.getWorkspace().addSaveParticipant(JSFCorePlugin.getDefault().getPluginID()
                    , participant);
        }
        catch (CoreException e)
        {
            JSFCorePlugin.log(e, "TLDRegistryManager failed to install save participant"); //$NON-NLS-1$
        }
    }

    @Override
    protected TLDTagRegistry createNewInstance(final IProject project)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions
                    .log("TLDRegistryManager: creating new instance for " //$NON-NLS-1$
                            + project.toString());
        }

        return new TLDTagRegistry(project);
    }

    @Override
    protected void runAfterGetInstance(final IProject resource)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions
                    .log("TLDRegistryManager: Acquired instance for " //$NON-NLS-1$
                            + resource.toString());
        }
    }

    @Override
    protected void runBeforeGetInstance(final IProject resource)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions.log("TLDRegistryManager: Getting registry for " //$NON-NLS-1$
                    + resource.toString());
        }
    }

    /**
     * Adapter used to allow creation by extension point.
     * 
     * @author cbateman
     * 
     */
    public static class MyRegistryFactory extends TagRegistryFactory
    {
        @Override
        public ITagRegistry createTagRegistry(IProject project)
                throws TagRegistryFactoryException
        {
            try
            {
                return getGlobalManager().getInstance(project);
            }
            catch (ManagedObjectException e)
            {
                throw new TagRegistryFactoryException(e);
            }
        }

        @Override
        public boolean isInstance(IProject project)
        {
           return getGlobalManager().isInstance(project);
        }

        public String getDisplayName()
        {
            return Messages.TLDRegistryManager_DisplayName;
        }

        @Override
        public boolean projectIsValid(IProject project) {
            if (project == null)
            {
                return false;
            }

            // Check that this is a dynamic web project
            // (I.E. the JST Web facet is installed)
            try
            {
                if (ProjectFacetsManager.isProjectFacetDefined(JST_WEB_MODULE))
                {
                    IFacetedProject faceted = ProjectFacetsManager.create(project);
                    IProjectFacet webModuleFacet = ProjectFacetsManager.getProjectFacet(JST_WEB_MODULE);
                    if (faceted != null && faceted.hasProjectFacet(webModuleFacet))
                    {
                        return true;
                    }
                }
            }
            catch (CoreException ce)
            {
            	JSFCorePlugin.log(ce, "TLDRegistryManager failed checking web project"); //$NON-NLS-1$
            }

            return false;
        }
    }

    private class MyWorkspaceSaveParticipant implements ISaveParticipant
    {
        public void saving(ISaveContext context) throws CoreException
        {
            if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
            {
                JSFCoreTraceOptions.log("MyWorkspaceSaveParticipant.saving: Kind="+context.getKind()); //$NON-NLS-1$
            }
            try
            {
                if (context.getKind() == ISaveContext.FULL_SAVE)
                {
                    final Collection<IProject> projects = TLDRegistryManager.this
                            .getManagedResources();

                    for (final IProject project : projects)
                    {
                        saveProject(project);
                    }
                }
                else if (context.getKind() == ISaveContext.PROJECT_SAVE)
                {
                    saveProject(context.getProject());
                }

            }
            catch (ManagedObjectException moe)
            {
                throw new CoreException(new Status(IStatus.ERROR,
                        JSFCorePlugin.PLUGIN_ID, "Couldn't commit workspace", //$NON-NLS-1$
                        moe));
            }
        }

        private void saveProject(final IProject project)
                throws ManagedObjectException
        {
            if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
            {
                JSFCoreTraceOptions.log("MyWorkspaceSaveParticipant.saveProject: Project="+project.toString()); //$NON-NLS-1$
            }

            // check that there is already instance -- don't do anything if there
            // isn't already an instance for project.
            if (TLDRegistryManager.this.isInstance(project))
            {
                final TLDTagRegistry registry = TLDRegistryManager.this
                        .getInstance(project);
                if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
                {
                    JSFCoreTraceOptions.log("MyWorkspaceSaveParticipant.saveProject: calling checkpoint on registry: "+registry.toString()); //$NON-NLS-1$
                }

                registry.checkpoint();
            }
        }

        public void doneSaving(ISaveContext context)
        {
            // nothing to do
        }

        public void prepareToSave(ISaveContext context) throws CoreException
        {
            // nothing to do
        }

        public void rollback(ISaveContext context)
        {
            // nothing to do
        }
    }
}
