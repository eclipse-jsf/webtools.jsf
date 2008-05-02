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
