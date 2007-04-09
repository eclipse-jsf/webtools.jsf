package org.eclipse.jst.jsf.common.sets.internal.provisional.mapping;

import org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet;

/**
 * Defines an axiomatic mapping from set to another.  In formal notation, this may
 * also be defined as a "function" or a "proposition".
 * 
 * Applying the map method can be seen as being equivalent to:
 * 
 * AxiomaticSetMapping: set -> map(set)
 * 
 * @author cbateman
 *
 */
public interface AxiomaticSetMapping 
{
    /**
     * @param set
     * @return the new set resulting from applying the map to set
     */
    AxiomaticSet map(AxiomaticSet set);
}
