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
package org.eclipse.jst.pagedesigner.elementedit.jsp;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TaglibElementEdit extends AbstractElementEdit {
	private TaglibURIAction action;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager,
	 *      org.w3c.dom.Element)
	 */
	public void fillContextMenu(IMenuManager contextMenu, Element ele) {
		super.fillContextMenu(contextMenu, ele);

		TaglibURIAction action1 = getAction();

		action1.setURI(ele.getAttribute(ICSSPropertyID.ATTR_URI));

		action1.setElement(ele);

        contextMenu.appendToGroup(PageDesignerActionConstants.GROUP_SPECIAL,
				action1);
	}

	private TaglibURIAction getAction() {
		if (action == null) {
			action = new TaglibURIAction();
		}
		return action;
	}
}
