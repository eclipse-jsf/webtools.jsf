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
package org.eclipse.jst.pagedesigner.ui.dialogfields;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeStyleCommand;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.jst.pagedesigner.ui.dialogs.DialogsMessages;
import org.eclipse.jst.pagedesigner.ui.dialogs.StyleDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSStyleDeclaration;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * @author mengbo
 * @deprecated
 */
public class StyleButtonDialogField extends StringButtonDialogField implements
		IElementContextable {
	private IDOMElement _element;

	/**
	 * Equivalent to StyleButtonDialogField(null)
	 */
	public StyleButtonDialogField() {
		this(null);
	}

	/**
	 * @param element
	 */
	public StyleButtonDialogField(IDOMElement element) {
		this(null, element);
		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				browseButtonPressed();
			}
		});
		setButtonLabel(DialogsMessages.getString("StyleButtonDialogField.Edit"));//$NON-NLS-1$ 
	}

	/**
	 * @param adapter
	 * @param element
	 */
	public StyleButtonDialogField(IStringButtonAdapter adapter,
			IDOMElement element) {
		super(adapter);
		_element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable#setElementContext(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement)
	 */
	public void setElementContext(IDOMNode ancester, IDOMElement element) {
		this._element = element;
	}

	private void browseButtonPressed() {
		if (_element instanceof ElementCSSInlineStyle) {
			ICSSStyleDeclaration styleDeclaration = (ICSSStyleDeclaration) ((ElementCSSInlineStyle) _element)
					.getStyle();

			PreferenceManager manager = new PreferenceManager();
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell();

			CSSPropertyContext context = new CSSPropertyContext(
					styleDeclaration);
			StyleDialog dialog = new StyleDialog(shell, manager, _element,
					context);
			if (dialog.open() == Dialog.OK) {
				if (!context.isModified()) {
					return;
				}
				ChangeStyleCommand c = new ChangeStyleCommand(_element, context);
				c.execute();

				String style = (_element == null ? null : _element
						.getAttribute(IJSFConstants.ATTR_STYLE));
				setText(style);
			}
		}
	}
}
