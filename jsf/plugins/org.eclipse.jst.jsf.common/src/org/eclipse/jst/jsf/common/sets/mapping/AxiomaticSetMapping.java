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

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;

/**
 * Defines an axiomatic mapping from set to another.  In formal notation, this may
 * also be defined as a "function" or a "proposition".
 * 
 * Applying the map method can be seen as being equivalent to:
 * 
 * AxiomaticSetMapping: set -> map(set)
 * 
 * <p><b>Provisional API - subject to change</b></p>
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
