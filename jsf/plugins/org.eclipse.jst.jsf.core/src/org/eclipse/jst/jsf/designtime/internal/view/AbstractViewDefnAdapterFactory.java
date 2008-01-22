package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * Factories that create view definition adapters must extend this
 * class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractViewDefnAdapterFactory implements IViewDefnAdapterFactory 
{
    /**
     * @param context
     * @param viewId
     * @return a view adapter for the viewid or null if this factory
     * cannot produce an adapter for the underlying view definition.
     */
    public abstract IViewDefnAdapter createAdapter(DTFacesContext context, String viewId); 
}
