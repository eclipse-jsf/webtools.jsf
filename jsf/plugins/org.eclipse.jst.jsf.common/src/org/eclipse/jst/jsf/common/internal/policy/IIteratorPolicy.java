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
