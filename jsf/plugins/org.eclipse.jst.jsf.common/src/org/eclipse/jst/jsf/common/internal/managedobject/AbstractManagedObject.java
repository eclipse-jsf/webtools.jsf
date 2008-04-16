package org.eclipse.jst.jsf.common.internal.managedobject;

/**
 * Sub-class for managed objects.
 *
 */
public abstract class AbstractManagedObject implements IManagedObject 
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#dispose()
     */
    public abstract void dispose();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#checkpoint()
     */
    public abstract void checkpoint();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#destroy()
     */
    public abstract void destroy();

}
