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

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Dialog field containing a label, text control and a button control.
 */
public class StringButtonDialogField extends StringDialogField {
	private Button _browseButton;

	private String _browseButtonLabel;

	private IStringButtonAdapter _stringButtonAdapter;

	private boolean _buttonEnabled;

	/**
	 * @param adapter
	 */
	public StringButtonDialogField(IStringButtonAdapter adapter) {
		_stringButtonAdapter = adapter;
		_browseButtonLabel = JSFUICommonPlugin
				.getResourceString("DialogField.Browse"); //$NON-NLS-1$
		_buttonEnabled = true;
	}

	/**
	 * Sets the label of the button.
	 * @param label 
	 */
	public void setButtonLabel(String label) {
		_browseButtonLabel = label;
	}

	// ------ adapter communication

	/**
	 * Programmatical pressing of the button
	 */
	public void changeControlPressed() {
		_stringButtonAdapter.changeControlPressed(this);
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control requiredLabel = getRequiredLabelControl(toolkit, parent);
		requiredLabel.setLayoutData(gridDataForLabel(1));

		Control label = getLabelControl(toolkit, parent);
		label.setLayoutData(gridDataForLabel(1));

		Text text = getTextControl(toolkit, parent);
		text.setLayoutData(gridDataForText(nColumns - 3));
		Button button = getChangeControl(toolkit, parent);
		button.setLayoutData(gridDataForButton(toolkit, button, text, 1));

		return new Control[] { requiredLabel, label, text, button };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	public int getNumberOfControls() {
		return 4;
	}

	/**
	 * @param toolkit
	 * @param button
	 * @param text
	 * @param span
	 * @return the grid data for the button
	 */
	protected static GridData gridDataForButton(FormToolkit toolkit,
			Button button, Text text, int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		// gd.heightHint = SWTUtil.getButtonHeightHint(button);
		gd.widthHint = LayoutUtil.getButtonWidthHint(button);
		gd.heightHint = LayoutUtil.getButtonHeightHint(toolkit, text);
		return gd;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created buttom widget.
	 * @param toolkit 
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has
	 *            already been created.
	 * @return the button
	 */
	public Button getChangeControl(FormToolkit toolkit, Composite parent) {
		if (_browseButton == null || _browseButton.isDisposed()) {
			assertCompositeNotNull(parent);
			if (toolkit != null) {
				_browseButton = toolkit.createButton(parent,
						_browseButtonLabel, SWT.PUSH);
			} else {
				_browseButton = new Button(parent, SWT.PUSH);
				_browseButton.setText(_browseButtonLabel);
			}
			_browseButton.setEnabled(isEnabled() && _buttonEnabled);
			_browseButton.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {
					changeControlPressed();
				}

				public void widgetSelected(SelectionEvent e) {
					changeControlPressed();
				}
			});

		}
		return _browseButton;
	}

	// ------ enable / disable management

	/**
	 * Sets the enable state of the button.
	 * @param enable 
	 */
	public void enableButton(boolean enable) {
		if (isOkToUse(_browseButton)) {
			_browseButton.setEnabled(isEnabled() && enable);
		}
		_buttonEnabled = enable;
	}

	/*
	 * @see DialogField#updateEnableState
	 */
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(_browseButton)) {
			_browseButton.setEnabled(isEnabled() && _buttonEnabled);
		}
	}

	/**
	 * @return Returns the _stringButtonAdapter.
	 */
	public IStringButtonAdapter getStringButtonAdapter() {
		return _stringButtonAdapter;
	}

	/**
	 * @param buttonAdapter
	 *            The _stringButtonAdapter to set.
	 */
	public void setStringButtonAdapter(IStringButtonAdapter buttonAdapter) {
		_stringButtonAdapter = buttonAdapter;
	}
}
