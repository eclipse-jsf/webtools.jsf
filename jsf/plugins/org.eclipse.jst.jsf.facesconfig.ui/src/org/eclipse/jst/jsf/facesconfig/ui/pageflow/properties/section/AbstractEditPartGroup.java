/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author jchoi
 * @version
 */
/*package*/ abstract class AbstractEditPartGroup extends DialogFieldGroup {

	private StringDialogField descField;

	private StringDialogField displayNameField;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		displayNameField = new StringDialogField();
		displayNameField.setLabelText(PropertyMessages.property_displayName);
		displayNameField
				.setDialogFieldChangeListener(getDefaultChangeListener());
		descField = new StringDialogField();

		descField.setLabelText(PropertyMessages.property_description);
		descField.setDialogFieldChangeListener(getDefaultChangeListener());

	}


	/**
	 * @param toolkit
	 * @param parent
	 * @param col
	 */
	protected void layoutDialogFields(FormToolkit toolkit, Composite parent,
			int col) {
		displayNameField.doFillIntoGrid(toolkit, parent, col);
		descField.doFillIntoGrid(toolkit, parent, col);

	}

	/**
	 * @param elem
	 */
	protected void refreshData(PageflowElement elem) {
		descField.setTextWithoutUpdate(elem.getComment());
		displayNameField.setTextWithoutUpdate(elem.getName());
	}

	/**
	 * @return the description field
	 */
	protected StringDialogField getDescField() {
		return descField;
	}

	/**
	 * @return the display name field
	 */
	protected StringDialogField getDisplayNameField() {
		return displayNameField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#validateDialogFields()
	 */
	public IStatus[] validateDialogFields() {

		return null;
	}
}
