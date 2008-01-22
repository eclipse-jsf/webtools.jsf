package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * <p>Interface for a factory that creates view definition adapters.</p>
 * 
 * @author cbateman
 *
 */
public interface IViewDefnAdapterFactory 
{
    /**
     * @param context
     * @param viewId
     * @return a view adapter for the viewid or null if this factory
     * cannot produce an adapter for the underlying view definition.
     */
    IViewDefnAdapter<?,?> createAdapter(DTFacesContext context, String viewId); 
}