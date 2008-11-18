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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.actions.link.MakeLinkAction;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.editors.actions.DesignActionBarFactory;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.ui.actions.ActionGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class RangeActionGroup extends ActionGroup {
    // TODO: what is this?  a separator?
	private static final Action action = new Action() {
        // Action is abstract but has no abstract methods
        // so create a default empty action that does nothing
	};

	/**
	 * 
	 */
	public RangeActionGroup() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager contextMenu) 
    {
		DesignRange selection = fixUpSelection(getContext().getSelection());
		if (selection == null) {
			return;
		}
        IContributionItem styleSubMenuItem = 
            contextMenu.find(PageDesignerActionConstants.STYLE_SUBMENU_ID);
        
        if (styleSubMenuItem instanceof IMenuManager)
        {
            final IMenuManager subMenu = (IMenuManager) styleSubMenuItem;
    		if (getContext().getInput() instanceof IHTMLGraphicalViewer) {
    			addParagraphFormatMenu(subMenu, selection,
    					(IHTMLGraphicalViewer) getContext().getInput());
    			addHorizontalAlignMenu(subMenu, selection,
    					(IHTMLGraphicalViewer) getContext().getInput());
    			addTextStyleMenu(subMenu, (IHTMLGraphicalViewer) getContext()
    					.getInput());
    		}
            // TODO: the (commented out) copy of this method does nothing
    		//addListModeMenu(menu, selection);
    
    		// TODO: the (commented out) copy of this method does nothing
            //addTextFontMenu(menu, selection);
    
    		addLinkMenu(subMenu, selection);
        }
    }

	/**
	 * @param selection
	 * @return
	 */
	private DesignRange fixUpSelection(ISelection selection) {
		if (selection instanceof DesignRange) {
			return (DesignRange) selection;
		}
        return null;
	}

	private void addLinkMenu(IMenuManager menu, final DesignRange selection) {
		Action action1 = new MakeLinkAction(selection);
		menu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE, action1);
	}

	private void addTextStyleMenu(IMenuManager menu,
			final IHTMLGraphicalViewer viewer) {
		final IMenuManager submenu = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.TextStyle"));//$NON-NLS-1$
		submenu.add(action);

		submenu.setRemoveAllWhenShown(true);
		submenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				DesignerToolBarAction action1 = null;
				action1 = DesignActionBarFactory.getInstance().getStyleAction(
						IHTMLConstants.TAG_U);
				action1.setViewer(viewer);
				submenu.add(action1);

				action1 = DesignActionBarFactory.getInstance().getStyleAction(
						IHTMLConstants.TAG_B);
				action1.setViewer(viewer);
				submenu.add(action1);

				action1 = DesignActionBarFactory.getInstance().getStyleAction(
						IHTMLConstants.TAG_I);
				action1.setViewer(viewer);
				submenu.add(action1);

				action1 = DesignActionBarFactory.getInstance().getStyleAction(
						IHTMLConstants.TAG_SMALL);
				action1.setViewer(viewer);
				submenu.add(action1);

				action1 = DesignActionBarFactory.getInstance().getStyleAction(
						IHTMLConstants.TAG_BIG);
				action1.setViewer(viewer);
				submenu.add(action1);

			}
		});
		menu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE, submenu);
	}

	/**
	 * @param menu
	 * @param selection
	 */
//	private void addTextFontMenu(IMenuManager menu, DesignRange selection) {
//	    //
//	}

	/**
	 * @param menu
	 * @param selection
	 */
	private void addHorizontalAlignMenu(IMenuManager menu,
			final DesignRange selection, final IHTMLGraphicalViewer viewer) {
		// we have to initialize align nodes here for some refresh problem
		Element[] alignNodes = new Element[4];
		final String[] alignValues = new String[] { "left", "center", "right", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"justify" }; //$NON-NLS-1$
		Document document = viewer.getModel().getDocument();
		for (int i = 0; i < 4; i++) {
			Element node = document.createElement(IHTMLConstants.TAG_P);
			node.setAttribute(IHTMLConstants.ATTR_ALIGN, alignValues[i]);
			alignNodes[i] = node;
		}
		AlignSupport.setAlignNodes(alignNodes);

		final IMenuManager submenu = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.Align"));//$NON-NLS-1$
		submenu.add(action);
		submenu.setRemoveAllWhenShown(true);
		submenu.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				AlignSupport.createAlignActions(submenu, viewer);
			}
		});
		menu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE, submenu);

	}

	/**
	 * @param menu
	 * @param selection
	 */
//	private void addListModeMenu(IMenuManager menu, DesignRange selection) {
//	}

	/**
	 * @param menu
	 * @param selection
	 */
	private void addParagraphFormatMenu(IMenuManager menu,
			final DesignRange selection, final IHTMLGraphicalViewer viewer) {
		final IMenuManager submenu = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.ParagraphFormat"));//$NON-NLS-1$
		submenu.add(action);
		// Add the submenu.

		submenu.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				submenu.removeAll();
				ParagraphSupport.createParagraphActions(submenu, selection,
						viewer);
			}
		});
		menu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE, submenu);
	}
}
