/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.sets.mapping;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;

/**
 * Defines an axiomatic mapping from set to another.  In formal notation, this may
 * also be defined as a "function" or a "proposition".
 * 
 * Applying the map method can be seen as being equivalent to:
 * 
 * AxiomaticSetMapping: set -> map(set)
 * 
 * @author cbateman
 *
 */
public interface AxiomaticSetMapping 
{
    /**
     * @param set
     * @return the new set resulting from applying the map to set
     */
    AxiomaticSet map(AxiomaticSet set);
}
