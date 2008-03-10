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
                    "Problem during run", e);
        }
    }

    public IStatus getResult()
    {
        return _result;
    }

    protected abstract IStatus doRun();
}