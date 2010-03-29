package org.eclipse.jst.jsf.facelet.core.internal.view;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.AbstractViewDefnAdapterFactory;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapterFactory;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;

class ViewDefnAdapterFactory extends AbstractViewDefnAdapterFactory
{
    private final DTFaceletViewHandler _myViewHandler;
	private final IViewDefnAdapterFactory _defaultViewDefnAdapterFactory;

    ViewDefnAdapterFactory(final DTFaceletViewHandler viewHandler, final IViewDefnAdapterFactory defaultViewDefnAdapterFactory) 
    {
        _myViewHandler = viewHandler;
        _defaultViewDefnAdapterFactory = defaultViewDefnAdapterFactory;
    }

    @Override
    public IViewDefnAdapter<?, ?> createAdapter(DTFacesContext context, String viewId)
    {
        try
        {
            final IResource res =
                    _myViewHandler.getActionDefinition(context, viewId);

            if (res instanceof IFile)
            {
                final IFile srcFile = (IFile) res;
                final ITagRegistry registry = findTagRegistry(srcFile);
                if (registry != null) {
	                if (_myViewHandler.isHTMLContent(srcFile))
	                {
	                    return new FaceletViewDefnAdapter(registry);
	                }
                    // if we have a jsp file, then return the default
                    // adapter
	                return _defaultViewDefnAdapterFactory.createAdapter(context, viewId);	                
                }
            }
        }
        catch (final ViewHandlerException vhe)
        {
            JSFCorePlugin.log(vhe, "While acquiring view adapter"); //$NON-NLS-1$
        }

        // not found or failed
        return null;
    }

}
