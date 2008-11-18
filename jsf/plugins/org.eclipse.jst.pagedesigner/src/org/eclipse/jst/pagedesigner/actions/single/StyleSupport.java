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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public final class StyleSupport {
	/**
	 * @param menu
	 * @param part
	 * @param ele
	 */
	public static void createStyleAction(IMenuManager menu,
			ElementEditPart part, IDOMElement ele) {
		if (part == null) {
			return;
		}
		if (hasStyleAttribute(ele)) {
			IAction action = new ChangeStyleAction(part, ele);
			menu.appendToGroup(PageDesignerActionConstants.GROUP_STYLE, action);
		}
	}

	private static boolean hasStyleAttribute(IDOMElement ele) {
		CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
		if (decl == null) {
			return false;
		}
		if (decl.getAttributes().getNamedItem("style") != null) { //$NON-NLS-1$
			return true;
		}
		return false;
	}
	
	private StyleSupport()
	{
		// util class; no instantiation
	}
}
