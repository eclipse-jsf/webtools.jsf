/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * Classes in this package are related to JSF operation. 
 * 
 * @author mengbo
 * @version 1.5
 */
public class JSFAddChildAction extends Action
{
    private IDOMElement _parentEle;

    /**
     * 
     */
    public JSFAddChildAction(String text, IDOMElement parentNode)
    {
        super(text);
        this._parentEle = parentNode;
    }

    public IDOMElement getParentElement()
    {
        return _parentEle;
    }

    public Shell getShell()
    {
        IWorkbench bench = PlatformUI.getWorkbench();
        if (bench != null)
        {
            IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
            if (window == null)
            {
                IWorkbenchWindow[] windows = bench.getWorkbenchWindows();
                if (windows != null && windows.length > 0)
                {
                    window = windows[0];
                }
            }
            if (window != null)
            {
                return window.getShell();
            }
        }

        return null;
    }
}
