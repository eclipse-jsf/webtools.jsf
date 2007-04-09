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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This class represents a group of dialog fields, following the normal dialog
 * field's lifecycle.
 * 
 * The design of this class is to make a section could be reused in both dialog
 * environment and form based editor environment.
 * 
 * @author mengbo
 */
public abstract class DialogFieldGroup {
	private IDialogFieldChangeListener _defaultChangeListener;

	private IDialogFieldApplyListener _defaultApplyListener;

	/**
	 * set default handler, should be called before <code>initialize()</code>
	 * @param changelistener 
	 * 
	 */
	public void setDefaultChangeListener(
			IDialogFieldChangeListener changelistener) {
		_defaultChangeListener = changelistener;
	}

	/**
	 * Normally, the client should call this method in <code>initialize()</code>
	 * for those field that wants to use the default event handler.
	 * 
	 * @return could be null
	 */
	public IDialogFieldChangeListener getDefaultChangeListener() {
		return _defaultChangeListener;
	}

	/**
	 * set default handler, should be called before <code>initialize()</code>
	 * 
	 * @param applylistener
	 */
	public void setDefaultApplyListener(IDialogFieldApplyListener applylistener) {
		_defaultApplyListener = applylistener;
	}

	/**
	 * Normally, the client should call this method in <code>initialize()</code>
	 * for those field that wants to use the default event handler.
	 * 
	 * @return could be null
	 */
	public IDialogFieldApplyListener getDefaultApplyListener() {
		return _defaultApplyListener;
	}

	/**
	 * it is supposed to create all dialog fields and setup event listeners in
	 * this method.
	 * 
	 * Normally client will create DialogFieldSection first, then
	 * setDefaultChangeListener()/setDefaultApplyListener(), then call
	 * initialize().
	 */
	public abstract void initialize();

	/**
	 * reload data from underlying model and set them into the dialog fields.
	 * 
	 */
	public abstract void refreshData();

	/**
	 * layout the dialog fields.
	 * 
	 * @param toolkit
	 *            could be null
	 * @param parent
	 * 
	 */
	public abstract void layoutDialogFields(FormToolkit toolkit,
			Composite parent);

	/**
	 * validate whether the values in the dialog fields are valid. This method
	 * should also enable/disable dialog fields based on their current value and
	 * relationship
	 * 
	 * @return could return null. or an array containing null elements.
	 */
	public abstract IStatus[] validateDialogFields();
}
