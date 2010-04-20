package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.jst.jsf.common.internal.managedobject.AbstractManagedObject;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;

/**
 * A managed object that tracks changes to a taglib resource.
 * 
 * @author cbateman
 *
 */
public class TaglibResourceTracker extends AbstractManagedObject implements
        IResourceLifecycleListener
{

    public EventResult acceptEvent(ResourceLifecycleEvent event)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkpoint()
    {
        // nothing currently persisted
    
    }

    @Override
    public void destroy()
    {
        // nothing currently persisted
    }

}
