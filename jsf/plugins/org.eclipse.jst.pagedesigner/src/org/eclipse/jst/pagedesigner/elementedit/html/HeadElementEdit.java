/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit.html;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.editpolicies.HeadItemCreationEditPolicy;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * Simple ElementEdit implementation to support context menu insert
 * actions and drag and drop of tags into the HTML head tag.
 */
public class HeadElementEdit extends AbstractElementEdit
{
	private static final Action EMPTY_ACTION = new Action() {
        // create a default instance that does nothing.
        // Action is abstract but has no abstract methods
        // run() on this object is a NOOP
	};

	/* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager, org.w3c.dom.Element)
     */
	@Override
    public void fillContextMenu(IMenuManager contextMenu, Element ele) {
		super.fillContextMenu(contextMenu, ele);

		final IDOMElement element = (IDOMElement) ele;
		IContributionItem item = contextMenu.find(PageDesignerActionConstants.INSERT_SUBMENU_ID);
		if (item instanceof IMenuManager) {
			final IMenuManager submenu = (IMenuManager) item;
    		submenu.add(EMPTY_ACTION);
    		submenu.addMenuListener(new IMenuListener() {
    			public void menuAboutToShow(IMenuManager manager) {
    				submenu.removeAll();
    				addHeadAddItems(submenu, element);
    			}
    		});
		}
    }

    private void addHeadAddItems(IMenuManager submenu, IDOMElement element) {
		submenu.add(new InsertStylesheetLinkAction(element));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
	 */
	@Override
	public void createEditPolicies(ElementEditPart part) {
		part.installEditPolicy(ItemCreationRequest.REQ_ITEM_CREATION,
				new HeadItemCreationEditPolicy(part));
	}
}
