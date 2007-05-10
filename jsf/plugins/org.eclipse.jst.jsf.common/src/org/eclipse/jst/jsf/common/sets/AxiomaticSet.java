/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.sets;

import java.util.Set;

/**
 * A java.util.Set with the basic mathematic set axioms of 
 * extensionality (equality), union, intersection, 
 * relative complement (set subtraction) and empty (already 
 * supported by Set).
 * 
 * @author cbateman
 *
 */
public interface AxiomaticSet extends Set 
{
    /**
     * Implementations should aim to provide O(mn) time cost
     * where n is the number of elements in this set.  And
     * m is the cost to check membership of an element in this
     * set in toSet.  When a set is itself a member of a set,
     * the implementation must call isEquivalent on those subsets
     * recursively.
     * 
     * @param toSet 
     * 
     * @return true iff this set is equivalent toSet.  Note
     * that extensionality holds that two sets are equivalent
     * if and only if they contain exactly the same elements.
     * 
     *
     */
    boolean isEquivalent(AxiomaticSet toSet);
    
    /**
     * @param set
     * @return the axiomatic union of this set with set
     */
    AxiomaticSet union(AxiomaticSet set);
    
    
    /**
     * @param set
     * @return the axiomatic intersection of this set with set
     */
    AxiomaticSet intersect(AxiomaticSet set);
    
    /**
     * @param set
     * @return convenience method that must be equivalent to
     * (this.intersect(set).isEmpty())
     */
    boolean isDisjoint(AxiomaticSet set);
    
    /**
     * The set constructed by the removing the intersection
     * of this with set from this.  The set will contain all
     * elements in this that are not in set.
     * 
     * Eqivalence: this - set
     * 
     * @param set
     * @return the relative complement or theoretic difference of 
     * set from this
     */
    AxiomaticSet subtract(AxiomaticSet set);
    
    /**
     * @return the first element in the set.  There is no guarantee which element
     * will be chosen, but the call should always return the same element of the set
     * for multiple invocations on the same set.  Generally this is a convience method
     * for when the set only contains one element.
     * 
     * @throws java.util.NoSuchElementException if the set is empty.
     */
    Object getFirstElement();
}
