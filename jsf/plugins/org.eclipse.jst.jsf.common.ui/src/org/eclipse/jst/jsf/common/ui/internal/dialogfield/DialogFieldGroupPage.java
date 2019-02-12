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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author mengbo
 */
public class DialogFieldGroupPage extends WizardPage {
	private DialogFieldGroup _group;

	private IDialogFieldChangeListener _defaultChangeListener = new IDialogFieldChangeListener() {
		public void dialogFieldChanged(DialogField field) {
			validate();
		}
	};

	/**
	 * @param pageName
	 * @param section
	 */
	public DialogFieldGroupPage(String pageName, DialogFieldGroup section) {
		super(pageName);
		_group = section;
		initializeGroup(_group);
	}

	/**
	 * child class could override this method.
	 * 
	 * @param group
	 */
	protected void initializeGroup(DialogFieldGroup group) {
		group.setDefaultChangeListener(_defaultChangeListener);
		group.initialize();
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		_group.layoutDialogFields(null, container);
		_group.refreshData();
		validate();
		this.setControl(container);
	}

	/**
	 * @return the dialog field group
	 */
	public DialogFieldGroup getDialogFieldGroup() {
		return _group;
	}

	/**
	 * validate all the dialog fields.  Updates
	 * status line and sets page completion if there are no errors
	 */
	private void validate() {
		IStatus[] statuses = _group.validateDialogFields();
		IStatus status = StatusUtil.getMostSevere(statuses);
		StatusUtil.applyToStatusLine(this, status);
		setPageComplete(status == null || status.getSeverity() != IStatus.ERROR);
	}
}
