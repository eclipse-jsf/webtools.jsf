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

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.CSSUtil;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class StyleClassSupport {
	private final static String DEFAULT = PDPlugin
			.getResourceString("StyleClassSupport.Default"); //$NON-NLS-1$

	/**
	 * @param classmenu
	 * @param ele 
	 */
	public static void createStyleClassActions(IMenuManager classmenu,
			IDOMElement ele) {
		String styleClassAttr = getStyleClassAttributeName(ele);
		if (styleClassAttr == null) {
			return; // don't support style class
		}
		String styleClass = getStyleClass(ele);

		boolean needAdditional = true;
		ChangeAttributeAction action = new ChangeAttributeAction(DEFAULT, ele,
				styleClassAttr, null);
		if (styleClass == null || styleClass.length() == 0) {
			action.setChecked(true);
			needAdditional = false;
		}
		classmenu.add(action);
		String[] classes = CSSUtil.getCSSClasses(ele.getOwnerDocument());
		if (classes.length > 0) {
			classmenu.add(new Separator());
		}
		for (int i = 0; i < classes.length; i++) {
			ChangeAttributeAction action2 = new ChangeAttributeAction(
					classes[i], ele, styleClassAttr, classes[i]);
			if (classes[i].equalsIgnoreCase(styleClass)) {
				action2.setChecked(true);
				needAdditional = false;
			}
			classmenu.add(action2);
		}
		if (needAdditional) {
			ChangeAttributeAction action2 = new ChangeAttributeAction(
					styleClass, ele, styleClassAttr, styleClass);
			action2.setChecked(true);
			classmenu.add(action2);
		}
	}

	/**
	 * @param ele
	 * @return the style class for ele or null if none
	 */
	public static String getStyleClass(IDOMElement ele) {
		String styleClassAttr = getStyleClassAttributeName(ele);
		if (styleClassAttr != null) {
			return ele.getAttribute(styleClassAttr);
		}
        return null;
	}

	/**
	 * FIXME: This is a temparary impelementation, with everything hard coded.
	 * In the future, should have some INodeAdapter mechanism for each node to
	 * tell the style class attribute name.
	 * 
	 * @param ele
	 * @return the style class attribute name or null if none applies
	 */
	public static String getStyleClassAttributeName(IDOMElement ele) {
		CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
		if (decl == null) {
			return null;
		}
		String taguri = CMUtil.getTagURI(decl);
		if (taguri == null || ITLDConstants.URI_HTML.equals(taguri)) {
			if (decl.getAttributes().getNamedItem("class") != null) { //$NON-NLS-1$
				return "class"; //$NON-NLS-1$
			}
            return null;
		} else if (decl.getAttributes().getNamedItem("styleClass") != null) { //$NON-NLS-1$
			return "styleClass"; //$NON-NLS-1$
		} else if (decl.getAttributes().getNamedItem("class") != null) { //$NON-NLS-1$
			return "class"; //$NON-NLS-1$
		} else {
			return null;
		}
	}

}
