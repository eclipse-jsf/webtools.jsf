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

import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This is a wrapper to a dialog field, by adding a small button at the end. The
 * caller is responsible to provide the image or lable text and the button click
 * handler.
 * 
 * @author mengbo
 * @version 1.5
 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper
 */

public class DialogFieldWrapper implements DialogField, ISupportTextValue {
	private DialogField _wrappedDialogField;

	private Button _button;

	private boolean _buttonEnabled;

	private Image _buttonImage;

	private Image _disabledImage;

	private String _buttonLabel;

	private IStringButtonAdapter _adapter;

	/**
	 * 
	 */
	public DialogFieldWrapper(DialogField field, Image image,
			Image disabledImage) {
		super();
		if (!(field instanceof ISupportTextValue)) {
			throw new IllegalArgumentException(
					"Field must be ISupportTextValue");
		}
		_wrappedDialogField = field;
		_buttonImage = image;
		_disabledImage = disabledImage;
	}

	/**
	 * 
	 */
	public DialogFieldWrapper(DialogField field, String label) {
		super();
		if (!(field instanceof ISupportTextValue)) {
			throw new IllegalArgumentException(
					"Field must be ISupportTextValue");
		}
		_wrappedDialogField = field;
		_buttonLabel = label;
	}

	public void setButtonAdapter(IStringButtonAdapter adapter) {
		this._adapter = adapter;
		this.updateButtonControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue#setTextWithoutUpdate(java.lang.String)
	 */
	public void setTextWithoutUpdate(String value) {
		((ISupportTextValue) _wrappedDialogField).setTextWithoutUpdate(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue#getText()
	 */
	public String getText() {
		return ((ISupportTextValue) _wrappedDialogField).getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue#setText(java.lang.String)
	 */
	public void setText(String value) {
		((ISupportTextValue) _wrappedDialogField).setText(value);
	}

	// --------------------------------------------------------------------------------------------
	// wrapped method to add the attached button
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#doFillIntoGrid(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite, int)
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		Control[] wrappedControls = _wrappedDialogField.doFillIntoGrid(toolkit,
				parent, nColumns - 1);
		Control[] result = new Control[wrappedControls.length];

		Control button = getButton(toolkit, parent);
		button.setLayoutData(gridDataForButton(1));

		System.arraycopy(wrappedControls, 0, result, 0, wrappedControls.length);
		result[result.length - 1] = _button;
		return result;
	}

	/**
	 * @param span
	 * @return
	 */
	private GridData gridDataForButton(int span) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		gd.widthHint = gd.heightHint = 18;
		return gd;
	}

	/**
	 * @param toolkit
	 * @param parent
	 * @return
	 */
	private Control getButton(FormToolkit toolkit, Composite parent) {
		if (_button == null) {
			Assert.isNotNull(parent,
					"uncreated control requested with composite null"); //$NON-NLS-1$
			if (toolkit != null) {
				_button = toolkit.createButton(parent, "", SWT.PUSH);
				if (_buttonImage != null) {
					_button.setImage(_buttonImage);
				} else {
					_button.setText(_buttonLabel);
				}
			} else {
				_button = new Button(parent, SWT.PUSH);
				if (_buttonImage != null) {
					_button.setImage(_buttonImage);
				} else {
					_button.setText(_buttonLabel);
				}
			}
			_button.addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent e) {
					if (!_button.isEnabled() && _disabledImage != null) {
						Rectangle buttonBounds = _button.getBounds();
						Rectangle imageBounds = _disabledImage.getBounds();
						e.gc.drawImage(_disabledImage,
								(buttonBounds.width - imageBounds.width) / 2,
								(buttonBounds.height - imageBounds.height) / 2);
					}
				}
			});
			_button.setEnabled(isEnabled() && _buttonEnabled);
			_button.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {
					buttonPressed();
				}

				public void widgetSelected(SelectionEvent e) {
					buttonPressed();
				}
			});

		}
		return _button;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#getLabelControl(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public Control getLabelControl(FormToolkit _formToolkit, Composite parent) {
		return _wrappedDialogField.getLabelControl(_formToolkit, parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setHyperLink(org.eclipse.ui.forms.events.IHyperlinkListener)
	 */
	public void setHyperLink(IHyperlinkListener listener) {
		_wrappedDialogField.setHyperLink(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setLabelText(java.lang.String)
	 */
	public void setLabelText(String labeltext) {
		_wrappedDialogField.setLabelText(labeltext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setDialogFieldChangeListener(org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldChangeListener)
	 */
	public void setDialogFieldChangeListener(IDialogFieldChangeListener listener) {
		_wrappedDialogField.setDialogFieldChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setDialogFieldApplyListener(org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener)
	 */
	public void setDialogFieldApplyListener(IDialogFieldApplyListener listener) {
		_wrappedDialogField.setDialogFieldApplyListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setFocus()
	 */
	public boolean setFocus() {
		return _wrappedDialogField.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#getNumberOfControls()
	 */
	public int getNumberOfControls() {
		return _wrappedDialogField.getNumberOfControls() + 1;
	}

	public void setButtonEnabled(boolean enabled) {
		this._buttonEnabled = enabled;
		updateButtonControl();
	}

	public boolean isButtonEnabled() {
		return _buttonEnabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		_wrappedDialogField.setEnabled(enabled);
		updateButtonControl();
	}

	/**
	 * 
	 */
	private void updateButtonControl() {
		if (this._button != null) {
			this._button.setEnabled(this.isEnabled() && _buttonEnabled
					&& _adapter != null);
			_button.redraw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#isEnabled()
	 */
	public boolean isEnabled() {
		return _wrappedDialogField.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#getAttachedData(java.lang.Object)
	 */
	public Object getAttachedData(Object key) {
		return _wrappedDialogField.getAttachedData(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogField#putAttachedData(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void putAttachedData(Object key, Object value) {
		_wrappedDialogField.putAttachedData(key, value);
	}

	protected void buttonPressed() {
		if (_adapter != null) {
			_adapter.changeControlPressed(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#handleGrabHorizontal()
	 */
	public void handleGrabHorizontal() {
		_wrappedDialogField.handleGrabHorizontal();
	}

	public DialogField getWrappedDialogField() {
		return _wrappedDialogField;
	}

	public boolean isRequired() {
		return _wrappedDialogField.isRequired();
	}

	public void setToolTip(String toolTip) {
		_wrappedDialogField.setToolTip(toolTip);
	}
}
