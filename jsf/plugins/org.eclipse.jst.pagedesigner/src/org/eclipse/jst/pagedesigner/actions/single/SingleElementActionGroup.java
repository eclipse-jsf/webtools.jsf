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
package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class SingleElementActionGroup extends ActionGroup {
    // TODO: why?
	private static final Action action = new Action() {
        // create a default instance that does nothing
        // Action is abstract but has no abstract methods
        // run() on this object is a NOOP
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		ElementEditPart part = fixUpSelection(getContext().getSelection());
		if (part == null) {
			return;
		}
		IDOMElement ele = (IDOMElement) part.getIDOMNode();
		addStylelMenu(menu, part, ele);

		// next add element special actions
		IElementEdit elementEdit = part.getElementEdit();
		if (elementEdit != null) {
			elementEdit.fillContextMenu(menu, ele);
		}
	}

	/**
	 * @param selection
	 * @return
	 */
	private ElementEditPart fixUpSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			if (sel.size() != 1) {
				return null;
			}
			if (sel.getFirstElement() instanceof ElementEditPart) {
				return (ElementEditPart) sel.getFirstElement();
			}
		}
		return null;
	}

	/**
	 * @param menu
	 * @param part
	 */
	private void addStylelMenu(IMenuManager menu, ElementEditPart part,
			IDOMElement ele) {
		// IMenuManager stylesub = new MenuManager("Style");
		IMenuManager stylesub = menu;

		addStyle(menu, part, ele);
		addStyleClassesMenu(stylesub, part, ele);

		if (DOMStyleUtil.supportStyleAttribute(ele)) {
			// addBorderStyleMenu(stylesub, part, ele);
			addColorMenu(stylesub, part, ele);
			addBackgroundMenu(stylesub, part, ele);
		}

		// menu.add(stylesub);
	}

	/**
	 * 
	 * @param menu
	 * @param part
	 * @param ele
	 */
	private void addStyle(IMenuManager menu, ElementEditPart part,
			IDOMElement ele) {
		StyleSupport.createStyleAction(menu, part, ele);
	}

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addStyleClassesMenu(IMenuManager stylesub,
			ElementEditPart part, final IDOMElement ele) {
		final IMenuManager classmenu = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.StyleClasses"));//$NON-NLS-1$
		StyleClassSupport.createStyleClassActions(classmenu, ele);
		/*
		 * classmenu.add(action); classmenu.addMenuListener(new IMenuListener() {
		 * 
		 * public void menuAboutToShow(IMenuManager manager) {
		 * classmenu.removeAll();
		 * StyleClassSupport.createStyleClassActions(classmenu, ele); } } );
		 */

		stylesub.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
				classmenu);
	}

	/**
	 * @param stylesub
	 * @param part
	 */
	/*
	 * private void addBorderStyleMenu(IMenuManager stylesub, ElementEditPart
	 * part, final IDOMElement ele) { final IMenuManager borderStyleSub = new
	 * MenuManager(PDPlugin
	 * .getResourceString("ActionGroup.Submenu.BorderStyle"));//$NON-NLS-1$
	 * borderStyleSub.add(action);
	 * 
	 * final String mode = BorderStyleSupport.getCurrentBorderStyle(ele);
	 * 
	 * borderStyleSub.addMenuListener(new IMenuListener() { public void
	 * menuAboutToShow(IMenuManager manager) { borderStyleSub.removeAll();
	 * BorderStyleSupport.createParagraphActions(borderStyleSub, ele, mode); }
	 * }); stylesub.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
	 * borderStyleSub); }
	 */

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addColorMenu(IMenuManager stylesub, ElementEditPart part,
			final IDOMElement ele) {
		final IMenuManager colorSub = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.Color"));//$NON-NLS-1$
		colorSub.add(action);
		colorSub.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				colorSub.removeAll();
				ColorSupport.createColorActions(colorSub, ele,
						ICSSPropertyID.ATTR_COLOR);
			}
		});
		stylesub.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
				colorSub);

	}

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addBackgroundMenu(IMenuManager stylesub, ElementEditPart part,
			final IDOMElement ele) {
		final IMenuManager colorSub = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.BackgroundColor"));//$NON-NLS-1$
		colorSub.add(action);
		colorSub.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				colorSub.removeAll();
				ColorSupport.createColorActions(colorSub, ele,
						ICSSPropertyID.ATTR_BACKGROUND_COLOR);
			}
		});

		stylesub.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
				colorSub);
	}
}
