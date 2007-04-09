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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author mengbo
 * @version 1.5
 */
public interface DialogField {
	/**
	 * this method must be called directly after constructor, in this case,
	 * system will create a hyper link label, and when the hyper link is
	 * clicked, the corresponding method on the listene will be called. A
	 * RuntimeException will throw out if this method is called after the label
	 * has been created.
	 * 
	 * @param listener
	 *            can't be null
	 */
	public abstract void setHyperLink(IHyperlinkListener listener);

	/**
	 * Sets the label of the dialog field.
	 */
	public abstract void setLabelText(String labeltext);

	// ------ change listener
	public abstract void setDialogFieldChangeListener(
			IDialogFieldChangeListener listener);

	public abstract void setDialogFieldApplyListener(
			IDialogFieldApplyListener listener);

	// ------- focus management
	public abstract boolean setFocus();

	// ------- layout helpers
	public abstract Control[] doFillIntoGrid(FormToolkit toolkit,
			Composite parent, int nColumns);

	/**
	 * Returns the number of columns of the dialog field. To be reimplemented by
	 * dialog field implementors.
	 */
	public abstract int getNumberOfControls();

	// ------- ui creation
	public abstract Control getLabelControl(FormToolkit _formToolkit,
			Composite parent);

	// --------- enable / disable management
	public abstract void setEnabled(boolean enabled);

	/**
	 * Gets the enable state of the dialog field.
	 */
	public abstract boolean isEnabled();

	/**
	 * Get attached data by key.
	 * 
	 * @param key
	 * @return the attached data object for key
	 */
	public abstract Object getAttachedData(Object key);

	/**
	 * You can attach any data to the DialogField, and get it using the
	 * <code>getAttachedData</code> method.
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void putAttachedData(Object key, Object value);

	/**
	 * this method give the DialogField a chance to set the correct column to
	 * grab horizontal space. In the implementation of this method, should only
	 * change the GridData of control, should not do anything else.
	 * 
	 * The caller is responsible to make sure the controls for the dialog field
	 * has been created before calling this method.
	 */
	public abstract void handleGrabHorizontal();

	public abstract boolean isRequired();

	public abstract void setToolTip(String toolTip);
}
