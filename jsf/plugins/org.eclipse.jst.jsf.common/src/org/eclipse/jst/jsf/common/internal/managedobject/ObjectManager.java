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
package org.eclipse.jst.jsf.common.internal.managedobject;


/**
 * An abstraction for a manager that manages managed objects based on a KEYTYPE
 * 
 * @author cbateman
 * 
 * @param <T>
 * @param <KEYTYPE>
 */
public abstract class ObjectManager<T extends IManagedObject, KEYTYPE> extends
        AbstractManagedObject
{

    /**
     * A valid instance of T for the key. The instance of T may be unique on a
     * per-key basis or may not.
     * 
     * @param key
     * @return an instance of the managed object associated with key
     * @throws ManagedObjectException
     *             if an error occurs during construction
     */
    public abstract T getInstance(KEYTYPE key) throws ManagedObjectException;

    @Override
    public abstract void destroy();

    @Override
    public abstract void checkpoint();

    @Override
    public abstract void dispose();

    /**
     * Indicates a problem that occurred during a managed object operation
     * 
     * @author cbateman
     * 
     */
    public static class ManagedObjectException extends Exception
    {
        /**
         * 
         */
        private static final long serialVersionUID = -8723548990029368844L;

        /**
         * 
         */
        public ManagedObjectException()
        {
            super();
        }

        /**
         * @param message
         * @param cause
         */
        public ManagedObjectException(final String message, final Throwable cause)
        {
            super(message, cause);
        }

        /**
         * @param message
         */
        public ManagedObjectException(final String message)
        {
            super(message);
        }

        /**
         * @param cause
         */
        public ManagedObjectException(final Throwable cause)
        {
            super(cause);
        }
    }
}
