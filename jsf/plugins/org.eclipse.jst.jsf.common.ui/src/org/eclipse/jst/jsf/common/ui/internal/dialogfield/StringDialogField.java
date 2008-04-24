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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Dialog field containing a label and a text control.
 */
public class StringDialogField extends DialogFieldBase implements
		ISupportTextValue {
	final static private int TEXT_WIDTH_HINT = 10;

	private String _text;

	private Text _textControl;

	private ModifyListener _modifyListener;

	private int _numRows;

	/**
	 * whether there is change in the UI but not fire applied event yet.
	 */
	private boolean _pending = false;

	/**
	 * default constructor
	 * numRows == 1
	 */
	public StringDialogField() {
		this(1);
		_text = ""; //$NON-NLS-1$
	}

	/**
	 * @param numRows
	 */
	public StringDialogField(int numRows) {
		super();
		_text = ""; //$NON-NLS-1$
		_numRows = numRows;
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	public Control[] doFillIntoGrid(FormToolkit kit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control requiredLabel = getRequiredLabelControl(kit, parent);
		requiredLabel.setLayoutData(gridDataForLabel(1));

		Control label = getLabelControl(kit, parent);
		label.setLayoutData(gridDataForLabel(1));

		Text text = getTextControl(kit, parent);
		int heightHint = -1;
		if (_numRows > 1) {
			heightHint = getDialogUnits(parent).getHeight() * _numRows;
		}
		text.setLayoutData(gridDataForText(nColumns - 2, heightHint));

		return new Control[] { requiredLabel, label, text };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	public int getNumberOfControls() {
		return 3;
	}

	protected static GridData gridDataForLabel(int span) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		return gd;
	}

	/**
	 * @param span
	 * @param heightHint
	 * @return the grid data for text
	 */
	protected static GridData gridDataForText(int span, int heightHint) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.heightHint = heightHint;
		gd.horizontalSpan = span;
		gd.widthHint = TEXT_WIDTH_HINT;
		return gd;
	}

	/**
	 * @param span
	 * @return the grid data for text
	 */
	protected static GridData gridDataForText(int span) {
		GridData gd = gridDataForText(span, -1);
		return gd;
	}

	// ------- focus methods

	/*
	 * @see DialogField#setFocus
	 */
	public boolean setFocus() {
		if (isOkToUse(_textControl)) {
			_textControl.setFocus();
			_textControl.setSelection(0, _textControl.getText().length());
		}
		return true;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created text control.
	 * @param toolkit 
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> when the widget
	 *            has already been created.
	 * @return the text control
	 */
	public Text getTextControl(FormToolkit toolkit, Composite parent) {
		if (_textControl == null || _textControl.isDisposed()) {
			assertCompositeNotNull(parent);
			_modifyListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					doModifyText(e);
				}
			};

			if (toolkit != null) {
				if (_numRows <= 1) {
					_textControl = toolkit.createText(parent, ""); //$NON-NLS-1$
				} else {
					_textControl = toolkit.createText(parent, "", SWT.V_SCROLL); //$NON-NLS-1$
				}
			} else {
				if (_numRows <= 1) {

					_textControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
				} else {
					_textControl = new Text(parent, SWT.V_SCROLL | SWT.WRAP
							| SWT.BORDER);
				}
			}

			_textControl.setText(_text);
			_textControl.setFont(parent.getFont());
			_textControl.addModifyListener(_modifyListener);
			_textControl.addFocusListener(new FocusAdapter() {

				public void focusLost(FocusEvent e) {
					doFocusLost(e);
				}

			});
			_textControl.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					doKeyReleased(e);
				}
			});

			_textControl.setEnabled(isEnabled());

			_textControl.setToolTipText(getToolTip());
		}
		return _textControl;
	}

	/**
	 * @param e
	 */
	protected void doKeyReleased(KeyEvent e) {
		if (e.character == '\r') {
			// commit value
			if (_pending) {
				_pending = false;
				dialogFieldApplied();
			}
		}
	}

	/**
	 * @param e
	 */
	protected void doFocusLost(FocusEvent e) {
		if (_pending) {
			_pending = false;
			dialogFieldApplied();
		}
	}

	/**
	 * some get changed in the Text. As in <code>setText</code> and
	 * <code>setTextWithoutUpdate</code> we removed the listener, so this must
	 * be user typing in the text field.
	 * 
	 * @param e
	 */
	private void doModifyText(ModifyEvent e) {
		if (isOkToUse(_textControl)) {
			_text = _textControl.getText();
		}
		_pending = true;
		dialogFieldChanged();
	}

	// ------ enable / disable management

	/*
	 * @see DialogField#updateEnableState
	 */
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(_textControl)) {
			_textControl.setEnabled(isEnabled());
		}
	}

	// ------ text access

	/**
	 * Gets the text. Can not be <code>null</code>
	 */
	public String getText() {
		return _text;
	}

	/**
	 * Sets the text. Triggers a dialog-changed event.
	 */
	public void setText(String text) {
		setTextWithoutUpdate(text);
		dialogFieldChangedAndApplied();
	}

	/**
	 * Sets the text without triggering a dialog-changed event.
	 */
	public void setTextWithoutUpdate(String text) {
		// reset _pending state.
		_pending = false;

		if (text == null)
			text = ""; //$NON-NLS-1$
		_text = text;
		if (isOkToUse(_textControl)) {
			_textControl.removeModifyListener(_modifyListener);
			_textControl.setText(text);
			_textControl.addModifyListener(_modifyListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#handleGrabHorizontal()
	 */
	public void handleGrabHorizontal() {
		LayoutUtil.setGrabHorizontal(_textControl, true);
	}
}
