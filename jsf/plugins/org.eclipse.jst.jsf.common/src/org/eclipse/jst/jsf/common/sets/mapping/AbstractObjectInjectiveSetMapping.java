/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.sets.mapping;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;

/**
 * Super-class of all ObjectInjectiveSetMapping.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public abstract class AbstractObjectInjectiveSetMapping implements
        ObjectInjectiveSetMapping {

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.sets.mapping.ObjectInjectiveSetMapping#mapIterator(org.eclipse.jst.jsf.common.sets.AxiomaticSet)
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
     * @see org.eclipse.jst.jsf.common.sets.mapping.AxiomaticSetMapping#map(org.eclipse.jst.jsf.common.sets.AxiomaticSet)
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
