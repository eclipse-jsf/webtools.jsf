package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @author cbateman
 *
 * @param <ITERATORTYPE>
 */
public class IdentifierOrderedIteratorPolicy<ITERATORTYPE> implements
        IIteratorPolicy<ITERATORTYPE>
{
    private final Iterable<ITERATORTYPE>   _policyOrder;
    
    /**
     * @param policyOrder
     */
    public IdentifierOrderedIteratorPolicy(final Iterable<ITERATORTYPE> policyOrder)
    {
        _policyOrder = policyOrder;
    }
    
    public Iterator<ITERATORTYPE> getIterator(
            final Collection<ITERATORTYPE> forCollection)
    {
        return new MyIterator(forCollection);
    }

    private class MyIterator implements Iterator
    {
        private final List<ITERATORTYPE>         _items;
        private final Iterator<ITERATORTYPE>    _policyIterator;
        private ITERATORTYPE                    _next;
        
        MyIterator(final Collection<ITERATORTYPE> collection)
        {
            _items = new ArrayList();
            _items.addAll(collection);

            _policyIterator = _policyOrder.iterator();
            _next = findNext();
        }
        
        public boolean hasNext()
        {
            return _next != null;
        }

        public Object next()
        {
            if (_next != null)
            {
                Object next = _next;
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
