/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An iterator policy that creates Iterators that traverse a target collection
 * by returning items in the order they are in the policyOrder object pased at
 * construction.  The target collection passed to getIterator is copied, so the
 * iterator will not be effected by subsequent changes to the target.
 * 
 * NOTE: the policyOrder iterable collection should not be modified after it is
 * passed to the constructor.
 * 
 * The class is thread-safe, however the iterators are not.  That is, more than
 * one thread can safely call any of the public methods, however each Iterator
 * returned by getIterator can only be used safely by a single thread.
 * 
 * @author cbateman
 *
 * @param <ITERATORTYPE>
 */
public class IdentifierOrderedIteratorPolicy<ITERATORTYPE> implements
        IIteratorPolicy<ITERATORTYPE>
{
    private final Iterable<ITERATORTYPE>   _policyOrder;
    // controls whether the policy iterator will return items that are
    // not explicitly listed in policyOrder.
    private volatile boolean               _excludeNonExplicitValues = false;

    /**
     * @param policyOrder
     */
    public IdentifierOrderedIteratorPolicy(final Iterable<ITERATORTYPE> policyOrder)
    {
        _policyOrder = policyOrder;
    }
    
    /**
     * Default value is <b>false</b>.
     * 
     * @return if true, the iterator will not return values in the forCollection
     * passed to getIterator whose identifier are not explicitly listed in
     * the policyOrder,  If false, these values will be return after all
     * the policyOrder values have been returned.
     */
    public boolean isExcludeNonExplicitValues()
    {
        return _excludeNonExplicitValues;
    }

    /**
     * @param excludeNonExplicitValues
     */
    public void setExcludeNonExplicitValues(boolean excludeNonExplicitValues)
    {
        _excludeNonExplicitValues = excludeNonExplicitValues;
    }

    public Iterator<ITERATORTYPE> getIterator(
            final Collection<ITERATORTYPE> forCollection)
    {
        final boolean excludeNonExplicitValues = _excludeNonExplicitValues;
        return new MyIterator<ITERATORTYPE>(forCollection, excludeNonExplicitValues, _policyOrder);
    }

    private static class MyIterator<ITERATORTYPE> implements Iterator<ITERATORTYPE>
    {
        private final List<ITERATORTYPE>         _items;
        private final Iterator<ITERATORTYPE>    _policyIterator;
        private ITERATORTYPE                    _next;
        
        MyIterator(final Collection<ITERATORTYPE> collection,
                final boolean excludeNonExplicitValues,
                final Iterable<ITERATORTYPE> policyOrder)
        {
            _items = new ArrayList();
            _items.addAll(collection);

            _policyIterator = policyOrder.iterator();
            _next = findNext();
        }
        
        public boolean hasNext()
        {
            return _next != null;
        }

        public ITERATORTYPE next()
        {
            if (_next != null)
            {
                ITERATORTYPE next = _next;
                //calculate next one before returning
                _next = findNext();
                return next;
            }
            
            throw new NoSuchElementException("No more elements");
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        private ITERATORTYPE findNext()
        {
            while (_policyIterator.hasNext())
            {
                ITERATORTYPE next = _policyIterator.next();
                if (_items.contains(next))
                {
                    _items.remove(next);
                    return next;
                }
            }
            
            // we have exhausted the _items that are in the policy iterator
            // now return any further _items in the order they are in the list
            if (_items.size() > 0)
            {
                return _items.remove(0);
            }
            
            return null;
        }
    }
}
