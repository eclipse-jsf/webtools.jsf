package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * @author cbateman
 *
 * @param <VIEW_DEFN_BASE_TYPE>
 * @param <VIEW_CONTAINER_TYPE>
 */
public abstract class ComponentTreeConstructionStrategy<VIEW_DEFN_BASE_TYPE, VIEW_CONTAINER_TYPE>
{
    /**
     * @param context 
     * @param viewRoot 
     * @return a component tree rooted
     * XXX: need to accommodate different cases of fragments, invalid multiple roots
     * etc, perhaps with special surrogate root type
     */
    public abstract ComponentInfo createComponentTree(DTFacesContext context, DTUIViewRoot viewRoot);
}
