package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.internal.JSPUtil;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDTagRegistry;
import org.w3c.dom.Node;

/**
 * A default implementation of the design time view handler meant to 
 * parallel the default runtime ViewHandler required by the JSF spec. 
 *
 */
public class DefaultDTViewHandler extends AbstractDTViewHandler
{

    @Override
    public String calculateLocale(final DTFacesContext context)
            throws ViewHandlerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IResource getActionDefinition(final DTFacesContext context, final String viewId)
            throws ViewHandlerException {
        // TODO: this seems like a bit of a cope out...
        return context.adaptContextObject();
    }

    @Override
    public IPath getActionURL(final DTFacesContext context, final IResource resource,
            final IPath requestPath) throws ViewHandlerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getELExpression(final IModelContext context)
            throws ViewHandlerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPath getRelativeActionPath(final DTFacesContext context,
            final String relativeToViewId, final String uri) throws ViewHandlerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IViewDefnAdapterFactory getViewMetadataAdapterFactory(
            final DTFacesContext context) throws ViewHandlerException
    {
        final IResource res = context.adaptContextObject();

        if (res instanceof IFile)
        {
            return new DefaultViewDefnAdapterFactory(this);
        }

        return null;
    }

    static class DefaultViewDefnAdapterFactory extends AbstractViewDefnAdapterFactory
    {
        private final DefaultDTViewHandler _myViewHandler;

        DefaultViewDefnAdapterFactory(final DefaultDTViewHandler viewHandler)
        {
            _myViewHandler = viewHandler;
        }

        @Override
        public IViewDefnAdapter<Node,IDocument> createAdapter(final DTFacesContext context,
                final String viewId) 
        {
            try
            {
                final IResource res = _myViewHandler.getActionDefinition(context, viewId);
                
                if (res instanceof IFile)
                {
                    final IProject proj = res.getProject();
                    final TLDTagRegistry registry = TLDTagRegistry.getRegistry(proj);
                    final IFile srcFile = (IFile) res;
                    if (JSPUtil.isJSPContentType(srcFile)
                            && registry != null)
                    {
                        // if we have a jsp file, then return the default adapter
                        return new JSPViewDefnAdapter(registry);
                    }
                }
            }
            catch (final ViewHandlerException vhe)
            {
                JSFCorePlugin.log(vhe, "While acquiring view adapter");
            }

            // not found or failed
            return null;
        }
    }

    @Override
    protected DTUIViewRoot internalCreateView(final DTFacesContext facesContext, final String viewId) {
        return new DefaultDTUIViewRoot(facesContext, this);
    }
}
