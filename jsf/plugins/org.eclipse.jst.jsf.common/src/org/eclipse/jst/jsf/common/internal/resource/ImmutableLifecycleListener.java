package org.eclipse.jst.jsf.common.internal.resource;


/**
 * A lifecycle listener abstraction that cannot have its resources modified.
 * Clients may only register and deregister for events.
 *
 * @author cbateman
 *
 */
public abstract class ImmutableLifecycleListener
{

    /**
     * Adds listener to the list of objects registered to receive 
     * lifecycle events for this resource.  Only adds the listener
     * if it is not already in the list.
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     * 
     * @param listener
     */
    public abstract void addListener(final IResourceLifecycleListener listener);

    /**
     * Removes listener from the list of registered listeners
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     *
     * @param listener
     */
    public abstract void removeListener(final IResourceLifecycleListener listener);
}