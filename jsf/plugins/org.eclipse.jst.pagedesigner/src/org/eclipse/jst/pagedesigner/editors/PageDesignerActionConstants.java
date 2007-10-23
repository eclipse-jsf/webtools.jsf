/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors;

import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * Common constants used to id specific locations in the canvas context menu.
 * 
 * @see SimpleGraphicalEditor.ContextMenuListener for construction order
 * 
 * @author mengbo
 */
public class PageDesignerActionConstants 
{
    /**
     * Id for the standard Edit sub-menu
     */
    public static final String EDIT_SUBMENU_ID = "org.eclipse.jst.pagedesigner.context.submenu.edit"; //$NON-NLS-1$
    /**
     * Id for the standard Select sub-menu
     */
    public static final String SELECT_SUBMENU_ID = "org.eclipse.jst.pagedesigner.context.submenu.select"; //$NON-NLS-1$
    /**
     * Id for the standard Insert sub-menu
     */
    public static final String INSERT_SUBMENU_ID = "org.eclipse.jst.pagedesigner.context.submenu.insert"; //$NON-NLS-1$
    /**
     * Id for the standard Navigate sub-menu
     */
    public static final String NAVIGATE_SUBMENU_ID = "org.eclipse.jst.pagedesigner.context.submenu.navigate"; //$NON-NLS-1$
    /**
     * Id for the standard Style sub-menu
     */
    public static final String STYLE_SUBMENU_ID = "org.eclipse.jst.pagedesigner.context.submenu.style"; //$NON-NLS-1$
    /**
     * Id for the standard Show(View) sub-menu
     */
    public static final String SHOWVIEW_SUBMENU_ID = "org.eclipse.jst.pagedesigner.viewMenuMgr"; //$NON-NLS-1$
    
	/**
	 * Undo action group
	 */
	public static final String GROUP_UNDO = GEFActionConstants.GROUP_UNDO;
	/**
	 * Standard Edit sub-menu action group
	 */
	public static final String GROUP_EDIT = GEFActionConstants.GROUP_EDIT;
	/**
	 * Group for container injected actions
	 */
	public static final String GROUP_CONTAINER = "org.eclipse.jst.pagedesigner.container"; //$NON-NLS-1$
	/**
	 * Standard Style sub-menu action group
	 */
	public static final String GROUP_STYLE = "org.eclipse.jst.pagedesigner.style"; //$NON-NLS-1$
	/**
	 * Group for third-party contributed tag-specific actions
	 */
	public static final String GROUP_SPECIAL = "org.eclipse.jst.pagedesigner.special"; //$NON-NLS-1$
    /**
     * Standard Select sub-menu action group
     */
    public static final String GROUP_SELECT = "org.eclipse.jst.pagedesigner.select"; //$NON-NLS-1$
    /**
     * Standard Insert sub-menu action group
     */
    public static final String GROUP_INSERT = "org.eclipse.jst.pagedesigner.insert"; //$NON-NLS-1$
    /**
     * Standard Navigate sub-menu action group
     */
    public static final String GROUP_NAVIGATE = "org.eclipse.jst.pagedesigner.navigate"; //$NON-NLS-1$


    /**
     * Add standard group separators for the Edit sub-menu
     * 
     * @param menu
     */
    public static final void addStandardEditActionGroups(IMenuManager menu)
    {
        menu.add(new Separator(GROUP_UNDO));
        menu.add(new Separator(GROUP_EDIT));
    }
   
	/**
	 * Adds standard group separators to the given MenuManager.
	 * 
	 * @param menu the MenuManager
	 */
	public static final void addStandardActionGroups(IMenuManager menu) {
		menu.add(new Separator(GROUP_CONTAINER));
		menu.add(new Separator(GROUP_SPECIAL));
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

    /**
     * Add standard group separators for the Style sub-menu
     * @param menu
     */
    public static void addStandardStyleActionGroups(IMenuManager menu) 
    {
        menu.add(new Separator(GROUP_STYLE));
    }

    /**
     * Add standard group separators for the Select sub-menu
     * @param menu
     */
    public static void addStandardSelectActionGroups(IMenuManager menu) {
        menu.add(new Separator(GROUP_SELECT));
    }

    /**
     * Add standard group separators for the Insert sub-menu
     * @param menu
     */
    public static void addStandardInsertActionGroups(IMenuManager menu) {
        menu.add(new Separator(GROUP_INSERT));
    }

    /**
     * Add standard group separators for the Navigate sub-menu
     * @param menu
     */
    public static void addStandardNavigateActionGroups(
            IMenuManager menu) {
        menu.add(new Separator(GROUP_NAVIGATE));
    }
}
