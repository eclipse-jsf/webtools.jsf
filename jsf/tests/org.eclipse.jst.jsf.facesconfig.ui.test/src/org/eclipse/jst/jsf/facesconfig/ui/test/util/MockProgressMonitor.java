/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.test.util;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * mock progress monitor.
 * 
 * @author xgzhang
 * @version
 */
public class MockProgressMonitor implements IProgressMonitor
{

    /**
     * @see IProgressMonitor#beginTask
     */
    public void beginTask(String name, int totalWork)
    {
        //do nothing
    }

    /**
     * @see IProgressMonitor#done
     */
    public void done()
    {
        //do nothing
    }

    public void internalWorked(double work)
    {
        //do nothing
    }

    /**
     * @see IProgressMonitor#isCanceled
     */
    public boolean isCanceled()
    {
        return false;
    }

    /**
     * @see IProgressMonitor#setCanceled
     */
    public void setCanceled(boolean b)
    {
        //do nothing
    }

    /**
     * @see IProgressMonitor#setTaskName
     */
    public void setTaskName(String name)
    {
        //do nothing
    }

    /**
     * @see IProgressMonitor#subTask
     */
    public void subTask(String name)
    {
        //do nothing
    }

    /**
     * @see IProgressMonitor#worked
     */
    public void worked(int work)
    {
        //do nothing
    }
}
