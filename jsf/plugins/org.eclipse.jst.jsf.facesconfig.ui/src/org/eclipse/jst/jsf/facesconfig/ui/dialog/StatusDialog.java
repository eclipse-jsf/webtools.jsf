/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Zhi-peng Zhang
 * @version
 */

/**
 * A common Dialog with validation information.
 */
public abstract class StatusDialog extends Dialog {
	/** The validation image */
	private Label statusImage;

	/** The validation message */
	private Label statusLabel;

	/** The validation message content */
	private String statusMessage;

	/**
	 * 
	 * @param parentShell
	 */
	protected StatusDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Creates and returns the contents of the upper part of this dialog (above
	 * the validation widgets). User should create Layout for the parent parameter.
	 * 
	 * @param parent
	 *            the parent composite to contain the dialog area
	 * @return the dialog area control
	 */
	protected abstract Control createDialogContents(Composite parent);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(new GridLayout(2,false));

		Composite contents = new Composite(area, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		contents.setLayoutData(gd);
		createDialogContents(contents);

		statusImage = createLabel(area);
		statusImage.setImage(JFaceResources
				.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
		statusLabel = createLabel(area);
		statusImage.setVisible(false);
		return area;
	}

	private Label createLabel(Composite parent) {
		Label label = new Label(parent, SWT.LEFT);
		GridData data = new GridData();
		data.horizontalSpan = 1;
		data.horizontalAlignment = GridData.FILL;
		label.setLayoutData(data);
		return label;
	}

	protected abstract boolean isValid();

	protected void updateStatus() {
		if (isValid()) {
			statusImage.setVisible(false);
			statusLabel.setText(""); //$NON-NLS-1$
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		} else {
			statusImage.setVisible(true);
			statusLabel.setText(statusMessage == null ? "" : statusMessage);//$NON-NLS-1$
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
