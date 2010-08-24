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
