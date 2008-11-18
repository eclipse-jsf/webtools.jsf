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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
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
import org.w3c.dom.Element;

/**
 * Group that constructs context menu items for a single selected element.
 * 
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
        addPositionRelativeMenu(menu, part, ele);
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
     * Add menu actions that select relative to the current position
     * 
     * @param menu
     * @param part
     * @param ele
     */
    private void addPositionRelativeMenu(IMenuManager menu, ElementEditPart part, IDOMElement ele)
    {
        IContributionItem subMenuItem = menu.find(PageDesignerActionConstants.SELECT_SUBMENU_ID);
        
        if (subMenuItem instanceof IMenuManager)
        {
            final IMenuManager subMenu = (IMenuManager) subMenuItem;
    //        final IMenuManager selectMenu = new MenuManager(PDPlugin
    //                .getResourceString("ActionGroup.Submenu.SelectRelative"));//$NON-NLS-1$
            SelectParentAction  selectParentAction = SelectParentAction.create(ele, part);
            // Eclipse UI guideline: 6.13
            // even if there is no parent, a disabled action will be returned by
            // create and this should be added to the menu
            subMenu.add(selectParentAction);
            
            List children = new ArrayList();
            for (Iterator it = part.getChildren().iterator(); it.hasNext();)
            {
                EditPart childPart = (EditPart) it.next();
                
                // only include selectable element edit part that are modelling
                // XML elements in the source doc
                if (childPart instanceof ElementEditPart
                        && ((ElementEditPart)childPart).isSelectable()
                        && ((ElementEditPart)childPart).getModel() instanceof Element)
                {
                    children.add(SelectNodeAction
                            .create(((Element)((ElementEditPart)childPart).getModel()).getNodeName(), childPart));
                }
            }

            
            // don't add the select Children menu unless there are actually children
            if (children.size() > 0)
            {
                MenuManager selectChildMenu = new MenuManager(Messages.SingleElementActionGroup_ChildrenActionText);
                subMenu.add(selectChildMenu);

                for (final Iterator it = children.iterator(); it.hasNext();)
                {
                    selectChildMenu.add((Action)it.next());
                }
            }
            else
            {   // Eclipse UI guideline 6.13
                // create the child actions even if no children but make it
                // a disabled option if no children
                Action childrenAction = new Action(Messages.SingleElementActionGroup_ChildrenActionText){/* do nothing*/};
                childrenAction.setEnabled(false);
                subMenu.add(childrenAction);
            }
        }
    }
    
	/**
	 * @param menu
	 * @param part
	 */
	private void addStylelMenu(IMenuManager menu, ElementEditPart part,
			IDOMElement ele) {
        IContributionItem subMenu = 
            menu.find(PageDesignerActionConstants.STYLE_SUBMENU_ID);

        if (subMenu instanceof IMenuManager)
        {
            final IMenuManager subMenuManager = 
                (IMenuManager) subMenu;
    		addStyle(subMenuManager, part, ele);
    		addStyleClassesMenu(subMenuManager, part, ele);
    
    		if (DOMStyleUtil.supportStyleAttribute(ele)) {
    			// addBorderStyleMenu(stylesub, part, ele);
    			addColorMenu(subMenuManager, part, ele);
    			addBackgroundMenu(subMenuManager, part, ele);
    		}
        }
	}

	/**
	 * 
	 * @param menu
	 * @param part
	 * @param ele
	 */
	private void addStyle(IMenuManager subMenu, ElementEditPart part,
			IDOMElement ele) {
        StyleSupport.createStyleAction(subMenu, part, ele);
	}

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addStyleClassesMenu(IMenuManager subMenu,
			ElementEditPart part, final IDOMElement ele) {
		final IMenuManager classmenu = new MenuManager(PDPlugin
				.getResourceString("ActionGroup.Submenu.StyleClasses"));//$NON-NLS-1$
		StyleClassSupport.createStyleClassActions(classmenu, ele);

        subMenu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
                classmenu);
	}

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addColorMenu(IMenuManager subMenu, ElementEditPart part,
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
        subMenu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
				colorSub);

	}

	/**
	 * @param stylesub
	 * @param part
	 */
	private void addBackgroundMenu(IMenuManager subMenu, ElementEditPart part,
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

        subMenu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE,
				colorSub);
	}
}
