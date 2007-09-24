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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

/**
 * @author mengbo
 */
public class HTagsInsertGroupAction extends DesignerToolBarAction implements
		IMenuCreator {
	private Menu _menu;

	private static Map _actions = new HashMap();

	/**
	 * @param image
	 * @param style
	 */
	public HTagsInsertGroupAction(ImageDescriptor image, int style) {
		super(
				PDPlugin
						.getResourceString("HTagsInsertGroupAction.ActionLabel.Hx"), IAction.AS_DROP_DOWN_MENU); //$NON-NLS-1$
		this.setImageDescriptor(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
	 */
	public Menu getMenu(Control parent) {
		if (_menu != null) {
			_menu.dispose();
		}
		_menu = new Menu(parent);
		addActionToMenu(_menu, IHTMLConstants.TAG_H1);
		addActionToMenu(_menu, IHTMLConstants.TAG_H2);
		addActionToMenu(_menu, IHTMLConstants.TAG_H3);
		addActionToMenu(_menu, IHTMLConstants.TAG_H4);
		addActionToMenu(_menu, IHTMLConstants.TAG_H5);
		addActionToMenu(_menu, IHTMLConstants.TAG_H6);

		return _menu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
	 */
	public Menu getMenu(Menu parent) {
		return null;
	}

	/**
	 * @param parent
	 * @param name
	 */
	protected void addActionToMenu(Menu parent, String name) {
		DesignerToolBarAction action;
		if (_actions.get(name) == null) {
			action = new ParagraphStyleAction(name, name, null,
					IAction.AS_CHECK_BOX);
			_actions.put(name, action);
		} else {
			action = (ParagraphStyleAction) _actions.get(name);
		}
		action.setViewer(getViewer());
		action.update();
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IMenuCreator#dispose()
	 */
	public void dispose() {
		if (_menu != null) {
			_menu.dispose();
			_menu = null;
			_actions.clear();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#getMenuCreator()
	 */
	public IMenuCreator getMenuCreator() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.actions.DesignerToolBarAction#canRun(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected boolean isApplied(DOMRange range) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#getCommand()
	 */
	protected Command getCommand() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#setViewer(org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer)
	 */
	public void setViewer(IHTMLGraphicalViewer viewer) {
		if (_actions != null && _actions.size() > 0) {
			Collection values = _actions.values();
			Iterator iterator = values.iterator();
			while (iterator.hasNext()) {
				DesignerToolBarAction action = (DesignerToolBarAction) iterator
						.next();
				action.setViewer(viewer);
			}
		}
		super.setViewer(viewer);
	}

	/*
	 * The group will delegate update to its children.
	 */
	public void updateStatus() {
		if (_actions != null && _actions.size() > 0) {
			Collection values = _actions.values();
			Iterator iterator = values.iterator();
			while (iterator.hasNext()) {
				DesignerToolBarAction action = (DesignerToolBarAction) iterator
						.next();
				action.updateStatus();
			}
		}
	}
}
