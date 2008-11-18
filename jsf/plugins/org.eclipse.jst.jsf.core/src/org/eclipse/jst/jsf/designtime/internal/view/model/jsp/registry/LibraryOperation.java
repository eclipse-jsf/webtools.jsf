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
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;

abstract class LibraryOperation implements Runnable
{
    protected final ITaglibRecord _changeRecord;
    private IStatus               _result;

    protected LibraryOperation(final ITaglibRecord changeRecord)
    {
        _changeRecord = changeRecord;
    }

    public final void run()
    {
        try
        {
            _result = doRun();
        }
        catch (final Exception e)
        {
            _result = new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID,
                    "Problem during run", e); //$NON-NLS-1$
        }
    }

    public IStatus getResult()
    {
        return _result;
    }

    protected abstract IStatus doRun();
}