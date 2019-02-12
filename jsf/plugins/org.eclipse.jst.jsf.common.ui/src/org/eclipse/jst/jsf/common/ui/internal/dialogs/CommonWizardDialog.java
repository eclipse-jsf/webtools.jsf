/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Wizard Dialog with a Finish set to OK if no previous
 * or next buttons are needed
 *
 */
public class CommonWizardDialog extends WizardDialog {

	/**
	 * @param parentShell
	 * @param newWizard
	 */
	public CommonWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
	}

	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);

		IWizard wizard = getWizard();
		if (!wizard.needsPreviousAndNextButtons()) {
			getButton(IDialogConstants.FINISH_ID).setText(
					IDialogConstants.OK_LABEL);
		}
	}
}
