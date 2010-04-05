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
package org.eclipse.jst.jsf.common.internal.managedobject;

/**
 * Represents an object that wishes to be managed by the framework.
 * 
 * Clients should not implement: sub-class AbstractManagedObject instead.
 * 
 */
public interface IManagedObject
{
    /**
     * Called by the object client to indicate is finished with the object. The
     * object should save any data it may want between sessions, perhaps by
     * calling its checkpoint, before cleaning held resources and clearing
     * memory references.
     */
    void dispose();

    /**
     * @return true if dispose has been successfully called on the object.
     */
    boolean isDisposed();

    /**
     * Called to indicate that the object should remove all its data from both
     * memory and persistent storage. This differentiates it from dispose in
     * that it signals that this object and its associated state will never
     * again be loaded for associated objects.
     */
    void destroy();

    /**
     * Indicates that an object should flush cached data and make durable any
     * data it might want between sessions. It does not indicate a disposal of
     * the object.
     */
    void checkpoint();
}
