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
 * @author mengbo
 */
public class PageDesignerActionConstants {
	public static final String GROUP_UNDO = GEFActionConstants.GROUP_UNDO;

	public static final String GROUP_EDIT = GEFActionConstants.GROUP_EDIT;

	public static final String GROUP_CONTAINER = "org.eclipse.jst.pagedesigner.container"; //$NON-NLS-1$

	public static final String GROUP_STYLE = "org.eclipse.jst.pagedesigner.style"; //$NON-NLS-1$

	public static final String GROUP_SPECIAL = "org.eclipse.jst.pagedesigner.special"; //$NON-NLS-1$

	public static final String MENUMGR_VIEW_ID = "org.eclipse.jst.pagedesigner.viewMenuMgr"; //$NON-NLS-1$

	/**
	 * Adds standard group separators to the given MenuManager.
	 * 
	 * @param menu
	 *            the MenuManager
	 */
	public static final void addStandardActionGroups(IMenuManager menu) {
		menu.add(new Separator(GROUP_UNDO));
		menu.add(new Separator(GROUP_EDIT));
		menu.add(new Separator(GROUP_CONTAINER));
		menu.add(new Separator(GROUP_STYLE));
		menu.add(new Separator(GROUP_SPECIAL));
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
}
