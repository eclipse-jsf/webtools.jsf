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

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Sub-class for managed objects.
 * 
 */
/**
 * @author cbateman
 *
 */
public abstract class AbstractManagedObject implements IManagedObject
{
    /**
     * Flag for checking disposal.
     */
    protected final AtomicBoolean _isDisposed = new AtomicBoolean(false);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#dispose
     * ()
     */
    public void dispose()
    {
        _isDisposed.set(true);
    }

    public boolean isDisposed()
    {
        return _isDisposed.get();
    }

    /**
     * Checks if this object is disposed and throws IllegalStateException if it
     * is.
     */
    protected final void assertNotDisposed()
    {
        if (isDisposed())
        {
            throw new IllegalStateException(this.toString() + " is disposed"); //$NON-NLS-1$
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#checkpoint
     * ()
     */
    public abstract void checkpoint();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#destroy
     * ()
     */
    public abstract void destroy();

}
