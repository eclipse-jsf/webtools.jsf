package org.eclipse.jst.jsf.common.internal.managedobject;

/**
 * Represents an object that wishes to be managed by the framework.
 * 
 * Clients should not implement: sub-class AbstractManagedObject instead.
 *
 */
public interface IManagedObject 
{
    /**
     * Called by the object client to indicate is finished with the object
     */
    void dispose();
}
