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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;


/**
 * @author mengbo
 * @version 1.5
 */
public class PageFlowContributor implements IContextMenuItemContributor
{
    private String _uri;

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor#setURI(java.lang.String)
     */
    public void setURI(String uri)
    {
        this._uri = uri;

    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor#getURI()
     */
    public String getURI()
    {
        return _uri;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor#setModel(org.eclipse.wst.sse.core.internal.provisional.IStructuredModel)
     */

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void fillContextMenu(IMenuManager manager, ISelection selection, IStructuredModel model, Control parentUI)
    {
        PageFlowAction action = new PageFlowAction();
        action.setId(PageFlowAction.ID);
        action.setModel(model);
        IMenuManager viewMgr = manager.findMenuUsingPath(PageDesignerActionConstants.SHOWVIEW_SUBMENU_ID);
        if (viewMgr != null)
        {
            viewMgr.add(action);
        }
        else
        {
            manager.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action);
        }
    }

    private static class PageFlowAction extends Action
    {
        /**
         * this action's id
         */
        public final static String ID = "org.eclipse.jst.pagedesigner.jsf.ui.actions.PageFlowAction"; //$NON-NLS-1$
        private IStructuredModel   model;

        /**
         * construct the action
         */
        public PageFlowAction()
        {
            setText(ActionsResources.getString("PageFlowContributor.Menu.PageFlow"));//$NON-NLS-1$
        }

        /**
         * @param model
         */
        public void setModel(IStructuredModel model)
        {
            this.model = model;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.action.IAction#run()
         */
        public void run()
        {
            IFile file = StructuredModelUtil.getFileFor(model);
            if (file != null)
            {
                try
                {
                   // FacesConfigUtil.locateJSPFile(file);
                }
                catch (IllegalArgumentException e)
                {
                    JSFUIPlugin.getAlerts().warning("Message.Warning.Title", e.getLocalizedMessage());//$NON-NLS-1$
                }
            }
        }
    }
}
