/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.impl;

/**
 * Information about the map source used by the IMapTypeDescriptor
 *
 */
public interface IMapSourceInfo
{
    
    /**
     * @param key
     * @return true if the map source has changed since key was last set
     */
    boolean hasChanged(final Object key);
    
    /**
     * The key is used in the standard HashMap way.
     * 
     * @param key
     * @return the cached value for the key.
     */
    Object getCachedValue(final Object key);
    /**
     * Add cached value for key.
     * 
     * @param key
     * @param value
     */
    void putCachedValue(final Object key, final Object value);
}
