package org.eclipse.jst.jsf.common.sets;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.NodeList;

/**
 * A set backed by a W3C NodeList so as to avoid copying.  Note that operations
 * that return new sets still invoke a copy.  Also, membership operations
 * require O(n) time.  This should generally used for sets that are assumed to be
 * small where O(n) search time will be small compared to doing a full copy.
 * 
 * This set is immutable (mutation methods throw exceptions)
 * 
 * @author cbateman
 *
 */
public class NodeSet extends AbstractSet implements AxiomaticSet 
{
    private NodeList        _data;
    
    /**
     * @param data  -- wrap the actual data source
     */
    public NodeSet(NodeList data)
    {
        _data = data;
    }
    
    public Iterator iterator() {
        return new Iterator()
        {
            private int  pos = 0;
            
            public boolean hasNext() {
                return pos < _data.getLength();
            }

            public Object next() {
                return _data.item(pos++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }

    public int size() {
        return _data.getLength();
    }

    public AxiomaticSet intersect(AxiomaticSet set) {
        ConcreteAxiomaticSet newSet = new ConcreteAxiomaticSet();
        AxiomaticSetUtil.intersect(newSet, this, set);
        return newSet;
    }

    public boolean isEquivalent(AxiomaticSet toSet) {
        if (this == toSet)
        {
            return true;
        }
        
        if (toSet instanceof NodeSet
                && ((NodeSet)toSet)._data == this._data)
        {
            return true;
        }
        
        // if different sizes, false
        if (toSet.size() != size())
        {
            return false;
        }
        
        // otherwise, compare item by item
        // Iterator through me with the hope that look ups
        // are more efficent in toSet 
        for (Iterator it = iterator(); it.hasNext();)
        {
            Object obj = it.next();
            
            if (!toSet.contains(obj))
            {
                // fail fase on membership mismatch
                return false;
            }
        }
        
        // if we get to here then the item by item match succeeded
        return true;
    }

    public AxiomaticSet union(AxiomaticSet set) {
        ConcreteAxiomaticSet newSet = new ConcreteAxiomaticSet();
        AxiomaticSetUtil.union(newSet, this, set);
        return newSet;
    }

    public boolean isDisjoint(AxiomaticSet set) {
        return AxiomaticSetUtil.isDisjoint(this, set);
    }

    public Object getFirstElement() {
        if (_data.getLength()>0)
        {
            return _data.item(0);
        }
        throw new NoSuchElementException("Set is empty"); //$NON-NLS-1$
    }

    public AxiomaticSet subtract(AxiomaticSet set) {
        return AxiomaticSetUtil.subtract(this, set);
    }
}
