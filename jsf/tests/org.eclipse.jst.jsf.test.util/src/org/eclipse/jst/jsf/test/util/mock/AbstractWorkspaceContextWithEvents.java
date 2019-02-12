/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.test.util.mock;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.Assert;

public abstract class AbstractWorkspaceContextWithEvents implements
        IWorkspaceContextWithEvents
{
    private AtomicBoolean _isInitialized = new AtomicBoolean(false);
    private AtomicBoolean _isDisposed = new AtomicBoolean(false);

    public final void init() throws Exception
    {
        if (_isInitialized.compareAndSet(false, true))
        {
            doInit();
            return;
        }
        throw new IllegalStateException();
    }

    protected abstract void doInit() throws Exception;

    public final void dispose() throws Exception
    {
        if (_isDisposed.compareAndSet(false, true))
        {
            doDispose();
            return;
        }
        throw new IllegalStateException();
    }

    protected abstract void doDispose() throws Exception;
    
    protected void assertInitialized()
    {
        Assert.assertTrue("context must be initialized", _isInitialized.get());
        Assert.assertFalse("context cannot be used after it is disposed", _isDisposed.get());
    }
}
