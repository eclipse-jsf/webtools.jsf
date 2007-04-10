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
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;


/**
 * @author mengbo
 * @version 1.5
 */
public class JSFNavigationGroup
{
    private final static Action EMPTY_ACTION = new Action()
    {
        // TODO: why?
    };


    /**
     * @param menu
     * @param element
     * @param support
     */
    public void fillContextMenu(IMenuManager menu, final IDOMElement element, final IJSFCoreSupport support)
    {
        final IContributionItem item = 
            menu.find(PageDesignerActionConstants.NAVIGATE_SUBMENU_ID);
        
        if (item instanceof IMenuManager)
        {
            final IMenuManager submenu = (IMenuManager) item; 
            final IMenuManager javaMenu = new MenuManager(JSFUIPlugin.getResourceString("ElementEdit.Submenu.JavaNavigation"));//$NON-NLS-1$
            javaMenu.add(EMPTY_ACTION);
            javaMenu.addMenuListener(new IMenuListener()
            {
                public void menuAboutToShow(IMenuManager manager)
                {
                    javaMenu.removeAll();
                    addNavigationItems(javaMenu, element, support);
                }
            }
            );
            
            submenu.appendToGroup(PageDesignerActionConstants.GROUP_NAVIGATE, javaMenu);
        }
    }

    private void addNavigationItems(IMenuManager submenu, IDOMElement ele, IJSFCoreSupport support)
    {
        if (support.isActionSource())
        {
            ExpressionAction action = new ExpressionAction(ExpressionAction.METHOD);
            action.setText(JSFUIPlugin.getResourceString("ElementEdit.Submenu.JavaNavigation.Action"));//$NON-NLS-1$
            action.setActionValue(ele.getAttribute(ICSSPropertyID.ATTR_ACTION));
            if (ele != null)
            {
                IDOMModel model = ele.getModel();
                action.setProject(StructuredModelUtil.getProjectFor(model));
                action.setFile(StructuredModelUtil.getFileFor(model));
            }
            submenu.add(action);
        }
        if (support.isValueHolder())
        {
            ExpressionAction action = new ExpressionAction(ExpressionAction.VARIABLE);
            action.setText(JSFUIPlugin.getResourceString("ElementEdit.Submenu.JavaNavigation.Value"));//$NON-NLS-1$
            action.setActionValue(ele.getAttribute(ICSSPropertyID.ATTR_VALUE));
            if (ele != null)
            {
                IDOMModel model = ele.getModel();
                action.setProject(StructuredModelUtil.getProjectFor(model));
                action.setFile(StructuredModelUtil.getFileFor(model));
            }
            submenu.add(action);
        }

        {
            ExpressionAction action = new ExpressionAction(ExpressionAction.VARIABLE);
            action.setText(JSFUIPlugin.getResourceString("ElementEdit.Submenu.JavaNavigation.Binding"));//$NON-NLS-1$
            action.setActionValue(ele.getAttribute(ICSSPropertyID.ATTR_BINDING));
            if (ele != null)
            {
                IDOMModel model = ele.getModel();
                action.setProject(StructuredModelUtil.getProjectFor(model));
                action.setFile(StructuredModelUtil.getFileFor(model));
            }
            submenu.add(action);
        }
    }
}
