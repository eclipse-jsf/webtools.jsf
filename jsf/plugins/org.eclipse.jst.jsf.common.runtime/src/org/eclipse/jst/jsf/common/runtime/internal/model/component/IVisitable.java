package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Iterator;

/**
 * Implemented by a class that can accept visitors
 *
 */
interface IVisitable
{
    /**
     * Called on a visitable to accept a visitor
     * 
     * @param visitor
     */
    void accept(AbstractVisitor visitor);
    
    /**
     * @return an iterator that returns IVisitable children.
     */
    Iterator getVisitableChildren();
}
