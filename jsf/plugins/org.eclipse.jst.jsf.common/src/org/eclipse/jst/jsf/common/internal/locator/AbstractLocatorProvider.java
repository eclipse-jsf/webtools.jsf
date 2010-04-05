package org.eclipse.jst.jsf.common.internal.locator;

import java.util.Collections;
import java.util.List;

/**
 * The super-class of all ILocatorProvider's.
 * 
 * @author cbateman
 * @param <LOCATORTYPE> 
 * 
 */
public abstract class AbstractLocatorProvider<LOCATORTYPE> implements ILocatorProvider<LOCATORTYPE>
{
    private boolean _isInit = false;

    public void initialize()
    {
        doInitialize();
        _isInit = true;
    }

    /**
     * Overrride to do the init. You can override initialize if you want to
     */
    protected abstract void doInitialize();

    public List<? extends LOCATORTYPE> getLocators()
    {
        if (!_isInit)
        {
            throw new IllegalStateException(
                    "initialize must be called before getLocators"); //$NON-NLS-1$
        }
        return doGetLocators();
    }

    /**
     * @return the locators.
     */
    protected abstract List<? extends LOCATORTYPE> doGetLocators();
    
    /**
     * A default implementation of the provider that simply sits on a list
     * of a locators.
     *
     * @param <LOCATORTYPE>
     */
    public static class DefaultLocatorProvider<LOCATORTYPE> extends AbstractLocatorProvider<LOCATORTYPE>
    {
        private final List<LOCATORTYPE> _locators;

        /**
         * @param locators
         */
        public DefaultLocatorProvider(List<LOCATORTYPE> locators)
        {
            _locators = locators;
        }
        @Override
        protected void doInitialize()
        {
            // nothing to do.
        }

        @Override
        protected List<? extends LOCATORTYPE> doGetLocators()
        {
            return Collections.unmodifiableList(_locators);
        }
    }
}
