package org.eclipse.jst.jsf.common.sets.internal.provisional.mapping;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.internal.provisional.ConcreteAxiomaticSet;

/**
 * Super-class of all ObjectInjectiveSetMapping.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractObjectInjectiveSetMapping implements
        ObjectInjectiveSetMapping {

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.sets.internal.provisional.mapping.ObjectInjectiveSetMapping#mapIterator(org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet)
     */
    public Iterator mapIterator(final AxiomaticSet set) 
    {
        return new Iterator()
        {
            final Iterator  setIterator = set.iterator();
            
            public boolean hasNext() 
            {
                return setIterator.hasNext();
            }

            public Object next() 
            {
                return map(setIterator.next());
            }

            public void remove() 
            {
                throw new UnsupportedOperationException("cannot modify iterator"); //$NON-NLS-1$
            }
        };
    }

    /**
     * @param element
     * @return the result object from the mapping on element
     */
    public abstract Object map(Object element);
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.sets.internal.provisional.mapping.AxiomaticSetMapping#map(org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet)
     */
    public final AxiomaticSet map(AxiomaticSet set) {
       AxiomaticSet resultSet = new ConcreteAxiomaticSet();
       
       for (final Iterator it = mapIterator(set); it.hasNext();)
       {
           resultSet.add(it.next());
       }
       
       return resultSet;
    }

}
