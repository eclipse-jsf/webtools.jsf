package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.wst.common.componentcore.ComponentCore;

/**
 * All IDTViewHandler's must sub-class this abstract class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDTViewHandler implements IDTViewHandler 
{
    /**
     * the path separator
     */
    public static final String PATH_SEPARATOR = "/";

    public abstract String calculateLocale(DTFacesContext context) throws ViewHandlerException;

    public DTUIViewRoot createView(final DTFacesContext facesContext, final String viewId)
                                    throws ViewHandlerException
    {
        final DTUIViewRoot viewRoot = internalCreateView(facesContext, viewId);
        viewRoot.setViewId(viewId);
        return viewRoot;
    }
        
    /**
     * @param facesContext 
     * @param viewId 
     * @return internal construction of the view root.
     */
    protected abstract DTUIViewRoot internalCreateView(final DTFacesContext facesContext, final String viewId);
    
    public abstract IResource getActionDefinition(DTFacesContext context, String viewId)
            throws ViewHandlerException;

    public abstract IPath getActionURL(DTFacesContext context, IResource resource,
            IPath requestPath) throws ViewHandlerException;

    public abstract String getELExpression(IModelContext context) throws ViewHandlerException;

    public abstract IPath getRelativeActionPath(DTFacesContext context,
        String relativeToViewId, String uri) throws ViewHandlerException;

    public abstract IViewDefnAdapterFactory getViewMetadataAdapterFactory(
        DTFacesContext context) throws ViewHandlerException;

//    public abstract IDTUIViewRoot populateView(DTFacesContext context,
//        IDTUIViewRoot viewRoot) throws ViewHandlerException;

    public String getViewId(final DTFacesContext context,final IResource res) 
    {
        // TODO: sync with WebrootUtil?
        String strWebrootPath = "";
        final IProject project = res.getProject();
        final IPath path = res.getFullPath();
        final IPath webContentPath = getWebContentPath(project);
        if (webContentPath != null && webContentPath.isPrefixOf(path)) {
            final int start = path.matchingFirstSegments(webContentPath);
            final String[] segments = path.segments();
            for (int i = start, n = path.segmentCount(); i < n; i++) {
                strWebrootPath = strWebrootPath
                        + PATH_SEPARATOR + segments[i];
            }
        }
        return strWebrootPath;
    }

    private IPath getWebContentPath(final IProject project) {
        if (project != null) {
            return ComponentCore.createComponent(project).getRootFolder().getUnderlyingFolder().getFullPath();
        }
        return null;
    }
}
