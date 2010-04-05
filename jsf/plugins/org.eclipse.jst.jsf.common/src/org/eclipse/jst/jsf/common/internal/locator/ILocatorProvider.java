package org.eclipse.jst.jsf.common.internal.locator;

import java.util.List;

/**
 * An object that provides zero or more locators to a client.
 * 
 * @author cbateman
 * @param <LOCATORTYPE> 
 *
 */
public interface ILocatorProvider<LOCATORTYPE>
{
    /**
     * Must be called before getLocators is called.  Implementations MUST
     * support multiple calls to initialize on the same instance but need not
     * do anything on any of them.
     */
    void initialize();

    /**
     * @return the list of locators.
     */
    List<? extends LOCATORTYPE> getLocators();
}
