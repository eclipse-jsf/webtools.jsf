/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.action.ShowPropertyViewAction;
import org.eclipse.ui.actions.ActionFactory;

/**
 * This is the context menu provider for pageflow editor.
 * 
 */
public class PageflowEditorContextMenuProvider extends ContextMenuProvider {

	/** the action registry */
	private final ActionRegistry actionRegistry;

	/**
	 * Creates a new PageflowEditorContextMenuProvider instance.
	 * 
	 * @param viewer -
	 *            the editor viewer
	 * @param actionRegistry -
	 *            action registry of the editor
	 */
	public PageflowEditorContextMenuProvider(EditPartViewer viewer,
			ActionRegistry actionRegistry) {
		super(viewer);
		this.actionRegistry = actionRegistry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ContextMenuProvider#buildContextMenu(IMenuManager)
	 */
	public void buildContextMenu(IMenuManager menuManager) {
		// add GEF menu groups, such as undo, edit, save, etc.
		GEFActionConstants.addStandardActionGroups(menuManager);

		// add typical undo/redo commands
		appendActionToUndoGroup(menuManager, ActionFactory.UNDO.getId());
		appendActionToUndoGroup(menuManager, ActionFactory.REDO.getId());

		// add edit commands, including copy, paste, delete
		appendActionToEditGroup(menuManager, ActionFactory.COPY.getId());
		appendActionToEditGroup(menuManager, ActionFactory.PASTE.getId());
		appendActionToEditGroup(menuManager, ActionFactory.DELETE.getId());

		// Add Open Editor Action
		appendActionToEditGroup(menuManager, FacesConfigEditor.EDITOR_ID);

		// append the save submenu
		appendActionToSaveGroup(menuManager, ActionFactory.SAVE.getId());

		// append the alignment submenu, including top, bottom, middle, left,
		// right and center.
		appendAlignmentSubmenu(menuManager);

		//append the show view submenu
		appendShowViewSubmenu(menuManager);
	}

	/**
	 * Appends the alignment submenu.
	 * 
	 * @param menuManager manager of workbench
	 */
	private void appendShowViewSubmenu(IMenuManager menuManager) {
		// Show View Actions
		MenuManager submenu = new MenuManager(
				PageflowMessages.Pageflow_Contextmenu_ShowView_Label);

		IAction action = getActionRegistry().getAction(
				ShowPropertyViewAction.SHOW_PROPERTY_VIEW);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		// action = getActionRegistry().getAction(
		// ShowPaletteViewAction.SHOW_PALETTE_VIEW);
		// if (null != action && action.isEnabled()) {
		// submenu.add(action);
		// }

		if (!submenu.isEmpty()) {
			menuManager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);
		}
	}

	/**
	 * Appends the alignment submenu.
	 * 
	 * @param menuManager manager of workbench
	 */
	private void appendAlignmentSubmenu(IMenuManager menuManager) {
		// Alignment Actions
		MenuManager submenu = new MenuManager(
				PageflowMessages.Pageflow_Contextmenu_Align_Label);

		IAction action = getActionRegistry().getAction(
				GEFActionConstants.ALIGN_LEFT);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM);
		if (null != action && action.isEnabled()) {
			submenu.add(action);
		}

		if (!submenu.isEmpty()) {
			menuManager.appendToGroup(GEFActionConstants.GROUP_EDIT, submenu);
		}
	}

	/**
	 * Returns the action registry.
	 * 
	 * @return - the action registry
	 */
	protected ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * Appends the specified action to the 'Undo' menu group
	 * 
	 * @param menu menu manager
	 * @param actionId action's ID
	 */
	private void appendActionToUndoGroup(IMenuManager menu, String actionId) {
		IAction action = getActionRegistry().getAction(actionId);
		if (null != action && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		}
	}

	/**
	 * Appends the specified action to the 'edit' menu group
	 * 
	 * @param menu menu manager
	 * @param actionId action's ID
	 */
	private void appendActionToEditGroup(IMenuManager menu, String actionId) {
		IAction action = getActionRegistry().getAction(actionId);
		if (null != action && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
	}

	/**
	 * Appends the specified action to the 'save' menu group
	 * 
	 * @param menu menu manager
	 * @param actionId action's ID
	 */
	private void appendActionToSaveGroup(IMenuManager menu, String actionId) {
		IAction action = getActionRegistry().getAction(actionId);
		if (null != action && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_SAVE, action);
		}
	}

	//Bug 367897 - Faces-config | Navigation Rule, provide un-usable menu items (Run As, Debug As, Profile As)
	private static List<String> DISALLOWED_CONTRIBUTION_IDS = new ArrayList<String>();
	static {
		DISALLOWED_CONTRIBUTION_IDS.add("org.eclipse.debug.ui.contextualLaunch.profile.submenu"); //$NON-NLS-1$
		DISALLOWED_CONTRIBUTION_IDS.add("org.eclipse.debug.ui.contextualLaunch.debug.submenu"); //$NON-NLS-1$
		DISALLOWED_CONTRIBUTION_IDS.add("org.eclipse.debug.ui.contextualLaunch.run.submenu"); //$NON-NLS-1$
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionManager#allowItem(org.eclipse.jface.action.IContributionItem)
	 */
	protected boolean allowItem(IContributionItem itemToAdd) {
		boolean allow = true;
		if (itemToAdd != null) {
			final String itemToAddId = itemToAdd.getId();
			if (itemToAddId != null) {
				allow = !DISALLOWED_CONTRIBUTION_IDS.contains(itemToAddId);
			}
		}
		return allow;
	}

}
