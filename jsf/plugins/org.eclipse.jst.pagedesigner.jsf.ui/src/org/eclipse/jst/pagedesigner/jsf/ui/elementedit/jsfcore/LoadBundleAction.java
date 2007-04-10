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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfcore;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;


/**
 * @author mengbo
 */
public class LoadBundleAction extends Action
{
    private String   _baseName;
    private IProject _project;

    /**
     * Create a new action
     */
    public LoadBundleAction()
    {
        setText(JSFUIPlugin.getResourceString("ElementEdit.Submenu.LoadBundle"));//$NON-NLS-1$
    }

    /**
     * @param attribute
     */
    public void setBaseName(String attribute)
    {
        _baseName = attribute;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        if (_project == null || _baseName == null)
        {
            return;
        }

        IStorage storage = null;
        try
        {
            storage = LoadBundleUtil.getLoadBundleResource(_project, _baseName);
        }
        catch (CoreException e)
        {
            JSFUIPlugin.getAlerts().warning("Message.Warning.Title", e.getLocalizedMessage());//$NON-NLS-1$
            return;
        }

        if (storage instanceof IFile)
        {
            try
            {
                IDE.openEditor(getPage(), (IFile) storage, true);
            }
            catch (PartInitException e1)
            {
                JSFUIPlugin.getAlerts().warning("Message.Warning.Title", e1.getLocalizedMessage());//$NON-NLS-1$     
            }
        }
        else
        {
            IEditorInput input = new JarEntryEditorInput(storage);
            try
            {
                getPage().openEditor(input, "org.eclipse.ui.DefaultTextEditor");//$NON-NLS-1$
            }
            catch (PartInitException e1)
            {
                JSFUIPlugin.getAlerts().warning("Message.Warning.Title", e1.getLocalizedMessage());//$NON-NLS-1$
            }
        }
    }


    private IWorkbenchPage getPage()
    {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        return window.getActivePage();
    }

    /**
     * @param projectFor
     */
    public void setProject(IProject projectFor)
    {
        this._project = projectFor;
    }

    /**
     * @param inputName
     * @return the editor id of theditor to open for the inputName or null
     * if none can be ascertained
     */
    public String getEditorID(String inputName)
    {
        IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();

        // check for a default editor
        IEditorDescriptor editorDescriptor = registry.getDefaultEditor(inputName);

        // next check the OS for in-place editor (OLE on Win32)
        if (editorDescriptor == null && registry.isSystemInPlaceEditorAvailable(inputName))
        {
            editorDescriptor = registry.findEditor(IEditorRegistry.SYSTEM_INPLACE_EDITOR_ID);
        }

        // next check with the OS for an external editor
        if (editorDescriptor == null && registry.isSystemExternalEditorAvailable(inputName))
        {
            editorDescriptor = registry.findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
        }

        // next lookup the default text editor
        if (editorDescriptor == null)
        {
            editorDescriptor = registry.findEditor("org.eclipse.ui.DefaultTextEditor"); //$NON-NLS-1$
        }

        // if no valid editor found, bail out
        if (editorDescriptor == null)
        {
            return null;
        }

        return editorDescriptor.getId();
    }
    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#isEnabled()
     */
    public boolean isEnabled()
    {
        return _project != null && _baseName != null && !"".equals(_baseName);//$NON-NLS-1$
    }
}
