package org.eclipse.jst.jsf.common.internal.locator;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Implements the bare minimum of AbstractLocator needed to treat it as a
 * sub-class by composition (delegation).
 * 
 * @author cbateman
 * @param <LOCATORTYPE> 
 * @param <CONTEXTTYPE> 
 * @param <IDTYPE> 
 *
 */
public class DefaultLocatorDelegate<LOCATORTYPE, CONTEXTTYPE, IDTYPE> extends
        AbstractLocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>
{

    /**
     * @param id
     * @param displayName
     */
    public DefaultLocatorDelegate(IDTYPE id, String displayName)
    {
        super(id, displayName);
    }

    
    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     */
    public DefaultLocatorDelegate(
            IDTYPE id,
            String displayName,
            LOCATORTYPE noResultValue,
            CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList)
    {
        super(id, displayName, noResultValue, mutableListenerList);
    }


    @Override
    protected LOCATORTYPE doLocate(CONTEXTTYPE context)
    {
        throw new UnsupportedOperationException("This method is abstract and should not be called"); //$NON-NLS-1$
    }
}
