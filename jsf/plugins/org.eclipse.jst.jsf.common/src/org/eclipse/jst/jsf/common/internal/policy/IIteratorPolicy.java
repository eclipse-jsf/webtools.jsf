package org.eclipse.jst.jsf.common.internal.policy;

import java.util.Collection;
import java.util.Iterator;

/**
 * A policy that allows a strategy composite to change the order in which
 * it queries it's list of strategies.
 * 
 * @author cbateman
 * @param <ITERATORTYPE> 
 *
 */
public interface IIteratorPolicy<ITERATORTYPE>
{
    /**
     * @param forCollection 
     * @return an iterator that controls the ordering through forCollection
     * in a policy directed way.  The policy may take a copy of the collection
     * or may choose to iterate in place.
     */
    Iterator<ITERATORTYPE>  getIterator(Collection<ITERATORTYPE> forCollection);
}
