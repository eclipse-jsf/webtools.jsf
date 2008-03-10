package org.eclipse.jst.jsf.common.internal.policy;

/**
 * An object with an unique identity
 * @author cbateman
 * @param <IDTYPE> 
 *
 */
public interface IIdentifiable<IDTYPE>
{
    /**
     * @return the unique identifier
     */
    IDTYPE getId();
    
    /**
     * @return a human-readable name for this identifier.
     */
    String getDisplayName();
}
