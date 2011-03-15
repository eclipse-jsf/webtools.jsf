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

import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.single.ChangeStyleCommand;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.ui.dialogs.StyleDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSStyleDeclaration;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * @author mengbo
 * @version 1.5
 */
public class ChangeStyleAction extends Action {
	private static final String MY_TEXT = PDPlugin
			.getResourceString("ChangeStyleAction.Text"); //$NON-NLS-1$

	private ElementEditPart _editPart;

	private IDOMElement _element;

	private String _attribute;

	/**
	 * @param part
	 * @param ele
	 */
	public ChangeStyleAction(ElementEditPart part, IDOMElement ele) {
		this(part, ele, "style");//$NON-NLS-1$
	}
	
	/**
	 * @param part
	 * @param ele
	 * @param styleAttributeName 
	 */
	public ChangeStyleAction(ElementEditPart part, IDOMElement ele, String styleAttributeName) {
		super(MY_TEXT);
		this._editPart = part;
		this._element = ele;
		this._attribute = styleAttributeName;
	}

	public void run() {
		ICSSStyleDeclaration styleDeclaration = (ICSSStyleDeclaration) ((ElementCSSInlineStyle) this._element)
				.getStyle();
		PreferenceManager manager = new PreferenceManager();
		EditPartViewer viewer = this._editPart.getViewer();
		Shell shell = viewer.getControl().getShell();

		CSSPropertyContext context = new CSSPropertyContext(styleDeclaration);
		StyleDialog dialog = new StyleDialog(shell, manager, _element, context);
		if (dialog.open() == Window.OK) {
			if (context.isModified()) {
				ChangeStyleCommand c = new ChangeStyleCommand(_element, _attribute, context);
				c.execute();
			}
		}
	}
}
