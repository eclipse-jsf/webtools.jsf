package org.eclipse.jst.jsf.designtime;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapterFactory;
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

        final IProject project = wkResolver.getProject();
        final IResource res = wkResolver.getResource();

        if (project != null && res instanceof IFile)
        {
            return getXMLViewDefnAdapter(project, (IFile) res);
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
            JSFCorePlugin.log(e, "");
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
}
