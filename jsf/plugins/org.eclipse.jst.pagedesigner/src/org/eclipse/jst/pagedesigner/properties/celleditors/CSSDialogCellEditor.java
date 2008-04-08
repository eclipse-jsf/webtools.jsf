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
package org.eclipse.jst.pagedesigner.properties.celleditors;

import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeStyleCommand;
import org.eclipse.jst.pagedesigner.ui.dialogs.StyleDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSStyleDeclaration;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * A css dialog cell editor
 *
 */
public class CSSDialogCellEditor extends EditableDialogCellEditor {
	private IDOMElement _element;

	/**
	 * @param parent
	 * @param element 
	 */
	public CSSDialogCellEditor(Composite parent, IDOMElement element) {
		super(parent);
		_element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		ICSSStyleDeclaration styleDeclaration = (ICSSStyleDeclaration) ((ElementCSSInlineStyle) _element)
				.getStyle();

		PreferenceManager manager = new PreferenceManager();
		Shell shell = cellEditorWindow.getShell();

		final CSSPropertyContext context = new CSSPropertyContext(styleDeclaration);
		StyleDialog dialog = new StyleDialog(shell, manager, _element, context);
		if (dialog.open() == Window.OK) {
			if (context.isModified()) {			
				PlatformUI.getWorkbench().getDisplay().asyncExec(
	                    new Runnable()
	                    {
	                        public void run()
	                        {
	            				ChangeStyleCommand c = new ChangeStyleCommand(_element, context);
	                        	c.execute();
	                        }
	            });
			}
		}

		String style = (_element == null ? null : _element
				.getAttribute(IJSFConstants.ATTR_STYLE));
		return style == null ? "" : style; //$NON-NLS-1$
	}
}
