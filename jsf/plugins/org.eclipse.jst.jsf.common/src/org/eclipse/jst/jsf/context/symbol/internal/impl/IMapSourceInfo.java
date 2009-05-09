package org.eclipse.jst.jsf.context.symbol.internal.impl;

/**
 * Information about the map source used by the IMapTypeDescriptor
 *
 */
public interface IMapSourceInfo
{
    
    /**
     * @param key
     * @return true if the map source has changed since key was last set
     */
    boolean hasChanged(final Object key);
    
    /**
     * The key is used in the standard HashMap way.
     * 
     * @param key
     * @return the cached value for the key.
     */
    Object getCachedValue(final Object key);
    /**
     * Add cached value for key.
     * 
     * @param key
     * @param value
     */
    void putCachedValue(final Object key, final Object value);
}
