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
package org.eclipse.jst.jsf.designtime;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapterFactory;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;

/**
 * Utility methods for dealing with {@link DesignTimeApplicationManager}
 * 
 * @author cbateman
 * 
 */
public class DTAppManagerUtil
{
    /**
     * @param context
     * @return the view adapter for context or null if none.
     */
    public static XMLViewDefnAdapter getXMLViewDefnAdapter(
            final IStructuredDocumentContext context)
    {
        final IWorkspaceContextResolver wkResolver =
                IStructuredDocumentContextResolverFactory.INSTANCE
                        .getWorkspaceContextResolver(context);

        final IResource res = wkResolver.getResource();

        if (res instanceof IFile)
        {
            final IProject project = res.getProject();
            if (project != null)
            {
                return getXMLViewDefnAdapter(project, (IFile) res);
            }
        }
        // not found
        return null;
    }

    /**
     * Equivalent to getXMLViewDefnAdapter(file.getProject(), file)
     * 
     * @param file
     * @return the XMLViewDefnAdapter or null
     */
    public static XMLViewDefnAdapter getXMLViewDefnAdapter(final IFile file)
    {
        return getXMLViewDefnAdapter(file.getProject(), file);
    }

    /**
     * @param project
     * @param file
     * @return an XMLViewDefnAdapter for file in project or null if not
     *         applicable
     */
    public static XMLViewDefnAdapter getXMLViewDefnAdapter(
            final IProject project, final IFile file)
    {
        final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(project);

        if (manager == null)
        {
            return null;
        }
        final IDTViewHandler viewHandler =
                DesignTimeApplicationManager.getInstance(project)
                        .getViewHandler();
        try
        {
            final DTFacesContext facesContext = manager.getFacesContext(file);
            
            if (facesContext != null)
            {
                final IViewDefnAdapterFactory factory =
                        viewHandler.getViewMetadataAdapterFactory(facesContext);
                final IViewDefnAdapter adapter =
                        factory.createAdapter(facesContext, viewHandler.getViewId(
                                facesContext, file));
    
                if (adapter instanceof XMLViewDefnAdapter)
                {
                    return (XMLViewDefnAdapter) adapter;
                }
            }
        }
        catch (final ViewHandlerException e)
        {
            JSFCorePlugin.log(e, ""); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * @param project
     * @return the view handler for project or null if none.
     */
    public static IDTViewHandler getViewHandler(final IProject project)
    {
        final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(project);

        if (manager == null)
        {
            return null;
        }
        return DesignTimeApplicationManager.getInstance(project)
                .getViewHandler();
    }

    /**
     * @param context
     * @return the view root handle from this context or null if can't get one.
     *     
     */
    public static IViewRootHandle getViewRootHandle(final IStructuredDocumentContext context)
    {
        final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory2.INSTANCE
                .getWorkspaceContextResolver(context);

        if (resolver == null)
        {
            return null;
        }

        final IResource resource = resolver.getResource();
        if (resource != null)
        {
            IProject project = resource.getProject();
            if (project != null)
            {
                return getViewRootHandle(resource);
            }
        }
        return null;
    }

    /**
     * @param res 
     * @return the view root handle for the resource
     */
    public static IViewRootHandle getViewRootHandle(final IResource res)
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(res.getProject());

        if (manager != null)
        {
            if (res instanceof IFile)
            {
                final DTFacesContext facesContext = manager
                        .getFacesContext((IFile) res);
                if (facesContext != null)
                {
                    return facesContext.getViewRootHandle();
                }
            }
        }
        return null;
    }
}
