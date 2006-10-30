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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


/**
 * @author mengbo
 * @version 1.5
 */
public class ExpressionAction extends Action
{
    public static final int METHOD   = 0;
    public static final int VARIABLE = 1;

    private String          _action;
    private IProject        _project;
    private IFile           _file;
    private int             _type;

    public ExpressionAction(int type)
    {
        _type = type;
    }

    /**
     * @param attribute
     */
    public void setActionValue(String attribute)
    {
        _action = attribute;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#isEnabled()
     */
    public boolean isEnabled()
    {
        return _project != null && isExpression();
    }

    private boolean isExpression()
    {
        boolean flag = false;
        if (_action != null)
        {
            flag = _action.startsWith("#{") && _action.endsWith("}");//$NON-NLS-1$ $NON-NLS-2$
        }
        return flag;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        IPageVariablesProvider pageVarProvider = (IPageVariablesProvider) getPage().getActiveEditor().getAdapter(
                IPageVariablesProvider.class);
        pageVarProvider.refresh();

        Object result = null;
        if (_type == METHOD)
        {
//            result = VariableResolverUtils.resolveMethodBinding(_action, _project, IWebFrameworkResolver.ACTION_METHOD,                    true);
        	result = null;
        }
        else
        {
//            result = VariableResolverUtils.resolveValueBinding(_action, _project, true);
        	result = null;
        }
        if (result == null)
        {
            JSFUIPlugin.getAlerts().warning("Message.Warning.Title", "Message.Warning.InvalidateExpression");//$NON-NLS-1$ $NON-NLS-2$
        }
    }

    /**
     * @param projectFor
     */
    public void setProject(IProject projectFor)
    {
        this._project = projectFor;
    }

    private IWorkbenchPage getPage()
    {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        return window.getActivePage();
    }

    public void setFile(IFile fileFor)
    {
        _file = fileFor;
    }

    public IFile getFile()
    {
        return _file;
    }
}
