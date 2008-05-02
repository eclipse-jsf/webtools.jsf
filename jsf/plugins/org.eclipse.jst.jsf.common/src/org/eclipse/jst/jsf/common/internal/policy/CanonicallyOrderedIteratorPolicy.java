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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator policy that sorts a comparable iterator type canonically.  This
 * policy takes a private copy of the collectin passed in getIterator.  The
 * cost of the sort is incurred when getIterator() is called under the 
 * assumption that the iterator will normally be used immediately after 
 * construction, so there is little value in deferring.
 * 
 * @author cbateman
 *
 * @param <ITERATORTYPE>
 */
public class CanonicallyOrderedIteratorPolicy<ITERATORTYPE extends Comparable<ITERATORTYPE>> 
        implements IIteratorPolicy<ITERATORTYPE>
{

    public Iterator<ITERATORTYPE> getIterator(
            Collection<ITERATORTYPE> forCollection)
    {
        return new CanonicalIterator<ITERATORTYPE>(forCollection);
    }

    private final static class CanonicalIterator<ITERATORTYPE extends Comparable> implements Iterator<ITERATORTYPE>
    {
        private final Iterator<ITERATORTYPE>      _sortedIterator;
        private CanonicalIterator(final Collection<ITERATORTYPE> source)
        {
            List<ITERATORTYPE>  sortable = new ArrayList<ITERATORTYPE>(source);
            Collections.sort(sortable);
            _sortedIterator = sortable.iterator();
        }
        
        public boolean hasNext()
        {
            return _sortedIterator.hasNext();
        }
        
        public ITERATORTYPE next()
        {
            return _sortedIterator.next();
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
