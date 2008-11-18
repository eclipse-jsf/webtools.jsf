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
package org.eclipse.jst.pagedesigner.ui.dialogs;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class StyleDialog extends PreferenceDialog {
	/**
	 * @param parentShell
	 * @param manager
	 * @param element 
	 * @param style 
	 */
	public StyleDialog(Shell parentShell, PreferenceManager manager,
			IDOMElement element, CSSPropertyContext style) {
		super(parentShell, manager);
		manager.addToRoot(new TextPreferenceNode(element, style));
		manager.addToRoot(new BackgroudPreferenceNode(element, style));
		manager.addToRoot(new BlockPreferenceNode(element, style));
		manager.addToRoot(new BoxPreferenceNode(element, style));
		manager.addToRoot(new BorderPreferenceNode(element, style));
		manager.addToRoot(new ListPreferenceNode(element, style));
		manager.addToRoot(new PositioningPreferenceNode(element, style));
		manager.addToRoot(new ExtensionsPreferenceNode(element, style));
	}

	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
				PDPlugin.getResourceString("StyleDialog.help.id")); //$NON-NLS-1$
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				getTreeViewer().getControl(),
				PDPlugin.getResourceString("StyleDialog.help.id")); //$NON-NLS-1$
		return control;
	}

	protected void update() {
		super.update();
		getShell().setText(DialogsMessages.getString("StyleDialog.Title")); //$NON-NLS-1$
	}
}
