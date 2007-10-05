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
import org.eclipse.swt.widgets.Shell;
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
	 * @param labeltext 
	 */
	public abstract void setLabelText(String labeltext);

	// ------ change listener
	/**
	 * Listener that is notified on a field change
	 * @param listener
	 */
	public abstract void setDialogFieldChangeListener(
			IDialogFieldChangeListener listener);

	/**
	 * Listener  that is notified on a field apply
	 * @param listener
	 */
	public abstract void setDialogFieldApplyListener(
			IDialogFieldApplyListener listener);

	// ------- focus management
	/**
	 * Tries to set the focus to the dialog field. Returns <code>true</code>
	 * if the dialog field can take focus. To be reimplemented by dialog field
	 * implementors.
	 * @return true if dialog field can take focus
	 */
	public abstract boolean setFocus();

	/**
	 * Creates all controls of the dialog field and fills it to a composite. The
	 * composite is assumed to have <code>MGridLayout</code> as layout. The
	 * dialog field will adjust its controls' spans to the number of columns
	 * given. To be reimplemented by dialog field implementors.
	 * @param toolkit 
	 * @param parent 
	 * @param nColumns 
	 * @return the controls
	 */
	public abstract Control[] doFillIntoGrid(FormToolkit toolkit,
			Composite parent, int nColumns);

	/**
	 * Returns the number of columns of the dialog field. To be reimplemented by
	 * dialog field implementors.
	 * @return the number of columns
	 */
	public abstract int getNumberOfControls();

	// ------- ui creation
	/**
	 * @param _formToolkit
	 * @param parent
	 * @return the label control
	 */
	public abstract Control getLabelControl(FormToolkit _formToolkit,
			Composite parent);

	// --------- enable / disable management
	/**
	 * @param enabled
	 */
	public abstract void setEnabled(boolean enabled);

	/**
	 * Gets the enable state of the dialog field.
	 * @return true if enabled is set
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

	/**
	 * @return true if is required field
	 */
	public abstract boolean isRequired();

	/**
	 * @param toolTip
	 */
	public abstract void setToolTip(String toolTip);
	
	/**
	 * @return the field's enclosing shell or null if none
	 */
	public Shell getShell();
}
