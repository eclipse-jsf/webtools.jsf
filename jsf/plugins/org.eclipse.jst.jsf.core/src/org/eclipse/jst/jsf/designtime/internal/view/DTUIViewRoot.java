package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

/**
 * The interface for all design view root objects.
 *
 */
public abstract class DTUIViewRoot extends ComponentInfo
{
    /**
     * 
     */
    private static final long serialVersionUID = -6375907116774572269L;

    /*package*/ DTUIViewRoot(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo) {
        super(id, parent, componentTypeInfo, true);
    }

    /**
     * @return the view id
     */
    public abstract String getViewId();
    
    /**
     * @param viewId 
     * 
     */
    public abstract void setViewId(final String viewId);
    
    /**
     * <p>Update the view root to synchronize it with the corresponding
     * view definition.  It is up to the view handler that created the view
     * handler to configure the root so it knows how to access its view definition</p>
     * 
     * <p>This method may cause long running operations to be performed.  If
     * you provide a non-null runnable for runAfter, refresh() will run long 
     * running operation on a background thread and return quickly to the caller.
     * runAfter will be executed on successful (non-cancelled) completion of the
     * refresh operations.  Passing null will cause the calling thread to block
     * until necessary operations are complete.
     * 
     * No guarantee is made as to what thread runAfter will run on.
     * </p>
     * @param runAfter 
     */
    public abstract void refresh(final Runnable runAfter);
}
