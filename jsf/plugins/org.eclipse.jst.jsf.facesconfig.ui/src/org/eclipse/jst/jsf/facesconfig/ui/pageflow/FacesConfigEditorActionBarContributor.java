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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.ui.internal.actions.ActionContributorXML;

public class FacesConfigEditorActionBarContributor extends ActionBarContributor {
	protected ActionContributorXML sourceActionContributor = null;

	private SubActionBars sourceActionBars;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ActionBarContributor#buildActions()
	 */
	protected void buildActions() {
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

		addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ActionBarContributor#declareGlobalActionKeys()
	 */
	protected void declareGlobalActionKeys() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see EditorActionBarContributor#contributeToToolBar(IToolBarManager)
	 */
	public void contributeToToolBar(IToolBarManager tbm) {
		tbm.add(getAction(ActionFactory.UNDO.getId()));
		tbm.add(getAction(ActionFactory.REDO.getId()));

		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_LEFT));
		tbm.add(getAction(GEFActionConstants.ALIGN_CENTER));
		tbm.add(getAction(GEFActionConstants.ALIGN_RIGHT));
		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_TOP));
		tbm.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
		tbm.add(getAction(GEFActionConstants.ALIGN_BOTTOM));

		tbm.add(new Separator());
		String[] zoomStrings = new String[] { ZoomManager.FIT_ALL,
				ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH };
		tbm.add(new ZoomComboContributionItem(getPage(), zoomStrings));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see EditorActionBarContributor#contributeToMenu(IMenuManager)
	 */
	public void contributeToMenu(IMenuManager menubar) {
		super.contributeToMenu(menubar);
		MenuManager viewMenu = new MenuManager("View");
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		menubar.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#init(org.eclipse.ui.IActionBars)
	 */
	public void init(IActionBars bars) {
		super.init(bars);
		sourceActionContributor = new ActionContributorXML();
		sourceActionBars = new SubActionBars(bars);
		sourceActionContributor.init(sourceActionBars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorActionBarContributor#dispose()
	 */
	public void dispose() {
		if (sourceActionContributor != null) {
			sourceActionContributor.dispose();
		}
		if (sourceActionBars != null) {
			sourceActionBars.dispose();
		}
		super.dispose();
	}

	/*
	 * (non-JavaDoc) Method declared on EditorActionBarContributor Registers the
	 * contributor with the multi-page editor for future editor action
	 * redirection when the active page is changed, and sets the active page.
	 */
	public void setActiveEditor(IEditorPart part) {
		IEditorPart activeNestedEditor = null;
		if (part instanceof FormEditor) {
			activeNestedEditor = ((FormEditor) part).getActiveEditor();
		}
		setActivePage(activeNestedEditor);
	}

	/**
	 * Sets the active page of the the multi-page editor to be the given editor.
	 * Redirect actions to the given editor if actions are not already being
	 * sent to it.
	 * <p>
	 * This method is called whenever the page changes. Subclasses must
	 * implement this method to redirect actions to the given editor (if not
	 * already directed to it).
	 * </p>
	 * 
	 * @param activeEditor
	 *            the new active editor, or <code>null</code> if there is no
	 *            active page, or if the active page does not have a
	 *            corresponding editor
	 */
	public void setActivePage(IEditorPart activeEditor) {
		if (activeEditor == null) {
			return;
		}
		if (activeEditor instanceof StructuredTextEditor) {
			sourceActionContributor.setActiveEditor(activeEditor);
			setSourceActionBarsActive(true);
		} else {
			setSourceActionBarsActive(false);
			ActionRegistry registry = (ActionRegistry) activeEditor
					.getAdapter(ActionRegistry.class);

			if (registry != null) {
				super.setActiveEditor(activeEditor);
			} else {
				getActionBars().updateActionBars();
			}
		}
	}

	private void setSourceActionBarsActive(boolean active) {
		IActionBars rootBars = getActionBars();
		if (active) {
			sourceActionBars.activate();
		} else {
			sourceActionBars.deactivate(true);
		}
		rootBars.clearGlobalActionHandlers();
		if (active) {
			Map handlers = sourceActionBars.getGlobalActionHandlers();
			if (handlers != null) {
				Set keys = handlers.keySet();
				for (Iterator iter = keys.iterator(); iter.hasNext();) {
					String id = (String) iter.next();
					rootBars.setGlobalActionHandler(id, (IAction) handlers
							.get(id));
				}
			}
		}
		rootBars.updateActionBars();
	}
}
