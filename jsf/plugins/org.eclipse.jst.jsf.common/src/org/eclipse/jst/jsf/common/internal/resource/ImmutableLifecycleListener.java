/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.internal.resource;


/**
 * A lifecycle listener abstraction that cannot have its resources modified.
 * Clients may only register and deregister for events.
 *
 * @author cbateman
 * @param <LISTENERTYPE> 
 *
 */
public abstract class ImmutableLifecycleListener<LISTENERTYPE extends ILifecycleListener>
{

    /**
     * Adds listener to the list of objects registered to receive 
     * lifecycle events for this resource.  Only adds the listener
     * if it is not already in the list.
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     * 
     * @param listener
     */
    public abstract void addListener(final LISTENERTYPE listener);

    /**
     * Removes listener from the list of registered listeners
     * 
     * Method is thread-safe and may block the caller
     * 
     * Throws {@link IllegalStateException} if isDisposed() == true
     *
     * @param listener
     */
    public abstract void removeListener(final LISTENERTYPE listener);
}