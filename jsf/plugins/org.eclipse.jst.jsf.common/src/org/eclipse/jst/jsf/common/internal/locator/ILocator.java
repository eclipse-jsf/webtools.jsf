package org.eclipse.jst.jsf.common.internal.locator;

import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;

/**
 * A generic location strategy object.
 * 
 * @author cbateman
 * 
 * @param <LOCATORTYPE>
 *            the type of object being returned by the locator
 * @param <CONTEXTTYPE>
 *            the type of the context object passed for starting and locating
 * @param <IDTYPE>
 *            the type of the id used to identify a locator
 * @noimplement extend AbstractLocator instead
 */
public interface ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE> extends
        IIdentifiableStrategy<CONTEXTTYPE, LOCATORTYPE, IDTYPE>
{
    /**
     * Initialize the locator optionally using initialContext.
     * 
     * Must be called before any other methods. Implementations should throw
     * IllegalStateException if this has not been called before anything else.
     * 
     * @param initialContext
     */
    void start(CONTEXTTYPE initialContext);

    /**
     * Implementations should return false if the system prevents them from
     * starting. It must also be checked before a client attempts to restart a
     * stopped locator. Must return false if isStarted is true.
     * 
     * @return true if the locator can be started
     */
    boolean canStart();

    /**
     * @return true if the start() has been called but stop has not yet been
     *         called.
     */
    boolean isStarted();

    /**
     * Stop the locator.
     */
    void stop();

    /**
     * @param listener
     */
    void addListener( final ILocatorChangeListener listener);

    /**
     * @param listener
     */
    void removeListener(final ILocatorChangeListener listener);

    /**
     * perform must call this method in any concrete implementation.
     * 
     * @param context
     * @return the located type.
     */
    LOCATORTYPE locate(CONTEXTTYPE context);
}
